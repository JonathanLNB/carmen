package com.example.jonalnb.reproductor;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;

/**
 * Created by JonaLNB on 17/03/2017.
 */

public class Hilo extends AsyncTask<Void, Integer, Void> {
    ProgressBar pAvance;
    Inicio in;
    public Hilo (ProgressBar pAvance, Inicio in){
        this.pAvance = pAvance;
        this.in=in;
    }
    protected Void doInBackground(Void... params) {
        try {
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(1000);
                publishProgress(20*i);
            }
        }
        catch (Exception e){
            Toast.makeText(null,e.getMessage(),Toast.LENGTH_SHORT);
        }
        return null;
    }
    protected void onPreExecute() {
        super.onPreExecute();
    }
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pAvance.setProgress(values[0]);
    }
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent intent = new Intent(in,Reproductor.class);
        in.startActivity(intent);
        in.finish();
    }
}
