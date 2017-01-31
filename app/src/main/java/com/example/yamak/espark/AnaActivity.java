package com.example.yamak.espark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class AnaActivity extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    public void bul(View view) {
        CharSequence location = spinner.getSelectedItem().toString();
        Intent ıntent = new Intent(AnaActivity.this,MapActivity.class);
        ıntent.putExtra("Location",location);
        startActivity(ıntent);
    }
}
