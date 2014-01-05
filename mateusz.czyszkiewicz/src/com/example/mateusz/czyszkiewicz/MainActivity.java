package com.example.mateusz.czyszkiewicz;

import android.net.Uri;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements
		LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter adapter;
	private int pozycja;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getListView().setDividerHeight(2);
		fillData();
		registerForContextMenu(getListView());

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_add: {

			FragmentManager fm = getFragmentManager();
			MyDialog mydial = new MyDialog();
			mydial.show(fm, "dial");
			break;

		}
		case R.id.menu_edit: {
			FragmentManager fm = getFragmentManager();
			EditDialog editdialog = new EditDialog();
			editdialog.position(pozycja);
			editdialog.show(fm, "editdialog");
			break;
		}
		case R.id.menu_remove: {
			pozycja++;
			String where = String.valueOf(pozycja);
			Uri deleteUri = Uri.parse(MyContentProvider.WYDATKI_URI_ID + where);
			getContentResolver().delete(deleteUri, where, null);
			break;

		}

		default:
			return super.onOptionsItemSelected(item);
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {

		String[] columns = { WydatkiTable.COLUMN_ID, WydatkiTable.COLUMN_NAZWA,
				WydatkiTable.COLUMN_TYP, WydatkiTable.COLUMN_WARTOSC,
				WydatkiTable.COLUMN_DATA };
		CursorLoader cursorloader = new CursorLoader(this,
				MyContentProvider.WYDATKI_URI, columns, null, null, null);
		return cursorloader;

	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		adapter.swapCursor(arg1);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);

	}

	public void fillData() {
		String[] from = new String[] { WydatkiTable.COLUMN_NAZWA,
				WydatkiTable.COLUMN_TYP, WydatkiTable.COLUMN_WARTOSC,
				WydatkiTable.COLUMN_DATA };
		int[] to = new int[] { R.id.rowView2, R.id.rowView4, R.id.rowView3,
				R.id.rowView };
		getLoaderManager().initLoader(0, null, this);
		adapter = new SimpleCursorAdapter(this, R.layout.row, null, from, to, 0);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		pozycjoner(position);
		Toast.makeText(getApplicationContext(),
				"Prosze teraz wybrac usuniecie lub edycje", Toast.LENGTH_SHORT)
				.show();
	}

	public void pozycjoner(int pos) {
		pozycja = pos;
	}

}
