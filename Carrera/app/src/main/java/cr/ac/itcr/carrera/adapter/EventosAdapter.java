package cr.ac.itcr.carrera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


import cr.ac.itcr.carrera.R;
import cr.ac.itcr.carrera.activity.Eventos;
import cr.ac.itcr.carrera.model.ChatRoom;

/**
 * Created by usuario on 24/5/2016.
 */
public class EventosAdapter extends BaseAdapter {
    private String TAG = EventosAdapter.class.getSimpleName();
    LayoutInflater minflater;
    ArrayList<ChatRoom> lista;

    public EventosAdapter(Context context, ArrayList<ChatRoom> lista) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=minflater.inflate(R.layout.fragment_lvleventos,null);

            final TextView typeevento = (TextView)convertView.findViewById(R.id.typeEventos);
            final TextView nameEvento = (TextView)convertView.findViewById(R.id.nameCarrera);

            typeevento.setText(lista.get(position).getTipoevento());
            nameEvento.setText(lista.get(position).getName());


        }

        return convertView;
    }
}
