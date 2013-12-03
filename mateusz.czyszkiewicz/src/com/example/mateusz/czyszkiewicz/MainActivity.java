package com.example.mateusz.czyszkiewicz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final int CZAS = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Thread background = new Thread() {
			
			public void run() {
				try {
					sleep(CZAS);
					Intent intent = new Intent(getBaseContext(),
							SplashActivity.class);
					startActivity(intent);
					finish();
				} catch (Exception e) {
				}

			}
		};
		background.start();
	}

	public void onBackPressed()
	{
		super.onBackPressed();
		finish();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
