package net.lizistired.cavedust.forge;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.mojang.logging.LogUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.slf4j.Logger;

import java.io.File;

public class ForgeOptions extends GameOptions {
    static final Logger LOGGER = LogUtils.getLogger();
    static final Gson GSON = new Gson();

    private static final Splitter COLON_SPLITTER = Splitter.on(':').limit(2);

    public static final String EMPTY_STRING = "";

    private final SimpleOption<Double> viewDistance1;

    private final File optionsFile;

    public SimpleOption getViewDistance1(){
        return this.viewDistance1;
    }
    public ForgeOptions(MinecraftClient client, File optionsFile) {
        super(client, optionsFile);
        this.optionsFile = new File(optionsFile, "options.txt");
        this.viewDistance1 = new SimpleOption("options.renderDistance", SimpleOption.emptyTooltip(), (optionText, value) -> {
            return getGenericValueText(optionText, Text.translatable("options.chunks", new Object[]{value}));
        }, new SimpleOption.ValidatingIntSliderCallbacks(2, 16), 8, (value) -> {
        });
    }

    public File getOptionsFile() {
        return this.optionsFile;
    }
}
