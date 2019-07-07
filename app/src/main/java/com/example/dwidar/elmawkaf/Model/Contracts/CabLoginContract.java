package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Presenter.CabLoginPresenter;

public class CabLoginContract {

    public interface IView
    {
        void ShowInvalidMessage();
        void Login_Successfully();
        void SigningIn();
        void FinishSignIn();
        void Set_CapID(String id);
    }

    public interface IPresenter
    {
        void CabLogin(String empId , String Pwd);
        void OnSuccessLogin();
        void OnFailLogin();
    }

    public interface IModel
    {
        void ULogin(Cab actor, final CabLoginPresenter prese);
        String get_CapID();
    }

}
