package org.cryptomator.frontend.fuse;

import ru.serce.jnrfuse.FuseFS;

import java.nio.file.Path;

public interface FuseNioAdapter extends FuseFS, AutoCloseable {

	boolean isMounted();

	void umountForced();

	/**
	 * Sets mounted to false. Other than in {@link FuseFS#umount()} this will not actually attempt to unmount the device.
	 */
	@Override
	void umount();

	UnmounterFactory unmounterFactory();

	Runnable getForcedUnmounter();

	void setForcedUnmounter(Runnable forcedUnmounter);

	Runnable getUnmounter();

	void setUnmounter(Runnable unmounter);

	interface UnmounterFactory {

		Runnable fuseExitUnmounter();

		Runnable commandUnmounter(ProcessBuilder builder);

		Runnable commandUnmounter(String command, Path directory);

	}
}
