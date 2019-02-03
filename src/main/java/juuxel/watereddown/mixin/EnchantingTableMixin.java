/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.api.FluidProperty;
import juuxel.watereddown.api.Fluidloggable;
import juuxel.watereddown.api.WDFluid;
import juuxel.watereddown.util.FluidloggableImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "waterlog$"))
public abstract class EnchantingTableMixin extends BlockMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstruct(Block.Settings var1, CallbackInfo info) {
        setDefaultState(getDefaultState().with(FluidProperty.FLUID, FluidProperty.EMPTY));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> var1, CallbackInfo info) {
        Fluidloggable.onAppendProperties(var1);
    }

    @Override
    protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> info) {
        FluidloggableImpl.onGetPlacementState(context, info);
    }

    @Inject(at = @At("HEAD"), method = "activate", cancellable = true)
    private void onActivate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> info) {
        if (WDFluid.of(state.get(FluidProperty.FLUID)).blocksEnchantingTables()) {
            double x = pos.getX() + 0.25 + world.getRandom().nextDouble() * 0.5;
            double y = pos.getY() + 0.9;
            double z = pos.getZ() + 0.25 + world.getRandom().nextDouble() * 0.5;

            for (int i = 0; i < 3; i++) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0, 0.0, 0.0);
            }
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCK, 1f, 1f);
            info.setReturnValue(true);
            info.cancel();
        }
    }
}
