package com.kumabook.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kumabook.EventListener;
import com.kumabook.R;
import com.kumabook.demo.DemoApplication.People;

public class FirstActivity extends Activity {
	private final static String TAG = "FirstActivity";
	Button nextButton;
	Button notifyButton;
	TextView logTextView;
	TextView peopleNameTextView;
	EditText nameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		initializeLayout();
		run();
	}
	private void initializeLayout() {
		nextButton = (Button) findViewById(R.id.next_button);
		notifyButton = (Button) findViewById(R.id.notify_button);
		logTextView = (TextView) findViewById(R.id.log);
		peopleNameTextView = (TextView) findViewById(R.id.people_name);
		nameEditText = (EditText) findViewById(R.id.name_input);
	}
	public void run() {
		final People p = new People();
		DemoApplication demoApp = (DemoApplication) this.getApplication();
		demoApp.people = p;
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(FirstActivity.this, SecondActivity.class);
				startActivity(i);
				System.gc();
			}
		});
		notifyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				p.name.set(nameEditText.getText().toString());
			}
		});
		p.name.addListener(new EventListener<String>(){
			@Override
			public void listen(String t) {
				Log.d(TAG, "notify people name change event " + t);
				logTextView.setText(logTextView.getText() + "\nname is changed " + t);
				peopleNameTextView.setText("He is " + t + ".");
			}
		});
		p.name.set("taro");
	}
}
