<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var QuotaForm={};
$(document).ready(function(){
	
	//返回
	$("#quotaReturn").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/base/quota/quota_list.jsp");
	})
	
	//保存
	$("#quotaSave").click(function(){				
		if(!$('#QuotaForm').valid()){alert('请正确填写表单!');return;}
		
		$.getJSON($('#initPath').val()+'/QuotaController.do?method=save',
			formToJsonObject("QuotaForm"),
			function(json){
				if(json.success){
					alert("保存成功!");
					$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/base/quota/quota_list.jsp");
				}
			}
		);
		
	})
});
</script>


	<form:form id="QuotaForm" method="post" modelAttribute="quota">
	<form:hidden path="objId"></form:hidden>
	<div id = "addquota" class="formLayout form1Pa">
	    <h4><span>指标信息</span></h4>
		<ul>
			<li id="nameLi">
				<label>指标名称：</label>
				<form:input path="name" cssClass="required"></form:input>
				<span class="eleRequired">*</span>
			</li>	
			
			<li id="proportionLi">
				<label>指标比重：</label>
				<form:input path="proportion" cssClass="required floats"></form:input>
				<span class="eleRequired">*</span>
			</li>	
			
			<li>
				<label>角色类型：</label>
				<form:select path="type">
					<form:option value="01">供应商</form:option>
					<form:option value="02">采购人</form:option>
					<!--
					<form:option value="03">代理机构</form:option>
					<form:option value="04">监管机构</form:option>
					<form:option value="05">监督机构</form:option>
					-->
				</form:select>				
			</li>
		</ul>
	</div>
	</form:form>
   	
   	<div class="conOperation center">
	  	<button  id="quotaSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	  	<button  id="quotaReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
