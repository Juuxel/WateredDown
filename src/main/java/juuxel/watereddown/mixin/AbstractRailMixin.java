/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.Fluidloggable;
import juuxel.watereddown.util.FluidloggableImpl;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractRailBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "waterlog$"))
public abstract class AbstractRailMixin extends BlockMixin {
    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidloggableImpl.onGetPlacementState(context, info);
    }
}
