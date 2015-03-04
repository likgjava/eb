<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	var connertType = "客户"; 
	$('#groupTypeLabel').html("客户名称：");
	//if($('#groupType').val() == "01"){
	//	connertType = "供应商";
	//	$('#groupTypeLabel').html("供应商：");
	//}else if($('#groupType').val() == "02"){
	//	connertType = "采购人";
	//	$('#groupTypeLabel').html("采购人：");
	//}

	$('#addNewConcernGroupLi').hide();
	// 取关注分组
	$.getJSON($('#initPath').val()+'/ConcernGroupController.do?method=getObjectQuery&queryColumns=objId,groupName',{"belongsUser.objId":$('#concernForm').find('input[id=curUserId]').val(),"order":"sort"},function(json){
		if(json.result.length > 0){
			$('[id=concernGroup.objId]').empty();
			var concernGroupId = $('#concernGroupId').val();
			$.each(json.result,function(i,n){
			var isChecked="";
				if(concernGroupId == n["objId"]){
					isChecked = "selected='selected'"
				}
				$('[id=concernGroup.objId]').append('<option value='+n["objId"]+' '+isChecked+'>'+n["groupName"]+'</option>');
			})
		}
	});

	// 选中名单分类
	var listTypeValue = $('#listType').val();
	if('02'==listTypeValue){$('#listTypeId').val('02');}//02是黑名单  01是客户
	//$.each($('select[name=listType] option'),function(){
	//	if($('#listType').val() == $(this).val()){
	//		$(this).attr("selected",true);
	//	}
	//})
	
	// 保存关注对象
	$('#saveConcernBtn').click(function(){
		if(!$('#concernForm').valid()){alert('请正确填写表单');return;}
		
		var concern = formToJsonObject('concernForm');
		var typeList=$.ajax({ url:$("#initPath").val()+"/ConcernController.do?method=getObjectQuery&queryColumns=objId",data:{"belongsUser.objId":$('#curUserId').val(),"orgInfo.objId":concern['orgInfo.objId']}, async: false }).responseText
		var alreadyExistsLength = JSON.parse(typeList).result.length;
		var isAlreadyExists = true;
		if(""!= concern.objId && alreadyExistsLength > 1){
			isAlreadyExists = false;
		}else if(""== concern.objId && alreadyExistsLength > 0){
			isAlreadyExists = false;
		}
		if(!isAlreadyExists){
			alert("该"+connertType+"已被关注！");
			return false;
		}
		$('#saveConcernBtn').attr('disabled',true);
		$.ajax({
			url:$('#initPath').val()+'/ConcernController.do?method=saveConcern',
			type:'POST',
			data:concern,
			async:false,
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);$('#saveConcernBtn').attr('disabled',false);return;}
				if(json.success){
					alert(json.result);
					$('#epsDialogClose').click();
				}
			}
		})
	});
	// 关闭弹出层
	$('#closeConcernBtn').click(function(){
		$('#epsDialogClose').click(); 
	});

	// 点击新建分组
	$('#addNewConcernGroupBtn').click(function(){
		$('#addNewConcernGroupSpan').hide();
		$('#addNewConcernGroupLi').show();
	});
	// 取消新建分组
	$('#cancleNewConcernGroupBtn').click(function(){
		$('#addNewConcernGroupSpan').show();
		$('#addNewConcernGroupLi').hide();
		$('#groupName').val("");
	});

	// 确认新分组
	$('#saveNewConcernGroupBtn').click(function(){
		if($('#groupName').val()==""){
			alert("请输入分组名称！");
			return false;
		}
		var flag = true;
		$('[id=concernGroup.objId]').find("option").each(function(i,n){
			if($(n).text() == $('#groupName').val()){
				flag = false;
			}
		})
		if(!flag){
			alert("["+$('#groupName').val()+"]分组已经存在");
			return false;
		} 
		$('#saveNewConcernGroupBtn').attr('disabled',true);
		var concernGroup = {};
		concernGroup.groupType = $('#groupType').val();
		concernGroup.groupName = $('#groupName').val();
		concernGroup.objId ="";
		$.ajax({
			url:$('#initPath').val()+'/ConcernGroupController.do?method=saveConcernGroup',
			type:'POST',
			data:concernGroup,
			async:false,
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);$('#saveNewConcernGroupBtn').attr('disabled',false);return;}
				if(json.success && json.concernGroup.objId !=""){
					$('[id=concernGroup.objId]').append('<option value='+json.concernGroup.objId+' selected="selected">'+$('#groupName').val()+'</option>');
					$('#addNewConcernGroupSpan').show();
					$('#addNewConcernGroupLi').hide();
					$('#saveNewConcernGroupBtn').attr('disabled',false);
					$('#groupName').val("");
				}
			}
		})
	});
})
</script>
 <div class="formLayout formPa">
 <input id="listType" type="hidden" value="${param.listType}"/>
<form id="concernForm" method="post">
<input name="objId" id="concernId" type="hidden" value="${param.objId }"/>
<input id="concernGroupId" type="hidden" value="${param.concernGroupId }"/>
<input name="orgInfo.objId" type="hidden" value="${param.orgInfoId }"/>
<input name="concernGroup.groupType" id="groupType" type="hidden" value="00"/>
<input name="listType" id="listTypeId" type="hidden" value="01"/>
<input id="curUserId" type="hidden" value="${curUserId}"/>
     	<ul>
	     	<li>
	     		<label id="groupTypeLabel"></label>
	     		<span id="orgInfoName"></span>
    	    </li>
	     	<li>
	     		<label>所属分组：</label>
	     		<select name="concernGroup.objId" id="concernGroup.objId"><option value="">默认分组</option></select>
	     		<span id="addNewConcernGroupSpan"><a href="javascript:void(0);" id="addNewConcernGroupBtn"><span>添加分组</span></a></span>
    	    </li>
    	    <li id="addNewConcernGroupLi">
    	    <label>分组名称：</label>
	     		<input id="groupName" value=""><a href="javascript:void(0);" id="saveNewConcernGroupBtn">确认新分组</a>&nbsp;&nbsp;<a href="javascript:void(0);" id="cancleNewConcernGroupBtn">取消</a>
	     	</li>
       </ul>
 </form>
 <div class="conOperation">
	<button type="button" id="saveConcernBtn"><span><spring:message code="globe.save"/></span></button>
	<button type="button" id="closeConcernBtn" tabindex="19""><span><spring:message code="globe.close"/></span></button>
  </div>
 </div>


        
        

 
