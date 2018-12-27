/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.util.FluidAccessor;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BucketItem.class)
@Implements(@Interface(iface = FluidAccessor.class, prefix = "wd_access$"))
public class BucketMixin implements FluidAccessor {
    @Shadow
    private Fluid fluid;

    @Override
    public Fluid wd_getFluid() {
        return fluid;
    }
}
