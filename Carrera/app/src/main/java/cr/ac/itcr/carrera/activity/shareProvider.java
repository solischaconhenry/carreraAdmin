package cr.ac.itcr.carrera.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;

public class shareProvider extends AppCompatActivity {
    private String TAG = shareProvider.class.getSimpleName();
    private ArrayList<String> to; //who send de email
    EditText txtFrom, txtAsunto;
    EditText txtMesg;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_provider);

        to = new ArrayList<>();

        txtFrom = (EditText)findViewById(R.id.txtFrom);
        txtMesg = (EditText)findViewById(R.id.txtMessage);
        txtAsunto = (EditText)findViewById(R.id.txtAsunto);
        sendEmail = (Button)findViewById(R.id.btnEmail);

        //obtener los datos recibidos en bundle

        Bundle bolsarR  = getIntent().getExtras();
        txtMesg.setText(bolsarR.get("cuerpo").toString());
        txtAsunto.setText(bolsarR.get("asunto").toString());


        fetchUserEmails();

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    /**
     * función para obtener los correo para remitir la información
     */
    private void fetchUserEmails() {

        String endPoint = EndPoints.USERS;

        StringRequest strReq = new StringRequest(Request.Method.GET,
                endPoint, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    // check for error flag
                    if (obj.getBoolean("success") == true) {
                        JSONArray chatRoomsArray = obj.getJSONArray("data");
                        for (int i = 0; i < chatRoomsArray.length(); i++) {
                            JSONObject chatRoomsObj = (JSONObject) chatRoomsArray.get(i);
                            if(Integer.parseInt(chatRoomsObj.getString("admin")) == 0){ //evalua que los usuario a a inserta en to sean usuario y no admin
                                to.add(chatRoomsObj.getString("email"));
                            }
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }

    protected void sendEmail() {
        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, txtAsunto.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, txtMesg.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
