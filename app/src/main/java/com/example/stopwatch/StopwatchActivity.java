package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {
    private int seconds = 0; // В переменных seconds и running хранится количество прошедших секунд и флаг работы секундомера.
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) { // вызывается при щелчке на кнопке Start
        running = true;
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) { // вызывается при щелчке на кнопке Stop
        running = false; // остановить секундомер
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) { // вызывается при щелчке на кнопке Reset
        running = false; // установить секундомер и присвоить seconds значение 0
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler(); // создать новый объект Handler
        handler.post(new Runnable() { // метод post обеспечивает выполнение без задержки, и код будет выполнен немедленно(быстро)
            @Override
            public void run() { // содержит код, который требуется выполнить(код обновления надписи)
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000); // Код из объекта Runnable передается на повторное выполнение после истечения задержки в 1000 миллисекунд (1 секунда). Так как эта строка кода включена в метод run() объекта Runnable, код будет вызываться снова и снова
            }
        });
    }
}