package com.example.activitylifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogBox extends AppCompatActivity {

	// defining the Layout classes
	Button dialogBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_dialog);

		dialogBtn = (Button) findViewById(R.id.dialogBtn);

		dialogBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogBox.this);
				builder.setTitle("Alert box");
				builder.setMessage("This is a alert box bruh");

				builder.setCancelable(false);

				builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AlertDialogBox.this, "You clicked on Okay button", Toast.LENGTH_LONG).show();

						dialog.cancel();
					}
				});

				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AlertDialogBox.this, "You clicked on Cancel button", Toast.LENGTH_LONG).show();

						dialog.cancel();
					}
				});

				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}
}