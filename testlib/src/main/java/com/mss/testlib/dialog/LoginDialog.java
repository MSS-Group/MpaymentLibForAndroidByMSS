package com.mss.testlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mss.testlib.R;

public class LoginDialog {

    private Context context;
    private Dialog loginDialog;
    private OnLoginClickListener onLoginClickListener;

    public LoginDialog(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        loginDialog = new Dialog(context);
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_login,null);
        loginDialog.setContentView(v);

        final EditText etUsername = v.findViewById(R.id.et_username);
        final EditText etPassword = v.findViewById(R.id.et_password);
        Button btLogin = v.findViewById(R.id.bt_login);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = loginDialog.getWindow();
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(etUsername)||isEmpty(etPassword))
                    Toast.makeText(context,"Veuillez saisir vos coordonnées",Toast.LENGTH_SHORT).show();
                else
                {
                    if (onLoginClickListener!=null) {
                        onLoginClickListener.login(etUsername.getText().toString(), etPassword.getText().toString());
                        loginDialog.dismiss();
                    }
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
}
