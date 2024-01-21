package net.lizistired.cavedust.forge;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ButtonWidgetMine extends ButtonWidget {
    protected ButtonWidgetMine(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, message, onPress, narrationSupplier);
    }
}
