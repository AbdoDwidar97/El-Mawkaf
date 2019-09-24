package com.example.dwidar.elmawkaf.Model.ModelView;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dwidar.elmawkaf.Model.Components.LocationPoint;
import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Presenter.CustMainPresenter;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustMainModel implements CustMainContract.IModel
{

    CustMainContract.IPresenter prese;

    DatabaseReference CustAvailRef;
    DatabaseReference CabAvailRef;
    DatabaseReference DBRef;
    GeoFire geoFire;
    FirebaseUser UserMe;
    FirebaseAuth mAuth;

    boolean CabFound = false;
    String CabfoundID;

    public CustMainModel(CustMainPresenter presenter)
    {
        this.prese = presenter;
        CustAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCustomers");

        CabAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCabs");

        DBRef = FirebaseDatabase.getInstance().getReference("Database").child("Locations").child("-Lmuym5r3REXq57uiUHU");

        geoFire = new GeoFire(CustAvailRef);
        mAuth = FirebaseAuth.getInstance();
        UserMe = mAuth.getCurrentUser();
    }

    public CustMainModel()
    {
        CustAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCustomers");

        CabAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCabs");

        DBRef = FirebaseDatabase.getInstance().getReference("Database").child("Locations").child("-Lmuym5r3REXq57uiUHU");

        geoFire = new GeoFire(CustAvailRef);
        mAuth = FirebaseAuth.getInstance();
        UserMe = mAuth.getCurrentUser();
    }


    @Override
    public void cust_Signout()
    {
        mAuth.signOut();
    }

    @Override
    public void AddLocations(ArrayList<LocationPoint> locations)
    {
        DBRef.push().setValue(locations);



    }

    @Override
    public void get_all_locations()
    {
        //Log.e("TEST LOCATION MODEL", "get all locations");
        UIShowLocations uiShowLocations = new UIShowLocations(this.DBRef, this.prese);
        uiShowLocations.execute();
    }


    @Override
    public void get_kilo_cost()
    {
        DatabaseReference DBRef_cost = FirebaseDatabase.getInstance().getReference("Database").child("Settings");
        DBRef_cost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                double icost = Double.parseDouble(dataSnapshot.child("kilo_cost").getValue().toString());
                prese.get_trip_cost(icost);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void get_Nerby_Cab(final LatLng customerLocation, final int radius)
    {
        GeoFire gFire = new GeoFire(CabAvailRef);
        GeoQuery geoQuery = gFire.queryAtLocation(new GeoLocation(customerLocation.latitude, customerLocation.longitude), radius);
        geoQuery.removeAllListeners();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location)
            {
                if (!CabFound)
                {
                    CabFound = true;
                    CabfoundID = key;
                    prese.Nerby_Cab_Successfull(key);
                }
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady()
            {
                if (!CabFound && radius < 20)
                {
                    get_Nerby_Cab(customerLocation, radius + 1);
                }
                else if (radius == 20)
                {
                    prese.FailTofindCabs();
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

}

class UIShowLocations extends AsyncTask
{

    CustMainContract.IPresenter presenter;
    DatabaseReference dbref;

    ArrayList<LocationPoint> alllocations;

    UIShowLocations(DatabaseReference dbref, CustMainContract.IPresenter prese)
    {
        this.dbref = dbref;
        this.presenter = prese;
        alllocations = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                for (DataSnapshot itr : dataSnapshot.getChildren())
                {
                    LocationPoint newLocation = itr.getValue(LocationPoint.class);
                    alllocations.add(newLocation);
                }
                presenter.on_getLocationSuccess(alllocations);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

}
