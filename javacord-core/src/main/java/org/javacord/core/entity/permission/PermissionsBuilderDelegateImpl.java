package org.javacord.core.entity.permission;

import org.javacord.api.entity.permission.PermissionState;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.internal.PermissionsBuilderDelegate;

/**
 * The implementation of {@link PermissionsBuilderDelegate}.
 */
public class PermissionsBuilderDelegateImpl implements PermissionsBuilderDelegate {

    /**
     * The integer containing all allowed permission types
     */
    private int allowed = 0;

    /**
     * The integer containing all denied permission types.
     */
    private int denied = 0;

    /**
     * Creates a new permissions factory.
     */
    public PermissionsBuilderDelegateImpl() { }

    /**
     * Creates a new permissions factory with the states of the given permissions object.
     *
     * @param permissions The permissions which should be copied.
     */
    public PermissionsBuilderDelegateImpl(Permissions permissions) {
        allowed = permissions.getAllowedBitmask();
        denied = permissions.getDeniedBitmask();
    }

    @Override
    public void setState(PermissionType type, PermissionState state) {
        switch (state) {
            case ALLOWED:
                allowed = type.set(allowed, true);
                denied = type.set(denied, false);
                break;
            case DENIED:
                allowed = type.set(allowed, false);
                denied = type.set(denied, true);
                break;
            case NONE:
                allowed = type.set(allowed, false);
                denied = type.set(denied, false);
                break;
        }
    }

    @Override
    public PermissionState getState(PermissionType type) {
        if (type.isSet(allowed)) {
            return PermissionState.ALLOWED;
        }
        if (type.isSet(denied)) {
            return PermissionState.DENIED;
        }
        return PermissionState.NONE;
    }

    @Override
    public Permissions build() {
        return new PermissionsImpl(allowed, denied);
    }

}