
package fr.iut.appmob.fortuna;

        import android.app.Activity;
        import android.app.Dialog;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class PopupAddNewDeposit extends Dialog {

    private String title;
    private String textValue, textCategorie;
    private Button cancelButton, addButton;
    private TextView titleView, valueView, categorieView;
    private EditText editTextValue, editTextCategorie;


    public PopupAddNewDeposit(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog);
        setContentView(R.layout.popup_add_new_deposit);
        this.title = "Add deposit";
        this.textValue = "Value :";
        this.textCategorie = "Categorie :";
        this.cancelButton = findViewById(R.id.cancelButtonAddNewDepositPopup);
        this.addButton = findViewById(R.id.addButtonAddNewDepositPopup);
        this.titleView = findViewById(R.id.titleAddNewDepositPopup);
        this.valueView = findViewById(R.id.valueAddNewDepositPopup);
        this.categorieView = findViewById(R.id.categorieAddNewDepositPopup);
        this.editTextValue = findViewById(R.id.editTextValueAddNewDepositPopup);
        this.editTextCategorie = findViewById(R.id.editTextCategoriAddNewDepositPopup);
    }


    public void setTitle(String title) { this.title = title; }

    public void setTextValue(String textValue) { this.textValue = textValue; }

    public void setTextCategorie(String textCategorie) { this.textCategorie = textCategorie; }

    public Button getCancelButton() { return cancelButton; }

    public Button getAddButton() { return  addButton; }

    public EditText getEditTextValue() { return editTextValue; }

    public EditText getEditTextCategorie() { return  editTextCategorie; }

    public void build() {
        show();
        titleView.setText(title);
        valueView.setText(textValue);
        categorieView.setText(textCategorie);
    }

}
