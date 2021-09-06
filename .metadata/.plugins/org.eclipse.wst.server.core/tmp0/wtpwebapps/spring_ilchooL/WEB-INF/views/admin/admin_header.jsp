<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">nav_menu</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/admin/admin_dashboard.do"><img
				src="../assets/img/Logo(white).png" alt="logo"
				style="max-width: 100px"></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right hidden-sm hidden-md hidden-lg">
				<li><a href="${pageContext.request.contextPath}/admin/admin_dashboard.do">Dashboard</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/admin_list.do">Users</a></li>
			</ul>
		</div>
	</div>
</div>