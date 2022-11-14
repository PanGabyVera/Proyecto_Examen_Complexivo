package com.example.proyecto_examen_complexivo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_examen_complexivo.R;
import com.example.proyecto_examen_complexivo.modelo.Producto;
import com.example.proyecto_examen_complexivo.modelo.Subcategoria;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> listproducto;
    private Context context;

    public ProductoAdapter(List<Producto> listproducto, Context context) {
        this.listproducto = listproducto;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_productos,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        Producto pro=listproducto.get(position);
        holder.nombreproducto.setText(pro.getNombre());
        holder.precioproducto.setText("$ "+pro.getPrecio());
        Picasso.get().load(pro.getFoto()).resize(300,300).centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listproducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nombreproducto,precioproducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreproducto=itemView.findViewById(R.id.nombreproducto);
            imageView=itemView.findViewById(R.id.imagenproducto);
            precioproducto=itemView.findViewById(R.id.precioproducto);
        }
    }
}
