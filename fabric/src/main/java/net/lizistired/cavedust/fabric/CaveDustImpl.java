package net.lizistired.cavedust.fabric;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.lizistired.cavedust.CaveDust.PARTICLE_AMOUNT;
import static net.lizistired.cavedust.CaveDust.WHITE_ASH_ID;
import static net.lizistired.cavedust.utils.MathHelper.generateRandomDouble;
import static net.lizistired.cavedust.utils.MathHelper.normalize;
import static net.lizistired.cavedust.ParticleSpawnUtil.shouldParticlesSpawn;

public class CaveDustImpl {
    public static net.lizistired.cavedust.CaveDustConfig config;
    public static void createCaveDust(MinecraftClient client) {
        //ensure world is not null
        if (client.world == null) return;
        World world = client.world;
        //LOGGER.info(String.valueOf(((ClientWorldAccessor) client.world.getLevelProperties()).getFlatWorld()));
        // )
        double probabilityNormalized = normalize(config.getLowerLimit(), config.getUpperLimit(), client.player.getBlockY());
        PARTICLE_AMOUNT = (int) (probabilityNormalized * config.getParticleMultiplier() * config.getParticleMultiplierMultiplier());

        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            try {
                int x = (int) (client.player.getPos().getX() + (int) generateRandomDouble(config.getDimensionsX() * -1, config.getDimensionsX()));
                int y = (int) (client.player.getPos().getY() + (int) generateRandomDouble(config.getDimensionsY() * -1, config.getDimensionsY()));
                int z = (int) (client.player.getPos().getZ() + (int) generateRandomDouble(config.getDimensionsZ() * -1, config.getDimensionsZ()));
                BlockPos particlePos = new BlockPos(x, y, z);

                if (shouldParticlesSpawn(client, particlePos)) {
                    if (client.world.getBlockState(particlePos).isAir()) {
                        world.addParticle(config.getParticle(), x, y, z, config.getVelocityRandomnessRandom(), config.getVelocityRandomnessRandom(), config.getVelocityRandomnessRandom());
                    }
                }
            }
            catch (NullPointerException e) {
                LOGGER.error(String.valueOf(e));
                config.setParticleID(WHITE_ASH_ID);
            }
        }
    }
}
