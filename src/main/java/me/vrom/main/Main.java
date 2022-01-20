package me.vrom.main;

import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.vrom.main.commands.TutorialCommand;
import me.vrom.main.commands.FlyCommand;
import me.vrom.main.events.Events;
import me.vrom.main.events.JoinScoreboardEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Aarush v5 has been enabled!");
        registerCommands();
        registerEvents();
        ScoreboardLib.setPluginInstance(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Aarush v5 has been disabled!");
    }



    public void registerCommands() {
        getCommand("tutorial").setExecutor(new TutorialCommand());
        getCommand("fly").setExecutor(new FlyCommand());
    }

    public void registerEvents() {
        PluginManager pm  = this.getServer().getPluginManager();
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new JoinScoreboardEvent(), this);
    }


}
