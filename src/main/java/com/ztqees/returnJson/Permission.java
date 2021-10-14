package com.ztqees.returnJson;

/**
 * @author zhouqien
 * @date 2021-10-14 11:15
 */
public class Permission {
    private String permissionId;

    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }

    public Permission() {
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                '}';
    }
}
