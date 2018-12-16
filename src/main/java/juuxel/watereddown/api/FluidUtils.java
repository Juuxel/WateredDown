/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;

public final class FluidUtils {
    private FluidUtils() {}

    public static FluidState getWorldState(Fluid fluid) {
        if (fluid instanceof BaseFluid) {
            return ((BaseFluid) fluid).getState(false);
        } else {
            return fluid.getDefaultState();
        }
    }
}
