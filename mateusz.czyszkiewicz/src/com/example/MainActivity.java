package com.example;

import com.example.mat.czysz.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {

	EditText name;
	MyDialog mydial = new MyDialog();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText) findViewById(R.id.editText_name);

	}

	public void showAlert(View v) {

		FragmentManager fm = getSupportFragmentManager();
		mydial.show(fm, "cos");
		mydial.setName(name.getText().toString());

	}

	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
