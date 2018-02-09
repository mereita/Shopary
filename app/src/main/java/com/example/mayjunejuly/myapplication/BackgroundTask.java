package com.example.mayjunejuly.myapplication;

/**
 * Created by MAYJUNEJULY on 1/14/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AppCompatActivity {
    Context ctx;
    //List<Product> productList;
    //the recyclerview
    private TextView textViewID = null;
    private TextView textViewName,textViewDescription,textViewStock,textViewPrice;
    private ImageView imageView;
    String barcode;
    //BackgroundTask(Context ctx){
        //this.ctx = ctx;
    //}
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            barcode = extras.getString("barcode");
            Log.v("Pass","success");
        }
        else{
            Log.v("Error","notfound");
        }
        textViewID = (TextView) findViewById(R.id.textViewID);
        textViewName =  (TextView) findViewById(R.id.textViewName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewStock =  (TextView) findViewById(R.id.textViewStock);
        textViewPrice =  (TextView) findViewById(R.id.textViewPrice);
        imageView =  (ImageView) findViewById(R.id.imageView);
        String method ="barcode";
        new getJSON().execute(method,barcode);
        //textViewID.setText("555");
    }
    public class getJSON extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String queryUrl = "http://cpehotel.hol.es/queryID.php";
            //String loginUrl = "http://waiijoswag.esy.es/login.php";
            //String ptloginUrl = "http://waiijoswag.esy.es/login_patient.php";
            String barcode = params[1];
            try {
                URL url = new URL(queryUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("productID", "UTF-8") + "=" + URLEncoder.encode(barcode, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.v("TAG", response.toString());
                return response.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Toast.makeText(BackgroundTask.this, result, Toast.LENGTH_LONG).show();
            loadIntoListView(result);
        }
    }
    public void loadIntoListView(String response) {
        if (response != null) {
            try {
                //converting the string to json array object
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    //traversing through all the object
                    //getting product object from json array
                    JSONObject product = array.getJSONObject(i);
                    //adding the product to product list
                    String ID = product.getString("id");
                    String Name = product.getString("name");
                    String price = String.valueOf(product.getDouble("price"));
                    String stock = String.valueOf(product.getInt("stock"));
                    String Desc = product.getString("description");
                    String url = product.getString("image");
                    Log.v("ID", ID);
                    Log.v("Name", Name);
                    Log.v("Desc", Desc);
                    Log.v("price", price);
                    Log.v("Stock",stock);
                   textViewName.setText(Name+"");
                   textViewPrice.setText(price+"");
                   textViewDescription.setText(Desc+"");
                    textViewStock.setBackgroundColor(Color.BLUE);
                   textViewStock.setText(stock+"");
                    Glide.with(this)
                            .load(url)
                            .into(imageView);
                  textViewID.setText(ID+"");
                    //Snackbar.make(findViewById(android.R.id.content), ID, Snackbar.LENGTH_LONG).show();
                   // Toast.makeText(BackgroundTask.this, ID, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}