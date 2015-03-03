var channelModelForm={};

$(document).ready(function(){
	
	$.validator.addMethod("nameUnique",function(value,element,param){return uniqueHandler("ChannelModel",param,value,"");},'该模型名称已存在');
	$.validator.addMethod("shortNameUnique",function(value,element,param){return uniqueHandler("ChannelModel",param,value,"");},'该模型简称已存在');

    $("#createTime").epsDatepicker();
   	
    if($('#channelModelId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ChannelModelController.do?method=createOrUpdate',{objId:$('#channelModelId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('channelModelForm', json.channelModel);
    		if(true==json.channelModel.display){
    			$("#channelModelForm").attr("checked",true);
    		}
    		// 如果当前是拷贝操作，则当作新增操作验证
    		if($("#isCopy").val()!="true"){
    			$.validator.addMethod("nameUnique",function(value,element,param){return uniqueHandler("ChannelModel",param,value,json.channelModel.objId);},'该模型名称已存在');
    			$.validator.addMethod("shortNameUnique",function(value,element,param){return uniqueHandler("ChannelModel",param,value,json.channelModel.objId);},'该模型简称已存在');
    		}

    	});
    }
    
    // 验证模型名称和简称是否已经存在
    $("#channelModelForm").validate({
		rules:{
		name:{nameUnique:"name"},
		shortName:{shortNameUnique:"shortName"}
		}
	});	
    
    if($("#isCopy").val()=="true"){
    	$('#modelH5Title').html("拷贝栏目模型");
    	$('#modelItemH5Title').html("拷贝栏目模型表单数据");
    }

	
	// 查询模型条目
	var queryColumns="objId,label,name,required,sort,formType,fullLine,help,display,channelModel.objId,channelModel.name";
	var ChannelModelItemJson=$.ajax({url:"ChannelModelItemController.do?method=getObjectQuery&queryColumns="+queryColumns,data:{"order":"sort","channelModel.objId":$("#channelModelId").val()}, async: false}).responseText;
	var ChannelModelJsonObj = JSON.parse(ChannelModelItemJson).result;
	var channelModelItemLi = "";
	$(ChannelModelJsonObj).each(function(i,n){
		var sort = i;
		var  displayChenked= "";
		var  requiredChenked= "";
		var  fullLineChenked= "";
		if("true"==n.display){
			displayChenked = "checked";
		}
		if("true"==n.required){
			requiredChenked = "checked";
		}
		if("true"==n.fullLine){
			fullLineChenked = "checked";
		}
		var formType1 = "";
		var formType2 = "";
		var formType3 = "";
		var formType4 = "";
		var formType5 = "";
		if("1"==n.formType){
			formType1 = "selected";
		}else if("2"==n.formType){
			formType2 = "selected";
		}else if("3"==n.formType){
			formType3 = "selected";
		}else if("4"==n.formType){
			formType4 = "selected";
		}else if("5"==n.formType){
			formType5 = "selected";
		}
		var upAndDown = "";
		upAndDown +='<a name="move_up" class="upMove"   href="javascript:void(0)"><span>上移</span></a>';
		upAndDown +='<a name="move_down" class="downMove"  href="javascript:void(0)"><span>下移</span></a>';
		channelModelItemLi += "<li class='fullLine' id='"+n.objId+"' sort='"+(i+1)+"'>";
		channelModelItemLi += "<label>名称</label>";
		channelModelItemLi += "<input type='text' name='ChannelModelItem["+sort+"].name' disabled='disabled' size='5' value='"+n.name+"'/>";
		channelModelItemLi += "<span>标题</span><input type='text' name='ChannelModelItem["+sort+"].label'  size='6'  value='"+n.label+"'/>";
		channelModelItemLi += "<input type='text' class='hidden' name='ChannelModelItem["+sort+"].sort'  size='2' value='"+n.sort+"'/>"+upAndDown+"";
		channelModelItemLi += "<span>提示信息</span><input type='text' name='ChannelModelItem["+sort+"].help'  size='20'  value='"+n.help+"'/>";
		channelModelItemLi += "<span>显示</span><input type='checkbox' name='ChannelModelItem["+sort+"].display' "+displayChenked+" value='true'  />";
		channelModelItemLi += "<span>必填</span><input type='checkbox' name='ChannelModelItem["+sort+"].required' "+requiredChenked+" value='true'  />";
		channelModelItemLi += "<span>单行</span><input type='checkbox' name='ChannelModelItem["+sort+"].fullLine' "+fullLineChenked+" value='true'  />";
		channelModelItemLi += "&nbsp;<a href='javascript:void(0)' name='editItemLi' title='配置输入框'>配置输入框</a>";
		channelModelItemLi += "&nbsp;<a href='javascript:void(0)' name='deleteItemLi' title='删除'>删除</a>";
		channelModelItemLi += "<input type='hidden' name='ChannelModelItem["+sort+"].objId' value='"+n.objId+"'/>";
		channelModelItemLi += "</li>";
	})
	$("#ChannelDataList").append(channelModelItemLi).find("li>a[name=deleteItemLi]").click(function(){
		channelModelForm.removeChannelItem();
	}).end().find("li>a[name=editItemLi]").click(function(){
		$.epsDialog({
			title:'配置输入框',
			url:'ChannelModelItemController.do?method=toEditChannelModelItem&objId='+$(this).parent().find("input[name$=.objId]").val(),
			width: '620',
			height: '320',
			hasBg:true,//背景
			fadeTo:80 //透明度
		});
		return false;
	}).end().find("input").blur(function(){
		$(this).removeClass("eleRight");
	}).end().find('a[name^=move_]').click(function(){
		var objIdA = $(this).parent().attr('id');
		var sortA = parseInt($(this).parent().attr('sort'));
		var jsonObj = {};
		jsonObj['className'] = 'com.gpcsoft.cms.channel.domain.ChannelModelItem';
		if($(this).attr('name').replace('move_','')=='up'){
			if(objIdA == $("#ChannelDataList>li:first").attr('id')){
				 alert("已经到达顶端！");
				 return false;
			 }
			var objIdB = $(this).parent().prev('li').attr('id');
			var sortB = parseInt($(this).parent().prev('li').attr('sort'));
			 jsonObj['sortA.objId'] = objIdA;
			 jsonObj['sortA.sort'] = sortB;
			 jsonObj['sortB.objId'] = objIdB;
			 jsonObj['sortB.sort'] = sortA;
			 $(this).parent().attr('sort',sortB);
			 $(this).parent().find('input[name$=sort]').val(sortB);
			 $(this).parent().prev('li').attr('sort',sortA);
			 $(this).parent().prev('li').find('input[name$=sort]').val(sortA);
			 $($(this).parent().insertBefore($(this).parent().prev('li')));
		}else{
			 if(objIdA == $("#ChannelDataList>li:last").attr('id')){
				 alert("已经到达底部！");
				 return false;
			 }
			 var objIdB = $(this).parent().next('li').attr('id');
			 var sortB = parseInt($(this).parent().next('li').attr('sort'));
			 jsonObj['sortA.objId'] = objIdA;
			 jsonObj['sortA.sort'] = sortB;
			 jsonObj['sortB.objId'] = objIdB;
			 jsonObj['sortB.sort'] = sortA;
			 $(this).parent().attr('sort',sortB);
			 $(this).parent().find('input[name$=sort]').val(sortB);
			 $(this).parent().next('li').attr('sort',sortA);
			 $(this).parent().next('li').find('input[name$=sort]').val(sortA);
			 $($(this).parent().insertAfter($(this).parent().next('li')));
		}
		$.ajax({
			url:$("#initPath").val()+"/SortController.do?method=sort",
			type:"POST",
			async:false,
			data:jsonObj,
			success:function(msg){
			},error:function(e){
				alert("排序失败！");
				return true;
			}
		})
		return true;
	});

	
	
	
	// 返回
	$('#channelModelReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ChannelModelController.do");
	});
    
	// 提交
	$('#channelModelSave').click(function(){
		if(!$('#channelModelForm').valid()){alert('请正确填写表单!');return;}
		var channelModel = formToJsonObject('channelModelForm');
		channelModel.ChannelModelItemJSONString=JSON.stringify(formToJsonObject("channelModelForm","json").ChannelModelItem);
		channelModel.isCopy = $("#isCopy").val();
		$.getJSON($('#initPath').val()+'/ChannelModelController.do?method=saveChannelModel',channelModel , function(json){
			if(json.failure){alert(json.result);return;}
			
			$('#conBody').loadPage($('#initPath').val()+'/ChannelModelController.do');
		});
	});

});

$("#addChannelModelItemRow").click(function(){
	var channelModelItemLi = "";
	var sort = $("#ChannelDataList").find("li").length;
	if(sort>0){
		sort = $("#ChannelDataList").find("li:last").attr('sort');
	}
	sort = parseInt(sort)+1;
	channelModelItemLi += "<li class='fullLine' sort='"+sort+"'>";
	channelModelItemLi += "<label>名称</label>";
	channelModelItemLi += "<input type='text' name='ChannelModelItem["+sort+"].name' 	size='5' />";
	channelModelItemLi += "<span>标题</span><input type='text' name='ChannelModelItem["+sort+"].label'  size='6' />";
	channelModelItemLi += "<span>序号</span><input type='text' class='' name='ChannelModelItem["+sort+"].sort'  size='2' value='"+sort+"'/>";
	channelModelItemLi += "<span>提示信息</span><input type='text' name='ChannelModelItem["+sort+"].help'  size='20' />";
	channelModelItemLi += "<span>显示</span><input type='checkbox' name='ChannelModelItem["+sort+"].display' value='true' />";
	channelModelItemLi += "<span>必填</span><input type='checkbox' name='ChannelModelItem["+sort+"].required' value='true' />";
	channelModelItemLi += "<span>单行</span><input type='checkbox' name='ChannelModelItem["+sort+"].fullLine' value='true' />";
	channelModelItemLi += "&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' name='deleteItemLi' title='删除'>删除</a>";
	channelModelItemLi += "</li>";
	$("#ChannelDataList").append(channelModelItemLi).find("li>a[name=deleteItemLi]").click(function(){
		$(this).parent().remove();
	}).end().find("input").blur(function(){
		$(this).removeClass("eleRight");
	});
});

channelModelForm.removeChannelItem = function(){
	alert("暂不支持删除！如不要该表单可取掉显示选择。");
}
