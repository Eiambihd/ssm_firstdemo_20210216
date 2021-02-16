package com.last.dao;

import com.last.pojo.Employee;
import com.last.pojo.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    //��ѯԱ����ĳЩ���������ҷ���ֵ���в�����Ϣ
    List<Employee> selectByExamplewithdept(EmployeeExample example);
    //��ѯԱ��ͨ������������ֵ���в�����Ϣ
    Employee selectByPrimaryKeywithdept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}