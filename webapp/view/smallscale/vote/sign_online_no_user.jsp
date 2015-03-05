<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/index.css"/>
<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<h4>在线报名</h4>
		<h4>一、供应商注册（带*号的为必填项）</h4>
		<div class="formLayout form2Pa">
			<form name="SupplierRegisterForm" id="OrgInfoRegisterForm">	 
				<ul>
					<div class="formTips warm hidden" id="tips"></div>
					<li class="fullLine">
						<label for="orgName">机构名称：</label>
						<input type="text" id="orgName" name="orgName" maxlength="50" class="required" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li> 
					<li class="formTextarea">
						<label for="purSbjct">经营范围：</label>
						<textarea name="bidForRange_1" id="bidForRange_1" maxlength="500" readonly="readonly" style="width: 360px;height: 110px;"></textarea>
						<input type="hidden" id="bidForRange_2" class="required"/>
						<input type="hidden" name="bidForRange" id="bidForRange" value="${orgInfo.bidForRange}"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>   	
				</ul>		
			</form>
			
			<form name="SupplierRegisterForm" id="UserRegisterForm">
				<ul>
					<li class="fullLine">
						<label for="usName">用&nbsp;户&nbsp;名：</label>
						<input type="text" name="usName" id="usName" minlength="6" maxlength="16" class="required uName" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span><br><div style="margin-left: 120px;">(只能包括英文、数字和下划线)(16位以内不少于6位)</div>
					</li>
					<li class="fullLine">
						<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
						<input type="password" name="password" id="password" class="rlogin" minlength="6" maxlength="20"  size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>
					<li class="fullLine">
						<label for="password_confirm">重复密码：</label>
						<input type="password" name="password_confirm" id="password_confirm" class="required" equalTo="#password" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>
					<li class="fullLine">
						<label for="name">联&nbsp;系&nbsp;人：</label>
						<input type="text" name="name" id="name" maxlength="20" class="required" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>
					<li class="fullLine">
						<label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
						<input name="sex" id="sex" type="radio" checked="checked" value="true" />&nbsp;先生&nbsp;&nbsp;
						<input name="sex" id="sex" type="radio" class="required" value="false"/>&nbsp;女士
					</li>	   
						<li class="fullLine">
						<label for="email">电子邮箱：</label>
						<input type="text" name="email" id="email" maxlength="50" class="required email" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>
					<li class="fullLine">
						<label for="mobile">移动电话：</label>
						<input type="text" name="mobile" id="mobile" maxlength="11" class="cnMobile required" size="50"/>
						<span style="font-size:12px;font-weight:100;color:#ff0000;">*</span>
					</li>
				</ul>
			</form>
			
			<div class="conOperation" style="text-align: center;">
				<button type="button" id="submitBtn"><span><spring:message code="globe.submit"/></span></button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var signOnlineForm = {};
//选择品目（投标范围及类别）
$("#bidForRange_1").click(function(e){
    $.epsDialog({
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true&childNodeOnly=true&checkStatus=true',
        onClose: function(){ 
      		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
      	}
    }); 
});

//机构名称提示
$('#orgName').change(function(){
	if(""==$(this).val()) return;//为空不检测
	$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=checkOrgName",{"orgName":$(this).val()},function(json){
		if(json.success){
			$("#tips").html('<em>注意：</em>'+json.result);
			if(json.sameNameList!=null){
				var sameBrandHtml = '&nbsp;&nbsp;以下机构名称已经存在：';
				$.each(json.sameNameList,function(index,obj){
					if(index==0){
						sameBrandHtml += obj;
					}else{
						sameBrandHtml += '、'+obj;
					}
				})
				if(sameBrandHtml.length>100) {
					sameBrandHtml = sameBrandHtml.substring(0,99) + "...";
				}
				$("#tips").append(sameBrandHtml);
			}
		}else{
			$("#tips").html(json.result+'<input type="hidden" id="samename" value="true"/>');
		}
		$("#tips").show();
	});
});

//提交注册信息
$("#submitBtn").click(function(){
	if(!$('#OrgInfoRegisterForm').valid()){alert('请正确填写表单!');return;}
	if(!$('#UserRegisterForm').valid()){alert('请正确填写表单!');return;}
	$("#submitBtn").attr("disabled","disabled");
	var orgForm = formToJsonObject('OrgInfoRegisterForm');
	var userForm = formToJsonObject('UserRegisterForm');
	if(window.confirm('是否确定注册供应商？')){
		$.getJSON($('#initPath').val()+"/SupplierController.do?method=saveSupplierOfRegister", $.extend(orgForm,userForm), function(json){
			if(json.failure){
				alert("注册失败，请与管理员联系！");
				$("#submitBtn").attr("disabled",false);
				return;
			}
			else{
	  			//获取当前用户
				$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", {} , function(userjson){
					if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
					//修改登录头部信息
					common.getCurrUser(userjson);
					$('#userId').val(userjson.cuid);
					$('#userName').val(userjson.cuname);
				});
				alert("注册供应商成功,请完善机构信息！下一步请发布品牌.");
				//添加品牌页面
	  			$('#conBody').loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=signOnLine&templateId='+$('#templateId').val());
			}
			
		});
    }else {
			$("#submitBtn").attr("disabled",false);
			return;
      	  }
 });

$(document).ready(function(){	
	var jsonNameObj= {};
	jsonNameObj["id"]="";//新注册ID传空
	jsonNameObj["property"]="orgName";
	jsonNameObj["type"]="supplier";//只验证供应商机构不重复
	$.validator.addMethod("orgNameUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgName="+native2ascii($("#orgName").val()),jsonNameObj);},'该机构名称已存在');

	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');
	
	//机构表单验证
	$("#OrgInfoRegisterForm").validate({
		rules:{
			orgName:{orgNameUnique:"orgName"}
		}
	});	
	//用户表单验证
	$("#UserRegisterForm").validate({
		rules:{
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"}
		}
	});	
});
</script>