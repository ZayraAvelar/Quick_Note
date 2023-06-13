package com.example.quick_note.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quick_note.Agregar;
import com.example.quick_note.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


public class NotaListAdapter extends RecyclerView.Adapter<NotaListAdapter.NotaViewHolder> {
    private final LayoutInflater mInflater; //mInflater permite mostrar la lista de elementos en la vista
    private List<Nota> listaNota; //obtiene la lista de elementos de la base de datos
    private Context context; //almacena el contexto de la actividad

    private static final int REQUEST_CODE = 1;

    //constructor de la clase
    public NotaListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setNotas(List<Nota> Notas) {
        listaNota = Notas;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public NotaListAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NotaListAdapter.NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota nota = listaNota.get(position);

        String notaTitulo = nota.getNombreNota();
        holder.notaItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("¿Que deseas hacer?")
                        .setItems(new String[]{"ver", "Eliminar", "Cancelar"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones según la opción seleccionada
                                switch (which) {
                                    case 0:

                                        Intent intent = new Intent(view.getContext(), Agregar.class);

                                        intent.putExtra("idNota", nota.getIdNota());
                                        intent.putExtra("idCat", nota.getCatNota());
                                        intent.putExtra("tNota", nota.getNombreNota());
                                        intent.putExtra("descNota", nota.getDescNota());

                                        view.getContext().startActivity(intent);
                                        break;
                                    case 1:
                                        NotaDao notaDao = new NotaDao(context).eliminarNota(nota);
                                        listaNota.remove(position);
                                        notifyDataSetChanged();
                                        break;
                                    case 2:
                                        Toast.makeText(context, "Cancelando operaciones", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        holder.notaItemView.setText(notaTitulo);
    }

    @Override
    public int getItemCount() {
        if (listaNota != null) {
            return listaNota.size();
        } else {
            return 0;
        }
    }

    //declaracion de la clase interna ViewHolder
    class NotaViewHolder extends RecyclerView.ViewHolder {
        private final TextView notaItemView;

        public NotaViewHolder(@NotNull View itemView) {
            super(itemView);
            notaItemView = itemView.findViewById(R.id.nombre_nota);
        }
    }//fin de la clase interna
}
