package ar.com.kerbrum.kerbrum;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ivb.com.materialstepper.simpleMobileStepper;

public class Steppers extends simpleMobileStepper {
<<<<<<< HEAD
    Medicamento med;
=======
    //private Menu menu;
    //EditText et_nombreMed;
>>>>>>> origin/master
    List<Class> stepperFragmentList = new ArrayList<>();
    View vi;


    @Override
    public void onStepperCompleted() {
        showCompletedDialog();
    }

    protected void showCompletedDialog(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                Steppers.this);

        alertDialogBuilder.setTitle("Messi + 10");
        alertDialogBuilder
                .setMessage("Aguante Messi")
                .setCancelable(true)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });


        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }


    @Override
    public List<Class> init() {

        stepperFragmentList.add(fragment_paso1.class);
        stepperFragmentList.add(fragment_paso2.class);
        stepperFragmentList.add(fragment_paso3.class);
        return stepperFragmentList;
    }
}
