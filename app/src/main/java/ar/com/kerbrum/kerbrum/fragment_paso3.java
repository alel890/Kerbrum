package ar.com.kerbrum.kerbrum;


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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import ivb.com.materialstepper.stepperFragment;


public class fragment_paso3 extends stepperFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private View v;

    Button agregarNuevaDosisyHora;
    ListView listview;
    ArrayList<CustomListView> args;
    CustomAdapterListView adap;
    int indexOfarray;
    Boolean dosisyhoraSeteada;
    int posicionItemdeLista;

    Spinner spin_periodoIngesta;

    public fragment_paso3() {
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
        v = inflater.inflate(R.layout.fragment_paso3, container, false);
        indexOfarray = -1;
        listview = (ListView) v.findViewById(R.id.listview);
        spin_periodoIngesta = (Spinner) v.findViewById(R.id.spinnerDosisaldia);
        agregarNuevaDosisyHora = (Button) v.findViewById(R.id.agregarItem);
        agregarNuevaDosisyHora.setOnClickListener(this);
        dosisyhoraSeteada = false;



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.periodoingesta, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);


        spin_periodoIngesta.setAdapter(adapter2);

        //spin_periodoIngesta.setOnItemSelectedListener(getActivity().getContext);

        args = new ArrayList<CustomListView>();
        adap = new CustomAdapterListView(getActivity(), args);
        listview.setAdapter(adap);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //for(int i = 0; i-1<position; i++){
                posicionItemdeLista = position;
                System.out.println(posicionItemdeLista + " position en el onItem");
                dosisyhoraSeteada = true;
                crearAlertDialog(args, adap, dosisyhoraSeteada);
                System.out.println("dosisyhoraseteada es" + dosisyhoraSeteada);

                //}
                /*switch (position){
                    case 0:
                        setDosisyHora(args,adap,position);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }*/

            }
        });

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
    public void crearAlertDialog(final ArrayList<CustomListView> args, final CustomAdapterListView adap, final boolean dosisyhoraSeteada) {
        //final boolean issetdosisyhora = dosisyhoraSeteada;
        LayoutInflater lin = LayoutInflater.from(getActivity());
        View dosisYhora = lin.inflate(R.layout.dosis_y_hora, null);
        AlertDialog.Builder alertDialogB = new AlertDialog.Builder(getActivity());
        alertDialogB.setView(dosisYhora);
        String[] separated ;
        final TimePicker timePick = (TimePicker) dosisYhora.findViewById(R.id.pickHora);
        //final NumberPicker numPick = (NumberPicker) dosisYhora.findViewById(R.id.pickDosis);
        //numPick.setMinValue(1);
        //numPick.setMaxValue(20);
        //numPick.setWrapSelectorWheel(false);
        //numPick.setValue(1);
        if (dosisyhoraSeteada) {
            final String currentDosis = args.get(posicionItemdeLista).getDosis();
            final float currdosis = Float.parseFloat(currentDosis);
            final String currentTurno = args.get(posicionItemdeLista).getTurno();
            separated = currentTurno.split(":");
            timePick.setCurrentHour(Integer.parseInt(separated[0]));
            timePick.setCurrentMinute(Integer.parseInt(separated[1]));
            // TODO aca debería pasarle los valores que tenian seteados del turno y la dosis.
            //ar.com.kerbrum.kerbrum.NumPicker.setValue(currdosis);

        }
        alertDialogB
                .setCancelable(true)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alertDialogDosisHora = alertDialogB.create();
        alertDialogDosisHora.show();
        alertDialogDosisHora.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float floatNumPick = ar.com.kerbrum.kerbrum.NumPicker.getValue();
                final String strDosis = Float.toString(floatNumPick);
                final String strTurno = timePick.getCurrentHour() + ":" + timePick.getCurrentMinute();

                if (strDosis == "0.0") {
                    Toast error = Toast.makeText(getActivity(), "Tenes que agregar por le menos 0.25 de dosis", Toast.LENGTH_SHORT);
                    error.show();

                } else {
                    if (dosisyhoraSeteada) {

                        //Pedirle al objeto custom la hroa y dosis la dibujas a partir de aca

                        modificarDosisYhora(args, adap, posicionItemdeLista, strDosis, strTurno);
                        adap.notifyDataSetChanged();
                        Toast toastModificacion = Toast.makeText(getActivity(), "Modificaste la configuración de este medicamento para tomar " + strDosis + " dosis a las: " + strTurno, Toast.LENGTH_LONG);
                        toastModificacion.show();
                        alertDialogDosisHora.dismiss();


                    } else {
                        setDosisyHora(args, adap, strDosis, strTurno);
                        //setDosisYHora(); en este metodo tnees que crear el nuevo objeto customlist view y agregarlo al array /add
                        adap.notifyDataSetChanged();
                        Toast toastSeteoDosis = Toast.makeText(getActivity(), "Configuraste este medicamento para tomar " + strDosis + " dosis a las: " + strTurno, Toast.LENGTH_LONG);
                        toastSeteoDosis.show();
                        alertDialogDosisHora.dismiss();

                    }
                }
            }
        });

    }

    public void setDosisyHora(final ArrayList<CustomListView> args, final CustomAdapterListView adap, final String dosis, final String turno) {
        //TODO QUE NO SE PUEDA INGRESAR DOS MISMOS HORARIOS

        args.add(new CustomListView(turno, dosis));
        for (int i = 0; i < args.size(); i++) {
            System.out.print(args.get(i));
            //System.out.println(Arrays.toString(args.get(i)));
        }
        if(adap.getCount() > 3){
            View item = adap.getView(0, null, listview);
            item.measure(0, 0);
            ViewGroup.LayoutParams lp = listview.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = (int) (4 * item.getMeasuredHeight());
            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (4 * item.getMeasuredHeight()));
            //listview.setLayoutParams(params);
            listview.requestLayout();
        }

    }

    public void modificarDosisYhora(final ArrayList<CustomListView> args, final CustomAdapterListView adap, final int position, final String dosis, final String turno) {
        final int posicion = position;
        args.get(posicion).setTurno(turno);
        args.get(posicion).setDosis(dosis);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agregarItem:

                indexOfarray++;
                dosisyhoraSeteada = false;
                crearAlertDialog(args, adap, dosisyhoraSeteada);

                break;
            /*case R.id.btn_cancelar2:
                volver(v);
                break;
            case R.id.btn_irapaso3:

                //final float unidad = ar.com.kerbrum.kerbrum.NumberPicker.getValue();
                continuar(v);
                break;*/
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
