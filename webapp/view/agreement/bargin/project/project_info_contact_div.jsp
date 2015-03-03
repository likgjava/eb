<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.formTag{
	border:1px solid #D1D7DC;
	background:none repeat scroll 0 0 transparent;
}
.formTag h4{
	background:none repeat scroll 0 0 #EFF7FE;
	border-bottom:1px solid #D1D7DC;
	line-height:20px;
	padding-left:10px;
	color:#444444;
}
.formTag li{
	border:none;
}
</style>

<div class="formLayout form2Pa formTag" >
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目联系人信息</span></h4>
		    <ul>
				<li>
					<label>联系人：</label>
					<span>${Info.linker}</span>
				</li>
				<li>
					<label>移动电话：</label>
					<span>${Info.mobilePhone}</span>
				</li>
				<li>
					<label>固定电话：</label>
					<span>${Info.fixedTelephone}</span>
				</li>
				<li>
					<label>传真：</label>
					<span>${Info.fax}</span>
				</li>
				<li class="fullLine">
					<label>邮编：</label>
					<span>${Info.postCode}</span>
				</li>
				<li class="fullLine">
					<label>地址：</label>
					<span>${Info.address}</span>
				</li>
			</ul>
</div>