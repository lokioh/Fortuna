package fr.iut.appmob.fortuna.init;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;

import fr.iut.appmob.fortuna.R;

public class Loading {
    private Activity activity;
    private AlertDialog alertDialog;
    private Handler handler;

    // give the activity in which the loading screen should be load
    public Loading(Activity activity) {
        this.activity = activity;
        this.handler = new Handler();
    }

    private static int ANIMATION_TIMEOUT = 3100;
    // Launch the loading screen
    public void startLoading() {
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
                new Authentication(activity).start();
            }
        }, ANIMATION_TIMEOUT);
    }

    // Stop the loading screen
    private void stopLoading() {
        this.alertDialog.dismiss();
    }

}