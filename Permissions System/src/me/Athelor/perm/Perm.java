package me.Athelor.perm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class Perm {
	public Perm() {
	}
	@SuppressWarnings("all")
	public static ArrayList<String> getGroups() {
		ArrayList<String> groups = new ArrayList();
		ConfigurationSection cs = Permission.groupConfig.getConfigurationSection("");
		Iterator var3 = cs.getKeys(false).iterator();
		
		while(var3.hasNext()) {
            String group = (String)var3.next();
            if(!groups.contains(group)) {
                groups.add(group);
            }
        }
        return groups;
	}
	public static void addPermToGroup(String groupname, String permname) {
		List<String> perms = getPermsFromGroup(groupname);
		perms.add(permname);
		Permission.groupConfig.set(groupname + ".permissions", perms);
		Permission.plugin.groupConfigSave();
	}
	
	public static void removePermFromGroup(String groupname, String permname) {
        List<String> perms = getPermsFromGroup(groupname);
        perms.remove(permname);
        Permission.groupConfig.set(groupname + ".permissions", perms);
        Permission.plugin.groupConfigSave();
    }

    public static List<String> getPermsFromGroup(String groupname) {
        List<String> perms = Permission.groupConfig.getStringList(groupname + ".permissions");
        return perms;
    }
      
    public static String getDefaultGroup() {
            ConfigurationSection cs = Permission.groupConfig.getConfigurationSection("");
            @SuppressWarnings("rawtypes")
			Iterator var2 = cs.getKeys(false).iterator();

            while(var2.hasNext()) {
                String group = (String)var2.next();
                if(Permission.groupConfig.getBoolean(group + ".default")) {
                    return group;
                }
            }

            return null;
        }
    }