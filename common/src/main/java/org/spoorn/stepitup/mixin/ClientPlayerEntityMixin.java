package org.spoorn.stepitup.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.stepitup.config.ModConfig;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    private static float originalStepHeight = -1.0f;

    @Shadow public abstract boolean isSneaking();

    // Step up, should apply before autojump thereby "disabling" autojump
    @Inject(method = "move", at = @At(value = "HEAD"))
    private void enableStepUp(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        Entity thisEntity = ((Entity) (Object) this);
        if (ModConfig.get().enableStepUp && (!this.isSneaking() || ModConfig.get().enableStepUpWhenSneaking)) {
            if (originalStepHeight < 0) {
                originalStepHeight = thisEntity.getStepHeight();
            }
            thisEntity.setStepHeight(1.25f);
        } else if (originalStepHeight >= 0) {
            thisEntity.setStepHeight(originalStepHeight);
        }
    }
    
    @Inject(method = "shouldAutoJump", at = @At(value = "HEAD"), cancellable = true)
    private void disableAutoJump(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().enableStepUp && (!this.isSneaking() || ModConfig.get().enableStepUpWhenSneaking)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
