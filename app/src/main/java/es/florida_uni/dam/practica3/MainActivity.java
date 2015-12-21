package es.florida_uni.dam.practica3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "SYP-practica3";

    TareaMuyCostosa tareaMuyCostosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instalarManejadores();
    }

    private void instalarManejadores(){
        Button btnTareaCostosa = (Button) findViewById(R.id.btnTareaCostosa);
        Button btnSaludar = (Button) findViewById(R.id.btnSaludar);
        Button btnDetener = (Button) findViewById(R.id.btnDetener);

        // Manejador del botón de saludar
        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "Saludando");
                Toast.makeText(
                        MainActivity.this, "Hola", Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Manejador del botón que ejecuta la tarea costosa
        btnTareaCostosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "Ejecutando tarea muy costosa");
                ejecutarTareaMuyCostosa();
            }
        });


        // Manejador del botón que detiene la tarea costosa
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "Deteniendo tarea muy costosa");
                detenerTareaMuyCostosa();
            }
        });
    }

    // Lanzamiento de la tarea muy costosa
    private void ejecutarTareaMuyCostosa(){
        if(tareaMuyCostosaEjecutandose()) {
            Log.i(TAG, "La tarea muy costosa ya se estaba ejecutando");
            return;
        }

        tareaMuyCostosa = new TareaMuyCostosa();
        new Thread(tareaMuyCostosa).start();
    }

    // Detención de la tarea muy costosa
    private void detenerTareaMuyCostosa(){
        if(!tareaMuyCostosaEjecutandose()) {
            Log.i(TAG, "La tarea muy costosa no se estaba ejecutando, no se puede detener");
            return;
        }

        tareaMuyCostosa.cancel();
        tareaMuyCostosa = null;
    }

    private boolean tareaMuyCostosaEjecutandose(){
        return tareaMuyCostosa != null;
    }

}
