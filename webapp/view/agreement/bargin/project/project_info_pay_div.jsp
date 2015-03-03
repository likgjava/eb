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
			<h4><span><img src="view/resource/skin/skin07/img/leaderBg.png" />&nbsp;项目支付信息</span></h4>
		    <ul>
				<li>
					<label>交货日期：</label>
					<span>${Info.deliveryDate}</span>
				</li>
				<li>
					<label>交货地点：</label>
					<span>${Info.deliveryAddress}</span>
				</li>
				<li>
					<label>交货方式：</label>
					<span>${Info.deliveryTypeCN}</span>
				</li>
				<li>
					<label>付款方式：</label>
					<span>${Info.payTypeCN}</span>
				</li>
				<li class="fullLine">
					<label>其他补充说明：</label>
					<span>${Info.supplement}</span>
				</li>
			</ul>
</div>