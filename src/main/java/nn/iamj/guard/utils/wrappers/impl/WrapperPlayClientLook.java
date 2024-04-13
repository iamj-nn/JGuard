package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayClientLook extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.LOOK;

    private final float yaw, pitch;
    private final boolean onGround;

    public WrapperPlayClientLook(final PacketContainer packet) {
        super(packet);

        final StructureModifier<Float> floats = handle.getFloat();

        this.yaw = floats.read(0);
        this.pitch = floats.read(1);
        this.onGround = handle.getBooleans().read(0);
    }

}
