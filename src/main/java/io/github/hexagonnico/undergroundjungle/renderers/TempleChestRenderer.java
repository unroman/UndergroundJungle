package io.github.hexagonnico.undergroundjungle.renderers;

import io.github.hexagonnico.undergroundjungle.blocks.TempleChestBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;

public class TempleChestRenderer extends ChestBlockEntityRenderer<TempleChestBlockEntity> {

    public TempleChestRenderer(BlockEntityRendererFactory.Context context) {
        super(context);
    }

//    @Override
//    protected @NotNull Material getMaterial(@NotNull TempleChestBlockEntity blockEntity, @NotNull ChestType chestType) {
//        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(UndergroundJungleMod.ID, "entity/chest/temple"));
//    }
}
