package se.plushogskolan.taskhandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.repository.WorkitemRepository;


@Service
public class WorkItemService {

	private final WorkitemRepository workitemRepository;
	
	@Autowired
	public WorkItemService (WorkitemRepository workitemRepository){
		this.workitemRepository = workitemRepository;
	}
	
}
