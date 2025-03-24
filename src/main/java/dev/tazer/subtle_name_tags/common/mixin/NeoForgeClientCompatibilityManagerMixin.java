package dev.tazer.subtle_name_tags.common.mixin;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import de.maxhenkel.voicechat.intercompatibility.NeoForgeClientCompatibilityManager;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded(value = "voicechat")
@Mixin(NeoForgeClientCompatibilityManager.class)
public class NeoForgeClientCompatibilityManagerMixin {
    @Inject(method = "onRenderName", at = @At("HEAD"), cancellable = true)
    private void hideVoiceChatIcons(RenderNameTagEvent event, CallbackInfo ci) {
        if (event.canRender() == TriState.FALSE) ci.cancel();
    }
}
