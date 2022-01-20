package me.vrom.main.events;

import me.clip.placeholderapi.PlaceholderAPI;

import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.tigerhix.lib.scoreboard.common.EntryBuilder;
import me.tigerhix.lib.scoreboard.common.Strings;
import me.tigerhix.lib.scoreboard.common.animate.HighlightedString;
import me.tigerhix.lib.scoreboard.common.animate.ScrollableString;
import me.tigerhix.lib.scoreboard.type.Entry;
import me.tigerhix.lib.scoreboard.type.Scoreboard;
import me.tigerhix.lib.scoreboard.type.ScoreboardHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class JoinScoreboardEvent implements Listener {

    public void scoreboard() {
        for (Player player: getServer().getOnlinePlayers()) {
            Scoreboard scoreboard = ScoreboardLib.createScoreboard(player)
                    .setHandler(new ScoreboardHandler() {


                        @Override
                        public String getTitle(Player player) {
                            return null;
                        }

                        @Override
                        public List<Entry> getEntries(Player player) {
                            String rank = "%vault_rank";
                            return new EntryBuilder()
                                    .next("&6&lIMPERIAL VALOR")
                                    .blank()
                                    .next("&cUSERNAME : %player_name%")
                                    .blank()
                                    .next("&cRANK : %vault_rank%")
                                    .blank()
                                    .next("&6github.com/AarushKiller")
                                    .build();
                        }

                    })
                    .setUpdateInterval(2l);
            scoreboard.activate();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        scoreboard();
    }

}
