package com.mss.testlib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.FragmentActivity;

import com.chaos.view.PinView;
import com.mss.testlib.BuildConfig;
import com.mss.testlib.R;
import com.mss.testlib.UserManager;
import com.mss.testlib.data.ApiClient;
import com.mss.testlib.data.model.Activation;
import com.mss.testlib.data.model.PreActivation;
import com.mss.testlib.data.model.Token;
import com.mss.testlib.utils.IdentificationGenerator;
import com.mss.testlib.utils.Shared;
import com.mss.testlib.utils.constants.PaymentResult;
import com.mss.testlib.utils.encrypt.RSA;
import com.santalu.widget.MaskEditText;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoginDialog {

    private Context context;
    private Dialog loginDialog;
    private PaymentCallback paymentCallback;
    private TextView tvTitle;
    private Button btOk;
    private Button btOkOTP;
    private MaskEditText etMsisdn;
    private PinView pvOTP;
    private LinearLayout layoutMsisdn;
    private LinearLayout layoutLoading;
    private LinearLayout layoutOTP;
    private AppCompatSpinner spNumberPrefix;
    private AppCompatImageView checkMark;
    private ColorfulRingProgressView progressBar;
    private ImageView ivClose;

    public LoginDialog(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        loginDialog = new Dialog(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_login,null);
        loginDialog.setContentView(v);
        loginDialog.setCancelable(false);
        etMsisdn = v.findViewById(R.id.met_msisdn_device);
        pvOTP = v.findViewById(R.id.pv_otp);
        tvTitle = v.findViewById(R.id.tv_title);
        btOk = v.findViewById(R.id.btn_ok);
        btOkOTP = v.findViewById(R.id.btn_otp_ok);
        layoutLoading = v.findViewById(R.id.layout_loading);
        layoutMsisdn = v.findViewById(R.id.layout_msisdn);
        layoutOTP = v.findViewById(R.id.layout_otp);
        spNumberPrefix = v.findViewById(R.id.sp_number_prefix);
        checkMark = v.findViewById(R.id.iv_check_mark);
        progressBar = v.findViewById(R.id.progressBar);
        ivClose = v.findViewById(R.id.iv_close);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentCallback.payment(PaymentResult.CANCELED,"Opération annulée");
                loginDialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = loginDialog.getWindow();
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loginDialog.getWindow().getAttributes().windowAnimations = R.style.InfoPopup;
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (isEmpty(etMsisdn) || etMsisdn.getRawText().length() < 8)
                    Toast.makeText(context,"Veuillez saisir le numéro de téléphone",Toast.LENGTH_SHORT).show();
                else
                {
                    loadingToOTP();
                }
            }
        });

        btOkOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (isEmpty(pvOTP) || pvOTP.getText().length()<4)
                    Toast.makeText(context,"Veuillez saisir le code OTP",Toast.LENGTH_SHORT).show();
                else {
                    loadingToPayment();
                }
            }
        });

        spNumberPrefix.setAdapter(new ArrayAdapter<String>(context,R.layout.spinner_dropdown_item, Arrays.asList("+216","+212")));

        etMsisdn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 10)
                    checkMark.setVisibility(View.VISIBLE);
                else
                    checkMark.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Bitmap myBitmap = ((BitmapDrawable)checkMark.getDrawable()).getBitmap();
        Bitmap newBitmap = addGradient(myBitmap);
        checkMark.setImageDrawable(new BitmapDrawable(context.getResources(), newBitmap));

        progressBar.animateIndeterminate();
    }



    public Bitmap addGradient(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);

        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height, Color.parseColor("#33d49e"), Color.parseColor("#17a374"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);

        return updatedBitmap;
    }

    public void show(){
        loginDialog.show();
    }

    public void setPaymentCallback(PaymentCallback paymentCallback) {
        this.paymentCallback = paymentCallback;
    }

    private boolean isEmpty(EditText editText){
       return editText.getText().toString().length() == 0;
    }

    private void loadingToOTP() {
        IdentificationGenerator id = new IdentificationGenerator(context);
        layoutMsisdn.setVisibility(View.GONE);
        layoutLoading.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.GONE);
        ApiClient.getApiServices()
                .preActivation("216"+etMsisdn.getRawText(),id.getIMEI(),id.getIdSession(etMsisdn.getRawText()),"C")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PreActivation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(PreActivation preActivation) {
                        layoutLoading.setVisibility(View.GONE);
                        ivClose.setVisibility(View.VISIBLE);
                        String resultCode = preActivation.getResultCode();
                        if (resultCode.equals("0")){
                            if (BuildConfig.DEBUG) {
                                Toast.makeText(context, "OTP : " + preActivation.getIdTransaction(), Toast.LENGTH_LONG).show();
                                layoutOTP.setVisibility(View.VISIBLE);
                            }
                        }else {
                            Toast.makeText(context, Shared.CheckError(context,resultCode), Toast.LENGTH_LONG).show();
                            layoutMsisdn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ivClose.setVisibility(View.VISIBLE);
                        layoutLoading.setVisibility(View.GONE);
                        Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                        layoutMsisdn.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void loadingToPayment() {
        final IdentificationGenerator id = new IdentificationGenerator(context);
        layoutOTP.setVisibility(View.GONE);
        layoutLoading.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.GONE);
        final String otp = pvOTP.getText().toString();
        pvOTP.setText("");
        try {
            ApiClient.getApiServices().token(id.getIMEI(), RSA.Encrypt("216" + etMsisdn.getRawText()))
                    .flatMap(new Function<Token, SingleSource<Activation>>() {
                        @Override
                        public SingleSource<Activation> apply(Token token1) throws Exception {
                            String token = token1.getAccessToken();
                            return ApiClient.getApiServices()
                                    .activation("216"+etMsisdn.getRawText(),id.getIMEI(),id.getIdSession(etMsisdn.getRawText()),"C",otp,token);
                        }
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Activation>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Activation activation) {
                            layoutLoading.setVisibility(View.GONE);
                            ivClose.setVisibility(View.VISIBLE);
                            String resultCode = activation.getResultCode();
                            if (Integer.valueOf(resultCode) == 0){
                                toPayment();
                            }else {
                                Toast.makeText(context, Shared.CheckError(context,resultCode), Toast.LENGTH_LONG).show();
                                layoutMsisdn.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ivClose.setVisibility(View.VISIBLE);
                            layoutLoading.setVisibility(View.GONE);
                            Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                            layoutMsisdn.setVisibility(View.VISIBLE);
                        }
                    });
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

    }

    private void toPayment() {
        loginDialog.dismiss();
        UserManager.showPaymentDialog(context,
                etMsisdn.getRawText(),
                "21633225580",                    //idClient
                "39565976545795695", //idClientMobicash
                "56956U78Y30U466086086",           //idMerchant
                "5690557U07580757U0773",    //idMerchantMobicash
                "192035558706770787",//idTransaction
                "1.2.1",//idVersion
                "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve",      //token
                "10000",//amount
                 paymentCallback);
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = ((FragmentActivity)context).getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(context);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
