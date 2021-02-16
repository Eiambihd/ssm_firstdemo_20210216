package com.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.last.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.last.pojo.Employee;
import com.last.pojo.MSG;

import java.util.*;

/**
 * @author 29634
 *员工信息控制器
 */
@Controller
public class EmpController {
	
	@Autowired
	private EmpService es;
	
	@RequestMapping("/emps")
	public String list(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model m) {
//		PageHelper.startPage(pn, 5);
//		List<Employee> l = es.getAll();
//		PageInfo pi = new PageInfo(l,5);
//		m.addAttribute("emplist", pi);
		return "list";
	}
	/*
	 * 用于展示页面员工信息，get方法进行传参，参数pn为第几页，运用PageHelper工具实现分页
	 * */
	@RequestMapping("/empsbyjson")
	@ResponseBody
	public MSG empsbyjson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
		PageHelper.startPage(pn, 5);
		List<Employee> l = es.getAll();
		//PageInfo包装list,以及分页信息
		PageInfo pi = new PageInfo(l,5);
		//将pageinfo pi 放入MSG中，返回前台json数据。
		return MSG.success().add("pi", pi);
	}
	/*
	 * 删除方法，可以实现单个删除以及多个删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	public MSG delempbyid(@PathVariable("id")String id) {
		/*
		 * 对传入的id进行判断，若包含-，即1-2-3-4，进行多个删除，否则进行单个删除
		 * */
		if(id.contains("-")) {
			//split方法可以根据传入的字符对字符串进行分割，返回一个字符串数组
			String[] ids = id.split("-");
			List<Integer> list=new ArrayList<Integer>();
			//遍历并将字符串转为数字
			for(String i:ids) {
				list.add(Integer.parseInt(i));
			}
			es.delempbach(list);
		}else {
			es.delemp(Integer.parseInt(id));
		}
		return MSG.success();
	}
	
	/*
	 * 修改员工信息，前台传入employee json数据,并将员工id以url占位符方式传入
	 * springmvc将会将id自动封装
	 * PathVariable 占位符与方法中参数一致在实验中并不会因为同名而自动绑定，
	 * 需要使用@PathVariable注解，但传入对象却可以自动封装。
	 * 这里有疑问，但并不影响使用
	 * */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	public MSG updateemp(Employee emp) {	
		es.updateemp(emp);
		return MSG.success();
	}
	/*
	 * 员工添加，没什么好说的
	 * */
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	@ResponseBody
	public MSG addemp(Employee emp) {
		es.addemp(emp);
		return MSG.success();
	}
	/*
	 * 获取员工信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	public MSG getemp(@PathVariable("id")Integer empId) {
		Employee e = es.getempbyid(empId);
		return MSG.success().add("emp", e);
	}
	/*
	 * 检测邮箱是否重复
	 * */
	@RequestMapping("/checkemail")
	@ResponseBody
	public MSG emailcheck(@RequestParam("empemail")String email) {
		boolean flag = es.checkemail(email);
		if(flag) {
			return MSG.success();
		}else {
			return MSG.fail();
		}
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
