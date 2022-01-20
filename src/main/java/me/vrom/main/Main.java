package me.vrom.main;

import me.clip.placeholderapi.PlaceholderAPI;

import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.vrom.main.NPC.NPCManager;
import me.vrom.main.commands.TutorialCommand;
import me.vrom.main.commands.FlyCommand;
import me.vrom.main.events.Events;
import me.vrom.main.events.JoinScoreboardEvent;
import net.jitse.npclib.NPCLib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.NPC;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private static NPCManager npcManager;
    private NPCLib library;

    public NPCLib getNPCLib() {
        return library;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Aarush v6 has been enabled!");
        registerCommands();
        registerEvents();
        ScoreboardLib.setPluginInstance(this);
        this.npcManager = new NPCManager(this);
        this.library = new NPCLib(this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Aarush v6 has been disabled!");
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
