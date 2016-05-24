package cr.ac.itcr.carrera.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.activity.vistaCrearAdmFragment;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;
import cr.ac.itcr.carrera.model.Eventos;

/**
 * Created by usuario on 21/5/2016.
 */
public class AdapterView extends BaseAdapter {
    private String TAG = AdapterView.class.getSimpleName();
    LayoutInflater minflater;
    ArrayList<Eventos> lista;
    public AdapterView(Context context, ArrayList<Eventos> lista) {
        minflater = LayoutInflater.from(context);
        this.lista=lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView==null){
            convertView=minflater.inflate(R.layout.fragment_vista_crear_adm,null);

        }
        final TextView namelvl = (TextView)convertView.findViewById(R.id.namelvl);
        final TextView idlvl = (TextView)convertView.findViewById(R.id.idlvl);
        final Switch swlvl = (Switch)convertView.findViewById(R.id.swlvl);

        namelvl.setText(lista.get(position).getNombre());
        idlvl.setText(lista.get(position).getId());
        swlvl.setChecked(lista.get(position).isAdmin());
        swlvl.setText(lista.get(position).getTipo());


        swlvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "endpoint: " + parent.getClass());
                if(swlvl.getText() == "Admin"){
                    String endPoint = EndPoints.ADMINUSERS;

                    Log.e(TAG, "endpoint: " + endPoint);

                    StringRequest strReq = new StringRequest(Request.Method.PUT,
                            endPoint, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            NetworkResponse networkResponse = error.networkResponse;
                            Log.e(TAG, "VolleyAdmin error: " + error.getMessage() + ", code: " + networkResponse);
                            Toast.makeText(parent.getContext(), "VolleyAdmin error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("idUsuario", idlvl.getText().toString());
                            if(swlvl.isChecked()) {
                                params.put("admin","1");
                            }
                            else{
                                params.put("admin","0");
                            }
                            Log.e(TAG, "params: " + params.toString());
                            return params;
                        }
                    };

                    //Adding request to request queue
                    MyApplication.getInstance().addToRequestQueue(strReq);
                }else if(swlvl.getText() == "Bloq"){
                    String endPoint = EndPoints.BLOQUSERS;

                    Log.e(TAG, "endpoint: " + endPoint);

                    StringRequest strReq = new StringRequest(Request.Method.PUT,
                            endPoint, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            NetworkResponse networkResponse = error.networkResponse;
                            Log.e(TAG, "VolleyBloq error: " + error.getMessage() + ", code: " + networkResponse);
                            Toast.makeText(parent.getContext(), "VolleyBloq error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("idUsuario", idlvl.getText().toString());
                            if(swlvl.isChecked()) {
                                params.put("bloqueado","1");
                            }
                            else{
                                params.put("bloqueado","0");
                            }
                            Log.e(TAG, "params: " + params.toString());
                            return params;
                        }
                    };

                    //Adding request to request queue
                    MyApplication.getInstance().addToRequestQueue(strReq);

                }
            }
        });

        return convertView;
    }
}
