package ar.com.kerbrum.kerbrum;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import ivb.com.materialstepper.stepperFragment;



public class fragment_paso2 extends stepperFragment implements View.OnClickListener{
    View v;
    private EditText et_Remaining, et_PastillasConsumidas, et_Cajas;
    Button btn_fechainicio;
    Activity activity;
    Spinner spin_duracionIngesta;
    ToggleButton lunes, martes, miercoles, jueves, viernes, sabado, domingo;
    TextView hora, dosis;
    public fragment_paso2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* ===== action bar ======= */
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(R.mipmap.ic_launcherdrawer2);
        setHasOptionsMenu(true);
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflator.inflate(R.layout.actionbar_et, null);
        actionBar.setCustomView(vi);
        actionBar.show();
        /* ===== action bar ======= */
        v = inflater.inflate(R.layout.fragment_paso2, container, false);
        //TODO poder seleccionar todos los días tipo con un checkbox
        lunes = (ToggleButton) v.findViewById(R.id.lunes);
        martes = (ToggleButton) v.findViewById(R.id.martes);
        miercoles = (ToggleButton) v.findViewById(R.id.miercoles);
        jueves = (ToggleButton) v.findViewById(R.id.jueves);
        viernes = (ToggleButton) v.findViewById(R.id.viernes);
        sabado = (ToggleButton) v.findViewById(R.id.sabado);
        domingo = (ToggleButton) v.findViewById(R.id.domingo);
        spin_duracionIngesta = (Spinner) v.findViewById(R.id.spin_duracion);
        et_Remaining = (EditText)v.findViewById(R.id.etRemaining);
        et_PastillasConsumidas = (EditText)v.findViewById(R.id.etPastillasConsumidas);
        et_Cajas = (EditText)v.findViewById(R.id.etCajas);
        btn_fechainicio = (Button)v.findViewById(R.id.btn_setFechaInicio);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.duracionIngesta, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);


        spin_duracionIngesta.setAdapter(adapter2);

        et_Remaining.setText("30");
        et_Cajas.setText("2");
        et_PastillasConsumidas.setText("15");

        CompoundButton.OnCheckedChangeListener touchListener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String estado;
                switch (buttonView.getId()) {
                    case R.id.lunes:
                        break;
                    case R.id.martes:
                        break;
                    case R.id.miercoles:
                        break;
                    case R.id.jueves:
                        break;
                    case R.id.viernes:
                        break;
                    case R.id.sabado:
                        break;
                    case R.id.domingo:
                        break;
                }
            }


        };

        lunes.setOnCheckedChangeListener(touchListener);
        martes.setOnCheckedChangeListener(touchListener);
        miercoles.setOnCheckedChangeListener(touchListener);
        jueves.setOnCheckedChangeListener(touchListener);
        miercoles.setOnCheckedChangeListener(touchListener);
        jueves.setOnCheckedChangeListener(touchListener);
        viernes.setOnCheckedChangeListener(touchListener);
        sabado.setOnCheckedChangeListener(touchListener);
        domingo.setOnCheckedChangeListener(touchListener);



        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.ab_primeruso, menu);

        super.getActivity().onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.menu.ab_primeruso:
                Toast t = Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT);
                t.show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    @Override
    public boolean onNextButtonHandler() {
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setFechaInicio:
                break;
        }
    }
}
