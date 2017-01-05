package prueba.anotaciones;

/**
 * Created by Christian on 18/12/2016.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "anotaciones.db";
    private final Context contexto;

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "ANOTACIONES");
        String CREATE_ANOTACIONES_TABLE = "CREATE TABLE IF NOT EXISTS"
                + " ANOTACIONES" + " ("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Categoria" + " TEXT,"
                + "Nota" + " TEXT,"
                + "Fecha" + " DATETIME );";

        sqLiteDatabase.execSQL(CREATE_ANOTACIONES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if( i1 > i){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "ANOTACIONES");
            onCreate(sqLiteDatabase);
        }
    }


    public long insertarRegistro(SQLiteDatabase db, String categoria, String nota){

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("Nota", nota);
        nuevoRegistro.put("Categoria", categoria);
        nuevoRegistro.put("Fecha", getDateTime());
        return db.insert("ANOTACIONES", null , nuevoRegistro);
    }
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null,SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {

        }
        return checkDB != null;
    }

    public ArrayList<String> obtenerRegistros(SQLiteDatabase db){

        String Categoria, Nota, Fecha;
        String filtroWhereLike = "%";

        String where = "Categoria LIKE ?";
        String[] whereArgs = {filtroWhereLike};

        Cursor c = null;
        c = db.query("ANOTACIONES", null, where, whereArgs, null, null, null);
        ArrayList <String> anotaciones = new ArrayList<>();
        if( c != null && c.moveToFirst() ){
            do {
                Categoria = "Categoría: " + c.getString(1);
                Nota = " Nota: " + c.getString(2) + " ";
                Fecha = "Fecha: " +c.getString(3);
                anotaciones.add(Categoria);
                anotaciones.add(Nota);
                anotaciones.add(Fecha);
            }while( c.moveToNext());
        }
        c.close();
        return anotaciones;
    }

    public void sentencia(SQLiteDatabase db, String sentencia){

        db.execSQL(sentencia);


    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(

                //Formato para poder comprobar que cambia la fecha mediante HH:mm:ss
                //"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



}
