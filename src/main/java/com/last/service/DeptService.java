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
	 * ��ȡ���еĲ�����Ϣ
	 * */
	
	public List<Department> getalldept(){
		return dm.selectByExample(null);
	};
}
