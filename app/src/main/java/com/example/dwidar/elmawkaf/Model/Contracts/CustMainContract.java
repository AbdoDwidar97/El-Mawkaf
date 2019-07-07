package com.example.dwidar.elmawkaf.Model.Contracts;

public class CustMainContract
{
    public interface IView
    {

    }

    public interface IPresenter
    {
        void Logout();
    }

    public interface IModel
    {
        void cust_Signout();
    }

}
