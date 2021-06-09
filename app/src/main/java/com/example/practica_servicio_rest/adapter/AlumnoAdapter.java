package com.example.practica_servicio_rest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.practica_servicio_rest.R;
import com.example.practica_servicio_rest.entity.Alumno;
import java.util.List;

public class AlumnoAdapter extends ArrayAdapter<Alumno> {

    private Context context;
    private List<Alumno> lista;

    public AlumnoAdapter(@NonNull Context context, int resource, @NonNull List<Alumno> lista) {
        super(context, resource,  lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_alumno_crud_item, parent, false);

        Alumno obj = lista.get(position);

        TextView txtId = row.findViewById(R.id.idItemCrudAlumnoId);
        txtId.setText(String.valueOf(obj.getIdAlumno()));

        TextView txtNombre = row.findViewById(R.id.idItemCrudAlumnoNombre);
        txtNombre.setText(obj.getNombres());

        TextView txtApellido = row.findViewById(R.id.idItemCrudAlumnoApellido);
        txtNombre.setText(obj.getApellidos());

        TextView txtdni = row.findViewById(R.id.idItemCrudAlumnoDNI);
        txtNombre.setText(obj.getDni());

        TextView txtcorreo = row.findViewById(R.id.idItemCrudAlumnoCorreo);
        txtNombre.setText(obj.getCorreo());

        TextView txtfechaN = row.findViewById(R.id.idItemCrudAlumnoFechaN);
        txtNombre.setText(obj.getFechaNacimiento());

        TextView txtfechaR = row.findViewById(R.id.idItemCrudAlumnoRegistro);
        txtNombre.setText(obj.getFechaRegistro());

        return row;


    }

}
