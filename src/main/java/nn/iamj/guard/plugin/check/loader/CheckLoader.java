package nn.iamj.guard.plugin.check.loader;

import nn.iamj.guard.plugin.check.Check;
import nn.iamj.guard.plugin.check.impl.position.PositionA;
import nn.iamj.guard.plugin.check.impl.position.PositionB;
import nn.iamj.guard.plugin.player.JPlayer;
import nn.iamj.guard.plugin.process.ProcessPacket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CheckLoader {

    private final JPlayer player;
    private final List<Check> checks;

    public CheckLoader(final @NotNull JPlayer player) {
        this.player = player;
        this.checks = new ArrayList<>();
    }

    public void run(final ProcessPacket packet) {
        this.checks.forEach(check ->
                check.process(packet));
    }

    public void load() {
        if (this.player.getPlayer().hasPermission("jguard.bypass")) return;

        this.checks.addAll(Arrays.asList(
                new PositionA(this.player),
                new PositionB(this.player)
        ));
    }

    public void reset() {
        this.checks.clear();
    }

}
