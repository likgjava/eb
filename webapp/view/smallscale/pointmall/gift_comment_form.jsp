<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!-- 评价from -->
<form id="giftCommentForm">
<div class="formLayout">
	<h4><span>礼品评价</span></h4>
</div>
<div class="shop-rating" id="starsDiv">
			<span class="title">评论级别：</span>
			<ul class="rating-level" id="stars">
				<li><a class="aa1-star" star:value="1" href="#">1</a></li>
				<li><a class="aa2-stars" star:value="2" href="#">2</a></li>
				<li><a class="aa3-stars" star:value="3" href="#">3</a></li>
				<li><a class="aa4-stars" star:value="4" href="#">4</a></li>
				<li><a class="aa5-stars" star:value="5" href="#">5</a></li>
				<li><a class="aa6-stars" star:value="6" href="#">6</a></li>
				<li><a class="aa7-stars" star:value="7" href="#">7</a></li>
				<li><a class="aa8-stars" star:value="8" href="#">8</a></li>
				<li><a class="aa9-stars" star:value="9" href="#">9</a></li>
				<li><a class="aa10-stars" star:value="10" href="#">10</a></li>
			</ul>
			<span class="result" id="stars-tips"></span>
			<input type="hidden" id="stars-input" name="level" value=""/>
</div>
<div class="formLayout form2Pa">
	<ul>
		<li class="fullLine"></li>
		<li class="fullLine">
			<label>评价标题：</label>
			<input id="title" name="title" value="" size="45" maxlength="10" class="required"/><span class="eleRequired">*(10个字符以内)</span> 
		</li>
		<li class="formTextarea">
			<label>评价描述：</label>
			<textarea id="comment" name="comment"></textarea>
		</li>	
	</ul>
	<div class="conOperation">
		<button id="tuEvaluate" type="button"><span>发表</span></button>
	</div>
</div>
</form>

<script>
var goodsDetail = {}

$(document).ready(function(){
	
	//评价
	new Stars("stars",{nowClass:"current-rating",tipsTxt:["1分-质量非常差，价格很贵","2分-质量差，价格很高","3分-质量比较差，价格一般","4分-质量一般，价格贵","5分-质量一般，价格一般","6分-质量一般，价格便宜","7分-质量比较好，价格一般","8分-质量好，价格比较便宜","9分-质量很好，价格便宜","10分-质量非常好，价格相当便宜"]});

	//提交评价
	$("#tuEvaluate").click(function(){

		//判断是否登录
		if(common.isLogin(true,"请先登录再进行评价！")){
			var level = $("#giftCommentForm").find("input[name=level]").val();
			if(!$('#giftCommentForm').valid()){alert('请正确填写表单!');return;}
			if(level==null||""==level){
				alert("请做出评价再提交！");
				return false;
			}else{
				$.getJSON($("#initPath").val()+"/GiftCommentController.do?method=saveGiftComment",
						{
							"title":$("#title").val(),
							"level":$("input[name=level]").val(),
							"giftId":$("#giftId").val(),
							"comment":$("#comment").val()
						},
						function(json){
							if(json.success&&json.flag){
								alert(json.result);
							}else if(json.failure=="true"&&json.flag=="false"){
								alert(json.result);
							}else{
								alert("评价失败!");
							}
							//点击reload
							$("#evaluateListTab").click();
				})
			}
		}
	})
});
</script>