package com.testnisum.api_rest.models.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {

    USER(Arrays.asList(
            Permission.READ_ALL_USERS,
            Permission.READ_USER_BY_ID,
            Permission.UPDATE_USER,
            Permission.DELETE_USER
    )
    );

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
