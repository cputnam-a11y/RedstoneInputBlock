package redstoneinputblock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import redstoneinputblock.block.ModBlocks;
import redstoneinputblock.tint.RedstoneInputBlockColorProvider;

public class RedstoneInputBlockClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(
                new RedstoneInputBlockColorProvider(),
                ModBlocks.REDSTONE_INPUT_BLOCK
        );
        BlockRenderLayerMap.INSTANCE.putBlock(
                ModBlocks.REDSTONE_INPUT_BLOCK,
                RenderLayer.getCutout()
        );
    }
}