package dev.skillcode.gtbsolver;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = GTBSolver.MODID, version = GTBSolver.VERSION)
public class GTBSolver {
    public static final String MODID = "GTB";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TickListener());
        System.out.println("Hi");
    }
}
