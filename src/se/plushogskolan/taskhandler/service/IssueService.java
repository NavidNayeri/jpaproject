package se.plushogskolan.taskhandler.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.exception.ServiceException;
import se.plushogskolan.taskhandler.model.Issue;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.IssueRepository;
import se.plushogskolan.taskhandler.repository.WorkItemRepository;


@Service
public class IssueService {
	
	private final IssueRepository issueRepository;
	
	private final WorkItemRepository workItemRepository;

	@Autowired
	public IssueService(IssueRepository issueRepository, WorkItemRepository workItemRepository) {
		this.issueRepository = issueRepository;
		this.workItemRepository = workItemRepository;
	}
	
	public Issue saveOrUpdateIssue(Issue issue) {
		WorkItem workItem = workItemRepository.findOne(issue.getWorkItemId());
		if (workItem.getStatus().equals(WorkItemStatus.DONE)) {
			workItem.setStatus(WorkItemStatus.UNSTARTED);
			workItemRepository.save(workItem);
			return issueRepository.save(issue);
		}
		throw new ServiceException("Can only assign issue to work item with status : DONE");
	}
	
	public Collection<WorkItem> getAllWorkItemsWithIssues() {
		Collection<WorkItem> workItems = new HashSet<>();
		for (Issue issue : issueRepository.findAll()) {
			workItems.add(workItemRepository.findOne(issue.getWorkItemId()));
		}
		return workItems;
	}
	
	public Issue deleteIssueByWorkItemId(Long id) {
		Issue issue = issueRepository.findByWorkItemId(id).get(0);
		issueRepository.delete(id);
		return issue;
	}

}
