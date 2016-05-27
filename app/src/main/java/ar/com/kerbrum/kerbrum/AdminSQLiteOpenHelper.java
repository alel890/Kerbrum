package ar.com.kerbrum.kerbrum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.Iterator;


/**
 * Created by Marcos on 10/05/2016.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static String name = "baseMedicamentos";
    private static int version = 1;
    private static CursorFactory cursorFactory = null;
    protected static String tableMedicamentos = "medicamento";
    protected static String tableDosis = "dosisDiaHora";

    //Medicamento (id autoincremental, nombre, color, forma, unidad, duracion, inicio, turno, stock)

    private String SQLtableMeds = "CREATE TABLE " + tableMedicamentos +
            " (id_med INTEGER PRIMARY KEY , " +
            "nombre VARCHAR(100) , " +
            "color INTEGER, " +
            "forma INTEGER," +
            "unidad TEXT," +
            "duracion INTEGER," +
            "inicio TEXT," +
            "turno INTEGER, " +
            "stock INTEGER," +
            "FOREIGN KEY (id_med) REFERENCES medicamento (id_med)) ";

    private String SQLtableDosis = "CREATE TABLE " + tableDosis +
            " (id_dosis INTEGER PRIMARY KEY," +
            "dia VARCHAR(60) , " +
            "hora VARCHAR(10) ," +
            "dosis REAL ) " ;

    public AdminSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLtableMeds);
        db.execSQL(SQLtableDosis);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarMedicamento(Medicamento m){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            //TABLA MEDS
            ContentValues datosMed = new ContentValues();
            datosMed.put("nombre", m.getNombre());
            datosMed.put("color", m.getColor());
            datosMed.put("forma", m.getForma());
            datosMed.put("unidad", m.getUnidad());
            datosMed.put("duracion", m.getDuracion());
            datosMed.put("inicio", m.getInicio());
            datosMed.put("turno", m.getTurno());
            datosMed.put("stock", m.getStock());
            db.insert(tableMedicamentos, null, datosMed);

            Iterator<String> nombreIterator = m.getDiaArray().iterator();
            while (nombreIterator.hasNext()) {
                String dia = nombreIterator.next();
                System.out.println(dia);
                try {

                    //TABLA DOSIS
                    ContentValues datosDosis = new ContentValues();
                    datosDosis.put("dia", dia);
                    datosDosis.put("hora", m.getHora());
                    datosDosis.put("dosis", m.getDosis());
                //TODO check this
                    db.insertWithOnConflict(tableDosis, null, datosDosis,SQLiteDatabase.CONFLICT_IGNORE);
                    //  db.insertOrThrow(tableDosis, null, datosDosis);

                }catch(Exception e){
                    System.out.println("EXCEPTION LANZADA");
                }

            }
            db.close();
        }
    }
    public Cursor consultarMeds(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT rowid _id, nombre "+
                " FROM "+tableMedicamentos, null);
    }


    public Cursor consultarCasillero(String dia, int turno){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT rowid _id, nombre, hora, dosis,turno,forma,color"+
                " FROM "+tableMedicamentos + " WHERE dia LIKE " +"'%" + dia + "%' AND turno =" + turno  , null);
    }


    public void eliminarMed(long id){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM " + tableMedicamentos + " WHERE rowid =" + id );
            db.close();
        }
    }


}
