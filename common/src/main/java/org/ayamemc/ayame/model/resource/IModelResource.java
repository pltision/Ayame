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

package org.ayamemc.ayame.model.resource;

import net.minecraft.resources.ResourceLocation;
import org.ayamemc.ayame.model.AyameModelCache;
import org.ayamemc.ayame.model.DefaultModelType;
import org.ayamemc.ayame.model.ModelType;
import org.ayamemc.ayame.model.IndexData;
import org.ayamemc.ayame.util.JsonInterpreter;
import org.ayamemc.ayame.util.TODO;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import static org.ayamemc.ayame.util.ResourceLocationHelper.withAyameNamespace;

public interface IModelResource {
    // TODO 完成
    IndexData.ModelMetaData getMetaData();
    default String getName(){
        return getMetaData().name();
    }

    List<IndexData.ModelData> getModels();

    JsonInterpreter getModelJson(IndexData.ModelData model);
    JsonInterpreter getAnimationJson(IndexData.ModelData model);
    InputStream getTexture(IndexData.ModelData model);

    default IndexData.ModelData getDefault() {
        return getModels().getFirst();
    }
    default ResourceLocation createModelResourceLocation(){
        return withAyameNamespace("geo/" + getName() + ".json");
    }
    default ResourceLocation createAnimationResourceLocation(){
        return withAyameNamespace("animations/" + getName() + ".json");
    }
    default ResourceLocation createTextureResourceLocation(){
        return withAyameNamespace("textures/" + getName() + ".png");
    }

    static IModelResource fromFile(File file){
        throw new TODO("create model from file");
    }
}
