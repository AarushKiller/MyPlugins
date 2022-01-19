package me.vrom.main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin logic at shutdown
    }



    public void registerCommands() {
        getCommand("tutorial").setExecutor(new Command());
        getCommand("fly").setExecutor(new FlyCommand());
    }

    public void registerEvents() {
        PluginManager pm  = this.getServer().getPluginManager();
        pm.registerEvents(new BlockEvents(), this);
    }

}
