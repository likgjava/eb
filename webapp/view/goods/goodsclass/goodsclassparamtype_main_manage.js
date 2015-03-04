var goodsClassParam={};
goodsClassParam.count = 0;
var goodsClassParamTypeTreeForm = {};
var goodsClassId = "";

//判断是否有为保存的行
goodsClassParam.newGoodsClassParamCount = function() {
	var canAddNewGoodsClassParam = true;
	if($("#goodsClassParamTbody").find("tr input[name=objId][value=]").length >= 1) {
		canAddNewGoodsClassParam = false;
	}
	return canAddNewGoodsClassParam
}

// 添加顶级参数
goodsClassParam.addTopRow = function(objId, paramName){
	var row = $("#goodsClassParamTfoot").find("tr:first").clone(true).appendTo("#goodsClassParamTbody");
	// 设置关闭展开的样式
	if("" != objId){
		$(row).addClass("open");
	}else{
		$(row).addClass("close");
	}
	$(row).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
	$(row).attr("id",objId).attr("pid","").attr("path","");
	$(row).find("td input[name=objId]").val(objId);
	$(row).find("td input[name=paramName]").val(paramName);
	$(row).find("input:first").css({width:"130px"});
	$(row).find("img").hide();
}

// 为当前的参数添加下级
goodsClassParam.addNewRow = function( currentRow, index, objId, paramName, parentId, path) {
	var curRowCss = parseInt($(currentRow).find("input[name=paramName]").css("width").replace("px",""))-19;
	if(goodsClassParam.newGoodsClassParamCount()) {
		// 克隆行
		var nrow = $("#goodsClassParamTfoot").find("tr").eq(index).clone(true);
		// 设置关闭展开的样式
		if("" != objId){
			$(nrow).addClass("hidden");
			$(nrow).addClass("open");
		}else{
			$(nrow).addClass("close");
			$(currentRow).removeClass("open").addClass("close");
			$(nrow).find("td").find("span[name=closeOrOpen]").addClass("bagTreeLine");
		}
		$(nrow).attr("id",objId).attr("pid",parentId).attr("path",path);
		$(nrow).find("input[name=objId]").val(objId);
		$(nrow).find("input[name=parent.objId]").val(parentId);
		$(nrow).find("input[name=paramName]").val(paramName).css({width:curRowCss+"px"});
		$(nrow).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
		
		//将新行进行追加
		if( $("#goodsClassParamTbody tr[path^="+path+"]").length > 0) {
			$("#goodsClassParamTbody tr[path^="+path+"]:last").after(nrow);
		}else {
			$(currentRow).after(nrow);
		}
		
		return nrow;
	}else {
		alert("请先保存新增的参数！");
	}
}

// 保存参数
function saveGoodsClassParamType(curElement, ParamType) {
	var curRow = $(curElement).parent().parent();
	var parentObjId = $(curRow).find("input[name=parent.objId]").val();
	var  paramName = $(curRow).find("input[name=paramName]").val();
	if(paramName == "") {
		$(curRow).find("span[class=eleRequired]").html("请输入商品参数名称");
		return false;
	}else if(paramName.length > 50 ){
		$(curRow).find("span[class=eleRequired]").html("不能超过 50个字");
		return false;
	}
	var goodsClassParamTypeObj = {};
	goodsClassParamTypeObj.objId = $(curRow).find("input[name=objId]").val();
	goodsClassParamTypeObj.paramName = paramName;
	if(parentObjId != ""){
		goodsClassParamTypeObj.parent = {};
		goodsClassParamTypeObj.parent.objId = parentObjId
	}
	goodsClassParamTypeObj.goodsClass = {};
	goodsClassParamTypeObj.goodsClass.objId = goodsClassId;
	var jsonObj=$.ajax({url:$("#initPath").val()+"/GoodsClassParamTypeController.do?method=isUnique",data:{"goodsClassId":goodsClassId,"objId":goodsClassParamTypeObj.objId,"parentId":parentObjId,"paramName":native2ascii(paramName)}, async: false }).responseText
	jsonObj = eval("("+jsonObj+")");
	if(jsonObj.isUnique !="true"){
		$(curRow).find("span[class=eleRequired]").html("该参数名称已经存在！");
		return false;
	}
	$.getJSON($("#initPath").val()+"/"+ParamType+"Controller.do?method=save"+ParamType,{"goodsClassParamTypeJson":JSON.stringify(goodsClassParamTypeObj)},function(json){
		if(json.success == "true"){
			$(curRow).find("input[name=objId]").val(json.objId);
			$(curRow).attr("id",json.objId);
			$(curRow).attr("pid",json.parentId);
			$(curRow).attr("path",json.path);
			$(curRow).find("a").removeClass("hidden");
			$(curRow).find("a>span").removeClass("hidden");
			$(curRow).find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
		    $(curRow).find("a[name^=detilGoodsClassParam]>span").removeClass("siEdit").addClass("siEditBtn");
			initUpAndDownCss();
		}
		if(json.failure) {
			alert(json.result);
			return;
		}
	});
	
}

// 初始化数据
function initQualification(currentRow,allQuqlityListJsonObj) {
	var objId = $(currentRow).attr("id");
	$(allQuqlityListJsonObj).each(function(i, n) {
		if(n.parent && n.parent.objId == objId) {
			
			// 追加当前行
			var prow = goodsClassParam.addNewRow ( currentRow, parseInt(n.paramType.substring(1))-1, n.objId, n.paramName, n.parent.objId, n.path) ;
			
			//递归追加下级
			if(n.isLeaf == "false") {
				initQualification(prow,allQuqlityListJsonObj);
			}
		}
	})
}

// 设置上下移动和删除的样式
function initUpAndDownCss() {
	$("#goodsClassParamTbody tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#goodsClassParamTbody tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	$("#goodsClassParamTbody tr a[name^=removeGoodsClassParam]>span").removeClass("siExitGray").addClass("siExit");
	
    $("#goodsClassParamTbody tr").each(function() {
    	$("#goodsClassParamTbody tr[path*="+$(this).attr("id")+"]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    	$("#goodsClassParamTbody tr[path*="+$(this).attr("id")+"][pid="+$(this).attr("id")+"]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
    	
    	// 删除样式设置
    	if($("#goodsClassParamTbody tr[path*="+$(this).attr("id")+"]").length > 0) {
    		$(this).find("a[name^=removeGoodsClassParam]>span").removeClass("siExit").addClass("siExitGray");
    		// 设置展开关闭样式
    		var _spanCloseOrOpen = $(this).find("td").find("span[name=closeOrOpen]");
    		if($(this).hasClass("close")){
    			$(_spanCloseOrOpen).removeClass("bagClose").removeClass("bagOpen").addClass("bagClose");
    		}else{
    			$(_spanCloseOrOpen).removeClass("bagClose").removeClass("bagOpen").addClass("bagOpen");
    		}
    	}else{
    		$(this).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen");//.addClass("bagTreeLine");
    	}
    })
    
    // 顶级节点 
    $("#goodsClassParamTbody tr[path=]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    $("#goodsClassParamTbody tr[path=]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
    
    
}

goodsClassParam.loadGoodClassParam = function(goodsClassId) {
	
	// 初始化数据
	$.getJSON($('#initPath').val()+"/GoodsClassParamTypeController.do?method=getObject",{"order":"sort","goodsClass.objId":goodsClassId},function(json){
		var allQuqlityListJsonObj = json.list;
		
		//添加顶级数据
	    $(allQuqlityListJsonObj).each(function(i,n){
	    	if(undefined == n.parent) {
	    		goodsClassParam.addTopRow( n.objId, n.paramName);
	    		
	    	}
	    })
	    
	    //递归添加下级
	    $("#goodsClassParamTbody").find("tr").each(function(i,n){
	    	initQualification(n,allQuqlityListJsonObj);
	    })
	    
	   
	    //去掉多余样式
	    $("#goodsClassParamTbody tr").find("a").removeClass("befSave");
	    $("#goodsClassParamTbody tr").find("a").removeClass("hidden");
	    $("#goodsClassParamTbody tr").find("a>span").removeClass("hidden");
	    $("#goodsClassParamTbody tr").find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
	    
	    initUpAndDownCss();
	    if($("#goodsClassParamTbody").find("tr").length < 1) {
	    	goodsClassParam.addTopRow( "", "");
	    }
	});
}

// 点击节点事件
goodsClassParamTypeTreeForm.nodeClick=function(id){
	goodsClassId = id;
    $("#goodsClassParamTbody").empty();
    $("#addGoodsClassParamTypeBtn").attr("disabled",true);
    if("" != id && "-1" != id) {
    	$("#addGoodsClassParamTypeBtn").attr("disabled",false);
    	$("input[name=goodsClass.objId]").val(id)
    	goodsClassParam.loadGoodClassParam(id);
    }
    
    //控制是否可以导入参数
    if(goodsClassParamTypeTreeForm.tree.getLevel(id) > 2){
    	$("#importGoodsClassParamTypeBtn").parent().show();
    }else{
    	$("#importGoodsClassParamTypeBtn").parent().hide();
    }
}

// 初始化树
goodsClassParamTypeTreeForm.initGoodsClassTree=function(){
    goodsClassParamTypeTreeForm.tree=new dhtmlXTreeObject('goodsClassParamTypeTreeForm',"100%","100%",0);
    goodsClassParamTypeTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    goodsClassParamTypeTreeForm.tree.enableDragAndDrop(1);
    goodsClassParamTypeTreeForm.tree.enableThreeStateCheckboxes(true);
    goodsClassParamTypeTreeForm.tree.setOnClickHandler(goodsClassParamTypeTreeForm.nodeClick);
    goodsClassParamTypeTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listById&order=sort&order_flag=false');//点 + 号触发展开事件
    goodsClassParamTypeTreeForm.tree.loadXML($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listTop&isOpen=0&order=sort&order_flag=false',function(){
    });
}

$(document).ready(function() {
	
	// 保存
	$('a[name^=save_]').click(function(){
		saveGoodsClassParamType(this,$(this).attr("name").replace("save_",""));
	});
	
	// 初始加载时将添加按钮置为不可用
	$("#addGoodsClassParamTypeBtn").attr("disabled",true);
	
	// 加载树
	goodsClassParamTypeTreeForm.initGoodsClassTree();
	$("input[name=paramName]").focus(function() {
    	$(this).parent().find("span.eleRequired").empty();
    });
});


// 添加参数
$("a[name=addGoodsClassParam]").click(function(){
	goodsClassParam.addNewRow( $(this).parent().parent(), 1, "", "", $(this).parent().parent().find("input[name=objId]").val(),$(this).parent().parent().attr("path")+$(this).parent().parent().attr("id"));
})


// 添加顶级参数分类
$("[id=addGoodsClassParamTypeBtn]").click(function(){
	if(goodsClassId == "" || goodsClassId == "-1"){
		alert("请先选择要添加参数的商品的分类！");
		return false;
	}
	if(goodsClassParam.newGoodsClassParamCount()) {
		goodsClassParam.addTopRow( "", "");
	}else {
		alert("请先保存新增的参数！");
	}
});

//导入参数分类
$("#importGoodsClassParamTypeBtn").click(function(){
	var pid = goodsClassParamTypeTreeForm.tree.getParentId(goodsClassId);
	var goodsClassIds = pid;
	var goodsClassNames = goodsClassParamTypeTreeForm.tree.getItemText(pid);
	
	var subIdsArray = goodsClassParamTypeTreeForm.tree.getSubItems(pid).split(',');
	for(var i=0; i<subIdsArray.length; i++){
		if(goodsClassId != subIdsArray[i]){
			goodsClassIds += ',' + subIdsArray[i];
			goodsClassNames += ',' + goodsClassParamTypeTreeForm.tree.getItemText(subIdsArray[i]);
		}
	}
	
	//存放商品分类id和name供弹出层使用
	$("#importGoodsClassIds").val(goodsClassIds);
	$("#importGoodsClassNames").val(goodsClassNames);
	
	$.epsDialog({
        title:'导入类似商品分类的参数',
        url: $("#initPath").val()+"/view/goods/goodsclass/goodsclassparamtype_import.jsp?goodsClassId="+goodsClassId,
        width: '500',
        height: '300',
        onClose: function(){
			if($("#importSuccess").val() == "1"){
				$("#importSuccess").val("0"); //还原标记
				goodsClassParamTypeTreeForm.nodeClick(goodsClassId);
			}
		}
	});
});

// 详情
$("tr a[name^=detilGoodsClassParam]").click(function() {
	var actionName = $(this).attr("name").replace("detil","");
	var objId = $(this).parent().parent().attr("id");
	 $.epsDialog({
	        title:'参数详情',
	        url:$("#initPath").val()+"/"+actionName+"Controller.do?method=toCreateOrUpdate&objId=" + objId,
	        width: '500',
	        height: '300',
	        onClose: function(){}
	 }); 
})

// 删除
$("tr a[name^=removeGoodsClassParam]").click(function() {
	var actionName = $(this).attr("name").replace("remove","");
	var curRow = $(this).parent().parent();
	var objId = $(curRow).attr("id");
	// 如果还没有保存就删除，则只需要将该行删除即可
	if("" == objId) {
		$(curRow).remove();
		
		// 设置上下移动的样式
		initUpAndDownCss();
	}else {
		
		if($("#goodsClassParamTbody tr[path*="+objId+"]").length > 0) {
			return false;
		}
		if(window.confirm("确定要删除该参数吗？")) {
			$.getJSON($('#initPath').val()+"/"+actionName+"Controller.do?method="+$(this).attr("name"),{"objId":objId},function(json) {
				if(json.failure) {
					alert(json.result);
					return;
				}
				$(curRow).remove();
				
				// 设置上下移动的样式
				initUpAndDownCss();
			})
			
		}
		
	}
})

// 展开关闭
$("[name=closeOrOpen]").click(function() {
	var row = $(this).parent("td").parent("tr");
	var _spanCloseOrOpen = $(this);
	
	//如果是子节点，返回
	if ($(_spanCloseOrOpen).hasClass("bagTreeLine")) {
		return false;
	}
	
	if($(row).hasClass("close")){
		// 将该节点下的所有子节点关闭
		$("#goodsClassParamTbody").find("tr[path*="+$(row).attr("id")+"]").addClass("hidden");
		//设置关闭图标样式
		$(_spanCloseOrOpen).removeClass("bagClose").addClass("bagOpen");
		// 为该行标注为关闭状态
		$(row).removeClass("close").addClass("open");
		
		$("#goodsClassParamTbody").find("tr[path*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	// 为该节点下的所有父节点设置图标样式和行状态
			if($(n).attr("id") != "" && $("#goodsClassParamTbody tr[path*="+$(n).attr("id")+"]").length > 0) {
				$(n).removeClass("close").addClass("open");
	    		$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagTreeLine").addClass("bagOpen");
	    	}
		})
	}else{
		$("#goodsClassParamTbody").find("tr[path*="+$(row).attr("id")+"]").removeClass("hidden");
		$(_spanCloseOrOpen).removeClass("bagOpen").addClass("bagClose");
		$(row).removeClass("open").addClass("close");
		
		$("#goodsClassParamTbody").find("tr[path*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	if($(n).attr("id") != "" && $("#goodsClassParamTbody").find("tr[path*="+$(n).attr("id")+"]").length > 0) {
	    		$(n).removeClass("open").addClass("close");
	    		$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagTreeLine").addClass("bagClose");
	    	}
		})
	}
})

// 向上
$("tr a[name=up]").click(function() {
	var curRow = $(this).parent().parent();
	var pid = ($(curRow).attr("pid"))
	var id = ($(curRow).attr("id"))
	if($("#goodsClassParamTbody tr[pid="+pid+"]").index(curRow) == 0 || $(this).find("span").hasClass("siUpGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#goodsClassParamTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#goodsClassParamTbody tr[pid="+pid+"]").get(curPidIndex - 1);
	
	$.getJSON($('#initPath').val()+"/GoodsClassParamTypeController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	
	if($("#goodsClassParamTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#goodsClassParamTbody tr[path*="+id+"]");
		
		// 先将当前行的所有子级放到目标行下面
		$(targetRow).after(sourceRow);
		
		// 然后再将当前行放到目标行下面
		$(targetRow).after(curRow);
		
		// 最后将目标行放到当前行的所有子行下面
		$("#goodsClassParamTbody tr[path*="+id+"]:last").after(targetRow)
	}else {
		$(targetRow).before(curRow);
	}
	
	// 设置上下移动的样式
	initUpAndDownCss();
})

// 向下
$("tr a[name=down]").click(function() {
	var curRow = $(this).parent().parent();
	var pid = ($(curRow).attr("pid"))
	var id = ($(curRow).attr("id"))
	if($("#goodsClassParamTbody tr[pid="+pid+"]").index(curRow) == $("#goodsClassParamTbody tr[pid="+pid+"]").length -1 || $(this).find("span").hasClass("siDownGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#goodsClassParamTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#goodsClassParamTbody tr[pid="+pid+"]").get(curPidIndex + 1);
	
	$.getJSON($('#initPath').val()+"/GoodsClassParamTypeController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	if($("#goodsClassParamTbody tr[pid="+$(targetRow).attr("id")+"]").length > 0) {
		targetRow = $("#goodsClassParamTbody tr[path*="+$(targetRow).attr("id")+"]:last");
	}
	if($("#goodsClassParamTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#goodsClassParamTbody tr[path*="+id+"]");
		
		// 先将当前行的所有子级放到目标行下面
		$(targetRow).after(sourceRow);
		
		// 然后再将当前行放到目标行下面
		$(targetRow).after(curRow);
		
	}else {
		$(targetRow).after(curRow);
	}
	
	// 设置上下移动的样式
	initUpAndDownCss();
})
