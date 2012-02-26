package com.kumabook.demo;

import com.kumabook.EventListener;
import com.kumabook.Property;

public class Demo {
	public void run() {
		People p = new People();
		p.name.addListener(new EventListener<String>(){
			@Override
			public void listen(String t) {
				System.out.println("name " + t + " is set.");
			}
		});
		p.name.set("taro");
	}
	
	static class People {
		public final Property<String> name = new Property<String>();
		public final Property<Integer> age = new Property<Integer>();
	}
}
