package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayClientChat extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.CHAT;

    private final String message;

    public WrapperPlayClientChat(final PacketContainer packet) {
        super(packet);

        this.message = handle.getStrings().read(0);
    }

}
