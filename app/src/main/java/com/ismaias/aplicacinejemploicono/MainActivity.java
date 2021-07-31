package com.ismaias.aplicacinejemploicono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RequestQueue queue;
    private TextView mensajeUsuario;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mensajeUsuario = findViewById(R.id.textIsma);




        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.ismaias.com/maestria/semestre-3/iot/api/login";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject obj = new JSONObject(response);

                            Log.d("SOLOVINO", obj.toString());
                            Log.d("SOLOVINO", obj.getString("toke"));

                        } catch (Throwable t) {
                            Log.e("SOLOVINO", "Could not parse malformed JSON: \"" + response + "\"");
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", "test");
                params.put("email", "ddddd@ddddd.com");
                return params;
            }
        };

        queue.add(strRequest);









        /*
        queue = Volley.newRequestQueue(this);
        try {
            obtenerDatillos();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */



        /*
        try {
            postDatos();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */



    }

    /*
    private void postDatos() throws JSONException {
        String url = "https://www.ismaias.com/maestria/semestre-3/iot/api/login";


        JSONObject jsonBody = new JSONObject();
        jsonBody.put("usar", "Isaías Tello");
        jsonBody.put("email", "ismael.tello@demo.com");
        final String requestBody = jsonBody.toString();





        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuestota", response);
                    }
                },
                error -> Toast.makeText(MainActivity.this, "Errior", Toast.LENGTH_LONG).show()){




                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user", "Ismael Tello");
                    params.put("email", "demox@desdfsdfsfsdfsdf.com");
                    return params;
                }


            };




        JsonRequest peticionIsmaiasSoy = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String respuestaServidor = response.getString("message");
                    String respuestaUsuario = response.getString("user");
                    int respuestaCodigo = response.getInt("code");

                    Toast.makeText(MainActivity.this, "Respuesta: "+respuestaServidor, Toast.LENGTH_LONG).show();

                    mensajeUsuario.setText(respuestaServidor);

                    if(respuestaCodigo == 1) {
                        Toast.makeText(MainActivity.this, "XXX: "+respuestaCodigo, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, Bienvenida.class);
                        intent.putExtra("esteEsUsuario", respuestaUsuario); // pass your values and retrieve them in the other Activity using keyName startActivity(intent);
                        startActivity(intent);

                    }




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

        //requestQueue = Volley.newRequestQueue(MainActivity.this);
        queue.add(peticionIsmaiasSoy);
    }



     */
    private void obtenerDatillos() throws JSONException {

        String url = "https://www.ismaias.com/maestria/semestre-3/iot/api/login";
        Map<String, String> params = new HashMap();
        params.put("user", "Pistache");
        params.put("email", "pi@tache.com");

        JSONObject parameters = new JSONObject(params);

        Log.i("SOLOVINO", String.valueOf(parameters));

        JsonRequest peticionIsmaiasSoy = new JsonObjectRequest(Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String respuestaServidor = response.getString("message");
                    String respuestaUsuario = response.getString("user");
                    int respuestaCodigo = response.getInt("code");

                    Log.i("SOLOVINO", String.valueOf(response));

                    Toast.makeText(MainActivity.this, "Respuesta: "+respuestaServidor, Toast.LENGTH_LONG).show();

                    mensajeUsuario.setText(respuestaServidor);

                    if(respuestaCodigo == 1) {
                        Toast.makeText(MainActivity.this, "XXX: "+respuestaCodigo, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, Bienvenida.class);
                        intent.putExtra("esteEsUsuario", respuestaUsuario); // pass your values and retrieve them in the other Activity using keyName startActivity(intent);
                        startActivity(intent);
                        //Intent intent = new Intent(MainActivity.this, Bienvenida.class);
                        //startActivity(intent.putExtra("usuario",respuestaUsuario));
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SOLOVINO 🥲", String.valueOf(error));
            }
        });


        queue.add(peticionIsmaiasSoy);

    }
}