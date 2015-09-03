package com.epickrram.lockit;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public final class NoOpClipboardOwner implements ClipboardOwner
{
    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents)
    {

    }
}
