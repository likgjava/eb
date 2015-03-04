<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
function reSort(){
	// 取关注分组
	$.getJSON($('#initPath').val()+'/ConcernGroupController.do?method=getObjectQuery&queryColumns=objId,groupName,sort',{"belongsUser.objId":$('#curUserId').val(),"order":"sort"},function(json){
		var concernGroupTableTrList = "";
		$("#concernGroupTbody").empty();
		if(json.result.length > 0){
			$.each(json.result,function(i,n){
				var row = $('#concernGroupTable').find('tfoot').find("tr:first").clone(true).appendTo("#concernGroupTbody");
				$(row).attr("id",n["objId"]);
				$(row).find("span[name=beforEditShow_groupName]").html(n["groupName"]).end().find("input[name=groupName]").val(n["groupName"]);
				$(row).find("span[name=beforEditShow_sort]").html(n["sort"]).end().find("input[name=sort]").val(n["sort"]);
			})
				
		}
	});
}

function isExist(groupName){
	var flag = true;
	if(groupName==""){
		alert("请输入分组名称！");
		flag = false;
	}
	if($("#concernGroupTbody").find("input[name=groupName][value="+groupName+"]").length > 1){
		alert("["+groupName+"]分组已经存在！");
		flag = false;
	}
		return flag;
}
$(document).ready(function(){
	$('#concernGroupTable').find('tfoot').find("a[name=edit]").click(function(){
		var curRow = $(this).parent("span").parent("td").parent("tr");
		$(curRow).find("span[name=EditShow]").show().end().find("span[name^=beforEditShow]").hide();
	}).end().find("a[name=cancle]").click(function(){
		var curRow = $(this).parent("span").parent("td").parent("tr");
		$(curRow).find("span[name=EditShow]").hide().end().find("span[name^=beforEditShow]").show();
	}).end().find("a[name=delete]").click(function(){
		var curRow = $(this).parent("td").parent("tr");
		if(window.confirm("确认要删除对该分组吗?")){
			var ConcernCountList=$.ajax({ url:$("#initPath").val()+"/ConcernController.do?method=getObjectQuery&queryColumns=objId",data:{"concernGroup.objId":$(curRow).attr("id")}, async: false }).responseText
			if(JSON.parse(ConcernCountList).result.length > 0){
				alert("该分组下还有关注的客户对象，不能被删除！");
				return false;
			}
			$.ajax({
				url:$('#initPath').val()+'/ConcernGroupController.do?method=remove',
				type:'POST',
				data:{objId:$(curRow).attr("id")},
				async:false,
				dataType:'json',
				success:function(json){
					if(json.success){
						reSort();
					}
				}
			})
		}
	}).end().find("a[name=save]").click(function(){
		var curRow = $(this).parent("span").parent("td").parent("tr");
		// 判断是否已经存在
		if(!isExist($(curRow).find("input[name=groupName]").val())){
			return false;
		}
		var concernGroup = {};
		concernGroup.groupName = $(curRow).find("input[name=groupName]").val();
		concernGroup.sort = $(curRow).find("input[name=sort]").val();
		concernGroup.objId = $(curRow).attr("id");
		$.ajax({
			url:$('#initPath').val()+'/ConcernGroupController.do?method=updateConcernGroup',
			type:'POST',
			data:concernGroup,
			async:false,
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				if(json.success){
					$(curRow).find("input[name=groupName]").val(concernGroup.groupName);
					$(curRow).find("input[name=sort]").val(concernGroup.sort);
					$(curRow).find("span[name=beforEditShow_groupName]").html(concernGroup.groupName);
					$(curRow).find("span[name=beforEditShow_sort]").html(concernGroup.sort);
					$(curRow).find("span[name=EditShow]").hide().end().find("span[name^=beforEditShow]").show();
					reSort(); //重新排序
				}
			}
		})
		$(this).parent("span").parent("td").parent("tr").find("span[name=EditShow]").hide().end().find("span[name^=beforEditShow]").show();
	})
	
	// 取关注分组
	reSort();
	
	// 点击新建分组
	$('#addConcernGroupBtn').click(function(){
		if($("#concernGroupTbody").find("tr[isNew]").length > 0){
			alert("请选保存新建的分组！");
			return false;
		}

		// 获得当前最大的序号
		var maxSort = 0;
		$("#concernGroupTbody").find("tr").each(function(i,n){
			var curSort = parseInt($(n).find("input[name=sort]").val());
			if( curSort > maxSort){
				maxSort = curSort;
			}
		})
		var curRow = $('#concernGroupTable').find('tfoot').find("tr:first").clone(false);
		$(curRow).attr("isNew",true);
		$(curRow).find("input[name=groupName]").val("新建分组"+(maxSort+1));
		$(curRow).find("input[name=sort]").val((maxSort+1));
		$("#concernGroupTbody").append($(curRow)).find("a[name=save]").click(function(){
			// 判断是否已经存在
			if(!isExist($(curRow).find("input[name=groupName]").val())){
				return false;
			}
			var concernGroup = {};
			concernGroup.groupType = $('#groupType').val();
			concernGroup.groupName = $(curRow).find("input[name=groupName]").val();
			concernGroup.sort = $(curRow).find("input[name=sort]").val();
			concernGroup.objId ="";
			
			$.ajax({
				url:$('#initPath').val()+'/ConcernGroupController.do?method=saveConcernGroup',
				type:'POST',
				data:concernGroup,
				async:false,
				dataType:'json',
				success:function(json){
					if(json.failure){alert(json.result);return;}
					if(json.success && json.concernGroup.objId !=""){
						$(curRow).attr("id",json.concernGroup.objId);
						$(curRow).find("input[name=groupName]").val(json.concernGroup.groupName);
						$(curRow).find("input[name=sort]").val(json.concernGroup.sort);
						$(curRow).find("span[name=beforEditShow_groupName]").html(json.concernGroup.groupName);
						$(curRow).find("span[name=beforEditShow_sort]").html(json.concernGroup.sort);
						$(curRow).find("span[name=EditShow]").hide().end().find("span[name^=beforEditShow]").show();
						reSort();
					}
				}
			})
		}).end().find("a[name=cancle]").click(function(){
			$(curRow).remove();
		}).end().find("a[name=delete]").click(function(){
			$(curRow).remove();
		});
		$(curRow).find("span[name=EditShow]").show().end().find("span[name^=beforEditShow]").hide();
	});
})

</script>
<div class="formTips attention">
	<ul>
		<li>
			<em>新建分组请点击
				<span class="sysicon siAdd"><a id="addConcernGroupBtn" href="javascript:void(0);"><strong>新建分组</strong></a></span>
			</em>
		</li>
	</ul>
</div>
<input name="" id="groupType" type="hidden" value="01"/>
<div class="formLayout detail">
	<table id="concernGroupTable">
		<thead>
			<tr>
				<th style="text-align:center">分组名称</th>
				<th style="text-align:center">排序</th>
				<th style="text-align:center">操作</th>
			</tr>
		</thead>
		<tbody id="concernGroupTbody">
		</tbody>
		<tfoot class="hidden">
			<tr id="">
				<td><span name="beforEditShow_groupName"></span><span class="hidden" name="EditShow"><input name="groupName" value=""/></span></td>
				<td class="r"><span name="beforEditShow_sort"></span><span class="hidden" name="EditShow"><input name="sort" value="" style="width:80px"/></span></td>
				<td class="center"><span name="beforEditShow"><a href="javascript:void(0);" name="edit">修改</a></span><span name="EditShow" class="hidden"><a href="javascript:void(0);" name="save">保存</a>&nbsp;<a href="javascript:void(0);" name="cancle">取消</a></span>&nbsp;<a href="javascript:void(0);" name="delete">删除</a></td>
			</tr>
		</tfoot>
	</table>
</div>