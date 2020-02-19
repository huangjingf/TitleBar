package utils;

import android.app.Instrumentation;
import android.util.Log;

public class CustomFuncManager {

    private static CustomFuncManager mInstance;

    private Instrumentation instrumentation = new Instrumentation();

    public static CustomFuncManager getInstance() {
        if (mInstance == null) {
            mInstance = new CustomFuncManager();

        }
        return mInstance;
    }

    public void sendKeyEvent(final int keyCode) {
        Thread thread;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (instrumentation != null) {
                    instrumentation.sendKeyDownUpSync(keyCode);
                } else {
                    Log.d("zw", "instrumentation is null");
                }
            }
        });
        thread.start();
    }
}
