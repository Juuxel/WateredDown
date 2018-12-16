/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

import static net.minecraft.block.CauldronBlock.field_10745;

@Mixin(CauldronBlock.class)
public abstract class CauldronMixin extends BlockMixin {
    private static final FluidProperty FLUID = FluidProperty.create("fluid", Arrays.asList(Fluids.LAVA, Fluids.WATER, null));
    private static final FluidProperty.FluidContainer LAVA = new FluidProperty.FluidContainer(Fluids.LAVA);
    private static final FluidProperty.FluidContainer WATER = new FluidProperty.FluidContainer(Fluids.WATER);
    private static final FluidProperty.FluidContainer NONE = new FluidProperty.FluidContainer(null);

    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FLUID, NONE));
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidState state = context.getWorld().getFluidState(context.getPos());

        if (state.matches(FluidTags.WATER))
            info.setReturnValue(info.getReturnValue().with(field_10745, 3).with(FLUID, WATER));
        else if (state.matches(FluidTags.LAVA))
            info.setReturnValue(info.getReturnValue().with(field_10745, 3).with(FLUID, LAVA));
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    private void onAppendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        var1.with(FLUID);
    }

    @Overwrite
    public void method_9726(World var1, BlockPos var2, BlockState var3, int var4) {
        placeFluid(var1, var2, var3, var4, WATER);
    }

    @Inject(at = @At("RETURN"), method = "activate", cancellable = true)
    private void onActivate(BlockState var1, World var2, BlockPos var3, PlayerEntity var4, Hand var5, Direction var6, float var7, float var8, float var9, CallbackInfoReturnable<Boolean> info) {
        ItemStack stack = var4.getStackInHand(var5);
        int var11 = var1.get(field_10745);
        Item item = stack.getItem();
        if (!info.getReturnValue() && item == Items.LAVA_BUCKET) {
            if (var11 < 3 && !var2.isRemote) {
                if (!var4.abilities.creativeMode) {
                    var4.setStackInHand(var5, new ItemStack(Items.BUCKET));
                }

                var4.method_7281(Stats.FILL_CAULDRON);
                placeFluid(var2, var3, var1, 3, LAVA);
                var2.playSound((PlayerEntity)null, var3, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCK, 1.0F, 1.0F);
            }

            info.setReturnValue(true);
        }
    }

    private void placeFluid(World var1, BlockPos var2, BlockState var3, int var4, FluidProperty.FluidContainer fluid) {
        var1.setBlockState(var2, var3.with(field_10745, MathHelper.clamp(var4, 0, 3)).with(FLUID, fluid), 2);
        var1.updateHorizontalAdjacent(var2, (Block) (Object) this);
    }
}
