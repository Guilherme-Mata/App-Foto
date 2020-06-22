package com.example.appfoto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.pm.PackageManager.*;


class MainActivityMainActivity extends AppCompatActivity {
    ImageView img;
//    Button bttirafoto;
    String[] permissoes =  new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,

    };
    Boolean permissaook=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.validate(MainActivityMainActivity.this, 0, permissoes);
        if (permissaook == true){
            retiraFoto();
        }

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults){
            if (result == PERMISSION_DENIED){
                Toast.makeText(getApplicationContext(), "PERMISSAO NEGADA", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                permissaook= true;
                Toast.makeText(getApplicationContext(), "PERMISSÃO CONCEDIDA", Toast.LENGTH_LONG).show();
            }
        }
    }

        private  void  retiraFoto(){
        img = (ImageView)findViewById(R.id.imagem);
        Button bttirafoto = (Button)findViewById(R.id.botao);
        bttirafoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
             }
            });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        if (data != null){
            Bitmap bitmap = (Bitmap) bundle.get("data");
            img.setImageBitmap(bitmap);
        }
    }

}



