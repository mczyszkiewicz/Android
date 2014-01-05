package com.example;

import com.example.mat.czysz.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends DialogFragment {

	TextView text;
	private String myname;
	Button button_dialog;

	public MyDialog() {
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog, container);
		text = (TextView) view.findViewById(R.id.textViewName);
		text.setText(myname);
		getDialog().setTitle("Hi");
		button_dialog = (Button) view.findViewById(R.id.buttonDialog);
		button_dialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});

		return view;
	}

	public void setName(String name) {
		if (name.length() > 0) {
			myname = "Hello " + name;
		} else {
			myname = "Please type your name";
		}
	}

	@Override
	public void onSaveInstanceState(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(arg0);
		arg0.putString("myString", myname);
	}

}
