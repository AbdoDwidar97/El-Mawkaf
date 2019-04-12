package com.example.dwidar.elmawkaf.Presenter;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Model.Contracts.CabLoginContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CabLoginModel;
import com.example.dwidar.elmawkaf.View.DriverLoginActivity;

public class CabLoginPresenter extends ActorPresenter implements CabLoginContract.IPresenter {

    CabLoginContract.IView view;


    public CabLoginPresenter(DriverLoginActivity v)
    {
        view = v;
    }

    @Override
    public void CabLogin(String EmpID , String Pwd)
    {
        Cab cab = new Cab();
        cab.setEmpID(EmpID);
        cab.setUPassword(Pwd);
        view.SigningIn();
        CabLoginModel cabLoginModel = new CabLoginModel();
        cabLoginModel.ULogin(cab , this);
    }

    public void OnSuccessLogin()
    {
        view.Login_Successfully();
        view.FinishSignIn();
    }
    public void OnFailLogin()
    {
        view.ShowInvalidMessage();
        view.FinishSignIn();
    }

}
