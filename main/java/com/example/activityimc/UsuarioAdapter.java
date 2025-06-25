package com.example.activityimc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.nombreTextView.setText(usuario.getNombre());
        holder.primerApellidoTextView.setText(usuario.getPrimerApellido());
        holder.segundoApellidoTextView.setText(usuario.getSegundoApellido());
        holder.direccionTextView.setText(usuario.getDireccion());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView primerApellidoTextView;
        TextView segundoApellidoTextView;
        TextView direccionTextView;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            primerApellidoTextView = itemView.findViewById(R.id.primerApellidoTextView);
            segundoApellidoTextView = itemView.findViewById(R.id.segundoApellidoTextView);
            direccionTextView = itemView.findViewById(R.id.direccionTextView);
        }
    }
}

