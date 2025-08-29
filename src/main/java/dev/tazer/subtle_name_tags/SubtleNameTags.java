package dev.tazer.subtle_name_tags;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(value = SubtleNameTags.MODID, dist = Dist.CLIENT)
public class SubtleNameTags {
    public static final String MODID = "subtle_name_tags";

    public SubtleNameTags(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, SNTConfig.CLIENT_CONFIG);
    }
}