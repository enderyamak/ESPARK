package com.example.yamak.espark;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback
{
    GoogleMap haritam;
    String gelenLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle gelenVeri = getIntent().getExtras();
        CharSequence gelenLctn = gelenVeri.getCharSequence("Location");
        gelenLocation = gelenLctn.toString();

        boolean var_mi = false;

        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if(api.isGooglePlayServicesAvailable(this)== ConnectionResult.SUCCESS)
            var_mi = true;
        else if(api.isUserResolvableError(api.isGooglePlayServicesAvailable(this)))
            api.getErrorDialog(this,api.isGooglePlayServicesAvailable(this),0).show();
        else
            Toast.makeText(this,"Api yok",Toast.LENGTH_SHORT).show();
        if(var_mi)
        {
            Toast.makeText(this,"Baglandi...",Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_map);
            MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrgmnt);
            fragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        haritam = googleMap;
        if(haritam != null)
        {
            haritam.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener(){
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    Geocoder gc = new Geocoder(MapActivity.this);
                    LatLng ll = marker.getPosition();
                    try
                    {
                        List<Address> addressLists = gc.getFromLocation(ll.latitude,ll.longitude,1);
                        Address address = addressLists.get(0);
                        marker.setTitle(address.getLocality());
                        marker.showInfoWindow();
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            haritam.setOnMarkerClickListener(click);
        }
        Geocoder gc = new Geocoder(MapActivity.this);
        List<Address> addresses = null;
        try {
             addresses = gc.getFromLocationName(gelenLocation,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Marker m=null,m1 = null,m2=null,m3=null,m4=null,m5=null,m6=null,m7=null;

        Address address = addresses.get(0);
        String locacity = address.getLocality();

        Toast.makeText(MapActivity.this,locacity,Toast.LENGTH_SHORT).show();

        double lt = address.getLatitude();
        double lg = address.getLongitude();

        goToLocationZoom(lt,lg,15);

        setMarker(m,address,lt,lg,1);
        setMarker(m1,address,lt+0.0005,lg,2);
        setMarker(m2,address,lt+0.001,lg,3);
        setMarker(m3,address,lt+0.0015,lg,4);
        setMarker(m4,address,lt+0.002,lg,5);
        setMarker(m5,address,lt+0.0025,lg,6);
        setMarker(m6,address,lt+0.003,lg,7);
        setMarker(m7,address,lt+0.0035,lg,8);
        // latitude enlem longtitude boylam

    }

    public void setMarker(Marker marker,Address address, double lt, double lg,int sayi) {
        MarkerOptions markerOptions = new MarkerOptions().
                title(address.getLocality()).
                draggable(true).
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).
                position(new LatLng(lt,lg)).
                snippet("Park "+sayi);
        marker = haritam.addMarker(markerOptions);

    }
    GoogleMap.OnMarkerClickListener click = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker1) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(MapActivity.this);
            aBuilder.setMessage("Kiralayacak mısınız ?");
            aBuilder.setCancelable(false).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent ıntent = new Intent(MapActivity.this,KiralaActivity.class);
                    startActivity(ıntent);
                }
            });
            AlertDialog alertDialog = aBuilder.create();
            alertDialog.setTitle("Emin misiniz ?");
            alertDialog.show();
            return true;
        }
    };

    private void goToLocationZoom(double latitude,double lontitude,int zoom) {
        LatLng ll = new LatLng(latitude,lontitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        haritam.animateCamera(cameraUpdate);
    }
}
