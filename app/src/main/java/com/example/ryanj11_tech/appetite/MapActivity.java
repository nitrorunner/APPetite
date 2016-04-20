package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //sets map to store location
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        LatLng storeLocation = new LatLng(41.503339, -81.503725);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.addMarker(new MarkerOptions().position(storeLocation).title("Granite City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 16));
    }

    public void onClick_TakeMe(View view) {
        Intent geoIntent = new Intent();
        geoIntent.setAction(Intent.ACTION_VIEW);
        geoIntent.setData(Uri.parse("google.navigation:q=Granite+City+Brewery+Lyndhurst+Ohio"));
        startActivity(geoIntent);
    }
}
