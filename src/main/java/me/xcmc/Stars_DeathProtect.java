package me.xcmc;

import me.xcmc.listener.PlayerDeath;
import me.xcmc.listener.PlayerRespawn;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Stars_DeathProtect extends JavaPlugin {
    public static Stars_DeathProtect main;
    private static Economy economy=null;
    public static File config;
    public static FileConfiguration cc;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        Loadconfig();

        main = this;
        getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(this),this);

        if (!setupEconomy() ) {
            getLogger().info("Vault插件没有找到，或者请检查Vault插件安装是否正确");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("插件成功加载！");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
    public static Economy getEconomy() {
        return economy;
    }
    @Override
    public void onDisable() {
        saveResource("config.yml",false);
    }
    public void Loadconfig(){
        config = new File(getDataFolder(),"config.yml");
        if (!config.exists()){
            saveResource("config.yml",false);
        }
        cc = YamlConfiguration.loadConfiguration(config);
        System.out.println("config.yml文件已加载完毕！");
    }
}
