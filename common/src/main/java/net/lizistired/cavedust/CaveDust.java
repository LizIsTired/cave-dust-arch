package net.lizistired.cavedust;

//minecraft imports
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
//other imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//java imports
//static imports


public class CaveDust {
	public static final String MOD_ID = "cavedust";
	//logger
	public static final Logger LOGGER = LoggerFactory.getLogger("cavedust");
	//config assignment

	public static int WHITE_ASH_ID = Registries.PARTICLE_TYPE.getRawId(ParticleTypes.WHITE_ASH);
	public static int PARTICLE_AMOUNT = 0;


	public static void initializeClient(){
		System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
		ClientTickEvent.CLIENT_POST.register(CaveDust::createCaveDust);
		//config path and loading
	}
	@ExpectPlatform
	public static void createCaveDust(MinecraftClient client) {
		throw new AssertionError();
	}
}
