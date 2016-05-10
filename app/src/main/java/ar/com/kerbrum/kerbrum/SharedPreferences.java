package ar.com.kerbrum.kerbrum;

import android.content.Context;

/**
 * Created by iAle7 on 21/08/2015.
 */
public class SharedPreferences {

        private final String SHARED_PREFS_FILE = "HMPrefs";
        private final String KEY_NAMEA = "nombreAyudante";
        private final String KEY_NAMEP= "nombrePaciente";
        private final String PRIMER_USO = "primerUso";
        private Boolean pUso = null;

        private Context mContext;


        public SharedPreferences(Context context){
            mContext = context;
        }

        private android.content.SharedPreferences getSettings(){
            return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
        }

        public String getNombreAyudante(){
            return getSettings().getString(KEY_NAMEA, null);
        }

        public void setNombreAyudante(String nombre){
            android.content.SharedPreferences.Editor editor = getSettings().edit();
            editor.putString(KEY_NAMEA, nombre );
            editor.commit();
        }
        public String getNombrePaciente(){
            return getSettings().getString(KEY_NAMEP, null);
        }

        public void setNombrePaciente(String nombre){
            android.content.SharedPreferences.Editor editor = getSettings().edit();
            editor.putString(KEY_NAMEP, nombre );
            editor.commit();
        }

        public boolean primeraVez() {
            if (pUso == null) {
                pUso = getSettings().getBoolean(PRIMER_USO, true);
                if (pUso) {
                    android.content.SharedPreferences.Editor editor = getSettings().edit();
                    editor.putBoolean(PRIMER_USO, false);
                    editor.commit();
                }
            }
            return pUso;
        }




}
