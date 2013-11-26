package com.huey.yina;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlanDbAdapter {

	public static final String KEY_CONTENTS = "contents";
	public static final String KEY_ROWID = "_id";
	private static final String TAG = "PlanDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE = "create table plan (_id integer primary key, "
			+ "contents text not null);";
	private static final String DATABASE_NAME = "YINADB";
	private static final String DATABASE_TABLE = "plan";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d("database", "oncreate");
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS consumer_posts");
			onCreate(db);
		}
	}

	public PlanDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public PlanDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long addPlan(String contents) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CONTENTS, contents);
		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deletePost(long rowId) {

		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public boolean deleteAll(){
		return mDb.delete(DATABASE_TABLE, null, null)>0;
	}
	public ArrayList<Plan> fetchAllPlan() {
		ArrayList<Plan> planList = new ArrayList<Plan>();
		Cursor cursor = mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_CONTENTS }, null, null,
				null, null, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Plan plan = new Plan();
			plan.setRow_id(cursor.getInt(cursor.getColumnIndex(KEY_ROWID)));
			plan.setContents(cursor.getString(cursor.getColumnIndex(KEY_CONTENTS)));
			planList.add(plan);
			cursor.moveToNext();
		}
		return planList;
	}
}
