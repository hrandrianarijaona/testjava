package com.excilys.formation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.model.Operation;
import com.excilys.formation.persistence.OperationDao;
import com.excilys.formation.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	OperationDao operationDao;
	
	@Override
	public Operation save(Operation o) {
		// TODO Auto-generated method stub
		return operationDao.save(o);
	}

}
