package tech.paulzzh.gamespacelocale;

import android.content.Context;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals("com.oneplus.gamespace"))
            return;
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        try {
            XC_MethodReplacement hook = new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) {
                    return true;
                }
            };
            findAndHookMethod("com.oneplus.gamespace.c0.w", lpparam.classLoader, "b", Context.class, hook);
        } catch (Throwable t) {
            XposedBridge.log(t);
        }
    }
}