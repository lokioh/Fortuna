package fr.iut.appmob.fortuna.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.data.DataManagement;

public class ProfilePictureActivity extends AppCompatActivity {
    private static int icon = R.drawable.ic_man;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        findViewById(R.id.imageWoman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.womanImgShadow).setAlpha(0);
                findViewById(R.id.manImgShadow).setAlpha(0.6f);

                icon = R.drawable.ic_woman;
            }
        });

        findViewById(R.id.imageMan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.manImgShadow).setAlpha(0);
                findViewById(R.id.womanImgShadow).setAlpha(0.6f);

                icon = R.drawable.ic_man;
            }
        });

        findViewById(R.id.buttonPP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManagement.setProfilePicture(ProfilePictureActivity.this, icon);
                openInitFirstCardActivity();
            }
        });

    }


    private void openInitFirstCardActivity() {
        startActivity(new Intent(this, CardActivity.class));

    }
}