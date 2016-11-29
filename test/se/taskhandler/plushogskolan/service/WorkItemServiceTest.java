package se.taskhandler.plushogskolan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.assets.config.AppConfig;
import se.plushogskolan.taskhandler.model.Team;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.service.TeamService;
import se.plushogskolan.taskhandler.service.UserService;
import se.plushogskolan.taskhandler.service.WorkItemService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class WorkItemServiceTest {
		
	@Autowired
	private WorkItemService workItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	@Test
	public void testSavingWorkItem() {
		WorkItem workItem = new WorkItem("Work Item", "Describing", WorkItemStatus.STARTED);
		workItem = workItemService.saveOrUpdateWorkItem(workItem);
		
		assertEquals(workItem, workItemService.saveOrUpdateWorkItem(workItem));
	}
	
	@Test
	public void testChangingWorkItemStatus() {
		WorkItem workItem = new WorkItem("Work Item", "Describing", WorkItemStatus.STARTED);
		workItem = workItemService.saveOrUpdateWorkItem(workItem);
		workItem = workItemService.changeWorkItemStatus(WorkItemStatus.DONE, workItem.getId());
		assertEquals(WorkItemStatus.DONE, workItem.getStatus());
	}
	
	@Test
	public void testRemoveWorkItem() {
		WorkItem workItem = new WorkItem("Work Item", "Describing", WorkItemStatus.STARTED);
		workItem = workItemService.saveOrUpdateWorkItem(workItem);
		Long id = workItem.getId();
		workItemService.removeWorkItem(id);
		assertNull(workItemService.findOne(id));
	}
	
	@Test
	public void testAssignWorkItemToUser() {
		User user = userService.saveOrUpdateUser(new User("Navid", "Tester", "NavidNavid", "hejsan1", true));
		
		WorkItem workItem = new WorkItem("Work Item", "Describing", WorkItemStatus.STARTED);
		workItem = workItemService.saveOrUpdateWorkItem(workItem);
		
		workItemService.assignWorkItemToUser(user.getId(), workItem.getId());
		user = userService.findOne(user.getId());
		assertEquals(user.getWorkItems().iterator().next(), workItem);
	}
	
	@Test
	public void testGetAllWorkItemByStatus() {
		WorkItem workItem1 = new WorkItem("Work Item 1", "Describing", WorkItemStatus.STARTED);
		WorkItem workItem2 = new WorkItem("Work Item 2", "Describing", WorkItemStatus.DONE);
		WorkItem workItem3 = new WorkItem("Work Item 3", "Describing", WorkItemStatus.STARTED);
		
		workItem1 = workItemService.saveOrUpdateWorkItem(workItem1);
		workItem2 = workItemService.saveOrUpdateWorkItem(workItem2);
		workItem3 = workItemService.saveOrUpdateWorkItem(workItem3);

		assertEquals(2, workItemService.getAllWorkItemsByStatus(WorkItemStatus.STARTED).size());
		assertEquals(1, workItemService.getAllWorkItemsByStatus(WorkItemStatus.DONE).size());
	}
	
	@Test
	public void testGetAllWorkItemsByTeam() {
		User user1 = userService.saveOrUpdateUser(new User("Navid", "Tester", "NavidNavid1", "hejsan1", true));
		User user2 = userService.saveOrUpdateUser(new User("Marcus", "Tester", "MarcusMarcus", "goodAndSecurePassword1", true));
			
		WorkItem workItem1 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 1", "Describing", WorkItemStatus.STARTED));
		WorkItem workItem2 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 2", "Describing", WorkItemStatus.DONE));
		WorkItem workItem3 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 3", "Describing", WorkItemStatus.STARTED));

		Team team = teamService.saveOrUpdateTeam(new Team("Good Team", true));
				
		user1 = teamService.addUserToTeam(user1.getId(), team.getId());
		user2 = teamService.addUserToTeam(user2.getId(), team.getId());
		
		workItemService.assignWorkItemToUser(user1.getId(), workItem1.getId());
		workItemService.assignWorkItemToUser(user1.getId(), workItem2.getId());
		workItemService.assignWorkItemToUser(user2.getId(), workItem3.getId());
		
		assertEquals(3, workItemService.getAllWorkItemsByTeam(team.getId()).size());
	}
	
	@Test
	public void testGetAllWorkitemsByUser() {
		User user1 = userService.saveOrUpdateUser(new User("Navid", "Tester", "NavidNavid2", "hejsan1", true));
		
		WorkItem workItem1 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 1", "Describing", WorkItemStatus.STARTED));
		WorkItem workItem2 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 2", "Describing", WorkItemStatus.DONE));
		WorkItem workItem3 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 3", "Describing", WorkItemStatus.STARTED));
		
		workItemService.assignWorkItemToUser(user1.getId(), workItem1.getId());
		workItemService.assignWorkItemToUser(user1.getId(), workItem2.getId());
		workItemService.assignWorkItemToUser(user1.getId(), workItem3.getId());
		
		user1 = userService.findOne(user1.getId());
		
		assertEquals(3, workItemService.getAllWorkItemsByUser(user1.getId()).size());
	}
	
	@Test
	public void testFindWorkItemContaingDesc() {
		String description = "illa";
		
		WorkItem workItem1 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 1", "Killabeast", WorkItemStatus.STARTED));
		WorkItem workItem2 = workItemService.saveOrUpdateWorkItem(new WorkItem("Work Item 2", "ThrillaHeast", WorkItemStatus.DONE));
		
		assertEquals(2 ,workItemService.findWorkItemByDescriptionContaining(description).size());
		
	}
	
	@Test
	public void testFindAllUsingPage() {	
		assertEquals(5, workItemService.findAllUsingPage(new PageRequest(0,5)).getSize());	
	}
	
}
