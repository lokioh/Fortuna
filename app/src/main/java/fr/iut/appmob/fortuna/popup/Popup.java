package fr.iut.appmob.fortuna.popup;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.iut.appmob.fortuna.R;

public class Popup extends Dialog {
    private String[] texts;
    private Button[] buttons;
    private TextView[] textViews;
    private EditText[] editTexts;

    public Popup(Activity activity, String[] texts) {

        super(activity, R.style.dialog_theme);

        this.texts = texts;

    }

    public void setButtons(Button[] buttons) { this.buttons = buttons; }

    public void setEditTexts(EditText[] editTexts) {this.editTexts = editTexts;}
    public void setTextViews(TextView[] textViews) {this.textViews = textViews;}

    public EditText getEditText(int idx) {
        if (idx < 0) return null;
        if (idx > 1) return null;
        return editTexts[idx];
    }

    // build the dialog box and put in place the listeners
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

    // cancel the action
    public void cancel() {
        super.cancel();
    };

    // confirm the action, need to be implemented
    public void validate() {
        return;
    }

}
