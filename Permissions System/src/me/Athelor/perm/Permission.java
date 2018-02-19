package me.Athelor.perm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Athelor.perm.Commands.GroupCMD;
import me.Athelor.perm.Commands.PermCMD;
import me.Athelor.perm.Commands.UserCMD;
import me.Athelor.perm.Events.ChatPrefix;
import me.Athelor.perm.Events.JoinListener;
import me.Athelor.perm.Events.TabPrefix;

public class Permission extends JavaPlugin {
    public static File userFile = new File("plugins/Permissions/users.yml");
    public static File groupFile = new File("plugins/Permissions/groups.yml");
    public static File playerFile = new File("plugins/Permissions/players.yml");
    public static FileConfiguration userConfig;
    public static FileConfiguration groupConfig;
    public static FileConfiguration playerConfig;
    public static Permission plugin;

    static {
        userConfig = YamlConfiguration.loadConfiguration(userFile);
        groupConfig = YamlConfiguration.loadConfiguration(groupFile);
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
    }

    public Permission() {
    }

    public void onEnable() {
        plugin = this;
        this.saveDefault();
        getCommand("group").setExecutor(new GroupCMD());
        getCommand("user").setExecutor(new UserCMD());
        getCommand("perm").setExecutor(new PermCMD());
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new TabPrefix(), this);
    }

    public void onDisable() {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveDefault() {
        if(!userFile.exists()) {
            this.userConfigSave();
        }

        ArrayList players;
        if(!groupFile.exists()) {
            players = new ArrayList();
            players.add("PERMISSION");
            groupConfig.set("default.default", Boolean.valueOf(true));
            groupConfig.set("default.chatprefix", "&7Speler >> ");
            groupConfig.set("default.tabprefix", "&7Speler >> ");
            groupConfig.set("default.permissions", players);
            this.groupConfigSave();
        }

        if(!playerFile.exists()) {
            players = new ArrayList();
            players.add("REMOVE ME!");
            playerConfig.set("Players", players);

            try {
                playerConfig.save(playerFile);
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

    }

    public void userConfigSave() {
        try {
            userConfig.save(userFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void groupConfigSave() {
        try {
            groupConfig.save(groupFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void userConfigLoad() {
        try {
            userConfig.load(userFile);
        } catch (FileNotFoundException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (InvalidConfigurationException var4) {
            var4.printStackTrace();
        }

    }

    public void groupConfigLoad() {
        try {
            groupConfig.load(groupFile);
        } catch (FileNotFoundException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (InvalidConfigurationException var4) {
            var4.printStackTrace();
        }

    }
}
