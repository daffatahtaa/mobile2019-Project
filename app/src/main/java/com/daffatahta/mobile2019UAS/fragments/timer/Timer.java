package com.daffatahta.mobile2019UAS.fragments.timer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daffatahta.mobile2019UAS.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Timer extends Fragment {

    private EditText editText;
    private Button setButton, startPause, reset;
    private TextView countDown;

    private Boolean timeRunning;

    private long minuteStartInMillis;
    private long minuteTimeLeftInMillis;
    private long minuteEndInMillis;

    private CountDownTimer countDownTimer;

    public Timer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_timer, container, false);
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        editText = view.findViewById(R.id.timerInput);
        setButton = view.findViewById(R.id.buttonSetTimer);
        startPause = view.findViewById(R.id.startButton);
        reset = view.findViewById(R.id.reset);
        countDown = view.findViewById(R.id.timeLeft);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getContext(), "Mohon di isi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long miliSec = Long.parseLong(input) * 60000;
                if (miliSec == 0) {
                    Toast.makeText(getContext(), "masukkan angka positif", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(miliSec);
                editText.setText("");
            }
        });

        startPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timeRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });


        return view;
    }

    private void setTime(long miliSec) {
        minuteEndInMillis = miliSec;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        minuteEndInMillis = System.currentTimeMillis() + minuteTimeLeftInMillis;
        countDownTimer = new CountDownTimer(minuteTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                minuteTimeLeftInMillis = l;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timeRunning = false;
                updateWatchInterface();
            }
        }.start();
        timeRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timeRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        minuteTimeLeftInMillis = minuteStartInMillis;
        updateCountdownText();
        updateWatchInterface();
    }

    private void updateCountdownText() {
        int hours = (int) (minuteTimeLeftInMillis / 1000) / 3500;
        int minutes = (int) ((minuteTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (minuteTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;

        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        }
        countDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (timeRunning) {
            editText.setVisibility(View.INVISIBLE);
            setButton.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);
            startPause.setText("Pause");
        } else {
            editText.setVisibility(View.VISIBLE);
            setButton.setVisibility(View.VISIBLE);
            startPause.setVisibility(View.VISIBLE);
            if (minuteTimeLeftInMillis < 1000) {
                startPause.setVisibility(View.VISIBLE);
            } else {
                startPause.setVisibility(View.VISIBLE);
            }
            if (minuteTimeLeftInMillis < minuteStartInMillis) {
                reset.setVisibility(View.VISIBLE);
            } else {
                reset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = Timer.this.getView().findFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("StartTimeInMillisecs", minuteStartInMillis);
        editor.putLong("MillisecindsLeft", minuteTimeLeftInMillis);
        editor.putBoolean("TimeRunning", timeRunning);
        editor.putLong("EndTIme", minuteTimeLeftInMillis);

        editor.apply();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        minuteStartInMillis = prefs.getLong("StartTimeInMillis", minuteTimeLeftInMillis);
        minuteTimeLeftInMillis = prefs.getLong("MillisecLeft", minuteTimeLeftInMillis);
        timeRunning = prefs.getBoolean("TimeRunning", false);

        updateCountdownText();
        updateWatchInterface();

        if (timeRunning) {
            minuteEndInMillis = prefs.getLong("endTime", 0);
            minuteTimeLeftInMillis = minuteEndInMillis - System.currentTimeMillis();

            if (timeRunning) {
                minuteTimeLeftInMillis = 0;
                timeRunning = false;
                updateWatchInterface();
                updateCountdownText();
            } else {
                startTimer();
            }
        }

    }
}
