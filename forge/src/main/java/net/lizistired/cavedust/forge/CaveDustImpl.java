package net.lizistired.cavedust.forge;

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
    public static void createCaveDust(MinecraftClient client) {
        //ensure world is not null
        if (client.world == null) return;
        World world = client.world;
        //LOGGER.info(String.valueOf(((ClientWorldAccessor) client.world.getLevelProperties()).getFlatWorld()));
        // )
        double probabilityNormalized = normalize(ConfigForge.LOWER_LIMIT.get(), ConfigForge.UPPER_LIMIT.get(), client.player.getBlockY());
        PARTICLE_AMOUNT = (int) (probabilityNormalized * ConfigForge.PARTICLE_MULTIPLIER.get() * ConfigForge.PARTICLE_MULTIPLIER_MULTIPLIER.get());

        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            try {
                int x = (int) (client.player.getPos().getX() + (int) generateRandomDouble(ConfigForge.DIMENSION_X.get() * -1, ConfigForge.DIMENSION_X.get()));
                int y = (int) (client.player.getPos().getY() + (int) generateRandomDouble(ConfigForge.DIMENSION_Y.get() * -1, ConfigForge.DIMENSION_Y.get()));
                int z = (int) (client.player.getPos().getZ() + (int) generateRandomDouble(ConfigForge.DIMENSION_Z.get() * -1, ConfigForge.DIMENSION_Z.get()));
                BlockPos particlePos = new BlockPos(x, y, z);

                if (shouldParticlesSpawn(client, particlePos)) {
                    if (client.world.getBlockState(particlePos).isAir()) {
                        world.addParticle(CaveDustConfigScreen.getParticle(), x, y, z, 0,  0, 0);
                    }
                }
            }
            catch (NullPointerException e) {
                LOGGER.error(String.valueOf(e));
                ConfigForge.PARTICLE_ID.set(WHITE_ASH_ID);
            }
        }
    }
}
