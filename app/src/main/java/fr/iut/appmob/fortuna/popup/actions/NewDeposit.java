package fr.iut.appmob.fortuna.popup.actions;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.MainActivity;
import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.popup.Popup;
import fr.iut.appmob.fortuna.data.DataManagement;

public class NewDeposit extends Popup {

    EditText editText_newDeposit;
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
        editText_newDeposit = findViewById(R.id.editTextValueAddNewDepositPopup);
        String depositValue = editText_newDeposit.getText().toString();
        if (value.toString().equals("")) {
            super.getEditText(0).setError("Error : can't be empty !");
        } else {
            DataManagement.addNewDeposit(getContext(), depositValue);
            MainActivity.getNavbar().setItemSelected(R.id.menuHome, false);
            MainActivity.getNavbar().setItemSelected(R.id.menuHome, true);
            this.dismiss();
        }

    }

}
