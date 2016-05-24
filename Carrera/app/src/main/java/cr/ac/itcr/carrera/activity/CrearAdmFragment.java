package cr.ac.itcr.carrera.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
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
import java.util.HashMap;
import java.util.List;

import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.adapter.AdapterView;
import cr.ac.itcr.carrera.app.EndPoints;
import cr.ac.itcr.carrera.app.MyApplication;
import cr.ac.itcr.carrera.model.ChatRoom;
import cr.ac.itcr.carrera.model.Eventos;
import cr.ac.itcr.carrera.model.Message;
import cr.ac.itcr.carrera.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrearAdmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrearAdmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearAdmFragment extends Fragment {
    private String TAG = CrearAdmFragment.class.getSimpleName();
    ListView lvlusers;
    private ArrayList<Eventos> eventosArraylist;
    private AdapterView mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CrearAdmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearAdmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearAdmFragment newInstance(String param1, String param2) {
        CrearAdmFragment fragment = new CrearAdmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crear_adm, container, false);

        eventosArraylist = new ArrayList<>();

        ListView listV = (ListView)view.findViewById(R.id.lvladmin);

        fetchChatRooms();

        mAdapter =new AdapterView(getContext(),eventosArraylist);
        listV.setAdapter(mAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void fetchChatRooms() {

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
                        JSONArray eventoArraylist = obj.getJSONArray("data");
                        for (int i = 0; i < eventoArraylist.length(); i++) {
                            JSONObject chatRoomsObj = (JSONObject) eventoArraylist.get(i);
                            Eventos cr = new Eventos();
                            cr.setId(chatRoomsObj.getString("_id"));
                            cr.setNombre(chatRoomsObj.getString("nombre"));
                            Log.e(TAG,chatRoomsObj.getString("admin"));
                            cr.setTipo("Admin");
                            if(Integer.parseInt(chatRoomsObj.getString("admin")) == 1){

                                cr.setAdmin(true);
                            }else if(Integer.parseInt(chatRoomsObj.getString("admin"))== 0){
                                cr.setAdmin(false);
                            }


                            eventosArraylist.add(cr);
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "VolleyAdmin error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getContext(), "VolleyAdmin error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }

}
