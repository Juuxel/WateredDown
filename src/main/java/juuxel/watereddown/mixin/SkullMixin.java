/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import juuxel.watereddown.api.Fluidloggable;
import juuxel.watereddown.util.FluidloggableImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ SkullBlock.class, WallSkullBlock.class })
@Implements(@Interface(iface = Fluidloggable.class, prefix = "waterlog$"))
public abstract class SkullMixin extends BlockMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(SkullBlock.SkullType type, Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FluidProperty.FLUID, FluidProperty.EMPTY));
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    private void onAppendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        Fluidloggable.onAppendProperties(var1);
    }

    @Inject(at = @At("RETURN"), method = "getPlacementState", cancellable = true)
    private void replacePlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidloggableImpl.onGetPlacementState(context, info);
    }
}
