package com.example.yamak.espark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GirisActivity extends AppCompatActivity {
    EditText edt_kullaniciAdi,edt_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        edt_kullaniciAdi = (EditText) findViewById(R.id.edt_kul);
        edt_sifre = (EditText) findViewById(R.id.edt_pass);
    }

    public void kayıtOl(View view) {
    }

    public void giris_yap(View view) {
        if(edt_kullaniciAdi.getText().toString().equals("fatih") && edt_sifre.getText().toString().equals("1234"))
        {
            Intent ıntent = new Intent(GirisActivity.this,AnaActivity.class);
            startActivity(ıntent);
        }
        else
        {
            Toast.makeText(GirisActivity.this,"Hatalı kullanıcı adı veya sifre",Toast.LENGTH_SHORT).show();
        }
    }
}
