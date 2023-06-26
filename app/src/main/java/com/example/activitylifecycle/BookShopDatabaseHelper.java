package com.example.activitylifecycle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class BookShopDatabaseHelper extends SQLiteOpenHelper {
	public BookShopDatabaseHelper(@Nullable Context context) {
		super(context, "book_db.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "" +
			"CREATE TABLE IF NOT EXISTS books" +
			"(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
			"book_nm VARCHAR(50) NOT NULL," +
			"book_description VARCHAR(255) NOT NULL," +
			"book_price FLOAT NOT NULL)";

		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public int insertBookInfo(String book_nm, String book_description, Float book_price) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues value = new ContentValues();

		value.put("book_nm", book_nm);
		value.put("book_description", book_description);
		value.put("book_price", book_price);

		return (int) db.insert("books", null, value);
	}

	public ArrayList<HashMap<String, String>> showBooks() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor result = db.rawQuery("SELECT * FROM books", null);

		int resultLen = result.getCount();
		ArrayList<HashMap<String, String>> booksArr = new ArrayList<>();

		result.moveToFirst();

		for (int i = 0; i < resultLen; i++) {
			HashMap<String, String> bookMap = new HashMap<>();

			String book_nm = result.getString(result.getColumnIndexOrThrow("book_nm"));
			String book_description = result.getString(result.getColumnIndexOrThrow("book_description"));
			String book_price = result.getString(result.getColumnIndexOrThrow("book_price"));

			// Format the book details as a single string
			bookMap.put("book_nm", book_nm);
			bookMap.put("book_description", book_description);
			bookMap.put("book_price", book_price);

			result.moveToNext();
		}

		result.close();

		return booksArr;
	}

}
