package ar.com.kerbrum.kerbrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_nombrePaciente;
    EditText et_nombreAyudante;
    Button btn_empe;
    String nombrePaciente, nombreAcompañante;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //la mayoria de imports que se usan son los de support.
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nombrePaciente = (EditText)findViewById(R.id.et_nombrePaciente);
        et_nombreAyudante = (EditText)findViewById(R.id.et_nombreAyudante);
        btn_empe = (Button)findViewById(R.id.btn_empezar);
        btn_empe.setOnClickListener(this);


/*
        config = new SharedPreferences(this);
        if (config.primeraVez()) {
            setContentView(R.layout.activity_main);
           // btn_aPaso2 = (Button)findViewById(R.id.btn_irapaso2);
            //btn_aPaso2.setOnClickListener(this);
        }
        else {
            Intent act2=new Intent("ar.com.kerbrum.kerbrum.SegundoUso");
            startActivity(act2);
            finish();
        }
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        nombrePaciente = et_nombrePaciente.getText().toString();
        nombreAcompañante = et_nombreAyudante.getText().toString();
       /* if(nombrePaciente.equals("")||nombreAcompañante.equals("")&& v.getId()== R.id.btn_empezar){
            Toast t = Toast.makeText(this,"Falta completar un campo", Toast.LENGTH_SHORT);
            t.show();
        }else{*/
        if(v.getId()== R.id.btn_empezar) {
            //config.setNombrePaciente(nombrePaciente);
           // config.setNombreAyudante(nombreAcompañante);
            Intent i = new Intent("ar.com.kerbrum.kerbrum.Steppers");
            startActivity(i);
        }
     //   }
    }
}
