package net.lizistired.cavedust.forge;

import dev.architectury.platform.forge.EventBuses;
import net.lizistired.cavedust.CaveDust;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static net.lizistired.cavedust.ParticleSpawnUtil.shouldParticlesSpawn;

@Mod(CaveDust.MOD_ID)
public class CaveDustForge {
    public CaveDustForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CaveDust.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        //Path CaveDustFolder = ExampleExpectPlatformImpl.getConfigDirectory();
        //config = new CaveDustConfig(CaveDustFolder.getParent().resolve("cavedust.json"));
        //config.load();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigForge.SPEC, "cavedust.toml");
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> new CaveDustConfigScreen(Text.of("Cave Dust Config"), screen)));
        CaveDust.initializeClient();
    }
}
