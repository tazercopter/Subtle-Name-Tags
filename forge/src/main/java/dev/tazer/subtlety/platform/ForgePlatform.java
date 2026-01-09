package dev.tazer.subtlety.platform;

import dev.tazer.subtlety.platform.services.Platform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatform implements Platform {
    @Override
    public Path configPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
