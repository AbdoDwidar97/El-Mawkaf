package com.example.dwidar.elmawkaf.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dwidar.elmawkaf.Model.Contracts.CabLoginContract;
import com.example.dwidar.elmawkaf.Presenter.CabLoginPresenter;
import com.example.dwidar.elmawkaf.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginActivity extends AppCompatActivity implements CabLoginContract.IView {

    private TextView LblCreateNewAccountDriver;
    private Button BtnDriverLogin;
    private CabLoginPresenter presenter;
    private ProgressDialog loadingBar;

    private EditText TxtEmpID;
    private EditText TxtPassword;


    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);


        //mAuth = FirebaseAuth.getInstance();

        presenter = new CabLoginPresenter(this);
        loadingBar = new ProgressDialog(this);

        TxtEmpID = (EditText) findViewById(R.id.TxtDriverEmail);
        TxtPassword = (EditText) findViewById(R.id.TxtPwdDriver);

        BtnDriverLogin = (Button) findViewById(R.id.BtnDriverLogin);
        LblCreateNewAccountDriver = (TextView) findViewById(R.id.LblCreateNewAccountDriver);
        LblCreateNewAccountDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_Register_driver(v);
            }
        });


        BtnDriverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CabLogin(v);

            }
        });
    }

    void go_Register_driver(View view)
    {
        Intent DriverRegister = new Intent(this , RegisterDriverActivity.class);
        startActivity(DriverRegister);
    }

    void CabLogin(View view)
    {
        String EmpID = TxtEmpID.getText().toString();
        String Pwd = TxtPassword.getText().toString();

        if (EmpID.isEmpty()) Toast.makeText(DriverLoginActivity.this,"Please enter Employee ID !",Toast.LENGTH_SHORT).show();
        else if (Pwd.isEmpty()) Toast.makeText(DriverLoginActivity.this,"Please enter Password !",Toast.LENGTH_SHORT).show();
        else presenter.CabLogin(EmpID , Pwd);

    }

    @Override
    public void ShowInvalidMessage() {

        Toast.makeText(DriverLoginActivity.this,"Invalid employee ID or password",Toast.LENGTH_LONG).show();

    }

    @Override
    public void Login_Successfully()
    {
        Toast.makeText(DriverLoginActivity.this,"Success !",Toast.LENGTH_SHORT).show();
        Intent DriverMap = new Intent(DriverLoginActivity.this , DriverMapsActivity.class);
        startActivity(DriverMap);
        finish();
    }

    @Override
    public void SigningIn()
    {
        loadingBar.setTitle("Cab Login");
        loadingBar.setMessage("Signing in ...");
        loadingBar.show();
    }

    @Override
    public void FinishSignIn()
    {
        loadingBar.dismiss();
    }

}
