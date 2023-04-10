package com.example.fartosandroid;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fartosandroid.LayoutManager.ScaleCenterItemLayoutManager;
import com.example.fartosandroid.adapter.AdapterJugadorBtn;
import com.example.fartosandroid.listener.SelectListenerJugador;
import com.example.fartosandroid.objects.CartaV;
import com.example.fartosandroid.objects.Casilla;
import com.example.fartosandroid.objects.Jugador;

import java.util.ArrayList;
import java.util.List;

public class DialogFragmentCarta extends DialogFragment implements SelectListenerJugador {
    Jugador selectedJugador;
    CartaV cartaV;
    RecyclerView btnRv;
    AdapterJugadorBtn adapterJugadorBtn;
    List<Jugador> jugadors = new ArrayList<>();
    List<Casilla> casillas = new ArrayList<>();
    Jugador user;
    Casilla lastCasilla;
    boolean isUser = false;
    boolean first8 = true;
    boolean broma = false;

    @SuppressLint("MissingInflatedId")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Crear una instancia del AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Obtener la vista del diálogo
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment, null);

        //get Carta
        Bundle bundle = getArguments();
        cartaV = (CartaV) bundle.getSerializable("carta");
        jugadors = (List<Jugador>) bundle.getSerializable("jugadors");
        casillas = (List<Casilla>) bundle.getSerializable("casillas");
        user = (Jugador) bundle.getSerializable("user");


        ImageView imageView = view.findViewById(R.id.cartaInFragment);
        btnRv = view.findViewById(R.id.recylerbtns);
        imageView.setImageResource(cartaV.getSkin());

        init();


        // Configurar la vista del diálogo y devolverla
        builder.setView(view);
        return builder.create();
    }

    public void init(){
        adapterJugadorBtn = new AdapterJugadorBtn(jugadors,this);
        btnRv.setHasFixedSize(true);
        //scaleCenterItemLayoutManager = new ScaleCenterItemLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        btnRv.setLayoutManager(new ScaleCenterItemLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        btnRv.setAdapter(adapterJugadorBtn);
    }

    @Override
    public void onItemClicked(Jugador jugador) {
        selectedJugador = jugador;
        isUser = false;
        int move = 0;
        if (selectedJugador.equals(user)){
            isUser = true;
        }
        Log.d("posUser",""+isUser);
        if (selectedJugador.isPatada() && isUser){
            move = move -1;
            selectedJugador.setPatada(false);
        }

        if (cartaV.getTipus() == 1){
            if (isUser){
                ((MainActivity) getActivity()).move(selectedJugador,move + 1);
            } else {
                ((MainActivity) getActivity()).move(selectedJugador,move - 1);
            }
        }
        if (cartaV.getTipus() == 2){
            if (isUser){
                ((MainActivity) getActivity()).move(selectedJugador,move + 2);
            } else {
                ((MainActivity) getActivity()).move(selectedJugador,move - 2);
            }
        }
        if (cartaV.getTipus() == 3){
            if (isUser){
                ((MainActivity) getActivity()).move(selectedJugador,move + 3);
            } else {
                ((MainActivity) getActivity()).move(selectedJugador,move - 3);
            }
        }
        if (cartaV.getTipus()==4){
            ((MainActivity) getActivity()).teleport(user,selectedJugador);
        }
        if (cartaV.getTipus()==5){
            ((MainActivity) getActivity()).zancadilla(selectedJugador);
        }
        if (cartaV.getTipus()==6){
            ((MainActivity) getActivity()).patada(selectedJugador);
        }
        if (cartaV.getTipus()==7){
            ((MainActivity) getActivity()).hundimiento(selectedJugador);

        }
        if (cartaV.getTipus()==8){
            broma = true;
            ((MainActivity) getActivity()).broma(user,selectedJugador);
        }
        if (selectedJugador.getnCasilla() == 7 && first8){
            first8 = false;
            ((MainActivity) getActivity()).casilla8(user);
        }
        Log.d("pos",""+first8);
        //quitar carta mano
        boolean trobada = false;
        int posTrob = -1;
        int pos = 0;

        for (CartaV c: user.ma) {
            if (c.getTipus()== cartaV.getTipus() && !trobada){
                posTrob = pos;
                trobada = true;
                //user.ma.remove(pos);
            }
            pos = pos +1;
        }

        //game/map behaviour
        //cuando hand.size = 0 -> init baralla() i hand
        //toast que diga la ronda en una variable que se guarda en... casilla
        //cuando ronda == 3 despues de onClick carta eliminar casilla 0 i pjs en esa casilla(primero pj luego casilla)
        lastCasilla = casillas.get(casillas.size()-1);
        if (!(user.ma.size() <= 0) && !broma) {
            user.ma.remove(posTrob);
            broma = false;
        }
        if (lastCasilla.getRonda() >= 3){
            lastCasilla.setRonda(lastCasilla.getRonda()+1);
            ((MainActivity) getActivity()).deleteCasilla();
            init();
        }
        ((MainActivity) getActivity()).checkWin(selectedJugador);
        ((MainActivity) getActivity()).recyclerHand();

        // Cerrar el diálogo
        dismiss();
    }

}
