package net.lizistired.cavedust.forge;

import net.minecraft.text.Text;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeSliderButMine extends ForgeSlider {
    long startTime = System.currentTimeMillis();
    private int timer = 0;
    final ForgeConfigSpec.ConfigValue<Integer> configOption;

    /**
     * @param x            x position of upper left corner
     * @param y            y position of upper left corner
     * @param width        Width of the widget
     * @param height       Height of the widget
     * @param prefix       {@link Text} displayed before the value string
     * @param suffix       {@link Text} displayed after the value string
     * @param minValue     Minimum (left) value of slider
     * @param maxValue     Maximum (right) value of slider
     * @param currentValue Starting value when widget is first displayed
     * @param stepSize     Size of step used. Precision will automatically be calculated based on this value if this value is not 0.
     * @param precision    Only used when {@code stepSize} is 0. Limited to a maximum of 4 (inclusive).
     * @param drawString   Should text be displayed on the widget
     * @param configOption
     */
    public ForgeSliderButMine(int x, int y, int width, int height, Text prefix, Text suffix, double minValue, double maxValue, double currentValue, double stepSize, int precision, boolean drawString, ForgeConfigSpec.ConfigValue<Integer> configOption) {
        super(x, y, width, height, prefix, suffix, minValue, maxValue, currentValue, stepSize, precision, drawString);
        this.configOption = configOption;
    }

    @Override
    protected void updateMessage() {
            setMessage(Text.literal(prefix.getString() + getValueString()));
    }
    @Override
    public void applyValue(){

    }

    /**
     * @deprecated bad code ignore this
     */
    @Deprecated
    public void doTheThing(){
        configOption.set((int) getValue());
    }

}
