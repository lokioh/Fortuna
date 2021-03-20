package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewSpending extends Popup {

    public NewSpending(Activity activity, String title) {
        super(activity, new String[] {
                title,
                "Value :",
                "Categorie :"
        });
        setContentView(R.layout.popup_newspending);

        super.setButtons(new Button[] {
                findViewById(R.id.cancelButtonAddNewSpendingPopup),
                findViewById(R.id.addButtonAddNewSpendingPopup)
        });

        super.setEditTexts(new EditText[] {
                findViewById(R.id.editTextValueAddNewSpendingPopup),
                findViewById(R.id.editTextCategoriAddNewSpendingPopup)
        });

        super.setTextViews(new TextView[] {
                findViewById(R.id.titleAddNewSpendingPopup),
                findViewById(R.id.valueAddNewSpendingPopup),
                findViewById(R.id.categorieAddNewSpendingPopup)
        });
    }

    @Override
    public void validate() {
        Log.i("NewSpending", "Adding a new spending.");
    }

}
