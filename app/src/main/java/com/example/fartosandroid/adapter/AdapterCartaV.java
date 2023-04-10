package com.example.fartosandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fartosandroid.R;
import com.example.fartosandroid.listener.SelectListenerCarta;
import com.example.fartosandroid.objects.Carta;
import com.example.fartosandroid.objects.CartaV;

import java.util.List;

public class AdapterCartaV extends RecyclerView.Adapter<AdapterCartaV.MyViewHolder> {

    private List<CartaV>cartaVList;
    private SelectListenerCarta listenerCarta;

    public AdapterCartaV(List<CartaV> cartaVList, SelectListenerCarta listenerCarta){
        this.cartaVList = cartaVList;
        this.listenerCarta = listenerCarta;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(cartaVList.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerCarta.onItemClicked(cartaVList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartaVList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView cartaImg;
        TextView nomCarta;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            cartaImg = itemView.findViewById(R.id.carta);
            cardView = itemView.findViewById(R.id.cv_card);

        }

        void bindData(final CartaV cartaV){
            cartaImg.setImageResource(cartaV.getSkin());
            //nomCarta.setText(cartaV.getNom());
        }
    }
}
