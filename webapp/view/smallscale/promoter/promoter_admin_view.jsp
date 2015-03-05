<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">         
	<form id="promoterViewForm" method="post">
	    <input type="hidden" name="objId" id="objId" value="${param.objId}"/>
		
		 <ul> 	    
           <li class="fullLine">
            <span><label for="promoterName">采购大使姓名：</label></span>
            <span>${promoter.promoterName}</span>
        </li>
        <li class="fullLine">
            <span><label for="promoterCID">采购大使身份证号：</label></span>
            <span>${promoter.promoterCID}</span>            
        </li>
          <li class="fullLine">
			<span><label for="">单位名称：</label></span>
            <span>${promoter.promoterUnitName}</span>
          </li>          
        
           <li class="fullLine">
			<span><label for="">联系人：</label></span>
            <span>${promoter.promotedLinkName}</span>
          </li>  
          <li class="fullLine">
			<span><label for="">联系电话：</label></span>
            <span>${promoter.promotedLinkTel}</span>
          </li>
          
          
          <li class="fullLine">
			<span><label for="">验证码：</label></span>
            <span>${promoter.validationCode}
                  <input name="validationCode" type="hidden"  id="validationCode" value="${promoter.validationCode}" />
                  <input id="dealStatus" name="dealStatus" type="hidden"  value="01" />
            </span>
          </li>  
       
          
  		</ul>
		
		<div class="conOperation">	
		 <c:if test="${promoter.dealStatus eq '00'}">
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
				alert('提交失败！');
				return;
			}
			if(json.result=='faile'){
			 alert('提交成功！');
			}		
			$('#conBody').loadPage($('#initPath').val()+'/PromoterController.do?method=listbyAdmin');			
				$('.epsDialogClose').trigger('click');
		});
	});  
    
     //关闭弹出层
	$('#promoterViewClose').click(function(){			
			$('.epsDialogClose').trigger('click');
		
	});
});

</script>