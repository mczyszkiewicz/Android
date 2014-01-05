package com.example;

import com.example.mat.czysz.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

	private static final int CZAS = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread background = new Thread() {
			public void run() {
				try {
					sleep(CZAS);
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		background.start();
	}

	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
