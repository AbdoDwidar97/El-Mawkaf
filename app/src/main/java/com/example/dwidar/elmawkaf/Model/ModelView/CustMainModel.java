package com.example.dwidar.elmawkaf.Model.ModelView;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.dwidar.elmawkaf.Model.Components.Location;
import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Presenter.CustMainPresenter;
import com.example.dwidar.elmawkaf.View.CustMainActivity;
import com.firebase.geofire.GeoFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;

public class CustMainModel implements CustMainContract.IModel
{

    CustMainContract.IPresenter prese;

    DatabaseReference CustAvailRef;
    DatabaseReference DBRef;
    GeoFire geoFire;
    FirebaseUser UserMe;
    FirebaseAuth mAuth;

    public CustMainModel(CustMainPresenter presenter)
    {
        this.prese = presenter;
        CustAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCustomers");
        DBRef = FirebaseDatabase.getInstance().getReference("Database").child("Locations").child("-Lmuym5r3REXq57uiUHU");

        geoFire = new GeoFire(CustAvailRef);
        mAuth = FirebaseAuth.getInstance();
        UserMe = mAuth.getCurrentUser();
    }

    public CustMainModel()
    {
        CustAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCustomers");
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
    public void AddLocations(ArrayList<Location> locations)
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
}

class UIShowLocations extends AsyncTask
{

    CustMainContract.IPresenter presenter;
    DatabaseReference dbref;

    ArrayList<Location> alllocations;

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
                    Location newLocation = itr.getValue(Location.class);
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
