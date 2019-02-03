/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.mixin;

import juuxel.watereddown.block.WDBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.block.BlockItem;
import net.minecraft.item.block.SkullItem;
import net.minecraft.item.block.WallStandingBlockItem;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public abstract class ItemsMixin {
    /* LEVER */ @Shadow @Final @Mutable
    public static Item field_8865;

    /* RAIL */ @Shadow @Final @Mutable
    public static Item field_8129;

    /* POWERED_RAIL */ @Shadow @Final @Mutable
    public static Item field_8848;

    /* DETECTOR_RAIL */ @Shadow @Final @Mutable
    public static Item field_8211;

    /* ACTIVATOR_RAIL */ @Shadow @Final @Mutable
    public static Item field_8655;

    /* SKELETON_SKULL */ @Shadow @Final @Mutable
    public static Item field_8398;

    /* WITHER_SKELETON_SKULL */ @Shadow @Final @Mutable
    public static Item field_8791;

    /* PLAYER_HEAD */ @Shadow @Final @Mutable
    public static Item field_8575;

    /* ZOMBIE_HEAD */ @Shadow @Final @Mutable
    public static Item field_8470;

    /* CREEPER_HEAD */ @Shadow @Final @Mutable
    public static Item field_8681;

    /* DRAGON_HEAD */ @Shadow @Final @Mutable
    public static Item field_8712;

    @Shadow protected static Item register(BlockItem blockItem_1) { return null; }
    @Shadow protected static Item registerBlock(Block block_1, ItemGroup itemGroup_1) { return null; }

    @Inject(at = @At("RETURN"), method = "<clinit>")
    private static void onStaticInitialize(CallbackInfo info) {
        field_8865 = registerBlock(WDBlocks.LEVER, ItemGroup.REDSTONE);
        field_8129 = registerBlock(WDBlocks.RAIL, ItemGroup.TRANSPORTATION);
        field_8848 = registerBlock(WDBlocks.POWERED_RAIL, ItemGroup.TRANSPORTATION);
        field_8211 = registerBlock(WDBlocks.DETECTOR_RAIL, ItemGroup.TRANSPORTATION);
        field_8655 = registerBlock(WDBlocks.ACTIVATOR_RAIL, ItemGroup.TRANSPORTATION);
        field_8398 = register(new WallStandingBlockItem(WDBlocks.SKELETON_SKULL, WDBlocks.SKELETON_WALL_SKULL, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
        field_8791 = register(new WallStandingBlockItem(WDBlocks.WITHER_SKELETON_SKULL, WDBlocks.WITHER_SKELETON_WALL_SKULL, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
        field_8575 = register(new SkullItem(WDBlocks.PLAYER_HEAD, WDBlocks.PLAYER_WALL_HEAD, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
        field_8470 = register(new WallStandingBlockItem(WDBlocks.ZOMBIE_HEAD, WDBlocks.ZOMBIE_WALL_HEAD, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
        field_8681 = register(new WallStandingBlockItem(WDBlocks.CREEPER_HEAD, WDBlocks.CREEPER_WALL_HEAD, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
        field_8712 = register(new WallStandingBlockItem(WDBlocks.DRAGON_HEAD, WDBlocks.DRAGON_WALL_HEAD, (new Item.Settings()).itemGroup(ItemGroup.DECORATIONS).rarity(Rarity.UNCOMMON)));
    }
}
