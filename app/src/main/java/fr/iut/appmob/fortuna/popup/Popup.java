package fr.iut.appmob.fortuna;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Popup extends Dialog {
    private String[] texts;
    private Button[] buttons;
    private TextView[] textViews;
    private EditText[] editTexts;

    public Popup(Activity activity, String[] texts) {

        super(activity, R.style.dialog_theme);

        this.texts = texts;

    }

    public void setButtons(Button[] buttons) {this.buttons = buttons;}
    public void setEditTexts(EditText[] editTexts) {this.editTexts = editTexts;}
    public void setTextViews(TextView[] textViews) {this.textViews = textViews;}

    public Button getCancelButton() {return buttons[0];}
    public Button getAddButton() {return buttons[1];}


    public EditText getEditTextName() {return editTexts[0];}
    public EditText getEditTextBalance() {return editTexts[0];}

    public void build() {
        show();
        for (int i = 0; i < textViews.length; ++i)
            textViews[i].setText(texts[i]);

        this.buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        this.buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void cancel() {
        super.cancel();
    };

    public void validate() {
        return;
    }

}
