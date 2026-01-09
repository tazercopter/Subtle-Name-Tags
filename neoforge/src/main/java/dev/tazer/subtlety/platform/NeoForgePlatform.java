package dev.tazer.subtlety.platform;

import dev.tazer.subtlety.platform.services.Platform;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoForgePlatform implements Platform {
    @Override
    public Path configPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
