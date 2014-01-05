package com.example.mateusz.czyszkiewicz;

import android.database.sqlite.SQLiteDatabase;

public class WydatkiTable {

	public static final String TABLE_WYDATKI = "wydatki";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAZWA = "nazwa";
	public static final String COLUMN_TYP = "typ_id";
	public static final String COLUMN_WARTOSC = "wartosc";
	public static final String COLUMN_DATA = "data";

	private static final String DATABASE_CREATE = "Create table "
			+ TABLE_WYDATKI + "(" + COLUMN_ID + " integer primary key, "
			+ COLUMN_NAZWA + " text not null, " + COLUMN_TYP
			+ " text not null, " + COLUMN_WARTOSC + " text not null, "
			+ COLUMN_DATA + " text not null" + ");";

	public static void OnCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		database.execSQL("Drop table if exists " + TABLE_WYDATKI);
		OnCreate(database);
	}
}
