package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterBookInfo extends AppCompatActivity {

	EditText bookName, bookPrice, bookDescription;
	Button insertBook;
	BookShopDatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_book_info);

		bookName = (EditText) findViewById(R.id.bookName);
		bookPrice = (EditText) findViewById(R.id.bookPrice);
		bookDescription = (EditText) findViewById(R.id.bookDescription);

		insertBook = (Button) findViewById(R.id.insertBook);

		db = new BookShopDatabaseHelper(getApplicationContext());

		insertBook.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = bookName.getText().toString().trim();
				Float price = Float.parseFloat(bookPrice.getText().toString().trim());
				String description = bookDescription.getText().toString().trim();

				int result = db.insertBookInfo(name, description, price);

				System.out.println(result);

				if(result == -1) {
					Toast.makeText(EnterBookInfo.this, name + " Not inserted successfully", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(EnterBookInfo.this, name + " is now inserted successfully", Toast.LENGTH_LONG).show();
				}

			}
		});
	}
}