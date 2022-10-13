package com.illimani.laboratoriodegiros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListarGirosActivity extends AppCompatActivity {
    HttpURLConnection conn;
    URL url = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_giros);

        new ProcesoAsync().execute("http://www.clasespersonales.com/giros/listades.php?qci="+5960219);
        //new ProcesoAsync().execute("");
     }
    private class ProcesoAsync extends AsyncTask<String, Void,String> {

        //http://www.clasespersonales.com/giros/listades.php?qci



        @Override
        protected String doInBackground(String... strings) {
               return BajarDatos(strings[0]);
           /*try {
                Intent intent = getIntent();
                final String id = intent.getStringExtra("id");
                // Enter URL address where your php file resides
                url = new URL("http://www.ssss.ss/api/puente.php?dni=" + id);


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }*/


        }
          protected void onPostExecute(String resultado){
            JSONObject jsonObject;
            String id="";
            String ci="";
            String monto="";
            TextView tv = (TextView)findViewById(R.id.id);
            TextView tv2 = (TextView)findViewById(R.id.ci);
            TextView tv3 = (TextView)findViewById(R.id.monto);
             try {
                jsonObject = new JSONObject(resultado);
                JSONArray unidades = jsonObject.getJSONArray("clientes");
                for (int i = 0; i < unidades.length() ; i++) {
                    JSONObject jb = unidades.getJSONObject(i);
                    id = id + jb.getString("id")+"\n";
                    ci= ci + jb.getString("ci")+"\n";
                    monto = monto + jb.getString("monto")+"\n";
                }

            }catch (JSONException err){
                Log.d("Error", err.toString());
            }
            tv.setText(id);
            tv2.setText(ci);
            tv3.setText(resultado);
        }
    }
    private static String BajarDatos(String url){
        InputStream pt;
        String res="";
        try {
            URL pageUrl = new URL(url);
            HttpURLConnection uc = (HttpURLConnection)pageUrl.openConnection();
            pt = uc.getInputStream();
            if(pt!=null)
                res = convierteString(pt);

        }catch (Exception e){
            int control=99;
        }
        return res;
    }
    public static String convierteString(InputStream b)throws IOException {
        BufferedReader p = new BufferedReader(new InputStreamReader(b));
        String linea;
        String respuesta="";
        while ((linea=p.readLine())!=null){
            respuesta = respuesta+linea;
        }
        p.close();
        return respuesta;
    }
}
