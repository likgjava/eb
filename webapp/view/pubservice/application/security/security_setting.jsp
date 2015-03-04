<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="SupplierRegisterForm" id="OrgInfoRegisterForm">
	<input type="hidden" id="districtValue" name="districtValue" value=""/>
	
	 <div id="regiGuideSupplierOne" class="regGuide security">
	    <h3><span>找回密码</span></h3>
	    <ol>
	      <li><span id="step01">填写用户名称</span></li>
	      <li><span  class="next">下一步</span></li>
	      <li><span id="step02">填写密保问题</span></li>
	      <li><span  class="next finish">下一步</span></li>
	      <li><span id="step03" class="finish">完成密码找寻</span></li>
	    </ol>
	  </div>
	  <div id="supplierRegisterDivOne" class="formLayout form2Pa">
	    <h3 class="title"><span>填写密保问题</span></h3>
	    <c:forEach items="${userSecurityList}" var="userSecurity" varStatus="index">
	    	<input type="hidden" name="${userSecurity.question.objId}" id="question${index.count}" value="${userSecurity.question.dicName}"/>
	    </c:forEach>
	    <input type="hidden" name="userId" id="userId" value="${userId}"/>
	    <input type="hidden" name="result" id="result" value="${result }"/>
	    <ul id="userLogin">
	    	<li class="fullLine">
	            <label for="orgName">问题名称：</label>
	            <span id="questionContent"></span> 
	            <a id="change"><span>换个问题</span></a>
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">输入答案：</label>
	            <input type="text" id="answer" name="answer" maxlength="50" class="required"/>
	            <span class="eleRequired">*</span> 
	        </li>
	   </ul>
	   <div class="conOperation">
	      <button type="button" id="supplierRegisterNext"  class="next"><span>确定</span></button>
	      <button id="stepOneBack" type="button" class="back"><span>返回</span></button>
	    </div>
	  </div>
	  </form>
	  

<script>
//初始化一个问题到span中
function initQuestion(question){
	$("#questionContent").text(question);
	$("#answer").attr("name",$("#question1").attr("name"));
	$("#questionContent").attr("index","1");
}
//换下一个问题
function changeQuestion(){
	var currentQuestion=$("#questionContent").text();
	var nowQuestion="";
	var nowQuestionId="";
	var index=$("#questionContent").attr("index");
	for(index==3?i=1:i=index;index==3?i>=1:i<=3;index==3?i--:i++){//环式循环
		nowQuestion=$("#question"+i).val();
		nowQuestionId=$("#question"+i).attr("name");
		if(nowQuestion==currentQuestion){
			continue;
		}else{
			$("#questionContent").attr("index",i);
			break;
		}
	}
	$("#answer").attr("name",nowQuestionId);
	$("#questionContent").text(nowQuestion);
}

$(document).ready(function(){
	var question=$("#question1").val();
	initQuestion(question);
	$("a[id='change']").click(function(){
		changeQuestion()
	});
    //显示页面二的填写
	$("#supplierRegisterNext").click(function(){
		if(!$('#OrgInfoRegisterForm').valid()){alert('请正确填写表单!');return;}
		var answer=$("#answer").val();
		var questionId = $("#answer").attr("name");
		var userId=$("#userId").val();
		$.getJSON($('#initPath').val()+'/UserSecurityController.do?method=validateAnswer&userId='+userId+'&answer='+native2ascii(answer)+"&questionId="+questionId, function(json){
			if(json.result=='success'){
				$.getJSON($('#initPath').val()+'/UserSecurityController.do?method=sendEmailToUser&userId='+userId, function(json){
					if(json.flag=='yes'){
						alert("密码重置成功！");
						$('#contentMain').loadPage($('#initPath').val()+'/UserSecurityController.do?method=getPassword&step=3&userId='+userId);
					}else{
						alert("可能由于网络不通畅等原因造成发送邮件失败，请稍后重试！");
						return;
					}
				});
			}else{
				alert("密保问题回答错误，请输入正确的问题答案！");
				return;
			}
		});
	});
	
	//返回条款页面
	$("#stepOneBack").click(function(){
		document.location.href= $('#initPath').val()+'/UserSecurityController.do?method=getPassword&step=1';
    });
    
	//监听回车事件
	$('body').bind("keypress", function(event){
		if (event.keyCode == 13) {								   
			$("#supplierRegisterNext").click();
			return false;
		}
	});
	
});
</script>
