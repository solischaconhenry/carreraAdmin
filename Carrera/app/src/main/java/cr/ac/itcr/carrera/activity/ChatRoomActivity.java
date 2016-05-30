package cr.ac.itcr.carrera.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.adapter.ChatRoomThreadAdapter;
import cr.ac.itcr.carrera.app.Config;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;
import cr.ac.itcr.carrera.model.Message;
import cr.ac.itcr.carrera.model.User;


public class ChatRoomActivity extends AppCompatActivity {

    private String TAG = ChatRoomActivity.class.getSimpleName();

    private String description;
    private ChatRoomThreadAdapter mAdapter;
    private ArrayList<Message> messageArrayList;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    TextView txtevento, txtdetalle,txttipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        txtevento = (TextView)findViewById(R.id.shownameevent);
        txtdetalle =(TextView)findViewById(R.id.deatallesevento);
        txttipo =(TextView)findViewById(R.id.typeevent);

        Intent intent = getIntent();
        description = intent.getStringExtra("detalle");
        String title = intent.getStringExtra("name");
        String tipoevento = intent.getStringExtra("tipoevento");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (description == null) {
            Toast.makeText(getApplicationContext(), "Chat room not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        txtevento.setText(title);
        txtdetalle.setText(description);
        txttipo.setText(tipoevento);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chatroom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent i =  new Intent(this,shareProvider.class);
            Bundle bolsa =  new Bundle();
            bolsa.putString("cuerpo",txtdetalle.getText().toString());
            bolsa.putString("asunto",txtevento.getText().toString());
            bolsa.putString("tipo",txtdetalle.getText().toString());
            i.putExtras(bolsa);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
