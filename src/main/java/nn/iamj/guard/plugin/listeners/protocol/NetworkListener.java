package nn.iamj.guard.plugin.listeners.protocol;

import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import nn.iamj.guard.JGuard;
import nn.iamj.guard.loader.plugin.JGuardPlugin;
import nn.iamj.guard.plugin.player.JPlayer;
import nn.iamj.guard.plugin.process.ProcessPacket;
import nn.iamj.guard.utils.wrappers.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class NetworkListener extends PacketAdapter {

    public NetworkListener(final @NotNull JGuardPlugin plugin) {
        super(plugin, ListenerPriority.LOWEST, Arrays.asList(
                WrapperPlayClientChat.TYPE,
                WrapperPlayClientLook.TYPE,
                WrapperPlayClientPosition.TYPE,
                WrapperPlayClientPositionLook.TYPE,
                WrapperPlayClientSteerVehicle.TYPE,
                WrapperPlayClientTabComplete.TYPE,
                WrapperPlayClientVehicleMove.TYPE
        ));
    }

    @Override
    public void onPacketReceiving(final PacketEvent event) {
        if (event.isPlayerTemporary() || event.getPlayer() == null) return;

        final JPlayer player = JGuard.getInstance().getProfileManager().getPlayer(event.getPlayer().getUniqueId());

        if (player == null) return;

        player.process(new ProcessPacket(event.getPacket()));
    }


}
