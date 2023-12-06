package com.example.mobil_proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {

EditText inpttel,inptmsj;
Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        inptmsj=findViewById(R.id.inpt_msj);
        inpttel=findViewById(R.id.inpt_tel);
        btnsend=findViewById(R.id.btn_send);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(SmsActivity.this,android.Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else {
                    ActivityCompat.requestPermissions(SmsActivity.this,new String[]{android.Manifest.permission.SEND_SMS},100);
                }
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(requestCode==100 &&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else {
            Toast.makeText(this,"İzinler Reddedildi",Toast.LENGTH_SHORT).show();
        }
    }
    private void sendSMS(){
        String tel= inpttel.getText().toString();
        String msj= inptmsj.getText().toString();
        if(!tel.isEmpty()&&!msj.isEmpty()){
            SmsManager smsmngr=SmsManager.getDefault();
            smsmngr.sendTextMessage(tel,null,msj,null,null);
            Toast.makeText(this,"SMS Gönderildi",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"SMS Gönderilemedi",Toast.LENGTH_SHORT).show();
        }
    }
}