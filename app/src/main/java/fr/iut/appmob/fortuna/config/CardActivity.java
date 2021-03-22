package fr.iut.appmob.fortuna.config;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import fr.iut.appmob.fortuna.MainActivity;
import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.tutorial.StartUp;

public class CardActivity extends AppCompatActivity {

    private static String balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        findViewById(R.id.buttonConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText balanceInput = (EditText) findViewById(R.id.inputBalance);
                Editable balanceGiven = balanceInput.getText();


                if (balanceGiven.toString().equals("")) {
                    balance = "0";
                } else {
                    balance = balanceGiven.toString();
                }

                getSharedPreferences("MONEY", Context.MODE_PRIVATE).edit()
                        .putFloat("balance", Float.parseFloat(balance))
                        .commit();
                openTutorialActivity();
            }
        });
    }

    private void openTutorialActivity() {
        startActivity(new Intent(this, StartUp.class));
    }
}