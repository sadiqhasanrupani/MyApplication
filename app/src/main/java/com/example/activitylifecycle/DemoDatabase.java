package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DemoDatabase extends AppCompatActivity {

	EditText editText;
	TextView viewUsers;
	Button insertUserBtn;
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_database);

		editText = (EditText) findViewById(R.id.editText);
		insertUserBtn = (Button) findViewById(R.id.insertUserBtn);
		viewUsers = (TextView) findViewById(R.id.viewUsers);

		db = new DatabaseHelper(getApplicationContext());

		String[] users = db.showUsers();

		StringBuilder gotUsers = new StringBuilder();

		for(String user: users) {
			gotUsers.append(user).append("\n");
		}

		viewUsers.setText(gotUsers);

		insertUserBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = editText.getText().toString().trim();

				db.insertUser(userName);
			}
		});

	}
}