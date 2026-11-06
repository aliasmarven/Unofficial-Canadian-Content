package xen42.canadamod.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputStackCapacityTest {

    @Test
    void emptySlotAcceptsValidResult() {
        assertTrue(OutputStackCapacity.canFit(0, 1, 64));
    }

    @Test
    void exactCapacityIsAllowed() {
        assertTrue(OutputStackCapacity.canFit(63, 1, 64));
    }

    @Test
    void fullStackRejectsAdditionalItems() {
        assertFalse(OutputStackCapacity.canFit(64, 1, 64));
    }

    @Test
    void oversizedResultIsRejected() {
        assertFalse(OutputStackCapacity.canFit(0, 65, 64));
    }

    @Test
    void nonPositiveIncomingCountIsRejected() {
        assertFalse(OutputStackCapacity.canFit(0, 0, 64));
        assertFalse(OutputStackCapacity.canFit(0, -1, 64));
    }

    @Test
    void invalidCapacityAndExistingCountAreRejected() {
        assertFalse(OutputStackCapacity.canFit(-1, 1, 64));
        assertFalse(OutputStackCapacity.canFit(65, 1, 64));
        assertFalse(OutputStackCapacity.canFit(0, 1, 0));
    }
}
