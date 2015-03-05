<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<head>
<title>http500错误-政采科技</title>
<style>
.icons_not_found {
background-color:#FEFAEA;
border:1px solid #FEC799;
height:200px;
}
.icons_not_found .beer_head {
float:left;
padding-left:50px;
}
.icons_not_found .tab_form {
line-height:24px;
overflow:hidden;
padding-left:30px;
width:540px;
padding-top:20px;
}
.icons_not_found h5 {
font-size:22px;
font-weight:bold;
margin-bottom:10px;
}
.tab_form .tab_content p {
line-height:24px;
margin-top:10px;
overflow:hidden;
width:540px;
}
</style>
</head>

<div class="icons_not_found">
	<div class="beer_head">
		<img src="view/resource/images/500.png" />
	</div>
	<div class="tab_form">				
		<div class="tab_content">
			<h5>权限不足</h5>
			<p>
				错误原因：${result}
			</p>
			<p>
				您可以与管理员进行联系。
			</p>
		</div>
	</div>
</div>
