package redstoneinputblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redstoneinputblock.block.ModBlocks;

public class RedstoneInputBlock implements ModInitializer {
    public static final String MOD_ID = "redstoneinputblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries ->
                entries.addAfter(Items.REDSTONE_LAMP, ModBlocks.REDSTONE_INPUT_BLOCK.asItem())
        );
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}