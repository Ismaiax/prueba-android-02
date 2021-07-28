package com.ismaias.aplicacinejemploicono;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private RequestQueue queue;
    private TextView mensajeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensajeUsuario = findViewById(R.id.textIsma);


        queue = Volley.newRequestQueue(this);
        obtenerDatillos();



    }


    private void obtenerDatillos(){

        String url = "https://www.ismaias.com/maestria/semestre-3/iot/api/login";

        JsonRequest peticionIsmaiasSoy = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String respuestaServidor = response.getString("message");
                    Toast.makeText(MainActivity.this, "Respuesta: "+respuestaServidor, Toast.LENGTH_LONG).show();

                    mensajeUsuario.setText(respuestaServidor);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR 🥲", String.valueOf(error));
            }
        });


        queue.add(peticionIsmaiasSoy);

    }
}