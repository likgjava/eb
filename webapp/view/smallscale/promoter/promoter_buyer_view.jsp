<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="PromoterBuyerViewForm" id="PromoterBuyerViewForm">
<div class="formLayout form2Pa">
	<ul>        
		<li><label for="promoterName">推荐人姓名：</label>${promoter.promoterName}</li>
    	<li><label for="promoterDate">推荐日期：</label><span class="date"><fmt:formatDate value="${promoter.promoterDate}" pattern="yyyy-MM-dd" /></span></li>
        <li><label for="promoterCID">推荐人身份证号：</label>${promoter.promoterCID}</li>
        <li><label for="promoterCID">处理状态：</label><c:if test="${promoter.dealStatus == '00'}">未处理</c:if><c:if test="${promoter.dealStatus == '01'}">已处理</c:if><c:if test="${promoter.dealStatus == '02'}">管理员已经确认</c:if></li> 
        <li class="fullLine"><label for="promoterMemo">备注：</label>${promoter.promoterMemo}</li>    
		<c:choose>
			<c:when test="${promoter.dealStatus == '00' && promoter.creator.objId != currentUserId}">
				<li class="fullLine">
		            <label for="validationCode">请输入验证码：</label>
		            <input type="text" name="validationCode" id="validationCode" class="required" />
		            <span class="eleRequired">*</span>
		            <input type="hidden" name="objId" id="objId" value="${promoter.objId}" />
		            <input type="hidden" name="dealStatus" id="dealStatus" value="01" />
        		</li>
        	</c:when>
        	<c:otherwise>
        		<li class="fullLine">
            		<label for="promoterCID">验证码：</label>${promoter.validationCode}<c:if test="${promoter.dealStatus == '00'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">请把验证码通知采购大使</font></c:if>
        		</li> 
        	</c:otherwise>
		</c:choose>
	</ul>
    
	<div class="conOperation">
		<c:if test="${promoter.dealStatus == '00' && promoter.creator.objId != currentUserId}">
			<button type="button" id="submitBtn" class="next"><span>确认</span></button>
		</c:if>
    </div>
</div>
</form>

<script>
$(document).ready(function(){
	/*确认提交*/
	$("#submitBtn").click(function(){
		if(!$('#PromoterBuyerViewForm').valid()){alert('请正确填写表单!');return;}
		
		if(window.confirm('是否确认？')){
			$("#submitBtn").attr("disabled", true);
			$.getJSON($('#initPath').val()+"/PromoterController.do?method=promoterDealSave", formToJsonObject('PromoterBuyerViewForm'), function(json){
				if(json.failure){if(json.result)alert(json.result);$("#submitBtn").attr("disabled",false);return;}
				else{
					if(json.result=='faile'){
						alert('验证码未匹配成功,请重新输入验证码!');
						$("#submitBtn").attr("disabled",false);
						$("#validationCode").attr("value","");
					}else if(json.result=='success'){
			  			alert('验证码匹配正确,提交成功')
			  			$('#promoterInfo').loadPage($('#initPath').val()+'/PromoterController.do?method=toPromoterBuyerRecord');
			  		}
				}
			});
		}
	});
});
</script>