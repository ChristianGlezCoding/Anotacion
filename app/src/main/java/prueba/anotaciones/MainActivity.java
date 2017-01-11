package prueba.anotaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {

    Button guardar, ver;
    EditText nota;
    TextView resultado;
    BaseDatos db;
    RadioButton otros, tarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardar = (Button)findViewById(R.id.buttonGuargar);
        ver = (Button)findViewById(R.id.buttonVerNotas);

        otros = (RadioButton)findViewById(R.id.radioButtonOtros);
        tarea = (RadioButton)findViewById(R.id.radioButtonTareas);





        nota = (EditText)findViewById(R.id.editTextNota);
        resultado = (TextView)findViewById(R.id.textViewResultados);
        db = new BaseDatos(this);
        db.onCreate(db.getReadableDatabase());

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.onCreate(db.getReadableDatabase());
                Intent res = new Intent(getApplicationContext(), Resultados.class);
                startActivity(res);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    db.onCreate(db.getReadableDatabase());
                    resultado.setText("");
                    String categoria = "";

                    if(otros.isChecked()){
                        categoria = "Otros";
                    } else if(tarea.isChecked()) {
                        categoria = "Tarea";
                    }

                    //String para comprobar el cambio de tiempo mediante minutos y segundos
                    //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    String currentTimeString = DateFormat.getDateInstance().format(new Date());
                    db.insertarRegistro(db.getWritableDatabase(), categoria ,nota.getText().toString());
                    Toast.makeText(getApplicationContext(), "Datos introducidos correctamente", Toast.LENGTH_SHORT).show();
                   resultado.setText("Categoría: " + categoria +
                                      "\nNota: "+ nota.getText() +
                                      "\nFecha: " + currentTimeString +
                                      "\nAñadidos");
                }catch (Exception x){
                    Toast.makeText(getApplicationContext(), "Error al añadir datos", Toast.LENGTH_SHORT).show();
                }

            }




        });


    }

}
