package nn.iamj.guard.utils.wrappers;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@Getter
public abstract class PacketWrapper {

    @MonotonicNonNull
    protected final PacketContainer handle;

    protected PacketWrapper(final PacketContainer handle) {
        if (handle == null)
            throw new IllegalArgumentException("Packet handle cannot be NULL.");
        this.handle = handle;
    }

    public void sendPacket(final Player receiver) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(receiver,
                    getHandle());
        } catch (Exception ignored) {}
    }

    public void broadcastPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(getHandle());
    }

    public void receivePacket(final Player sender) {
        try {
            ProtocolLibrary.getProtocolManager().receiveClientPacket(sender,
                    getHandle());
        } catch (Exception ignored) {}
    }

}
