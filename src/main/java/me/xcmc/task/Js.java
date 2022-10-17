package me.xcmc.task;

import me.xcmc.Stars_DeathProtect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Js {
    private Stars_DeathProtect plugin;

    public Js(Stars_DeathProtect plugin){
        this.plugin = plugin;
    }

    public void RunnableTool(Player p){
        new BukkitRunnable() {
            private int i=5;
            @Override
            public void run() {
                if(i==0){
                    this.cancel();
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
                else{
                    p.sendTitle("§a距离死亡保护结束还有"+"§4" + i +"§a秒","§a请双击空格飞到§b安全§a的地方",10,20,20);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                }
                i--;
            }
        }.runTaskTimer(plugin,20L,20L);
    }
}
