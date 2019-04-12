package com.example.dwidar.elmawkaf.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dwidar.elmawkaf.Model.Contracts.CabCreationContract;
import com.example.dwidar.elmawkaf.Presenter.CabCreationPresenter;
import com.example.dwidar.elmawkaf.R;

public class RegisterDriverActivity extends AppCompatActivity implements CabCreationContract.IView {


    CabCreationPresenter presenter;
    ProgressDialog loadingBar;

    EditText TxtDriverFullName , TxtDriverEmpIDReg , TxtDriverRegPhone , TxtDriverRegPwd , TxtDriverRegPwd2;
    Button BtnDriverSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        presenter = new CabCreationPresenter(this);
        loadingBar = new ProgressDialog(this);

        TxtDriverFullName = (EditText) findViewById(R.id.TxtDriverFullName);
        TxtDriverEmpIDReg = (EditText) findViewById(R.id.TxtDriverEmpIDReg);
        TxtDriverRegPhone = (EditText) findViewById(R.id.TxtDriverRegPhone);
        TxtDriverRegPwd = (EditText) findViewById(R.id.TxtDriverRegPwd);
        TxtDriverRegPwd2 = (EditText) findViewById(R.id.TxtDriverRegPwd2);

        BtnDriverSignUp = (Button) findViewById(R.id.BtnDriverSignUp);

        BtnDriverSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCab(v);
            }
        });
    }


    void CreateCab(View view)
    {
        if (CheckValidation())
        {
            String FullName = TxtDriverFullName.getText().toString();
            String Empid = TxtDriverEmpIDReg.getText().toString();
            String phone = TxtDriverRegPhone.getText().toString();
            String pwd = TxtDriverRegPwd.getText().toString();

            presenter.CabCreation(FullName , Empid , pwd , phone);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void ShowInvalidMessage()
    {
        Toast.makeText(RegisterDriverActivity.this , "Some error occurred", Toast.LENGTH_LONG).show();
    }

    @Override
    public void Creation_Successfully()
    {
        Toast.makeText(RegisterDriverActivity.this , "Success !", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void SigningUp()
    {
        loadingBar.setTitle("Cab Registration");
        loadingBar.setMessage("Please wait until Signing up ...");
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
        String FullName = TxtDriverFullName.getText().toString();
        String Empid = TxtDriverEmpIDReg.getText().toString();
        String phone = TxtDriverRegPhone.getText().toString();
        String pwd = TxtDriverRegPwd.getText().toString();
        String pwd2 = TxtDriverRegPwd2.getText().toString();

        if (FullName.isEmpty() || Empid.isEmpty() || phone.isEmpty() || pwd.isEmpty() || pwd2.isEmpty())
        {
            Toast.makeText(RegisterDriverActivity.this , "Please complete information",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (!pwd.equals(pwd2))
        {
            Toast.makeText(RegisterDriverActivity.this, "Please enter similar password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
