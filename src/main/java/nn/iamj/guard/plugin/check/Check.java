package nn.iamj.guard.plugin.check;

import lombok.Getter;
import nn.iamj.guard.api.ExploitAttemptEvent;
import nn.iamj.guard.plugin.player.JPlayer;
import nn.iamj.guard.plugin.process.ProcessPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class Check {

    protected final JPlayer player;

    private final CheckType checkType;
    private final String type;

    public Check(final @NotNull JPlayer player, final CheckType checkType, final String type) {
        this.player = player;

        this.checkType = checkType;
        this.type = type;
    }

    public Check(final @NotNull JPlayer player, final CheckType checkType) {
        this.player = player;

        this.checkType = checkType;
        this.type = "";
    }

    public void exploit() {
        final Player player = this.player.getPlayer();

        if (player == null || !player.isOnline())
            return;

        final ExploitAttemptEvent event = new ExploitAttemptEvent(
                player,
                this.checkType.getName(),
                this.type
        );

        Bukkit.getServer().getPluginManager().callEvent(event);

        this.player.punish("Exploit attempt.");
    }

    public abstract void process(final @NotNull ProcessPacket processPacket);

}
