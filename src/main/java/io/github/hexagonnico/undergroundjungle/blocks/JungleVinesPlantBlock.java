package io.github.hexagonnico.undergroundjungle.blocks;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.CaveVinesBodyBlock;
import net.minecraft.util.math.Direction;

public class JungleVinesPlantBlock extends AbstractPlantBlock {

    public JungleVinesPlantBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, CaveVinesBodyBlock.SHAPE, false);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) UndergroundJungleBlocks.JUNGLE_VINES;
    }
}
