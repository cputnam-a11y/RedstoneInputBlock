package redstoneinputblock.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import redstoneinputblock.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new ModRecipeGenerator(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return "Recipe Provider";
    }

    private static class ModRecipeGenerator extends RecipeGenerator {

        protected ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            ShapedRecipeJsonBuilder.create(
                            Registries.ITEM,
                            RecipeCategory.REDSTONE,
                            ModBlocks.REDSTONE_INPUT_BLOCK
                    )
                    .pattern("SSS")
                    .pattern("SRS")
                    .pattern("SBS")
                    .input('S', Items.STONE)
                    .input('R', Items.REDSTONE)
                    .input('B', Items.REDSTONE_TORCH)
                    .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
                    .showNotification(false)
                    .offerTo(exporter);
        }
    }
}
