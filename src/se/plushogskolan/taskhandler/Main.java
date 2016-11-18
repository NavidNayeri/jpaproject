package se.plushogskolan.taskhandler;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.plushogskolan.taskhandler.assets.config.AppConfig;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.service.TeamService;
import se.plushogskolan.taskhandler.service.UserService;
import se.plushogskolan.taskhandler.service.WorkItemService;

public final class Main {

	public static void main(String[] args) throws SQLException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
			UserService userService = ctx.getBean(UserService.class);
			TeamService teamService = ctx.getBean(TeamService.class);
			WorkItemService workItemService = ctx.getBean(WorkItemService.class);
			

		}

	}
}
