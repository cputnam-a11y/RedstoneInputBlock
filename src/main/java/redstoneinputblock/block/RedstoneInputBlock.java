package redstoneinputblock.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class RedstoneInputBlock extends Block {
    public static final IntProperty SIGNAL = IntProperty.of("signal", 0, 15);

    public RedstoneInputBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SIGNAL, 0));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        int i = 0;
        if (world.isReceivingRedstonePower(pos)) {
            i = Math.max(world.getReceivedStrongRedstonePower(pos), world.getReceivedRedstonePower(pos));
        }
        return state.with(SIGNAL, i);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SIGNAL);
    }
}
