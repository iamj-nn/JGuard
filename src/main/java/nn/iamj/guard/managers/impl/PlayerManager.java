package nn.iamj.guard.managers.impl;

import nn.iamj.guard.managers.Manager;
import nn.iamj.guard.plugin.player.JPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class PlayerManager implements Manager {

    private final Map<UUID, JPlayer> playerList = new ConcurrentHashMap<>();

    @Override
    public void initialize() {
        Bukkit.getOnlinePlayers().forEach(this::initializePlayer);
    }

    public void initializePlayer(final Player player) {
        final UUID uniqueId = player.getUniqueId();

        if (this.playerList.containsKey(uniqueId))
            return;

        this.playerList.put(uniqueId, new JPlayer(player));
    }

    public JPlayer getPlayer(final UUID uniqueId) {
        return this.playerList.get(uniqueId);
    }

    public void removePlayer(final UUID uniqueId) {
        this.playerList.remove(uniqueId);
    }

    @Override
    public void shutdown() {
        this.playerList.clear();
    }

}
