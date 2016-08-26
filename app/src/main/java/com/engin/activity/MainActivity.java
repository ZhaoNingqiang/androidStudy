package com.engin.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.engin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoningqiang on 16/6/18.
 */

public class MainActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 127;
    private static final String TAG = "permission";

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }


    private boolean requestPermissions(Object obj, int requestCode, String... permissions) {

        //check没有授权的权限
        List<String> denyPermissions = new ArrayList<String>();

        Context context = obj instanceof Activity ? (Context) obj : ((Fragment) obj).getActivity();

        for (String permission : permissions) {

            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            {
                denyPermissions.add(permission);
            }
        }

        if (denyPermissions.size() > 0){
            if (obj instanceof  Activity){
                ActivityCompat.requestPermissions((Activity)obj,denyPermissions.toArray(new String[denyPermissions.size()]),requestCode);

            }else if (obj instanceof  Fragment){
                ((Fragment)obj).requestPermissions(denyPermissions.toArray(new String[denyPermissions.size()]),requestCode);
            }else{
                throw new RuntimeException("argument"+obj+" is not a fragment or activity");
            }
            return  false;
        }else{
            return true;
        }
    }
    public boolean isAllPermissionGrant(int[] grantResults){
        if (grantResults.length > 0){
            for (int grantResult : grantResults){

                if (grantResult != PackageManager.PERMISSION_GRANTED){
                    return false;
                }

            }
            return true;

        }else {
            return false;
        }


    }



    @Override
    public void setListener() {
        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{ Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//
//                    // Should we show an explanation?
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                            Manifest.permission.READ_CONTACTS)) {
//                        Log.d(TAG, "shouldShowRequestPermissionRationale true");
//
//                        // Show an expanation to the user *asynchronously* -- don't block
//                        // this thread waiting for the user's response! After the user
//                        // sees the explanation, try again to request the permission.
//
//                    } else {
//
//                        Log.d(TAG, "shouldShowRequestPermissionRationale false");
//                        // No explanation needed, we can request the permission.
//
//                        ActivityCompat.requestPermissions(MainActivity.this,
//                                new String[]{Manifest.permission.READ_CONTACTS},
//                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                        // app-defined int constant. The callback method gets the
//                        // result of the request.
//                    }
//
//                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG, "onRequestPermissionsResult requestCode = " + requestCode+" permissions size = "+permissions.length );
        for (String permission : permissions) {
            Log.d(TAG, "onRequestPermissionsResult permission = " + permission);
        }
        for (int grantResult : grantResults) {
            Log.d(TAG, "onRequestPermissionsResult permission = " + grantResult);
        }


        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult permission was granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.d(TAG, "onRequestPermissionsResult permission was denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
