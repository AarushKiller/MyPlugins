package me.vrom.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class LandmineGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("landmine")) {
            if (!(sender instanceof Player)) {
                Bukkit.getConsoleSender().sendMessage("You can only run this as a player!");
            }
            Player player = (Player) sender;
            if (!player.hasPermission("landmine.give")) {
                player.sendMessage(ChatColor.RED + "Error! you do not have permission to do this!");
                return true;
            } else {
                Material type;
                ItemStack stone = new ItemStack(Material.STONE_PLATE);
                ItemMeta meta = stone.getItemMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Landmines"));
                meta.setLore(Arrays.asList("Place this item", "to make a landmine!"));
                stone.setItemMeta(meta);
            }
        }
        return true;
    }
}
