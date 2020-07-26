package org.cryptomator.frontend.fuse.mount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.serce.jnrfuse.FuseStubFS;

public abstract class BypassedAdapter extends FuseStubFS implements BypassedFuseFS {

	private static final Logger LOG = LoggerFactory.getLogger(BypassedAdapter.class);

	@Override
	public void umount(boolean bypass) {
		if(bypass) {
			umount();
		} else {
			super.umount();
		}
	}

	/*
	 * We overwrite the default implementation to skip the "internal" unmount command,
	 * because we want to use system commands instead for Mac and Linux.
	 * See also: https://github.com/cryptomator/fuse-nio-adapter/issues/29
	 *
	 * Use #umount(false) to access the original super method.
	 */
	@Override
	public void umount() {
		// this might be called multiple times: explicitly _and_ via a shutdown hook registered during mount() in AbstractFuseFS
		if (mounted.compareAndSet(true, false)) {
			LOG.debug("Marked file system adapter as unmounted.");
		} else {
			LOG.trace("File system adapter already unmounted.");
		}
	}
}