package com.example.mateusz.czyszkiewicz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {
	private static final int CZAS = 5000;
	private Thread background;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		background = new Thread() {
			public void run() {
				try {
					sleep(CZAS);
					Intent intent = new Intent(SplashScreen.this,
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
