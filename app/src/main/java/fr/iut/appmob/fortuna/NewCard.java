package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewCard extends Popup {

    public NewCard(Activity activity, String title) {
        super(activity, new String[] {
                title,
                "Name :",
                "Balance :"
        });
        setContentView(R.layout.popup_newcard);

        super.setButtons(new Button[] {
             findViewById(R.id.cancelButtonAddNewCreditCardPopup),
             findViewById(R.id.addButtonAddNewCreditCardPopup)
        });

        super.setEditTexts(new EditText[] {
                findViewById(R.id.editTextNamePopup),
                findViewById(R.id.editTextBalancePopup)
        });

        super.setTextViews(new TextView[] {
                findViewById(R.id.titleAddNewCreditCardPopup),
                findViewById(R.id.nameAddNewCreditCardPopup),
                findViewById(R.id.balanceAddNewCreditCardPopup)
        });
    }

    @Override
    public void validate() {
        Log.i("NewCard", "Adding a new card");
    }

}