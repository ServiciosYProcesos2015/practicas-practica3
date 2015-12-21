package es.florida_uni.dam.practica3;


import android.util.Log;

public class TareaMuyCostosa implements Runnable{

    final static String TAG = "SYP-practica3";

    volatile boolean interrumpido = false;

    @Override
    public void run(){
        Log.i(TAG, "Tarea muy costosa iniciada");

        try {
            for(int i=0;i<100;i++){
                if(interrumpido) return;
                Log.i(TAG, "Tarea muy costosa en marcha...");
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            return;
        }

        Log.i(TAG, "Tarea muy costosa finalizada");
    }

    synchronized public void cancel(){
        if(interrumpido) return;
        interrumpido = true;
        Thread.currentThread().interrupt();
    }
}
