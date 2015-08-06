package plus.mcpe.mcpe_plus;

import android.app.Application;

public class MainApplication extends Application {
    static private MainApplication self;

    public static MainApplication getInstance() {
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }
}
