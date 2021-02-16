package com.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.last.dao.DepartmentMapper;
import com.last.pojo.*;
import java.util.*;

@Service
public class DeptService {
	
	@Autowired
	private DepartmentMapper dm;
	
	/*
	 * 获取所有的部门信息
	 * */
	
	public List<Department> getalldept(){
		return dm.selectByExample(null);
	};
}
