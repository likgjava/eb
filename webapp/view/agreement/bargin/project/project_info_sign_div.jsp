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
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目报名信息</span></h4>
		    <ul>
				<li class="fullLine">
					<label>企业资质：</label>
					<span>${Info.companyQualification}</span>
				</li>
				<li class="fullLine">
					<label>企业产能：</label>
					<span>${Info.companyProductivity}</span>
				</li>
				<li class="fullLine">
					<label>质量认证：</label>
					<span>${Info.qualityStandard}</span>
				</li>
				<li class="fullLine">
					<label>注册资金(万元)：</label>
					<span>${Info.registeredCapital}</span>
				</li>
				<li class="fullLine">
					<label>附加语：</label>
					<span>${Info.additionalMemo}</span>
				</li>
			</ul>
</div>