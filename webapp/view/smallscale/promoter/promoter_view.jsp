<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="promoterViewForm" method="post">
	    <input type="hidden" name="objId" id="objId" value="${param.objId}"/>
		
		 <ul> 	    
          <li class="fullLine">
			<span><label for="">采购单位名称：</label></span>
            <span>${promoter.promoterUnitName}</span>
          </li>
          <li class="fullLine">
			<span><label for="">采购意向：</label></span>
			<c:if test="${promoter.promoterUnitCode != null}">
            	<span>${promoter.promoterUnitCode}</span>
            </c:if>
            <c:if test="${promoter.promoterUnitCode == null}">
            	<span>未填写</span>
            </c:if>
          </li>          
           <li class="fullLine">
			<span><label for="">采购单位联系人：</label></span>
            <span>${promoter.promotedLinkName}</span>
          </li>  
          <li class="fullLine">
			<span><label for="">联系电话：</label></span>
            <span>${promoter.promotedLinkTel}</span>
          </li>
          <li class="fullLine">			
			<span><label for="">联系人邮箱：</label></span>
			<span>${promoter.email}</span>			
		</li>
          
          <c:if test="${promoter.dealStatus eq '00' && promoter.recordType != '01'}">
          <li class="fullLine">
			<span><label for="">验证码：</label></span>
            <span><input name="validationCode" id="validationCode" class="required"/>
                  <input id="dealStatus" name="dealStatus" type="hidden"  value="01" />
            </span>
            <span class="eleRequired">*</span>
          </li>  
          </c:if>
          
          <c:if test="${promoter.recordType == '01'}">
          <li class="fullLine">
			<span><label for="">验证码：</label></span>
            <span>${promoter.validationCode}</span>           
          </li>  
          </c:if>
          
  		</ul>
		
		<div class="conOperation">	
		 <c:if test="${promoter.dealStatus eq '00' && promoter.recordType eq '00'}">
            <button id="promoterDeal" type="button" ><span>确认</span></button>
        </c:if>           
         <button id="promoterViewClose" type="button" ><span>关闭</span></button>   
	    </div>	
	</form>
</div>

<script>
$(document).ready(function(){
   //确认
	$('#promoterDeal').click(function(){	
				
		$.getJSON($('#initPath').val()+'/PromoterController.do?method=promoterDealSave', formToJsonObject('promoterViewForm'), function(json){
			if(json.failure)return;

			if(json.result=='faile'){
				alert('验证码未匹配成功,请重新输入验证码!');
				return;
			}
			if(json.result!='faile'){
			 alert('验证码匹配正确,提交成功');
			}		
			$('#conBody').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyUser');			
				$('.epsDialogClose').trigger('click');
		});
	});  
    
     //关闭弹出层
	$('#promoterViewClose').click(function(){			
			$('.epsDialogClose').trigger('click');
		
	});
});

</script>