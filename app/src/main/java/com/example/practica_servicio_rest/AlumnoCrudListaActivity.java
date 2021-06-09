package com.example.practica_servicio_rest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practica_servicio_rest.adapter.AlumnoAdapter;
import com.example.practica_servicio_rest.api.ServiceAlumnoapi;
import com.example.practica_servicio_rest.entity.Alumno;
import com.example.practica_servicio_rest.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumnoCrudListaActivity extends AppCompatActivity {

    List<Alumno> lstData = new ArrayList<Alumno>();
    AlumnoAdapter adaptador = null;
    ListView lstView = null;
    ServiceAlumnoapi api = null;

    Button btnAgregar;
    Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_crud_lista);

        btnAgregar = findViewById(R.id.btnCrudAlumnoIngresar);
        btnListar = findViewById(R.id.btnCrudAlumnoListar);

        lstView = findViewById(R.id.idCrudAlumnoListView);
        adaptador = new AlumnoAdapter(this, R.layout.activity_alumno_crud_item, lstData);
        lstView.setAdapter(adaptador);

        api = ConnectionRest.getConnection().create(ServiceAlumnoapi.class);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el listado");
                lista();
            }
        });


    }

    public void lista(){
        mensaje("LOG -> Inicio método lista");
        Call<List<Alumno>> call =  api.listaAlumno();
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if (response.isSuccessful()){
                    List<Alumno> lista =   response.body();
                    mensaje("LOG ->  Cantidad Recibida : " + lista.size());
                    lstData.clear();
                    lstData.addAll(lista);
                    adaptador.notifyDataSetChanged();
                }else{
                    mensaje("ERROR -> Error en la respuesta");
                }
            }
            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                mensaje("ERROR -> Error en la respuesta");
            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idMenuCrudAutor) {
            Intent intent = new Intent(this, AlumnoCrudListaActivity.class);
            startActivity(intent);



            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}