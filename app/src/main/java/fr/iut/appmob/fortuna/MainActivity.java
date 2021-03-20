package fr.iut.appmob.fortuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    // for the action button
    FloatingActionButton[] buttons;
    Animation[] openingAnimations;
    Animation[] closingAnimations;
    boolean isOpen = false;

    // for the navbar
    ChipNavigationBar navbar;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Loading(this).startLoading();
        setContentView(R.layout.activity_main);

        this.navbar = findViewById(R.id.bottomNav);
        this.manager = getSupportFragmentManager();
        this.setButtons();
        this.setAnimations();
        this.setListener();

        this.navbar.setItemSelected(R.id.menuHome, true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new Loading(this).startLoading();
    }

    public void setButtons() {
        buttons = new FloatingActionButton[] {
                (FloatingActionButton) findViewById(R.id.add_btn),
                (FloatingActionButton) findViewById(R.id.add_new_credit_card),
                (FloatingActionButton) findViewById(R.id.add_new_deposit),
                (FloatingActionButton) findViewById(R.id.add_new_spending)
        };

    }

    private void setAnimations() {
        openingAnimations = new Animation[] {
                AnimationUtils.loadAnimation(this, R.anim.rotate_foward_anim),
                AnimationUtils.loadAnimation(this, R.anim.open_anim)
        };

        closingAnimations = new Animation[] {
                AnimationUtils.loadAnimation(this, R.anim.rotate_backward_anim),
                AnimationUtils.loadAnimation(this, R.anim.close_anim)
        };

    }

    private void setListener() {
        for (int i = 0; i < buttons.length; ++i) {
            if (i == 0) {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animateBtn();
                    }
                });
            } else {
                int popup = i;
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animateBtn();
                        switch (popup) {
                            case 1:
                                new NewCard(MainActivity.this, "Add new credit card").build();
                                break;
                            case 2:
                                new NewDeposit(MainActivity.this, "Add deposit").build();
                                break;
                            case 3:
                                new NewSpending(MainActivity.this, "Add spending").build();
                                break;
                        }
                    }
                });
            }
        }

        navbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switch (id) {
                    case R.id.menuHome:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.menuStat:
                        setFragment(new StatsFragment());
                        break;
                    case R.id.menuSettings:
                        setFragment(new SettingsFragment());
                        break;
                    default:
                        try {
                            throw new Exception("Error when creating fragment");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });

    }

    private void animateBtn () {
        if (isOpen) {
            try {
                this.addBtnAnimation(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setButtonClickable(false);
            isOpen = false;
        } else {
            try {
                this.addBtnAnimation(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setButtonClickable(true);
            isOpen = true;
        }

    }

    private void addBtnAnimation(int animation) throws Exception {
        Animation[] animations = new Animation[2];
        if (animation == 0) animations = openingAnimations;
        if (animation == 1) animations = closingAnimations;
        if (animation > 1) throw new Exception("There is only two choices 0 or 1");

        for (int i = 0; i < buttons.length; ++i) {
            if (i != 0) {
                buttons[i].startAnimation(animations[1]);
            } else {
                buttons[i].startAnimation(animations[0]);
            }
        }

    }

    private void setButtonClickable(boolean clickable) {
        for (int i = 1; i < buttons.length; ++i)
            buttons[i].setClickable(clickable);

    }

    private void setFragment(Fragment fragment) {
        this.manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}

