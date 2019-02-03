/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import net.minecraft.fluid.Fluid;

/**
 * Watered Down's extensions to Fluid. Implemented by all fluids.
 */
public interface WDFluid {
    default int getLuminance() {
        return 0;
    }

    default boolean blocksEnchantingTables() {
        return false;
    }

    static WDFluid of(Fluid fluid) {
        return (WDFluid) fluid;
    }

    static WDFluid of(FluidProperty.Wrapper fluidWrapper) {
        return (WDFluid) fluidWrapper.getFluid();
    }
}
