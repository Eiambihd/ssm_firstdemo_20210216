package com.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.last.pojo.MSG;
import com.last.service.DeptService;

/**
 * @author 29634
 * ���ſ����������ڴ���ҳ���<select>��ǩ����䣬ͨ��getalldept������
 */
@Controller
public class DeptController {
	
	@Autowired
	private DeptService ds;
	
	
	
	@RequestMapping("/alldept")
	@ResponseBody
	public MSG getalldept() {
		return MSG.success().add("dept", ds.getalldept());
	}
	
}
