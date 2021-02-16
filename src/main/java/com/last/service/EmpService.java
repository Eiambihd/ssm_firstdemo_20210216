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
	 * mybatis�е��Զ�����ʵ�幤���У�example��ʮ�ֱ�ݵĴ�����
	 * �ȴ�����new��һ��example�����ٵ���createCriteria()����
	 * Criteria �����еķ�������ƴ�Ӹ�������
	 * ��ʹ��mapper�е�ByExample��������
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
