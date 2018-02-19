package me.Athelor.perm;

import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {
    public PlayerManager() {
    }

    public static void initPerm(Player p, String groupname) {
        List perms;
        String permname;
        Iterator var4;
        if(Permission.userConfig.getString(p.getUniqueId().toString() + ".group") != null) {
            perms = Perm.getPermsFromGroup(groupname);
            var4 = perms.iterator();

            while(var4.hasNext()) {
                permname = (String)var4.next();
                p.addAttachment(Permission.plugin, permname, false);
            }
        }

        Permission.userConfig.set(p.getUniqueId().toString() + ".group", groupname);
        Permission.plugin.userConfigSave();
        Permission.plugin.userConfigLoad();
        perms = Perm.getPermsFromGroup(groupname);
        var4 = perms.iterator();

        while(var4.hasNext()) {
            permname = (String)var4.next();
            p.addAttachment(Permission.plugin, permname, true);
        }

    }

    public static String getGroup(Player p) {
        return Permission.userConfig.getString(p.getUniqueId().toString() + ".group");
    }

    public static void setGroup(String groupname, Player p) {
        initPerm(p, groupname);
    }

    public static Player nameToPlayer(String name) {
        Player p = Bukkit.getPlayer(name);
        return p;
    }

    public static String playerToName(Player p) {
        String name = p.getName();
        return name;
    }
}
