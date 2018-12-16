/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.api;

import net.minecraft.block.BlockState;
import net.minecraft.class_2263;
import net.minecraft.class_2402;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

/**
 * Like waterlogging but lava instead of water.
 */
public interface Lavaloggable extends class_2263, class_2402 {
    BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

    default boolean method_10310(BlockView var1, BlockPos var2, BlockState var3, Fluid var4) {
        return !(Boolean)var3.get(LAVALOGGED) && var4 == Fluids.LAVA;
    }

    default boolean method_10311(IWorld var1, BlockPos var2, BlockState var3, FluidState var4) {
        if (!(Boolean)var3.get(LAVALOGGED) && var4.getFluid() == Fluids.LAVA) {
            if (!var1.isClient()) {
                var1.setBlockState(var2, (BlockState)var3.with(LAVALOGGED, true), 3);
                var1.getFluidTickScheduler().schedule(var2, var4.getFluid(), var4.getFluid().method_15789(var1));
            }

            return true;
        } else {
            return false;
        }
    }

    default Fluid method_9700(IWorld var1, BlockPos var2, BlockState var3) {
        if ((Boolean)var3.get(LAVALOGGED)) {
            var1.setBlockState(var2, (BlockState)var3.with(LAVALOGGED, false), 3);
            return Fluids.LAVA;
        } else {
            return Fluids.EMPTY;
        }
    }
}
