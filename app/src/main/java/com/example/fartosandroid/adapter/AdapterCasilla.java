package com.example.fartosandroid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fartosandroid.R;
import com.example.fartosandroid.objects.CartaV;
import com.example.fartosandroid.objects.Casilla;

import java.util.List;

public class AdapterCasilla extends RecyclerView.Adapter<AdapterCasilla.MyViewHolder> {
    private List<Casilla> casillaList;

    public AdapterCasilla(List<Casilla> casillaList) {
        this.casillaList = casillaList;
    }

    @NonNull
    @Override
    public AdapterCasilla.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCasilla.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_casillas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCasilla.MyViewHolder holder, int position) {
        holder.bindData(casillaList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return casillaList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView spriteP1;
        ImageView spriteP2;
        TextView txtCasilla;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spriteP1 = itemView.findViewById(R.id.p1);
            spriteP2 = itemView.findViewById(R.id.p2);
            txtCasilla = itemView.findViewById(R.id.casillaN);

        }

        void bindData(final Casilla casilla, int position) {
            position = position+1;
            txtCasilla.setText("Casilla "+ casilla.getNom());
                spriteP1.setImageResource(casilla.getP1());
                spriteP2.setImageResource(casilla.getP2());

        }
    }
}
