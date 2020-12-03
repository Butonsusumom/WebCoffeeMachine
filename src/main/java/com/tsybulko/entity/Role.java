package com.tsybulko.entity;

/**
 * Enum which represents roles.
 */
public enum Role {
    /**
     * User role.
     */
    USER(1),
    /**
     * Admin role.
     */
    ADMIN(2),
    /**
     * Guest role.
     */
    GUEST(3);

    /**
     * Role id in DB.
     */
    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Getting role by id.
     *
     * @param id role id.
     * @return role.
     */
    public static Role getRoleById(int id) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}
