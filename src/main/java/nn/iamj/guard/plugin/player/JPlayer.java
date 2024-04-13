package nn.iamj.guard.plugin.player;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import lombok.Getter;
import nn.iamj.guard.plugin.check.loader.CheckLoader;
import nn.iamj.guard.plugin.process.ProcessPacket;
import nn.iamj.guard.utils.threads.Threads;
import nn.iamj.guard.utils.wrappers.impl.WrapperPlayServerKickDisconnect;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
public class JPlayer {

    private final Player player;
    private final UUID uniqueId;

    private final CheckLoader loader;

    public JPlayer(final @NotNull Player player) {
        this.player = player;
        this.uniqueId = player.getUniqueId();

        this.loader = new CheckLoader(this);
        this.loader.load();
    }

    public void process(final ProcessPacket packet) {
        if (player == null) return;

        Threads.execute(() -> this.loader.run(packet));
    }

    public void punish(final String reason) {
        if (this.player == null) return;

        final WrapperPlayServerKickDisconnect kick = new WrapperPlayServerKickDisconnect();
        kick.setReason(WrappedChatComponent.fromText(
                ChatColor.translateAlternateColorCodes('&', reason)));

        kick.sendPacket(this.player);
    }

}
