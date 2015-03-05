<%@ include file="/view/srplatform/common/taglibs.jsp"%><%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formZone">         
	<form id="ruleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc"><spring:message code="globe.input.required.prompt"/> 1、积分计算参数指成交额度等</div>
     	<div class="formLayout">
            <ul>
               
              <li>
	     	     <label>积分来源：</label>
	     	     <html:select selectedValue="${sourceCode}" id="sourceCode" name="sourceCode" code="com.gpcsoft.smallscale.point.domain.Rule.sourceCode"></html:select>			     
			     <span class="eleRequired">*</span>               
	          </li>
	          <li>
		          <label>积分累计方式：</label>
		          <input type="radio" name="pointSign" id="pointSign"  value="1" CHECKED="checked">加分
                  <input type="radio" name="pointSign" id="pointSign"  value="-1" >减分
              </li>
			  <li>
		          <label>计算方式：</label>	
		          <input type="radio" name="pointWay" id="pointWay1"  value="0" CHECKED="checked">额度
                  <input type="radio" name="pointWay" id="pointWay2"  value="1" >百分比	         
              </li>
              <li id="lipointNumber">
		          <label>积分额度：</label>
		          <input type="text" name="pointNumber" id="pointNumber" class="digits" />	
		          <span class="eleRequired">*</span> 			      			   
              </li> 
              <li id="lipointPercent" class="hidden">
		          <label>积分百分数：</label>		          		    
			      <input type="text" name="pointPercent" id="pointPercent"  class="floats" />
			      <span class="eleRequired">*</span> 			   
              </li> 
              <!--            
              <li>
		          <label>积分计算参数范围：</label>
		          <input type="text" name="amountMin" id="amountMin" class="digits" />
		          ~
		          <input type="text" name="amountMax" id="amountMax" class="digits" />
              </li>  
               -->         	
            </ul>
		</div>
		 <div class="conOperation">
	        
	        <button id="ruleSave" type="button"><span><spring:message code="globe.save"/></span></button>
	        <button  id="ruleReturn" type="button"><span><spring:message code="globe.return"/></span></button>
	        
	    </div>
	   
	</form>
</div>

<script>
	var ruleForm={};

	$(document).ready(function(){
		$('#ruleForm').validate();		
		$('#lipointPercent').hide();
		$('#pointWay1').click(function(){
			
			$('#lipointNumber').show();
			$('#lipointPercent').hide();
			$("#pointPercent").attr('value','');
		});

		$('#pointWay2').click(function(){
			$('#lipointNumber').hide();
			$('#lipointPercent').show();
			$("#pointNumber").attr('value','');
		});
		
	    if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/RuleController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('ruleForm', json.rule);	

	    		if(json.rule.pointWay=='0'){
	    			$("#pointPercent").attr('value','');
	    			$('#lipointNumber').show();
	    			$('#lipointPercent').hide();	    			 
	    			
	    		}
	    		else{
	    			$("#pointNumber").attr('value','');
	    			$('#lipointNumber').hide();
	    			$('#lipointPercent').show();
	    			
	    		}	    		  		
	    	});
	    }	    
	    
		//返回
		$('#ruleReturn').click(function(){			
			$('#conBody').loadPage($('#initPath').val()+"/RuleController.do");
		});
		
		//提交
		$('#ruleSave').click(function(){				
			if(!$('#ruleForm').valid()){alert('请正确填写表单!');return;}
			if($("#sourceCode").val()==''){alert('请选择积分来源!');return;}
			
			//alert($("#lipointNumber").val());
			//alert($("#lipointPercent").val());
			if($("#lipointNumber").is(":visible") && $("#pointNumber").val()==''){
				alert('积分额度不能为空!');
				return;
    		}
    		else if($("#lipointPercent").is(":visible") && $("#pointPercent").val()=='' ) {
    			alert('积分百分比不能为空!');
    			return;
    		}

    		/*
    		if($("#amountMin").val()!="" && $("#amountMax").val() !=""){   			
        		if(Number($("#amountMin").val()) >Number($("#amountMax").val())){
        			alert('积分计算参数最小值不能大于最大值!');
        			return ;
        		}
    		}
    		*/
			
			$.getJSON($('#initPath').val()+'/RuleController.do?method=save', formToJsonObject('ruleForm'), function(json){
				if(json.result)alert(json.result);if(json.failure){alert("保存失败！");return;}
				$('#conBody').loadPage($('#initPath').val()+'/RuleController.do');
			});
		}); 		

	});


</script>
