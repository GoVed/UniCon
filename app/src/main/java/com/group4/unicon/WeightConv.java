package com.group4.unicon;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class WeightConv extends AppCompatActivity {
    Spinner fromSpin;
    Spinner toSpin;
    EditText inp;
    TextView out;
    TextView othersText;
    ImageView conv;


    double output;
    String[] availableUnits = {"Gram","Kilogram","Tonne","Milligram","US Ton","Imperial Ton","Stone","Pound","Ounce"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_conv);
        setVars();


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,availableUnits);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpin.setAdapter(aa);
        toSpin.setAdapter(aa);

        setOnClickListeners();
        setOnChangedListeners();
    }

    private void setVars(){
        fromSpin = findViewById(R.id.fromSpinner);
        toSpin = findViewById(R.id.toSpinner);
        inp = findViewById(R.id.userInp);
        out = findViewById(R.id.outText);
        othersText = findViewById(R.id.others);
        conv = findViewById(R.id.convImg);
    }

    private void setOnClickListeners(){
        conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp;
                ObjectAnimator animation = ObjectAnimator.ofFloat(conv, "rotation", 0f,180f);
                animation.setDuration(200);
                animation.start();
                temp = fromSpin.getSelectedItemPosition();
                fromSpin.setSelection(toSpin.getSelectedItemPosition());
                toSpin.setSelection(temp);
            }
        });
    }
    private void setOnChangedListeners(){
        inp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                out.setText(""+calc(inp.getText().toString(),fromSpin.getSelectedItemPosition(),toSpin.getSelectedItemPosition()));
                setOthersText();
            }
        });

        fromSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                out.setText(""+calc(inp.getText().toString(),fromSpin.getSelectedItemPosition(),toSpin.getSelectedItemPosition()));
                setOthersText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                out.setText(""+calc(inp.getText().toString(),fromSpin.getSelectedItemPosition(),toSpin.getSelectedItemPosition()));
                setOthersText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private double calc(String inp,int from,int to){
        try{
            output = Double.parseDouble(inp);
        }
        catch (Exception e){
            output = 0;
        }

        if(from==0)
            output *= 1;
        if(from==1)
            output *= 1000;
        if(from==2)
            output *= 1e+6;
        if(from==3)
            output *= 0.001;
        if(from==4)
            output *= 907185;
        if(from==5)
            output *= 1.016e+6;
        if(from==6)
            output *= 6350.29;
        if(from==7)
            output *= 453.592;
        if(from==8)
            output *= 28.3495;

        if(to==0)
            output /= 1;
        if(to==1)
            output /= 1000;
        if(to==2)
            output /= 1e+6;
        if(to==3)
            output /= 0.001;
        if(to==4)
            output /= 907185;
        if(to==5)
            output /= 1.016e+6;
        if(to==6)
            output /= 6350.29;
        if(to==7)
            output /= 453.592;
        if(to==8)
            output /= 28.3495;

        ValueAnimator animator = ObjectAnimator.ofFloat(out, "textSize", 16,17);
        animator.setDuration(100);
        animator = ObjectAnimator.ofFloat(out, "textSize", 17,16);
        animator.setDuration(100);

        animator.start();

        return output;
    }

    private void setOthersText(){
        String outs = "Other conversions:\n\n";
        int i = 0;
        while(i<availableUnits.length){
            if(i!=toSpin.getSelectedItemPosition() && i!=fromSpin.getSelectedItemPosition())
                outs = outs + "to " + availableUnits[i] + " is " + calc(inp.getText().toString(),fromSpin.getSelectedItemPosition(),i) + "\n";
            i++;
        }
        othersText.setText(outs);
    }
}
