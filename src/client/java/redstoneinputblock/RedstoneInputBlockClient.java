package redstoneinputblock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import redstoneinputblock.block.ModBlocks;
import redstoneinputblock.tint.RedstoneInputBlockColorProvider;

public class RedstoneInputBlockClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(
                new RedstoneInputBlockColorProvider(),
                ModBlocks.REDSTONE_INPUT_BLOCK
        );
    }
}