package com.example.mateusz.czyszkiewicz;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 9;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		WydatkiTable.OnCreate(database);
		TypTable.OnCreate(database);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		WydatkiTable.onUpgrade(database, oldVersion, newVersion);
		TypTable.onUpgrade(database, oldVersion, newVersion);
		Log.d("zmiana", "Dokonano zmiany tabel");

	}

	public List<String> getAllLabels() {
		List<String> labels = new ArrayList<String>();

		String selectQuery = "SELECT  * FROM " + TypTable.TYP_TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return labels;
	}
}
