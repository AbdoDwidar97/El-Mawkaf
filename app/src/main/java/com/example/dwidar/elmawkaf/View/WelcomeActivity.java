package com.example.dwidar.elmawkaf.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dwidar.elmawkaf.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button WelcomeCustomerBtn;
    private  Button WelcomeDriverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeCustomerBtn = (Button) findViewById(R.id.BtnCustomer);
        WelcomeDriverBtn = (Button) findViewById(R.id.Btndriver);

        WelcomeCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_custMod(v);
            }
        });


        WelcomeDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_driverMod(v);
            }
        });
    }

    public void go_driverMod(View view) {

        Intent driverLogin = new Intent(this,DriverLoginActivity.class);
        startActivity(driverLogin);

    }

    public void go_custMod(View view) {
        Intent customerLogin = new Intent(this,CustomerLoginActivity.class);
        startActivity(customerLogin);
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
