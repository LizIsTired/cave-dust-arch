package net.lizistired.cavedust.fabric;

import com.minelittlepony.common.util.GamePaths;
import net.fabricmc.api.ClientModInitializer;
import net.lizistired.cavedust.CaveDust;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.nio.file.Path;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.lizistired.cavedust.fabric.KeybindingHelper.*;
import static net.lizistired.cavedust.ParticleSpawnUtil.shouldParticlesSpawn;

public class CaveDustFabric implements ClientModInitializer {
    public static net.lizistired.cavedust.CaveDustConfig config;
    @Override
    public void onInitializeClient() {

        Path CaveDustFolder = GamePaths.getConfigDirectory().resolve("cavedust");
        config = new net.lizistired.cavedust.CaveDustConfig(CaveDustFolder.getParent().resolve("cavedust.json"));
        config.load();
        registerKeyBindings();
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

}
