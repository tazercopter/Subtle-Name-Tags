package dev.tazer.subtlety.mixin;

import dev.tazer.subtlety.Config;
import dev.tazer.subtlety.Hooks;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Shadow
    @Final
    protected EntityRenderDispatcher entityRenderDispatcher;

    //? <26.1 {
    /*@Inject(method = "shouldShowName", at = @At("RETURN"), cancellable = true)
    private void shouldShowName(T entity, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && !Config.INSTANCE.NAME_TAGS.HEAD_NAMES.value()) {
            cir.setReturnValue(Hooks.showName(entity, entityRenderDispatcher));
        }
    }
    *///?} else {
    @Inject(method = "shouldShowName", at = @At("RETURN"), cancellable = true)
    private void shouldShowName(T entity, double distanceToCameraSq, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && !Config.INSTANCE.NAME_TAGS.HEAD_NAMES.value()) {
            cir.setReturnValue(Hooks.showName(entity, entityRenderDispatcher));
        }
    }
    //?}
}
