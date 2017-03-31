package eim.systems.cs.pub.ro.practicaltest01var01;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.Random;

class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private double arithmeticMean;
    private double geometricMean;

    private String text;

    public ProcessingThread(Context context, String text) {
        this.text = text;
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        sleep();
        sendMessage();
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("1");
        intent.putExtra("1", new Date(System.currentTimeMillis()) + " " + text);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}


public class PracticalTest01Var01Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra("1");
        processingThread = new ProcessingThread(this, text);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
