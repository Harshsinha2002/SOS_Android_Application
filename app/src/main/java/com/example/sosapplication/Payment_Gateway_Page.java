package com.example.sosapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Payment_Gateway_Page extends AppCompatActivity implements PaymentResultListener
{
    TextView User_id_txt;
    EditText Phone_no_edittxt;
    EditText Adress_line1_edittxt;
    EditText Adress_line2_edittxt;
    EditText Pin_Code_edittxt;
    TextView Quantity_Minus_btn;
    TextView Quantity_Plus_btn;
    TextView Quantity;
    TextView Total_Amt_Txt;
    Button Pay_btn;

    String phoneNumForRazorPay, emailIdForRazorPay, amountForRazorPay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));

        User_id_txt = findViewById(R.id.User_id_txt);
        Phone_no_edittxt = findViewById(R.id.Phone_no_edittxt);
        Adress_line1_edittxt = findViewById(R.id.Adress_line1_edittxt);
        Adress_line2_edittxt = findViewById(R.id.Adress_line2_edittxt);
        Pin_Code_edittxt = findViewById(R.id.Pin_Code_edittxt);
        Quantity_Minus_btn = findViewById(R.id.Quantity_Minus_btn);
        Quantity_Plus_btn = findViewById(R.id.Quantity_Plus_btn);
        Quantity = findViewById(R.id.Quantity);
        Total_Amt_Txt = findViewById(R.id.Total_Amt_Txt);
        Pay_btn = findViewById(R.id.Pay_btn);

        Checkout.preload(getApplicationContext());
        Checkout checkout = new Checkout();

        Intent intent = getIntent();
        String productPrice = intent.getStringExtra("Product_price");
        int price = Integer.parseInt(productPrice);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            String email = user.getEmail();
            User_id_txt.setText(email);
            emailIdForRazorPay = User_id_txt.getText().toString();
        }

        Quantity_Minus_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String getQuantity = Quantity.getText().toString();
                if(getQuantity.equals("0"))
                {
                    Toast.makeText(Payment_Gateway_Page.this, "Item Quantity Cannot Be Less Than 0", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    int itemQuantity = Integer.parseInt(getQuantity);
                    itemQuantity = itemQuantity - 1;
                    int totalPrice = price * itemQuantity;
                    String itemPrice = Integer.toString(totalPrice);
                    String item = Integer.toString(itemQuantity);
                    Quantity.setText(item);
                    Total_Amt_Txt.setText(itemPrice);
                }
            }
        });

        Quantity_Plus_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String getQuantity = Quantity.getText().toString();
                if(getQuantity.equals("15"))
                {
                    Toast.makeText(Payment_Gateway_Page.this, "Item Quantity Cannot Be More Than 15", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    int itemQuantity = Integer.parseInt(getQuantity);
                    itemQuantity = itemQuantity + 1;
                    int totalPrice = price * itemQuantity;
                    String itemPrice = Integer.toString(totalPrice);
                    String item = Integer.toString(itemQuantity);
                    Quantity.setText(item);
                    Total_Amt_Txt.setText(itemPrice);
                }
            }
        });

        Pay_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phoneNumber = Phone_no_edittxt.getText().toString().trim();
                String adressLine1 = Adress_line1_edittxt.getText().toString().trim();
                String adressLine2 = Adress_line2_edittxt.getText().toString().trim();
                String pincode = Pin_Code_edittxt.getText().toString().trim();
                String totalAmt = Total_Amt_Txt.getText().toString().trim();

                if (phoneNumber.isEmpty() || adressLine1.isEmpty() || adressLine2.isEmpty() || pincode.isEmpty() || totalAmt.isEmpty())
                {
                    Toast.makeText(Payment_Gateway_Page.this, "Enter all the fields!!", Toast.LENGTH_SHORT).show();
                }
                else if (phoneNumber.length() != 10)
                {
                    Toast.makeText(Payment_Gateway_Page.this, "Incorrect Phone Number!!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    phoneNumForRazorPay = phoneNumber;
                    int payableAmt = Integer.parseInt(Total_Amt_Txt.getText().toString()) * 100;
                    amountForRazorPay = Integer.toString(payableAmt);
                    startPayment();
                }
            }
        });
    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_IdUsGFjdy0i2oR");
        /**
         * Instantiate Checkout
         */

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.sos_app_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Safe You Android App");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amountForRazorPay);//pass amount in currency subunits
            options.put("prefill.email", emailIdForRazorPay);
            options.put("prefill.contact",phoneNumForRazorPay);
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 7);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s)
    {
        Toast.makeText(Payment_Gateway_Page.this, "Payment Sucess", Toast.LENGTH_LONG);
    }

    @Override
    public void onPaymentError(int i, String s)
    {
        Toast.makeText(Payment_Gateway_Page.this, "Payment Failure", Toast.LENGTH_LONG);
    }
}