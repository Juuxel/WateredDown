/* This file is a part of the Watered Down project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/WateredDown
 */
package juuxel.watereddown.block;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.SkullBlock;

public class SkullBlockExtension extends SkullBlock {
    public SkullBlockExtension(SkullType var1, FabricBlockSettings var2) {
        super(var1, var2.build());
    }
}
