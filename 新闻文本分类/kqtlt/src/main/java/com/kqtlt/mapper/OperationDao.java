package com.kqtlt.mapper;

import com.kqtlt.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //告诉spring这是dao层
public interface OperationDao {
    Boolean insertOneOperation(Operation operation);
    List<Operation> selectAllOperation();
}
