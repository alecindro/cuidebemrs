/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aleci
 */
@Embeddable
public class RolesPermissionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String rolename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String permissions;

    public RolesPermissionsPK() {
    }

    public RolesPermissionsPK(String rolename, String permissions) {
        this.rolename = rolename;
        this.permissions = permissions;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolename != null ? rolename.hashCode() : 0);
        hash += (permissions != null ? permissions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermissionsPK)) {
            return false;
        }
        RolesPermissionsPK other = (RolesPermissionsPK) object;
        if ((this.rolename == null && other.rolename != null) || (this.rolename != null && !this.rolename.equals(other.rolename))) {
            return false;
        }
        if ((this.permissions == null && other.permissions != null) || (this.permissions != null && !this.permissions.equals(other.permissions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.RolesPermissionsPK[ rolename=" + rolename + ", permissions=" + permissions + " ]";
    }
    
}
