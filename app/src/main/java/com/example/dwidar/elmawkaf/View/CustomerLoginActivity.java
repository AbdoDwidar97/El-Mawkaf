package com.example.dwidar.elmawkaf.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dwidar.elmawkaf.Model.Contracts.CustLoginContract;
import com.example.dwidar.elmawkaf.Presenter.CustLoginPresenter;
import com.example.dwidar.elmawkaf.R;

public class CustomerLoginActivity extends AppCompatActivity implements CustLoginContract.IView {

    private CustLoginPresenter custPresenter;
    private ProgressDialog loadingBar;
    private TextView LblCreateNewAccountCustomer;
    private EditText TxtCustEmail;
    private EditText TxtPwd;
    private Button BtnCustLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        custPresenter = new CustLoginPresenter(this);
        loadingBar = new ProgressDialog(this);
        LblCreateNewAccountCustomer = (TextView) findViewById(R.id.LblCreateNewAccountCustomer);
        TxtCustEmail = (EditText) findViewById(R.id.TxtCustEmail);
        TxtPwd = (EditText) findViewById(R.id.TxtPwd);
        BtnCustLogin = (Button) findViewById(R.id.BtnCustLogin);


        LblCreateNewAccountCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_Register_Customer(v);
            }
        });


        BtnCustLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OnCustomerLogin(v);
            }
        });
    }


    void go_Register_Customer(View view)
    {
        Intent Cust_Reg = new Intent(this,RegisterCustActivity.class);
        startActivity(Cust_Reg);
    }

    void OnCustomerLogin(View view)
    {
        String email = TxtCustEmail.getText().toString();
        String pwd = TxtPwd.getText().toString();
        if (email.isEmpty()) Toast.makeText(CustomerLoginActivity.this , "Enter Email" , Toast.LENGTH_SHORT).show();
        else if (pwd.isEmpty()) Toast.makeText(CustomerLoginActivity.this , "Enter Password" , Toast.LENGTH_SHORT).show();
        else custPresenter.CustLogin(email , pwd);
    }

    @Override
    public void ShowInvalidMessage()
    {
        Toast.makeText(CustomerLoginActivity.this , "Invalid Email or Password" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void Login_Successfully()
    {
        Toast.makeText(CustomerLoginActivity.this , "Success !" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void SigningIn()
    {
        loadingBar.setTitle("Customer login");
        loadingBar.setMessage("Please wait until sign in ...");
        loadingBar.show();
    }

    @Override
    public void FinishSignIn()
    {
        loadingBar.dismiss();
    }
}
