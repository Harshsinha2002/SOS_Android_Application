package com.example.sosapplication;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView User_Address;
    private static final int REQUEST_CHECK_SETTING = 1001;
    private LocationRequest locationRequest;
    double Latitude;
    double Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_Address = findViewById(R.id.User_location);
        Button  SOS_Button = findViewById(R.id.SOS_btn);
        locationRequest = LocationRequest.create(); // create a location request
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // set how accurately the location will be provided
        locationRequest.setInterval(5000); // interval at with the location is set
        locationRequest.setFastestInterval(2000);
        Button SignOut_btn =  findViewById(R.id.SignOut_btn);


        SignOut_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        });


        SOS_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) // checks if the gps permission is on or not
                    {
                        if(isGPSEnable()) // checks if the gps is on or not
                        {
                            LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);

                                    if(locationResult != null && locationResult.getLocations().size() > 0)
                                    {
                                        int index =  locationResult.getLocations().size() - 1;
                                         double latitude = locationResult.getLocations().get(index).getLatitude();
                                         double longitude = locationResult.getLocations().get(index).getLongitude();

                                        Latitude = (double)latitude;
                                        Longitude = (double) longitude;

                                        User_Address.setText("Latitude : " + Latitude  + "\nLongitude : " + Longitude );
                                        sendSMS(Longitude,Latitude);

                                        Toast.makeText(MainActivity.this, "SOS Message sent!!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, Looper.getMainLooper());
                        }

                        else // when gps is off
                        {
                            Toast.makeText(MainActivity.this, "Turn On Your GPS", Toast.LENGTH_LONG).show();
                            turnOnGPS();
                        }
                    }

                    else
                    {
                        requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 44);
                    }
                }

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)// checks if the sms permission is on or not
                {
                    //
                }

                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if(requestCode == 44 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                sendSMS(Longitude, Latitude);
            }
        }

        else
        {
            Toast.makeText(MainActivity.this, "App Permission Denied!! Check Settings", Toast.LENGTH_LONG);
        }
    }

    private void sendSMS(Double Longitude, Double Latitude)
    {
        String phone1 = "7011934577";
        //String phone2 = "9625065291";
        String message = "SOS NEED HELP!!!" + "\nCHECK  MY LOCATION " + "\nLongitude : " + Longitude + "\nLatitude : " + Latitude;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone1, null, message,null,null);
        //smsManager.sendTextMessage(phone2, null, message, null, null);
    }

    private void turnOnGPS()
    {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addAllLocationRequests(Collections.singleton(locationRequest)); //used to get the response as gps location of client
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MainActivity.this, "GPS is On!!", Toast.LENGTH_LONG).show();

                }
                catch (ApiException e) // e here is the api exception error
                {
                    switch (e.getStatusCode())
                    {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED : //location setting status code used to get the location setting status(wether its turn on, or the device have location access or not etc)
                            //Resolution Required is used to check the location being turned on

                            try
                            {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e; // used to get the api exception error
                                resolvableApiException.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTING);
                            }
                            catch (IntentSender.SendIntentException ex)
                            {
                                ex.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE :
                            break;
                    }
                }
            }
        });
    }

    private boolean isGPSEnable() // this method returns true is gps is turned on and returns false if gps is turned off
    {
        LocationManager locationManager = null;
        boolean isEnable = false;

        if(locationManager == null)
        {
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        }

        isEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnable;
    }
}