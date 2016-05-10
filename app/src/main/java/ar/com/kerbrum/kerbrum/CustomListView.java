package ar.com.kerbrum.kerbrum;

import android.widget.Button;

/**
 * Created by iAle7 on 02/10/2015.
 */
public class CustomListView {
    private String turno;
    private String dosis;

    public CustomListView(String turno, String dosis){
        this.turno = turno;
        this.dosis = dosis;
    }

    public String getTurno(){
        return turno;
    }

    public String getDosis(){
        return dosis;
    }

    public void setDosis(String dosis){
        this.dosis = dosis;
    }
    public void setTurno(String turno){
        this.turno = turno;
    }
}
