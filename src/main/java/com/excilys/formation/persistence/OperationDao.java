package com.excilys.formation.persistence;

import com.excilys.formation.model.Operation;

/**
 * OperationDao
 * DAO interface for table "operations" to be implemented
 */
public interface OperationDao {

  Operation save(Operation o);

}
