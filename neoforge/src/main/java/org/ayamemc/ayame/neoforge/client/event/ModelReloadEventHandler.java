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

package org.ayamemc.ayame.neoforge.client.event;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.resource.ContextAwareReloadListener;
import org.ayamemc.ayame.client.api.PlayerModelAPI;
import org.ayamemc.ayame.client.util.ModelResourceWriterUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ModelReloadEventHandler extends ContextAwareReloadListener {
    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        CompletableFuture<Void> modelReloadTask = CompletableFuture.runAsync(() -> {
            PlayerModelAPI.getCache().forEach((player, cacheEntry) -> {
                ModelResourceWriterUtil.addModelResource(cacheEntry);
            });
        }, gameExecutor);

        // 使用 preparationBarrier 来确保准备阶段结束后再进入应用阶段
        return modelReloadTask.thenCompose(preparationBarrier::wait);
    }
}
