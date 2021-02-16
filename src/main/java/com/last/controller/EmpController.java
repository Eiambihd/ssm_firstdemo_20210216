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
 *Ա����Ϣ������
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
	 * ����չʾҳ��Ա����Ϣ��get�������д��Σ�����pnΪ�ڼ�ҳ������PageHelper����ʵ�ַ�ҳ
	 * */
	@RequestMapping("/empsbyjson")
	@ResponseBody
	public MSG empsbyjson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
		PageHelper.startPage(pn, 5);
		List<Employee> l = es.getAll();
		//PageInfo��װlist,�Լ���ҳ��Ϣ
		PageInfo pi = new PageInfo(l,5);
		//��pageinfo pi ����MSG�У�����ǰ̨json���ݡ�
		return MSG.success().add("pi", pi);
	}
	/*
	 * ɾ������������ʵ�ֵ���ɾ���Լ����ɾ��
	 * */
	@ResponseBody
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
	public MSG delempbyid(@PathVariable("id")String id) {
		/*
		 * �Դ����id�����жϣ�������-����1-2-3-4�����ж��ɾ����������е���ɾ��
		 * */
		if(id.contains("-")) {
			//split�������Ը��ݴ�����ַ����ַ������зָ����һ���ַ�������
			String[] ids = id.split("-");
			List<Integer> list=new ArrayList<Integer>();
			//���������ַ���תΪ����
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
	 * �޸�Ա����Ϣ��ǰ̨����employee json����,����Ա��id��urlռλ����ʽ����
	 * springmvc���Ὣid�Զ���װ
	 * PathVariable ռλ���뷽���в���һ����ʵ���в�������Ϊͬ�����Զ��󶨣�
	 * ��Ҫʹ��@PathVariableע�⣬���������ȴ�����Զ���װ��
	 * ���������ʣ�������Ӱ��ʹ��
	 * */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	public MSG updateemp(Employee emp) {	
		es.updateemp(emp);
		return MSG.success();
	}
	/*
	 * Ա����ӣ�ûʲô��˵��
	 * */
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	@ResponseBody
	public MSG addemp(Employee emp) {
		es.addemp(emp);
		return MSG.success();
	}
	/*
	 * ��ȡԱ����Ϣ
	 * */
	@ResponseBody
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	public MSG getemp(@PathVariable("id")Integer empId) {
		Employee e = es.getempbyid(empId);
		return MSG.success().add("emp", e);
	}
	/*
	 * ��������Ƿ��ظ�
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
