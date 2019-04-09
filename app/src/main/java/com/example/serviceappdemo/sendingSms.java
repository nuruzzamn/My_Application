package com.example.serviceappdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendingSms extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    private  Button mSendMessageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_sms);

/*
        Button sendMessageBtn = (Button) findViewById(R.id.sendSMS);
        final EditText messagetEt = (EditText) findViewById(R.id.message);
        final EditText mPhoneNoEt = (EditText) findViewById(R.id.phoneNumber);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messagetEt.getText().toString();
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                }
            }
        });*/

        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        ///
        messageSend();
        //


    }

    private void messageSend() {
        Button sendMessageBtn = (Button) findViewById(R.id.sendSMS);
        final EditText messagetEt = (EditText) findViewById(R.id.message);
        final EditText mPhoneNoEt = (EditText) findViewById(R.id.phoneNumber);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messagetEt.getText().toString();
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                }
            }
        });

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(sendingSms.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(sendingSms.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(sendingSms.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    sendSMS.setEnabled(false);

                }
                break;
        }
    }
}