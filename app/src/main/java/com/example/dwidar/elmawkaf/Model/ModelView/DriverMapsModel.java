package com.example.dwidar.elmawkaf.Model.ModelView;

import android.location.Location;

import com.example.dwidar.elmawkaf.Model.Contracts.CabMapsContract;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverMapsModel implements CabMapsContract.IModel
{

    DatabaseReference driverAvailRef;
    GeoFire geoFire;
    FirebaseUser UserMe;
    FirebaseAuth mAuth;

    public DriverMapsModel()
    {
        driverAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCabs");
        geoFire = new GeoFire(driverAvailRef);
        mAuth = FirebaseAuth.getInstance();
        UserMe = mAuth.getCurrentUser();
    }


    @Override
    public void SetAvailableCab(String Cabid, LatLng location)
    {
        geoFire.setLocation(Cabid, new GeoLocation(location.latitude, location.longitude));

    }

    @Override
    public void RemoveLocation(String Cabid)
    {
        geoFire.removeLocation(Cabid);
    }

    @Override
    public void CabSignOut()
    {
        mAuth.signOut();
    }
}
