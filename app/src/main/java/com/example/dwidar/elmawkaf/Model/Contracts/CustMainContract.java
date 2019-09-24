package com.example.dwidar.elmawkaf.Model.Contracts;

import com.example.dwidar.elmawkaf.Model.Components.LocationPoint;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class CustMainContract
{
    public interface IView
    {
        void ShowLocationToView(ArrayList<LocationPoint> locations);
        void ShowTripCost(double trip_cost);
        void onMap_DrawPath(PolylineOptions polylineOptions);
        void Getting_NerbyCab_Successfull();
        void CabsNotfound();
    }

    public interface IPresenter
    {
        void Logout();
        void AddLocations(ArrayList<LocationPoint> locations);
        void get_location_to_show();
        void on_getLocationSuccess(ArrayList<LocationPoint> locations);
        void get_trip_cost(double kilo_cost);
        void get_Cost(LatLng cur_location, LatLng destination);
        void get_Path_Draw(LatLng origin, LatLng dest, String api_key);
        void Draw_path(PolylineOptions polylineOptions);
        void get_Nearby_Cab(LatLng customerLocation);
        void Nerby_Cab_Successfull(String CabID);
        void FailTofindCabs();
    }

    public interface IModel
    {
        void cust_Signout();
        void AddLocations(ArrayList<LocationPoint> locations);
        void get_all_locations();
        void get_kilo_cost();
        void get_Nerby_Cab(LatLng customerLocation, int radius);
    }

}
