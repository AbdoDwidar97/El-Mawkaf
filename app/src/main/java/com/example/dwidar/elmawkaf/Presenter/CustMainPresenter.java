package com.example.dwidar.elmawkaf.Presenter;

import android.util.Log;

import com.example.dwidar.elmawkaf.Model.Components.Location;
import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CustMainModel;
import com.example.dwidar.elmawkaf.View.CustMainActivity;

import java.util.ArrayList;

public class CustMainPresenter implements CustMainContract.IPresenter
{

    CustMainModel model;
    CustMainContract.IView view;

    public CustMainPresenter(CustMainActivity v)
    {
        model = new CustMainModel(this);
        this.view = v;
    }


    @Override
    public void Logout()
    {
        model.cust_Signout();
    }

    @Override
    public void AddLocations(ArrayList<Location> locations)
    {
        model.AddLocations(locations);
    }

    @Override
    public void get_location_to_show()
    {
        model.get_all_locations();
    }

    @Override
    public void on_getLocationSuccess(ArrayList<Location> locations)
    {
        Log.e("TEST LOCATION PRESE", "on get_location_success now!");
        view.ShowLocationToView(locations);
    }
}
