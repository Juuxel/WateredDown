/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public World world;

    @Shadow public abstract BlockPos getPos();

    @Inject(method = "isTouchingLava", at = @At("RETURN"), cancellable = true)
    private void isTouchingLava(CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValue()) {
            BlockState state = world.getBlockState(getPos());
            info.setReturnValue(state.getBlock() instanceof CauldronBlock &&
                    state.get(FluidProperty.VANILLA_FLUIDS).getFluid() == Fluids.LAVA);
        }
    }
}
