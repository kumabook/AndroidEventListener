package com.kumabook.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.kumabook.EventListener;
import com.kumabook.test.R;
import com.kumabook.test.DemoApplication.People;

public class FirstActivity extends Activity {
	private final static String TAG = "FirstActivity";
	Button nextButton;
	Button notifyButton;
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
			public void listen(String name) {
				Log.d(TAG, "notify people name change event " + name);
				peopleNameTextView.setText(name);
			}
		});
		p.name.set("taro");
		nameEditText.setText("taro", BufferType.NORMAL);
	}
}
