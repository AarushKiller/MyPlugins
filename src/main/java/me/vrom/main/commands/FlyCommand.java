package me.vrom.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")) {
            if (!(sender instanceof Player)) {
                Bukkit.getConsoleSender().sendMessage("You can only run this as a player!");
            }
            Player player = (Player) sender;
            if (!player.hasPermission("fly.user")) {
                player.sendMessage(ChatColor.RED + "lmfao no bro xd");
                return true;
            }
            if (player.getAllowFlight() == true) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(ChatColor.RED + "Flight has been disabled!");
                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
            } else {
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(ChatColor.GREEN + "Flight has been enabled!");
                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                player.getActivePotionEffects().clear();
            }
        }
        return false;
    }

}