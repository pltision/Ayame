/*
 *     Custom player model mod. Powered by GeckoLib.
 *     Copyright (C) 2024  CrystalNeko, HappyRespawnanchor, pertaz(Icon Designer)
 *
 *     This file is part of Ayame.
 *
 *     Ayame is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Ayame is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with Ayame.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.ayamemc.ayame.model;

import static org.ayamemc.ayame.util.ResourceLocationHelper.withAyameNamespace;

public class DefaultModels {
    public static final String MODEL_PATH = "config/ayame/models/";
    public static final AyameModelType DEFAULT_MODEL = DefaultAyameModelType.of(
            withAyameNamespace("geo/ayame/default"),
            withAyameNamespace("animations/ayame/default"),
            withAyameNamespace("textures/ayame/default"),
            withAyameNamespace("metadata/ayame/default")
    );
}
