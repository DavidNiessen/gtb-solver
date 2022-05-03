package dev.skillcode.gtbsolver;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Optional;

public class ActionBarHelper {

    private ActionBarHelper() {
    }

    public static Optional<String> getActionBarContent() {
        try {
            final String actionBar = (String) ReflectionHelper
                    .findField(GuiIngame.class, "displayedActionBar", "field_73838_g")
                    .get(Minecraft.getMinecraft().ingameGUI);
            return Optional.of(actionBar);
        } catch (IllegalAccessException e) {
            // ignore
        }
        return Optional.empty();
    }

    public static Boolean isGTBActionBar(final String actionBar) {
        return actionBar.contains("§b")
                && actionBar.contains("§e")
                && actionBar.contains("_");
    }

}
