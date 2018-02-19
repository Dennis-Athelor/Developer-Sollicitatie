package me.Athelor.perm.Commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Athelor.perm.Perm;
import me.Athelor.perm.Permission;

public class GroupCMD implements CommandExecutor {
    public GroupCMD() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        List<String> players = new ArrayList();
        players.add("REMOVE ME!");
        players.add("REMOVE ME!!");
        players.add("REMOVE ME!!!");
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(!p.hasPermission("permissions.group")) {
                p.sendMessage("§cYou dont heb permission!");
            } else if(p.hasPermission("permissions.group")) {
                if(args.length == 0) {
                    this.sendGroupHelp(p);
                } else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("list")) {
                        Iterator var8 = Perm.getGroups().iterator();

                        while(var8.hasNext()) {
                            String all = (String)var8.next();
                            p.sendMessage("§2" + all);
                        }
                    } else {
                        this.sendGroupHelp(p);
                    }
                } else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("delete")) {
                        if(Permission.groupConfig.get(args[1]) != null) {
                            if(Permission.groupConfig.getBoolean(args[1] + ".default")) {
                                p.sendMessage("§cYou can not delete the default group!");
                            } else {
                            	Permission.groupConfig.set(args[1], (Object)null);
                            	Permission.plugin.groupConfigSave();
                                p.sendMessage("§7The group §c" + args[1] + " §7was successfully deleted!");
                            }
                        } else {
                            p.sendMessage("§cThis group does not exist!");
                        }
                    } else if(args[0].equalsIgnoreCase("create")) {
                        if(Permission.groupConfig.get(args[1]) == null) {
                        	Permission.groupConfig.set(args[1] + ".default", Boolean.valueOf(false));
                        	Permission.groupConfig.set(args[1] + ".permissions", players);
                        	Permission.plugin.groupConfigSave();
                            p.sendMessage("§7The group §c" + args[1] + " §7was successfully created!");
                        } else {
                            p.sendMessage("§cThis group already exists!");
                        }
                    } else {
                        this.sendGroupHelp(p);
                    }
                } else if(args.length == 3) {
                    List perms;
                    if(args[0].equalsIgnoreCase("remove")) {
                        perms = Permission.groupConfig.getStringList(args[1] + ".permissions");
                        if(Permission.groupConfig.get(args[1]) != null) {
                            if(perms.contains(args[2])) {
                                perms.remove(args[2]);
                                Permission.groupConfig.set(args[1] + ".permissions", perms);
                                Permission.plugin.groupConfigSave();
                                p.sendMessage("§7The permission has been successfully removed!");
                            } else {
                                p.sendMessage("§7This group does not have the permission!");
                            }
                        } else {
                            p.sendMessage("§7This group does not exist!");
                        }
                    } else if(args[0].equalsIgnoreCase("add")) {
                        perms = Permission.groupConfig.getStringList(args[1] + ".permissions");
                        if(Permission.groupConfig.get(args[1]) != null) {
                            if(!perms.contains(args[2])) {
                                perms.add(args[2]);
                                Permission.groupConfig.set(args[1] + ".permissions", perms);
                                Permission.plugin.groupConfigSave();
                                p.sendMessage("§7The permission was successfully added!");
                            } else {
                                p.sendMessage("§7This group already owns the permission!");
                            }
                        } else {
                            p.sendMessage("§7This group does not exist!");
                        }
                    } else {
                        this.sendGroupHelp(p);
                    }
                }
            }
        } else {
            sender.sendMessage("This ia allowed for a player");
        }

        return false;
    }

    public void sendGroupHelp(Player p) {
        p.sendMessage("§c/Group add <group> §8- §7Add a perm to a group!");
        p.sendMessage("§c/Group remove <group> §8- §7Remove a perm from a group!");
        p.sendMessage("§c/Group create <groupname> §8- §7Create a new group!");
        p.sendMessage("§c/Group delete <groupname> §8- §7Delete a group!");
        p.sendMessage("§c/Group list §8- §7See the list of groups");
    }
}
