package com.illimani.laboratoriodegiros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void listarG(View vista){
        Intent intent = new Intent(MainActivity.this,ListarGirosActivity.class);
        startActivity(intent);
    }
    public void buscarCi(View vista){
        Intent intent = new Intent(MainActivity.this,BuscarCiActivity.class);
        startActivity(intent);
    }

}
