package se.taskhandler.plushogskolan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.assets.config.AppConfig;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.service.WorkItemService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class WorkItemServiceTest {
	
	
	@Autowired
	private WorkItemService workItemService;
	
	@Test
	public void testSavingAWorkItem() {
		WorkItem workItem = new WorkItem("Description", "Name", WorkItemStatus.DONE);
		workItemService.saveOrUpdateWorkItem(workItem);
		workItemService.findAllUsingPage(new PageRequest(0, 5)).forEach(System.out::println);
	}
	
	

}
