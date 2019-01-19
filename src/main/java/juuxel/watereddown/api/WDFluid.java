/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

/**
 * Watered Down's extensions to Fluid. Implemented by all fluids.
 */
public interface WDFluid {
    default int getLuminance() {
        return 0;
    }
}
