package com.secrething.entity;

public class WgRole {
    private Integer roleId;

    private String roleName;

    private Boolean permissionStatus;

    private Boolean permissionDataSearch;

    private Boolean permissionDataShar;

    private String roleDes;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Boolean getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Boolean permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public Boolean getPermissionDataSearch() {
        return permissionDataSearch;
    }

    public void setPermissionDataSearch(Boolean permissionDataSearch) {
        this.permissionDataSearch = permissionDataSearch;
    }

    public Boolean getPermissionDataShar() {
        return permissionDataShar;
    }

    public void setPermissionDataShar(Boolean permissionDataShar) {
        this.permissionDataShar = permissionDataShar;
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes == null ? null : roleDes.trim();
    }
}