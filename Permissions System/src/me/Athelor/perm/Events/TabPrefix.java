package me.Athelor.perm.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Athelor.perm.Permission;
import me.Athelor.perm.PlayerManager;

public class TabPrefix implements Listener {
    public TabPrefix() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        this.setPrefix(PlayerManager.getGroup(p).toString(), p);
    }

    public void setPrefix(String groupname, Player p) {
        if(Permission.groupConfig.getString(groupname + ".tabprefix") != null) {
            String prefix = Permission.groupConfig.getString(groupname + ".tabprefix").replace("&", "§") + p.getName();
            p.setPlayerListName(prefix);
        }

    }
}
