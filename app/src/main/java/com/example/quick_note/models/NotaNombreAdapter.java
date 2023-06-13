package com.example.quick_note.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quick_note.R;

import java.util.List;

public class NotaNombreAdapter extends RecyclerView.Adapter<NotaNombreAdapter.NotaViewHolder> {


    private List<Nota> notas;

    public NotaNombreAdapter(List<Nota> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota nota = notas.get(position);
        holder.bind(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class NotaViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreTextView;

        public NotaViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.recyclerview);
        }

        public void bind(Nota nota) {
            nombreTextView.setText(nota.getNombreNota());
        }
    }
}

