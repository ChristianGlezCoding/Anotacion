package prueba.anotaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button buttonAnotar, buttonVerAnotaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonAnotar = (Button)findViewById(R.id.buttonAnotar);
        buttonVerAnotaciones = (Button)findViewById(R.id.buttonVerAnotaciones);



        buttonVerAnotaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent res = new Intent(getApplicationContext(), Resultados.class);
                startActivity(res);
            }
        });

        buttonAnotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent res = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(res);
            }
        });


    }
}
