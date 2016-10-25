package ng.cheo.android.singapore;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    EditText qnOneAnswerEditText = null;
    Dictionary qnTwoCheckBoxes = null;
    Dictionary qnThreeCheckBoxes = null;
    Boolean qnFourRadio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize question one
        qnOneAnswerEditText = (EditText) findViewById(R.id.qn1_ans);
        qnOneAnswerEditText.setText("");

        // Initialize question two
        qnTwoCheckBoxes = new Hashtable();
        qnTwoCheckBoxes.put("red", false);
        qnTwoCheckBoxes.put("blue", false);
        qnTwoCheckBoxes.put("purple", false);
        qnTwoCheckBoxes.put("yellow", false);
        qnTwoCheckBoxes.put("green", false);
        qnTwoCheckBoxes.put("white", false);

        // Initialize question three
        qnThreeCheckBoxes = new Hashtable();
        qnThreeCheckBoxes.put("english", false);
        qnThreeCheckBoxes.put("spanish", false);
        qnThreeCheckBoxes.put("malay", false);
        qnThreeCheckBoxes.put("mandarin", false);
        qnThreeCheckBoxes.put("tamil", false);
        qnThreeCheckBoxes.put("japanese", false);

    }

    /**
     * This method is called when there is a click on the question two checkboxes
     * @param view
     */
    public void qnTwoCheckboxClicked(View view) {

        switch (view.getId()) {
            case R.id.qn2_red:
                Boolean red = (Boolean) qnTwoCheckBoxes.get("red");
                qnTwoCheckBoxes.put("red", !red);
                break;
            case R.id.qn2_blue:
                Boolean blue = (Boolean) qnTwoCheckBoxes.get("blue");
                qnTwoCheckBoxes.put("blue", !blue);
                break;
            case R.id.qn2_purple:
                Boolean purple = (Boolean) qnTwoCheckBoxes.get("purple");
                qnTwoCheckBoxes.put("purple", !purple);
                break;
            case R.id.qn2_yellow:
                Boolean yellow = (Boolean) qnTwoCheckBoxes.get("yellow");
                qnTwoCheckBoxes.put("yellow", !yellow);
                break;
            case R.id.qn2_green:
                Boolean green = (Boolean) qnTwoCheckBoxes.get("green");
                qnTwoCheckBoxes.put("green", !green);
                break;
            case R.id.qn2_white:
                Boolean white = (Boolean) qnTwoCheckBoxes.get("white");
                qnTwoCheckBoxes.put("white", !white);
                break;
        }

    }

    /**
     * This method is called when there is a click on the question three checkboxes
     * @param view
     */
    public void qnThreeCheckboxClicked(View view) {

        switch (view.getId()) {
            case R.id.qn3_english:
                Boolean english = (Boolean) qnThreeCheckBoxes.get("english");
                qnThreeCheckBoxes.put("english", !english);
                break;
            case R.id.qn3_spanish:
                Boolean spanish = (Boolean) qnThreeCheckBoxes.get("spanish");
                qnThreeCheckBoxes.put("spanish", !spanish);
                break;
            case R.id.qn3_malay:
                Boolean malay = (Boolean) qnThreeCheckBoxes.get("malay");
                qnThreeCheckBoxes.put("malay", !malay);
                break;
            case R.id.qn3_mandarin:
                Boolean mandarin = (Boolean) qnThreeCheckBoxes.get("mandarin");
                qnThreeCheckBoxes.put("mandarin", !mandarin);
                break;
            case R.id.qn3_tamil:
                Boolean tamil = (Boolean) qnThreeCheckBoxes.get("tamil");
                qnThreeCheckBoxes.put("tamil", !tamil);
                break;
            case R.id.qn3_japanese:
                Boolean japanese = (Boolean) qnThreeCheckBoxes.get("japanese");
                qnThreeCheckBoxes.put("japanese", !japanese);
                break;
        }

    }

    /**
     * This method is called when there is a click on the question four radio buttons
     * @param view
     */
    public void qnFourRadioClicked(View view) {
        switch (view.getId()) {
            case R.id.qn4_yes:
                qnFourRadio = true;
                break;
            case R.id.qn4_no:
                qnFourRadio = false;
                break;
        }
    }

    /**
     * This method is called when the user submit the quiz
     * @param view
     */
    public void submitQuiz(View view) {
        hideKeyboard(MainActivity.this);

        Integer score = 0;

        /**
         * This is where we validate question one
         */
        String qnOneAnswer = String.valueOf(qnOneAnswerEditText.getText()).trim();
        if (qnOneAnswer.isEmpty()) {
            Toast.makeText(MainActivity.this, "You cannot leave question 1 blank", Toast.LENGTH_LONG).show();
            return;
        }
        else if (qnOneAnswer.toLowerCase().equals("asia")) {
            score += 1;
        }

        /**
         * This is where we validate question two
         */
        if (((Boolean) qnTwoCheckBoxes.get("red")) &&
                !((Boolean) qnTwoCheckBoxes.get("blue")) &&
                !((Boolean) qnTwoCheckBoxes.get("purple")) &&
                !((Boolean) qnTwoCheckBoxes.get("yellow")) &&
                !((Boolean) qnTwoCheckBoxes.get("green")) &&
                ((Boolean) qnTwoCheckBoxes.get("white"))) {
            score += 1;
        }

        /**
         * This is where we validate question three
         */
        if (((Boolean) qnThreeCheckBoxes.get("english")) &&
                !((Boolean) qnThreeCheckBoxes.get("spanish")) &&
                ((Boolean) qnThreeCheckBoxes.get("malay")) &&
                ((Boolean) qnThreeCheckBoxes.get("mandarin")) &&
                ((Boolean) qnThreeCheckBoxes.get("tamil")) &&
                !((Boolean) qnThreeCheckBoxes.get("japanese"))) {
            score += 1;
        }

        /**
         * This is where we validate question four
         */
        if (qnFourRadio != null) {
            if (!qnFourRadio) {
                score += 1;
            }
        }
        else {
            Toast.makeText(MainActivity.this, "You forgot to answer question 4", Toast.LENGTH_LONG).show();
            return;
        }

        /**
         * This is where we display the score
         */
        String message = "Total score: " + String.valueOf(score) + "/4";
        if (score == 4) {
            message += ". Congrats, you got all the answers correct!";
        }

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * This method is called to hide the keyboard
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        View v = activity.getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
