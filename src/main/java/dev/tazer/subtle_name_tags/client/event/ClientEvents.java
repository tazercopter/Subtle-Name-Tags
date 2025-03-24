package dev.tazer.subtle_name_tags.client.event;

import dev.tazer.subtle_name_tags.SNTConfig;
import dev.tazer.subtle_name_tags.SubtleNameTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
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

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SubtleNameTags.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        Entity entity = event.getEntity();

        if (entity.shouldShowName()) {
            boolean canRender = false;
            EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
            Entity cameraEntity = entityRenderDispatcher.camera.getEntity();
            Vec3 cameraPosition = entityRenderDispatcher.camera.getPosition();

            if (!cameraEntity.is(entity) && !entity.isDiscrete() && cameraEntity.distanceTo(entity) < SNTConfig.NAME_RADIUS.get()) {
                if (SNTConfig.REQUIRE_UNOBSTRUCTED_VIEW.get()) {
                    Vec3 entityPosition = entity.getEyePosition();
                    Vector3f lookVector = entityRenderDispatcher.camera.getLookVector();

                    if (entityPosition.subtract(cameraPosition).normalize().dot(new Vec3(lookVector.x, lookVector.y, lookVector.z)) > 0.9) {
                        HitResult hit = entity.level().clip(new ClipContext(cameraPosition, entityPosition, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity));

                        if (hit.getType() == HitResult.Type.MISS)
                            canRender = true;
                    }
                } else canRender = true;
            }

            event.setCanRender(canRender ? TriState.TRUE : TriState.FALSE);
        }
    }
}
