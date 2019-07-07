package com.example.dwidar.elmawkaf.Presenter;

import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CustMainModel;
import com.example.dwidar.elmawkaf.View.CustMainActivity;

public class CustMainPresenter implements CustMainContract.IPresenter
{

    CustMainModel model;
    CustMainContract.IView view;

    public CustMainPresenter(CustMainActivity v)
    {
        model = new CustMainModel();
        this.view = v;
    }


    @Override
    public void Logout()
    {
        model.cust_Signout();
    }
}
