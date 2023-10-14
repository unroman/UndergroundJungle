package io.github.hexagonnico.undergroundjungle.blocks;

import net.minecraft.block.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class MudGrassBlock extends Block implements Fertilizable {

    private final RegistryKey<ConfiguredFeature<?, ?>> bonemealFeature;

    public MudGrassBlock(AbstractBlock.Settings settings, String bonemealFeature) {
        super(settings);
        this.bonemealFeature = ConfiguredFeatures.of("underground_jungle:" + bonemealFeature);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!canBeGrass(state, world, pos)) {
            world.setBlockState(pos, Blocks.MUD.getDefaultState());
        }
        if(world.getLightLevel(pos.up()) >= 9) {
            BlockState defaultState = this.getDefaultState();
            for(int i = 0; i < 4; i++) {
                BlockPos nextPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if(world.getBlockState(nextPos).isOf(Blocks.MUD) && canPropagate(defaultState, world, nextPos)) {
                    world.setBlockState(nextPos, defaultState);
                }
            }
        }
    }

    private static boolean canPropagate(BlockState blockState, ServerWorld world, BlockPos pos) {
        return canBeGrass(blockState, world, pos) && !world.getFluidState(pos.up()).isIn(FluidTags.WATER);
    }

    private static boolean canBeGrass(BlockState blockState, ServerWorld world, BlockPos pos) {
        BlockPos posAbove = pos.up();
        BlockState blockStateAbove = world.getBlockState(posAbove);
        if (blockStateAbove.isOf(Blocks.SNOW) && blockStateAbove.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockStateAbove.getFluidState().getLevel() == 8) {
            return false;
        } else {
            return ChunkLightProvider.getRealisticOpacity(world, blockState, pos, blockStateAbove, posAbove, Direction.UP, blockStateAbove.getOpacity(world, posAbove)) < world.getMaxLightLevel();
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE);
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        for(int x = -3; x <= 3; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -3; z <= 3; z++) {
                    BlockPos offsetPos = pos.add(x, y, z);
                    if(random.nextBoolean() && world.getBlockState(offsetPos).isIn(BlockTags.DIRT) && world.getBlockState(offsetPos.up()).isAir()) {
                        registry.getEntry(this.bonemealFeature).ifPresent(feature -> feature.value().generate(world, chunkGenerator, random, offsetPos.up()));
                    }
                }
            }
        }
    }
}
