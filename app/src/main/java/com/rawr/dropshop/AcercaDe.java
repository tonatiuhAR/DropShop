package com.rawr.dropshop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AcercaDe extends AppCompatActivity {
    public TextView tvContacto, tvDesarrollo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tvContacto = (TextView)findViewById(R.id.contacto_rawr);
        tvDesarrollo = (TextView)findViewById(R.id.tv_develop);
        Typeface cabinSketch = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        Typeface fingerPaint = Typeface.createFromAsset(getAssets(), "fonts/IndieFlower.ttf");
        tvDesarrollo.setTypeface(cabinSketch);
        tvContacto.setTypeface(fingerPaint);

        tvContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarCorreo(view);
            }
        });
    }

    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getResources().getString(R.string.correo_soporte_rawr)});
        startActivity(intent);
    }

}
