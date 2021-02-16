<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ssm/static/js/jquery-1.11.0.min.js"></script>
<link href="/ssm/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="/ssm/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 ">
				<table class="table table-hover table-bordered">
					<tr>
						<th>id</th>
						<th>姓名</th>
						<th>email</th>
						<th>性别</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					<c:forEach var="l" items="${emplist.list }">
						<tr>
							<td>${l.empId }</td>
							<td>${l.empName }</td>
							<td>${l.email }</td>
							<td>${l.gender=="M"?"男":"女" }</td>
							<td>${l.department.deptName }</td>
							<td>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">当前第${emplist.pageNum }页，总共${emplist.pages }页，总共${emplist.total }条记录</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="/ssm/emps?pn=1">首页</a></li>
						<li><c:if test="${emplist.hasPreviousPage}">
								<a href="/ssm/emps?pn=${emplist.pageNum -1}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a>
							</c:if></li>
						<c:forEach items="${emplist.navigatepageNums }" var="i">
							<c:if test="${i==emplist.pageNum }">
								<li class="active"><a href="#">${i }</a></li>
							</c:if>
							<c:if test="${i!=emplist.pageNum }">
								<li><a href="/ssm/emps?pn=${i }">${i }</a></li>
							</c:if>
						</c:forEach>
						<li><c:if test="${emplist.hasNextPage}">
								<a href="/ssm/emps?pn=${emplist.pageNum +1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
							</c:if></li>
						<li><a href="/ssm/emps?pn=${emplist.pages}">尾页</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>