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

package org.ayamemc.ayame.util;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.linkfs.LinkFileSystem;
import net.minecraft.server.packs.repository.FolderRepositorySource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackDetector;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.ayamemc.ayame.Ayame;
import org.ayamemc.ayame.model.resource.ModelResourceRegistry;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.ayamemc.ayame.Ayame.LOGGER;

public class ModelContent extends PackDetector<ModelResourceRegistry.ModelFile> {
    public ModelContent(DirectoryValidator validator) {
        super(validator);
    }

    @Nullable
    protected ModelResourceRegistry.ModelFile createZipPack(Path path) {
        FileSystem fileSystem = path.getFileSystem();
        if (fileSystem != FileSystems.getDefault() && !(fileSystem instanceof LinkFileSystem)) {
            Ayame.LOGGER.info("Can't open pack archive at {}", path);
            return null;
        } else {
            return new FilePackResources.FileResourcesSupplier(path);
        }
    }

    protected ModelResourceRegistry.ModelFile createDirectoryPack(Path path) {
        return new PathPackResources.PathResourcesSupplier(path);
    }


}
