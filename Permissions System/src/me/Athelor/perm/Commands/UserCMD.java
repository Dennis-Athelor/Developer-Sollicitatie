package me.Athelor.perm.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Athelor.perm.Perm;
import me.Athelor.perm.Permission;
import me.Athelor.perm.PlayerManager;

public class UserCMD implements CommandExecutor {
    public UserCMD() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(!p.hasPermission("permissions.user")) {
                p.sendMessage("§cYou dont heb permission!");
            } else if(p.hasPermission("permissions.user")) {
                if(args.length == 0) {
                    this.sendUserHelp(p);
                } else {
                    Player target;
                    if(args.length == 2) {
                        if(args[0].equalsIgnoreCase("info")) {
                            target = Bukkit.getPlayer(args[1]);
                            p.sendMessage("§c" + target.getName() + " §7Is in the group §c" + PlayerManager.getGroup(target));
                        } else if(args[0].equalsIgnoreCase("setdefault")) {
                            target = Bukkit.getPlayer(args[1]);
                            PlayerManager.setGroup(Perm.getDefaultGroup(), target);
                            target.kickPlayer("§cPermissions Reloaded\n§7Rejoin please!");
                            p.sendMessage("§7De player §c" + target.getName() + " §7is now the §cdefault §6group");
                        } else {
                            this.sendUserHelp(p);
                        }
                    } else if(args.length == 3) {
                        if(args[0].equalsIgnoreCase("set")) {
                            target = Bukkit.getPlayer(args[1]);
                            if(target == null) {
                                p.sendMessage("§7The player §c" + args[1] + " §7is not online!");
                                return false;
                            }

                            if(Permission.groupConfig.getString(args[2]) != null) {
                                PlayerManager.setGroup(args[2], target);
                                target.kickPlayer("§cPermissions Reloaded\n§7Rejoin please!");
                                p.sendMessage("§7De player §c" + target.getName() + " §7Is now in the group §c" + args[2]);
                            } else {
                                p.sendMessage("§7The group does not exist!");
                            }
                        } else {
                            this.sendUserHelp(p);
                        }
                    } else {
                        this.sendUserHelp(p);
                    }
                }
            }
        } else {
            sender.sendMessage("This is allowed for a player");
        }

        return false;
    }

    public void sendUserHelp(Player p) {
    	p.sendMessage("§c/user set <player> <group> §8- §7Set a player in a group!");
        p.sendMessage("§c/user remove <player> <group> §8- §7Remove a player from a group!");
        p.sendMessage("§c/user info <Player> §8- §7Get information of a player!");
    }
}