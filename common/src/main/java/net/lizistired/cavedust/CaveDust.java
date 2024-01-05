package net.lizistired.cavedust;

//minecraft imports
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
//other imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//java imports
//static imports
import static net.lizistired.cavedust.utils.ParticleSpawnUtil.shouldParticlesSpawn;


public class CaveDust {
	public static final String MOD_ID = "cavedust";
	//logger
	public static final Logger LOGGER = LoggerFactory.getLogger("cavedust");
	//config assignment

	public static int WHITE_ASH_ID = Registries.PARTICLE_TYPE.getRawId(ParticleTypes.WHITE_ASH);
	public static int PARTICLE_AMOUNT = 0;


	public static void initializeClient(){
		System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
		//config path and loading
	}
}
