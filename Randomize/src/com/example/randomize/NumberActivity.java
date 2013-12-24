package com.example.randomize;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NumberActivity extends Activity {
	private TextView resultLabel;
	private int frequency = 0;
	private int count = 0;
	private int start = 0;
	private int end = 0;
	private Timer timer;
	static private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number);
		resultLabel = (TextView) findViewById(R.id.textViewResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.number, menu);
		return true;
	}
	
	public void rollNumber(final View view) {
		EditText startView = (EditText) findViewById(R.id.editTextRangeStart);
		EditText endView = (EditText) findViewById(R.id.editTextRangeEnd);
		
		int startNum = Integer.valueOf(startView.getText().toString());
		int endNum = Integer.valueOf(endView.getText().toString());
		
		start = startNum < endNum ? startNum : endNum;
		end = startNum > endNum ? startNum : endNum;
		
		timer = new Timer();
		frequency = 150;
		count = 0;
		timer.schedule(new TimerTask() {
			public void run() {
				final int random = start + (int)(Math.random() * (end+1-start));
				if(count >= 35) {
					timer.cancel();
					timer.purge();
				}
				count++;
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						resultLabel.setText(String.valueOf(String.valueOf(random)));
					}
				});
			}
		}, 0, frequency);
	}

}
