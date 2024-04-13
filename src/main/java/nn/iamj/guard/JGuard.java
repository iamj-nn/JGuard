package nn.iamj.guard;

import nn.iamj.guard.loader.JGuardLoader;
import nn.iamj.guard.loader.plugin.JGuardPlugin;
import nn.iamj.guard.managers.impl.ConfigManager;
import nn.iamj.guard.managers.impl.PlayerManager;

public interface JGuard {

    void prepare();
    void initialize();
    void shutdown();

    ConfigManager getConfigManager();
    PlayerManager getProfileManager();

    JGuardPlugin getPlugin();
    static JGuard getInstance() {
        return JGuardLoader.getInstance();
    }

}
