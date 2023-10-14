package io.github.hexagonnico.undergroundjungle.renderers;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleBlocks;
import io.github.hexagonnico.undergroundjungle.blocks.TempleChestBlockEntity;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class TempleChestItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockEntity blockEntity = new TempleChestBlockEntity(BlockPos.ORIGIN, UndergroundJungleBlocks.TEMPLE_CHEST.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.SOUTH));
        MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
    }
}
