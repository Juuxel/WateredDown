/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SandBlock.class)
@Implements(@Interface(iface = Waterloggable.class, prefix = "watereddown$"))
public abstract class SandMixin extends BlockMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(int var1, Block.Settings var2, CallbackInfo info) {
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        var1.with(Properties.WATERLOGGED);
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        try {
            FluidState state = context.getWorld().getFluidState(context.getBlockPos());
            info.setReturnValue(info.getReturnValue().with(Properties.WATERLOGGED, state.matches(FluidTags.WATER)));
        } catch (NullPointerException e) {}
    }
}
