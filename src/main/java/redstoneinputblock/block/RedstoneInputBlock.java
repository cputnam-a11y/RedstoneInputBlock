package redstoneinputblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class RedstoneInputBlock extends PillarBlock {
    public static final IntProperty SIGNAL = IntProperty.of("signal", 0, 15);

    public RedstoneInputBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SIGNAL, 0));
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        int i = 0;
        if (world.isReceivingRedstonePower(pos)) {
            i = Math.max(world.getReceivedStrongRedstonePower(pos), world.getReceivedRedstonePower(pos));
        }
        if (i != state.get(SIGNAL))
            world.setBlockState(pos, state.with(SIGNAL, i), Block.NOTIFY_ALL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SIGNAL);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(
                        SIGNAL,
                        Math.max(
                                ctx.getWorld().getReceivedStrongRedstonePower(
                                        ctx.getBlockPos()
                                ),
                                ctx.getWorld().getReceivedRedstonePower(
                                        ctx.getBlockPos()
                                )
                        )
                );
    }
}
