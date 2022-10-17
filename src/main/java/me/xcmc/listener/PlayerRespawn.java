package me.xcmc.listener;

import me.xcmc.Stars_DeathProtect;
import me.xcmc.task.Js;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    private Js taskTools;
    private Stars_DeathProtect plugin;

    public PlayerRespawn(Stars_DeathProtect plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        taskTools=new Js(plugin);

        Player player = event.getPlayer();
        taskTools.RunnableTool(player);
    }
}
