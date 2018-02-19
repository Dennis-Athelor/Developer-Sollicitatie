package me.Athelor.perm.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCMD implements CommandExecutor {
    public PermCMD() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(!p.hasPermission("permission.help")) {
                p.sendMessage("§cYou dont heb permission!");
            } else if(p.hasPermission("permission.help")) {
                this.sendHelp(p);
            }
        } else {
            sender.sendMessage("You are not a player");
        }

        return false;
    }

    public void sendHelp(Player p) {
        p.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        p.sendMessage("§c/user set <player> <group> §8- §7Set a player in a group!");
        p.sendMessage("§c/user remove <player> <group> §8- §7Remove a player from a group!");
        p.sendMessage("§c/user info <Player> §8- §7Get information of a player!");
        p.sendMessage("    ");
        p.sendMessage("§c/Group add <group> §8- §7Add a perm to a group!");
        p.sendMessage("§c/Group remove <group> §8- §7Remove a perm from a group!");
        p.sendMessage("§c/Group create <groupname> §8- §7Create a new group!");
        p.sendMessage("§c/Group delete <groupname> §8- §7Delete a group!");
        p.sendMessage("§c/Group list §8- §7See the list of groups");
        p.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        
        
        
    }
}
