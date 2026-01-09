package dev.tazer.subtlety.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.tazer.subtlety.Hooks;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class GuiMixin {
    //? >1.20.1 {
    @WrapOperation(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getArmorValue()I"))
    private static int getArmorValue(Player instance, Operation<Integer> original) {
        return Hooks.getArmorValue(instance, original);
    }
    //?} else {
    /*@WrapOperation(method = "renderPlayerHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getArmorValue()I"))
    private int getArmorValue(Player instance, Operation<Integer> original) {
        return Hooks.getArmorValue(instance, original);
    }
    *///?}

}
