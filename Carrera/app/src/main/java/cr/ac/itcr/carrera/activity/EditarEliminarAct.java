package cr.ac.itcr.carrera.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cr.ac.itcr.carrera.R;

public class EditarEliminarAct extends AppCompatActivity {

    String description, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView etTipoE= (TextView)findViewById(R.id.spTEvenEditar);
        TextView etNombreE= (TextView)findViewById(R.id.etNombreEvenEditar);
        TextView etDetallesE= (TextView)findViewById(R.id.etDetallesEvenEditar);

        Button btnEditar = (Button)findViewById(R.id.btnEditarEvenEditar);
        Button btnEliminar = (Button)findViewById(R.id.btnEliminarEvenEditar);



        Intent intent = getIntent();
        description = intent.getStringExtra("detalle");
        title = intent.getStringExtra("name");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombreE.setText(title);
        etDetallesE.setText(description);
        etTipoE.setText("Arreglar");


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });



    }

}
