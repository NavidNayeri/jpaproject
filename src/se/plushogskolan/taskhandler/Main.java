package se.plushogskolan.taskhandler;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.plushogskolan.taskhandler.assets.config.AppConfig;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.service.UserService;

public final class Main {
	
	
	public static void main(String[] args) throws SQLException {
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)){
			UserService userService = ctx.getBean(UserService.class);
			userService.saveOrUpdateUser(new User("Navid", "Nayeri" , "UserName", "Password", "1"));
			
		}
		 
		  
	}
}
