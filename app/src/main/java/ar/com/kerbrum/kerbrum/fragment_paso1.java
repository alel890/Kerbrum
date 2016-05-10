package ar.com.kerbrum.kerbrum;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import ivb.com.materialstepper.stepperFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_paso1 extends stepperFragment  {
    int[] sampleImages = {R.drawable.forma1, R.drawable.forma2, R.drawable.forma3, R.drawable.forma4};
    ArrayList<ImageView> images;
    CarouselView carouselView;
    public String colorFiltroImagen;
    Context context;

    RadioButton color1,color2,color3,color4,color5,color6,rb_g,rb_mg;
    //EditText et_nombreMed,et_gramaje;
    RadioGroup radiog, botonesColores;
    private View v;
    public fragment_paso1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        v = inflater.inflate(R.layout.fragment_paso1, container, false);
        /*botones-colores*/

        context = getActivity();
        images = new ArrayList<ImageView>();
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        radiog = (RadioGroup)v.findViewById(R.id.radiogrup);
        botonesColores = (RadioGroup)v.findViewById(R.id.botonesSRColores);
        color1 = (RadioButton)v.findViewById(R.id.btn_color1);
        color2 = (RadioButton)v.findViewById(R.id.btn_color2);
        color3 = (RadioButton)v.findViewById(R.id.btn_color3);
        color4 = (RadioButton)v.findViewById(R.id.btn_color4);
        color5 = (RadioButton)v.findViewById(R.id.btn_color5);
        color6 = (RadioButton)v.findViewById(R.id.btn_color6);
        // Check if no view has focus:

        rb_g = (RadioButton)v.findViewById(R.id.rb_g);
        rb_mg = (RadioButton)v.findViewById(R.id.rb_mg);


        RadioGroup.OnCheckedChangeListener checkedchangelistener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_color1:
                        //color = color chequeado;
                        colorFiltroImagen= "#FFFFFF";
                        setColor(colorFiltroImagen);
                        break;
                    case R.id.btn_color2:
                        colorFiltroImagen= "#D85050"; //rojo
                        setColor(colorFiltroImagen);
                        break;

                    case R.id.btn_color3:
                        colorFiltroImagen= "#EDE964"; //amarillo
                        setColor(colorFiltroImagen);
                        break;

                    case R.id.btn_color4:
                        colorFiltroImagen= "#15D18D"; //verde
                        setColor(colorFiltroImagen);

                        break;

                    case R.id.btn_color5:
                        colorFiltroImagen= "#4059A8"; //azul
                        setColor(colorFiltroImagen);

                        break;

                    case R.id.btn_color6:
                        colorFiltroImagen= "#DB8ACC"; //azul
                        setColor(colorFiltroImagen);

                        break;

                }
            }
        };
        botonesColores.setOnCheckedChangeListener(checkedchangelistener);


        return v;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(final int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
            imageView.setColorFilter(Color.parseColor("#FFFFFF"));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            images.add(imageView);

        }

    };

    public void setColor(String color) {
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setColorFilter(Color.parseColor(color));
        }
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
    //mg g y tiene que setearse con un listener xq desde fragments no se pueden usar onCLick desde xml
    @Override
    public boolean onNextButtonHandler() {
        return true;
    }

}
