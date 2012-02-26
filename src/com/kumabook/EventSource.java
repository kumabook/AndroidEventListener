package com.kumabook;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import android.os.Handler;


public class EventSource<T> {
	
	List<WeakReference<Tuple<T>>> list = new ArrayList<WeakReference<Tuple<T>>>();
	
	final public void addListener(EventListener<T> listener) {
		list.add(new WeakReference<EventSource.Tuple<T>>(new Tuple<T>(listener, new Handler())));
	}
	
	final public void notify(final T value) {
		for (int i = 0; i < list.size(); i++) {
			final Tuple<T> t = list.get(i).get();
			if (t == null) {
				list.remove(i);
				i--;
				continue;
			}
			t.handler.post(new Runnable(){
				@Override
				public void run() {
					t.listener.listen(value);
				}
			});
		}
	}
	
	private final static class Tuple<T> {
		public final EventListener<T> listener;
		public final Handler handler;
		public Tuple(EventListener<T> l, Handler h) {
			this.listener = l;
			this.handler = h;
		}
	}
}
