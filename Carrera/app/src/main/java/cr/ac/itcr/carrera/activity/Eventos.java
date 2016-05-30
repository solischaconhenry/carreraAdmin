package cr.ac.itcr.carrera.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
import cr.ac.itcr.carrera.adapter.AdapterView;
import cr.ac.itcr.carrera.adapter.EventosAdapter;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;
import cr.ac.itcr.carrera.model.ChatRoom;

public class Eventos extends AppCompatActivity {

    private String TAG = Eventos.class.getSimpleName();
    private ArrayList<ChatRoom> chatRoomArrayList;
    private EventosAdapter mAdapter;
    ListView lvlEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);


        chatRoomArrayList = new ArrayList<>();

        lvlEventos = (ListView)findViewById(R.id.lvlEventos);
        fetchChatRooms();

        mAdapter =new EventosAdapter(this,chatRoomArrayList);
        lvlEventos.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        lvlEventos.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
                // when chat is clicked, launch full chat thread activity
                ChatRoom chatRoom = chatRoomArrayList.get(position);
                Intent intent = new Intent(Eventos.this, ChatRoomActivity.class);
                intent.putExtra("detalle", chatRoom.getDescription());
                intent.putExtra("name", chatRoom.getName());
                intent.putExtra("tipoevento", chatRoom.getTipoevento());
                startActivity(intent);
            }
        });

        lvlEventos.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
                ChatRoom chatRoom = chatRoomArrayList.get(position);
                Intent intent = new Intent(Eventos.this, EditarEliminarAct.class);
                intent.putExtra("detalle", chatRoom.getDescription());
                intent.putExtra("name", chatRoom.getName());
                intent.putExtra("tipoevento",chatRoom.getTipoevento());
                intent.putExtra("_id",chatRoom.getId());
                startActivity(intent);
                return false;
            }
        });



    }


    private void fetchChatRooms() {

        String endPoint = EndPoints.CHAT_ROOMS;

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
                            ChatRoom cr = new ChatRoom();
                            cr.setId(chatRoomsObj.getString("_id"));
                            cr.setName(chatRoomsObj.getString("nombre"));
                            cr.setDescription(chatRoomsObj.getString("descripcion"));
                            cr.setTipoevento(chatRoomsObj.getString("tipoevento"));
                            cr.setLastMessage("");
                            cr.setUnreadCount(0);
                            cr.setTimestamp("");

                            chatRoomArrayList.add(cr);
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                mAdapter.notifyDataSetChanged();

                // subscribing to all chat room topics
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

}
