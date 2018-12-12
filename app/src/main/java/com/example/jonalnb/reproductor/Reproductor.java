package com.example.jonalnb.reproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Reproductor extends AppCompatActivity {
    @BindView(R.id.iImagen) ImageView iLogo;
    @BindView(R.id.iImagen2) ImageView iLogo2;
    @BindView(R.id.vInfo)
    TextView vInfo;
    private int[] audios = {R.raw.somethingaboutus, R.raw.cmon, R.raw.savetheworld, R.raw.coldwater, R.raw.nrg };
    private int[] imagenesB = {R.mipmap.bfrancia, R.mipmap.bholanda, R.mipmap.bsuecia, R.mipmap.bjamaica, R.mipmap.bestadosunidos};
    private int[] imagenesM = {R.mipmap.francia, R.mipmap.holanda, R.mipmap.suecia, R.mipmap.jamaica, R.mipmap.estadosunidos};
    private String[] info = {"Info","Info","Info","Info","Info"};
    private MediaPlayer reproductor;
    private int cont=0;
    private int tiempo=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        ButterKnife.bind(this);
        iLogo.setImageResource(imagenesB[cont]);
        iLogo2.setImageResource(imagenesM[cont]);
        reproductor = MediaPlayer.create(this, audios[cont]);
        reproductor.seekTo(0);
        reproductor.start();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CONT",cont);
        outState.putInt("TIEMPO",reproductor.getCurrentPosition());
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cont = savedInstanceState.getInt("CONT");
        tiempo = savedInstanceState.getInt("TIEMPO");
    }

    protected void onResume() {
        super.onResume();
        if(reproductor!=null)
            reproductor.seekTo(tiempo);
    }
    protected void onPause() {
        super.onPause();
        reproductor.pause();
    }
    @OnClick(R.id.bAnterior)
    public void setAnterior(){
        cont--;
        if(cont<0)
            cont=audios.length-1;
        if(reproductor!=null)
            reproductor.release();
        reproductor = MediaPlayer.create(this, audios[cont]);
        iLogo.setImageResource(imagenesM[cont]);
        iLogo2.setImageResource(imagenesB[cont]);
        vInfo.setText(info[cont]);
        reproductor.seekTo(0);
        reproductor.start();
    }
    @OnClick(R.id.bSiguiente)
    public void setSiguiente(){
        cont++;
        if (cont== audios.length)
            cont=0;
        if(reproductor!=null)
            reproductor.release();
        reproductor = MediaPlayer.create(this, audios[cont]);
        iLogo.setImageResource(imagenesM[cont]);
        iLogo2.setImageResource(imagenesB[cont]);
        vInfo.setText(info[cont]);
        reproductor.seekTo(0);
        reproductor.start();
    }
}
