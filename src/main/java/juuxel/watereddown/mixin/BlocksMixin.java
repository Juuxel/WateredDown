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
        LEVER = register("lever", new LeverBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(0.5F, 0.5F).sounds(BlockSoundGroup.WOOD).build().noCollision()));
        POWERED_RAIL = register("powered_rail", new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        DETECTOR_RAIL = register("detector_rail", new DetectorRailBlock(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        RAIL = register("rail", new RailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        ACTIVATOR_RAIL = register("activator_rail", new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision()));
        SKELETON_SKULL = register("skeleton_skull", new SkullBlockExtension(SkullBlock.Type.SKELETON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F)));
        SKELETON_WALL_SKULL = register("skeleton_wall_skull", new WallSkullBlockExtension(SkullBlock.Type.SKELETON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(SKELETON_SKULL)));
        WITHER_SKELETON_SKULL = register("wither_skeleton_skull", new WitherSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F)));
        WITHER_SKELETON_WALL_SKULL = register("wither_skeleton_wall_skull", new WallWitherSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(WITHER_SKELETON_SKULL)));
        ZOMBIE_HEAD = register("zombie_head", new SkullBlockExtension(SkullBlock.Type.ZOMBIE, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F)));
        ZOMBIE_WALL_HEAD = register("zombie_wall_head", new WallSkullBlockExtension(SkullBlock.Type.ZOMBIE, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(ZOMBIE_HEAD)));
        PLAYER_HEAD = register("player_head", new PlayerSkullBlockExtension(FabricBlockSettings.of(Material.WOOL).strength(1.0F, 1.0F)));
        PLAYER_WALL_HEAD = register("player_wall_head", new WallPlayerSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(PLAYER_HEAD)));
        CREEPER_HEAD = register("creeper_head", new SkullBlockExtension(SkullBlock.Type.CREEPER, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F)));
        CREEPER_WALL_HEAD = register("creeper_wall_head", new WallSkullBlockExtension(SkullBlock.Type.CREEPER, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(CREEPER_HEAD)));
        DRAGON_HEAD = register("dragon_head", new SkullBlockExtension(SkullBlock.Type.DRAGON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F)));
        DRAGON_WALL_HEAD = register("dragon_wall_head", new WallSkullBlockExtension(SkullBlock.Type.DRAGON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(DRAGON_HEAD)));
    }

    @Shadow
    private static Block register(String var0, Block var1) { return null; }
}
