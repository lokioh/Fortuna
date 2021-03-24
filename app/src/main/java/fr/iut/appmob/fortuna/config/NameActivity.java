package fr.iut.appmob.fortuna.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import fr.iut.appmob.fortuna.data.DataManagement;
import fr.iut.appmob.fortuna.init.Loading;
import fr.iut.appmob.fortuna.R;

public class NameActivity extends AppCompatActivity {

    private static final String errorMsg = "Error : can't be empty !";
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
                    firstnameInput.setError(errorMsg);
                } else {
                    lastnameValue = lastnameInput.getText();
                    if (lastnameValue.toString().equals("")) {
                        lastnameInput.setError(errorMsg);
                    } else {
                        DataManagement.setFirstAndLastName(NameActivity.this, firstnameValue.toString(), lastnameValue.toString());
                        openChooseIconActivity();
                    }
                }
            }
        });

    }

    // open the next Form
    private void openChooseIconActivity() {
        startActivity(new Intent(this, ProfilePictureActivity.class));

    }

}