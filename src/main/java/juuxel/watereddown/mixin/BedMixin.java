/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import juuxel.watereddown.api.Fluidloggable;
import juuxel.watereddown.util.FluidloggableImpl;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "waterlog$"))
public abstract class BedMixin extends BlockMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(DyeColor color, Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FluidProperty.FLUID, FluidProperty.EMPTY));
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    private void onAppendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        Fluidloggable.onAppendProperties(var1);
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidloggableImpl.onGetPlacementState(context, info);
    }
}
