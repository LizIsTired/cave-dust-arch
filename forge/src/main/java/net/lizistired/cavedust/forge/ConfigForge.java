package net.lizistired.cavedust.forge;

import dev.architectury.forge.ArchitecturyForge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import static net.lizistired.cavedust.CaveDust.WHITE_ASH_ID;
import static net.lizistired.cavedust.utils.MathHelper.generateRandomDouble;

public class ConfigForge {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> DIMENSION_X;
    public static final ForgeConfigSpec.ConfigValue<Integer> DIMENSION_Y;
    public static final ForgeConfigSpec.ConfigValue<Integer> DIMENSION_Z;

    public static final ForgeConfigSpec.BooleanValue CAVE_DUST_ENABLED;
    public static final ForgeConfigSpec.BooleanValue SEA_LEVEL_CHECK;
    public static final ForgeConfigSpec.BooleanValue SUPERFLAT_STATUS;

    public static final ForgeConfigSpec.ConfigValue<Integer> UPPER_LIMIT;
    public static final ForgeConfigSpec.ConfigValue<Integer> LOWER_LIMIT;
    public static final ForgeConfigSpec.ConfigValue<Integer> PARTICLE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> PARTICLE_MULTIPLIER_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> PARTICLE_ID;

    //public static final ForgeConfigSpec.ConfigValue<Integer> VELOCITY_RANDOMNESS;
    //public static final ForgeConfigSpec.ConfigValue<Double> VELOCITY_RANDOMNESS_RANDOMNESS;

    static {
        BUILDER.push("Config for Cave Dust");

        //private int dimensionX = 5;
        DIMENSION_X = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.X.tooltip")))
                .defineInRange("dimensionX", 5, 1, 50);
        DIMENSION_Y = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.Y.tooltip")))
                .defineInRange("dimensionX", 5, 1, 50);
        DIMENSION_Z = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.Z.tooltip")))
                .defineInRange("dimensionX", 5, 1, 50);

        CAVE_DUST_ENABLED = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.enabled.tooltip")))
                .define("caveDustEnabled", true);

        SEA_LEVEL_CHECK = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.sealevelcheck.tooltip")))
                .define("seaLevelCheck", true);

        SUPERFLAT_STATUS = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.superflatstatus.tooltip")))
                .define("superFlatStatus", false);

        UPPER_LIMIT = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.upperlimit.tooltip")))
                .defineInRange("upperLimit", 64, 0, 255);

        LOWER_LIMIT = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.lowerlimit.tooltip")))
                        .defineInRange("lowerLimit", -64, -255, 0);


        PARTICLE_MULTIPLIER = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.particlemultiplier.tooltip")))
                .defineInRange("particleMultiplier", 1, 1, 100);

        PARTICLE_MULTIPLIER_MULTIPLIER = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.particlemultiplier.tooltip")))
                .defineInRange("particleMultiplierMultiplier", 10, 1, 100);

        PARTICLE_ID = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.particle.tooltip")))
                .defineInRange("particleID", WHITE_ASH_ID, 0, Registries.PARTICLE_TYPE.size());

        //VELOCITY_RANDOMNESS = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.velocityrandomness.tooltip")))
        //        .defineInRange("velocityRandomness", 1, 0, 10);
//
        //VELOCITY_RANDOMNESS_RANDOMNESS = BUILDER.comment(String.valueOf(Text.translatable("menu.cavedust.velocityrandomness.tooltip")))
        //        .defineInRange("velocityRandomness", generateRandomDouble(-VELOCITY_RANDOMNESS.get(), VELOCITY_RANDOMNESS.get()), 0.0d, 10.0d);





        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
