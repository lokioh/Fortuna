package fr.iut.appmob.fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    FloatingActionButton add_btn, add_new_spending, add_new_deposit, add_new_credit_card;
    Animation btnOpen, btnClose, rotateForward, rotateBackward;
    boolean isOpen = false; // Boolean which identifify if the add btn is open or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        add_btn = (FloatingActionButton) findViewById(R.id.add_btn);
        add_new_spending = (FloatingActionButton) findViewById(R.id.add_new_credit_card);
        add_new_deposit = (FloatingActionButton) findViewById(R.id.add_new_deposit);
        add_new_credit_card = (FloatingActionButton) findViewById(R.id.add_new_spending);

        btnOpen = AnimationUtils.loadAnimation(this,R.anim.open_anim);
        btnClose = AnimationUtils.loadAnimation(this,R.anim.close_anim);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_foward_anim);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward_anim);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateBtn();
            }
        });

        add_new_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateBtn();
                Toast.makeText(Dashboard.this, "Add new credit card clicked", Toast.LENGTH_SHORT).show();
            }
        });

        add_new_spending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateBtn();
                Toast.makeText(Dashboard.this, "Add new spending clicked", Toast.LENGTH_SHORT).show();
            }
        });

        add_new_credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateBtn();
                Toast.makeText(Dashboard.this, "Add new deposit clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateBtn () {
        if (isOpen) {
            add_btn.startAnimation(rotateBackward);
            add_new_credit_card.startAnimation(btnClose);
            add_new_deposit.startAnimation(btnClose);
            add_new_spending.startAnimation(btnClose);
            add_new_credit_card.setClickable(false);
            add_new_spending.setClickable(false);
            add_new_deposit.setClickable(false);
            isOpen = false;
        } else {
            add_btn.startAnimation(rotateForward);
            add_new_credit_card.startAnimation(btnOpen);
            add_new_deposit.startAnimation(btnOpen);
            add_new_spending.startAnimation(btnOpen);
            add_new_credit_card.setClickable(true);
            add_new_spending.setClickable(true);
            add_new_deposit.setClickable(true);
            isOpen = true;
        }
    }
}

