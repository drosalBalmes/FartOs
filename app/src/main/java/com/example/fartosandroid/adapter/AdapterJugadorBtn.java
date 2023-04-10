package com.example.fartosandroid.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fartosandroid.R;
import com.example.fartosandroid.listener.SelectListenerJugador;
import com.example.fartosandroid.objects.Casilla;
import com.example.fartosandroid.objects.Jugador;

import java.util.List;

public class AdapterJugadorBtn extends RecyclerView.Adapter<AdapterJugadorBtn.MyViewHolder> {
    private List<Jugador> playerList;
    SelectListenerJugador listenerJugador;

    public AdapterJugadorBtn(List<Jugador> playerList, SelectListenerJugador listenerJugador) {
        this.playerList = playerList;
        this.listenerJugador = listenerJugador;
    }

    @NonNull
    @Override
    public AdapterJugadorBtn.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterJugadorBtn.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_buton,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJugadorBtn.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(playerList.get(position),position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerJugador.onItemClicked(playerList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView btn;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            btn = itemView.findViewById(R.id.playerButton);
            cardView = itemView.findViewById(R.id.cardViewBtn);
        }

        void bindData(final Jugador jugador, int position){
            btn.setImageResource(jugador.getIcon());
        }
    }
}
