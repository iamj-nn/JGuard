package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public final class WrapperPlayClientPosition extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.POSITION;

    private final double x, y, z;
    private final boolean onGround;

    public WrapperPlayClientPosition(final PacketContainer packet) {
        super(packet);

        final StructureModifier<Double> doubles = handle.getDoubles();

        this.x = doubles.read(0);
        this.y = doubles.read(1);
        this.z = doubles.read(2);

        this.onGround = handle.getBooleans().read(0);
    }

}
