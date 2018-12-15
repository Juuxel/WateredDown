/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.block.LeverBlockExtension;
import juuxel.watereddown.block.PoweredRailBlockExtension;
import juuxel.watereddown.block.RailBlockExtension;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blocks.class)
public class BlockReplacementMixin {
    @Shadow @Final @Mutable public static Block LEVER;
    @Shadow @Final @Mutable public static Block RAIL;
    @Shadow @Final @Mutable public static Block ACTIVATOR_RAIL;
    @Shadow @Final @Mutable public static Block POWERED_RAIL;
    @Shadow @Final @Mutable public static Block DETECTOR_RAIL;

    @Inject(at = @At("RETURN"), method = "<clinit>")
    private static void onStaticInitialize(CallbackInfo info) {
        LEVER = register("lever", new LeverBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(0.5F, 0.5F).sounds(BlockSoundGroup.WOOD).build().noCollision()));
        POWERED_RAIL = register("powered_rail", new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        DETECTOR_RAIL = register("detector_rail", new DetectorRailBlock(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        RAIL = register("rail", new RailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        ACTIVATOR_RAIL = register("activator_rail", new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
    }

    @Shadow
    private static Block register(String var0, Block var1) { return null; }
}
