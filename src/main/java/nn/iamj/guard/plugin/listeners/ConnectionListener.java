package nn.iamj.guard.plugin.listeners;

import nn.iamj.guard.JGuard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class ConnectionListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(final PlayerJoinEvent event) {
        JGuard.getInstance().getProfileManager().initializePlayer(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDisconnect(final PlayerQuitEvent event) {
        JGuard.getInstance().getProfileManager().removePlayer(event.getPlayer().getUniqueId());
    }

}
