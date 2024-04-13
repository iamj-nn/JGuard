package nn.iamj.guard.plugin.check;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CheckType {

    INVALID_POSITION("InvalidPosition"),
    FLIGHT("Flight"),

    INVALID_ROTATION("InvalidRotation"),

    INVALID_PACKET("InvalidPacket"),
    INVALID_CAMERA("InvalidCamera"),
    INVALID_SPRINT("InvalidSprint"),
    INVALID_INTERACT("InvalidInteract"),
    INVALID_RIDING("InvalidRiding"),
    INVENTORY("Inventory");

    private final String name;

}
