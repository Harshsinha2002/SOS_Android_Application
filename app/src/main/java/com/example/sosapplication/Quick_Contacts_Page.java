package com.example.sosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class Quick_Contacts_Page extends AppCompatActivity {

    TextView Quick_Contact_1_FullName_editText;
    TextView Quick_Contact_1_PhoneNo_editText;
    TextView Quick_Contact_2_FullName_editText;
    TextView Quick_Contact_2_PhoneNo_editText;
    TextView Quick_Contact_3_FullName_editText;
    TextView Quick_Contact_3_PhoneNo_editText;
    TextView Quick_Contact_4_FullName_editText;
    TextView Quick_Contact_4_PhoneNo_editText;
    TextView Quick_Contact_5_FullName_editText;
    TextView Quick_Contact_5_PhoneNo_editText;

    MaterialCardView Quick_Contact_1_CardView;
    MaterialCardView Quick_Contact_2_CardView;
    MaterialCardView Quick_Contact_3_CardView;
    MaterialCardView Quick_Contact_4_CardView;
    MaterialCardView Quick_Contact_5_CardView;

    String Quick_Contact_1_FullName, Quick_Contact_1_PhoneNo,Quick_Contact_2_FullName, Quick_Contact_2_PhoneNo, Quick_Contact_3_FullName, Quick_Contact_3_PhoneNo, Quick_Contact_4_FullName, Quick_Contact_4_PhoneNo, Quick_Contact_5_FullName, Quick_Contact_5_PhoneNo;

    private static final int REQUEST_CHECK_SETTING = 1001;
    private  static  final  int Call_Permission_Code = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_contacts_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));

        Quick_Contact_1_CardView = findViewById(R.id.Quick_Contact_1_CardView);
        Quick_Contact_2_CardView = findViewById(R.id.Quick_Contact_2_CardView);
        Quick_Contact_3_CardView = findViewById(R.id.Quick_Contact_3_CardView);
        Quick_Contact_4_CardView = findViewById(R.id.Quick_Contact_4_CardView);
        Quick_Contact_5_CardView = findViewById(R.id.Quick_Contact_5_CardView);


        Quick_Contact_1_FullName_editText = findViewById(R.id.Quick_Contact_1_FullName_editText);
        Quick_Contact_1_PhoneNo_editText = findViewById(R.id.Quick_Contact_1_PhoneNo_editText);
        Quick_Contact_2_FullName_editText = findViewById(R.id.Quick_Contact_2_FullName_editText);
        Quick_Contact_2_PhoneNo_editText = findViewById(R.id.Quick_Contact_2_PhoneNo_editText);
        Quick_Contact_3_FullName_editText = findViewById(R.id.Quick_Contact_3_FullName_editText);
        Quick_Contact_3_PhoneNo_editText = findViewById(R.id.Quick_Contact_3_PhoneNo_editText);
        Quick_Contact_4_FullName_editText = findViewById(R.id.Quick_Contact_4_FullName_editText);
        Quick_Contact_4_PhoneNo_editText = findViewById(R.id.Quick_Contact_4_PhoneNo_editText);
        Quick_Contact_5_FullName_editText = findViewById(R.id.Quick_Contact_5_FullName_editText);
        Quick_Contact_5_PhoneNo_editText = findViewById(R.id.Quick_Contact_5_PhoneNo_editText);


        Quick_Contact_1_FullName = getIntent().getStringExtra("Quick_Contact_1_FullName");
        Quick_Contact_1_FullName_editText.setText(Quick_Contact_1_FullName);
        Quick_Contact_1_PhoneNo = getIntent().getStringExtra("Quick_Contact_1_PhoneNo");
        Quick_Contact_1_PhoneNo_editText.setText(Quick_Contact_1_PhoneNo);
        Quick_Contact_2_FullName = getIntent().getStringExtra("Quick_Contact_2_FullName");
        Quick_Contact_2_FullName_editText.setText(Quick_Contact_2_FullName);
        Quick_Contact_2_PhoneNo = getIntent().getStringExtra("Quick_Contact_2_PhoneNo");
        Quick_Contact_2_PhoneNo_editText.setText(Quick_Contact_2_PhoneNo);
        Quick_Contact_3_FullName = getIntent().getStringExtra("Quick_Contact_3_FullName");
        Quick_Contact_3_FullName_editText.setText(Quick_Contact_3_FullName);
        Quick_Contact_3_PhoneNo = getIntent().getStringExtra("Quick_Contact_3_PhoneNo");
        Quick_Contact_3_PhoneNo_editText.setText(Quick_Contact_3_PhoneNo);
        Quick_Contact_4_FullName = getIntent().getStringExtra("Quick_Contact_4_FullName");
        Quick_Contact_4_FullName_editText.setText(Quick_Contact_4_FullName);
        Quick_Contact_4_PhoneNo = getIntent().getStringExtra("Quick_Contact_4_PhoneNo");
        Quick_Contact_4_PhoneNo_editText.setText(Quick_Contact_4_PhoneNo);
        Quick_Contact_5_FullName = getIntent().getStringExtra("Quick_Contact_5_FullName");
        Quick_Contact_5_FullName_editText.setText(Quick_Contact_5_FullName);
        Quick_Contact_5_PhoneNo = getIntent().getStringExtra("Quick_Contact_5_PhoneNo");
        Quick_Contact_5_PhoneNo_editText.setText(Quick_Contact_5_PhoneNo);


        Quick_Contact_1_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callContact1();
            }
        });

        Quick_Contact_2_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callContact2();
            }
        });

        Quick_Contact_3_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callContact3();
            }
        });

        Quick_Contact_4_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callContact4();
            }
        });

        Quick_Contact_5_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callContact5();
            }
        });
    }

    private void callContact1()
    {
        String number = Quick_Contact_1_PhoneNo;
        if(ContextCompat.checkSelfPermission(Quick_Contacts_Page.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Quick_Contacts_Page.this, new String[]{android.Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    private void callContact2()
    {
        String number = Quick_Contact_2_PhoneNo;
        if(ContextCompat.checkSelfPermission(Quick_Contacts_Page.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Quick_Contacts_Page.this, new String[]{android.Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    private void callContact3()
    {
        String number = Quick_Contact_3_PhoneNo;
        if(ContextCompat.checkSelfPermission(Quick_Contacts_Page.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Quick_Contacts_Page.this, new String[]{android.Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    private void callContact4()
    {
        String number = Quick_Contact_4_PhoneNo;
        if(ContextCompat.checkSelfPermission(Quick_Contacts_Page.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Quick_Contacts_Page.this, new String[]{android.Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    private void callContact5()
    {
        String number = Quick_Contact_5_PhoneNo;
        if(ContextCompat.checkSelfPermission(Quick_Contacts_Page.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Quick_Contacts_Page.this, new String[]{Manifest.permission.CALL_PHONE}, Call_Permission_Code);
        }

        else
        {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1001 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if(requestCode == 44 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callContact1();
                callContact2();
                callContact3();
                callContact4();
                callContact5();
            }
        }

        else
        {
            Toast.makeText(Quick_Contacts_Page.this, "App Permission Denied!! Check Settings", Toast.LENGTH_LONG);
        }
    }

}