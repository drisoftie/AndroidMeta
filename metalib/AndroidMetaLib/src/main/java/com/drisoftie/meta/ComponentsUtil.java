/*****************************************************************************
 *
 * Copyright 2012-2013 Sony Corporation
 *
 * The information contained here-in is the property of Sony corporation and
 * is not to be disclosed or used without the prior written permission of
 * Sony corporation. This copyright extends to all media in which this
 * information may be preserved including magnetic storage, computer
 * print-out or visual display.
 *
 * Contains proprietary information, copyright and database rights Sony.
 * Decompilation prohibited save as permitted by law. No using, disclosing,
 * reproducing, accessing or modifying without Sony prior written consent.
 *
 ****************************************************************************/
package com.drisoftie.meta;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

/**
 * Utility for working with Android components.
 *
 * @author Alexander Dridiger
 */
public class ComponentsUtil {

    /**
     * Changes the {@link ComponentState} of components named by {@code names}. A name is provided by the component's
     * {@link Class#getName()} method.
     *
     * @param context the Android context
     * @param state   the state to apply
     * @param names   use {@link Class#getName()}
     */
    public static void changeComponentsState(Context context, ComponentState state, String... names) {
        ComponentName comp;
        for (String name : names) {
            comp = new ComponentName(context, name);
            int newState = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT;
            switch (state) {
                case ENABLED:
                    newState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
                    break;
                case DISABLED:
                    newState = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                    break;
            }
            context.getPackageManager().setComponentEnabledSetting(comp, newState, PackageManager.DONT_KILL_APP);
        }
    }

    /**
     * Helper delegate method for {@link #changeComponentsState(Context, ComponentState, String...)}.
     *
     * @param context the Android context
     * @param state   the state to apply
     * @param names   use {@link Class#getName()}
     */
    public static void changeComponentsState(Context context, ComponentState state, List<String> names) {
        changeComponentsState(context, state, names.toArray(new String[names.size()]));
    }

    /**
     * Enable the components named by {@code names}. A name is provided by the component's {@link Class#getName()} method.
     *
     * @param context the Android context
     * @param names   use {@link Class#getName()}
     */
    public static void enableComponents(Context context, String... names) {
        changeComponentsState(context, ComponentState.ENABLED, names);
    }

    /**
     * Disable the components named by {@code names}. A name is provided by the component's {@link Class#getName()} method.
     *
     * @param context the Android context
     * @param names   use {@link Class#getName()}
     */
    public static void disableComponents(Context context, String... names) {
        changeComponentsState(context, ComponentState.DISABLED, names);
    }

    /**
     * Returns the {@link ComponentState} of the component found by its {@code name}. A name is provided by the component's
     * {@link Class#getName()} method. Returns {@code null} if the state could not be determined.
     *
     * @param context the Android context
     * @param name    use {@link Class#getName()}
     * @return {@link ComponentState} or {@code null}
     */
    public static ComponentState getComponentState(Context context, String name) {
        ComponentName comp = new ComponentName(context, name);
        int state = context.getPackageManager().getComponentEnabledSetting(comp);
        switch (state) {
            case PackageManager.COMPONENT_ENABLED_STATE_DISABLED:
            case PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER:
            case PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED:
                return ComponentState.DISABLED;
            case PackageManager.COMPONENT_ENABLED_STATE_ENABLED:
                return ComponentState.ENABLED;
        }
        return null;
    }

    /**
     * Searches for all installed apps and returns their application name, package name and icon.
     *
     * @param context the Android context
     * @return list of all {@link AppInfo}s
     */
    public synchronized static List<AppInfo> searchInstalledApps(Context context) {
        List<AppInfo> result = new ArrayList<>();

        // search for all android apps on device
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pM = context.getPackageManager();
        List<ResolveInfo> pkgAppsList = pM.queryIntentActivities(mainIntent, 0);
        for (ResolveInfo resolveInfo : pkgAppsList) {
            ApplicationInfo appInfo = resolveInfo.activityInfo.applicationInfo;

            // And then you retrieve all needed data:
            Drawable packageIcon = pM.getApplicationIcon(appInfo); // Icon
            String packageName = appInfo.packageName; // Package name
            String packageLabel = String.valueOf(pM.getApplicationLabel(appInfo)); // Package appName(app name)
            result.add(new AppInfo(packageLabel, packageName, packageIcon));
        }
        return result;
    }
}
