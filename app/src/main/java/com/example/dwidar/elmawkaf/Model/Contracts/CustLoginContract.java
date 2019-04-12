package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Presenter.CustLoginPresenter;

public class CustLoginContract {

    public interface IView
    {
        void ShowInvalidMessage();
        void Login_Successfully();
        void SigningIn();
        void FinishSignIn();
    }

    public interface IPresenter
    {
        void CustLogin(String Email , String Pwd);
        void OnSuccessLogin();
        void OnFailLogin();
    }

    public interface IModel
    {
        void ULogin(Customer actor, final CustLoginPresenter prese);
    }

}
