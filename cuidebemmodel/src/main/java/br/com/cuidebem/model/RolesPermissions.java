/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author aleci
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "roles_permissions", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPermissions.findAll", query = "SELECT r FROM RolesPermissions r")
    , @NamedQuery(name = "RolesPermissions.findByRolename", query = "SELECT r FROM RolesPermissions r WHERE r.rolesPermissionsPK.rolename = :rolename")
    , @NamedQuery(name = "RolesPermissions.findByPermissions", query = "SELECT r FROM RolesPermissions r WHERE r.rolesPermissionsPK.permissions = :permissions")})
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPermissionsPK rolesPermissionsPK;
    @JoinColumn(name = "rolename", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roles roles;

    public RolesPermissions() {
    }

    public RolesPermissions(RolesPermissionsPK rolesPermissionsPK) {
        this.rolesPermissionsPK = rolesPermissionsPK;
    }

    public RolesPermissions(String rolename, String permissions) {
        this.rolesPermissionsPK = new RolesPermissionsPK(rolename, permissions);
    }

    public RolesPermissionsPK getRolesPermissionsPK() {
        return rolesPermissionsPK;
    }

    public void setRolesPermissionsPK(RolesPermissionsPK rolesPermissionsPK) {
        this.rolesPermissionsPK = rolesPermissionsPK;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    
       @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPermissionsPK != null ? rolesPermissionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermissions)) {
            return false;
        }
        RolesPermissions other = (RolesPermissions) object;
        if ((this.rolesPermissionsPK == null && other.rolesPermissionsPK != null) || (this.rolesPermissionsPK != null && !this.rolesPermissionsPK.equals(other.rolesPermissionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.RolesPermissions[ rolesPermissionsPK=" + rolesPermissionsPK + " ]";
    }
    
}
