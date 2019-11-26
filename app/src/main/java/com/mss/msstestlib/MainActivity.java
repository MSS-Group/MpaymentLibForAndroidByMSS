package com.mss.msstestlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.testlib.UserManager;
import com.mss.testlib.dialog.OnLoginClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.et_name);
        final TextView tvUserAmout = findViewById(R.id.tv_user_amount);
        final TextView tvUserInfos = findViewById(R.id.tv_user_infos);
        Button btGetAmount = findViewById(R.id.bt_get_amount);
        Button btLogin = findViewById(R.id.bt_login);

        btGetAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvUserAmout.setText("Your amount is " + UserManager.getUserAmount(etName.getText().toString()));
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.showPaymentDialog(MainActivity.this,
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
        });

    }
}
