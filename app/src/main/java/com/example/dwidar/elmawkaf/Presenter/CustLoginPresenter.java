package com.example.dwidar.elmawkaf.Presenter;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Model.Contracts.CustLoginContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CustLoginModel;
import com.example.dwidar.elmawkaf.View.CustomerLoginActivity;

public class CustLoginPresenter extends ActorPresenter implements CustLoginContract.IPresenter {

    CustLoginContract.IView view;

    public CustLoginPresenter(CustomerLoginActivity v)
    {
        view = v;
    }

    @Override
    public void CustLogin(String Email, String Pwd)
    {
        Customer customer = new Customer();
        customer.setEmail(Email);
        customer.setUPassword(Pwd);
        view.SigningIn();
        CustLoginModel custLoginModel = new CustLoginModel();
        custLoginModel.ULogin(customer, this);
    }

    @Override
    public void OnSuccessLogin()
    {
        view.Login_Successfully();
        view.FinishSignIn();
    }

    @Override
    public void OnFailLogin()
    {

        view.ShowInvalidMessage();
        view.FinishSignIn();
    }

}
