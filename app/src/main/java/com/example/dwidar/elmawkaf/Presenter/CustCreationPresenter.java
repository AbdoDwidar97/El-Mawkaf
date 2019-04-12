package com.example.dwidar.elmawkaf.Presenter;

import com.example.dwidar.elmawkaf.Model.Components.Customer;
import com.example.dwidar.elmawkaf.Model.Contracts.CustCreationContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CustCreationModel;
import com.example.dwidar.elmawkaf.View.RegisterCustActivity;

public class CustCreationPresenter extends ActorCreationPresenter implements CustCreationContract.IPresenter
{

    CustCreationContract.IView view;

    public CustCreationPresenter(RegisterCustActivity v)
    {
        view = v;
    }

    @Override
    public void CustCreation(String fullName , String email, String Pwd , String Phone)
    {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setUPassword(Pwd);
        customer.setPhoneNumber(Phone);
        customer.setFullName(fullName);

        CustCreationModel custCreateModel = new CustCreationModel();
        view.SigningUp();
        custCreateModel.AddNew(customer , this);
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
        view.FinishSignUp();
    }
}
