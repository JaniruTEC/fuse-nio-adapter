package org.cryptomator.frontend.fuse.mount;

import ru.serce.jnrfuse.AbstractFuseFS;
import ru.serce.jnrfuse.FuseFS;

public interface BypassedFuseFS extends FuseFS, AutoCloseable {

	/**
	 * Sets mounted to false. This overrides the behavior of {@link AbstractFuseFS#umount()} and will not actually attempt to unmount the device.<br>
	 * Use {@link #umount(boolean) umount(false)} to access {@link AbstractFuseFS#umount()}.<br>
	 * See <a href="https://github.com/cryptomator/fuse-nio-adapter/issues/29">here</a> for the technical reasons for that approach.
	 *
	 * @see #umount(boolean)
	 */
	@Override
	void umount();

	/**
	 * Allows to access the non-bypassed/non-overridden version of {@link #umount()}, which means {@link AbstractFuseFS#umount()} instead of {@link BypassedAdapter#umount()}.<br>
	 * If {@code bypass} is set to {@code true} the changes introduced by this interface will be <i>active.</i><br>
	 * If {@code bypass} is set to {@code false} the changes introduced by this interface will be <i>inactive.</i><br>
	 *
	 * @param bypass true will call the overridden implementation of {@link #umount()}; false will call the implementation introduced in {@link AbstractFuseFS#umount()}.
	 */
	void umount(boolean bypass);
}