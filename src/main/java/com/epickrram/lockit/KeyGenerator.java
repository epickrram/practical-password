package com.epickrram.lockit;

import org.bouncycastle.crypto.generators.SCrypt;

public final class KeyGenerator
{
    public static byte[] generateKey(final byte[] passphrase, final byte[] salt)
    {
        return SCrypt.generate(passphrase, salt,
                32, 8, 32, (Wheel.TOTAL_SPOKES * Wheel.DEPTH) +
                        (Wheel.TOTAL_SPOKES * Wheel.CHARS_PER_SPOKE_ENTRY * Wheel.DEPTH));
    }
}
