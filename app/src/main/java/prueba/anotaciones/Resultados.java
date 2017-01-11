package prueba.anotaciones;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    TextView resultados;
    Button atras, borrar, filtrar;
    BaseDatos db;
    EditText fecha_inicio, fecha_fin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        db = new BaseDatos(this);

        resultados = (TextView)findViewById(R.id.textViewResultados);
        atras = (Button)findViewById(R.id.buttonAtras);

        borrar = (Button)findViewById(R.id.ButtonBorrar);
        filtrar = (Button)findViewById(R.id.buttonFiltrar);

        fecha_inicio = (EditText)findViewById(R.id.editTextFechaInicio);
        fecha_fin = (EditText)findViewById(R.id.editTextFechaFin);




        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha_in, fecha_fi;

                fecha_in = fecha_inicio.getText().toString();
                fecha_fi = fecha_fin.getText().toString();

                if (fecha_fi.isEmpty()){

                    fecha_fi = "31-12-9999";

                }
                if(fecha_in.isEmpty()){

                    fecha_in = "01-01-1900";
                }

                mostrarArticulosFiltrados(fecha_in, fecha_fi);


            }
        });
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

    private void mostrarArticulosFiltrados( String fecha_in, String fecha_fin){


        ArrayList<String> articulos;
        articulos = db.obtenerRegistrosFiltrados(db.getReadableDatabase(), fecha_in, fecha_fin);

        int i = 1;

        resultados.setText("");
        for(String dato : articulos){
            resultados.append(dato + "    ");
            if( i % 3 == 0)
                resultados.append("\n");
            i++;
        }
    }
}
