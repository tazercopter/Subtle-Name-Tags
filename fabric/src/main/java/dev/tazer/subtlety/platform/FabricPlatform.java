package dev.tazer.subtlety.platform;

import dev.tazer.subtlety.platform.services.Platform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FabricPlatform implements Platform {
    @Override
    public Path configPath() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
