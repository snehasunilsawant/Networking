package com.sneha.lnr.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@Size(min=1,message = "Description can not be empty")	
	@NotNull(message="Description can not be empty")
	private String description;
	
	@NotNull(message="Email can not be empty")
	private String email;
	
	@Size(min=8, message = "Password should be minimum 8 characters")
	private String password;	
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//    name = "friendships", 
//    joinColumns = @JoinColumn(name = "user_id"), 
//    inverseJoinColumns = @JoinColumn(name = "friend_id")
//    )
//    private List<User> friends;
//    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//    name = "friendships", 
//    joinColumns = @JoinColumn(name = "friend_id"), 
//    inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> userFriends;

    @OneToMany(mappedBy="userfriend", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE,orphanRemoval=true)
    private List<User> friends;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="friend_id")
	private User userfriend;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "userinvitations", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "invitation_id")
    )
    private List<User> invitations;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "userinvitations", 
    joinColumns = @JoinColumn(name = "invitation_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userinvitations;
	
//	@OneToMany(mappedBy="userinvitations", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE,orphanRemoval=true)
//    private List<User> invitations;
//    
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="invitation_id")
//	private User userinvitations;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public User getUserfriend() {
		return userfriend;
	}

	public void setUserfriend(User userfriend) {
		this.userfriend = userfriend;
	}

	



	public List<User> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<User> invitations) {
		this.invitations = invitations;
	}

	public List<User> getUserinvitations() {
		return userinvitations;
	}

	public void setUserinvitations(List<User> userinvitations) {
		this.userinvitations = userinvitations;
	}

	public User() {
		
	}

}
