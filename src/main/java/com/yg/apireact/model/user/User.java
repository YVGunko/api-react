package com.yg.apireact.model.user;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5113923763634991903L;

	@Access(AccessType.PROPERTY)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NaturalId //Cache
	private String name;
	
	@NotNull
	@Column(name = "pswd", columnDefinition="default '012345'")
	private @JsonIgnore String pswd;
	
	@Column(name = "super_user", columnDefinition="bit default 0")
	private @JsonIgnore Boolean superUser;
	
	@Column(name = "external", columnDefinition="bit default 1")
	private Boolean external;
	
	@Column(name = "employee_id", columnDefinition="BIGINT(20) default '0'")
	private Long employee_id;
	
	@Column(name = "filial_id", columnDefinition="BIGINT(20) default '0'")
	private Long filial_id;
	
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	@Column(name = "dt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date dt ;
	
	@Column(name = "version", columnDefinition="BIGINT(20) default '0'")
	private @Version @JsonIgnore Long version;

	public Boolean getExternal() {
		return external;
	}
	@Column(name = "expired", columnDefinition="bit default 0")
	private @JsonIgnore boolean expired;
	
	@Column(name = "locked", columnDefinition="bit default 0")
	private @JsonIgnore boolean locked;

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !expired;
	}

	@Override
	public boolean isEnabled() {
		return !locked;
	}
	public void setExternal(Boolean external) {
		this.external = external;
	}
	@Size(min=8, max=32, message = "Не меньше 8 знаков, не больше 32")
	private  @JsonIgnore String password;
    @Transient
    private  @JsonIgnore String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Date getDt() {
		return dt;
	}

	public User() {
		super();
	}
	
	public User(Long id, String name, String pswd, Boolean superUser, Boolean external) {
		super();
		this.id = id;
		this.name = name;
		this.pswd = pswd;
		this.superUser = superUser;
		this.external = external;
		this.employee_id = 0L;
		this.filial_id = 0L;
		this.dt = new Date();
	}
	public User(Long id) {
		super();
		this.id = id;
		this.name = "";
		this.pswd = "";
		this.superUser = false;
		this.external = false;
		this.employee_id = 0L;
		this.filial_id = 0L;
		this.dt = new Date();
	}


    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public String getUsername() {
		return name;
	}
	
    @Override
    public String toString() {
        return "User{" + "id=" + id + '\'' +
        		", name=" + name + '\'' +
        		", pswd=" + pswd + '\'' +
        		", superUser=" + superUser +
        		", dt=" + dt +
        		", external=" + external +
        		", employee_id=" + employee_id +
        		", filial_id=" + filial_id +
        		", expired=" + expired +
        		", locked=" + locked +
        		", version=" + version +
        		'}';
    }
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User client = (User) o;
		return Objects.equals(id, client.id) &&
			Objects.equals(name, client.name) &&
			Objects.equals(pswd, client.pswd) &&
			Objects.equals(superUser, client.superUser) &&
			Objects.equals(dt, client.dt) &&
			Objects.equals(external, client.external) &&
			Objects.equals(employee_id, client.employee_id) &&
			Objects.equals(filial_id, client.filial_id) &&
			Objects.equals(expired, client.expired) &&
			Objects.equals(locked, client.locked) &&
			Objects.equals(version, client.version);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, pswd, superUser, dt, external, employee_id, filial_id, expired, locked, version);
	}

	public Long getFilial_id() {
		return filial_id;
	}

	public void setFilial_id(Long filial_id) {
		this.filial_id = filial_id;
	}
}
