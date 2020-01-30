package com.mss.testlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.testlib.R;
import com.mss.testlib.data.ApiClient;
import com.mss.testlib.data.model.Authentification;
import com.mss.testlib.data.model.Token;
import com.mss.testlib.data.model.TransfertProInput;
import com.mss.testlib.data.model.TransfertProOutput;
import com.mss.testlib.utils.IdentificationGenerator;
import com.mss.testlib.utils.Shared;
import com.mss.testlib.utils.constants.PaymentResult;
import com.mss.testlib.utils.custom.CustomText;
import com.mss.testlib.utils.encrypt.CryptoHash;
import com.mss.testlib.utils.encrypt.RSA;
import com.mss.testlib.utils.passcodeview.PassCodeView;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

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

public class PaymentDialog {

    private Context context;
    private String amount;
    private String msisdn;
    private String idClient;
    private int paymentResultCode;
    private String paymentMessage;
    private int state = 0;
    private ImageView dlg_one_button_iv_icon;
    private ImageView ivClose;
    private LinearLayout layoutPayment, layoutLogin;
    private TextView dlg_one_button_tv_title, dlg_one_button_tv_message;
    private Button dlg_one_button_btn_ok, dlg_one_button_btn_login;
    private Dialog loginDialog;
    private ColorfulRingProgressView progressBar;
    private PaymentCallback paymentCallback;
    private static final double DIALOG_WINDOW_WIDTH = 0.85;
    private PassCodeView passCodeView;

    public PaymentDialog(Context context, String msisdn, String idClient, String amount) {
        this.context = context;
        this.amount = amount;
        this.msisdn = msisdn;
        this.idClient = idClient;
        init();
    }

    private void init() {
        loginDialog = new Dialog(context);

        loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loginDialog.setCancelable(false);
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loginDialog.getWindow().getAttributes().windowAnimations = R.style.InfoPopup;
        View v = LayoutInflater.from(context).inflate(R.layout.pop_up_payment_validation1, null);
        loginDialog.setContentView(v);
        //setDialogWindowWidth(loginDialog,DIALOG_WINDOW_WIDTH);
        layoutPayment = loginDialog.findViewById(R.id.layout_payment);
        layoutLogin = loginDialog.findViewById(R.id.layout_login);
        dlg_one_button_iv_icon = loginDialog.findViewById(R.id.dlg_one_button_iv_icon);
        dlg_one_button_btn_ok = loginDialog.findViewById(R.id.dlg_one_button_btn_ok);
        dlg_one_button_btn_login = loginDialog.findViewById(R.id.dlg_one_button_btn_login);
        dlg_one_button_tv_title = loginDialog.findViewById(R.id.dlg_one_button_tv_title);
        dlg_one_button_tv_message = loginDialog.findViewById(R.id.dlg_one_button_tv_message);
        passCodeView = loginDialog.findViewById(R.id.pass_code_view);
        ivClose = loginDialog.findViewById(R.id.iv_close);
        dlg_one_button_tv_title.setText(context.getResources().getString(R.string.payment_title));
        dlg_one_button_tv_message.setText(context.getResources().getString(R.string.payment_message, amount));
        progressBar = loginDialog.findViewById(R.id.progressBar);
        dlg_one_button_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passCodeView.getPassCodeText().length() == 4) {
                    layoutPayment.setVisibility(View.VISIBLE);
                    layoutLogin.setVisibility(View.GONE);
                    loading(state);


                } else {
                    passCodeView.setError(true);
                }
            }
        });
        dlg_one_button_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentCallback != null) {

                    if (state == 1) {
                        loading(state);

                    } else if (state == 2) {
                        paymentCallback.payment(paymentResultCode,paymentMessage);
                        loginDialog.dismiss();
                    }
                }
            }
        });

        progressBar.animateIndeterminate();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
            }
        });
    }

    public void show() {
        loginDialog.show();
    }

    private void close() {
        loginDialog.dismiss();
    }

    public void setPaymentCallback(PaymentCallback paymentCallback) {
        this.paymentCallback = paymentCallback;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().length() == 0;
    }

    private void setDialogWindowWidth(Dialog dialog, double width) {
        Window window = dialog.getWindow();
        Point size = new Point();
        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int maxWidth = size.x;
            window.setLayout((int) (maxWidth * width), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }

    private void loading(final int state) {
        dlg_one_button_tv_title.setText(context.getResources().getString(R.string.loading_title));
        dlg_one_button_tv_message.setText(context.getResources().getString(R.string.loading_message));
        if (state == 0)
            dlg_one_button_tv_message.setVisibility(View.GONE);
        else
            dlg_one_button_tv_message.setVisibility(View.VISIBLE);
        dlg_one_button_iv_icon.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        dlg_one_button_btn_ok.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        dlg_one_button_btn_ok.setEnabled(false);
        if (state == 0)
            authentication();
        else
            transfertPro();
    }

    private void transfertPro() {
        final IdentificationGenerator id = new IdentificationGenerator(context);
        final String idSession = id.getIdSession(msisdn);

        try {
            ApiClient.getApiServices().token(id.getIMEI(), RSA.Encrypt("216" + msisdn))
                    .flatMap(new Function<Token, SingleSource<TransfertProOutput>>() {
                        @Override
                        public SingleSource<TransfertProOutput> apply(Token token) throws Exception {
                            TransfertProInput input = new TransfertProInput("216" + msisdn
                                    , idClient
                                    , id.getIMEI()
                                    , idSession
                                    , RSA.Encrypt(passCodeView.getPassCodeText())
                                    , amount
                                    , "Mobicash"
                                    , "T"
                                    , "1"
                                    , token.getAccessToken());
                            return ApiClient.getApiServices().transfertPro(input);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<TransfertProOutput>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(TransfertProOutput transfertProOutput) {
                            String resultCode = transfertProOutput.getResultCode();

                            if (Integer.valueOf(resultCode) == 0) {
                                paymentMessage = "Paiement effectué avec succès";
                                paymentResultCode = PaymentResult.SUCCESS;
                                dlg_one_button_iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.icon5));
                                disLoading(1);
                                state++;
                            } else {
                                layoutPayment.setVisibility(View.VISIBLE);
                                layoutLogin.setVisibility(View.GONE);
                                dlg_one_button_iv_icon.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                paymentResultCode = PaymentResult.EROOR;
                                paymentMessage = Shared.CheckError(context,resultCode);
                                dlg_one_button_btn_ok.getBackground().setColorFilter(null);
                                dlg_one_button_btn_ok.setEnabled(true);
                                dlg_one_button_iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.icon6));
                                disLoading(2);
                                state++;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        } catch (IllegalBlockSizeException e) {
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

    private void authentication() {
        ivClose.setVisibility(View.GONE);
        final IdentificationGenerator id = new IdentificationGenerator(context);
        final String idSession = id.getIdSession(msisdn);
        try {
            ApiClient.getApiServices().token(id.getIMEI(), RSA.Encrypt("216" + msisdn))
                    .flatMap(new Function<Token, SingleSource<Authentification>>() {
                        @Override
                        public SingleSource<Authentification> apply(Token token1) throws Exception {
                            String token = token1.getAccessToken();
                            return ApiClient.getApiServices()
                                    .authentificationWithHachage("216" + msisdn, id.getIMEI(), idSession, "C"
                                            , RSA.Encrypt(passCodeView.getPassCodeText())
                                            , "************"
                                            , CryptoHash.sha256(("216" + msisdn) + id.getIMEI() + idSession + "C" + passCodeView.getPassCodeText() + "aed11c1b6ff44ffdcc9fbe634bd4c55e8e623415d3b27fc9424f1ea7c425515f")
                                            , token);
                        }
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Authentification>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Authentification authentification) {
                            String resultCode = authentification.getResultCode();
                            if (Integer.valueOf(resultCode) == 0) {
                                paymentCallback.payment(Integer.valueOf(resultCode), "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve");
                                disLoading(state);
                                state++;
                            } else {
                                layoutPayment.setVisibility(View.GONE);
                                layoutLogin.setVisibility(View.VISIBLE);
                                dlg_one_button_iv_icon.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                dlg_one_button_btn_ok.getBackground().setColorFilter(null);
                                dlg_one_button_btn_ok.setEnabled(true);
                                if (Shared.CheckError(context,resultCode).equals(context.getResources().getString(R.string.erreur_111)))
                                {
                                    paymentResultCode = Integer.valueOf(resultCode);
                                    paymentMessage = "Authentification échoué, veuillez vérifier votre compte Mobicash";
                                    dlg_one_button_iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.icon6));
                                    disLoading(2);
                                }
                                else
                                {
                                    ivClose.setVisibility(View.VISIBLE);
                                    Toast.makeText(context, Shared.CheckError(context,resultCode), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(context, "Une erreur est survenue ", Toast.LENGTH_LONG).show();
                            layoutPayment.setVisibility(View.GONE);
                            layoutLogin.setVisibility(View.VISIBLE);
                            dlg_one_button_iv_icon.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            dlg_one_button_btn_ok.getBackground().setColorFilter(null);
                            dlg_one_button_btn_ok.setEnabled(true);
                            ivClose.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (IllegalBlockSizeException e) {
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

    private void disLoading(int state) {
        switch (state) {
            case 0:
                dlg_one_button_tv_title.setText(context.getResources().getString(R.string.payment_title));
                dlg_one_button_tv_message.setText(context.getResources().getString(R.string.payment_message, CustomText.getInstance().stringToAmountWithTND(context,amount)));
                dlg_one_button_tv_message.setVisibility(View.VISIBLE);
                break;
            case 1:
                dlg_one_button_tv_title.setText(context.getResources().getString(R.string.success_title));
                dlg_one_button_tv_message.setText(context.getResources().getString(R.string.success_message));
                break;
            case 2:
                dlg_one_button_tv_title.setText(context.getResources().getString(R.string.error_title));
                dlg_one_button_tv_message.setText(paymentMessage);
                break;
        }
        dlg_one_button_iv_icon.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        dlg_one_button_btn_ok.getBackground().setColorFilter(null);
        dlg_one_button_btn_ok.setEnabled(true);
    }

}
