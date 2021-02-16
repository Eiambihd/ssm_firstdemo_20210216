package com.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.last.dao.EmployeeMapper;
import com.last.pojo.Employee;
import com.last.pojo.EmployeeExample;
import com.last.pojo.EmployeeExample.Criteria;

import java.util.*;

@Service
public class EmpService {
	
	@Autowired
	private EmployeeMapper em;
	
	public List<Employee> getAll(){
		return em.selectByExamplewithdept(null);
	}
	
	public void addemp(Employee emp) {
		em.insertSelective(emp);
	}
	
	public void delemp(Integer id) {
		em.deleteByPrimaryKey(id);
	}
	
	public void updateemp(Employee emp) {
		em.updateByPrimaryKey(emp);
	}
	
	public Employee getempbyid(Integer id) {
		return em.selectByPrimaryKeywithdept(id);
	}
	/*
	 * mybatis中的自动创建实体工具中，example是十分便捷的处理方法
	 * 先创建（new）一个example对象，再调用createCriteria()方法
	 * Criteria 对象中的方法可以拼接各种条件
	 * 再使用mapper中的ByExample方法即可
	 * */
	public boolean checkemail(String email) {
		EmployeeExample example = new EmployeeExample();
		Criteria c = example.createCriteria();
		c.andEmailEqualTo(email);
		long count = em.countByExample(example);
		return count == 0;
	}

	public void delempbach(List<Integer> ids) {
		EmployeeExample e = new EmployeeExample();
		Criteria c = e.createCriteria();
		c.andEmpIdIn(ids);
		em.deleteByExample(e);
	}
	
}
