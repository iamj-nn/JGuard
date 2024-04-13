package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayServerKickDisconnect extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Server.KICK_DISCONNECT;

    private WrappedChatComponent reason;

    public WrapperPlayServerKickDisconnect() {
        super(new PacketContainer(TYPE));

        handle.getModifier().writeDefaults();

        this.reason = handle.getChatComponents().read(0);
    }

    public WrapperPlayServerKickDisconnect(final PacketContainer packet) {
        super(packet);

        this.reason = handle.getChatComponents().read(0);
    }

    public void setReason(final WrappedChatComponent value) {
        handle.getChatComponents().write(0, value);

        this.reason = value;
    }

}

