package com.example.dwidar.elmawkaf.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.dwidar.elmawkaf.Model.Contracts.CustMainContract;
import com.example.dwidar.elmawkaf.Presenter.CustMainPresenter;
import com.example.dwidar.elmawkaf.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, ConfirmationDialog.DialogLestiner, CustMainContract.IView {


    CustMainPresenter presenter;
    Button BtnCallCab;
    AutoCompleteTextView searchViewPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_main);

        presenter = new CustMainPresenter(this);
        BtnCallCab = (Button) findViewById(R.id.BtnCallCab);
        searchViewPlaces = (AutoCompleteTextView) findViewById(R.id.SearchViewPlaces);

        BtnCallCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnCallClick();
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        handler = new Handler();
        myCurrentLocation = new LatLng(0.0, 0.0);

        //Log.e("TEST LOCATION OnCreate", "on create now!");
        presenter.get_location_to_show();

        getLocationPermission();

    }

    private void BtnCallClick()
    {
        Toast.makeText(this, "Calling a cab ...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.AllTrips)
        {
            Toast.makeText(this, "All Trips Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Wallet)
        {
            Toast.makeText(this, "Mawkaf Wallet Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Profile)
        {
            Toast.makeText(this, "Profile Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.About)
        {
            Toast.makeText(this, "About Activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.CustLogout)
        {
            ConfirmationDialog confirmationDialog = new ConfirmationDialog();
            confirmationDialog.show(getSupportFragmentManager(), "Confirmation Dialog");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //=========================================================================================

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    LatLng myCurrentLocation;
    Handler handler;

    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        getLastLocation.run();

    }

    private Runnable getLastLocation = new Runnable()
    {

        @Override
        public void run()
        {
            if (mLocationPermissionsGranted) {
                getDeviceLocation();
                if (ActivityCompat.checkSelfPermission(CustMainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CustMainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.clear();
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

            }
            handler.postDelayed(this, 4000);
        }
    };


    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            double longitude = currentLocation.getLongitude();
                            double latitude = currentLocation.getLatitude();
                            myCurrentLocation = new LatLng(latitude, longitude);

                            moveCamera(new LatLng(latitude, longitude),
                                    DEFAULT_ZOOM);

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(CustMainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }


    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(CustMainActivity.this);
    }


    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(getLastLocation);
        finish();
    }

    @Override
    public void onYesClicked()
    {
        presenter.Logout();
        Intent welcomeActivity = new Intent(this, WelcomeActivity.class);
        startActivity(welcomeActivity);
        finish();
    }


    void getlctions()
    {
        ArrayList<com.example.dwidar.elmawkaf.Model.Components.Location> alllocations = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("locations.txt");
            int size  = inputStream.available();
            byte [] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String txt = new String(buffer);


            String [] locationsStrings = txt.split("\n");
            for (String itr : locationsStrings)
            {
                String [] lctnStr = itr.split("=");
                com.example.dwidar.elmawkaf.Model.Components.Location lctn = new com.example.dwidar.elmawkaf.Model.Components.Location();
                lctn.setLocation_Title(lctnStr[0]);
                lctn.setLatLng_string(lctnStr[1]);
                alllocations.add(lctn);
            }

            presenter.AddLocations(alllocations);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void ShowLocationToView(ArrayList<com.example.dwidar.elmawkaf.Model.Components.Location> all_locations)
    {
        ArrayList<String> locations_str = new ArrayList<>();

        for (com.example.dwidar.elmawkaf.Model.Components.Location itr : all_locations)
        {
            locations_str.add(itr.getLocation_Title());
            Log.e("TEST LOCATIONS VIEW: ", itr.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,locations_str);

        searchViewPlaces.setAdapter(adapter);

    }

}

