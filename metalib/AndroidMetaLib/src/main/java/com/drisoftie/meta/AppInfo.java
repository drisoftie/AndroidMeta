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

import android.graphics.drawable.Drawable;

/**
 * Holder class for an application name, its package name and its icon (if any).<br>
 * <br>
 * Usable for external applications.
 *
 * @author Alexander Dridiger
 */
public class AppInfo {

    /**
     * The application name.
     */
    public CharSequence appName;

    /**
     * The unique Android package name.
     */
    public CharSequence packageName;

    /**
     * The application launcher icon.
     */
    public Drawable icon;

    public AppInfo() {
    }

    /**
     * Convenient constructor.
     *
     * @param appName     the name of the app
     * @param packageName package name
     * @param packageIcon the app icon
     */
    public AppInfo(CharSequence appName, CharSequence packageName, Drawable packageIcon) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = packageIcon;
    }
}
