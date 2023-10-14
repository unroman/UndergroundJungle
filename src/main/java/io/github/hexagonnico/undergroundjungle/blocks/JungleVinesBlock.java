package io.github.hexagonnico.undergroundjungle.blocks;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class JungleVinesBlock extends AbstractPlantStemBlock {

    public JungleVinesBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, CaveVinesHeadBlock.SHAPE, false, 0.1);
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 1;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getPlant() {
        return UndergroundJungleBlocks.JUNGLE_VINES_PLANT;
    }
}