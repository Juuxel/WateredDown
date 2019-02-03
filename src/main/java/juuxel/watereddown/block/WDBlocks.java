/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.block;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.block.Material;
import net.minecraft.block.SkullBlock;
import net.minecraft.sound.BlockSoundGroup;

public final class WDBlocks {
    public static final Block LEVER = new LeverBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(0.5F, 0.5F).sounds(BlockSoundGroup.WOOD).build().noCollision());
    public static final Block RAIL = new RailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision());
    public static final Block ACTIVATOR_RAIL = new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision());
    public static final Block POWERED_RAIL = new PoweredRailBlockExtension(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision());
    public static final Block DETECTOR_RAIL = new DetectorRailBlock(FabricBlockSettings.of(Material.PART).strength(0.7F, 0.7F).sounds(BlockSoundGroup.METAL).build().noCollision());
    public static final Block SKELETON_SKULL = new SkullBlockExtension(SkullBlock.Type.SKELETON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F));
    public static final Block SKELETON_WALL_SKULL = new WallSkullBlockExtension(SkullBlock.Type.SKELETON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(SKELETON_SKULL));
    public static final Block WITHER_SKELETON_SKULL = new WitherSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F));
    public static final Block WITHER_SKELETON_WALL_SKULL = new WallWitherSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(WITHER_SKELETON_SKULL));
    public static final Block ZOMBIE_HEAD = new SkullBlockExtension(SkullBlock.Type.ZOMBIE, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F));
    public static final Block ZOMBIE_WALL_HEAD = new WallSkullBlockExtension(SkullBlock.Type.ZOMBIE, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(ZOMBIE_HEAD));
    public static final Block PLAYER_HEAD = new PlayerSkullBlockExtension(FabricBlockSettings.of(Material.WOOL).strength(1.0F, 1.0F));
    public static final Block PLAYER_WALL_HEAD = new WallPlayerSkullBlockExtension(FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(PLAYER_HEAD));
    public static final Block CREEPER_HEAD = new SkullBlockExtension(SkullBlock.Type.CREEPER, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F));
    public static final Block CREEPER_WALL_HEAD = new WallSkullBlockExtension(SkullBlock.Type.CREEPER, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(CREEPER_HEAD));
    public static final Block DRAGON_HEAD = new SkullBlockExtension(SkullBlock.Type.DRAGON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F));
    public static final Block DRAGON_WALL_HEAD = new WallSkullBlockExtension(SkullBlock.Type.DRAGON, FabricBlockSettings.of(Material.WOOD).strength(1.0F, 1.0F).dropsLike(DRAGON_HEAD));
}
