package net.lizistired.cavedust;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.world.biome.BiomeKeys.LUSH_CAVES;

public class ParticleSpawnUtil {
    private static float timer;
    public static boolean shouldParticlesSpawn;

    /**
     * Returns true if particles should spawn.
     * @param client MinecraftClient
     * @param config CaveDustConfig
     * @return boolean
     */
    public static boolean shouldParticlesSpawn(MinecraftClient client, CaveDustConfig config) {

        //checks if the config is enabled, if the game isn't paused, if the world is valid, if the particle is valid and if the player isn't in a lush caves biome
        if (!config.getCaveDustEnabled()
                || client.isPaused()
                || client.world == null
                || !client.world.getDimension().bedWorks()
                || Objects.requireNonNull(client.player).isSubmergedInWater()
                || client.world.getBiome(Objects.requireNonNull(client.player.getBlockPos())).matchesKey(LUSH_CAVES))
        {
            timer = 0;
            shouldParticlesSpawn = false;
            return false;
        }

        World world = client.world;
        int seaLevel = world.getSeaLevel();

        if (!client.player.clientWorld.isSkyVisible(client.player.getBlockPos())) {
            if (client.player.getBlockPos().getY() + 2 < seaLevel){
                timer = timer + 1;
                if (timer > 10){
                    timer = 10;
                    shouldParticlesSpawn = true;
                    return true;
                }
            }
        }
        shouldParticlesSpawn = false;
        return false;
    }

    /**
     * Returns true if particles should spawn (uses particle position instead of player).
     * @param client MinecraftClient
     * @param pos BlockPos
     * @return boolean
     */

    @ExpectPlatform
    public static boolean shouldParticlesSpawn(MinecraftClient client, BlockPos pos) {
        throw new AssertionError();
    }
}
