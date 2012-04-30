package com.kumabook;

public class Property<T> {
	private T value;
	private final EventSource<T> source = new EventSource<T>();

	public final T get() { return value; }
	public final void set(T value) { 
		this.value = value;
		source.notify(value);
	}
	public final void addListener(EventListener<T> l) {
		source.addListener(l);
	}
}
