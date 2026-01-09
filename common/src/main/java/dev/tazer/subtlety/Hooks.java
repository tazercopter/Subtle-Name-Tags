package dev.tazer.subtlety;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3fc;

public class Hooks {
    public static int getArmorValue(Player player, Operation<Integer> original) {
        return Config.INSTANCE.SHOW_ARMOR.value() ? original.call(player) : 0;
    }

    public static boolean showName(Entity entity, EntityRenderDispatcher entityRenderDispatcher) {
        Config.NameTags.HeadNameTags config = Config.INSTANCE.NAME_TAGS.HEAD_NAME_TAGS;
        Camera activeCamera = entityRenderDispatcher.camera;
        //? < 26.1 {
        /*Entity cameraEntity = activeCamera.getEntity();
        Vec3 cameraPosition = activeCamera.getPosition();
        *///?} else {
        Entity cameraEntity = activeCamera.entity();
        Vec3 cameraPosition = activeCamera.position();
        //?}
        Vec3 targetPosition = entity.getPosition(0);

        double actualDistanceSqr = cameraPosition.distanceToSqr(targetPosition);
        double maxDistance = Mth.square(config.NAME_RADIUS.value());
        if (activeCamera.isDetached()) maxDistance += 25;
        if (cameraEntity.is(entity) || entity.isDiscrete() || actualDistanceSqr > maxDistance) {
            return false;
        }

        boolean canRender = true;

        if (config.UNOBSTRUCTED_VIEW.value()) {
            double nameTagYOffset = entity.getBbHeight() / 2;
            Vec3 nameTagPosition = targetPosition.add(0, nameTagYOffset, 0);

            //? < 26.1 {
            /*Vector3f cameraLook = activeCamera.getLookVector();
            *///?} else {
            Vector3fc cameraLook = activeCamera.forwardVector();
            //?}
            Vec3 cameraLookVec3 = new Vec3(cameraLook.x(), cameraLook.y(), cameraLook.z());
            double dotProduct = nameTagPosition.subtract(cameraPosition).normalize().dot(cameraLookVec3);
            if (config.LINE_OF_SIGHT.value() && dotProduct <= 0.9) {
                canRender = false;
            } else {
                HitResult hitResult = entity.level().clip(new ClipContext(
                        cameraPosition,
                        nameTagPosition,
                        ClipContext.Block.VISUAL,
                        ClipContext.Fluid.NONE,
                        cameraEntity
                ));

                canRender = hitResult.getType() == HitResult.Type.MISS;
            }
        }

        return canRender;
    }
}
