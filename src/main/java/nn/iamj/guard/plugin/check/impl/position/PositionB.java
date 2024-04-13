package nn.iamj.guard.plugin.check.impl.position;

import nn.iamj.guard.plugin.check.Check;
import nn.iamj.guard.plugin.check.CheckType;
import nn.iamj.guard.plugin.player.JPlayer;
import nn.iamj.guard.plugin.process.ProcessPacket;
import nn.iamj.guard.utils.wrappers.impl.WrapperPlayClientPosition;
import nn.iamj.guard.utils.wrappers.impl.WrapperPlayClientPositionLook;

public final class PositionB extends Check {

    public PositionB(final JPlayer player) {
        super(player, CheckType.INVALID_POSITION);
    }

    @Override
    public void process(final ProcessPacket packet) {
        double x = 0.0D, y = 0.0D, z = 0.0D;

        switch (packet.getType()) {
            case POSITION:
                final WrapperPlayClientPosition position = packet.getPositionWrapper();

                x = Math.abs(position.getX());
                y = Math.abs(position.getY());
                z = Math.abs(position.getZ());

                break;
            case POSITION_LOOK:
                final WrapperPlayClientPositionLook positionLook = packet.getPositionLookWrapper();

                x = Math.abs(positionLook.getX());
                y = Math.abs(positionLook.getY());
                z = Math.abs(positionLook.getZ());

                break;
        }

        final double fX = (int) x;
        final double fY = (int) y;
        final double fZ = (int) z;

        final double mX = Math.abs(x - fX);
        final double mY = Math.abs(y - fY);
        final double mZ = Math.abs(z - fZ);

        if (!Double.isFinite(mX) ||
                !Double.isFinite(mY) ||
                !Double.isFinite(mZ)) exploit();
    }

}

