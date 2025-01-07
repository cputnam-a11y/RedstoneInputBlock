package redstoneinputblock.tint;

import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;
import redstoneinputblock.block.RedstoneInputBlock;

public class RedstoneInputBlockColorProvider implements BlockColorProvider {
    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex != 1)
            return -1;
        return switch (state.get(RedstoneInputBlock.SIGNAL)) {
            case 0 -> 0x4B0000;
            case 1 -> 0x6F0000;
            case 2 -> 0x790000;
            case 3 -> 0x820000;
            case 4 -> 0x8C0000;
            case 5 -> 0x970000;
            case 6 -> 0xA10000;
            case 7 -> 0xAB0000;
            case 8 -> 0xB50000;
            case 9 -> 0xBF0000;
            case 10 -> 0xCA0000;
            case 11 -> 0xD30000;
            case 12 -> 0xDD0000;
            case 13 -> 0xE70600;
            case 14 -> 0xF11B00;
            case 15 -> 0xFC3100;
            default -> 0;
        };
    }
}
