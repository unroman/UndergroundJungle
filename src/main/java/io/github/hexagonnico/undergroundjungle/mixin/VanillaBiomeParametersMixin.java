package io.github.hexagonnico.undergroundjungle.mixin;

import com.mojang.datafixers.util.Pair;
import io.github.hexagonnico.undergroundjungle.UndergroundJungleMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {

    private static final float TEMPERATURE_MIN = 0.4f;
    private static final float TEMPERATURE_MAX = 1.0f;
    private static final float HUMIDITY_MIN = 0.4f;
    private static final float HUMIDITY_MAX = 1.0f;
    private static final float CONTINENTALNESS_MIN = 0.5f;
    private static final float CONTINENTALNESS_MAX = 1.0f;
    private static final float EROSION_MIN = -1.0f;
    private static final float EROSION_MAX = 1.0f;
    private static final float DEPTH_MIN = 0.0f;
    private static final float DEPTH_MAX = 0.7f;
    private static final float WEIRDNESS_MIN = 0.0f;
    private static final float WEIRDNESS_MAX = 1.0f;

    @Inject(at = @At("RETURN"), method = "writeCaveBiomes")
    public void writeCaveBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        RegistryKey<Biome> undergroundJungleKey = RegistryKey.of(RegistryKeys.BIOME, new Identifier(UndergroundJungleMod.ID, "underground_jungle"));
        MultiNoiseUtil.NoiseHypercube undergroundJungleClimate = MultiNoiseUtil.createNoiseHypercube(
            MultiNoiseUtil.ParameterRange.of(TEMPERATURE_MIN, TEMPERATURE_MAX),
            MultiNoiseUtil.ParameterRange.of(HUMIDITY_MIN, HUMIDITY_MAX),
            MultiNoiseUtil.ParameterRange.of(CONTINENTALNESS_MIN, CONTINENTALNESS_MAX),
            MultiNoiseUtil.ParameterRange.of(EROSION_MIN, EROSION_MAX),
            MultiNoiseUtil.ParameterRange.of(DEPTH_MIN, DEPTH_MAX),
            MultiNoiseUtil.ParameterRange.of(WEIRDNESS_MIN, WEIRDNESS_MAX),
            0.0f
        );
        parameters.accept(Pair.of(undergroundJungleClimate, undergroundJungleKey));
    }
}