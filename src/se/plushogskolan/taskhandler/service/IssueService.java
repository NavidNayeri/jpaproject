package se.plushogskolan.taskhandler.service;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Transactional
	public Issue saveIssue(Issue issue) {
		WorkItem workItem = workItemRepository.findOne(issue.getWorkItem().getId());
		
		if (workItem.getStatus().equals(WorkItemStatus.DONE)) {
			workItem.setStatus(WorkItemStatus.UNSTARTED);
			workItemRepository.save(workItem);
			return issueRepository.save(issue);
		}
		throw new ServiceException("Can only assign issue to work item with status : DONE");
	}
	
	public Issue updateIssue(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public Collection<WorkItem> getAllWorkItemsWithIssues() {
		Collection<WorkItem> workItems = new HashSet<>();
		for (Issue issue : issueRepository.findAll()) {
			workItems.add(workItemRepository.findOne(issue.getWorkItem().getId()));
		}
		return workItems;
	}
	
	public Issue deleteIssueByWorkItemId(Long id) {
		if(!issueRepository.findByWorkItemId(id).isEmpty()){
			Issue issue = issueRepository.findByWorkItemId(id).get(0);
			issueRepository.delete(id);			
			return issue;
		}
		return null;
	}
	
	public Page<Issue> findAllUsingPage(Pageable page){
		return issueRepository.findAll(page);
	}

}
