/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import juuxel.watereddown.api.WDFluid;
import juuxel.watereddown.util.FluidAccessor;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntegerProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockHitResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CauldronBlock.class)
@Implements(@Interface(iface = FluidDrainable.class, prefix = "wd_drainable$"))
public abstract class CauldronMixin extends BlockMixin implements FluidDrainable {
    @Shadow @Final public static IntegerProperty field_10745;
    private static final FluidProperty FLUID = FluidProperty.VANILLA_FLUIDS;

    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FLUID, FluidProperty.EMPTY));
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidState state = context.getWorld().getFluidState(context.getPos());

        if (state.getFluid() instanceof BaseFluid) {
            BaseFluid baseFluid = (BaseFluid) state.getFluid();
            Fluid still = baseFluid.getStill();
            info.setReturnValue(info.getReturnValue().with(field_10745, 3).with(FLUID, new FluidProperty.Wrapper(still)));
        }
    }

    @Inject(at = @At("RETURN"), method = "appendProperties", cancellable = true)
    private void onAppendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        var1.with(FLUID);
    }

    @Overwrite
    public void method_9726(World var1, BlockPos var2, BlockState var3, int var4) {
        placeFluid(var1, var2, var3, var4, FluidProperty.WATER);
    }

    @Inject(at = @At("HEAD"), method = "activate", cancellable = true)
    private void onActivateHead(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult var6, CallbackInfoReturnable<Boolean> info) {
        ItemStack stack = player.getStackInHand(hand);

        // Skip the vanilla bucket code
        if (stack.getItem() == Items.BUCKET) {
            info.setReturnValue(false);
            info.cancel();
            return;
        }

        // Item destruction with lava
        int lavaLevel = state.get(FLUID).getFluid().matches(FluidTags.LAVA) ? state.get(field_10745) : 0;

        if (lavaLevel == 0 || stack.getItem() instanceof BucketItem)
            return;

        stack.subtractAmount(1);
        world.playSound(player, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCK, 1f, 1f);
        world.setBlockState(pos, state.with(field_10745, lavaLevel - 1));
        setFluidFromLevel(world, pos);
        info.setReturnValue(true);
        info.cancel();
    }

    @Inject(at = @At("RETURN"), method = "activate", cancellable = true)
    private void onActivateReturn(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult var6, CallbackInfoReturnable<Boolean> info) {
        ItemStack stack = player.getStackInHand(hand);
        int var11 = state.get(field_10745);
        Item item = stack.getItem();
        if (!info.getReturnValue() && item instanceof BucketItem && ((FluidAccessor) item).wd_getFluid() != Fluids.EMPTY) {
            if (var11 < 3 && !world.isClient) {
                if (!player.abilities.creativeMode) {
                    player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                }

                player.increaseStat(Stats.FILL_CAULDRON);
                placeFluid(world, pos, state, 3, new FluidProperty.Wrapper(((FluidAccessor) item).wd_getFluid()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCK, 1.0F, 1.0F);
            }

            info.setReturnValue(true);
        }
    }

    private void placeFluid(World var1, BlockPos var2, BlockState var3, int var4, FluidProperty.Wrapper fluid) {
        var1.setBlockState(var2, var3.with(field_10745, MathHelper.clamp(var4, 0, 3)).with(FLUID, fluid), 2);
        var1.updateHorizontalAdjacent(var2, (Block) (Object) this);
    }

    private void setFluidFromLevel(IWorld world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(FLUID, state.get(field_10745) == 0 ? FluidProperty.EMPTY : state.get(FLUID)), 3);
    }

    @Override
    public Fluid tryDrainFluid(IWorld world, BlockPos pos, BlockState state) {
        int level = state.get(field_10745);
        Fluid fluid = state.get(FLUID).getFluid();

        if (level == 3) {
            world.setBlockState(pos, state.with(field_10745, 0), 3);
            setFluidFromLevel(world, pos);
            return fluid;
        }

        return Fluids.EMPTY;
    }

    @Override
    protected void getLuminance(BlockState state, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue(((WDFluid) state.get(FLUID).getFluid()).getLuminance());
        info.cancel();
    }

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void onEntityCollision(BlockState state, World world_1, BlockPos blockPos_1, Entity entity_1, CallbackInfo info) {
        if (state.get(FLUID).getFluid() == Fluids.LAVA)
            info.cancel();
    }
}
