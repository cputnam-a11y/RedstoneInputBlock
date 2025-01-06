package redstoneinputblock.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.registry.Registries;
import redstoneinputblock.block.ModBlocks;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        String[] suffixes = new String[]{
                "_zero", "_one",
                "_two", "_three",
                "_four", "_five",
                "_six", "_seven",
                "_eight", "_nine",
                "_ten", "_eleven",
                "_twelve", "_thirteen",
                "_fourteen", "_fifteen"
        };
        for (String suffix : suffixes) {
            make(suffix).upload(
                    ModBlocks.REDSTONE_INPUT_BLOCK,
                    suffix,
                    blockStateModelGenerator.modelCollector
            );
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(
                ModBlocks.REDSTONE_INPUT_BLOCK.asItem(),
                new Model(
                        Optional.of(
                                Registries.BLOCK.getId(ModBlocks.REDSTONE_INPUT_BLOCK)
                                        .withPrefixedPath("block/")
                                        .withSuffixedPath("_zero")
                        ),
                        Optional.empty()
                )
        );
    }

    private static TexturedModel.Factory make(String suffix) {
        return TexturedModel.makeFactory(block -> TextureMap.all(Registries.BLOCK.getId(block).withPrefixedPath("block/").withSuffixedPath(suffix)), Models.CUBE_ALL);
    }
}
