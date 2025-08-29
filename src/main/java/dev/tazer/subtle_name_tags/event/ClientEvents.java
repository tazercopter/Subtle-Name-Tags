package dev.tazer.subtle_name_tags.event;

import dev.tazer.subtle_name_tags.SNTConfig;
import dev.tazer.subtle_name_tags.SubtleNameTags;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;
import org.joml.Vector3f;

@EventBusSubscriber(modid = SubtleNameTags.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        Entity targetEntity = event.getEntity();

        if (!targetEntity.shouldShowName()) {
            return;
        }

        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        Camera activeCamera = entityRenderDispatcher.camera;
        Entity cameraEntity = activeCamera.getEntity();
        Vec3 cameraPosition = activeCamera.getPosition();
        Vec3 targetPosition = targetEntity.getPosition(event.getPartialTick());

        double actualDistanceSqr = cameraPosition.distanceToSqr(targetPosition);
        double maxDistance = Mth.square(SNTConfig.NAME_RADIUS.get());
        if (activeCamera.isDetached()) maxDistance += 25;
        if (cameraEntity.is(targetEntity) || targetEntity.isDiscrete() || actualDistanceSqr > maxDistance) {
            return;
        }

        boolean canRender = true;

        if (SNTConfig.REQUIRE_UNOBSTRUCTED_VIEW.get()) {
            double nameTagYOffset = targetEntity.getBbHeight() / 2;
            Vec3 nameTagPosition = targetPosition.add(0, nameTagYOffset, 0);

            Vector3f cameraLook = activeCamera.getLookVector();
            Vec3 cameraLookVec3 = new Vec3(cameraLook.x, cameraLook.y, cameraLook.z);
            double dotProduct = nameTagPosition.subtract(cameraPosition).normalize().dot(cameraLookVec3);
            if (SNTConfig.REQUIRE_LINE_OF_SIGHT.get() && dotProduct <= 0.9) {
                canRender = false;
            } else {
                HitResult hitResult = targetEntity.level().clip(new ClipContext(
                        cameraPosition,
                        nameTagPosition,
                        ClipContext.Block.VISUAL,
                        ClipContext.Fluid.NONE,
                        cameraEntity
                ));

                canRender = hitResult.getType() == HitResult.Type.MISS;
            }
        }

        event.setCanRender(canRender ? TriState.TRUE : TriState.FALSE);
    }
}
