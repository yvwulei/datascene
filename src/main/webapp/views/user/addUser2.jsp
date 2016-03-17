<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>addUser</title>
<script src="<c:url value='/static/js/public/jquery-1.11.0.min.js'/>" type="text/javascript"></script>
<script type="text/javascript">

$.ajax({
	  type: 'POST',
	  url: "<%=basePath %>user",
	  data: [{"username":"admin","nickname":"adminnike","password":"admin123","confirmPassword":"admin123","email":"ytianyi@cn.ibm.com","phone":"18611816316"}],
	  dataType: json,
	  success:  function(msg){ //成功
		  console.log( "Data Saved: " + msg );
	  } ,
	  error: function(msg){ //失败
		  console.log('Error loading document'+msg);
	  }
	});
</script>
</head>
<body>
<form action="<%=basePath %>user" method="post">
username:<input name="username"  type="text"/><br/>
nickname:<input name="nickname" type="text"/><br/>
password:<input name="password"  type="password"/><br/>
confirmPassword:<input name="confirmPassword" type="password"/><br/>
email:<input name="email" type="text"/><br/>
phone:<input name="phone" type="text"/><br/>
sex:<input name="sex" type="text"/><br/>
certType:<input name="certType" type="text"/><br/>
certNum:<input name="certNum" type="text"/><br/>
<input type="submit" value="Submit" />
</form>
</body>
</html>