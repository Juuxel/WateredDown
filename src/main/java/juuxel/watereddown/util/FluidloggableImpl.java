/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.util;

import juuxel.watereddown.api.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FluidloggableImpl {
    public static void onGetPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        info.setReturnValue(Fluidloggable.onGetPlacementState(context, info.getReturnValue()));
    }
}
