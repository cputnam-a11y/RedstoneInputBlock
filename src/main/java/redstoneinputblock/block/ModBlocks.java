package redstoneinputblock.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;
import java.util.function.Supplier;

import static redstoneinputblock.RedstoneInputBlock.id;

public class ModBlocks {
    public static final RedstoneInputBlock REDSTONE_INPUT_BLOCK = register(
            "redstone_input_block",
            RedstoneInputBlock::new,
            () -> AbstractBlock.Settings
                    .copy(Blocks.STONE)
                    .requiresTool()
                    .solid()
    );

    private static <T extends Block> T register(String name, Function<AbstractBlock.Settings, T> factory, Supplier<AbstractBlock.Settings> settingsSupplier) {
        var id = id(name);
        var blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
        var block = factory.apply(settingsSupplier.get().registryKey(blockKey));
        var itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        var item = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
        Registry.register(
                Registries.ITEM,
                itemKey,
                item
        );
        return Registry.register(
                Registries.BLOCK,
                blockKey,
                block
        );
    }

    public static void init() {

    }
}
