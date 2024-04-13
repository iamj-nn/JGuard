package nn.iamj.guard.loader.plugin;

import com.comphenix.protocol.ProtocolLibrary;
import nn.iamj.guard.JGuard;
import nn.iamj.guard.loader.JGuardLoader;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

public final class JGuardPlugin extends JavaPlugin {

    @MonotonicNonNull
    private static JGuard loader;

    public JGuardPlugin() {
        loader = new JGuardLoader(this);
    }

    @Override
    public void onLoad() {
        loader.prepare();
    }

    @Override
    public void onEnable() {
        loader.initialize();
    }

    @Override
    public void onDisable() {
        loader.shutdown();

        ProtocolLibrary.getProtocolManager().removePacketListeners(this);
        HandlerList.unregisterAll(this);

        loader = null;
    }

}
