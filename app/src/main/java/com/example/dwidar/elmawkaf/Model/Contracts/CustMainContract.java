package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.Location;

import java.util.ArrayList;

public class CustMainContract
{
    public interface IView
    {
        void ShowLocationToView(ArrayList<Location> locations);
    }

    public interface IPresenter
    {
        void Logout();
        void AddLocations(ArrayList<Location> locations);
        void get_location_to_show();
        void on_getLocationSuccess(ArrayList<Location> locations);
    }

    public interface IModel
    {
        void cust_Signout();
        void AddLocations(ArrayList<Location> locations);
        void get_all_locations();
    }

}
