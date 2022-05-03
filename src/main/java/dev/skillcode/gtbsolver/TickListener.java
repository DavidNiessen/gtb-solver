package dev.skillcode.gtbsolver;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TickListener {

    private int ticks = 0;
    private List<String> lastWords = new ArrayList<>();

    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent tickEvent) {
        if (++ticks != 20) return;
        ticks = 0;

        final Optional<String> optionalActionBar = ActionBarHelper.getActionBarContent();
        if (!optionalActionBar.isPresent()) return;

        final String actionBar = optionalActionBar.get();
        if (!ActionBarHelper.isGTBActionBar(actionBar)) return;

        final List<String> words = GTBHelper.possibleWords(actionBar);
        if (words.equals(lastWords)) return;

        final String wordString = String.join("§8, §e", words);
        final String message = "§6§lGTBSolver §8» §e["
                + words.size()
                + "] §8: §e" + wordString;

        lastWords = words;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }
}
