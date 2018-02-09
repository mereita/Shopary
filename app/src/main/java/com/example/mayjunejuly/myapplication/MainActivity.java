package com.example.mayjunejuly.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import eu.kudan.kudan.ARAPIKey;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey("agWZcpYLYjBxCbWf2qZx6k+PWISqeGtFCqKaZwYtwS+kdn1HKiQAmsJ55STRBe9BqCw3VwG6qL+ESI5ntTF/iV/uekLG3PCokaUE0/uTzqhaYlxRdmuNBIduzBCjq3mV2na+gy3ffHH9Ipc7eIN0geTj3p+ppsmK0U399iGmN38ndIh6k2y16cByWIecMSU3yw3Ztw7gHRqf83hVhZ5T2ACGK4SNkQhhdKp+CTaR5W3amYCJBgwumqFqNFyI9UniuMk70T/cQObRQum2U51OjjbMfmEAwIBt8Q8jD2yACzye6K4/1O4pZhbGEbiDeLrAfxqMwBAe5o6vnYIilGNnpDhfi3wOHhRaqtLOVvB58GUIFTnAPvmYFVnLWRJmCUZ9FJNDyX3ALCl/alFEWh+A/a6NFjcwLGKI9drPuGG4ONFg4p0l+p3b9DZoLzszlmWAflI/UFzQa++kQn3/sclO9i0vPnpi0LWoABm5vGswLVAIX/0k6384GXxfkADI6fjGtf62XJ5ImaVDiiREa9mabWEQGoifghQG1sGNDYgBIYEpiaLsVzOfTALpe20Q7kFCMjedJImQhhuLtEK1BXfXJEed1QqUOsG9IeKxKk28GbOtOF9w3yrSF3gnJslzZxF2kEF3C6ckog8byagS+4p37FJmbpPsiKNH1Qm0LuouGcQ=");
        permissionsRequest();
    }

    // Requests app permissions
    public void permissionsRequest() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 111);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED ) {

                } else {
                    permissionsNotSelected();
                }
            }
        }
    }

    private void permissionsNotSelected() {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setTitle("Permissions Required");
        builder.setMessage("Please enable the requested permissions in the app settings in order to use this demo app");
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                System.exit(1);
            }
        });
        AlertDialog noInternet = builder.create();
        noInternet.show();
    }
    public void startOnsaleActivity(View v) {
        Intent intent = new Intent(MainActivity.this, OnsaleActivity.class);
        startActivity(intent);
    }
    public void startARActivity(View v) {
        Intent intent = new Intent(MainActivity.this, ARCameraActivity.class);
        startActivity(intent);
    }
    public void startInventoryActivity(View v){
        Intent intent = new Intent(MainActivity.this, InventoryActivity.class);
        startActivity(intent);
    }


}
