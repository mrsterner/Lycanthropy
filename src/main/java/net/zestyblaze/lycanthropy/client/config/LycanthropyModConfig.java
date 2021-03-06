package net.zestyblaze.lycanthropy.client.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.zestyblaze.lycanthropy.Lycanthropy;

@Config(name = Lycanthropy.MODID)
public class LycanthropyModConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart
    public boolean debugMode = false;

    @ConfigEntry.Gui.RequiresRestart
    public boolean modelBook3D = true;

    public boolean werewolfStart = false;

    public int werewolfVisionDistance = 64;

    public static LycanthropyModConfig get() {
        return AutoConfig.getConfigHolder(LycanthropyModConfig.class).getConfig();
    }
}
