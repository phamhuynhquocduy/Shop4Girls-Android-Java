package com.example.shop4girls.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null ) {
            if (ni.getType() == ConnectivityManager.TYPE_WIFI)
                if (ni.isConnectedOrConnecting())
                    haveConnectedWifi = true;
            if (ni.getType() == ConnectivityManager.TYPE_MOBILE)
                if (ni.isConnectedOrConnecting())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    public  static  void ShowToast_short(Context context,String thongbao){
        Toast.makeText(context,thongbao, Toast.LENGTH_SHORT).show();
    }
}
