package fr.iut.appmob.fortuna.popup.actions;

import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.R;
import fr.iut.appmob.fortuna.popup.Popup;
import fr.iut.appmob.fortuna.DataManagement;

public class NewSpending extends Popup {

    EditText editText_newSpending;

    private static final String[] CATEGORIES = {
            "Home",
            "Phone",
            "Car",
            "Health",
            "Food",
            "Sport",
            "Gift",
            "Other"
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
        editText_newSpending = findViewById(R.id.editTextValueAddNewSpendingPopup);
        String spendingValue = editText_newSpending.getText().toString();
        Editable category;
        if (value.toString().equals("")) {
            super.getEditText(0).setError("Error : can't be empty !");
        } else {
            category = super.getEditText(1).getText();
            if(category.toString().equals("")) {
                super.getEditText(1).setError("Error : can't be empty !");
            } else if (!isValid(category.toString())) {
                super.getEditText(1).setError("Error : category should be : Home or " +
                        "Phone or Car or Health or Food or Sport " +
                        "or Gift or Other");
            } else {
                DataManagement.addNewExpense(getContext(), spendingValue);
                Log.i("NewSpending", "value : " + value + ", " + "category : " + category);
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
