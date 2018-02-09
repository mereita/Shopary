package com.example.mayjunejuly.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.zxing.client.result.ResultParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//implementing onclicklistener
public class InventoryActivity extends AppCompatActivity{
    private ProgressDialog pd;
    //View Objects
    private Button buttonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        //intializing scan object
        //qrScan = new IntentIntegrator(this);
        //attaching onclick listener
        //buttonScan.setOnClickListener(this);
    }

    public void startScan(View view) {
        //initiating the qr code scan
        Intent intent = new Intent(InventoryActivity.this, ScanActivity.class);
        startActivity(intent);
    }
    public void startSearch(View view) {
        //initiating the qr code scan
        Intent intent = new Intent(InventoryActivity.this, SearchActivity.class);
        startActivity(intent);
    }

}