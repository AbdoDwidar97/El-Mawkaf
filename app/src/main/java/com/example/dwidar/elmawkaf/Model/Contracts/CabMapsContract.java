package com.example.dwidar.elmawkaf.Model.Contracts;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class CabMapsContract
{
    public interface IView
    {

    }

    public interface IPresenter
    {
        void SetAvailCab (String Cabid, LatLng location);
        void RemoveCabLocation(String Cabid);
        void SignOut();
    }

    public interface IModel
    {
        void SetAvailableCab (String Cabid, LatLng location);
        void RemoveLocation (String Cabid);
        void CabSignOut();
    }

}
