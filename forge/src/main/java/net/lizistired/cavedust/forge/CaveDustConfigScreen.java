package net.lizistired.cavedust.forge;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.IExtensionPoint;

import static net.lizistired.cavedust.CaveDust.LOGGER;

import java.util.List;
import java.util.function.Supplier;

public class CaveDustConfigScreen extends Screen implements IExtensionPoint {
    private final Screen previous;
    private final List<ButtonWidget> buttons = Lists.newArrayList();

    private static final Text EXAMPLE_BUTTON = Text.translatable("menu.cavedust.examplebutton");

    private final int imagewidth, imageheight;
    private int leftpos, toppos;

    private Element optionsArray[] = new Element[10];
    public static final Identifier OPTIONS_BACKGROUND_TEXTURE = new Identifier("textures/gui/demo_background.png");



    private int buttonWidth;




    protected CaveDustConfigScreen(Text title, Screen previous) {
        super(Text.translatable("menu.cavedust.title"));
        this.imagewidth = 176;
        this.imageheight = 166;
        this.previous = previous;
    }
    @Override
    public final void init(){
        super.init();
        this.leftpos = width / 2 - 100;
        this.toppos = height / 4 + 14;
        var mouseX = 0;
        var mouseY = 0;

        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("menu.cavedust.global." + ConfigForge.CAVE_DUST_ENABLED.get()),
                        btn -> {
                            ConfigForge.CAVE_DUST_ENABLED.set(!ConfigForge.CAVE_DUST_ENABLED.get());
                            btn.setMessage(Text.translatable("menu.cavedust.global." + ConfigForge.CAVE_DUST_ENABLED.get()));
                        }
                ).dimensions(leftpos += -110, toppos += -60, 200, 20).build()
        );
        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("menu.cavedust.superflatstatus." + ConfigForge.SUPERFLAT_STATUS.get()),
                        btn -> {
                            ConfigForge.SUPERFLAT_STATUS.set(!ConfigForge.SUPERFLAT_STATUS.get());
                            btn.setMessage(Text.translatable("menu.cavedust.superflatstatus." + ConfigForge.SUPERFLAT_STATUS.get()));
                        }
                ).dimensions(leftpos, toppos += 24, 200, 20).build()
        );
        optionsArray[0] = addDrawableChild(
                new ForgeSliderButMine(
                        leftpos,
                        toppos += 24,
                        200,
                        20,
                        Text.translatable("menu.cavedust.particlemultiplier.forge"),
                        Text.of(""),
                        1,
                        100,
                        ConfigForge.PARTICLE_MULTIPLIER.get(),
                        1,
                        0,
                        true,
                        ConfigForge.PARTICLE_MULTIPLIER
                )
        );
        optionsArray[1] = addDrawableChild(
                new ForgeSliderButMine(
                        leftpos,
                        toppos += 24,
                        200,
                        20,
                        Text.translatable("menu.cavedust.particlemultipliermultiplier.forge"),
                        Text.of(""),
                        1,
                        100,
                        ConfigForge.PARTICLE_MULTIPLIER_MULTIPLIER.get(),
                        1,
                        0,
                        true,
                        ConfigForge.PARTICLE_MULTIPLIER_MULTIPLIER
                )
        );
        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("menu.cavedust.particle").append(Text.literal(getNameOfParticle())),
                        btn -> {
                            if (ConfigForge.PARTICLE_ID.get() == Registries.PARTICLE_TYPE.size() - 1) {
                                ConfigForge.PARTICLE_ID.set(1);
                                btn.setMessage(Text.translatable("menu.cavedust.particle").append(Text.literal(getNameOfParticle())));
                            } else {
                                ConfigForge.PARTICLE_ID.set(ConfigForge.PARTICLE_ID.get() + 1);
                                btn.setMessage(Text.translatable("menu.cavedust.particle").append(Text.literal(getNameOfParticle())));
                            }
                        }
                ).dimensions(leftpos, toppos  += 24, 200, 20).build()
        );
        optionsArray[2] = addDrawableChild(
                new ForgeSliderButMine(
                        leftpos += 220,
                        toppos -= 96,
                        200,
                        20,
                        Text.translatable("menu.cavedust.X.forge"),
                        Text.of(""),
                        1,
                        50,
                        ConfigForge.DIMENSION_X.get(),
                        1,
                        0,
                        true,
                        ConfigForge.DIMENSION_X
                )
        );
        optionsArray[3] = addDrawableChild(
                new ForgeSliderButMine(
                        leftpos,
                        toppos += 24,
                        200,
                        20,
                        Text.translatable("menu.cavedust.Y.forge"),
                        Text.of(""),
                        1,
                        50,
                        ConfigForge.DIMENSION_Y.get(),
                        1,
                        0,
                        true,
                        ConfigForge.DIMENSION_Y
                )
        );
        optionsArray[4] = addDrawableChild(
                new ForgeSliderButMine(
                        leftpos,
                        toppos += 24,
                        200,
                        20,
                        Text.translatable("menu.cavedust.Z.forge"),
                        Text.of(""),
                        1,
                        50,
                        ConfigForge.DIMENSION_Z.get(),
                        1,
                        0,
                        true,
                        ConfigForge.DIMENSION_Z
                )
        );
        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("menu.cavedust.reset"),
                        btn -> resetSettings()
                ).dimensions(leftpos -= 110, toppos += 120, 200, 20).build()
        );
        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("menu.cavedust.apply"),
                        btn -> {
                            applyValue(optionsArray);
                        }
                ).dimensions(leftpos, toppos += 24, 200, 20).build()
        );
        addDrawableChild(
                ButtonWidget.builder(
                        Text.translatable("gui.done"),
                        btn -> {
                            client.setScreen(this.previous);
                            applyValue(optionsArray);
                        }
                ).dimensions(leftpos, toppos += 24, 200, 20).build()
        );
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(context, mouseX, mouseY, delta);
    }

    //@Override
    //public void renderBackgroundTexture(DrawContext context){
    //    context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
    //    context.drawTexture(OPTIONS_BACKGROUND_TEXTURE, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 32, 32);
    //    context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    //}

    private String getNameOfParticle(){
        return Registries.PARTICLE_TYPE.getEntry(ConfigForge.PARTICLE_ID.get()).get().getKey().get().getValue().toString();
    }

    public static ParticleEffect getParticle(){
        try {
            return (ParticleEffect) Registries.PARTICLE_TYPE.get(new Identifier(Registries.PARTICLE_TYPE.getEntry(ConfigForge.PARTICLE_ID.get()).get().getKey().get().getValue().toString().toLowerCase()));
        } catch (ClassCastException e) {
            MinecraftClient.getInstance().player.sendMessage(Text.literal("Issue loading particle, defaulting to white ash particle!"), false);
            ConfigForge.PARTICLE_ID.set(Registries.PARTICLE_TYPE.getRawId(ParticleTypes.WHITE_ASH));
            return ParticleTypes.WHITE_ASH;
        }
    }

    private void applyValue(Element[] optionsArray) {
        for (Element element : optionsArray) {
            if (element instanceof ForgeSliderButMine) {
                ((ForgeSliderButMine) element).doTheThing();
            }
        }
    }

    private void resetSettings() {
        ConfigForge.CAVE_DUST_ENABLED.set(ConfigForge.CAVE_DUST_ENABLED.getDefault());
        ConfigForge.SUPERFLAT_STATUS.set(ConfigForge.SUPERFLAT_STATUS.getDefault());
        ConfigForge.UPPER_LIMIT.set(ConfigForge.UPPER_LIMIT.getDefault());
        ConfigForge.LOWER_LIMIT.set(ConfigForge.LOWER_LIMIT.getDefault());
        ConfigForge.PARTICLE_MULTIPLIER.set(ConfigForge.PARTICLE_MULTIPLIER.getDefault());
        ConfigForge.PARTICLE_MULTIPLIER_MULTIPLIER.set(ConfigForge.PARTICLE_MULTIPLIER_MULTIPLIER.getDefault());
        ConfigForge.DIMENSION_X.set(ConfigForge.DIMENSION_X.getDefault());
        ConfigForge.DIMENSION_Y.set(ConfigForge.DIMENSION_Y.getDefault());
        ConfigForge.DIMENSION_Z.set(ConfigForge.DIMENSION_Z.getDefault());
        ConfigForge.PARTICLE_ID.set(Registries.PARTICLE_TYPE.getRawId(ParticleTypes.WHITE_ASH));
        this.client.setScreen(this.previous);
    }

    @Override
    public void close() {
        this.client.setScreen(this.previous);
    }

    private ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier) {
        return ButtonWidget.builder(message, (button) -> {
            this.client.setScreen((Screen)screenSupplier.get());
        }).build();
    }
}
