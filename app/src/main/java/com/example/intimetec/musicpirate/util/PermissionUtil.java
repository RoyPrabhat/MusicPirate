package com.example.intimetec.musicpirate.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by intimetec on 10/25/2016.
 */

public class PermissionUtil {

    // Assume thisActivity is the current activity
    public static boolean isReadExternalStoragePermissionPresent(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;
        else return false;





    }
}
