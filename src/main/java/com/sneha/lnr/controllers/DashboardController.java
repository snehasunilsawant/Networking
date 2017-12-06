package com.sneha.lnr.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sneha.lnr.models.User;
import com.sneha.lnr.services.UserService;



@Controller
public class DashboardController {
	
	final UserService userService;
	
	public DashboardController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/dashboard")
	public String index(HttpSession session,Model model) {
		if ( session.getAttribute("userid") != null) {
			User currentuser = userService.getCurrentUserDetails(session);
			List<User> friends = currentuser.getFriends();
			System.out.println(friends.size());
			for (int i = 0; i < friends.size() ; i++) {
				System.out.println("mylist" + friends.get(i).getId());
				//code for check
			}
			model.addAttribute("name", session.getAttribute("username"));
			model.addAttribute("description", userService.getCurrentUserDetails(session).getDescription());
			model.addAttribute("user",currentuser );
			model.addAttribute("myinvitations", userService.getCurrentUserDetails(session).getInvitations());
			return "dashboard";
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/allusers")
	public String showAllUsers(Model model,HttpSession session) {
		model.addAttribute("allNotMyFriendsUsers", userService.allNotMyFriendsUsers(session));		
		return "allusers";
	}
	
	@RequestMapping("/connect/{id}")
	public String inviteFriend(@PathVariable("id") long id,Model model,HttpSession session) {		
		User user = userService.getCurrentUserDetails(session);
		User inviteUser = userService.getUser(id);
		userService.connectUser(inviteUser,user);		
		return "redirect:/dashboard";			
		
	}
	
	@RequestMapping("/acceptinvite/{id}")
	public String addFriend(@PathVariable("id") long id,Model model,HttpSession session) {	
		User currentuser = userService.getCurrentUserDetails(session);
		User addUser = userService.getUser(id);
		System.out.println(addUser.getName());
		userService.addFriend(addUser,currentuser);		
		return "redirect:/dashboard";			
		
	}
	
	@RequestMapping("/ignoreinvite/{id}")
	public String ignoreinvite(@PathVariable("id") long id,Model model,HttpSession session) {	
		System.out.println("inside ignore");
		User user = userService.getCurrentUserDetails(session);
		User inviteignoreUser = userService.getUser(id);
		userService.ignoreUser(inviteignoreUser,user);		
		return "redirect:/dashboard";			
		
	}
	
	@RequestMapping("/user/{id}")
	public String userDetails(@PathVariable("id") long id,Model model,HttpSession session) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "userdetails";
	}
	
}
	