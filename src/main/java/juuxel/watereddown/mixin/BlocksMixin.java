/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.block.*;
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
public class BlocksMixin {
    @Shadow @Final @Mutable public static Block LEVER;
    @Shadow @Final @Mutable public static Block RAIL;
    @Shadow @Final @Mutable public static Block ACTIVATOR_RAIL;
    @Shadow @Final @Mutable public static Block POWERED_RAIL;
    @Shadow @Final @Mutable public static Block DETECTOR_RAIL;
    @Shadow @Final @Mutable public static Block SKELETON_SKULL;
    @Shadow @Final @Mutable public static Block SKELETON_WALL_SKULL;
    @Shadow @Final @Mutable public static Block WITHER_SKELETON_SKULL;
    @Shadow @Final @Mutable public static Block WITHER_SKELETON_WALL_SKULL;
    @Shadow @Final @Mutable public static Block ZOMBIE_HEAD;
    @Shadow @Final @Mutable public static Block ZOMBIE_WALL_HEAD;
    @Shadow @Final @Mutable public static Block PLAYER_HEAD;
    @Shadow @Final @Mutable public static Block PLAYER_WALL_HEAD;
    @Shadow @Final @Mutable public static Block CREEPER_HEAD;
    @Shadow @Final @Mutable public static Block CREEPER_WALL_HEAD;
    @Shadow @Final @Mutable public static Block DRAGON_HEAD;
    @Shadow @Final @Mutable public static Block DRAGON_WALL_HEAD;

    @SuppressWarnings("ALL")
    @Inject(at = @At("RETURN"), method = "<clinit>")
    private static void onStaticInitialize(CallbackInfo info) {
        LEVER = register("lever", WDBlocks.LEVER);
        POWERED_RAIL = register("powered_rail", WDBlocks.POWERED_RAIL);
        DETECTOR_RAIL = register("detector_rail", WDBlocks.DETECTOR_RAIL);
        RAIL = register("rail", WDBlocks.RAIL);
        ACTIVATOR_RAIL = register("activator_rail", WDBlocks.ACTIVATOR_RAIL);
        SKELETON_SKULL = register("skeleton_skull", WDBlocks.SKELETON_SKULL);
        SKELETON_WALL_SKULL = register("skeleton_wall_skull", WDBlocks.SKELETON_WALL_SKULL);
        WITHER_SKELETON_SKULL = register("wither_skeleton_skull", WDBlocks.WITHER_SKELETON_SKULL);
        WITHER_SKELETON_WALL_SKULL = register("wither_skeleton_wall_skull", WDBlocks.WITHER_SKELETON_WALL_SKULL);
        ZOMBIE_HEAD = register("zombie_head", WDBlocks.ZOMBIE_HEAD);
        ZOMBIE_WALL_HEAD = register("zombie_wall_head", WDBlocks.ZOMBIE_WALL_HEAD);
        PLAYER_HEAD = register("player_head", WDBlocks.PLAYER_HEAD);
        PLAYER_WALL_HEAD = register("player_wall_head", WDBlocks.PLAYER_WALL_HEAD);
        CREEPER_HEAD = register("creeper_head", WDBlocks.CREEPER_HEAD);
        CREEPER_WALL_HEAD = register("creeper_wall_head", WDBlocks.CREEPER_WALL_HEAD);
        DRAGON_HEAD = register("dragon_head", WDBlocks.DRAGON_HEAD);
        DRAGON_WALL_HEAD = register("dragon_wall_head", WDBlocks.DRAGON_WALL_HEAD);
    }

    @Shadow
    private static Block register(String var0, Block var1) { return null; }
}
