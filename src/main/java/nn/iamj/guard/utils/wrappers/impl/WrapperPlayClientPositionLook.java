package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayClientPositionLook extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.POSITION_LOOK;

    private final double x, y, z;
    private final float yaw, pitch;
    private final boolean onGround;

    public WrapperPlayClientPositionLook(final PacketContainer packet) {
        super(packet);

        StructureModifier<Double> doubles = handle.getDoubles();
        StructureModifier<Float> floats = handle.getFloat();

        this.x = doubles.read(0);
        this.y = doubles.read(1);
        this.z = doubles.read(2);

        this.yaw = floats.read(0);
        this.pitch = floats.read(1);

        this.onGround = handle.getBooleans().read(0);
    }

}
