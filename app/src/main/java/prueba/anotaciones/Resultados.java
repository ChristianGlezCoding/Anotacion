package prueba.anotaciones;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    TextView resultados;
    Button atras, filtrar, borrar;
    BaseDatos db;
    MainActivity main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        db = new BaseDatos(this);

        resultados = (TextView)findViewById(R.id.textViewResultados);
        atras = (Button)findViewById(R.id.buttonAtras);
        filtrar = (Button)findViewById(R.id.buttonFiltrar);
        borrar = (Button)findViewById(R.id.ButtonBorrar);




        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        borrar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentenciaBorrar = "DROP TABLE IF EXISTS ANOTACIONES";
                resultados.setText("");
                db.sentencia(db.getReadableDatabase(),sentenciaBorrar);
                Toast.makeText(getApplicationContext(), "Se ha borrado la BDD", Toast.LENGTH_SHORT).show();
                finish();
            }
        }));

        mostrarArticulos(resultados);
    }

    private void mostrarArticulos(TextView r){


        ArrayList<String> articulos;
        articulos = db.obtenerRegistros(db.getReadableDatabase());

        int i = 1;

        r.setText("");
        for(String dato : articulos){
            r.append(dato + "    ");
            if( i % 3 == 0)
                r.append("\n");
            i++;
        }
    }
}
