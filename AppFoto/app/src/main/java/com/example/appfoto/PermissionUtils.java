package com.example.appfoto;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_DENIED;


public class PermissionUtils {

    public static boolean validate (Activity activity, int requestCode, String ... permissions){
            List<String> list = new ArrayList<String>();
            for (String permission : permissions){
                boolean ok = ContextCompat.checkSelfPermission(activity, permission)
                        == PackageManager.PERMISSION_GRANTED;
                if (! ok ){
                    list.add(permission);
                }

            }
            if (list.isEmpty()){
                return true;
            }

            String[] newPermissions = new String[list.size()];
            list.toArray(newPermissions);
            ActivityCompat.requestPermissions(activity, newPermissions, 1);
            return false;


        }

}


