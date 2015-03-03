<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="BuyerApplyForm" method="post">
	<input type="hidden" name="objId" id="buyerId"/>
	<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfo.objId}"/>
<div class="formLayout form2Pa">
<ul>
	<li class="fullLine"><label for="input02">上级单位：</label> <input
		type="text" name="supervisor" id="supervisor" size="60" /> <input
		type="hidden" id="supervisorId" name="parentUnit.objId" /></li>
	<li class="fullLine"><label for="input01">单位性质：</label> 
	<html:select selectedValue="0" id="unitType" name="unitType" code="biz.buyer.unitType"></html:select>
	</li>
	<li class="fullLine"><label for="belongIndustry">所属行业：</label>
        <html:select selectedValue="0" id="belongIndustry" name="belongIndustry" code="biz.buyer.belongIndustry"></html:select>
    </li>
<!--	<li class="fullLine"><label for="input02">行政部门：</label> -->
<!--	<html:select selectedValue="0" id="execDept" name="execDept" code="biz.buyer.execDept"></html:select>-->
<!--	</li>-->
<!--	<li class="fullLine"><label for="input02">主管部门：</label> -->
<!--	<html:select selectedValue="0" id="cmptDepType" name="cmptDepType" code="biz.buyer.cmptDepType"></html:select>-->
<!--	</li>-->
</ul>
</div>

<div class="conOperation">
	<button class="largeBtn" type="button" id="saveBuyerBtn"><span>申请</span></button>
</div>
</form>

<script>
var BuyerApplyForm={};

/*跳转到选择采购人选择的页面*/
BuyerApplyForm.toSelectParentUnit=function(){
    $.epsDialog({
        title:'请选择机构',
        url:$('#initPath').val()+'/OrgInfoController.do?method=toSelectOrgInfoViewPage&orgType=BUYER',
        width: '700',
        height: '410',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){ }
    });
}

$(document).ready(function(){
	$('#BuyerApplyForm').validate();
	
	//确定
	$("#saveBuyerBtn").click(function(){
		if($("#BuyerApplyForm").valid()){
			if(window.confirm('确定申请采购人的角色？')){
				$.getJSON($('#initPath').val()+'/BuyerController.do?method=saveApplyBuyer',formToJsonObject('BuyerApplyForm'), function(json){
					if(json.success=='true'){
						alert('申请成功，请耐心等待管理员的审核');
						$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
					}else{
						alert('操作失败');
					}
				})
			}
		}
	})
    
	//上级单位
	$("#supervisor").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/buyers/registration/select_chargedepartment.jsp?Hname=supervisor&Hid=supervisorId'
	    })
	})
})
</script>