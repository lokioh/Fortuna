package fr.iut.appmob.fortuna.popup.actions;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.popup.Popup;

public class NewDeposit extends Popup {
    public NewDeposit(Activity activity, String title) {
        super(activity, new String[] {
                title,
                "Value :",
                "Category :"
        });
        setContentView(R.layout.popup_newdeposit);

        super.setButtons(new Button[] {
                findViewById(R.id.cancelButtonAddNewDepositPopup),
                findViewById(R.id.addButtonAddNewDepositPopup)
        });

        super.setEditTexts(new EditText[] {
                findViewById(R.id.editTextValueAddNewDepositPopup)
        });

        super.setTextViews(new TextView[] {
                findViewById(R.id.titleAddNewDepositPopup),
                findViewById(R.id.valueAddNewDepositPopup),
        });
    }

    @Override
    public void validate() {
        Editable value = super.getEditText(0).getText();
        if (value.toString().equals("")) {
            super.getEditText(0).setError("Error : can't be empty !");
        } else {
            Log.i("NewDeposit", "value : " + value + ", " + "category : deposit" );
            this.dismiss();
        }
    }

}
