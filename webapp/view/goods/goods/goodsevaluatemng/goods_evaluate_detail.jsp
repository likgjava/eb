<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 评价from -->
<script>
$(document).ready(function(){
	var tipsTxt=new Array("1分-质量非常差，价格很贵","2分-质量差，价格很高","3分-质量比较差，价格一般","4分-质量一般，价格贵","5分-质量一般，价格一般","6分-质量一般，价格便宜","7分-质量比较好，价格一般","8分-质量好，价格比较便宜","9分-质量很好，价格便宜","10分-质量非常好，价格相当便宜");
	var level=$("input[name='level']").val();
	var result=tipsTxt[level-1];//级别提示
	var stars="<li class='fullLine'>";//级别星级等级
	for(i=1;i<=level;i++){
		stars+="<img src='view/resource/skin/sysicon/16/star.png'/>";
	}
	stars+="</li>";
	$("div ul[id='stars']").html(stars);
	$("div span[id='stars-tips']").html(result);

	//关闭
	$("#closeDesk").click(function(){
		$('.epsDialogClose').trigger('click');
	});
});
</script>

<form id="goodsEvaluateFrom">
<div class="shop-rating" id="starsDiv">
	<span class="title">评论级别：</span>
	<ul id="stars">
	</ul>
	<span class="result" id="stars-tips"></span>
	<input type="hidden" id="stars-input" name="level" value="${goodsEvaluate.level }"/>
</div>
<div class="formLayout form2Pa">
	<ul>
		<li class="fullLine">
			<label>评价标题：</label>
			<span >${goodsEvaluate.title }</span>
		</li>
		<li class="formTextarea">
			<label>评价描述：</label>
			<span >${goodsEvaluate.remark }</span>
		</li>
		<li >
			<label>评价人：</label>
			<span >${goodsEvaluate.createUser.usName }</span>
		</li>
		<li >
			<label>评价时间：</label>
			<span >${goodsEvaluate.createTime }</span>
		</li>	
	</ul>
</div>
<div class="operationBtnDiv"><button id="closeDesk" type="button"><span>关闭</span></button></div>
</form>

