package com.kumabook;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import android.os.Handler;


public class EventSource<T> {

    List<Tuple<T>> list = new ArrayList<Tuple<T>>();

    final public void addListener(EventListener<T> listener) {
        list.add((new Tuple<T>(listener, new Handler())));
    }

    final public void notify(final T value) {
        for (int i = 0; i < list.size(); i++) {
            final Tuple<T> t = list.get(i);
            if (t.listenerReference.get() == null) {
                list.remove(i);
                i--;
                continue;
            }
            t.handler.post(new Runnable(){
                @Override
                public void run() {
                    if (t.listenerReference.get() != null) {
                        t.listenerReference.get().listen(value);
                    }
                }
            });
        }
    }

    private final static class Tuple<T> {
        public final WeakReference<EventListener<T>> listenerReference;
        public final Handler handler;
        public Tuple(EventListener<T> listener, Handler handler) {
            this.listenerReference = new WeakReference<EventListener<T>>(listener);
            this.handler = handler;
        }
    }
}
