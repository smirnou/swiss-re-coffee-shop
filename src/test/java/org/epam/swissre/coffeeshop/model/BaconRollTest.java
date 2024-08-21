package org.epam.swissre.coffeeshop.model;

import org.epam.swissre.coffeeshop.enums.BaconRollSize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link BaconRoll}.
 */
public class BaconRollTest {

    /**
     * Test the creation of BaconRoll with valid size.
     */
    @Test
    public void testBaconRollCreation_ValidSize() {
        // Example test with the STANDARD size
        BaconRoll baconRoll = new BaconRoll(BaconRollSize.STANDARD);
        assertNotNull(baconRoll, "BaconRoll should be created");
        assertEquals(BaconRollSize.STANDARD.getDisplayName(), baconRoll.getName(), "BaconRoll name should match the size display name");
        assertEquals(BaconRollSize.STANDARD.getPriceInCHF(), baconRoll.getPrice(), "BaconRoll price should match the size price in CHF");
    }
}