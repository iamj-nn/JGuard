package nn.iamj.guard.managers.impl;

import nn.iamj.guard.JGuard;
import nn.iamj.guard.loader.plugin.JGuardPlugin;
import nn.iamj.guard.managers.Manager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ConfigManager implements Manager {

    private final Map<String, YamlConfiguration> configList = new ConcurrentHashMap<>();

    @Override @SuppressWarnings("all")
    public void initialize() {
        final File directory = JGuard.getInstance().getPlugin().getDataFolder();
        if (!directory.exists())
            directory.mkdirs();

        // this.loadFiles("config.yml", "lang.yml");
    }

    public void loadFiles(final Iterable<String> paths) {
        if (paths == null) return;

        for (final String path : paths)
            this.loadFile(path);
    }

    public void loadFiles(final String... paths) {
        if (paths == null) return;

        for (final String path : paths)
            this.loadFile(path);
    }

    public void loadFile(final String path) {
        if (path == null) return;

        final JGuardPlugin plugin = JGuard.getInstance().getPlugin();
        final File file = new File(plugin.getDataFolder(), path);

        if (!file.exists()) plugin.saveResource(path, false);

        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        this.configList.put(path, configuration);
    }

    public YamlConfiguration getFile(final String path) {
        if (path == null) return null;

        return this.configList.get(path);
    }

    public void reload() {
        this.configList.forEach((string, yaml) -> {
            this.reloadFile(string);
        });
    }

    public void reloadFiles(final Iterable<String> paths) {
        if (paths == null) return;

        for (final String path : paths)
            this.reloadFile(path);
    }

    public void reloadFiles(final String... paths) {
        if (paths == null) return;

        for (final String path : paths)
            this.reloadFile(path);
    }

    public void reloadFile(final String path) {
        if (path == null || !this.configList.containsKey(path)) return;

        final JGuardPlugin plugin = JGuard.getInstance().getPlugin();
        final File file = new File(plugin.getDataFolder(), path);

        if (!file.exists()) plugin.saveResource(path, false);

        final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        this.configList.put(path, configuration);
    }

    @SuppressWarnings("all")
    public void saveFile(final String path, final YamlConfiguration configuration) {
        if (path == null || !this.configList.containsKey(path)) return;

        final JGuardPlugin plugin = JGuard.getInstance().getPlugin();
        final File file = new File(plugin.getDataFolder(), path);

        try {
            configuration.save(file);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        this.configList.clear();
    }

}
