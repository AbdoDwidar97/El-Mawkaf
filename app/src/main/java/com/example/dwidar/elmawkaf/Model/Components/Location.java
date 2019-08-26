package com.example.dwidar.elmawkaf.Model.Components;

public class Location
{
    private String Location_Title;
    private double latitude = 0.0;
    private double longitude = 0.0;

    public Location()
    {

    }

    public Location(String title, double latitude, double longitude)
    {
        this.Location_Title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLatLng_string(String lat_lng)
    {
        String [] latLng = lat_lng.split(",");
        String lat = latLng[0];
        String lng = latLng[1];

        double lat2 = Double.parseDouble(lat);
        double lng2 = Double.parseDouble(lng);

        this.latitude = lat2;
        this.longitude =lng2;
    }

    public String getLocation_Title() {
        return Location_Title;
    }

    public void setLocation_Title(String location_Title) {
        Location_Title = location_Title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.getLocation_Title() + " = " + this.getLatitude() + " , " + this.getLongitude();
    }
}
