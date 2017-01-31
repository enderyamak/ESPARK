package com.example.yamak.espark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by yamak on 25.12.2016.
 */

public class Kirala extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirala);
    }

    public void kirala(View view) {
        Toast.makeText(Kirala.this,"Kiralama başarılı...",Toast.LENGTH_SHORT).show();
    }
}
