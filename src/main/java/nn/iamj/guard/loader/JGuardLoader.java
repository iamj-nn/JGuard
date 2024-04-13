package nn.iamj.guard.loader;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import nn.iamj.guard.JGuard;
import nn.iamj.guard.loader.plugin.JGuardPlugin;
import nn.iamj.guard.managers.impl.ConfigManager;
import nn.iamj.guard.managers.impl.PlayerManager;
import nn.iamj.guard.plugin.listeners.ConnectionListener;
import nn.iamj.guard.plugin.listeners.protocol.NetworkListener;
import nn.iamj.guard.utils.threads.Threads;
import org.bukkit.plugin.PluginManager;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jetbrains.annotations.NotNull;

@Getter
public final class JGuardLoader implements JGuard {

    @MonotonicNonNull @Getter
    private static JGuard instance;

    @MonotonicNonNull
    private final JGuardPlugin plugin;

    @MonotonicNonNull
    private final ConfigManager configManager;
    @MonotonicNonNull
    private final PlayerManager profileManager;

    public JGuardLoader(final @NotNull JGuardPlugin plugin) {
        instance = this;

        this.plugin = plugin;

        this.configManager = new ConfigManager();
        this.profileManager = new PlayerManager();
    }

    @Override
    public void prepare() {
        this.configManager.initialize();
        this.profileManager.initialize();
    }

    @Override
    public void initialize() {
        final PluginManager pluginManager = this.plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(), this.plugin);

        final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.addPacketListener(new NetworkListener(this.plugin));
    }

    @Override
    public void shutdown() {
        this.profileManager.shutdown();
        this.configManager.shutdown();

        Threads.shutdown();

        instance = null;
    }

}
