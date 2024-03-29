package net.lizistired.cavedust.fabric;

import com.minelittlepony.common.client.gui.GameGui;
import com.minelittlepony.common.client.gui.element.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import static net.lizistired.cavedust.fabric.CaveDustFabric.config;

public class ModMenuConfigScreen extends GameGui {
    public ModMenuConfigScreen(@Nullable Screen parent) {
        super(Text.translatable("menu.cavedust.title"), parent);
    }

    @Override
    public void init() {
        int left = width / 2 - 100;
        int row = height / 4 + 14;

        TranslatableTextHelper transText = new TranslatableTextHelper();;
        config.load();

        addButton(new Label(width / 2, 30)).setCentered().getStyle()
                .setText(getTitle());

        addButton(new Button(left += -110, row += -60).onClick(sender -> {
            sender.getStyle().setText("menu.cavedust.global." + config.toggleCaveDust()).setTooltip(Text.translatable("menu.cavedust.global.tooltip." + config.getCaveDustEnabled()));
        })).getStyle()
                .setText("menu.cavedust.global." + config.getCaveDustEnabled())
                .setTooltip(Text.translatable("menu.cavedust.global.tooltip." + config.getCaveDustEnabled()));

        /*addButton(new Button(left, row += 24).onClick(sender -> {
            sender.getStyle().setText("menu.cavedust.enhanceddetection." + config.setEnhancedDetection()).setTooltip(Text.translatable("menu.cavedust.enhanceddetection.tooltip"));
        })).getStyle()
                .setText("menu.cavedust.enhanceddetection." + config.getEnhancedDetection())
                .setTooltip(Text.translatable("menu.cavedust.enhanceddetection.tooltip"));*/

        addButton(new Button(left, row += 24).onClick(sender -> {
            sender.getStyle().setText("menu.cavedust.superflatstatus." + config.setSuperFlatStatus()).setTooltip(Text.translatable("menu.cavedust.superflatstatus.tooltip"));
        })).getStyle()
                .setText("menu.cavedust.superflatstatus." + config.getSuperFlatStatus())
                .setTooltip(Text.translatable("menu.cavedust.superflatstatus.tooltip"));



        /*addButton(new Slider(left, row += 48, -64, 319, config.getUpperLimit()))
                .onChange(config::setUpperLimit)
                .setTextFormat(transText::formatUpperLimit)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.upperlimit.tooltip"));

        addButton(new Slider(left, row += 24, -64, 319, config.getLowerLimit()))
                .onChange(config::setLowerLimit)
                .setTextFormat(transText::formatLowerLimit)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.lowerlimit.tooltip"));*/

        addButton(new Slider(left, row += 24, 1, 100, config.getParticleMultiplier()))
                .onChange(config::setParticleMultiplier)
                .setTextFormat(transText::formatParticleMultiplier)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.particlemultiplier.tooltip"));

        addButton(new Slider(left, row += 24, 1, 100, config.getParticleMultiplierMultiplier()))
                .onChange(config::setParticleMultiplierMultiplier)
                .setTextFormat(transText::formatParticleMultiplierMultiplier)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.particlemultipliermultiplier.tooltip"));
        addButton(new Button(left, row += 24).onClick(sender ->{
            config.iterateParticle();
            sender.getStyle().setText("Particle: " + (getNameOfParticle()));
        })).getStyle().setText("Particle: " + (getNameOfParticle()))
                .setTooltip(Text.translatable("menu.cavedust.particle.tooltip"));

        addButton(new Slider(left += 220, row -= 96, 1, 50, config.getDimensionsX()))
                .onChange(config::setDimensionsX)
                .setTextFormat(transText::formatMaxX)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.X.tooltip"));

        addButton(new Slider(left, row += 24, 1, 50, config.getDimensionsY()))
                .onChange(config::setDimensionsY)
                .setTextFormat(transText::formatMaxY)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.Y.tooltip"));

        addButton(new Slider(left, row += 24, 1, 50, config.getDimensionsZ()))
                .onChange(config::setDimensionsZ)
                .setTextFormat(transText::formatMaxZ)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.Z.tooltip"));

        addButton(new Slider(left, row += 24, 0, 10, config.getVelocityRandomness()))
                .onChange(config::setVelocityRandomness)
                .setTextFormat(transText::formatVelocityRandomness)
                .getStyle().setTooltip(Text.translatable("menu.cavedust.velocityrandomness.tooltip"));


        addButton(new Button(left -= 110, row += 60).onClick(sender -> {
            config.resetConfig();
            finish();
        })).getStyle().setText(Text.translatable("menu.cavedust.reset")).setTooltip(Text.translatable("menu.cavedust.reset.tooltip"));

        addButton(new Button(left, row += 24)
                .onClick(sender -> finish())).getStyle()
                .setText("gui.done");

    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, partialTicks);
    }
    private String getNameOfParticle(){
        config.load();
        return Registries.PARTICLE_TYPE.getEntry(config.getParticleID()).get().getKey().get().getValue().toString();
    }
}
