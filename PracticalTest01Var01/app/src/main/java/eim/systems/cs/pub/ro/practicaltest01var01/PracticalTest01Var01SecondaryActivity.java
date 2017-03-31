package eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    private TextView numberOfClicksTextView = null;
    private Button okButton = null;
    private Button cancelButton = null;

    private Context context;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_button:
                    Toast.makeText(context, "OK PRESSED!", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    Toast.makeText(context, "CANCEL PRESSED!", Toast.LENGTH_LONG).show();
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);
        context = this;

        numberOfClicksTextView = (TextView)findViewById(R.id.number_of_clicks_text_view);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("num")) {
            int numberOfClicks = intent.getIntExtra("num", -1);
            numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
        }

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
    }

}