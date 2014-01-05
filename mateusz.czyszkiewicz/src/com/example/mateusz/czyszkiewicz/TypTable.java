package com.example.mateusz.czyszkiewicz;

import android.database.sqlite.SQLiteDatabase;

public class TypTable {

	public static final String TYP_TABLE = "typ";
	private static final String COLUMN_NAZWA = "nazwa";

	private static final String CREATE_DATABASE = "Create table " + TYP_TABLE
			+ "(" + COLUMN_NAZWA + " text not null );";

	private static final String sql = "Insert  into Typ(nazwa)VALUES('Czesne')";
	private static final String sql2 = "Insert into Typ(NAZWA)VALUES('Podatki')";
	private static final String sql3 = "Insert into Typ(NAZWA)VALUES('Podró¿e')";
	private static final String sql4 = "Insert into Typ(NAZWA)VALUES('Naprawy')";

	public static void OnCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_DATABASE);
		database.execSQL(sql);
		database.execSQL(sql2);
		database.execSQL(sql3);
		database.execSQL(sql4);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		database.execSQL("Drop table if exists " + TYP_TABLE);
		OnCreate(database);
	}

}
