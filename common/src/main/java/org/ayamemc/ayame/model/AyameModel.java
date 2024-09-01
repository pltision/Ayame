/*
 *      Custom player model mod. Powered by GeckoLib.
 *      Copyright (C) 2024  CrystalNeko, HappyRespawnanchor, pertaz(Icon Desiger)
 *
 *      This file is part of Ayame.
 *
 *     Ayame is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Ayame is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License along with Ayame. If not, see <https://www.gnu.org/licenses/>.
 */

package org.ayamemc.ayame.model;

import net.minecraft.resources.ResourceLocation;

import static org.ayamemc.ayame.Ayame.MOD_ID;

/**
 * Ayame通用模型接口，适用于所有Geo模型
 * 所有模型必须先加载到内存中
 * 路径&命名标准：
 * Geo模型文件 -> assets/ayame/geo/{模型类型}/{模型名称}.json
 * 纹理文件 -> assets/ayame/textures/{模型类型}/{模型名称}.png
 * 动画文件 -> assets/ayame/animations/{模型类型}/{模型名称}.json
 * 模型元数据文件 -> assets/ayame/model_metadata/{模型类型}/{模型名称}.json
 * // 注：模型类型指的是使用了哪种模型，例如ayame的模型类型为"ayame"，兼容ysm的为"ysm"，值与ModelMetaData中的type()的值对应
 * // 无论何种格式，元数据文件必须转为Ayame的元数据文件格式
 */

public interface AyameModel {
    default ResourceLocation getGeoModel() {
        return ResourceLocation.fromNamespaceAndPath(
                MOD_ID,
                "geo/" + metaData().type() + "/" + metaData().name() + ".json"
        );
    }

    default ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(
                MOD_ID,
                "textures/" + metaData().type() + "/" + metaData().name() + ".png"
        );
    }

    default ResourceLocation getAnimation() {
        return ResourceLocation.fromNamespaceAndPath(
                MOD_ID,
                "animations/" + metaData().type() + "/" + metaData().name() + ".json"
        );
    }

    ModelMetaData metaData();
}
