package me.vrom.main.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Events implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("blockplace.use"))
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("blockbreak.use"))
        e.setCancelled(true);
    }

    @EventHandler
    public void PlayerEntityDamage(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getDamager();
        if (!player.hasPermission("pvp.use"))
            if (e.getDamager() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                        player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Error!" + ChatColor.GRAY + " You can't damage players");
                        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);

                    e.setCancelled(true);
            } else {
                    if (e.getEntity() instanceof NPC) {
                        if (!player.hasPermission("pvp.use"))
                        e.setCancelled(true);
                        player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Error!" + ChatColor.GRAY + " You can't damage NPCs!");
                        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
                    } else {
                        e.setCancelled(false);
                    }
                }
        } else {
                e.setCancelled(false);
            }
    }

}
