package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;

public class Loading {
    private Activity activity;
    private AlertDialog alertDialog;
    private Handler handler;

    Loading(Activity activity) {
        this.activity = activity;
        this.handler = new Handler();
    }

    private static int ANIMATION_TIMEOUT = 3100;
    void startLoading() {
        Builder builder = new Builder(this.activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = this.activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(false);

        this.alertDialog = builder.create();
        this.alertDialog.show();
        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopLoading();
            }
        }, ANIMATION_TIMEOUT);
    }

    private void stopLoading() {
        this.alertDialog.dismiss();
    }

}