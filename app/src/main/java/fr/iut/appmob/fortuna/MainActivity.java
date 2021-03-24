package fr.iut.appmob.fortuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import fr.iut.appmob.fortuna.config.NameActivity;
import fr.iut.appmob.fortuna.data.DataManagement;
import fr.iut.appmob.fortuna.init.Loading;
import fr.iut.appmob.fortuna.popup.actions.NewDeposit;
import fr.iut.appmob.fortuna.popup.actions.NewSpending;
import fr.iut.appmob.fortuna.views.HomeFragment;
import fr.iut.appmob.fortuna.views.SettingsFragment;
import fr.iut.appmob.fortuna.views.StatsFragment;

public class MainActivity extends AppCompatActivity {

    // for the action button
    private FloatingActionButton[] buttons;
    private Animation[] openingAnimations;
    private Animation[] closingAnimations;
    private boolean isOpen = false;

    // for the navbar
    private static ChipNavigationBar navbar;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Loading(this).startLoading();

        if (DataManagement.isFirstRun(MainActivity.this)) {
            openConfigActivity();
        }
        DataManagement.setFirstRun(MainActivity.this, false);
        setContentView(R.layout.activity_main);

        // init our differents elements
        navbar = findViewById(R.id.bottomNav);
        this.manager = getSupportFragmentManager();

        this.setButtons();
        this.setAnimations();
        this.setListener();

        // default selected item in the navbar
        navbar.setItemSelected(R.id.menuHome, true);

    }

    public static ChipNavigationBar getNavbar() {
        return navbar;

    }

    private void openConfigActivity(){
        startActivity(new Intent(this, NameActivity.class));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new Loading(this).startLoading();

    }

    public void setButtons() {
        buttons = new FloatingActionButton[] {
                (FloatingActionButton) findViewById(R.id.add_btn),
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
                                new NewDeposit(MainActivity.this, "Add deposit").build();
                                break;
                            case 2:
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

    // animate the buttons according to the state of
    // the action button open or close
    private void animateBtn () {
        if (isOpen) {
            try {
                this.launchAnimation(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setButtonClickable(false);
            isOpen = false;
        } else {
            try {
                this.launchAnimation(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setButtonClickable(true);
            isOpen = true;
        }

    }

    // launch the adequate animation according to the int
    // given. 0 : opening animation | 1 : closing animation
    private void launchAnimation(int animation) throws Exception {
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

    // update state of the buttons in the action button
    private void setButtonClickable(boolean clickable) {
        for (int i = 1; i < buttons.length; ++i)
            buttons[i].setClickable(clickable);

    }

    // Change the view according to the selected item
    // on the bottom navbar
    private void setFragment(Fragment fragment) {
        this.manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

}

