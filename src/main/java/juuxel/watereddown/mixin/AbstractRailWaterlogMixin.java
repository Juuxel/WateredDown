/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractRailBlock.class)
@Implements(@Interface(iface = Waterloggable.class, prefix = "waterlog$"))
public abstract class AbstractRailWaterlogMixin extends BlockShadowMixin {
    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidState state = context.getWorld().getFluidState(context.getPos());
        info.setReturnValue(info.getReturnValue().with(Properties.WATERLOGGED, state.matches(FluidTags.WATER)));
    }
}
