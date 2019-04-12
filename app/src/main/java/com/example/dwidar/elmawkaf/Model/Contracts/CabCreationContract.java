package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Presenter.CabCreationPresenter;

public class CabCreationContract
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
        void CabCreation(String fullName , String email, String Pwd , String Phone);
        void OnSuccessCreation();
        void OnFailCreation();
    }

    public interface IModel
    {
        void AddNew(Cab cab , final CabCreationPresenter prese);
    }
}
