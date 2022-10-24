package me.xcmc.listener;

import me.xcmc.Stars_DeathProtect;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerDeath implements Listener {
    private Stars_DeathProtect plugin;
    public PlayerDeath(Stars_DeathProtect plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void Exp(PlayerDeathEvent e){
        if (Stars_DeathProtect.cc.getBoolean("Default.enable")){
            Player p = e.getEntity().getPlayer();
            assert p != null;
            World w = p.getLocation().getWorld();
            assert w != null;
            if (Stars_DeathProtect.cc.getStringList("Default.worlds").contains(w.getName())){
                if (Stars_DeathProtect.cc.getBoolean("Default.Exp.enable")){
                    if (e.getEntity().getPlayer().hasPermission("starsdeath.use")){
                        return;
                    }
                    e.setKeepLevel(false);
                    int expPercent = Stars_DeathProtect.cc.getInt("Default.Exp.expPercent");
                    int expLeft = Stars_DeathProtect.cc.getInt("Default.Exp.expLeft");
                    int exp = e.getEntity().getPlayer().getLevel();
                    double littleNumber = expPercent / 100.0;
                    int result = exp - (int) (exp * littleNumber);
                    String message1 = Stars_DeathProtect.cc.getString("Default.Exp.message1");
                    String message2 = Stars_DeathProtect.cc.getString("Default.Exp.message2");
                    if (exp >= expLeft){
                        e.setNewLevel(result);
                        assert message1 != null;
                        p.sendMessage(message1);
                    }else {
                        e.setNewLevel(exp);
                        assert message2 != null;
                        p.sendMessage(message2);
                    }
                }
                if (Stars_DeathProtect.cc.getBoolean("Default.Money.enable")){
                    if (e.getEntity().getPlayer().hasPermission("starsdeath.use")){
                        return;
                    }
                    int moneyPercent = Stars_DeathProtect.cc.getInt("Default.Money.moneyPercent");
                    int moneyLeft = Stars_DeathProtect.cc.getInt("Default.Money.moneyLeft");
                    double nowMoney = Stars_DeathProtect.getEconomy().getBalance(p.getName());
                    String message1 = Stars_DeathProtect.cc.getString("Default.Money.message1");
                    String message2 = Stars_DeathProtect.cc.getString("Default.Money.message2");
                    if (moneyLeft <= nowMoney){
                        if (nowMoney - moneyPercent > 0){
                            Stars_DeathProtect.getEconomy().withdrawPlayer(p.getName(),moneyPercent);
                            assert message1 != null;
                            p.sendMessage(message1);
                        }
                    }else {
                        assert message2 != null;
                        p.sendMessage(message2);
                    }
                }
            }
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                e.getEntity().spigot().respawn();
            }
        }.runTaskLater(plugin, 40L);
    }
}
