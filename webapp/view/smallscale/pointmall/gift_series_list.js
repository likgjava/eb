var giftSeriesList = {}
giftSeriesList.count = 0;

//判断是否有为保存的行
giftSeriesList.newGiftSeriesCount = function() {
	var canAddNewGiftSeries = true;
	if($("#giftSeriesTbody").find("tr input[name=objId][value=]").length >= 1) {
		canAddNewGiftSeries = false;
	}
	return canAddNewGiftSeries
}

//添加顶级礼品系列
giftSeriesList.addTopRow = function(objId,giftSeriesName){
	var row = $('#giftSeriesTfoot').find('tr:first').clone(true).appendTo($('#giftSeriesTbody'));
	
	//设置展开关闭的样式
	if("" != objId){
		$(row).addClass('open');
	}else{
		$(row).addClass('close');
	}
	
	$(row).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
	$(row).attr("id",objId).attr("pid","").attr("path","");
	$(row).find("td input[name=objId]").val(objId);
	$(row).find("td input[name=giftSeriesName]").val(giftSeriesName);
	$(row).find("input:first").css({width:"130px"});
	$(row).find("img").hide();
}

//添加顶级礼品系列点击事件
$("#addGiftSeriesCasBtn").click(function(){
	if(giftSeriesList.newGiftSeriesCount()) {
		giftSeriesList.addTopRow( "", "");
	}else {
		alert("请先保存新增的 礼品系列 ！");
	}
});

//添加子系列
$("a[name=addGiftSeries]").click(function(){
	giftSeriesList.addNewRow( $(this).parent().parent(), 1, "", "", $(this).parent().parent().find("input[name=objId]").val(),$(this).parent().parent().attr("path")+$(this).parent().parent().attr("id"));
})

//为当前的礼品系列添加下级
giftSeriesList.addNewRow = function(  currentRow, index, objId, paramName, parentId, path) {
	var curRowCss = parseInt($(currentRow).find("input[name=giftSeriesName]").css("width").replace("px",""))-19;
	if(giftSeriesList.newGiftSeriesCount()) {
		// 克隆行
		var nrow = $("#giftSeriesTfoot").find("tr").eq(index).clone(true);
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
		$(nrow).find("input[name=giftSeriesName]").val(paramName).css({width:curRowCss+"px"});
		$(nrow).find("a[name=save]>span").removeClass("siSave").addClass("siSaveBtn");
		
		//将新行进行追加
		if( $("#giftSeriesTbody tr[path^="+path+"]").length > 0) {
			$("#giftSeriesTbody tr[path^="+path+"]:last").after(nrow);
		}else {
			$(currentRow).after(nrow);
		}
		
		return nrow;
	}else {
		alert("请先保存新增的参数！");
	}
}

//保存礼品系列
function saveGiftSeries(curElement, giftSeriesType) {
	var curRow = $(curElement).parent().parent();
	var giftSeriesName = $(curRow).find("input[name=giftSeriesName]").val();	
	if(giftSeriesName == "") {		
		$(curRow).find("span[class=eleRequired]").html("请输入礼品系列的名称");
		return false;
	}else if(giftSeriesName.length > 50 ){
		$(curRow).find("span[class=eleRequired]").html("不能超过 50个字");
		return false;
	}
	var giftSeriesObj = {};
	giftSeriesObj.objId = $(curRow).find("input[name=objId]").val();
	giftSeriesObj.name = giftSeriesName;
	var parentObjId = $(curRow).find("input[name=parent.objId]").val();
	if(parentObjId != ""){
		giftSeriesObj.parent = {};
		giftSeriesObj.parent.objId = parentObjId;
	}
	
	//验证系列名称是否存在
	var jsonObj=$.ajax({url:$("#initPath").val()+"/GiftSeriesController.do?method=isUnique",data:{"objId":giftSeriesObj.objId,"parentId":parentObjId,"giftSeriesName":native2ascii(giftSeriesName)}, async: false }).responseText
	jsonObj = eval("("+jsonObj+")");
	if(jsonObj.isUnique !="true"){
		$(curRow).find("span[class=eleRequired]").html("该系列名称已经存在！");
		return false;
	}
		
	//保存	
	$.getJSON($("#initPath").val()+"/"+giftSeriesType+"Controller.do?method=saveGiftSeries",{"giftSeriesJson":JSON.stringify(giftSeriesObj)},function(json){
		if(json.success == "true"){
			$(curRow).find("input[name=objId]").val(json.objId);
			$(curRow).attr("id",json.objId);
			$(curRow).attr("pid",json.parentId);
			$(curRow).attr("path",json.path);
			$(curRow).find("a").removeClass("hidden");
			$(curRow).find("a>span").removeClass("hidden");
			$(curRow).find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
		    //$(curRow).find("a[name^=detailGiftSeries]>span").removeClass("siEdit").addClass("siEditBtn");
			initUpAndDownCss();
		}
		if(json.failure) {
			alert(json.result);
			return;
		}
	});	
}

//初始化数据
function initGiftSeries(currentRow,allGiftSeriesListJsonObj) {
	var objId = $(currentRow).attr("id");
	$(allGiftSeriesListJsonObj).each(function(i, n) {
		if(n.parent && n.parent.objId == objId) {
			// 追加当前行
			var prow = giftSeriesList.addNewRow ( currentRow,1,n.objId, n.name, n.parent.objId, n.path) ;
			
		}
	})
}

$(document).ready(function(){
	
	// 初始化数据
	$.getJSON($('#initPath').val()+"/GiftSeriesController.do?method=getObject",{"order":"sort"},function(json){
		var allGiftSeriesListJsonObj = json.list;
		
		//添加顶级数据
	    $(allGiftSeriesListJsonObj).each(function(i,n){
	    	if(undefined == n.parent) {
	    		giftSeriesList.addTopRow( n.objId, n.name);
	    		
	    	}
	    })
	    
	    //递归添加下级
	    $("#giftSeriesTbody").find("tr").each(function(i,n){
	    	initGiftSeries(n,allGiftSeriesListJsonObj);
	    })
	    
	    //去掉多余样式
	    $("#giftSeriesTbody tr").find("a").removeClass("befSave");
	    $("#giftSeriesTbody tr").find("a").removeClass("hidden");
	    $("#giftSeriesTbody tr").find("a>span").removeClass("hidden");
	    $("#giftSeriesTbody tr").find("a[name=save]>span").removeClass("siSaveBtn").addClass("siSave");
	    //$("#giftSeriesTbody tr").find("a[name^=detailGiftSeries]>span").removeClass("siEditBtn").addClass("siEdit");
	    
	    initUpAndDownCss();
	    
	    $("#giftSeriesTbody tr[path='']").removeClass("hidden");
	    $("#giftSeriesForm").validate();
	    
	    $("input[name=giftSeriesName]").focus(function() {
	    	$(this).parent().find("span.eleRequired").empty();
	    });
	    
	    if($("#giftSeriesTbody").find("tr").length < 1){
	    	giftSeriesList.addTopRow( "", "");
	    }
	});
});

//保存
$('a[name^=save_]').click(function(){
	saveGiftSeries(this,$(this).attr("name").replace("save_",""));
});

//详情
$("tr a[name^=detailGiftSeries]").click(function() {	
	var objId = $(this).parent().parent().attr("id");
	 $.epsDialog({
	        title:'参数详情',
	        url:$("#initPath").val()+"/GiftSeriesController.do?method=toCreateOrUpdate&objId=" + objId,
	        width: '500',
	        height: '300',
	        onClose: function(){}
	 }); 
})

// 删除
$("tr a[name^=removeGiftSeries]").click(function() {	
	var curRow = $(this).parent().parent();
	var objId = $(curRow).attr("id");
	// 如果还没有保存就删除，则只需要将该行删除即可
	if("" == objId) {
		$(curRow).remove();
		
		// 设置上下移动的样式
		initUpAndDownCss();
	}else {		
		if($("#giftSeriesTbody tr[pid*="+objId+"]").length > 0) {
			return false;
		}
		
		if(window.confirm("确定要删除该参数吗？")) {
			$.getJSON($('#initPath').val()+"/GiftSeriesController.do?method="+$(this).attr("name"),{"objId":objId},function(json) {
				if(json.failure) {
					alert(json.result);
					return;
				}				
				if(json.isHasGift=="unsuccess"){
					alert("此系列还包含有礼品,不能删除!");
					return;
				}
				if(json.isHasGift=="success"){
					$(curRow).remove();
				}				
				
				// 设置上下移动的样式
				initUpAndDownCss();
			})
			
		}
		
	}
})

//设置上下移动和删除的样式
function initUpAndDownCss() {
	$("#giftSeriesTbody tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#giftSeriesTbody tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	$("#giftSeriesTbody tr a[name^=removeGiftSeries]>span").removeClass("siExitGray").addClass("siExit");
	
    $("#giftSeriesTbody tr").each(function() {
    	$("#giftSeriesTbody tr[pid*="+$(this).attr("id")+"]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    	$("#giftSeriesTbody tr[pid*="+$(this).attr("id")+"][pid="+$(this).attr("id")+"]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
    	
    	// 删除样式设置
    	if($("#giftSeriesTbody tr[pid*="+$(this).attr("id")+"]").length > 0) {
    		$(this).find("a[name^=removeGiftSeries]>span").removeClass("siExit").addClass("siExitGray");
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
    $("#giftSeriesTbody tr[path=]:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
    $("#giftSeriesTbody tr[path=]:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");    
}

//展开关闭
$("[name=closeOrOpen]").click(function() {
	var row = $(this).parent("td").parent("tr");
	var _spanCloseOrOpen = $(this);
	
	//如果是子节点，返回
	if ($(_spanCloseOrOpen).hasClass("bagTreeLine")) {
		return false;
	}
	if($(row).hasClass("close")){
		// 将该节点下的所有子节点关闭
		$("#giftSeriesTbody").find("tr[pid*="+$(row).attr("id")+"]").addClass("hidden");
		//设置关闭图标样式
		$(_spanCloseOrOpen).removeClass("bagClose").addClass("bagOpen");
		// 为该行标注为关闭状态
		$(row).removeClass("close").addClass("open");
		
		$("#giftSeriesTbody").find("tr[pid*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	// 为该节点下的所有父节点设置图标样式和行状态
			if($(n).attr("id") != "" && $("#giftSeriesTbody tr[pid*="+$(n).attr("id")+"]").length > 0) {
				$(n).removeClass("close").addClass("open");
	    		$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagTreeLine").addClass("bagOpen");
	    	}
		})
	}else{
		$("#giftSeriesTbody").find("tr[pid*="+$(row).attr("id")+"]").removeClass("hidden");
		$(_spanCloseOrOpen).removeClass("bagOpen").addClass("bagClose");
		$(row).removeClass("open").addClass("close");
		
		$("#giftSeriesTbody").find("tr[pid*="+$(row).attr("id")+"]").each(function(i,n) {
			$(n).find("td").find("span[name=closeOrOpen]").removeClass("bagClose").removeClass("bagOpen").addClass("bagTreeLine");
	    	if($(n).attr("id") != "" && $("#giftSeriesTbody").find("tr[pid*="+$(n).attr("id")+"]").length > 0) {
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
	if($("#giftSeriesTbody tr[pid="+pid+"]").index(curRow) == 0 || $(this).find("span").hasClass("siUpGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#giftSeriesTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#giftSeriesTbody tr[pid="+pid+"]").get(curPidIndex - 1);
	
	$.getJSON($('#initPath').val()+"/GiftSeriesController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	
	if($("#giftSeriesTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#giftSeriesTbody tr[pid*="+id+"]");
		
		// 先将当前行的所有子级放到目标行下面
		$(targetRow).after(sourceRow);
		
		// 然后再将当前行放到目标行下面
		$(targetRow).after(curRow);
		
		// 最后将目标行放到当前行的所有子行下面
		$("#giftSeriesTbody tr[pid*="+id+"]:last").after(targetRow)
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
	if($("#giftSeriesTbody tr[pid="+pid+"]").index(curRow) == $("#giftSeriesTbody tr[pid="+pid+"]").length -1 || $(this).find("span").hasClass("siDownGray")) {
		return false;
	}
	var sourceRow = curRow;
	
	// 得到当前要移动的同父节点的序号
	var curPidIndex = $("#giftSeriesTbody tr[pid="+pid+"]").index(curRow) ;
	
	var targetRow = $("#giftSeriesTbody tr[pid="+pid+"]").get(curPidIndex + 1);
	
	$.getJSON($('#initPath').val()+"/GiftSeriesController.do?method=updateSort",{"sourceObjId":id,"targetObjId":$(targetRow).attr("id")},function(json){})
	
	if($("#giftSeriesTbody tr[pid="+$(targetRow).attr("id")+"]").length > 0) {
		targetRow = $("#giftSeriesTbody tr[pid*="+$(targetRow).attr("id")+"]:last");
	}
	if($("#giftSeriesTbody tr[pid="+id+"]").length > 0) {
		sourceRow = $("#giftSeriesTbody tr[pid*="+id+"]");
		
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