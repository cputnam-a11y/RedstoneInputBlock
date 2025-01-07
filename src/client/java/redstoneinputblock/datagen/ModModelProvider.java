package redstoneinputblock.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.data.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
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
        var baseId = Registries.BLOCK.getId(ModBlocks.REDSTONE_INPUT_BLOCK);
        var map = BlockStateVariantMap.create(RedstoneInputBlock.SIGNAL, PillarBlock.AXIS)
                .register(
                        (signal, axis) -> BlockStateVariant.create()
                                .put(
                                        VariantSettings.MODEL,
                                        baseId
                                                .withPrefixedPath("block/")
                                                .withSuffixedPath(suffixes[signal])
                                )
                                .put(
                                        VariantSettings.X,
                                        switch (axis) {
                                            case X, Z -> VariantSettings.Rotation.R90;
                                            default -> VariantSettings.Rotation.R0;
                                        }
                                )
                                .put(
                                        VariantSettings.Y,
                                        axis != Direction.Axis.X
                                        ? VariantSettings.Rotation.R0
                                        : VariantSettings.Rotation.R90
                                )
                );


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
        var baseKey = TextureKey.of("base");
        var baseTopKey = TextureKey.of("base_top");
        var overlayKey = TextureKey.of("overlay");
        var overlayTopKey = TextureKey.of("overlay_top");
        return TexturedModel.makeFactory(
                block -> {
                    var id = Registries.BLOCK.getId(block).withPrefixedPath("block/");
                    return new TextureMap()
                            .put(
                                    baseKey,
                                    id.withSuffixedPath("_base")
                            )
                            .put(
                                    baseTopKey,
                                    id.withSuffixedPath("_base_top")
                            )
                            .put(
                                    overlayKey,
                                    id.withSuffixedPath(suffix).withSuffixedPath("_overlay")
                            )
                            .put(
                                    overlayTopKey,
                                    id.withSuffixedPath(suffix).withSuffixedPath("_overlay_top")
                            );
                },
                new Model(
                        Optional.of(Identifier.of(redstoneinputblock.RedstoneInputBlock.MOD_ID, "block/base_overlayable_axis_block")),
                        Optional.empty(),
                        baseKey,
                        baseTopKey,
                        overlayKey,
                        overlayTopKey
                )
        );
    }
    //endregion
}
