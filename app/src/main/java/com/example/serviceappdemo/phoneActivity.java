package com.example.serviceappdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

public class phoneActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;


    Button callButton;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }

        }
    }

    public boolean checkPermission() {

        int CallPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
        int ContactPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);

        return CallPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ContactPermissionResult == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(phoneActivity.this, new String[]
                {
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                }, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {


            case PERMISSION_REQUEST_CODE:
                callButton = (Button) findViewById(R.id.call);


                if (grantResults.length > 0) {

                    boolean CallPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;


                    if (CallPermission && ReadContactsPermission) {

                        Toast.makeText(phoneActivity.this,
                                "Permission accepted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(phoneActivity.this,
                                "Permission denied", Toast.LENGTH_LONG).show();

                        callButton.setEnabled(false);

                    }
                    break;
                }
        }
    }


    public void call(View view) {
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        String phoneNum = phoneNumber.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            phoneNumber.setText("");
        }else {
            Toast.makeText(phoneActivity.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }

    }
}
