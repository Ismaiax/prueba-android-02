package com.ismaias.aplicacinejemploicono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class falsoRegistro extends AppCompatActivity {

    EditText cajita;
    private RequestQueue requestQueue;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falso_registro);

        cajita = findViewById(R.id.isma_inp);

        RequestQueue queue = Volley.newRequestQueue(this);


        final Button button = findViewById(R.id.isma_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("SOLOVINO","Vamos bien");
                Log.v("SOLOVINO", cajita.getText().toString());


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
                        params.put("user", cajita.getText().toString());
                        params.put("email", "ddddd@ddddd.com");
                        return params;
                    }
                };

                queue.add(strRequest);




            }
        });
    }
}