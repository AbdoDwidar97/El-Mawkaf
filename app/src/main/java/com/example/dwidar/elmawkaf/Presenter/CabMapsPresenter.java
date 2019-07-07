package com.example.dwidar.elmawkaf.Presenter;

import android.location.Location;

import com.example.dwidar.elmawkaf.Model.Contracts.CabMapsContract;
import com.example.dwidar.elmawkaf.Model.ModelView.DriverMapsModel;
import com.example.dwidar.elmawkaf.View.DriverMapsActivity;
import com.google.android.gms.maps.model.LatLng;

public class CabMapsPresenter implements CabMapsContract.IPresenter
{

    CabMapsContract.IView view;
    DriverMapsModel model;

    public CabMapsPresenter(DriverMapsActivity v)
    {
        this.view = v;
        model = new DriverMapsModel();
    }

    @Override
    public void SetAvailCab(String Cabid, LatLng location)
    {
        model.SetAvailableCab(Cabid, location);
    }

    @Override
    public void RemoveCabLocation(String Cabid)
    {
        model.RemoveLocation(Cabid);
    }

    @Override
    public void SignOut()
    {
        model.CabSignOut();
    }
}
