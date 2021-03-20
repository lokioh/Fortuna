package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewDeposit extends Popup {

    public NewDeposit(Activity activity, String title) {
        super(activity, new String[] {
                title,
                "Value :",
                "Categorie :"
        });
        setContentView(R.layout.popup_newdeposit);

        super.setButtons(new Button[] {
                findViewById(R.id.cancelButtonAddNewDepositPopup),
                findViewById(R.id.addButtonAddNewDepositPopup)
        });

        super.setEditTexts(new EditText[] {
                findViewById(R.id.editTextValueAddNewDepositPopup),
                findViewById(R.id.editTextCategoriAddNewDepositPopup)
        });

        super.setTextViews(new TextView[] {
                findViewById(R.id.titleAddNewDepositPopup),
                findViewById(R.id.valueAddNewDepositPopup),
                findViewById(R.id.categorieAddNewDepositPopup)
        });
    }

    @Override
    public void validate() {
        Log.i("NewDeposit", "Adding a new deposit.");
    }
}
