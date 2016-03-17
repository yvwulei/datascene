<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>addUser</title>
<script src="<%=basePath %>/static/js/public/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function add(){
		$.ajax({
			  type: 'GET',
			  url: "<%=basePath %>users",
			  data: {},
			  contentType : 'application/json', 
			  dataType: "json", 
			  success:  function(response){ //成功
				  if (response && response.meta.success == true) {
					  console.log(response.data);
				  }
			  } ,
			  error: function(response){ //失败
				  if (!response || response.meta.success == false) {
					  console.log(response.data);
				  }
			  }
			});
	}
</script>


</head>
<body>
<a onclick="add(); href='javascript:;'">查看所有用户</a> 
</body>
</html>