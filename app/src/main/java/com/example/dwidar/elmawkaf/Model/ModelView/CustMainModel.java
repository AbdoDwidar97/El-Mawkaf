package com.example.dwidar.elmawkaf.Model.ModelView;

import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.firebase.geofire.GeoFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustMainModel implements CustMainContract.IModel
{

    DatabaseReference CustAvailRef;
    GeoFire geoFire;
    FirebaseUser UserMe;
    FirebaseAuth mAuth;

    public CustMainModel()
    {
        CustAvailRef = FirebaseDatabase.getInstance().getReference("Database").child("AvailCustomers");
        geoFire = new GeoFire(CustAvailRef);
        mAuth = FirebaseAuth.getInstance();
        UserMe = mAuth.getCurrentUser();
    }

    @Override
    public void cust_Signout()
    {
        mAuth.signOut();
    }
}
