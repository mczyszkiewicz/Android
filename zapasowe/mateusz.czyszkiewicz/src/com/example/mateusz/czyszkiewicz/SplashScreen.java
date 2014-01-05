package com.example.mateusz.czyszkiewicz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {
	private boolean stop = false;
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

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (!stop) {
						Intent intent = new Intent(SplashScreen.this,
								MainActivity.class);
						startActivity(intent);
						finish();
					} else
						
					finish();

				}
			}
		};
		background.start();

	}

	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (background.isAlive()) {
			this.stop = true;

		}
	}

}
