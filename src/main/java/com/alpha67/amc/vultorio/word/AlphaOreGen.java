package com.alpha67.amc.vultorio.word;

import com.alpha67.amc.vultorio.init.BlockInitVultorio;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaOreGen {

    public static final AlphaOreGen FEATURE = (AlphaOreGen) new AlphaOreGen().setRegistryName("amc:test");
    public static final ConfiguredFeature<?, ?> CONFIGURED_FEATURE = FEATURE
            .configured(new OreConfiguration(TestFeatureRuleTest.INSTANCE, AmcModBlocks.TEST.defaultBlockState(), 16))
            .range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)))).squared().count(10);
    public static final Set<ResourceLocation> GENERATE_BIOMES = null;

    public TestFeature() {
        super(OreConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel world = context.level();
        ResourceKey<Level> dimensionType = world.getLevel().dimension();
        boolean dimensionCriteria = false;
        if (dimensionType == Level.OVERWORLD)
            dimensionCriteria = true;
        if (!dimensionCriteria)
            return false;
        return super.place(context);
    }

    private static class TestFeatureRuleTest extends RuleTest {
        static final TestFeatureRuleTest INSTANCE = new TestFeatureRuleTest();
        static final com.mojang.serialization.Codec<TestFeatureRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
        static final RuleTestType<TestFeatureRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST, new ResourceLocation("amc:test_match"),
                () -> codec);

        public boolean test(BlockState blockAt, Random random) {
            boolean blockCriteria = false;
            if (blockAt.getBlock() == Blocks.STONE)
                blockCriteria = true;
            return blockCriteria;
        }

        protected RuleTestType<?> getType() {
            return CUSTOM_MATCH;
        }
    }
}
