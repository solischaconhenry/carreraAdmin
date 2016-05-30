package cr.ac.itcr.carrera.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;

public class EditarEliminarAct extends AppCompatActivity {

    String description, title, tipoevento,_id;
    private static final String TAG = EditarEliminarAct.class.getSimpleName();

    TextView etTipoE,etNombreE,etDetallesE;
    Button btnEditar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        etTipoE= (TextView)findViewById(R.id.spTEvenEditar);
        etNombreE= (TextView)findViewById(R.id.etNombreEvenEditar);
        etDetallesE= (TextView)findViewById(R.id.etDetallesEvenEditar);

        btnEditar = (Button)findViewById(R.id.btnEditarEvenEditar);
        btnEliminar = (Button)findViewById(R.id.btnEliminarEvenEditar);



        Intent intent = getIntent();
        description = intent.getStringExtra("detalle");
        title = intent.getStringExtra("name");
        tipoevento = intent.getStringExtra("tipoevento");
        _id = intent.getStringExtra("_id");


        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombreE.setText(title);
        etDetallesE.setText(description);
        etTipoE.setText(tipoevento);


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String endPoint = EndPoints.EDITAR_EVENTO.replace("idEvento", _id);

                Log.e(TAG, "endpoint: " + endPoint);

                StringRequest strReq = new StringRequest(Request.Method.POST,
                        endPoint, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        Log.e(TAG, "VolleyLis error: " + error.getMessage() + ", code: " + networkResponse);
                        Toast.makeText(getApplicationContext(), "VolleyLis error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre", etNombreE.getText().toString());
                        params.put("descripcion", etDetallesE.getText().toString());
                        params.put("tipoevento",etTipoE.getText().toString());


                        Log.e(TAG, "params: " + params.toString());
                        return params;
                    }
                };

                //Adding request to request queue
                MyApplication.getInstance().addToRequestQueue(strReq);


            }

        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endPoint = EndPoints.DELETE_EVENTO.replace("idEvento", _id);

                Log.e(TAG, "endpoint: " + endPoint);

                StringRequest strReq = new StringRequest(Request.Method.DELETE,
                        endPoint, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        Log.e(TAG, "VolleyLis error: " + error.getMessage() + ", code: " + networkResponse);
                        Toast.makeText(getApplicationContext(), "VolleyLis error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        return null;
                    }
                };

                //Adding request to request queue
                MyApplication.getInstance().addToRequestQueue(strReq);
                Intent i = new Intent(getApplicationContext(),Eventos.class);
                startActivity(i);
            }



        });



    }

}
