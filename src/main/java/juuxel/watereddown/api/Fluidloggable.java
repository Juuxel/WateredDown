/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

public interface Fluidloggable extends FluidDrainable, FluidFillable {
    @Override
    default boolean canFillWithFluid(BlockView var1, BlockPos var2, BlockState var3, Fluid var4) {
        return var3.get(FluidProperty.FLUID).getFluid() == Fluids.EMPTY;
    }

    default boolean tryFillWithFluid(IWorld var1, BlockPos var2, BlockState var3, FluidState var4) {
        if (var3.get(FluidProperty.FLUID).getFluid() == Fluids.EMPTY) {
            if (!var1.isClient()) {
                var1.setBlockState(var2, var3.with(FluidProperty.FLUID, new FluidProperty.Wrapper(var4.getFluid())), 3);
                var1.getFluidTickScheduler().schedule(var2, var4.getFluid(), var4.getFluid().method_15789(var1));
            }
            return true;
        } else {
            return false;
        }
    }

    default Fluid tryDrainFluid(IWorld var1, BlockPos var2, BlockState var3) {
        if (var3.get(FluidProperty.FLUID).getFluid() != Fluids.EMPTY) {
            var1.setBlockState(var2, var3.with(FluidProperty.FLUID, new FluidProperty.Wrapper(Fluids.EMPTY)), 3);
            return var3.get(FluidProperty.FLUID).getFluid();
        } else {
            return Fluids.EMPTY;
        }
    }
}
