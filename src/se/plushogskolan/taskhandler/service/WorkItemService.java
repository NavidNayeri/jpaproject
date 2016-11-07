package se.plushogskolan.taskhandler.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.UserRepository;
import se.plushogskolan.taskhandler.repository.WorkItemRepository;


@Service
public class WorkItemService {

	private final WorkItemRepository workItemRepository;
	
	private final UserRepository userRepository;
	
	@Autowired
	public WorkItemService (WorkItemRepository workItemRepository, UserRepository userRepository) {
		this.workItemRepository = workItemRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public WorkItem saveOrUpdateWorkItem(WorkItem workItem) {
		return workItemRepository.save(workItem);
	}
	
	@Transactional
	public WorkItem changeWorkItemStatus(WorkItemStatus status, Long id) {
		WorkItem workItem = workItemRepository.findOne(id);
		workItem.setStatus(status);
		return workItemRepository.save(workItem);
	}
	
	@Transactional
	public WorkItem removeWorkItem(Long id) {
		WorkItem workItem = workItemRepository.findOne(id);
		//   issueService.deleteIssueByWorkItemId(id);
		workItemRepository.delete(workItem);
		return workItem;
	}
	
	@Transactional
	public WorkItem assignWorkItemToUser(Long userId, Long workItemId) {
		User user = userRepository.findOne(userId);
		Collection<WorkItem> workItems = user.getWorkItems();
		WorkItem workItem = workItemRepository.findOne(workItemId);
		workItems.add(workItem);
		user.setWorkItems(workItems);
		userRepository.save(user);
		return workItem;
	}
	
	public List<WorkItem> getAllWorkItemsByStatus(WorkItemStatus status) {
		return workItemRepository.findByStatus(status);
	}
	
}
