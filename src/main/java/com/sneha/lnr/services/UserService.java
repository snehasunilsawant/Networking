package com.sneha.lnr.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sneha.lnr.models.User;
import com.sneha.lnr.models.UserInvitation;
import com.sneha.lnr.repositories.UserRepository;
import com.sneha.lnr.repositories.UserinvitationsRepository;

@Service
public class UserService {
	
	final UserRepository userRepository;
	final UserinvitationsRepository userinvitationsRepository;
	

	public UserService(UserRepository userRepository, UserinvitationsRepository userinvitationsRepository) {
		this.userRepository = userRepository;
		this.userinvitationsRepository = userinvitationsRepository;
	}
	
	
	public String saveUser(User user, String c_password, HttpSession session) {
		User UserEmail = userRepository.findByEmail(user.getEmail());
		if(!user.getPassword().equals(c_password) ) {
			System.out.println("password dont match");
			return "0";
		}
		if( UserEmail != null ){
			System.out.println("Email Exist");
			return "1";			
		}
		else {
			System.out.println("UserEmail : " + UserEmail);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(user);
			System.out.println("User id : " +  user.getId());
			System.out.println("User name : " +  user.getName());
			session.setAttribute("userid", user.getId());
			session.setAttribute("username", user.getName());		
			return "User Created";			
		}	
		
	}
	
	
	public boolean login(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		
		if( user != null) {
			if( BCrypt.checkpw(password, user.getPassword()) ) {
				session.setAttribute("userid", user.getId());
				session.setAttribute("username", user.getName());	
				return true;
			}
			else {
				System.out.println("Password not correct");
				return false;
			}
		}
		System.out.println("Email & password not correct");
		return false;
	}
	
	public User getCurrentUserDetails(HttpSession session) {
		return userRepository.findOne((Long) session.getAttribute("userid"));
	}
	
	public List<User> allNotMyFriendsUsers(HttpSession session){
		System.out.println("inside function");
		List<User> u1 = new ArrayList<User>();
		User user = userRepository.findOne((Long) session.getAttribute("userid"));
		System.out.println(user.getName());
		List<User> myFriends = user.getFriends();
		List<User> allUsers = userRepository.findAll();
		allUsers.remove(user);
		for (int i=0;i<allUsers.size();i++) {
			System.out.println("list" + allUsers.get(i).getId());
			if ( myFriends.size() == 0) {				
				return allUsers;
				
			}else {
				for (int j = 0; j < myFriends.size() ; j++) {
					System.out.println("mylist" + myFriends.get(j).getId());
					if( allUsers.get(i).getId() != myFriends.get(j).getId()) {
						u1.add(allUsers.get(i));
					}
				}
			}
						
		}
		System.out.println("Size"+u1.size());
		return u1;

		
	}
	
	
	public User getUser(long id) {
		return userRepository.findOne(id);
	}
	
	public void connectUser(User inviteUser ,User currentUser) {
//		currentUser.setUserinvitations(inviteUser);
		System.out.println("inside connect");
		UserInvitation u1 = new UserInvitation(inviteUser,currentUser);
		userinvitationsRepository.save(u1);
		userRepository.save(currentUser);
	}
	
	public void addFriend(User addUser,User currentUser) {
		System.out.println("inside");
		addUser.setUserfriend(currentUser);
		currentUser.setUserfriend(addUser);
		System.out.println("user aded");
		addUser.getInvitations().remove(currentUser);
		currentUser.getInvitations().remove(addUser);
		userRepository.save(currentUser);
		userRepository.save(addUser);
		System.out.println("user saved");
	}
	
	public void ignoreUser(User ignoreUser , User currentUser) {
		System.out.println("inside");
		System.out.println(ignoreUser.getInvitations().remove(currentUser));
		currentUser.getInvitations().remove(ignoreUser);
		userRepository.save(currentUser);
		userRepository.save(ignoreUser);
		System.out.println(ignoreUser.getInvitations().size());
	}
	
	
}