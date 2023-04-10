package com.example.fartosandroid;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fartosandroid.LayoutManager.ScaleCenterItemLayoutManager;
import com.example.fartosandroid.adapter.AdapterCartaV;
import com.example.fartosandroid.adapter.AdapterCasilla;
import com.example.fartosandroid.listener.SelectListenerCarta;
import com.example.fartosandroid.objects.CartaV;
import com.example.fartosandroid.objects.Casilla;
import com.example.fartosandroid.objects.Jugador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListenerCarta {
    CartaV selectedCarta;
    Casilla lastCasilla;
    Drawable trans;
    List<CartaV> baralla = new ArrayList<>();
    List<CartaV> hand = new ArrayList<>();
    List<Casilla> casillas = new ArrayList<>();
    List<Jugador> jugadors = new ArrayList<>();
    AdapterCartaV adapterCartaV;
    AdapterCasilla adapterCasilla;
    RecyclerView cartaRV;
    RecyclerView casillaRV;
    ScaleCenterItemLayoutManager linearLayoutManager;
    TextView testText;
    Jugador user;
    int tranInt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartaRV = findViewById(R.id.cartasRv);
        trans = ContextCompat.getDrawable(this,R.drawable.trans);
        tranInt =R.drawable.trans;
        initJugadors();
        user = jugadors.get(0);
        initMap();
        initBaralla();
        hand();

    }

    public void initJugadors(){
        jugadors.add(new Jugador("Goku",R.drawable.sprite_goku,R.drawable.icon_goku));
        jugadors.add(new Jugador("Lucario",R.drawable.sprite_lucario,R.drawable.icon_lucario));
        jugadors.add(new Jugador("Luffy",R.drawable.sprite_luffy,R.drawable.icon_luffy));
    }

    public void hand(){
        for (Jugador j: jugadors) {
            if (jugadors.size() >= 5){
                for (int i = 0; i < 5; i++) {
                    j.ma.add(baralla.get(0));
                    baralla.remove(0);
                }
            } else {
                for (int i = 0; i < 6; i++) {
                    j.ma.add(baralla.get(0));
                    baralla.remove(0);
                }
            }
        }
        recyclerHand();
    }

    public void recyclerHand(){
        hand = user.ma;
        if (hand.isEmpty()){
            cartaRV.setVisibility(View.GONE);
            Toast.makeText(this, "No te quedan Cartas", Toast.LENGTH_SHORT).show();
            initBaralla();
            hand();
            Toast.makeText(this, "Finalizada ronda "+lastCasilla.getRonda(), Toast.LENGTH_SHORT).show();
            if (lastCasilla.getRonda() < 3) {
                lastCasilla.setRonda(lastCasilla.getRonda() + 1);
            }

        } else {
            cartaRV.setVisibility(View.VISIBLE);
            adapterCartaV = new AdapterCartaV(hand, this);
            linearLayoutManager = new ScaleCenterItemLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            cartaRV.setHasFixedSize(true);
            cartaRV.setLayoutManager(linearLayoutManager);
            cartaRV.setAdapter(adapterCartaV);
        }
    }

    public void recyclerCasillas(){
        adapterCasilla = new AdapterCasilla(casillas);
        casillaRV.setHasFixedSize(true);
        casillaRV.setLayoutManager(new LinearLayoutManager(this));
        casillaRV.setAdapter(adapterCasilla);
    }

    public void initBaralla(){
        baralla = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            baralla.add(new CartaV("Moure 1",R.drawable.moure_1,1));
        }
        for (int i = 0; i < 18; i++) {
            baralla.add(new CartaV("Moure2",R.drawable.moure_2,2));
        }
        for (int i = 0; i < 10; i++) {
            baralla.add(new CartaV("Moure3",R.drawable.moure_3,3));
        }
        for (int i = 0; i < 3; i++) {
            baralla.add(new CartaV("Teleport",R.drawable.teleport,4));
        }
        for (int i = 0; i < 4; i++) {
            baralla.add(new CartaV("Zancadilla",R.drawable.zancadilla,5));
        }
        for (int i = 0; i < 3; i++) {
            baralla.add(new CartaV("Patada",R.drawable.patada,6));
        }
        for (int i = 0; i < 2; i++) {
            baralla.add(new CartaV("Hundimiento",R.drawable.hundimiento,7));
        }
        for (int i = 0; i < 2; i++) {
            baralla.add(new CartaV("Broma",R.drawable.broma,8));
        }
        Collections.shuffle(baralla);
    }


    public void initMap(){
        casillaRV = findViewById(R.id.casillasRv);
        casillas.add(new Casilla("1",1,tranInt,tranInt));
        casillas.add(new Casilla("2",2,tranInt,tranInt));
        casillas.add(new Casilla("3",3,tranInt,tranInt));
        casillas.add(new Casilla("4",4,tranInt,tranInt));
        casillas.add(new Casilla("5",5,tranInt,tranInt));
        casillas.add(new Casilla("6",6,tranInt,tranInt));
        casillas.add(new Casilla("7",7,tranInt,tranInt));
        casillas.add(new Casilla("8",8,tranInt,tranInt));
        casillas.add(new Casilla("9",9,tranInt,tranInt));
        casillas.add(new Casilla("10",10,tranInt,tranInt));
        casillas.add(new Casilla("11",11,tranInt,tranInt));
        casillas.add(new Casilla("12",12,tranInt,tranInt));
        casillas.add(new Casilla("13",13,tranInt,tranInt));
        casillas.add(new Casilla("14",14,tranInt,tranInt));
        casillas.add(new Casilla("15",15,tranInt,tranInt));
        lastCasilla =casillas.get(casillas.size()-1);
        recyclerCasillas();

        move(jugadors.get(0),0);
        move(jugadors.get(1),1);
        move(jugadors.get(2),4);

    }
    public void move(Jugador jugador,int move){

        int nCasilla1 = Math.max(jugador.getnCasilla(), 0);
        int nCasilla2 = nCasilla1+move;
        if (nCasilla2 <= 0){
            nCasilla2 = 0;
        }
        if (nCasilla2 >= casillas.size()-1){
            nCasilla2 = casillas.size()-1;
        }
        jugador.setnCasilla(nCasilla2);

        if (casillas.get(nCasilla2).getP1() == tranInt){
            if (jugador.isP()){
                Log.d("trans","P1,P1 "+ nCasilla1 + jugador.getNom());
                casillas.get(nCasilla1).setP1(tranInt);
            } else {
                Log.d("trans","P1,P2 "+nCasilla1 + jugador.getNom());
                casillas.get(nCasilla1).setP2(tranInt);
            }
            Log.d("trans","set P1 sprite");
            casillas.get(nCasilla2).setP1(jugador.getSprite());
            Log.d("trans","set P1 sprite " + nCasilla2 + jugador.getNom());
            jugador.setP(true);
        } else if (casillas.get(nCasilla2).getP2() == tranInt){
            if (jugador.isP()){
                Log.d("trans","P2,P1");
                casillas.get(nCasilla1).setP1(tranInt);
            } else {
                Log.d("trans","P2,P2");
                casillas.get(nCasilla1).setP2(tranInt);
            }
            Log.d("trans","set P2 sprite");
            casillas.get(nCasilla2).setP2(jugador.getSprite());
            jugador.setP(false);
        } else {
            //Toast.makeText(this, "Ya hay 2 jugadores en esa casilla!", Toast.LENGTH_LONG).show();
        }
        casillaRV.setAdapter(adapterCasilla);
    }

    public void teleport(Jugador user, Jugador selectedJugador){
        int casillaUser = user.getnCasilla();
        boolean pUser = user.isP();
        int casillaSelectedJugador = selectedJugador.getnCasilla();
        boolean pSelectedJugador = selectedJugador.isP();
        user.setnCasilla(casillaSelectedJugador);
        user.setP(pSelectedJugador);
        selectedJugador.setnCasilla(casillaUser);
        selectedJugador.setP(pUser);

        if (user.isP()){
            casillas.get(user.getnCasilla()).setP1(user.getSprite());
        } else {
            casillas.get(user.getnCasilla()).setP2(user.getSprite());
        }
        if (selectedJugador.isP()){
            casillas.get(selectedJugador.getnCasilla()).setP1(tranInt);
            casillas.get(selectedJugador.getnCasilla()).setP1(selectedJugador.getSprite());
        } else {
            casillas.get(selectedJugador.getnCasilla()).setP2(selectedJugador.getSprite());
        }
        recyclerCasillas();
    }

    public void zancadilla(Jugador jugador){
        jugador.ma.remove((int)(Math.random() * jugador.ma.size()));
    }
    public void patada(Jugador jugador){
        jugador.setPatada(true);
    }
    public void hundimiento(Jugador jugador){
        int move = jugador.getnCasilla();
        move(jugador,-move);
    }

    public void broma(Jugador user, Jugador selectedJugador){
        int posTrob = -1;
        boolean trobada = false;
        int pos = 0;
        for (CartaV c: user.ma) {
            if (c.getTipus()== 8 && !trobada){
                posTrob = pos;
                trobada = true;
                //user.ma.remove(pos);
            }
            pos = pos +1;
        }
        if (!(user.ma.size() <= 0)) {
            user.ma.remove(posTrob);
        }
        List<CartaV> handUser = user.ma;
        List<CartaV> handSelectedJugador = selectedJugador.ma;
        user.setMa(handSelectedJugador);
        selectedJugador.setMa(handUser);

        recyclerHand();
    }

    public void casilla8(Jugador user){
        C8DialogFragment c8DialogFragment = new C8DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        bundle.putSerializable("jugadors", (Serializable) jugadors);
        c8DialogFragment.setArguments(bundle);
        c8DialogFragment.show(getSupportFragmentManager(),"Casilla 8 Fragment");
    }

    public void deleteCasilla(){
        jugadors.removeIf(jugador -> jugador.getnCasilla() == 0);
        for (Jugador j: jugadors) {
            j.setnCasilla(j.getnCasilla()-1);
        }
        casillas.remove(0);

    }
    public void checkWin(Jugador jugador){
        if (jugador.getnCasilla() == casillas.size()-1){
            Toast.makeText(this, "Ganador " + jugador.getNom() + "!", Toast.LENGTH_SHORT).show();
        }
        if (jugadors.size()==1){
            Toast.makeText(this, "Ganador " + jugadors.get(0).getNom() + "!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClicked(CartaV cartaV) {
        selectedCarta = cartaV;
        Jugador user = jugadors.get(0);
        DialogFragmentCarta dialogFragmentCarta = new DialogFragmentCarta();
        Bundle bundle = new Bundle();
        Log.d("click","click");
        bundle.putSerializable("carta",selectedCarta);
        bundle.putSerializable("jugadors", (Serializable) jugadors);
        bundle.putSerializable("user",user);
        bundle.putSerializable("casillas", (Serializable) casillas);
        dialogFragmentCarta.setArguments(bundle);
        dialogFragmentCarta.show(getSupportFragmentManager(),"Carta Fragment");
        Log.d("click", "despues del show");
    }



}