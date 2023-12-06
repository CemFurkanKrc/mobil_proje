package com.example.mobil_proje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;


public class RandomActivity extends AppCompatActivity {
    EditText inptmin,inptadet,inptmax;
    LinearLayout prgrsbarrrr;
    Button btncrt;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        inptadet=findViewById(R.id.inpt_adet);
        inptmax=findViewById(R.id.inpt_max);
        inptmin=findViewById(R.id.inpt_min);
        btncrt=findViewById(R.id.btn_crt);
        prgrsbarrrr=findViewById(R.id.hedef_layout);


        btncrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnew();
            }
        });
    }


    public void addnew(){

        String adetstr=inptadet.getText().toString();
        String minstr=inptmin.getText().toString();
        String maxstr=inptmax.getText().toString();
        if (adetstr.isEmpty()){
            inptadet.setText("0");
            return;
        }
        if (minstr.isEmpty()){
            inptmin.setText("0");
            return;
        }
        if (maxstr.isEmpty()){
            inptmax.setText("0");
            return;
        }
        int adetint=Integer.parseInt(adetstr);
        int maxint=Integer.parseInt(maxstr);
        int minint=Integer.parseInt(minstr);

        if (maxint<minint){
            Toast.makeText(this,"Max Minden küçük olamaz",Toast.LENGTH_SHORT).show();
        }else {
            Random rnd =new Random();
            int big=0;
            int small=0;
            double mid=0;
            int mid2=0;
            double yüzde =0;
            int yüzde2= 0;

            for (int i=0;i<adetint;i++){

                int x = rnd.nextInt((maxint-minint+1))+minint;
                int y = rnd.nextInt((maxint-minint+1))+minint;
                if(x>y){
                    big=x;
                    small=y;
                }else {
                    big=y;
                    small=x;
                }
                DecimalFormat df=new DecimalFormat("#");
                mid = rnd.nextInt((big-small+1))+small;
                mid2= Integer.parseInt(df.format(mid));
                yüzde =((mid-small)/(big-small))*100;//   20      30      50
                yüzde2 = Integer.parseInt(df.format(yüzde));

                View prgs = getLayoutInflater().inflate(R.layout.progressbar,null);

                TextView tvbig = prgs.findViewById(R.id.tv_rest_big);
                TextView tvmid = prgs.findViewById(R.id.tv_rest_mid);
                TextView tvsmall = prgs.findViewById(R.id.tv_rest_small);
                ProgressBar bar= prgs.findViewById(R.id.prgs_bar);

                bar.setProgress(Integer.parseInt(df.format(yüzde)));

                tvbig.setText("" + big);
                tvmid.setText("" + mid2+"  --- %"+yüzde2);
                tvsmall.setText("" + small);

                prgrsbarrrr.addView(prgs);
            }
        }
    }
}