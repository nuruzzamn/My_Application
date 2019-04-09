package com.example.serviceappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button email,phone,sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(Button)findViewById(R.id.email);
        phone=(Button)findViewById(R.id.phone);
        sms=(Button)findViewById(R.id.sms);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),emailActivity.class);
                startActivity(i);
            }
        });


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),phoneActivity.class);
                startActivity(i);
            }
        });


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),sendingSms.class);
                startActivity(i);
            }
        });
    }
}
