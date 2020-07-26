package org.cryptomator.frontend.fuse;

import org.cryptomator.frontend.fuse.mount.BypassedFuseFS;

public interface FuseNioAdapter extends BypassedFuseFS {

	boolean isMounted();
}
