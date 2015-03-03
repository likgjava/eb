<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrgInfoAuditForm" method="post" modelAttribute="orgInfo">
		<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>
		<input type="hidden" id="currentId" name="currentId" value="<c:out value="${orgInfo.currentId}"/>"/>
        <input type="hidden" id="useStatus" name="useStatus" value="<c:out value="${orgInfo.useStatus}"/>"/>
		<input type="hidden" id="supplierId" name="supplierId" value="${orgInfo.supplierId}"/>
		<input type="hidden" id="buyerId" name="buyerId" value="${orgInfo.buyerId}"/>
		<input type="hidden" id="agencyId" name="agencyId" value="${orgInfo.agencyId}"/>
		<input type="hidden" id="auditRole" name="auditRole" value="${auditRole}"/>
	
	<div class="formLayout form2Pa">
		<h4><span>机构信息</span></h4>
		<ul>
			<li><label>机构名称：</label> <span id="orgName">${orgInfo.orgName}</span>
			</li>
			<li><label>机构代码：</label> <span id="orgCode">${orgInfo.orgCode}</span>
			</li>
			<li><label>联系手机：</label> <span id="company.mobilePhone">${orgInfo.company.mobilePhone}</span>
			</li>
			<li><label>联系电话：</label> <span id="company.tel">${orgInfo.company.tel}</span>
			</li>
			<li><label>行政区域：</label> <span id="distinctName">${orgInfo.distinctName}</span>
			</li>
			<li><label>邮编：</label> <span id="company.postCode">${orgInfo.company.objId}，${orgInfo.company.postCode}</span>
			</li>
			<li class="fullLine"><label>详细通信地址：</label> <span id="company.address">${orgInfo.company.address}</span>
			</li>
		</ul>
	</div>

	
	<!-- 扩展信息部分开始 -->
	<div id="infoDiv">
		<ul id="infoUl">
			<li id="supplierLi"><a href="#supplierInfo" id="loadSupplierBtn">供应商信息</a></li>
			<li id="buyerLi"><a href="#buyerInfo" id="loadBuyerBtn">采购人信息</a></li>
			<li id="agencyLi"><a href="#agencyInfo" id="loadAgencyBtn">代理机构信息</a></li>
		</ul>
		<div id="supplierInfo"></div>
		<div id="buyerInfo"></div>
		<div id="agencyInfo"></div>
	</div>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea"><label>审核意见：</label><textarea id="opinion" name="opinion" class="required"></textarea>
			</li>
		</ul>
	</div>
	
	</form:form>
	
	<div class="conOperation">
		<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
		<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
		<button type="button" name="historyBackBtn" ><span>返回</span></button>
		<button type="button" class="largeBtn" id="viewHistory"><span>查看历史</span></button>
	</div>
	<!-- 扩展信息部分结束 -->

<script>
	var OrgInfoAuditForm={};
	//审核
	OrgInfoAuditForm.auditOrgInfo=function(orgInfoId,auditStatus){
	  var jsonObj = formToJsonObject('OrgInfoAuditForm');
	  jsonObj.auditStatus=auditStatus;
	  var url = $('#initPath').val()+'/OrgInfoController.do?method=auditOrgInfo';

	  $.getJSON(url,jsonObj,function(json){
	      if(json.failure){return;}
	      var auditRole = $('#auditRole').val();
	      if(auditRole=="SUPPLIER") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/suppliers/suppliers/supplier_audit_list.jsp");
	      }else if(auditRole=="BUYER") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/buyers/buyers/buyer_audit_list.jsp");
	      }else if(auditRole=="AGENCY") {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/agency/agency/agency_audit_list.jsp");
	      }else {
	    	  $('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/manager/organization_audit_list.jsp");
	      }
	  });
	}
	//获得供应商角色信息
	function getSupplierRoleInfo(){
		$("#supplierInfo").loadPage($("#initPath").val()+"/SupplierController.do?method=toSupplierRole&supplierId="+$("#supplierId").val()+"&orgInfoCurrentId="+$("#currentId").val());
	}
	//获得采购人角色信息
	function getBuyerRoleInfo(){
		$("#buyerInfo").loadPage($("#initPath").val()+"/BuyerController.do?method=toBuyerRole&buyerId="+$("#buyerId").val()+"&orgInfoCurrentId="+$("#currentId").val());
	}
	//获得代理机构角色信息
	function getAgencyRoleInfo(){
		$("#agencyInfo").loadPage($("#initPath").val()+"/AgencyController.do?method=toAgencyRole&agencyId="+$("#agencyId").val()+"&orgInfoCurrentId="+$("#currentId").val());
	}
$(document).ready(function(){
  $('#agreeBtn').click(function(){//通过
	  if(window.confirm("确认通过吗")){
  		OrgInfoAuditForm.auditOrgInfo($("#objId").val(),'02');
	  }
  });
  $('#refuseBtn').click(function(){//不通过
	  if(window.confirm("确认不通过吗")){
  		OrgInfoAuditForm.auditOrgInfo($("#objId").val(),'03');
	  }
  });
  
  //加载扩展信息
  var $tabs=$("#infoDiv").tabs();
	$("#supplierLi").hide();
	$("#buyerLi").hide();
	$("#agencyLi").hide();
	$("#supplierInfo").hide();
	$("#buyerInfo").hide();
	$("#agencyInfo").hide();

	if($("#agencyId").val() != "" && $("#agencyId").val() != null){
		$tabs.tabs('select', 2);//切换到当前选项卡
		$("#agencyLi").show();
		$("#agencyInfo").show();
		getAgencyRoleInfo();
	}
	if($("#buyerId").val() != "" && $("#buyerId").val() != null){
		$tabs.tabs('select', 1);//切换到当前选项卡
		$("#buyerLi").show();
		$("#buyerInfo").show();
		getBuyerRoleInfo();
	}
	if($("#supplierId").val() != "" && $("#supplierId").val() != null){
		$tabs.tabs('select', 0);//切换到当前选项卡
		$("#supplierLi").show();
		$("#supplierInfo").show();
		getSupplierRoleInfo();
	}

	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/OrgInfoController.do?method=getOrgHistory";
		if($("#useStatus").val() == "00"){//临时
			url += "&id="+$("#currentId").val();
		}else if($("#useStatus").val() == "01"){//有效
			url += "&id="+$("#objId").val();
		}
		$.epsDialog({
	        title:'机构历史',
	        url:url
	    }); 
	})
});
</script>