package nn.iamj.guard.plugin.process;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import lombok.Getter;
import nn.iamj.guard.utils.wrappers.impl.*;

@Getter
public class ProcessPacket {

    private final PacketContainer container;
    private final ProcessType type;

    private WrapperPlayClientTabComplete tabCompleteWrapper;
    private WrapperPlayClientChat chatWrapper;
    private WrapperPlayClientPosition positionWrapper;
    private WrapperPlayClientPositionLook positionLookWrapper;
    private WrapperPlayClientLook lookWrapper;
    private WrapperPlayClientVehicleMove vehicleMoveWrapper;
    private WrapperPlayClientSteerVehicle steerVehicleWrapper;

    public ProcessPacket(final PacketContainer container) {
        this.container = container;
        this.type = ProcessType.from(container);

        if (this.type == null) return;

        switch (this.type) {
            case STEER_VEHICLE:
                this.steerVehicleWrapper = new WrapperPlayClientSteerVehicle(container);
                break;
            case VEHICLE_MOVE:
                this.vehicleMoveWrapper = new WrapperPlayClientVehicleMove(container);
                break;
            case CHAT:
                this.chatWrapper = new WrapperPlayClientChat(container);
                break;
            case POSITION:
                this.positionWrapper = new WrapperPlayClientPosition(container);
                break;
            case POSITION_LOOK:
                this.positionLookWrapper = new WrapperPlayClientPositionLook(container);
                break;
            case LOOK:
                this.lookWrapper = new WrapperPlayClientLook(container);
                break;
            case TAB_COMPLETE:
                this.tabCompleteWrapper = new WrapperPlayClientTabComplete(container);
                break;
        }
    }

    public boolean is(final ProcessType type) {
        return type == this.type;
    }

    public enum ProcessType {
        POSITION,
        POSITION_LOOK,
        LOOK,
        CHAT,
        STEER_VEHICLE,
        TAB_COMPLETE,
        VEHICLE_MOVE;

        private static ProcessType from(final PacketContainer container) {
            final PacketType type = container.getType();

            try {
                return ProcessType.valueOf(type.isClient() ? type.name() : null);
            } catch (Exception exception) {
                return null;
            }
        }

    }

}
