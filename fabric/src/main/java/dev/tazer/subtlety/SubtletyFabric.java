package dev.tazer.subtlety;

import net.fabricmc.api.ClientModInitializer;

public class SubtletyFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Subtlety.init();
    }
}
