<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>

<html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<title>Login</title>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	
	<%@ include file="../shared/importCss.jsp"%>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<c:url value='/static/plugins/matrix-admin/css/matrix-login.css'/>"  rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	
	<%@ include file="../shared/importJs.jsp"%>
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value='/static/plugins/jquery-validation/jquery.validate.min.js'/>" type="text/javascript"></script>   
	<!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<c:url value='/static/js/custom/login.validate.js'/>" type="text/javascript"></script>  
	<!-- END PAGE LEVEL SCRIPTS -->
	<script type="text/javascript">
		$(function(){
			$('#loginform').loginvalidate({});
		});
	</script>  
</head>

<!-- BEGIN BODY -->
<body>
<div id="loginbox">            
            <sf:form id="loginform" modelAttribute="contentModel" class="form-vertical" method="post">
				 <div class="control-group normal_text"> <h3><img src="<c:url value='/static/plugins/matrix-admin/img/logo.png'/>" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <div class="add-on bg_lg"><i class="icon-user"></i></div><sf:input path="username" class="form-control placeholder-no-fix" autocomplete="off" placeholder="用户名"/><br/>
							<sf:errors path="username" class="field-has-error"></sf:errors>  
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><sf:password path="password" class="form-control placeholder-no-fix" autocomplete="off" placeholder="密码"/><br/>
							<sf:errors path="password" class="field-has-error"></sf:errors>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">reset</a></span>
                    <span class="pull-right"><button type="submit" class="btn btn-success">Login</button></span>
                </div>
            </sf:form>
        </div>        
    </body>
</html>