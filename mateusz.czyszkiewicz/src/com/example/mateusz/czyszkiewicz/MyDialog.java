package com.example.mateusz.czyszkiewicz;

import java.util.List;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MyDialog extends DialogFragment implements OnItemSelectedListener {

	private Button button;
	private EditText wartosci;
	private EditText nazwy;
	private EditText daty;
	Context context;
	DataBaseHelper dbhelper;
	Spinner spinner;
	String wartosc_spinnera;

	public MyDialog() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog, container);
		getDialog().setTitle(R.string.addm);
		button = (Button) view.findViewById(R.id.button1);
		wartosci = (EditText) view.findViewById(R.id.editText4);
		nazwy = (EditText) view.findViewById(R.id.editText1);
		daty = (EditText) view.findViewById(R.id.editText3);
		spinner = (Spinner) view.findViewById(R.id.spinner1);
		loadSpinnerData();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nazwa = nazwy.getText().toString();
				String tmp2 = wartosci.getText().toString();
				wartosc_spinnera = spinner.getSelectedItem().toString();
				double wartosc = Double.parseDouble(tmp2);
				String data = daty.getText().toString();
				ContentValues values = new ContentValues();
				values.put(WydatkiTable.COLUMN_NAZWA, nazwa);
				values.put(WydatkiTable.COLUMN_TYP, wartosc_spinnera);
				values.put(WydatkiTable.COLUMN_WARTOSC, wartosc);
				values.put(WydatkiTable.COLUMN_DATA, data);
				getActivity().getContentResolver().insert(
						MyContentProvider.WYDATKI_URI, values);
				Toast.makeText(getActivity(), "Dodano wpis do bazy",
						Toast.LENGTH_SHORT).show();

				dismiss();

			}
		});

		return view;
	}

	private void loadSpinnerData() {

		DataBaseHelper db = new DataBaseHelper(getActivity()
				.getApplicationContext());

		List<String> lables = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		arg0.getItemAtPosition(arg2).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
