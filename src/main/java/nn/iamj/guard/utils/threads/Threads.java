package nn.iamj.guard.utils.threads;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Threads {

    private static final ExecutorService service = Executors.newFixedThreadPool(4);

    private Threads() {}

    public static void execute(final @NotNull Runnable runnable) {
        if (service.isShutdown())
            return;

        service.execute(runnable);
    }

    public static void shutdown() {
        if (!service.isShutdown())
            service.shutdownNow();
    }

}
