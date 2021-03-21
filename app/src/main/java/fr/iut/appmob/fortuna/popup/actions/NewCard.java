package fr.iut.appmob.fortuna.popup.actions;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.popup.Popup;

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
        Editable name = super.getEditText(0).getText();
        Editable balance;
        if(name.toString().equals("")) {
            super.getEditText(0).setError("Error : can't be empty !");
        } else {
            balance = super.getEditText(1).getText();
            if(balance.toString().equals("")) {
                super.getEditText(1).setError("Error : can't be empty !");
            } else {
                Log.i("NewCard", "cardname : " + name + ", " + " balance :" + balance );
                this.dismiss();
            }
        }


    }

}