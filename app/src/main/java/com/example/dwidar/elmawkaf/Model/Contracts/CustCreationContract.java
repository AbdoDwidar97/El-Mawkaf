package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Presenter.CustCreationPresenter;

public class CustCreationContract
{
    public interface IView
    {
        void ShowInvalidMessage();
        void Creation_Successfully();
        void SigningUp();
        void FinishSignUp();
        boolean CheckValidation();
    }

    public interface IPresenter
    {
        void CustCreation(String fullName , String empId , String Pwd , String Phone);
        void OnSuccessCreation();
        void OnFailCreation();
    }

    public interface IModel
    {
        void AddNew(Customer customer , final CustCreationPresenter prese);
    }

}
