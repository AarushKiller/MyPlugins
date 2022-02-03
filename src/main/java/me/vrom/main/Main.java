package me.vrom.main;

import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.vrom.main.NPC.NPCManager;
import me.vrom.main.commands.LandmineGive;
import me.vrom.main.commands.TutorialCommand;
import me.vrom.main.commands.FlyCommand;
import me.vrom.main.events.Events;
import me.vrom.main.events.JoinScoreboardEvent;
import me.vrom.main.events.Landmines;
import net.jitse.npclib.NPCLib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin implements Listener {

    private static NPCManager npcManager;
    private NPCLib library;
    private static Main Landmines;
    public ArrayList<Block> mines;

    public Main() {
        this.mines = new ArrayList<Block>();
    }

    public NPCLib getNPCLib() {
        return library;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Aarush has been enabled!");
        registerCommands();
        registerEvents();
        ScoreboardLib.setPluginInstance(this);
        this.npcManager = new NPCManager(this);
        this.library = new NPCLib(this);
        Main.Landmines = this;
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
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Aarush has been disabled!");
    }



    public void registerCommands() {
        getCommand("tutorial").setExecutor(new TutorialCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("landmine").setExecutor(new LandmineGive());
    }

    public void registerEvents() {
        PluginManager pm  = this.getServer().getPluginManager();
        pm.registerEvents(new Events(), this);
        pm.registerEvents(new JoinScoreboardEvent(), this);
        pm.registerEvents(new Landmines(), this);
    }

    public static Main getLandmines() {
        return Main.Landmines;
    }


}
