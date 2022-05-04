package dev.skillcode.gtbsolver;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Arrays;

@Mod(modid = GTBSolver.MODID, version = GTBSolver.VERSION)
public class GTBSolver {

    public static final String MODID = "GTB";
    public static final String VERSION = "1.0";
    public static final String PREFIX = "§6§lGTBSolver §8» ";
    public static final String AUTHOR = "SkillCode [https://skillcode.dev]";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TickListener());
        registerCommandAliases(
                "gtb",
                "gtbs",
                "gtbsolver",
                "guessthebuild",
                "guessthebuildsolver"
        );
    }

    private void registerCommandAliases(final String... aliases) {
        Arrays.stream(aliases).forEach(this::registerCommandAlias);
    }

    private void registerCommandAlias(final String alias) {
        ClientCommandHandler.instance.registerCommand(new CommandBase() {
            @Override
            public String getCommandName() {
                return alias;
            }

            @Override
            public String getCommandUsage(final ICommandSender sender) {
                return "";
            }

            @Override
            public void processCommand(final ICommandSender sender, final String[] args) throws CommandException {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(PREFIX + "§7GTBSolver version §e" + VERSION + " by §e" + AUTHOR));
            }

            @Override
            public int getRequiredPermissionLevel() {
                return 0;
            }
        });
    }
}
