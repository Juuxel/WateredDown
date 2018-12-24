/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "virtuoel.towelette.api.FluidProperty", remap = false)
public class ToweletteMixin {
    @Inject(method = "getFluid", at = @At("HEAD"), cancellable = true)
    private void onGetFluid(BlockState state, CallbackInfoReturnable<Fluid> info) {
        if (state.contains(FluidProperty.FLUID)) {
            info.setReturnValue(state.get(FluidProperty.FLUID).getFluid());
            info.cancel();
        }
    }
}
