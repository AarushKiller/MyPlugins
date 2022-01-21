package me.vrom.main.events;

import me.vrom.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Landmines implements Listener {

    @EventHandler
    public void onLandminePlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.STONE_PLATE) {
            Main.getLandmines().mines.add(e.getBlock());
            e.getPlayer().sendMessage(ChatColor.GREEN + "You have placed a landmine!");
        }
    }

    @EventHandler
    public void onLandminePress(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL && Main.getLandmines().mines.contains(e.getClickedBlock())) {
            TNTPrimed tnt = e.getClickedBlock().getWorld().spawn(e.getClickedBlock().getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(1);
            tnt.setIsIncendiary(true);
        }
    }


}
