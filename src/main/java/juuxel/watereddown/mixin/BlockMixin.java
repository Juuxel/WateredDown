/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// 1001 to skip Towelette's overwrite
// Virtuoel, if you're reading this, you should not overwrite that >:(
@Mixin(value = Block.class, priority = 1001)
public abstract class BlockMixin {
    @Shadow
    StateFactory<Block, BlockState> stateFactory;

    @Shadow
    abstract BlockState getDefaultState();

    @Shadow
    abstract void setDefaultState(BlockState var1);

    @Inject(at = @At("RETURN"), method = "getPlacementState", cancellable = true)
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    protected void appendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
    }

    @Inject(at = @At("RETURN"), method = "getLuminance", cancellable = true)
    protected void getLuminance(BlockState state, CallbackInfoReturnable<Integer> info) {
    }

    @Inject(method = "getFluidState", at = @At("HEAD"), cancellable = true)
    private void getFluidState(BlockState state, CallbackInfoReturnable<FluidState> info) {
        if (stateFactory.getProperties().contains(FluidProperty.FLUID) &&
                state.get(FluidProperty.FLUID).getFluid() instanceof BaseFluid) {
            info.setReturnValue(((BaseFluid) state.get(FluidProperty.FLUID).getFluid()).getState(false));
            info.cancel();
        }
    }
}
