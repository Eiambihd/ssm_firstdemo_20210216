package com.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.last.pojo.MSG;
import com.last.service.DeptService;

/**
 * @author 29634
 * 部门控制器，用于处理页面的<select>标签的填充，通过getalldept方法。
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
