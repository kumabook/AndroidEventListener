package com.kumabook.test;

import com.kumabook.EventListener;
import com.kumabook.test.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity{
	private final static String TAG = "SecondActivity";
	Button backButton;
	Button notifyButton;
	TextView peopleNameTextView;
	EditText nameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		initializeLayout();
		run();
	}
	private void initializeLayout() {
		backButton = (Button) findViewById(R.id.next_button);
		notifyButton = (Button) findViewById(R.id.notify_button);
		peopleNameTextView = (TextView) findViewById(R.id.people_name);
		nameEditText = (EditText) findViewById(R.id.name_input);
	}
	public void run() {
		final DemoApplication demoApp = (DemoApplication) this.getApplication();
		peopleNameTextView.setText(demoApp.people.name.get());
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		notifyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				demoApp.people.name.set(nameEditText.getText().toString());
			}
		});
		demoApp.people.name.addListener(new EventListener<String>(){
			@Override
			public void listen(String name) {
				peopleNameTextView.setText(name);
				Log.d(TAG, "notify people name change event " + name);
			}
		});
	}
}
