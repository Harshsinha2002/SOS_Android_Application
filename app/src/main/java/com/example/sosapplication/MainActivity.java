package com.example.sosapplication;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
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
import com.google.android.material.button.MaterialButton;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView User_Address;
    private static final int REQUEST_CHECK_SETTING = 1001;

    private  static  final  int Call_Permission_Code = 1000;
    private LocationRequest locationRequest;
    double Latitude;
    double Longitude;

    Dialog dialog;
    Button Dialog_Cancelbtn, Dialog_Logoutbtn;

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
        ImageView Call_Police = findViewById(R.id.Call_Police);
        ImageView Call_FireSafety = findViewById(R.id.Call_FireSafety);
        ImageView Call_Women_Helpline = findViewById(R.id.Call_Women_Helpline);
        ImageView Call_Ambulance = findViewById(R.id.Call_Ambulance);
        ImageView SafePlaces = findViewById(R.id.SafePlaces);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));


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


        Call_Police.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    callPolice();
            }
        });


        Call_FireSafety.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callFireSafety();
            }
        });


        Call_Women_Helpline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callWomenHelpLine();
            }
        });


        Call_Ambulance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callAmbulance();
            }
        });

        SafePlaces.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SafePlaces_Page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getTitle().equals("Logout"))
        {
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_dialog_box);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_box_background));
            dialog.setCancelable(false);
            dialog.show();

            Dialog_Cancelbtn = dialog.findViewById(R.id.Dialog_Cancelbtn);
            Dialog_Logoutbtn = dialog.findViewById(R.id.Dialog_Logoutbtn);

            Dialog_Cancelbtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });

            Dialog_Logoutbtn.setOnClickListener(new View.OnClickListener()
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void callPolice()
    {
        String number = "100";
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }


    private void callFireSafety()
    {
        String number = "101";
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }


    private void callWomenHelpLine()
    {
        String number = "181";
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }


    private void callAmbulance()
    {
        String number = "102";
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
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

        else if(requestCode == 1001 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
                callPolice();
                callFireSafety();
                callWomenHelpLine();
                callAmbulance();
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
        String message = "SOS NEED HELP!!!" + "\nCHECK  MY LOCATION " + "\nLatitude : " + Latitude + "\nLongitude : " + Longitude;

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