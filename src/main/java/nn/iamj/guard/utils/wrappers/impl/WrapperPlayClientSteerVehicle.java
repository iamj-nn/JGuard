package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public class WrapperPlayClientSteerVehicle extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.STEER_VEHICLE;

    private final float sideways, forward;
    private final boolean jump, unmount;

    public WrapperPlayClientSteerVehicle(final PacketContainer packet) {
        super(packet);

        handle.getModifier().writeDefaults();

        this.sideways = handle.getFloat().read(0);
        this.forward = handle.getFloat().read(1);

        this.jump = handle.getBooleans().read(0);
        this.unmount = handle.getBooleans().read(1);
    }

}
