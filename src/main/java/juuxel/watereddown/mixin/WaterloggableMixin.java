/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.WDProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Waterloggable.class)
public interface WaterloggableMixin {
    @Overwrite
    default boolean method_10310(BlockView var1, BlockPos var2, BlockState var3, Fluid var4) {
        return (var3.getProperties().contains(WDProperties.LAVALOGGED) && !(Boolean)var3.get(WDProperties.LAVALOGGED) && var4 == Fluids.LAVA) ||
                (!(Boolean)var3.get(Properties.WATERLOGGED) && var4 == Fluids.WATER);
    }

    @Overwrite
    default boolean method_10311(IWorld var1, BlockPos var2, BlockState var3, FluidState var4) {
        Fluid fluid = var4.getFluid();

        if ((var3.getProperties().contains(WDProperties.LAVALOGGED) && !(Boolean)var3.get(WDProperties.LAVALOGGED) && fluid == Fluids.LAVA) ||
                (!(Boolean)var3.get(Properties.WATERLOGGED) && fluid == Fluids.WATER)) {
            if (!var1.isClient()) {
                var1.setBlockState(var2, (BlockState)var3.with(fluid == Fluids.LAVA ? WDProperties.LAVALOGGED : Properties.WATERLOGGED, true), 3);
                var1.getFluidTickScheduler().schedule(var2, var4.getFluid(), var4.getFluid().method_15789(var1));
            }

            return true;
        } else {
            return false;
        }
    }

    @Overwrite
    default Fluid method_9700(IWorld var1, BlockPos var2, BlockState var3) {
        if (var3.getProperties().contains(WDProperties.LAVALOGGED) && (Boolean)var3.get(WDProperties.LAVALOGGED)) {
            var1.setBlockState(var2, (BlockState)var3.with(WDProperties.LAVALOGGED, false), 3);
            return Fluids.LAVA;
        } else if ((Boolean)var3.get(Properties.WATERLOGGED)) {
            var1.setBlockState(var2, (BlockState) var3.with(Properties.WATERLOGGED, false), 3);
            return Fluids.WATER;
        } else {
            return Fluids.EMPTY;
        }
    }
}
