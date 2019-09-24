package com.example.dwidar.elmawkaf.Presenter;

import android.location.Location;
import android.util.Log;

import com.example.dwidar.elmawkaf.Model.Components.FetchURL;
import com.example.dwidar.elmawkaf.Model.Components.LocationPoint;
import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Model.ModelView.CustMainModel;
import com.example.dwidar.elmawkaf.R;
import com.example.dwidar.elmawkaf.View.CustMainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import static android.provider.Settings.System.getString;

public class CustMainPresenter implements CustMainContract.IPresenter
{

    CustMainModel model;
    CustMainContract.IView view;
    float Distence = 0.0f;


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
    public void AddLocations(ArrayList<LocationPoint> locations)
    {
        model.AddLocations(locations);
    }

    @Override
    public void get_location_to_show()
    {
        model.get_all_locations();
    }

    @Override
    public void on_getLocationSuccess(ArrayList<LocationPoint> locations)
    {
        Log.e("TEST LOCATION PRESE", "on get_location_success now!");
        view.ShowLocationToView(locations);
    }

    @Override
    public void get_trip_cost(double kilo_cost)
    {
        float distenceByKilo = this.Distence/1000;
        float trip_cost = (float) (distenceByKilo * kilo_cost);
        view.ShowTripCost(trip_cost);
    }

    @Override
    public void get_Cost(LatLng cur_location, LatLng destination)
    {
        this.Distence = Calculate_distence(cur_location, destination);
        model.get_kilo_cost();
    }

    @Override
    public void get_Path_Draw(LatLng origin, LatLng dest, String api_key)
    {
        new FetchURL(this).execute(get_url(origin, dest, api_key), "driving");
    }

    @Override
    public void Draw_path(PolylineOptions polylineOptions)
    {
        view.onMap_DrawPath(polylineOptions);
    }

    @Override
    public void get_Nearby_Cab(LatLng customerLocation)
    {
        int radius = 1;
        model.get_Nerby_Cab(customerLocation, radius);
    }

    @Override
    public void Nerby_Cab_Successfull(String CabID)
    {
        view.Getting_NerbyCab_Successfull();
    }

    @Override
    public void FailTofindCabs()
    {
        view.CabsNotfound();
    }

    private String get_url(LatLng origin, LatLng dest, String api_key)
    {
        String directionMode = "driving";

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + api_key;

        return url;
    }

    private float Calculate_distence(LatLng cur_location, LatLng destination)
    {
        Location curlction = new Location("");
        curlction.setLatitude(cur_location.latitude);
        curlction.setLongitude(cur_location.longitude);

        Location destlction = new Location("");
        destlction.setLatitude(destination.latitude);
        destlction.setLongitude(destination.longitude);

        float distence = curlction.distanceTo(destlction);
        return distence;
    }
}
