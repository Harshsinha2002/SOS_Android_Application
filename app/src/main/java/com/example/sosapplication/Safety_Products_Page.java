package com.example.sosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Safety_Products_Page extends AppCompatActivity {

    int Safelet_Heart_colour_counter = 0;
    int Pepper_Spray_Gun_Heart_colour_counter = 0;
    int Safer_Pendant_Heart_colour_counter = 0;
    int Safety_Rod_Heart_colour_counter = 0;

    int Alarm_Wristlet_Heart_colour_counter = 0;
    int Safety_Torch_Heart_colour_counter = 0;
    int Sound_Grenade_Heart_colour_counter = 0;
    int Revolar_Heart_colour_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_products_page);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));

        TextView Safelet_Heart = findViewById(R.id.Safelet_Heart);
        TextView Pepper_Spray_Gun_Heart = findViewById(R.id.Pepper_Spray_Gun_Heart);
        TextView Safer_Pendant_Heart = findViewById(R.id.Safer_Pendant_Heart);
        TextView Safety_Rod_Heart = findViewById(R.id.Safety_Rod_Heart);
        TextView Alarm_Wristlet_Heart = findViewById(R.id.Alarm_Wristlet_Heart);
        TextView Safety_Torch_Heart = findViewById(R.id.Safety_Torch_Heart);
        TextView Sound_Grenade_Heart = findViewById(R.id.Sound_Grenade_Heart);
        TextView Revolar_Heart = findViewById(R.id.Revolar_Heart);


        ImageView Safelet_img = findViewById(R.id.safelet_img);
        ImageView Pepper_Spray_Pistol_img = findViewById(R.id.pepper_spray_pistol_img);
        ImageView Safer_Smart_Pendant_img = findViewById(R.id.safer_smart_pendant_img);
        ImageView Safety_Rod_img = findViewById(R.id.safety_rod_img);
        ImageView Alarm_Wristlet_img = findViewById(R.id.alarm_wristlet_img);
        ImageView Safety_Torch_img = findViewById(R.id.safety_torch_img);
        ImageView Sound_Grenade_Ealarm_img = findViewById(R.id.sound_grenade_ealarm_img);
        ImageView Revolar_img = findViewById(R.id.revolar_img);

        Sound_Grenade_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Sound_Grenade_Heart_colour_counter == 0)
                {
                    Sound_Grenade_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Sound_Grenade_Heart_colour_counter = 1;
                }

                else
                {
                    Sound_Grenade_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Sound_Grenade_Heart_colour_counter = 0;
                }
            }
        });


        Revolar_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Revolar_Heart_colour_counter == 0)
                {
                    Revolar_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Revolar_Heart_colour_counter = 1;
                }

                else
                {
                    Revolar_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Revolar_Heart_colour_counter = 0;
                }
            }
        });
        Safelet_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Safelet_Heart_colour_counter == 0)
                {
                    Safelet_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Safelet_Heart_colour_counter = 1;
                }

                else
                {
                    Safelet_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Safelet_Heart_colour_counter = 0;
                }
            }
        });

        Pepper_Spray_Gun_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Pepper_Spray_Gun_Heart_colour_counter == 0)
                {
                    Pepper_Spray_Gun_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Pepper_Spray_Gun_Heart_colour_counter = 1;
                }

                else
                {
                    Pepper_Spray_Gun_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Pepper_Spray_Gun_Heart_colour_counter = 0;
                }
            }
        });

        Safer_Pendant_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Safer_Pendant_Heart_colour_counter == 0)
                {
                    Safer_Pendant_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Safer_Pendant_Heart_colour_counter = 1;
                }

                else
                {
                    Safer_Pendant_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Safer_Pendant_Heart_colour_counter = 0;
                }
            }
        });

        Safety_Rod_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Safety_Rod_Heart_colour_counter == 0)
                {
                    Safety_Rod_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Safety_Rod_Heart_colour_counter = 1;
                }

                else
                {
                    Safety_Rod_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Safety_Rod_Heart_colour_counter = 0;
                }
            }
        });

        Alarm_Wristlet_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Alarm_Wristlet_Heart_colour_counter == 0)
                {
                    Alarm_Wristlet_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Alarm_Wristlet_Heart_colour_counter = 1;
                }

                else
                {
                    Alarm_Wristlet_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Alarm_Wristlet_Heart_colour_counter = 0;
                }
            }
        });

        Safety_Torch_Heart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Safety_Torch_Heart_colour_counter == 0)
                {
                    Safety_Torch_Heart.setTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(Safety_Products_Page.this, "Added to Favourite List", Toast.LENGTH_SHORT).show();
                    Safety_Torch_Heart_colour_counter = 1;
                }

                else
                {
                    Safety_Torch_Heart.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(Safety_Products_Page.this, "Removed from Favourite List", Toast.LENGTH_SHORT).show();
                    Safety_Torch_Heart_colour_counter = 0;
                }
            }
        });

        Safelet_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Safelet_Page.class));

            }
        });

        Pepper_Spray_Pistol_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Pepper_Spray_Pistol_Page.class));

            }
        });

        Safer_Smart_Pendant_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Safer_Smart_Pendant_Page.class));

            }
        });

        Safety_Rod_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Safety_Rod_Page.class));

            }
        });

        Alarm_Wristlet_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Alarm_Wristlet_Page.class));

            }
        });

        Safety_Torch_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Safety_Torch_Page.class));

            }
        });

        Sound_Grenade_Ealarm_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Sound_Grenade_Ealarm_Page.class));

            }
        });

        Revolar_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Safety_Products_Page.this, Product_Revolar_Page.class));

            }
        });

    }
}