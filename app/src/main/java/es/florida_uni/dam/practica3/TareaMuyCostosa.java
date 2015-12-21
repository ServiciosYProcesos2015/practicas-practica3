package es.florida_uni.dam.practica3;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TareaMuyCostosa implements Runnable{

    public final static int MSG_TAREA_FINALIZADA = 1;

    final static String TAG = "SYP-practica3";

    volatile boolean interrumpido = false;

    MainActivity actividadPadre;
    Handler handler;

    public TareaMuyCostosa(MainActivity actividadPadre, Handler handler){
        this.actividadPadre = actividadPadre;
        this.handler = handler;
    }

    @Override
    public void run(){
        Log.i(TAG, "Tarea muy costosa iniciada");

        try {
            for(int i=0;i<100;i++){
                if(interrumpido) return;
                Log.i(TAG, "Tarea muy costosa en marcha...");
                mostrarProgreso(i);
                Thread.sleep(50);
            }
            Log.i(TAG, "Tarea muy costosa finalizada");
            notificarFinalizacion();
        }catch(InterruptedException e){
            return;
        }

        Log.i(TAG, "Tarea muy costosa finalizada");
    }

    // TODO: Mostrar el progreso en la barra principal
    // Debéis hacerlo utilizando runOnUIThread o enviando un runnable
    private void mostrarProgreso(final int progreso) {
    }

    // TODO: Avisar de la finalización de la tarea
    // Debéis hacerlo enviando un mensaje a la actividad principal
    // No utilices runnables aquí
    private void notificarFinalizacion(){
    }

    synchronized public void cancel(){
        if(interrumpido) return;
        interrumpido = true;
        Thread.currentThread().interrupt();
    }
}
