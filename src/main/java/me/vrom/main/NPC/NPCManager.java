package me.vrom.main.NPC;

import me.vrom.main.Main;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.state.NPCSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class NPCManager implements Listener {
    private Main main;
    private NPCLib npcLib;

    private NPC gameLobbyNPC;

    public NPCManager(Main main) {
        this.main = main;
        this.npcLib = new NPCLib(main);
        load();
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    private void load() {
        int skinID = 277513;
        MineSkinFetcher.fetchSkinFromIdAsync(277513, skin -> {
            gameLobbyNPC = npcLib.createNPC(Arrays.asList(ChatColor.GOLD.toString() + ChatColor.BOLD + "Game Lobbies", ChatColor.YELLOW.toString() + ChatColor.BOLD + "CLICK TO JOIN"));
            gameLobbyNPC.setLocation(new Location(Bukkit.getWorlds().get(0), 118, 86 , 2, 0, 0));
            gameLobbyNPC.setItem(NPCSlot.MAINHAND, new ItemStack(Material.DIAMOND_SWORD));
            gameLobbyNPC.setSkin(skin);
            gameLobbyNPC.create();
        });
    }

    @EventHandler
    public void onNPCInteract(NPCInteractEvent event) {
        Player player = event.getWhoClicked();
        if(event.getNPC() == gameLobbyNPC) {
            player.sendMessage(ChatColor.RED + "This is a cool message!");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        gameLobbyNPC.show(event.getPlayer());
    }

}
