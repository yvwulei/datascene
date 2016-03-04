<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
</script>
<!-- BEGIN SIDEBAR -->

<!--sidebar-menu-->
<div id="sidebar">
   <ul>
		<c:forEach items="${leftMenus}" var="menu">
			<c:choose>
				<c:when test="${menu.childs.size()==0}">
					<li class="submenu"><a href="${menu.parent.href}"><i class="icon icon-home"></i><span>${menu.parent.name}</span></a></li>
				</c:when>
				<c:otherwise>
				    <li class="submenu"> <a href="${menu.parent.href}"><i class="icon icon-th-list"></i> <span>${menu.parent.name}</span> <span class="label label-important">${menu.childs.size()}</span></a>
			     		<ul>
			     	<c:forEach items="${menu.childs}" var="sub" varStatus="status">
	       					<li><a href="${sub.href}">${sub.name}</a></li>
			     	</c:forEach>
			     		</ul>
			     	</li>
				</c:otherwise>
			</c:choose>
		 </c:forEach>
		    <li class="submenu"><a href="#"><i class="icon icon-info-sign"></i>
				<span>Error</span> <span class="label label-important">4</span></a>
				<ul>
					<li><a href="<c:url value='/views/home/error403.jsp'/>"
						target="mainFrame">Error 403</a></li>
					<li><a href="<c:url value='/views/home/error404.jsp'/>"
						target="mainFrame">Error 404</a></li>
					<li><a href="error405.html">Error 405</a></li>
					<li><a href="error500.html">Error 500</a></li>
				</ul>
			 </li>
	</ul>
</div>
<!--sidebar-menu-->
<!-- BEGIN SIDEBAR MENU -->        

<!-- END SIDEBAR MENU -->
<!-- END SIDEBAR -->