package eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    Button Nb, Eb, Wb, Sb, navigate;
    TextView textView;
    int num;
    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String s = ((Button) v).getText().toString();
            textView.setText(textView.getText().toString() + ", " + s);
            num++;
            if (num == 4) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra("1", textView.getText().toString());
                getApplicationContext().startService(intent);
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("1"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        num = 0;
        Nb = (Button) findViewById(R.id.north);
        Eb = (Button) findViewById(R.id.east);
        Wb = (Button) findViewById(R.id.west);
        Sb = (Button) findViewById(R.id.south);
        navigate = (Button) findViewById(R.id.navigate);
        textView = (TextView) findViewById(R.id.textView);

        Nb.setOnClickListener(new ButtonListener());
        Eb.setOnClickListener(new ButtonListener());
        Wb.setOnClickListener(new ButtonListener());
        Sb.setOnClickListener(new ButtonListener());
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                intent.putExtra("num", num);
                textView.setText("");
                num = 0;
                startActivityForResult(intent, 1);
            }
        });
        intentFilter.addAction("1");

    }
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("num", num);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("num")) {
            num = savedInstanceState.getInt("num");
        }
        Log.d("NUM_MODIFIED", "Modified num is " + num);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Service.class);
        stopService(intent);
        super.onDestroy();
    }


}
