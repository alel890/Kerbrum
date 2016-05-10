package ar.com.kerbrum.kerbrum;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by iAle7 on 21/08/2015.
 */
public class SegundoUso extends AppCompatActivity{

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction ftransaction;
    private FragmentManager fmanager;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //la mayoria de imports que se usan son los de support.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo_uso);

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);


        //Lo correspondiente para mostrar el list view
        navList = (ListView)findViewById(R.id.navList);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], R.mipmap.inicio));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], R.mipmap.estado));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], R.mipmap.valija));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], R.mipmap.sync));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], R.mipmap.config));
        //navList.setOnItemClickListener(this);// esto es para que escuche los eventos de la lista.

        navMenuIcons.recycle();
        /*ArrayList<String> navArray = new ArrayList<String>();//aca voy a guardar las opciones del menu.
        navArray.add("Inicio");
        navArray.add("Estado Actual");
        navArray.add("Sincronizar");
        navArray.add("Valija");
        navArray.add("Configuracion");*/
        navList.setOnItemClickListener(new SlideMenuClickListener());

        adaptador = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,navArray);
        navList.setAdapter(adapter);*/
        navList.setAdapter(adaptador);
        //Fragments

        fmanager = getSupportFragmentManager();
        ftransaction = fmanager.beginTransaction();


        //este es el listener del drawerLayout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abrirDrawer,R.string.cerrarDrawer);//los ultimos dos parametros son para accesibilidad.
        //drawerLayout.setDrawerListener(actionBarDrawerToggle);//deprecated
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //Action Bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);//controla si mostrar o no el logo de la actividad
        //hace el icono y el titulo clickeable. Solo lo hace clickeable, no funcional. Pero sirve para darle desp la funcionalidad de "up" (o sea volver atras). Tambien agrega la flechita.
        //para hacerla volver hay que especificar el nombre de la actividad padre: android:parentActivityName en la actividad en el manifest.
        actionBar.setDisplayHomeAsUpEnabled(true);


        //abrirSeleccion(0);
    }

    private void abrirSeleccion(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new Inicio();

                break;
            case 1:
                fragment = new EstadoActual();

                break;
            case 2:
                fragment = new Valija();
                break;
            case 3:

                fragment = new Sincronizar();
                break;
            case 4:
                fragment = new Configuracion();
                break;
        }
        if (fragment != null) {
            navList.setItemChecked(i, true);
            navList.setSelection(i);
            ftransaction = fmanager.beginTransaction();
            ftransaction.replace(R.id.fragmentHolder, fragment);
            ftransaction.commit();
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();// con esto se sincroniza el icono del hamburguesa dependiendo de si esta abierto o no. Esta en el onPostCreate xq ssucede desp de la primera creacion
        System.out.println("ASDASDADSADSDASASDSDAASDSADASDASDDDDDDDDDD");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id== android.R.id.home){
            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }else{
                drawerLayout.openDrawer(navList);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            abrirSeleccion(position);
            drawerLayout.closeDrawer(navList);
        }
    }

    /*marcote @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {// maneja los elementos de la list cuando son clickeados
        System.out.println("POSICION NUMERO: " +  position);
        abrirSeleccion(position);
        drawerLayout.closeDrawer(navList);// siempre lo cierro despues de que un eleemento es seleccionado
    }*/

}
