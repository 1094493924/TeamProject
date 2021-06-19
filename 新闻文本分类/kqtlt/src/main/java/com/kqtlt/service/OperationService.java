package com.kqtlt.service;

import com.kqtlt.entity.Operation;

import java.util.List;

public interface OperationService {
    Boolean insertOneOperation(Operation operation);
    List<Operation> selectAllOperation();
}
