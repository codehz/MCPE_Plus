package plus.mcpe.mcpe_plus;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainApplication extends Application {
    static private MainApplication self;

    public static MainApplication getInstance() {
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
}
