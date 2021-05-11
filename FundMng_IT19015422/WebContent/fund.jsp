<%@page import="com.fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/fund.js"></script>
<meta charset="ISO-8859-1">
<title>Fund Management</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h3>Fund Management Details</h3>

				<form id="formFund" name="formFund">

					Funder Code: <input id="code" name="code" type="text" class="form-control form-control-sm"><br> 
					Funder Name: <input id="name" name="name" type="text" class="form-control form-control-sm"><br>
					Funder Price: <input id="price" name="price" type="text" class="form-control form-control-sm"><br>
					Funder Location: <input id="location" name="location" type="text" class="form-control form-control-sm"><br>
					
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidFundIdSave" name="hidFundIdSave" value="">
				</form>
				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<br>
				<div id="divItemsGrid">
					<%
					fund fundObject = new fund();
					out.print(fundObject.readFund());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>