package fr.iut.appmob.fortuna.popup.actions;

import android.app.Activity;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.MainActivity;
import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.popup.Popup;
import fr.iut.appmob.fortuna.data.DataManagement;

public class NewSpending extends Popup {

    EditText editTextNewSpending;
    EditText editTextCategory;

    private static final String[] CATEGORIES = {
            "home",
            "phone",
            "car",
            "health",
            "food",
            "sport",
            "gift",
            "other"
    };

    public NewSpending(Activity activity, String title) {
        super(activity, new String[] {
                title,
                "Value :",
                "Category :"
        });

        setContentView(R.layout.popup_newspending);

        super.setButtons(new Button[] {
                findViewById(R.id.cancelButtonAddNewSpendingPopup),
                findViewById(R.id.addButtonAddNewSpendingPopup)
        });

        super.setEditTexts(new EditText[] {
                findViewById(R.id.editTextValueAddNewSpendingPopup),
                findViewById(R.id.editTextCategoryAddNewSpendingPopup)
        });

        super.setTextViews(new TextView[] {
                findViewById(R.id.titleAddNewSpendingPopup),
                findViewById(R.id.valueAddNewSpendingPopup),
                findViewById(R.id.categoryAddNewSpendingPopup)
        });

    }

    @Override
    public void validate() {
        Editable value = super.getEditText(0).getText();
        editTextNewSpending = findViewById(R.id.editTextValueAddNewSpendingPopup);
        String spendingValue = editTextNewSpending.getText().toString();
        Editable categoryInput;

        if (value.toString().equals("")) {
            super.getEditText(0).setError("Error : can't be empty !");
        } else {
            categoryInput = super.getEditText(1).getText();
            if(categoryInput.toString().equals("")) {
                super.getEditText(1).setError("Error : can't be empty !");
            } else if (!isValid(categoryInput.toString().toLowerCase())) {
                super.getEditText(1).setError("Error : category should be : Home or " +
                        "Phone or Car or Health or Food or Sport " +
                        "or Gift or Other");
            } else {
                editTextCategory = findViewById(R.id.editTextCategoryAddNewSpendingPopup);
                String category = editTextCategory.getText().toString().toLowerCase();
                DataManagement.addNewExpense(getContext(), spendingValue, category);
                MainActivity.getNavbar().setItemSelected(R.id.menuHome, false);
                MainActivity.getNavbar().setItemSelected(R.id.menuHome, true);
                this.dismiss();
            }
        }

    }

    // check if the category given is in the CATEGORIES allowed
    private boolean isValid(String categoryGiven) {
        boolean valid = true;

        for (String category : CATEGORIES) {
            if (categoryGiven.equals(category)) return valid;
        }

        return !valid;

    }
}
