var qualificationList={};
qualificationList.count = 0;

//判断是否有为保存的行
qualificationList.newQualityCount = function() {
	var canAddNewQuality = true;
	if($("#qualityTbody").find("tr input[name=objId][value=]").length >= 1) {
		canAddNewQuality = false;
	}
	return canAddNewQuality
}

// 添加顶级资质
qualificationList.addTopRow = function(objId, qualityName){
	var row = $("#qualityTfoot").find("tr:first").clone(true).appendTo("#qualityTbody");
	// 设置关闭展开的样式
	if("" != objId){
		$(row).addClass("open");
	}else{
		$(row).addClass("close");
	}
	$(row).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
	$(row).attr("id",objId).attr("pid","").attr("path","");
	$(row).find("td input[name=objId]").val(objId);
	$(row).find("td input[name=qualityName]").val(qualityName);
	$(row).find("input:first").css({width:"130px"});
	$(row).find("img").hide();
}

// 为当前的资质添加下级
qualificationList.addNewRow = function( currentRow, index, objId, qualityName, parentId, path) {
	var curRowCss = parseInt($(currentRow).find("input[name=qualityName]").css("width").replace("px",""))-19;
	
	
	if(qualificationList.newQualityCount()) {
		
		// 克隆行
		var nrow = $("#qualityTfoot").find("tr").eq(index).clone(true);
		// 设置关闭展开的样式
		if("" != objId){
			$(nrow).addClass("hidden");
			$(nrow).addClass("open");
		}else{
			$(nrow).addClass("close");
			$(nrow).addClass("close");
			$(currentRow).removeClass("open").addClass("close");
			$(nrow).find("td").find("span[name=closeOrOpen]").addClass("bagTreeLine");
		}
		$(nrow).attr("id",objId).attr("pid",parentId).attr("path",path);
		$(nrow).find("input[name=objId]").val(objId);
		$(nrow).find("input[name=parent.objId]").val(parentId);
		$(nrow).find("input[name=qualityName]").val(qualityName).css({width:curRowCss+"px"});
		$(nrow).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
		
		//将新行进行追加
		if( $("#qualityTbody tr[path^="+path+"]").length > 0) {
			$("#qualityTbody tr[path^="+path+"]:last").after(nrow);
		}else {
			$(currentRow).after(nrow);
		}
		
		return nrow;
	}else {
		alert("请先保存新增的"+$("#qualityTbody").find("tr").find("input[name=objId][value=]").parent().parent().find("td:eq(0)").html()+"！");
	}
}

// 保存资质
function saveQualification(curElement, qualityType) {
	var curRow = $(curElement).parent().parent();
	var qualityName = $(curRow).find("input[name=qualityName]").val();
	if(qualityName == "") {
		
		$(curRow).find("span[class=eleRequired]").html("请输入"+$(curRow).find("td:eq(0)").html()+"名称");
		return false;
	}else if(qualityName.length > 50 ){
		$(curRow).find("span[class=eleRequired]").html("不能超过 50个字");
		return false;
	}
	var qualificationObj = {};
	qualificationObj.objId = $(curRow).find("input[name=objId]").val();
	qualificationObj.qualityName = qualityName;
	var parentObjId = $(curRow).find("input[name=parent.objId]").val();
	if(parentObjId != ""){
		qualificationObj.parent = {};
		qualificationObj.parent.objId = parentObjId;
	}
	var jsonObj=$.ajax({url:$("#initPath").val()+"/QualificationController.do?method=isUnique",data:{"objId":qualificationObj.objId,"parentId":parentObjId,"qualityName":native2ascii(qualityName)}, async: false }).responseText
	jsonObj = eval("("+jsonObj+")");
	if(jsonObj.isUnique !="true"){
		$(curRow).find("span[class=eleRequired]").html("该"+$(curRow).find("td:eq(0)").html()+"名称已经存在！");
		return false;
	}
	$.getJSON($("#initPath").val()+"/Qualification"+qualityType+"Controller.do?method=saveQualification"+qualityType,{"qualificationJson":JSON.stringify(qualificationObj)},function(json){
		if(json.success == "true"){
			$(curRow).find("input[name=objId]").val(json.objId);
			$(curRow).attr("id",json.objId);
			$(curRow).attr("pid",json.parentId);
			$(curRow).attr("path",json.path);
			$(curRow).find("a").removeClass("hidden");
			$(curRow).find("a>span").removeClass("hidden");
			$(curRow).find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
		    $(curRow).find("a[name^=detilQualification]>span").removeClass("siEdit").addClass("siEditBtn");
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
			var prow = qualificationList.addNewRow ( currentRow, n.type.substring(1), n.objId, n.qualityName, n.parent.objId, n.path) ;
			//递归追加下级
			if(n.isHasSub == "false") {
				initQualification(prow,allQuqlityListJsonObj);
			}
		}
	})
}

// 设置上下移动和删除的样式
function initUpAndDownCss() {
	$("#qualityTbody tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#qualityTbody tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	$("#qualityTbody tr a[name^=removeQualification]>span").removeClass("siExitGray").addClass("siExit");
	
    $("#qualityTbody tr").each(function() {
    	$("#qualityTbody tr[path*="+$(this).attr("id")+"]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    	$("#qualityTbody tr[path*="+$(this).attr("id")+"][pid="+$(this).attr("id")+"]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
    	
    	// 删除样式设置
    	if($("#qualityTbody tr[path*="+$(this).attr("id")+"]").length > 0) {
    		$(this).find("a[name^=removeQualification]>span").removeClass("siExit").addClass("siExitGray");
    		// 设置展开关闭样式
    		var _spanCloseOrOpen = $(this).find("td").find("span[name=closeOrOpen]");
    		if($(this).hasClass("close")){
    			$(_spanCloseOrOpen).removeClass("bagTreeLine").removeClass("bagOpen").addClass("bagClose");
    		}else{
    			$(_spanCloseOrOpen).removeClass("bagTreeLine").removeClass("bagClose").addClass("bagOpen");
    		}
    	}else{
    		if(undefined != $(this).attr("pid") && $(this).attr("pid") != ""){
    			$(this).find("td").find("span[name=closeOrOpen]").addClass("bagTreeLine");
    		}
    		$(this).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen");
    	}
    })
    
    // 顶级节点 
    $("#qualityTbody tr[path=]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    $("#qualityTbody tr[path=]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
}

$(document).ready(function() {
	
	// 保存
	$('a[name^=save_]').click(function(){
		saveQualification(this,$(this).attr("name").replace("save_",""));
	});
	
	// 初始化数据
	$.getJSON($('#initPath').val()+"/QualificationController.do?method=getObject",{"order":"sort"},function(json){
		var allQuqlityListJsonObj = json.list;
		
		//添加顶级数据
	    $(allQuqlityListJsonObj).each(function(i,n){
	    	if(undefined == n.parent) {
	    		qualificationList.addTopRow( n.objId, n.qualityName);
	    		
	    	}
	    })
	    
	    //递归添加下级
	    $("#qualityTbody").find("tr").each(function(i,n){
	    	initQualification(n,allQuqlityListJsonObj);
	    })
	    
	    //去掉多余样式
	    $("#qualityTbody tr").find("a").removeClass("befSave");
	    $("#qualityTbody tr").find("a").removeClass("hidden");
	    $("#qualityTbody tr").find("a>span").removeClass("hidden");
	    $("#qualityTbody tr").find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
	    $("#qualityTbody tr").find("a[name^=detilQualification]>span").removeClass("siEditBtn").addClass("siEdit");
	    
	    initUpAndDownCss();
	    $("#qualityTbody tr[path='']").removeClass("hidden");
	    $("#qualityForm").validate();
	    $("input[name=qualityName]").focus(function() {
	    	$(this).parent().find("span.eleRequired").empty();
	    });
	    
	    if($("#qualityTbody").find("tr").length < 1){
	    	qualificationList.addTopRow("", "");
	    }
	});
});

//添加子分类
$("a[name=addSubQualityClass]").click(function(){
	qualificationList.addNewRow( $(this).parent().parent(), 0, "", "", $(this).parent().parent().find("input[name=objId]").val(),$(this).parent().parent().attr("path")+$(this).parent().parent().attr("id"));
})

// 添加资质定义
$("a[name=addSubQualityDefine]").click(function(){
	qualificationList.addNewRow( $(this).parent().parent(), 1, "", "", $(this).parent().parent().find("input[name=objId]").val(),$(this).parent().parent().attr("path")+$(this).parent().parent().attr("id"));
})

// 添加资质参数
$("a[name=addSubQualityParam]").click(function(){
	qualificationList.addNewRow( $(this).parent().parent(), 2, "", "", $(this).parent().parent().find("input[name=objId]").val(),$(this).parent().parent().attr("path")+$(this).parent().parent().attr("id"));
})


// 添加顶级资质分类
$("#addQulificationCasBtn").click(function(){
	if(qualificationList.newQualityCount()) {
		qualificationList.addTopRow("", "");
	}else {
		alert("请先保存新增的"+$("#qualityTbody").find("tr").find("input[name=objId][value=]").parent().parent().find("td:eq(0)").html()+"！");
	}
});

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
		$("#qualityTbody").find("tr[path*="+$(row).attr("id")+"]").addClass("hidden");
		//设置关闭图标样式
		$(_spanCloseOrOpen).removeClass("bagClose").addClass("bagOpen");
		// 为该行标注为关闭状态
		$(row).removeClass("close").addClass("open");
		
		$("#qualityTbody").find("tr[path*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	// 为该节点下的所有父节点设置图标样式和行状态
			if($(n).attr("id") != "" && $("#qualityTbody tr[path*="+$(n).attr("id")+"]").length > 0) {
	    		$(n).removeClass("close").addClass("open");
	    		$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagTreeLine").addClass("bagOpen");
	    	}
		})
	}else{
		$("#qualityTbody").find("tr[path*="+$(row).attr("id")+"]").removeClass("hidden");
		$(_spanCloseOrOpen).removeClass("bagOpen").addClass("bagClose");
		$(row).removeClass("open").addClass("close");
		
		$("#qualityTbody").find("tr[path*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	if($(n).attr("id") != "" && $("#qualityTbody").find("tr[path*="+$(n).attr("id")+"]").length > 0) {
	    		$(n).removeClass("open").addClass("close");
	    		$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagTreeLine").addClass("bagClose");
	    	}
		})
	}
})
	
// 详情
	$("tr a[name^=detilQualification]").click(function() {
	var actionName = $(this).attr("name").replace("detil","");
	var objId = $(this).parent().parent().attr("id");
	var height="200";
	if(actionName == "QualificationParam") {
		
		height = "300";
	}
	 $.epsDialog({
	        title:'资质详情',
	        url:$("#initPath").val()+"/"+actionName+"Controller.do?method=toCreateOrUpdate&objId=" + objId,
	        height: height,
	        onClose: function(){}
	 }); 
})

// 删除
$("tr a[name^=removeQualification]").click(function() {
	var actionName = $(this).attr("name").replace("remove","");
	var curRow = $(this).parent().parent();
	var objId = $(curRow).attr("id");
	// 如果还没有保存就删除，则只需要将该行删除即可
	if("" == objId) {
		$(curRow).remove();
		
		// 设置上下移动的样式
		initUpAndDownCss();
	}else {
		
		if($("#qualityTbody tr[path*="+objId+"]").length > 0) {
			//alert("该资质下还有子级资质，不充许删除！");
			return false;
		}
		if(window.confirm("确定要删除该资质吗？")) {
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

// 向上
$("tr a[name=up]").click(function() {
	var curRow = $(this).parent().parent();
	var pid = ($(curRow).attr("pid"))
	var id = ($(curRow).attr("id"))
	if($("#qualityTbody tr[pid="+pid+"]").index(curRow) == 0 || $(this).find("span").hasClass("siUpGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#qualityTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#qualityTbody tr[pid="+pid+"]").get(curPidIndex - 1);
	
	$.getJSON($('#initPath').val()+"/QualificationController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	
	if($("#qualityTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#qualityTbody tr[path*="+id+"]");
		
		// 先将当前行的所有子级放到目标行下面
		$(targetRow).after(sourceRow);
		
		// 然后再将当前行放到目标行下面
		$(targetRow).after(curRow);
		
		// 最后将目标行放到当前行的所有子行下面
		$("#qualityTbody tr[path*="+id+"]:last").after(targetRow)
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
	if($("#qualityTbody tr[pid="+pid+"]").index(curRow) == $("#qualityTbody tr[pid="+pid+"]").length -1 || $(this).find("span").hasClass("siDownGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#qualityTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#qualityTbody tr[pid="+pid+"]").get(curPidIndex + 1);
	
	$.getJSON($('#initPath').val()+"/QualificationController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	if($("#qualityTbody tr[pid="+$(targetRow).attr("id")+"]").length > 0) {
		targetRow = $("#qualityTbody tr[path*="+$(targetRow).attr("id")+"]:last");
	}
	if($("#qualityTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#qualityTbody tr[path*="+id+"]");
		
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
