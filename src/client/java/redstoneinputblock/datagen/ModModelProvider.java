package redstoneinputblock.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.registry.Registries;
import redstoneinputblock.block.ModBlocks;
import redstoneinputblock.block.RedstoneInputBlock;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    //region Constructor
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    //endregion

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //region Suffixes
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
        //endregion
        //region Block Models
        for (String suffix : suffixes) {
            makeSuffixedFactory(suffix).upload(
                    ModBlocks.REDSTONE_INPUT_BLOCK,
                    suffix,
                    blockStateModelGenerator.modelCollector
            );
        }
        //endregion
        //region Block States
        var map = BlockStateVariantMap.create(RedstoneInputBlock.SIGNAL);
        var baseId = Registries.BLOCK.getId(ModBlocks.REDSTONE_INPUT_BLOCK);
        for (int value : RedstoneInputBlock.SIGNAL.getValues()) {
            map.register(
                    value,
                    BlockStateVariant.create()
                            .put(
                                    VariantSettings.MODEL,
                                    baseId
                                            .withPrefixedPath("block/")
                                            .withSuffixedPath(suffixes[value])
                            )
            );
        }
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier
                        .create(ModBlocks.REDSTONE_INPUT_BLOCK)
                        .coordinate(map)
        );
        //endregion
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //region Item Models
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
        //endregion
    }
    //region Util
    private static TexturedModel.Factory makeSuffixedFactory(String suffix) {
        return TexturedModel.makeFactory(block -> TextureMap.all(Registries.BLOCK.getId(block).withPrefixedPath("block/").withSuffixedPath(suffix)), Models.CUBE_ALL);
    }
    //endregion
}
