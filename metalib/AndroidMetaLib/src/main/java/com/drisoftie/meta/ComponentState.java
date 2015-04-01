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

import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Indicator for enabled/-ing or disabled/-ing components.
 *
 * @author Alexander Dridiger
 */
public enum ComponentState {

    /**
     * Indicator for {@link PackageManager#COMPONENT_ENABLED_STATE_ENABLED}.
     */
    ENABLED(PackageManager.COMPONENT_ENABLED_STATE_ENABLED),

    /**
     * Indicator for {@link PackageManager#COMPONENT_ENABLED_STATE_DISABLED},
     * {@link PackageManager#COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED} and {@link
     * PackageManager#COMPONENT_ENABLED_STATE_DISABLED_USER}.<br>
     * <br>
     * <p/>
     * <b>NOTE</b> if using platform below 14,
     */
    DISABLED(
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED, (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) ?
            (PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) : (null), (Build.VERSION.SDK_INT >= Build.VERSION_CODES
            .ICE_CREAM_SANDWICH) ? (PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER) : (null));

    private Integer[] states;

    private ComponentState(Integer... states) {
        // apply filter for null values due to platform being below API 14
        List<Integer> filter = new ArrayList<Integer>();
        for (Integer state : states) {
            if (state != null) {
                filter.add(state);
            }
        }
        this.states = filter.toArray(new Integer[filter.size()]);
    }

    /**
     * Constructs a {@link ComponentState} from an appropriate {@link PackageManager} component state. Returns {@code null} if the type
     * could not be constructed.
     *
     * @param state {@code int} representing the state
     * @return the type constructed from the <b>{@code state}</b>
     */
    public static ComponentState fromData(int state) {
        for (ComponentState compState : values()) {
            for (int value : compState.states) {
                if (value == state) {
                    return compState;
                }
            }
        }
        return null;
    }
}
