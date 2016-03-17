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
			  type: 'POST',
			  url: "<%=basePath %>user",
			  data: '{"username":"admin","nickname":"adminnike","password":"admin123","confirmPassword":"admin123","email":"ytianyi@cn.ibm.com","phone":"18611816316"}',
			  //data: '{"nickname":"adminnike","password":"admin123","confirmPassword":"admin123","email":"ytianyi@cn.ibm.com","phone":"18611816316"}',
			  contentType : 'application/json', 
			  async:true,//异步
			  dataType: "json", 
			  success:  function(response){ //成功
				  console.log("success");
				  console.log(response);
			  	  console.log(response.meta);
				  if (response.meta.success == false) {
					  console.log(response.meta.message);
				  }
				  if (response.meta.success == true) {
					  console.log(response.data);
				  }
			  } ,
			  error: function(response){ //失败
				  console.log("failure");
				  console.log(response);
			  	  console.log(response.meta);
				  if (response.responseJSON.meta.success == false) {
					  console.log(response.responseJSON.meta.message);
				  }
			  }
			});
	}
</script>


</head>
<body>
<a onclick="add(); href='javascript:;'">添加用户</a> 
</body>
</html>