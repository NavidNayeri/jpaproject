package se.plushogskolan.taskhandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.repository.IssueRepository;


@Service
public class IssueService {
	
	
	private final IssueRepository issueRepository;
	
	@Autowired
	public IssueService(IssueRepository issueRepository){
		this.issueRepository = issueRepository;
	}

}
