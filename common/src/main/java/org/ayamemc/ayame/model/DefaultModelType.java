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


import net.minecraft.resources.ResourceLocation;

/**
 * 默认模型类型，适用于Ayame模型
 *
 * @param geoModel  模型文件
 * @param animation 动画文件
 * @param texture   贴图文件
 * @param metaData  模型元数据
 */

public record DefaultModelType(ResourceLocation geoModel, ResourceLocation animation, ResourceLocation texture,
                               IndexData.ModelMetaData metaData) implements ModelType {


    @Override
    public ResourceLocation getGeoModel() {
        return geoModel;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public ResourceLocation getAnimation() {
        return animation;
    }

    @Override
    public IndexData.ModelMetaData metaData() {
        return metaData;
    }

    public static class Builder {
        private ResourceLocation geoModel;
        private ResourceLocation animation;
        private ResourceLocation texture;
        private IndexData.ModelMetaData metaData;

        public static Builder create() {
            return new Builder();
        }

        public Builder setGeoModel(ResourceLocation geoModel) {
            this.geoModel = geoModel;
            return this;
        }

        public Builder setAnimation(ResourceLocation animation) {
            this.animation = animation;
            return this;
        }

        public Builder setTexture(ResourceLocation texture) {
            this.texture = texture;
            return this;
        }

        public Builder setMetaData(IndexData.ModelMetaData metaData) {
            this.metaData = metaData;
            return this;
        }

        public DefaultModelType build() {
            return new DefaultModelType(geoModel, animation, texture, metaData);
        }
    }
}
