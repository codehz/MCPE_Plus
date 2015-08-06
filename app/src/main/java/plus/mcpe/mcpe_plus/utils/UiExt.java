package plus.mcpe.mcpe_plus.utils;

import android.content.res.Resources;

import java.lang.reflect.Field;

import plus.mcpe.mcpe_plus.MainApplication;

public class UiExt {
    public static int getStatusBarHeight() {
        int ret = 0;
        try {
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            Object object = aClass.newInstance();
            Field field = aClass.getField("status_bar_height");
            Resources resources = MainApplication.getInstance().getResources();
            ret = resources.getDimensionPixelSize(Integer.parseInt(field.get(object).toString()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return ret;
    }
}
