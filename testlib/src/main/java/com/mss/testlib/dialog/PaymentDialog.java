package com.mss.testlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Layout;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.testlib.R;
import com.mss.testlib.utils.passcodeview.PassCodeView;

public class PaymentDialog {

    private Context context;
    private String amount;
    private int state = 0;
    private ImageView dlg_one_button_iv_icon;
    private LinearLayout layoutPayment, layoutLogin;
    private TextView dlg_one_button_tv_title, dlg_one_button_tv_message;
    private Button dlg_one_button_btn_ok, dlg_one_button_btn_login;
    private Dialog loginDialog;
    private ProgressBar progressBar;
    private OnLoginClickListener onLoginClickListener;
    private static final double DIALOG_WINDOW_WIDTH = 0.85;
    private PassCodeView passCodeView;

    public PaymentDialog(Context context, String amount) {
        this.context = context;
        this.amount = amount;
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
        dlg_one_button_tv_title.setTextColor(context.getResources().getColor(R.color.zxing_possible_result_points));
        dlg_one_button_tv_title.setText("Paiement !");
        dlg_one_button_tv_message.setText("Vous voulez effectuer un paiement d'un montant de :" + amount + " ?");
        progressBar = loginDialog.findViewById(R.id.progressBar);
        dlg_one_button_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoginClickListener != null) {

                    if (state == 0) {
                        loading(state);
                        dlg_one_button_iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_close));
                        onLoginClickListener.payment(1, "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve");
                        state++;
                    } else if (state == 1) {
                        //loading(state);
                        layoutPayment.setVisibility(View.GONE);
                        layoutLogin.setVisibility(View.VISIBLE);
                        onLoginClickListener.payment(2, "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve");
                        dlg_one_button_btn_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (passCodeView.getPassCodeText().length() == 4) {
                                    layoutPayment.setVisibility(View.VISIBLE);
                                    layoutLogin.setVisibility(View.GONE);
                                    loading(state);
                                    dlg_one_button_iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_checked));
                                    onLoginClickListener.payment(0, "sfzshvljefùperfeùlkhveeùojetvbtlhveovejveùpigve");
                                    state++;
                                } else {
                                    passCodeView.setError(true);
                                }
                            }
                        });
                        state++;
                    } /*else if (state == 2) {

                    }*/ else {
                        loginDialog.dismiss();
                    }
                }
            }
        });
    }

    public void show() {
        loginDialog.show();
    }

    private void close() {
        loginDialog.dismiss();
    }

    public void setOnLoginClickListener(OnLoginClickListener onLoginClickListener) {
        this.onLoginClickListener = onLoginClickListener;
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
        dlg_one_button_tv_title.setTextColor(context.getResources().getColor(R.color.zxing_possible_result_points));
        dlg_one_button_tv_title.setText("Loading !");
        dlg_one_button_tv_message.setText("En cours de paiement ...");
        dlg_one_button_iv_icon.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        dlg_one_button_btn_ok.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        dlg_one_button_btn_ok.setEnabled(false);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                disLoading(state);
            }
        }, 5000);
    }

    private void disLoading(int state) {
        switch (state) {
            case 0:
                dlg_one_button_tv_title.setTextColor(context.getResources().getColor(R.color.red_500));
                dlg_one_button_tv_title.setText("Error !");
                dlg_one_button_tv_message.setText("Echec de paiement .");
                break;
            case 1:
                dlg_one_button_tv_title.setTextColor(context.getResources().getColor(R.color.green_500));
                dlg_one_button_tv_title.setText("SUCCESS !");
                dlg_one_button_tv_message.setText("Paiement effectué avec succès.");
                break;
        }
        dlg_one_button_iv_icon.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        dlg_one_button_btn_ok.getBackground().setColorFilter(null);
        dlg_one_button_btn_ok.setEnabled(true);
    }

}
