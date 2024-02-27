package com.example.sosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class User_Profile_Page extends AppCompatActivity {

    TextView User_id_txt;
    TextInputEditText User_FullName_editText;
    TextInputEditText User_PhoneNo_editText;
    TextInputEditText User_Address_Line1_editText;
    TextInputEditText User_Address_Line2_editText;
    TextInputEditText User_PinCode_editText;


    TextInputEditText Sos_Contact_1_FullName_editText;
    TextInputEditText Sos_Contact_1_PhoneNo_editText;
    TextInputEditText Sos_Contact_2_FullName_editText;
    TextInputEditText Sos_Contact_2_PhoneNo_editText;
    TextInputEditText Sos_Contact_3_FullName_editText;
    TextInputEditText Sos_Contact_3_PhoneNo_editText;


    TextInputEditText Quick_Contact_1_FullName_editText;
    TextInputEditText Quick_Contact_1_PhoneNo_editText;
    TextInputEditText Quick_Contact_2_FullName_editText;
    TextInputEditText Quick_Contact_2_PhoneNo_editText;
    TextInputEditText Quick_Contact_3_FullName_editText;
    TextInputEditText Quick_Contact_3_PhoneNo_editText;
    TextInputEditText Quick_Contact_4_FullName_editText;
    TextInputEditText Quick_Contact_4_PhoneNo_editText;
    TextInputEditText Quick_Contact_5_FullName_editText;
    TextInputEditText Quick_Contact_5_PhoneNo_editText;

    String User_id, User_FullName, User_PhoneNo, User_Address_Line1, User_Address_Line2, User_PinCode;
    String Sos_Contact_1_FullName, Sos_Contact_1_PhoneNo, Sos_Contact_2_FullName, Sos_Contact_2_PhoneNo, Sos_Contact_3_FullName, Sos_Contact_3_PhoneNo;
    String Quick_Contact_1_FullName, Quick_Contact_1_PhoneNo,Quick_Contact_2_FullName, Quick_Contact_2_PhoneNo, Quick_Contact_3_FullName, Quick_Contact_3_PhoneNo, Quick_Contact_4_FullName, Quick_Contact_4_PhoneNo, Quick_Contact_5_FullName, Quick_Contact_5_PhoneNo;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));

        User_id_txt = findViewById(R.id.User_id_txt);
        User_FullName_editText = findViewById(R.id.User_FullName_editText);
        User_PhoneNo_editText = findViewById(R.id.User_PhoneNo_editText);
        User_Address_Line1_editText = findViewById(R.id.User_Address_Line1_editText);
        User_Address_Line2_editText = findViewById(R.id.User_Address_Line2_editText);
        User_PinCode_editText = findViewById(R.id.User_PinCode_editText);


        Sos_Contact_1_FullName_editText = findViewById(R.id.Sos_Contact_1_FullName_editText);
        Sos_Contact_1_PhoneNo_editText = findViewById(R.id.Sos_Contact_1_PhoneNo_editText);
        Sos_Contact_2_FullName_editText = findViewById(R.id.Sos_Contact_2_FullName_editText);
        Sos_Contact_2_PhoneNo_editText = findViewById(R.id.Sos_Contact_2_PhoneNo_editText);
        Sos_Contact_3_FullName_editText = findViewById(R.id.Sos_Contact_3_FullName_editText);
        Sos_Contact_3_PhoneNo_editText = findViewById(R.id.Sos_Contact_3_PhoneNo_editText);


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


        User_id = getIntent().getStringExtra("User_id");
        User_id_txt.setText(User_id);
        User_FullName = getIntent().getStringExtra("User_FullName");
        User_FullName_editText.setText(User_FullName);
        User_PhoneNo = getIntent().getStringExtra("User_PhoneNo");
        User_PhoneNo_editText.setText(User_PhoneNo);
        User_Address_Line1 = getIntent().getStringExtra("User_Address_Line1");
        User_Address_Line1_editText.setText(User_Address_Line1);
        User_Address_Line2 = getIntent().getStringExtra("User_Address_Line2");
        User_Address_Line2_editText.setText(User_Address_Line2);
        User_PinCode = getIntent().getStringExtra("User_PinCode");
        User_PinCode_editText.setText(User_PinCode);


        Sos_Contact_1_FullName = getIntent().getStringExtra("Sos_Contact_1_FullName");
        Sos_Contact_1_FullName_editText.setText(Sos_Contact_1_FullName);
        Sos_Contact_1_PhoneNo = getIntent().getStringExtra("Sos_Contact_1_PhoneNo");
        Sos_Contact_1_PhoneNo_editText.setText(Sos_Contact_1_PhoneNo);
        Sos_Contact_2_FullName = getIntent().getStringExtra("Sos_Contact_2_FullName");
        Sos_Contact_2_FullName_editText.setText(Sos_Contact_2_FullName);
        Sos_Contact_2_PhoneNo = getIntent().getStringExtra("Sos_Contact_2_PhoneNo");
        Sos_Contact_2_PhoneNo_editText.setText(Sos_Contact_2_PhoneNo);
        Sos_Contact_3_FullName = getIntent().getStringExtra("Sos_Contact_3_FullName");
        Sos_Contact_3_FullName_editText.setText(Sos_Contact_3_FullName);
        Sos_Contact_3_PhoneNo = getIntent().getStringExtra("Sos_Contact_3_PhoneNo");
        Sos_Contact_3_PhoneNo_editText.setText(Sos_Contact_3_PhoneNo);


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


        Button Update_User_Data_Btn = findViewById(R.id.Update_User_Data_Btn);
        Update_User_Data_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String New_User_id = User_id_txt.getText().toString().replace(".", "");
                String New_User_FullName = User_FullName_editText.getText().toString();
                String New_User_PhoneNo = User_PhoneNo_editText.getText().toString();
                String New_User_Address_Line1 = User_Address_Line1_editText.getText().toString();
                String New_User_Address_Line2 = User_Address_Line2_editText.getText().toString();
                String New_User_PinCode = User_PinCode_editText.getText().toString();

                String New_Sos_Contact_1_FullName = Sos_Contact_1_FullName_editText.getText().toString();
                String New_Sos_Contact_1_PhoneNo = Sos_Contact_1_PhoneNo_editText.getText().toString();
                String New_Sos_Contact_2_FullName = Sos_Contact_2_FullName_editText.getText().toString();
                String New_Sos_Contact_2_PhoneNo = Sos_Contact_2_PhoneNo_editText.getText().toString();
                String New_Sos_Contact_3_FullName = Sos_Contact_3_FullName_editText.getText().toString();
                String New_Sos_Contact_3_PhoneNo = Sos_Contact_3_PhoneNo_editText.getText().toString();

                String New_Quick_Contact_1_FullName = Quick_Contact_1_FullName_editText.getText().toString();
                String New_Quick_Contact_1_PhoneNo = Quick_Contact_1_PhoneNo_editText.getText().toString();
                String New_Quick_Contact_2_FullName = Quick_Contact_2_FullName_editText.getText().toString();
                String New_Quick_Contact_2_PhoneNo = Quick_Contact_2_PhoneNo_editText.getText().toString();
                String New_Quick_Contact_3_FullName = Quick_Contact_3_FullName_editText.getText().toString();
                String New_Quick_Contact_3_PhoneNo = Quick_Contact_3_PhoneNo_editText.getText().toString();
                String New_Quick_Contact_4_FullName = Quick_Contact_4_FullName_editText.getText().toString();
                String New_Quick_Contact_4_PhoneNo = Quick_Contact_4_PhoneNo_editText.getText().toString();
                String New_Quick_Contact_5_FullName = Quick_Contact_5_FullName_editText.getText().toString();
                String New_Quick_Contact_5_PhoneNo = Quick_Contact_5_PhoneNo_editText.getText().toString();


                Toast.makeText(User_Profile_Page.this, "Data Updated Sucessfully!!", Toast.LENGTH_SHORT).show();
                HashMap<String, String> map = new HashMap<>();
                map.put("User_id", New_User_id);
                map.put("User_FullName", New_User_FullName);
                map.put("User_PhoneNo", New_User_PhoneNo);
                map.put("User_Address_Line1", New_User_Address_Line1);
                map.put("User_Address_Line2", New_User_Address_Line2);
                map.put("User_PinCode", New_User_PinCode);

                map.put("Sos_Contact_1_FullName", New_Sos_Contact_1_FullName);
                map.put("Sos_Contact_1_PhoneNo", New_Sos_Contact_1_PhoneNo);
                map.put("Sos_Contact_2_FullName", New_Sos_Contact_2_FullName);
                map.put("Sos_Contact_2_PhoneNo", New_Sos_Contact_2_PhoneNo);
                map.put("Sos_Contact_3_FullName", New_Sos_Contact_3_FullName);
                map.put("Sos_Contact_3_PhoneNo", New_Sos_Contact_3_PhoneNo);

                map.put("Quick_Contact_1_FullName", New_Quick_Contact_1_FullName);
                map.put("Quick_Contact_1_PhoneNo", New_Quick_Contact_1_PhoneNo);
                map.put("Quick_Contact_2_FullName", New_Quick_Contact_2_FullName);
                map.put("Quick_Contact_2_PhoneNo", New_Quick_Contact_2_PhoneNo);
                map.put("Quick_Contact_3_FullName", New_Quick_Contact_3_FullName);
                map.put("Quick_Contact_3_PhoneNo", New_Quick_Contact_3_PhoneNo);
                map.put("Quick_Contact_4_FullName", New_Quick_Contact_4_FullName);
                map.put("Quick_Contact_4_PhoneNo", New_Quick_Contact_4_PhoneNo);
                map.put("Quick_Contact_5_FullName", New_Quick_Contact_5_FullName);
                map.put("Quick_Contact_5_PhoneNo", New_Quick_Contact_5_PhoneNo);


                db = FirebaseDatabase.getInstance().getReference("User Info");
                db.child(User_id).setValue(map);

                startActivity(new Intent(User_Profile_Page.this, MainActivity.class));
            }
        });
    }
}