package com.kqtlt.service.impl;

import com.kqtlt.entity.Operation;
import com.kqtlt.mapper.OperationDao;
import com.kqtlt.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public Boolean insertOneOperation(Operation operation) {
        return operationDao.insertOneOperation(operation);
    }

    @Override
    public List<Operation> selectAllOperation() {
        return operationDao.selectAllOperation();
    }
}
