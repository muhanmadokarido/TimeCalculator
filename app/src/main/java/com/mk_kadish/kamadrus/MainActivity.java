package com.mk_kadish.kamadrus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int allHours=0;
    String timeLeftFormatted;
    Timer T;
    TextView tv_timer;
    int seconds;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_timer=findViewById(R.id.timer_tv);
    }

    public void start_pause_Lesson(View view) {
        findViewById(R.id.button_start_pause).setEnabled(false);
        findViewById(R.id.button_pause2).setEnabled(true);
        T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {  int count1=count;
                        if(count >0)
                        {
                            int h=0;
                            if(count1> 3600)
                            {
                                h=count1/3600;
                                count1=count1%3600;
                            }
                            int minutes  =count1/60;
                            int seconds = count1 % 60;

                            timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", h,minutes, seconds);
                            tv_timer.setText(timeLeftFormatted);
                        }
                        count++;
                    }
                });
            }
        }, 0, 1000);
    }


    private String updateCountDownText(int count1) {
        int h=0;
        if(count1> 3600)
        {
            h=count1/3600;
            count1=count1%3600;
        }
        int minutes  =count1/60;
        int seconds = count1 % 60;
        //String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
     //   return seconds+ " ثانية" + minutes + " دقيقة " +h+ " ساعة " ;
        return  h+"Hours" + minutes + "Minutes" +seconds+ "Seconds" ;
     }

    public void pause_Lesson(View view) {
        findViewById(R.id.button_start_pause).setEnabled(true);
        findViewById(R.id.button_pause2).setEnabled(false);
        T.cancel();
        allHours=allHours+count;
        count=0;
        tv_timer.setText("00:00:00");
        TextView tv=findViewById(R.id.allstudyies);
        tv.setText(updateCountDownText(allHours));
    }
}

