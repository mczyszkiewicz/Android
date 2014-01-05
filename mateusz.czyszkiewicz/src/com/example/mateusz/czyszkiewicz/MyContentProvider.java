package com.example.mateusz.czyszkiewicz;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

	private DataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private UriMatcher urimatcher;
	private static final int WYDATKI = 10;
	private static final int WYDATKI_ID = 20;

	private static final String AUTHORITY = "com.example.mateusz.czyszkiewicz.provider.mycontentprovider";

	private static final String WYDATKI_PATH = "WydatkiTable";

	public static final Uri WYDATKI_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + WYDATKI_PATH);
	public static final Uri WYDATKI_URI_ID = Uri.parse("content://" + AUTHORITY
			+ "/" + WYDATKI_PATH + "/");

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/wydatki";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/wydatek";

	private static final UriMatcher sUriMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sUriMatcher.addURI(AUTHORITY, WYDATKI_PATH, WYDATKI);
		sUriMatcher.addURI(AUTHORITY, WYDATKI_PATH + "/#", WYDATKI_ID);

	}

	@Override
	public boolean onCreate() {
		dbHelper = new DataBaseHelper(getContext());
		db = dbHelper.getWritableDatabase();
		return true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		int count = 0;

		switch (sUriMatcher.match(uri)) {
		case WYDATKI:

			count = db.delete(WydatkiTable.TABLE_WYDATKI,
					WydatkiTable.COLUMN_ID + "=" + selection, null);

			break;

		case WYDATKI_ID:
			Log.d("usuwanie", "tutaj usuwam");
			String id = uri.getPathSegments().get(1);
			count = db.delete(
					WydatkiTable.TABLE_WYDATKI,
					WydatkiTable.COLUMN_ID
							+ "="
							+ id
							+ (!TextUtils.isEmpty(selection) ? " AND ("
									+ selection + ")" : ""), selectionArgs);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (urimatcher.match(uri)) {
		case WYDATKI:
			return "vnd.android.cursor.dir/com.example.mateusz.czyszkiewicz.wydatki";

		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = db.insert(WydatkiTable.TABLE_WYDATKI, "", values);
		if (rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(WYDATKI_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			Log.d("Wprowadzenie danych", "Udalo sie wprowadzic dane");
			return _uri;
		}
		throw new SQLException("nie udalo sie dodac rekordu");
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(WydatkiTable.TABLE_WYDATKI);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, null, selection, selectionArgs, null, null,
				sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sUriMatcher.match(uri);
		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		int rowsupdated;
		switch (uriType) {

		case WYDATKI:
			rowsupdated = sqlDB.update(WydatkiTable.TABLE_WYDATKI, values,
					selection, selectionArgs);

			break;
		case WYDATKI_ID:
			String id = uri.getPathSegments().get(1);
			rowsupdated = sqlDB.update(WydatkiTable.TABLE_WYDATKI, values,
					"_id="
							+ id
							+ (!TextUtils.isEmpty("where") ? " AND ("
									+ selection + ")" : ""), selectionArgs);

			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsupdated;

	}
}
