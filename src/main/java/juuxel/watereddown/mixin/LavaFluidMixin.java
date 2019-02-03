/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LavaFluid.class)
public class LavaFluidMixin extends FluidMixin {
    @Override
    public int getLuminance() {
        return 15;
    }

    @Override
    public boolean blocksEnchantingTables() {
        return true;
    }
}
