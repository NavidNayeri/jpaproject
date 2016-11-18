package se.plushogskolan.taskhandler.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.exception.ServiceException;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.UserRepository;
import se.plushogskolan.taskhandler.repository.WorkItemRepository;


@Service
public class WorkItemService {

	private final WorkItemRepository workItemRepository;
	
	private final UserRepository userRepository;
	
	private final IssueService issueService;
	
	private final DataSource dataSource;
	
	
	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository, UserRepository userRepository, IssueService issueService, DataSource dataSource) {
		this.dataSource = dataSource;
		this.workItemRepository = workItemRepository;
		this.userRepository = userRepository;
		this.issueService = issueService;
	}
	
	public WorkItem saveOrUpdateWorkItem(WorkItem workItem) {
		return workItemRepository.save(workItem);
	}
	
	public WorkItem findOne(Long id){
		return workItemRepository.findOne(id);
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
		issueService.deleteIssueByWorkItemId(id);
		workItemRepository.delete(workItem);
		return workItem;
	}
	
	@Transactional
	public WorkItem assignWorkItemToUser(Long userId, Long workItemId) {
		User user = userRepository.findOne(userId);
		if (user.isActive()) {
			if (user.getWorkItems().size() < 5) {
				Collection<WorkItem> workItems = user.getWorkItems();
				WorkItem workItem = workItemRepository.findOne(workItemId);
				workItems.add(workItem);
				user.setWorkItems(workItems);
				userRepository.save(user);
				return workItem;
			} else {
				throw new ServiceException("User can't be assigned more than 5 work items!");
			}
		} else {
			throw new ServiceException("Can't assign work item to inactive user!");
		}
	}
	
	public List<WorkItem> getAllWorkItemsByStatus(WorkItemStatus status) {
		return workItemRepository.findByStatus(status);
	}
	
	public Collection<WorkItem> getAllWorkItemsByTeam(Long id) {
		Collection<WorkItem> workItems = new HashSet<>();
		List<User> users = userRepository.findByTeamId(id);
		for (User user : users) {
			user.getWorkItems().forEach(w -> workItems.add(w));
		}
		return workItems;
	}
	
	public Collection<WorkItem> getAllWorkItemsByUser(Long id) {
		return userRepository.findOne(id).getWorkItems();
	}
	
	public List<WorkItem> findWorkItemByDescriptionContaining(String text) {
		return workItemRepository.findByDescriptionContaining(text);
	}
	
	public Page<WorkItem> findAllUsingPage(Pageable page){
		return workItemRepository.findAll(page);
	}
	
	public List<WorkItem> getWorkItemHistory(LocalDate fromDate, LocalDate toDate){
		return workItemRepository.getWorkItemHistory(fromDate, toDate);
	}
	
}