package com.example.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private Button arriba;
    private Button abajo;
    private Button izquierda;
    private Button derecha;
    private Button salto;
    private Comunicacion com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arriba = findViewById(R.id.btn_arriba);
        abajo = findViewById(R.id.btn_abajo);
        izquierda = findViewById(R.id.btn_izq);
        derecha = findViewById(R.id.btn_der);
        salto = findViewById(R.id.btn_salto);
        com = Comunicacion.getInstance();
        com.addObserver(this);
        Thread t = new Thread(com);
        t.start();

        arriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("arriba");
            }
        });

        /*
        abajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("abajo");
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("izquierda");
            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("derecha");
            }
        });

        salto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("salto");
            }
        });
        */

        salto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        com.enviar("arriba");
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        com.enviar("stoparriba");
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        abajo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        com.enviar("abajo");
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        com.enviar("stopabajo");
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        izquierda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        com.enviar("izquierda");
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        com.enviar("stopizquierda");
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        derecha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        com.enviar("derecha");
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        com.enviar("stopderecha");
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        final String mensaje= (String) o;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
            }
        });
    }
}
