package com.illimani.laboratoriodegiros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscarCiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_ci);
        new ProcesoAsync().execute("http://www.clasespersonales.com/giros/listades.php");

    }
    private class ProcesoAsync extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return BajarDatos(strings[0]);
        }
        protected void onPostExecute(String resultado){
            JSONObject jsonObject;
            String id="";
            String ci="";
            String monto="";
            int cantMonto;
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
            tv3.setText(monto);
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
