<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Conquer | Admin Dashboard Template</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/bootstrap-responsive.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/fullcalendar.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/matrix-style.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/matrix-media.css'/>" />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/font-awesome/css/font-awesome.css'/>"  />
<link rel="stylesheet" href="<c:url value='/static/plugins/matrix-admin/css/jquery.gritter.css'/>" />
<link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>   
<script src="<c:url value='/static/plugins/matrix-admin/js/excanvas.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.ui.custom.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/bootstrap.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.flot.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.flot.resize.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.peity.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/fullcalendar.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.dashboard.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.gritter.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.interface.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.chat.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.validate.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.form_validation.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.wizard.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.uniform.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/select2.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.popover.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/jquery.dataTables.min.js'/>"></script> 
<script src="<c:url value='/static/plugins/matrix-admin/js/matrix.tables.js'/>"></script> 

<link rel="shortcut icon" href="<c:url value='/static/plugins/matrix-admin/img/favicon.ico'/>" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<!--Header-part-->
<div id="header">
  <h1><a href="#">Matrix Admin</a></h1>
</div>
<!--close-Header-part--> 
   <!-- BEGIN PAGE HEADER-->
            <%@ include file="../shared/header.jsp"%>
   <!-- END PAGE HEADER-->
   <!-- BEGIN PAGE NAV-->
            <%@ include file="../shared/left.jsp"%>
   <!-- BEGIN PAGE NAV-->
   <!-- BEGIN CONTAINER -->

	<div id="content">
		<!--<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a
					href="#" class="current">Error</a>
			</div>
			<h1>Error 403</h1>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-info-sign"></i>
							</span>
							<h5>Error 403</h5>
						</div>
						<div class="widget-content">
							<div class="error_ex">
								<h1>403</h1>
								<h3>Opps, You're lost.</h3>
								<p>Access to this page is forbidden</p>
								<a class="btn btn-warning btn-big" href="index.html">Back to
									Home</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- END CONTAINER -->
   <!-- BEGIN FOOTER -->
   <%@ include file="../shared/footer.jsp"%>
   <!-- END FOOTER -->
   
   <!-- BEGIN PAGE LEVEL PLUGINS -->
   <!-- END PAGE LEVEL PLUGINS -->
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <!-- END PAGE LEVEL SCRIPTS -->  

   <script type="text/javascript">
   </script>
   <!-- END JAVASCRIPTS -->
</body>
</html>