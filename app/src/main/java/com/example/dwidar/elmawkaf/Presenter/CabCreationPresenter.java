package com.example.dwidar.elmawkaf.Presenter;

import com.example.dwidar.elmawkaf.Model.Components.Cab;
import com.example.dwidar.elmawkaf.Model.Contracts.CabCreationContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CabCreationModel;
import com.example.dwidar.elmawkaf.View.RegisterDriverActivity;

public class CabCreationPresenter extends ActorCreationPresenter implements CabCreationContract.IPresenter
{

    CabCreationContract.IView view;

    public CabCreationPresenter(RegisterDriverActivity v)
    {
        view = v;
    }

    @Override
    public void CabCreation(String fullName , String empId, String Pwd , String Phone)
    {
        Cab cab = new Cab(fullName, empId, Phone, Pwd);
        CabCreationModel cabCreationModel = new CabCreationModel();
        view.SigningUp();
        cabCreationModel.AddNew(cab , this);
    }

    @Override
    public void OnSuccessCreation()
    {
        view.Creation_Successfully();
        view.FinishSignUp();
    }

    @Override
    public void OnFailCreation()
    {
        view.ShowInvalidMessage();
    }


}
