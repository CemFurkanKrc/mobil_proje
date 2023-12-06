package com.example.mobil_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConverterActivity extends AppCompatActivity {

TextView tvrestdec,tvrestmbyt,tvrestcel;
Spinner spnrdec,spnrmbyt;
EditText inptdec,inptmbyt,inptcel;
ArrayAdapter<CharSequence> adapterdec,adapterbyt;
RadioGroup radioGroup;
RadioButton rdofahr,rdoklvn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        inptdec=findViewById(R.id.inpt_dec);
        inptmbyt=findViewById(R.id.inpt_mbyt);
        inptcel=findViewById(R.id.inpt_cel);
        tvrestdec=findViewById(R.id.tv_rest_dec);
        tvrestmbyt=findViewById(R.id.tv_rest_mbyt);
        tvrestcel=findViewById(R.id.tv_rest_cel);
        spnrdec=findViewById(R.id.spnr_dec);
        spnrmbyt=findViewById(R.id.spnr_mbyt);
        radioGroup=findViewById(R.id.rdo_grp);
        rdofahr=findViewById(R.id.rdo_fahr);
        rdoklvn=findViewById(R.id.rdo_klvn);

        adapterdec=ArrayAdapter.createFromResource(this,R.array.decimalitems, android.R.layout.simple_spinner_item);
        adapterdec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrdec.setAdapter(adapterdec);
        adapterbyt=ArrayAdapter.createFromResource(this,R.array.byteitems, android.R.layout.simple_spinner_item);
        adapterbyt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrmbyt.setAdapter(adapterbyt);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> convertTemperature());

        inptcel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertTemperature();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        inptmbyt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertByt();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        inptdec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertDec();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        spnrmbyt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertByt();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnrdec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertDec();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void  convertTemperature(){
        String celinput=inptcel.getText().toString();
        if(celinput.isEmpty()){
            tvrestcel.setText("0");
            return;
        }
        double celvalue=Double.parseDouble(celinput);
        double result;
        if(rdofahr.isChecked()){
            result=celtofah(celvalue);
            tvrestcel.setText(String.valueOf(result));
        }else if(rdoklvn.isChecked()){
            result=celtoklv(celvalue);
            tvrestcel.setText(String.valueOf(result));
        }
    }
    private double celtofah(double cel){
        return (cel*9/5)+32;
    }
    private  double celtoklv(double cel){
        return cel+273.15;
    }
    private  void convertByt(){
        String mbyteinput=inptmbyt.getText().toString();
        if (mbyteinput.isEmpty()){
            tvrestmbyt.setText("0");
            return;
        }
        int mbytevalue=Integer.parseInt(mbyteinput);
        int KiloByte=mbytevalue*1024;
        int Byte = mbytevalue*1048576;
        int KibiByte=mbytevalue*976;
        int Bit = mbytevalue*8000000;
        String selecteditem=spnrmbyt.getSelectedItem().toString();
        switch (selecteditem){
            case "Kilo Byte":
                tvrestmbyt.setText(Integer.toString(KiloByte));
                break;
            case "Byte":
                tvrestmbyt.setText(Integer.toString(Byte));
                break;
            case "Kibi Byte":
                tvrestmbyt.setText(Integer.toString(KibiByte));
                break;
            case "Bit":
                tvrestmbyt.setText(Integer.toString(Bit));
                break;
            default:
                tvrestmbyt.setText(Integer.toString(KiloByte));
                break;
        }
    }
    private  void convertDec(){
        String decimalinput=inptdec.getText().toString();
        if(decimalinput.isEmpty()){
            tvrestdec.setText("0");
            return;
        }
        int decimalvalue=Integer.parseInt(decimalinput);
        String selecteditem=spnrdec.getSelectedItem().toString();
        switch (selecteditem){
            case "Binary:":
                tvrestdec.setText(Integer.toBinaryString(decimalvalue));
                break;
            case "Octal":
                tvrestdec.setText(Integer.toOctalString(decimalvalue));
                break;
            case "Hexadecimal":
                tvrestdec.setText((Integer.toHexString(decimalvalue)).toUpperCase());
                break;
            default:
                tvrestdec.setText(Integer.toBinaryString(decimalvalue));
                break;
        }
    }
}