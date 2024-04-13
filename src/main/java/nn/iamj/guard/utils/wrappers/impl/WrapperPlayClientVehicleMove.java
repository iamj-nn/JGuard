package nn.iamj.guard.utils.wrappers.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.PacketWrapper;

@Getter
public class WrapperPlayClientVehicleMove extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.VEHICLE_MOVE;

    private final double x, y, z;
    private final float yaw, pitch;

    public WrapperPlayClientVehicleMove(PacketContainer packet) {
        super(packet);

        this.x = handle.getDoubles().read(0);
        this.y = handle.getDoubles().read(1);
        this.z = handle.getDoubles().read(2);

        this.yaw = handle.getFloat().read(0);
        this.pitch = handle.getFloat().read(1);
    }

}