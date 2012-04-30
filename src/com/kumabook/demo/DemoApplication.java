package com.kumabook.demo;

import com.kumabook.Property;
import android.app.Application;

public class DemoApplication extends Application {
	public People people;
	static class People {
		public final Property<String> name = new Property<String>();
		public final Property<Integer> age = new Property<Integer>();
	}
}

