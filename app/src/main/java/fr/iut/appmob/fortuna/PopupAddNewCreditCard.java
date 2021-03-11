package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PopupAddNewCreditCard extends Dialog {

    private String title;
    private String textName, textBalance;
    private Button cancelButton, addButton;
    private TextView titleView, nameView, balanceView;
    private EditText editTextName, editTextBalance;


    public PopupAddNewCreditCard(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog);
        setContentView(R.layout.popup_add_new_credit_card);
        this.title = "Add new credit card";
        this.textName = "Name :";
        this.textBalance = "Balance :";
        this.cancelButton = findViewById(R.id.cancelButtonAddNewCreditCardPopup);
        this.addButton = findViewById(R.id.addButtonAddNewCreditCardPopup);
        this.titleView = findViewById(R.id.titleAddNewCreditCardPopup);
        this.nameView = findViewById(R.id.nameAddNewCreditCardPopup);
        this.balanceView = findViewById(R.id.balanceAddNewCreditCardPopup);
        this.editTextName = findViewById(R.id.editTextNamePopup);
        this.editTextBalance = findViewById(R.id.editTextBalancePopup);
    }


    public void setTitle(String title) { this.title = title; }

    public void setTextName(String textName) { this.textName = textName; }

    public void setTextBalance(String textBalance) { this.textBalance = textBalance; }

    public Button getCancelButton() { return cancelButton; }

    public Button getAddButton() { return  addButton; }

    public EditText getEditTextName() { return editTextName; }

    public EditText getEditTextBalance() { return  editTextBalance; }

    public void build() {
        show();
        titleView.setText(title);
        nameView.setText(textName);
        balanceView.setText(textBalance);
    }

}
