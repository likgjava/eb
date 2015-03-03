var channelTreeForm={};
channelTreeForm.tree;
channelTreeForm.isSort=0;
channelTreeForm.currentObj;
channelTreeForm.channelCount = 0;
channelTreeForm.contentCount = 0;
channelTreeForm.channelDataCount = 0;
channelTreeForm.modelSelect = "";
channelTreeForm.ChannelList = "";
channelTreeForm.VoteTopicList = "";
var channelId = "";
var isLeaf = "false";

// 点击节点事件
channelTreeForm.nodeClick=function(id){
	$('#channelForm').find("input[type=text]").val("");
	if("" == id || "-1" == id){return false;}
	$('#channelReturn').click();
	$('input').removeClass('eleWarning');
	$('span[class=eleWarning]').remove();
	channelId = id;
	channelTreeForm.virCreateStaticButtonStatus(id,(channelTreeForm.tree.hasChildren(id)==0)+"");

	var includedProperties=["channel"];
	
	// 取得栏目查询数据列表
    var ChannelDataListJson=$.ajax({url: $('#initPath').val()+"/ChannelDataController.do?method=getObjectQuery&includedProperties="+includedProperties.toString()+"&queryColumns=objId,name,num,channel,channel.name,channel.objId,display,needLoad,tmpType,dataType,dataVal,dataKey,sortType",data:{"channel.objId":id}, async: false}).responseText;
	var ChannelDataList = "";
	var ChannelDataSpanList = "";
	var ChannelContentDataList = "";
	var ChannelContentDataSpanList = "";
	var dataFrom = "";
	$(JSON.parse(ChannelDataListJson).result).each(function(i,n){
		
		// 只列出当前查询的数据
		if("true"==n.display){
			
			if("channel" == n.dataType){
				dataFrom = "从"+n.dataKey+"栏目获取"+n.num+"条子栏目列表记录";
			}else if("content" == n.dataType){
				dataFrom = "从"+n.dataKey+"栏目按"+channelTreeForm.getSortTypeCn(n.sortType)+"获取"+n.num+"条内容列表记录";
			}else if("voteTopic" == n.dataType){
				dataFrom = "获取"+n.dataKey+"投票";
			}
		}
		if("channel"==n.tmpType){
			if("true"==n.display){
				ChannelDataSpanList += "<li><label>数据："+n.name+"</label>";
				ChannelDataSpanList += "<span>"+dataFrom+"</span>";
				ChannelDataSpanList += "</li>";
			}
			ChannelDataList += channelTreeForm.loadChannelData(n);
			channelTreeForm.channelCount ++;
		}else if("content"==n.tmpType){
			if("true"==n.display){
				ChannelContentDataSpanList += "<li><label>数据："+n.name+"</label>";
				ChannelContentDataSpanList += "<span>"+dataFrom+"</span>";
				ChannelContentDataSpanList += "</li>";
			}
			ChannelContentDataList += channelTreeForm.loadChannelData(n);
			channelTreeForm.contentCount ++;
		}
		channelTreeForm.channelDataCount ++;
		
	});
	if(channelTreeForm.channelDataCount>0){
		channelTreeForm.initChannelDateList("ChannelDataList", channelTreeForm.channelDataCount, ChannelDataList, ChannelDataSpanList);
	}
	if(channelTreeForm.channelDataCount>0){
		channelTreeForm.initChannelDateList("ChannelContentDataList", channelTreeForm.channelDataCount, ChannelContentDataList, ChannelContentDataSpanList);
	}
	
	// 先初始化栏目模型
	if(channelTreeForm.modelSelect==""){
		channelTreeForm.modelListJson=$.ajax({url: $('#initPath').val()+"/ChannelModelController.do?method=getObjectQuery&queryColumns=objId,name,shortName",data:{"display":true}, async: false}).responseText;
		channelTreeForm.modelListJsonObj = JSON.parse(channelTreeForm.modelListJson).result;
		$(channelTreeForm.modelListJsonObj).each(function(i,n){
			channelTreeForm.modelSelect += "<option value='"+n.objId+"'>"+n.name+"</option>";
		})
	}
	$("select[name=channelModel.objId]").empty().append(channelTreeForm.modelSelect);
	
	
	var queryColumns=["parent","channelModel"];
	$.getJSON($('#initPath').val()+'/ChannelController.do?method=createOrUpdate&includedProperties='+queryColumns.toString(),{objId:id},function(json){
		channelTreeForm.currentObj=json.channel;
		
		// 根据栏目获得栏目的模板路径
		if(undefined != json.channel.channelModel && "" != json.channel.channelModel.objId ){
			channelTreeForm.getTemplateList(json.channel.channelModel.objId);
		}
		json2Object('channelSpanForm', json.channel,true);
		jsonObjectToForm('channelForm', json.channel,true);
		
		// 解决先点击
		if(undefined == json.channel.parent){
			$("input[id=parent.objId]").val("");
		}
	});
}

// 初始化树
channelTreeForm.initChannelTree=function(){
	channelTreeForm.tree=new dhtmlXTreeObject('channelTree',"100%","100%",0);
	channelTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	channelTreeForm.tree.enableDragAndDrop(0);
	channelTreeForm.tree.enableThreeStateCheckboxes(true);
	channelTreeForm.tree.setOnClickHandler(channelTreeForm.nodeClick);
	channelTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/ChannelController.do?method=getTree&action=listById&order=sort');//点 + 号触发展开事件
	channelTreeForm.tree.loadXML($('#initPath').val()+'/ChannelController.do?method=getTree&action=listTop&order=sort&isOpen=1',function(){
	});
}

// 验证发布的按钮情况
channelTreeForm.virCreateStaticButtonStatus = function(id,isLeaf){
	if(""==id || "-1"==id){
		$("button[name^=createChannel]").attr("disabled",true);
	}else{
		$("button[name^=createChannel]").attr("disabled",false);
	}
	if(isLeaf=="true"){
		$("button[name^=viewChannelContent]").attr("disabled",false);
	}else{
		$("button[name^=viewChannelContent]").attr("disabled",true);
	}
}

$(document).ready(function(){
	$('#addChannel').click(function(){//新增
		if($('#channelForm input[name=objId]').val()=="" && $('#channelForm input[name=parent.objId]').val()==""){
			alert("顶级栏目下只能有一个首页栏目，不能再添加！")
			return false;
		}
		$('#channelDetail').hide();
		$('#channelFormDiv').show();
		$('#channelForm div input').val('');
		$('#channelForm div textarea').val('');
		$('#channelForm input[name=objId]').val('');
		$('#channelForm input[name=parent.objId]').val(channelTreeForm.tree.getSelectedItemId());
		$('span[id=parent.name]').html(channelTreeForm.tree.getSelectedItemText());
		$("#ChannelDataSpanList").empty();
		$("#ChannelContentDataSpanList").empty();
		$("#ChannelDataList tr").not(":first").empty();
		$("#ChannelContentDataList tr").not(":first").empty();
	});
	
	$('#updateChannel').click(function(){//修改
		if(undefined ==channelTreeForm.currentObj){
			alert("请选择栏目！");
			return false;
		}
		$('#channelDetail').hide();
		$('#channelFormDiv').show();
		jsonObjectToForm('channelForm', channelTreeForm.currentObj);
	});
	
	$('#deleteChannel').click(function(){//删除
		if(undefined ==channelTreeForm.currentObj){
			alert("请选择栏目！");
			return false;
		}
		if($.trim($('#objId').val())!=""){
			if(window.confirm("确定删除?")){
	   			$.getJSON($('#initPath').val()+'/ChannelController.do?method=deleteChannel',{objId:$('#objId').val()},function(json){
					if(json.result=="true"){
						alert("删除成功！");
					}else{
						alert(json.msgs)
					}
					
					treeUtil.refreshTree(channelTreeForm.tree,'delete','');
					$('#channelForm')[0].reset();
				});
			}
		}
		$('#channelForm')[0].reset();
		channelTreeForm.currentObj=null;
	});
	
	$('#channelReturn').click(function(){
		$('#channelDetail').show();
		$('#channelFormDiv').hide();
	});

	// 系统提供的唯一性验证
	$.validator.addMethod("chnlCodeUnique",function(value,element,param){return uniqueHandler("Channel",param,value,$("input[name=objId]").val());},'该栏目编号已存在');
	$('#channelForm').validate({
		rules:{
		chnlCode:{chnlCodeUnique:"chnlCode"}
		}
	});//表单验证
	channelTreeForm.initChannelTree();//加载树
	
	// 提交
	$('#channelSave').click(function(){
		if(!$('#channelForm').valid()){alert('请正确填写表单!');return;}
		var channel=formToJsonObject('channelForm');
		channel.ChannelDataJSONString=JSON.stringify(formToJsonObject("channelForm","json").ChannelData);
		if(""!=$("input[id=parent.objId]").val()){
			channel['parent.isLeaf']='0';
		}
		$.getJSON($('#initPath').val()+'/ChannelController.do?method=saveChannel',channel, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			var pid=$("input[id=parent.objId]").val();
			var id=$('#objId').val();
			channelId = json.channel.objId;
			if(channelTreeForm.tree._globalIdStorageFind(json.channel.objId)){
				channelTreeForm.tree.selectItem(json.channel.objId);
				channelTreeForm.nodeClick(json.channel.objId)
				channelTreeForm.tree.setItemText(json.channel.objId,json.channel.name);
			}else{
				var selectedItemId = channelTreeForm.tree.getSelectedItemId();
				if(!selectedItemId)selectedItemId=-1;
				channelTreeForm.tree.insertNewItem(selectedItemId,json.channel.objId,json.channel.name,0,0,0,0,'SELECT');
				channelTreeForm.tree.setItemImage2(selectedItemId,"node.gif","folderOpen.gif","folderClosed.gif");
			}
			channelTreeForm.virCreateStaticButtonStatus(json.channel.objId,json.channel.isLeaf);
			alert('保存成功');
			$('#objId').val(id);
		});
	});
	
	// 设置拖拽事件
	channelTreeForm.tree.setDragHandler(function(id,newParentId){
		if(channelTreeForm.isSort==1)return true;
   		var jsonObj={};
   		jsonObj.sort=channelTreeForm.tree._globalIdStorageFind(newParentId).childsCount;
   		if(newParentId == "-1")newParentId = null;
   		jsonObj.objId=id;jsonObj.parent={};jsonObj.parent.objId=newParentId;
   		jsonObj.json=JSON.stringify(jsonObj);
		$.getJSON($('#initPath').val()+'/channelController.do?method=save', jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
		});
		return true;
   	});
	
   	// 展开合并所有节点
   	var isOpen = false;  //是否是展开状态
	var isFirst = true;  //是否是第一次展开
	$('#openAll').click(function(){
		if(!isOpen){
			if(isFirst){
				channelTreeForm.tree.deleteChildItems(0);//删掉根节点的所有子节点
				channelTreeForm.tree.loadXML($('#initPath').val()+'/ChannelController.do?method=getTree&action=listTop&isOpen=1');
				isFirst = false;
			}
			else channelTreeForm.tree.openAllItems('0');
			$('#openAll').html('关闭');
			isOpen=true;
		}else{
			channelTreeForm.tree.closeAllItems('0');
			$('#openAll').html('展开');
			isOpen=false;
		}
	});
	
   	// 增加下级节点
   	$('#newChild').click(function(){ 
   		if(channelTreeForm.tree.getSelectedItemId() == null || channelTreeForm.tree.getSelectedItemId() == '')
  			alert("请先选中一个节点，再进行该操作！");
  		else if(channelTreeForm.tree.getSelectedItemId() != "-1"){
  			var pid=$('#objId').val();
  			$('#channelForm')[0].reset();
  			$('input[id=parent.objId]').val(pid);
  		}
   	});
   	
    //上移
    $('#up').click(function(){
        treeUtil.sortUp(channelTreeForm.tree,"up","com.gpcsoft.cms.channel.domain.Channel")
    });

    //下移
    $('#down').click(function(){
    	treeUtil.sortDown(channelTreeForm.tree,"down","com.gpcsoft.cms.channel.domain.Channel")
    });
    
	channelTreeForm.virCreateStaticButtonStatus(channelId,isLeaf);
	
});

// 如果改栏目模型，重新加载栏目的模板
$("select[name=channelModel.objId]").change(function(){
	channelTreeForm.getTemplateList($(this).find("option:selected").val());
});
channelTreeForm.getTemplateList = function(modelId){
	if("" == modelId || undefined ==modelId){
		alert("该栏目对应的模型不存在，请先选择模型！");
		return false;
	}
	var ChannelTemplateList = "<option value=''>使用默认模板</option>";
	
	// 栏目模板
	var ChannelTemplateJson=$.ajax({url:"ChannelController.do?method=getTempList",data:{"modelId":modelId,"templateType":"channel"}, async: false}).responseText;
	
	// 如果该栏目所引用的模型不存在
	if(JSON.parse(ChannelTemplateJson).failure=="true"){
		return false;
	}
	var ChannelTemplateJsonObj = JSON.parse(ChannelTemplateJson).result;
	$(ChannelTemplateJsonObj).each(function(i,n){
		ChannelTemplateList += "<option value='"+n+"'>"+n+"</option>";
	})
	$("select[name=chnlTemplate]").empty().append(ChannelTemplateList);
	
	// 内容模板
	var ContentTemplateJson=$.ajax({url:"ChannelController.do?method=getTempList",data:{"modelId":modelId,"templateType":"content"}, async: false}).responseText;
	var ContentTemplateJsonObj = JSON.parse(ContentTemplateJson).result;
	var ContentTemplateList = "<option value=''>使用默认模板</option>";
	$(ContentTemplateJsonObj).each(function(i,n){
		ContentTemplateList += "<option value='"+n+"'>"+n+"</option>";
	})
	$("select[name=contentTemplate]").empty().append(ContentTemplateList);
}

// 初始化栏目数据来源
channelTreeForm.initChannelDateList = function(channelDateType,count,ChannelDataList, ChannelDataSpanList){
	
	// 生成数据列表
	$("#"+channelDateType+"Span").empty().append(ChannelDataSpanList);
	$("#"+channelDateType+" tr").not(":first").empty();
	$("#"+channelDateType).append(ChannelDataList).find("a[name=delete]").click(function(){
		var flag = channelTreeForm.removeChannelData($(this).parent().parent().find("input[name$=objId]").val());
		if(flag){
			$(this).parent().parent().remove();
		}
	}).end().find("a[name=modify]").click(function(){
		alert("修改")
	}).end().find("select[name$=dataType]").change(function(){
		
		if($(this).find("option[selected]").val()=="channel" || $(this).find("option[selected]").val()=="content"){
			if(""==channelTreeForm.ChannelList){
				
				// 栏目列表
				channelTreeForm.ChannelList = "<option value=''>选择栏目</option>";
				$(channelTreeForm.getChannelListJsonObj()).each(function(i,n){
					channelTreeForm.ChannelList += "<option value='"+n.objId+"'>"+n.name+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.ChannelList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
			channelTreeForm.initChannelDataLi($(this).parent(),false);
		}else if("voteTopic"==$(this).find("option[selected]").val()){
			if(""==channelTreeForm.VoteTopicList){
				
				// 投票列表
				channelTreeForm.VoteTopicList = "<option value=''>选择投票</option>";
				$(channelTreeForm.getVoteTopicListJsonObj()).each(function(i,n){
					channelTreeForm.VoteTopicList += "<option value='"+n.objId+"'>"+n.title+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.VoteTopicList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
			channelTreeForm.initChannelDataLi($(this).parent(),true);
		}else if("bulletin"==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append($("#template select[name=bullType]").html()).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
			channelTreeForm.initChannelDataLi($(this).parent(),false);
		}else if(""==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append("<option value=''>--请选择--</option>");
		}
		
	})
}

// 获取栏目列表
channelTreeForm.getChannelListJsonObj  = function(){
	channelTreeForm.ChannelListJson=$.ajax({url:"ChannelController.do?method=getObjectQuery&queryColumns=objId,name",data:{"order":"sort"}, async: false}).responseText;
	channelTreeForm.ChannelListJsonObj = JSON.parse(channelTreeForm.ChannelListJson).result;
	return channelTreeForm.ChannelListJsonObj;
}

// 获取投票列表
channelTreeForm.getVoteTopicListJsonObj  = function(){
	channelTreeForm.VoteListJson=$.ajax({url:"VoteTopicController.do?method=getObject",data:{"order":"totalCount"}, async: false}).responseText;
	channelTreeForm.VoteTopicListJsonObj = JSON.parse(channelTreeForm.VoteListJson).list;
	return channelTreeForm.VoteTopicListJsonObj;
}


// 添加一行栏目数据
$("#addChannelDataRow").click(function(){
	channelTreeForm.channelCount ++;
	channelTreeForm.addChannelDataRow("ChannelDataList",channelTreeForm.channelCount,"channel");
});

// 添加一行内容数据
$("#addChannelContentDataRow").click(function(){
	channelTreeForm.contentCount ++;
	channelTreeForm.addChannelDataRow("ChannelContentDataList",channelTreeForm.contentCount,"content");
});

channelTreeForm.addChannelDataRow = function(channelDateType,sort,tmpType){
	var ChannelDataList = "";
	channelTreeForm.channelDataCount ++;
	ChannelDataList += "<tr><td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].tmpType' value='"+tmpType+"' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].objId' value='' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].dataKey' value=''/>";
	ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataType'  style='width:80px'><option value='' selected='selected'>-请选择-</option><option value='channel'>栏目</option><option value='content'>内容</option><option value='bulletin'>公告</option><option value='voteTopic'>投票</option></select>";
	ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataVal' style='width:100px'><option value=''>-请选择-</option></select></td>";
	ChannelDataList += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].name' style='width:50px'/><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].sortNo' value='"+sort+"' size='' sytle='width:10px'/></td>";
	ChannelDataList += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].num' value='10' size='2' style='width:20px'/></td>";
	ChannelDataList += "<td><select style='width:80px' name='ChannelData["+channelTreeForm.channelDataCount+"].sortType'><option value='1'>时间倒序</option><option value='2'>时间顺序</option><option value='3'>访问量倒序</option><option value='4'>访问量顺序</option></select></td>";
	ChannelDataList += "<td><input type='checkbox' name='ChannelData["+channelTreeForm.channelDataCount+"].display' value='true'></td>";
	ChannelDataList += "<td><a href='javascript:void(0)' title='删除' name='delete'>删除</a></td>";
	ChannelDataList += "</tr>";
	$("#"+channelDateType).append(ChannelDataList).find("tr a[name=delete]").click(function(){
		$(this).parent().parent().remove();
	}).end().find("tr a[name=modify]").click(function(){
		alert("修改")
	}).end().find("select[name$=dataType]").change(function(){
		
		if($(this).find("option[selected]").val()=="channel" || $(this).find("option[selected]").val()=="content"){
			if(""==channelTreeForm.ChannelList){
				
				// 栏目列表
				channelTreeForm.ChannelList = "<option value=''>选择栏目</option>";
				$(channelTreeForm.getChannelListJsonObj()).each(function(i,n){
					channelTreeForm.ChannelList += "<option value='"+n.objId+"'>"+n.name+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.ChannelList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			})

			channelTreeForm.initChannelDataLi($(this).parent().parent(),false);
		}else if("voteTopic"==$(this).find("option[selected]").val()){
			if(""==channelTreeForm.VoteTopicList){
				
				// 投票列表
				channelTreeForm.VoteTopicList = "<option value=''>选择投票</option>";
				$(channelTreeForm.getVoteTopicListJsonObj()).each(function(i,n){
					channelTreeForm.VoteTopicList += "<option value='"+n.objId+"'>"+n.title+"</option>";
				})
			}
			$(this).parent().parent().find("select[name$=dataVal]").empty().append(channelTreeForm.VoteTopicList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			})
			channelTreeForm.initChannelDataLi($(this).parent().parent(),true);
		}else if("bulletin"==$(this).find("option[selected]").val()){
			$(this).parent().parent().find("select[name$=dataVal]").empty().append($("#template select[name=bullType]").html()).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			})
			channelTreeForm.initChannelDataLi($(this).parent().parent(),false);
		}else if(""==$(this).find("option[selected]").val()){
			$(this).parent().parent().find("select[name$=dataVal]").empty().append("<option value=''>--请选择--</option>").end().find("[name$=.num]").val("10").attr("disabled",false).end().find("[name$=.sortType]").attr("disabled",false);
		}

	});
}

channelTreeForm.initChannelDataLi = function(n,disabledFlag){
	$(n).find("[name$=.num]").attr("disabled",disabledFlag).end().find("[name$=.sortType]").attr("disabled",disabledFlag);
}

// 删除对数对象
channelTreeForm.removeChannelData = function(id){
	var falg = false;
	$.ajax({
		url: $("#initPath").val()+"/ChannelDataController.do?method=remove",
		type:"POST",
		data:{"objId":id},
		async:false,
		dataType:'json',
		success:function(msg){
			if(msg.success=="true"){
				falg =  true
			}else{
				alert(msg.result);
				falg =  false;
			}
		}
	})
	return falg;
}

// 发布栏目静态页面
$("button[name=createChannelStaticPages]").click(function(){
	$(this).attr("disabled",true);
	$(this).find("span").html("正在发布栏目静态页面...");
	$.ajax({
		url: $("#initPath").val()+"/ChannelController.do?method=createStaticPages",
		type:"POST",
		data:{"channelId":channelId,"pagesType":"channel"},
		dataType:'json',
		success:function(msg){
			if(msg.result=="true"){
				alert("发布成功");
				$("button[name=createChannelStaticPages]").attr("disabled",false);
				$("button[name=createChannelStaticPages]").find("span").html("发布栏目静态页面");
			}else{
				alert("发布失败");
				$("button[name=createChannelStaticPages]").attr("disabled",false);
				$("button[name=createChannelStaticPages]>span").html("发布栏目静态页面");
			}
		}
	})

});

// 发布栏目内容静态页面
$("button[name=createChannelNewsStaticPages]").click(function(){
	$(this).attr("disabled",true);
	$(this).find("span").html("正在发布该栏目下的所有静态内容页面...");
	$.ajax({
		url: $("#initPath").val()+"/ChannelController.do?method=createStaticPages",
		type:"POST",
		data:{"channelId":channelId,"pagesType":"content"},
		dataType:'json',
		success:function(msg){
			if(msg.result=="true"){
				alert("发布成功");
				$("button[name=createChannelNewsStaticPages]").attr("disabled",false);
				$("button[name=createChannelNewsStaticPages]").find("span").html("发布内容静态页面");
			}else{
				alert("发布失败");
				$("button[name=createChannelNewsStaticPages]").attr("disabled",false);
				$("button[name=createChannelNewsStaticPages]>span").html("发布内容静态页面");
			}
		}
	})
	
});


// 查看并导出数据字典
$("button[name=viewChannelContentDictionary]").click(function(){
	$.epsDialog({
		title:"查看数据字典",
		url:$("#initPath").val()+'/view/cms/channel/channel_dictionary.jsp?channelModelId='+$('select[name=channelModel.objId]').val(),
		width:650, 
		height:450,
		afterLoad: function(){$('#channelNameDataDictionary').html($('span[id=name]').html()+"("+$('span[id=channelModel.name]').html()+")数据字典") }
	})
	return false;
})

// 设置定时器
$("button[name=createChannelTimeTask]").click(function(){
	$.epsDialog({
		title:"设置定时器",
		url:$("#initPath").val()+'/view/cms/channel/channel_timer_task.jsp?',
		width:500, 
		height:230,
		afterLoad: function(){$('#channelNameDataDictionary').html($('span[id=name]').html()+"("+$('span[id=channelModel.name]').html()+")数据字典") }
	})
	return false;
})


// 内容的xml格式
$("button[name=viewChannelContentXML]").click(function(){
	window.open($('#initPath').val()+"/ChannelController.do?method=downLoadImportXml&objId="+channelId);
	return false;
})

channelTreeForm.getSortTypeCn=function(sortType){
	if("1"==sortType){
		return  "时间倒序"
	}else if("2"==sortType){
		return  "时间顺序"
	}else if("3"==sortType){
		return  "访问量倒序"
	}else if("4"==sortType){
		return  "访问量顺序"
	}
}

channelTreeForm.getSortType = function(sortType){
	var sortType1 = "";
	var sortType2 = "";
	var sortType3 = "";
	var sortType4 = "";
	if((sortType+"")=="2"){
		sortType2 = "selected";
	}else if((sortType+"")=="3"){
		sortType3 = "selected";
	}else if((sortType+"")=="4"){
		sortType4 = "selected";
	}
	var sortTypeStr = "";
	sortTypeStr += "<select style='width:80px' name='ChannelData["+channelTreeForm.channelDataCount+"].sortType'>";
	sortTypeStr += "<option value='1' "+sortType1+">时间倒序</option>";
	sortTypeStr += "<option value='2' "+sortType2+">时间顺序</option>";
	sortTypeStr += "<option value='3' "+sortType3+">访问量倒序</option>";
	sortTypeStr += "<option value='4' "+sortType4+">访问量顺序</option>";
	sortTypeStr += "</select>";
	return sortTypeStr ;
}

channelTreeForm.loadChannelData = function(n){
	var checkedDisplay = "";
	if(n.display=="true"){
		checkedDisplay = "checked";
	}
	var checkedNeedLoad = "";
	if(n.needLoad=="true"){
		checkedNeedLoad = "checked";
	}
	
	var checkedChannel = "";
	var checkedContent = "";
	var checkedVotopic = "";
	var checkedBulletin = "";
	if("channel" == n.dataType){
		checkedChannel = "selected";
	}else if("content" == n.dataType){
		checkedContent = "selected";
	}else if("voteTopic" == n.dataType){
		checkedVotopic = "selected";
	}else if("bulletin" == n.dataType){
		checkedBulletin = "selected";
	}
	var data_list = "";
	data_list += "<tr><td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].tmpType' value='"+n.tmpType+"' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].objId' value='"+n.objId+"' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].dataKey' value='"+n.dataKey+"'/>";
	data_list += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataType' style='width:80px'><option value=''>-请选择-</option><option value='channel' "+checkedChannel+">栏目</option><option value='content' "+checkedContent+">内容</option><option value='bulletin' "+checkedBulletin+">公告</option><option value='voteTopic' "+checkedVotopic+">投票</option></select>";
	data_list += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataVal' style='width:100px'><option value='"+n.dataVal+"' selected style='width:80px'>"+n.dataKey+"</option></select></td>";
	data_list += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].name' value='"+n.name+"' style='width:50px'/><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].sortNo' value='"+channelTreeForm.channelCount+"' size='2'/></td>";
	data_list += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].num' value='"+n.num+"' size='2' style='width:20px'/></td>";
	data_list += "<td>"+channelTreeForm.getSortType(n.sortType)+"</td>";
	data_list += "<td><input type='checkbox' name='ChannelData["+channelTreeForm.channelDataCount+"].display' value='true' "+checkedDisplay+"></td>";
	data_list += "<td><a href='javascript:void(0)' class='del' title='删除' name='delete'>删除</a></td>";
	data_list += "</tr>";
	return data_list;
}

//关闭打开
$('[id^=closeChannel]').toggle(function(){
	$("#"+(this.id).replace("close","")).show()
	$(this).attr("class","stop");
	$(this).find("span").html("关闭");
	if(this.id=="closeChannelDataList")$('#addChannelDataRow').show();
	if(this.id=="closeChannelContentDataList")$('#addChannelContentDataRow').show();
},function(){
	$("#"+(this.id).replace("close","")).hide()
	$(this).attr("class","enable");
	$(this).find("span").html("打开");
	if(this.id=="closeChannelDataList")$('#addChannelDataRow').hide();
	if(this.id=="closeChannelContentDataList")$('#addChannelContentDataRow').hide();
}); 
