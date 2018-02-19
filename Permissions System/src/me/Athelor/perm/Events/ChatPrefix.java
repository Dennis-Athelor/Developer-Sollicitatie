package me.Athelor.perm.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Athelor.perm.Permission;
import me.Athelor.perm.PlayerManager;

public class ChatPrefix implements Listener {
    public ChatPrefix() {
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String group = PlayerManager.getGroup(p).toString();
        if(Permission.groupConfig.getString(group + ".chatprefix") != null && !e.getMessage().startsWith("/")) {
            String format = Permission.groupConfig.getString(group + ".chatprefix");
            e.setCancelled(true);
            Bukkit.broadcastMessage(format.replace("&", "§") + "§7" + p.getName() + ": " + e.getMessage());
        } else if(Permission.groupConfig.getString(group + ".chatprefix") != null && e.getMessage().startsWith("/")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
            Bukkit.broadcastMessage("§7" + p.getDisplayName() + " §7: " + e.getMessage());
        }

    }
}
