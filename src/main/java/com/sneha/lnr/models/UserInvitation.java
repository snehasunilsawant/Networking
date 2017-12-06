package com.sneha.lnr.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="userinvitations")
public class UserInvitation {
	

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="invitation_id")
	private User invitation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getInvitation() {
		return invitation;
	}

	public void setInvitation(User invitation) {
		this.invitation = invitation;
	}
	
	public UserInvitation() {
		
	}
	
	public UserInvitation(User user,User invitation) {
		this.user=user;
		this.invitation=invitation;
	}

}
