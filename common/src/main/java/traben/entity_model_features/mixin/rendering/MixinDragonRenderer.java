package traben.entity_model_features.mixin.rendering;

import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import traben.entity_model_features.utils.EMFManager;
import traben.entity_texture_features.features.ETFRenderContext;

@Mixin(EnderDragonRenderer.class)
public abstract class MixinDragonRenderer {

    @Inject(method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/EnderDragonRenderer$DragonModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V",
            shift = At.Shift.BEFORE, ordinal = 2))
    private void emf$allowMultiPartRender(final CallbackInfo ci) {
        ETFRenderContext.startSpecialRenderOverlayPhase();
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EnderDragonRenderer$DragonModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V",
                    shift = At.Shift.AFTER, ordinal = 2))
    private void emf$allowMultiPartRender2(final CallbackInfo ci) {
        ETFRenderContext.endSpecialRenderOverlayPhase();
    }

}
