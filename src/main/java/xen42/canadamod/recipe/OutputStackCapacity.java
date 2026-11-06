package xen42.canadamod.recipe;

/**
 * Pure stack-capacity logic, independent of Minecraft runtime initialization.
 * Item identity and data-component compatibility are checked by the caller.
 */
public final class OutputStackCapacity {
    private OutputStackCapacity() {}

    public static boolean canFit(int existing, int incoming, int maximum) {
        return existing >= 0
                && incoming > 0
                && maximum > 0
                && existing <= maximum
                && incoming <= maximum - existing;
    }
}
