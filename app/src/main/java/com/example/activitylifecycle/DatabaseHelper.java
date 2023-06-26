package com.example.activitylifecycle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(@Nullable Context context) {
		super(context, "practice_db.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE users (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, user_name VARCHAR(255) NOT NULL)";

		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public int insertUser(String user_name) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value = new ContentValues();

		value.put("user_name", user_name);
		return (int) db.insert("users", null, value);
	}

	public String[] showUsers() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor result = db.rawQuery("SELECT * FROM users", null);

		int length = result.getCount();
		String[] users = new String[length];

		result.moveToFirst();

		for(int i  = 0; i < result.getCount(); i++) {
			users[i] = result.getString(result.getColumnIndexOrThrow("user_name"));

			result.moveToNext();
		}

		result.close();
		return users;

	}
}
