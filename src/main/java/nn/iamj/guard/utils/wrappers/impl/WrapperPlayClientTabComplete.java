package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayClientTabComplete extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.TAB_COMPLETE;

    private final String sequence;

    public WrapperPlayClientTabComplete(final PacketContainer packet) {
        super(packet);

        this.sequence = packet.getStrings().read(0);
    }

}
