package com.example.dwidar.elmawkaf.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dwidar.elmawkaf.Model.Contracts.CustCreationContract;
import com.example.dwidar.elmawkaf.Presenter.CustCreationPresenter;
import com.example.dwidar.elmawkaf.R;

public class RegisterCustActivity extends AppCompatActivity implements CustCreationContract.IView {

    CustCreationPresenter presenter;
    ProgressDialog loadingBar;

    EditText TxtCustFullName;
    EditText TxtCustEmailReg;
    EditText TxtCustRegPhone;
    EditText TxtCustRegPwd;
    EditText TxtCustRegPwd2;

    Button BtnCustSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cust);

        presenter = new CustCreationPresenter(this);
        loadingBar = new ProgressDialog(this);

        TxtCustFullName = (EditText) findViewById(R.id.TxtCustFullName);
        TxtCustEmailReg = (EditText) findViewById(R.id.TxtCustEmailReg);
        TxtCustRegPhone = (EditText) findViewById(R.id.TxtCustRegPhone);
        TxtCustRegPwd = (EditText) findViewById(R.id.TxtCustRegPwd);
        TxtCustRegPwd2 = (EditText) findViewById(R.id.TxtCustRegPwd2);

        BtnCustSignUp = (Button) findViewById(R.id.BtnCustSignUp);

        BtnCustSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                CustSignUp(v);
            }
        });
    }


    void CustSignUp(View view)
    {
        if (CheckValidation())
        {
            String FullName = TxtCustFullName.getText().toString();
            String email = TxtCustEmailReg.getText().toString();
            String phone = TxtCustRegPhone.getText().toString();
            String pwd = TxtCustRegPwd.getText().toString();

            presenter.CustCreation(FullName , email , pwd , phone);
        }

    }

    @Override
    public void ShowInvalidMessage() {
        Toast.makeText(RegisterCustActivity.this , "Something error occurred",Toast.LENGTH_LONG).show();
    }

    @Override
    public void Creation_Successfully() {
        Toast.makeText(RegisterCustActivity.this , "Success !",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void SigningUp()
    {
        loadingBar.setTitle("Customer Registration");
        loadingBar.setMessage("Please wait until signing up ...");
        loadingBar.show();
    }

    @Override
    public void FinishSignUp()
    {
        loadingBar.dismiss();
    }

    @Override
    public boolean CheckValidation()
    {
        String FullName = TxtCustFullName.getText().toString();
        String email = TxtCustEmailReg.getText().toString();
        String phone = TxtCustRegPhone.getText().toString();
        String pwd = TxtCustRegPwd.getText().toString();
        String pwd2 = TxtCustRegPwd2.getText().toString();

        if (FullName.isEmpty() || email.isEmpty() || phone.isEmpty() || pwd.isEmpty() || pwd2.isEmpty())
        {
            Toast.makeText(RegisterCustActivity.this , "Please complete information",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (!pwd.equals(pwd2))
        {
            Toast.makeText(RegisterCustActivity.this, "Please enter similar password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
