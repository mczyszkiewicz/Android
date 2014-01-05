package com.example.mateusz.czyszkiewicz;

import java.util.List;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditDialog extends DialogFragment implements
		OnItemSelectedListener

{

	private Button button;
	private EditText wartosci;
	private EditText nazwy;
	private EditText daty;
	private int position;
	private Spinner spinner;
	private String wartosc_spinnera;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.edit_dialog, container);
		getDialog().setTitle("Edycja");
		button = (Button) view.findViewById(R.id.EditDialogbutton1);
		wartosci = (EditText) view.findViewById(R.id.EditDialogeditText4);
		nazwy = (EditText) view.findViewById(R.id.EditDialogeditText1);
		daty = (EditText) view.findViewById(R.id.EditDialogeditText3);
		spinner = (Spinner) view.findViewById(R.id.spinner2);
		loadSpinnerData();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nazwa = nazwy.getText().toString();
				String tmp2 = wartosci.getText().toString();
				double wartosc = Double.parseDouble(tmp2);
				String data = daty.getText().toString();
				wartosc_spinnera = spinner.getSelectedItem().toString();
				ContentValues updatevalues = new ContentValues();
				updatevalues.put(WydatkiTable.COLUMN_NAZWA, nazwa);
				updatevalues.put(WydatkiTable.COLUMN_TYP, wartosc_spinnera);
				updatevalues.put(WydatkiTable.COLUMN_WARTOSC, wartosc);
				updatevalues.put(WydatkiTable.COLUMN_DATA, data);
				position++;
				String where = "_id ==" + String.valueOf(position);
				getActivity().getContentResolver().update(
						MyContentProvider.WYDATKI_URI, updatevalues, where,
						null);

				dismiss();
			}

		});

		return view;
	}

	public void position(int pos) {
		position = pos;
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
