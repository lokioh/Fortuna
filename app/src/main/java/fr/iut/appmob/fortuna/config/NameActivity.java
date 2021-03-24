package fr.iut.appmob.fortuna.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import fr.iut.appmob.fortuna.init.Loading;
import fr.iut.appmob.fortuna.R;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Loading(this).startLoading();
        setContentView(R.layout.activity_authentication);

        findViewById(R.id.buttonAuthentication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstnameInput = (EditText) findViewById(R.id.inputFirstNameForm);
                Editable firstnameValue = firstnameInput.getText();
                EditText lastnameInput = (EditText) findViewById(R.id.inputLastNameForm);
                Editable lastnameValue;
                if (firstnameValue.toString().equals("")) {
                    firstnameInput.setError("Error : can't be empty !");
                } else {
                    lastnameValue = lastnameInput.getText();
                    if (lastnameValue.toString().equals("")) {
                        lastnameInput.setError("Error : can't be empty !");
                    } else {
                        Log.e("Setting", firstnameValue.toString());
                        SharedPreferences CONFIG = getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
                        CONFIG.edit().putString("first_name", firstnameValue.toString()).commit();
                        CONFIG.edit().putString("last_name", lastnameValue.toString()).commit();
                        openChooseIconActivity();
                    }
                }



            }
        });
    }

    private void openChooseIconActivity() {
        startActivity(new Intent(this, ProfilePictureActivity.class));
    }
}