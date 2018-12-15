/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallMountedBlock.class)
public abstract class WallMountedBlockShadowMixin extends BlockShadowMixin {
    @Inject(at = @At("RETURN"), method = "getPlacementState", cancellable = true)
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {

    }
}
