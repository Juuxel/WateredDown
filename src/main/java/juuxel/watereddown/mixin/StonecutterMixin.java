/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.WDProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
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

@Mixin(StonecutterBlock.class)
@Implements(@Interface(iface = Waterloggable.class, prefix = "waterlog$"))
public abstract class StonecutterMixin extends BlockMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false).with(WDProperties.LAVALOGGED, false));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        var1.with(Properties.WATERLOGGED).with(WDProperties.LAVALOGGED);
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        try {
            FluidState state = context.getWorld().getFluidState(context.getPos());
            info.setReturnValue(info.getReturnValue()
                    .with(WDProperties.LAVALOGGED, state.matches(FluidTags.LAVA))
                    .with(Properties.WATERLOGGED, state.matches(FluidTags.WATER)));
        } catch (NullPointerException e) {}
    }
}
