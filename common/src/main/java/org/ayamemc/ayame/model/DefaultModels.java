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

import org.ayamemc.ayame.model.resource.IModelResource;
import org.ayamemc.ayame.model.resource.ModelContent;
import org.ayamemc.ayame.model.resource.ModelResourceRegistry;
import org.ayamemc.ayame.util.FileUtil;

import java.nio.file.Path;
import java.util.List;

import static org.ayamemc.ayame.util.ResourceLocationHelper.withAyameNamespace;

public class DefaultModels {
    public static final String MODEL_PATH = "config/ayame/models/";
    public static final AyameModelType DEFAULT_MODEL = DefaultAyameModelType.Builder.create()
            .setGeoModel(withAyameNamespace("geo/ayame/default"))
            .setAnimation(withAyameNamespace("animations/ayame/default"))
            .setTexture(withAyameNamespace("textures/ayame/default"))
            .setMetaData(IndexData.ModelMetaData.Builder.create()
                    .setName("default")
                    .setAuthors(new String[]{"CrystalNeko"})
                    .setDescription("Default model for Ayame")
                    .setVersion("0.0.1")
                    .build()
            )
            .build();

    public static final IModelResource AQUARTER_NEKO_RESOURCE = create("AQuarter_neko");

    // 静态初始化
    public static void init(){}

    private static IModelResource create(String name) {
        Path targetPath = Path.of(MODEL_PATH + name + ".zip");
        FileUtil.copyResource("assets/ayame/models/"+name+".zip", targetPath);
        return ModelResourceRegistry.create(ModelContent.create().createZipPack(targetPath));
    }
}
