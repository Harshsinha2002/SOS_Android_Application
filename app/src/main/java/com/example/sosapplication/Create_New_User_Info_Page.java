package com.example.sosapplication;

import static com.example.sosapplication.R.color.pink;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Create_New_User_Info_Page extends AppCompatActivity {

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
        setContentView(R.layout.activity_create_new_user_info_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(pink)));

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


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            String email = user.getEmail();
            User_id_txt.setText(email);
        }


        Button Add_User_Data_Btn = findViewById(R.id.Add_User_Data_Btn);
        Add_User_Data_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User_id = User_id_txt.getText().toString().replace(".", "");
                User_FullName = User_FullName_editText.getText().toString();
                User_PhoneNo = User_PhoneNo_editText.getText().toString();
                User_Address_Line1 = User_Address_Line1_editText.getText().toString();
                User_Address_Line2 = User_Address_Line2_editText.getText().toString();
                User_PinCode = User_PinCode_editText.getText().toString();

                Sos_Contact_1_FullName = Sos_Contact_1_FullName_editText.getText().toString();
                Sos_Contact_1_PhoneNo = Sos_Contact_1_PhoneNo_editText.getText().toString();
                Sos_Contact_2_FullName = Sos_Contact_2_FullName_editText.getText().toString();
                Sos_Contact_2_PhoneNo = Sos_Contact_2_PhoneNo_editText.getText().toString();
                Sos_Contact_3_FullName = Sos_Contact_3_FullName_editText.getText().toString();
                Sos_Contact_3_PhoneNo = Sos_Contact_3_PhoneNo_editText.getText().toString();

                Quick_Contact_1_FullName = Quick_Contact_1_FullName_editText.getText().toString();
                Quick_Contact_1_PhoneNo = Quick_Contact_1_PhoneNo_editText.getText().toString();
                Quick_Contact_2_FullName = Quick_Contact_2_FullName_editText.getText().toString();
                Quick_Contact_2_PhoneNo = Quick_Contact_2_PhoneNo_editText.getText().toString();
                Quick_Contact_3_FullName = Quick_Contact_3_FullName_editText.getText().toString();
                Quick_Contact_3_PhoneNo = Quick_Contact_3_PhoneNo_editText.getText().toString();
                Quick_Contact_4_FullName = Quick_Contact_4_FullName_editText.getText().toString();
                Quick_Contact_4_PhoneNo = Quick_Contact_4_PhoneNo_editText.getText().toString();
                Quick_Contact_5_FullName = Quick_Contact_5_FullName_editText.getText().toString();
                Quick_Contact_5_PhoneNo = Quick_Contact_5_PhoneNo_editText.getText().toString();

                if(User_id.isEmpty() || User_PhoneNo.isEmpty()|| User_Address_Line1.isEmpty()|| User_Address_Line2.isEmpty()|| User_PinCode.isEmpty()|| Sos_Contact_1_FullName.isEmpty()|| Sos_Contact_1_PhoneNo.isEmpty() || Sos_Contact_2_FullName.isEmpty()|| Sos_Contact_2_PhoneNo.isEmpty() || Sos_Contact_3_FullName.isEmpty()|| Sos_Contact_3_PhoneNo.isEmpty() || Quick_Contact_1_FullName.isEmpty() || Quick_Contact_1_PhoneNo.isEmpty() || Quick_Contact_2_FullName.isEmpty() || Quick_Contact_2_PhoneNo.isEmpty() || Quick_Contact_3_FullName.isEmpty() || Quick_Contact_3_PhoneNo.isEmpty() || Quick_Contact_4_FullName.isEmpty() || Quick_Contact_4_PhoneNo.isEmpty() || Quick_Contact_5_FullName.isEmpty() || Quick_Contact_5_PhoneNo.isEmpty())
                {
                    Toast.makeText(Create_New_User_Info_Page.this, "Please Enter All Fields!!", Toast.LENGTH_SHORT).show();
                }

                else {

                    Toast.makeText(Create_New_User_Info_Page.this, "User Data Added Sucessfully!!", Toast.LENGTH_SHORT).show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("User_id", User_id);
                    map.put("User_FullName", User_FullName);
                    map.put("User_PhoneNo", User_PhoneNo);
                    map.put("User_Address_Line1", User_Address_Line1);
                    map.put("User_Address_Line2", User_Address_Line2);
                    map.put("User_PinCode", User_PinCode);

                    map.put("Sos_Contact_1_FullName", Sos_Contact_1_FullName);
                    map.put("Sos_Contact_1_PhoneNo", Sos_Contact_1_PhoneNo);
                    map.put("Sos_Contact_2_FullName", Sos_Contact_2_FullName);
                    map.put("Sos_Contact_2_PhoneNo", Sos_Contact_2_PhoneNo);
                    map.put("Sos_Contact_3_FullName", Sos_Contact_3_FullName);
                    map.put("Sos_Contact_3_PhoneNo", Sos_Contact_3_PhoneNo);

                    map.put("Quick_Contact_1_FullName", Quick_Contact_1_FullName);
                    map.put("Quick_Contact_1_PhoneNo", Quick_Contact_1_PhoneNo);
                    map.put("Quick_Contact_2_FullName", Quick_Contact_2_FullName);
                    map.put("Quick_Contact_2_PhoneNo", Quick_Contact_2_PhoneNo);
                    map.put("Quick_Contact_3_FullName", Quick_Contact_3_FullName);
                    map.put("Quick_Contact_3_PhoneNo", Quick_Contact_3_PhoneNo);
                    map.put("Quick_Contact_4_FullName", Quick_Contact_4_FullName);
                    map.put("Quick_Contact_4_PhoneNo", Quick_Contact_4_PhoneNo);
                    map.put("Quick_Contact_5_FullName", Quick_Contact_5_FullName);
                    map.put("Quick_Contact_5_PhoneNo", Quick_Contact_5_PhoneNo);


                    db = FirebaseDatabase.getInstance().getReference("User Info");
                    db.child(User_id).setValue(map);

                    startActivity(new Intent(Create_New_User_Info_Page.this, MainActivity.class));
                }
            }
        });


        Button Sign_Out = findViewById(R.id.Sign_Out);
        Sign_Out.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Create_New_User_Info_Page.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}