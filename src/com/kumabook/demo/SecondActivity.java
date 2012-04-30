package com.kumabook.demo;

import com.kumabook.EventListener;
import com.kumabook.R;
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
	TextView logTextView;
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
		logTextView = (TextView) findViewById(R.id.log);
		peopleNameTextView = (TextView) findViewById(R.id.people_name);
		nameEditText = (EditText) findViewById(R.id.name_input);
	}
	public void run() {
		final DemoApplication demoApp = (DemoApplication) this.getApplication();
		peopleNameTextView.setText("He is " + demoApp.people.name.get() + ".");
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
			public void listen(String t) {
				peopleNameTextView.setText("He is " + t + ".");
				Log.d(TAG, "notify people name change event " + t);
				logTextView.setText(logTextView.getText() + "\nname is changed " + t);
			}
		});
	}
}
