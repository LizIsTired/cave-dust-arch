package net.lizistired.cavedust.fabric;

import com.minelittlepony.common.util.GamePaths;
import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.ClientModInitializer;
import net.lizistired.cavedust.CaveDust;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.nio.file.Path;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.lizistired.cavedust.CaveDust.*;
import static net.lizistired.cavedust.fabric.KeybindingHelper.*;
import static net.lizistired.cavedust.utils.MathHelper.generateRandomDouble;
import static net.lizistired.cavedust.utils.MathHelper.normalize;
import static net.lizistired.cavedust.utils.ParticleSpawnUtil.shouldParticlesSpawn;

public class ExampleModFabric implements ClientModInitializer {
    public static net.lizistired.cavedust.CaveDustConfig config;
    @Override
    public void onInitializeClient() {
        ClientTickEvent.CLIENT_POST.register(ExampleModFabric::createCaveDust);
        god1();
        Path CaveDustFolder = GamePaths.getConfigDirectory().resolve("cavedust");
        config = new net.lizistired.cavedust.CaveDustConfig(CaveDustFolder.getParent().resolve("cavedust.json"));
        config.load();
        registerKeyBindings();
    }

    public static void god1(){
        CaveDust.initializeClient();
    }

    void god3() {
        if (KeybindingHelper.keyBinding1.wasPressed()) {
            config.toggleCaveDust();
            LOGGER.info("Toggled dust");
            MinecraftClient.getInstance().player.sendMessage(Text.translatable("debug.cavedust.toggle." + config.getCaveDustEnabled()), false);
        }
        if (keyBinding2.wasPressed()) {
            config.load();
            LOGGER.info("Reloaded config");
            MinecraftClient.getInstance().player.sendMessage(Text.translatable("debug.cavedust.reload"), false);
        }
    }
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

                if (shouldParticlesSpawn(client, config, particlePos)) {
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
