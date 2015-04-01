/* Copyright [2013-2014] [Alexander Dridiger]
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.drisoftie.meta;

import java.util.Comparator;

/**
 * @author Alexander Dridiger
 */
public class AppInfoComparator implements Comparator<AppInfo> {

    @Override
    public int compare(AppInfo app1, AppInfo app2) {
        int res = String.CASE_INSENSITIVE_ORDER.compare(app1.appName.toString(), app2.appName.toString());
        return (res != 0) ? res : app1.appName.toString().compareTo(app2.appName.toString());
    }
}
