package ar.com.kerbrum.kerbrum;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ivb.com.materialstepper.simpleMobileStepper;

public class Steppers extends simpleMobileStepper {
    Medicamento med;
    List<Class> stepperFragmentList = new ArrayList<>();
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
