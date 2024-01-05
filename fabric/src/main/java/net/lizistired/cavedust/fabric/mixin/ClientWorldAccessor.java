package net.lizistired.cavedust.fabric.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientWorld.Properties.class)
public interface ClientWorldAccessor {
    @Accessor
    boolean getFlatWorld();
}

