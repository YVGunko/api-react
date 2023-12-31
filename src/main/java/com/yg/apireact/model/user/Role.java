package com.yg.apireact.model.user;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1001;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;
    
    public Role() {
    }
    public Role(Integer id) {
        this.id = id;
    }
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }    
    @Override
    public String getAuthority() {
        return getName();
    }
}
