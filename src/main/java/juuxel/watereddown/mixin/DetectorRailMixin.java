/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import juuxel.watereddown.api.Fluidloggable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.state.StateFactory;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DetectorRailBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "waterlog$"))
public abstract class DetectorRailMixin extends AbstractRailMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FluidProperty.FLUID, FluidProperty.EMPTY));
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    private void onAppendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        Fluidloggable.onAppendProperties(var1);
    }
}
