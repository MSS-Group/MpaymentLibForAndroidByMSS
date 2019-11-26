package com.mss.testlib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.chaos.view.PinView;
import com.mss.testlib.BuildConfig;
import com.mss.testlib.R;
import com.mss.testlib.UserManager;
import com.mss.testlib.data.ApiClient;
import com.mss.testlib.data.model.CheckOtp;
import com.mss.testlib.data.model.OtpGeneration;
import com.mss.testlib.data.model.Token;
import com.mss.testlib.utils.encrypt.RSA;
import com.santalu.widget.MaskEditText;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
    private OnLoginClickListener onLoginClickListener;
    private TextView tvTitle;
    private Button btOk;
    private Button btOkOTP;
    private MaskEditText etMsisdn;
    private PinView pvOTP;
    private LinearLayout layoutMsisdn;
    private LinearLayout layoutLoading;
    private LinearLayout layoutOTP;

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
    }

    public void show(){
        loginDialog.show();
    }

    public void setOnLoginClickListener(OnLoginClickListener onLoginClickListener) {
        this.onLoginClickListener = onLoginClickListener;
    }

    private boolean isEmpty(EditText editText){
       return editText.getText().toString().length() == 0;
    }

    private void loadingToOTP() {
        layoutMsisdn.setVisibility(View.GONE);
        layoutLoading.setVisibility(View.VISIBLE);
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toOTP();
            }
        }, 5000);*/
        ApiClient.getApiServices()
                .generateOtp("216"+etMsisdn.getRawText(),"352085032057236","22222229999990000112","C","Mobicash","Inscription")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OtpGeneration>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OtpGeneration otpGeneration) {
                        layoutLoading.setVisibility(View.GONE);
                        String resultCode = otpGeneration.getResultCode();
                        if (resultCode.equals("000")){
                            if (BuildConfig.DEBUG) {
                                Toast.makeText(context, "OTP : " + otpGeneration.getIdTransaction(), Toast.LENGTH_LONG).show();
                                layoutOTP.setVisibility(View.VISIBLE);
                            }
                        }else {
                            Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                            layoutMsisdn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        layoutLoading.setVisibility(View.GONE);
                        Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                        layoutMsisdn.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void loadingToPayment() {
        layoutOTP.setVisibility(View.GONE);
        layoutLoading.setVisibility(View.VISIBLE);
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toPayment();
            }
        }, 5000);*/
        try {
            ApiClient.getApiServices().token("352085032057236", RSA.Encrypt("216" + etMsisdn.getRawText()))
                    .flatMap(new Function<Token, SingleSource<CheckOtp>>() {
                        @Override
                        public SingleSource<CheckOtp> apply(Token token1) throws Exception {
                            String token = token1.getAccessToken();
                            return ApiClient.getApiServices()
                                    .checkOtp("216"+etMsisdn.getRawText(),"352085032057236","22222229999990000112","C","Mobicash",pvOTP.getText().toString(),token);
                        }
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<CheckOtp>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(CheckOtp checkOtp) {
                            layoutLoading.setVisibility(View.GONE);
                            String resultCode = checkOtp.getResultCode();
                            if (resultCode.equals("000")){
                                toPayment();
                            }else {
                                Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                                layoutMsisdn.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
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
                "348945Y475865",                    //idClient
                "39565976545795695", //idClientMobicash
                "56956U78Y30U466086086",           //idMerchant
                "5690557U07580757U0773",    //idMerchantMobicash
                "192035558706770787",//idTransaction
                "1.2.1",//idVersion
                "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve",      //token
                "10 DT",//amount
                new OnLoginClickListener() {
                    @Override
                    public void payment(int resultCode, String token) {

                        Log.e("Payment ResultCode =", String.valueOf(resultCode));
                        Log.e("Payment Token =", token);

                    }
                });
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
