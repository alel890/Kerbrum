package ar.com.kerbrum.kerbrum;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Ale on 23/04/2016.
 */
public class NumPicker extends LinearLayout {
    Context context;



    private final long REPEAT_DELAY = 50;

    private double ELEMENT_HEIGHT = 0;
    private  double ELEMENT_WIDTH = 0;

    private int elementIntHeight;
    private int elementIntWidth;

    private final float MINIMUM = (float) 0.25;
    private final float MAXIMUM = 8;

    private final int TEXT_SIZE = 16;

    public static Float value;

    Button decrement;
    Button increment;
    public EditText valueText;

    private Handler repeatUpdateHandler = new Handler();

    private boolean autoIncrement = false;
    private boolean autoDecrement = false;

    /**
     * This little guy handles the auto part of the auto incrementing feature.
     * In doing so it instantiates itself. There has to be a pattern name for
     * that...
     *
     * @author Jeffrey F. Cole
     *
     */
    class RepetetiveUpdater implements Runnable {
        public void run() {
            if( autoIncrement ){
                increment();
                repeatUpdateHandler.postDelayed( new RepetetiveUpdater(), REPEAT_DELAY );
            } else if( autoDecrement ){
                decrement();
                repeatUpdateHandler.postDelayed( new RepetetiveUpdater(), REPEAT_DELAY );
            }
        }
    }

    public NumPicker(Context context, AttributeSet attributeSet ) {
        super(context, attributeSet);
        int altoScreen = getScreenHeight();
        int anchoScreen = getScreenWidth();
        ELEMENT_HEIGHT = altoScreen * 0.1;
        ELEMENT_WIDTH = anchoScreen * 0.2;
        elementIntHeight = (int) Math.round(ELEMENT_HEIGHT);
        elementIntWidth = (int) Math.round(ELEMENT_WIDTH);
        this.setLayoutParams( new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
        LayoutParams elementParams = new LayoutParams( elementIntWidth, elementIntHeight);

        // init the individual elements
        initDecrementButton( context );
        initValueEditText( context );
        initIncrementButton( context );

        // Can be configured to be vertical or horizontal
        // Thanks for the help, LinearLayout!
        if( getOrientation() == VERTICAL ){
            addView( increment, elementParams );
            addView( valueText, elementParams );
            addView( decrement, elementParams );
        } else {
            addView( decrement, elementParams );
            addView( valueText, elementParams );
            addView( increment, elementParams );
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private void initIncrementButton( Context context){
        increment = new Button( context );
        increment.setTextSize( TEXT_SIZE );
        increment.setText( "+" );
        increment.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        increment.setTextColor(getResources().getColor(R.color.blanco));
        // Increment once for a click
        increment.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                increment();
            }
        });

        // Auto increment for a long click
        increment.setOnLongClickListener(
                new OnLongClickListener(){
                    public boolean onLongClick(View arg0) {
                        autoIncrement = true;
                        repeatUpdateHandler.post( new RepetetiveUpdater() );
                        return false;
                    }
                }
        );

        // When the button is released, if we're auto incrementing, stop
        increment.setOnTouchListener( new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if( event.getAction() == MotionEvent.ACTION_UP && autoIncrement ){
                    autoIncrement = false;
                }
                return false;
            }
        });
    }

    private void initValueEditText( Context context){

        value = new Float ( 0 );

        valueText = new EditText( context );
        valueText.setTextSize( TEXT_SIZE );
        //valueText.setTextColor(getResources().getColor(R.color.blanco));

        // Since we're a number that gets affected by the button, we need to be
        // ready to change the numeric value with a simple ++/--, so whenever
        // the value is changed with a keyboard, convert that text value to a
        // number. We can set the text area to only allow numeric input, but
        // even so, a carriage return can get hacked through. To prevent this
        // little quirk from causing a crash, store the value of the internal
        // number before attempting to parse the changed value in the text area
        // so we can revert to that in case the text change causes an invalid
        // number
        valueText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int arg1, KeyEvent event) {
                float backupValue = value;
                try {
                    value =  Float.valueOf( ((EditText)v).getText().toString() );
                } catch( NumberFormatException nfe ){
                    value = backupValue;
                }
                return false;
            }
        });

        // Highlight the number when we get focus
        valueText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if( hasFocus ){
                    ((EditText)v).selectAll();
                }
            }
        });
        valueText.setGravity( Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL );
        valueText.setText( value.toString() );
        valueText.setInputType( InputType.TYPE_CLASS_NUMBER );
    }

    private void initDecrementButton( Context context){
        decrement = new Button( context );
        decrement.setTextSize( TEXT_SIZE );
        decrement.setText( "-" );
        decrement.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        decrement.setTextColor(getResources().getColor(R.color.blanco));


        // Decrement once for a click
        decrement.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                decrement();
            }
        });


        // Auto Decrement for a long click
        decrement.setOnLongClickListener(
                new OnLongClickListener(){
                    public boolean onLongClick(View arg0) {
                        autoDecrement = true;
                        repeatUpdateHandler.post( new RepetetiveUpdater() );
                        return false;
                    }
                }
        );

        // When the button is released, if we're auto decrementing, stop
        decrement.setOnTouchListener( new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if( event.getAction() == MotionEvent.ACTION_UP && autoDecrement ){
                    autoDecrement = false;
                }
                return false;
            }
        });
    }

    public void increment(){
        if( value < MAXIMUM ){
            value =  (float) (value + 0.25);
            valueText.setText( value.toString() );
        }
    }

    public void decrement(){
        if( value > MINIMUM ){
            value = (float) (value - 0.25);
            valueText.setText( value.toString() );
        }
    }

    public static float getValue(){
        return value;
    }

    public void setValue( float val ){
        if( val > MAXIMUM ) val = MAXIMUM;
        if( val >= 0 && val <= 8){
            value = val;
            valueText.setText( value.toString() );
        }
    }

}