package org.ayamemc.ayame.util;

import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.linkfs.LinkFileSystem;
import net.minecraft.server.packs.repository.PackDetector;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.ayamemc.ayame.Ayame;
import org.ayamemc.ayame.model.resource.ModelResourceRegistry;
import org.jetbrains.annotations.Nullable;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class ModelContent extends PackDetector<ModelResourceRegistry.ModelFile> {

    public ModelContent(DirectoryValidator validator) {
        super(validator);
    }

    @Nullable
    @Override
    protected ModelResourceRegistry.ModelFile createZipPack(Path path) {
        try (ZipFile zipFile = new ZipFile(path.toFile())) {
            ZipEntry entry = zipFile.getEntry("index.json");
            if (entry != null) {
                return new ModelResourceRegistry.ModelFile();
            }
        } catch (IOException e) {
            Ayame.LOGGER.error("Failed to read ZIP pack at path: {}", path, e);
        }
        return null;
    }

    @Override
    protected ModelResourceRegistry.ModelFile createDirectoryPack(Path path) {
        Path indexFilePath = path.resolve("index.json");
        if (Files.exists(indexFilePath)) {
            return new ModelResourceRegistry.ModelFile();
        }
        return null;
    }
}
