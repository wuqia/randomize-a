package com.example.randomize;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

public class NumberActivity extends Activity {
	private TextView mResultLabel;
	private Thread mThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number);
		mResultLabel = (TextView) findViewById(R.id.textViewResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.number, menu);
		return true;
	}
	
	public void setLabelText(String s) {
		mResultLabel.setText(s);
	}
	
	public void animateLabel() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
		mResultLabel.startAnimation(animation);
	}
	
	public void rollNumber(final View view) {
		EditText startView = (EditText) findViewById(R.id.editTextRangeStart);
		EditText endView = (EditText) findViewById(R.id.editTextRangeEnd);
		
		int start = Integer.valueOf(startView.getText().toString());
		int end = Integer.valueOf(endView.getText().toString());
		
		if(mThread != null && mThread.isAlive()) {
			mThread.interrupt();
		}
		
		mThread = new RollingThread(start, end);
		mThread.start();
	}
	
	class RollingThread extends Thread {
		private int count = 0;
		private final int[] counts = new int[]{16, 24, 28};
		private final int start;
		private final int end;
		private Handler mHandler = new Handler();
		
		public RollingThread(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public void run() {
			while(count < counts[2]) {
				final int random = start + (int)(Math.random() * (end+1-start));
				mHandler.post(new Runnable() {
					public void run() {
						setLabelText(String.valueOf(random));
					}
				});
				int delay = 1000/(counts[2]-counts[1]);
				if(count < counts[0]) {
					delay = 1000/counts[0];
				}
				else if(count < counts[1]) {
					delay = 1000/(counts[1]-counts[0]);
				}
				count++;
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
				}
			}
			mHandler.post(new Runnable() {
				public void run() {
					animateLabel();
				}
			});
		 }
	}

}
