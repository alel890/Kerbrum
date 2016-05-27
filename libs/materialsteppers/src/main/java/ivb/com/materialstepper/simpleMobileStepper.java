package ivb.com.materialstepper;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;


public abstract class simpleMobileStepper extends AppCompatActivity implements View.OnClickListener {

    private Button mPrevious, mNext;
    private TextView mStepText,mStepText2,mStepText3;
    List<Class> mStepperFragmentList;
    private baseStepper mBaseStepper;
    private int RECOVERCURRENTSTATE = 0;
    private ScrollView mScroll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mobile_stepper);
        //if(getSupportActionBar()!=null){getSupportActionBar().hide();}
        mNext = (Button) findViewById(R.id.continuar);
        mPrevious = (Button)findViewById(R.id.atras);
        mStepText = (TextView)findViewById(R.id.steps1);
        mStepText2 = (TextView)findViewById(R.id.steps2);
        mStepText3 = (TextView)findViewById(R.id.steps3);
        mStepText.setText(R.string.paso1);
        mStepText2.setText(R.string.paso2);
        mStepText3.setText(R.string.paso3);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mScroll = (ScrollView)findViewById(R.id.mobilescroll);

        mNext.setOnClickListener(this);
        mPrevious.setOnClickListener(this);
        if(savedInstanceState!=null) {
            if(savedInstanceState.getSerializable("HSTEPPERBASE")!=null) {
                try {
                    mStepperFragmentList = (List<Class>) savedInstanceState.getSerializable("HSTEPPERBASE");
                    RECOVERCURRENTSTATE = savedInstanceState.getInt("HCURRENT");
                }catch(Exception e){
                    //it's  okay we will recover from the init method
                    mStepperFragmentList = init();
                }
            }
            else{
                mStepperFragmentList = init();
            }
        }
        else
        {
            mStepperFragmentList = init();
        }
        mBaseStepper = new baseStepper(mViewPager, mStepperFragmentList, getSupportFragmentManager());
        mBaseStepper.CURRENT_PAGE = RECOVERCURRENTSTATE;
        RECOVERCURRENTSTATE = 0;
        BackButtonConfig();
        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("HSTEPPERBASE",(Serializable)mStepperFragmentList);
        outState.putInt("HCURRENT",mBaseStepper.CURRENT_PAGE);
        super.onSaveInstanceState(outState);

    }

    protected void BackButtonConfig(){
       // if(mBaseStepper.CURRENT_PAGE==0)
       //     mPrevious.setVisibility(View.INVISIBLE);
       // else
            mPrevious.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.continuar) {
            if (checkStepper()) {
                mBaseStepper.onNextButtonClicked();
                BackButtonConfig();
                updateUI();
            }

        } else if (i == R.id.atras) {
            if(mBaseStepper.CURRENT_PAGE == 0){
                finish();
            }else {
                mBaseStepper.onBackButtonClicked();
                BackButtonConfig();
                updateUI();
            }
        }
    }
    public int getCurrentFragmentId(){
        return mBaseStepper.CURRENT_PAGE;
    }

    public boolean checkStepper(){
        if(mBaseStepper.resolveNavigation()){
            return true;
        }
        onStepperCompleted();
        return  false;

    }
    public void updateUI(){
//        mStepText.setText("Paso " + (mBaseStepper.CURRENT_PAGE + 1) + " de " + mBaseStepper.TOTAL_PAGE);

        switch(mBaseStepper.CURRENT_PAGE){
          case 0:
              mStepText.setTextColor(Color.parseColor("#212121"));
              mStepText2.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText3.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.active1,0,0,0);
              mStepText2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.inactive2,0,0,0);
              mStepText3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.inactive3,0,0,0);
              mNext.setText("CONTINUAR");

              break;
          case 1:
              mStepText.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText3.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText2.setTextColor(Color.parseColor("#212121"));
              mStepText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.success,0,0,0);
              mStepText2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.active2,0,0,0);
              mStepText3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.inactive3,0,0,0);
              mNext.setText("CONTINUAR");

              break;
          case 2:
              mStepText.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText2.setTextColor(Color.parseColor("#B6B6B6"));
              mStepText3.setTextColor(Color.parseColor("#212121"));
              mStepText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.success,0,0,0);
              mStepText2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.success,0,0,0);
              mStepText3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.active3,0,0,0);
              mNext.setText("FINALIZAR");
              break;

      }

        mScroll.pageScroll(View.FOCUS_UP);
    }
    public abstract void onStepperCompleted();
    public abstract List<Class> init();

}
