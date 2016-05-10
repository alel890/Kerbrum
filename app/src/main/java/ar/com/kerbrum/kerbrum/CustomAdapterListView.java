package ar.com.kerbrum.kerbrum;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by iAle7 on 02/10/2015.
 */
public class CustomAdapterListView extends ArrayAdapter<CustomListView>{
    Activity context;

    ArrayList<CustomListView> datos;

    public CustomAdapterListView(Activity context, ArrayList<CustomListView> datos) {
        super(context, R.layout.adaptador_listview_custom,datos);
        this.context= context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.adaptador_listview_custom, null);
        TextView turno,dosis;
        turno = (TextView) item.findViewById(R.id.turno);
        turno.setText(datos.get(position).getTurno());
        dosis = (TextView) item.findViewById(R.id.dosis);
        dosis.setText(datos.get(position).getDosis());
        return item;

    }

}
