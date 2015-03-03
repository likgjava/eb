
var choose_category = {};

//品目列表
choose_category.getFirstCategorys = function(){
	$.getJSON($("#initPath").val()+"/PurCategoryController.do?method=getCategorysByLevel",{"level":"second"},function(json){
		if(json.success){
			$("#level1").empty();
			$.each(json.result,function(index,obj){
				$("#level1").append('<div isleaf="false" class="bd_c_c_select_li">'+
				'<a href="javascript:void(0);" onclick="choose_category.getSecondCategorys(\''+obj.objId+'\');return false;" class="bd_c_c_select_li_p" id="'+obj.objId+'" isleaf="false">'+obj.categoryName+'</a></div>');
			})
		}
	})
}

//二级
choose_category.getSecondCategorys = function(parentId,e){
	$.getJSON($("#initPath").val()+"/PurCategoryController.do?method=getCategorysByLevel",{"parentId":parentId},function(json){
		if(json.success){
			$("#level2").empty();
			$("#level3").empty();
			choose_category.changeThisCss(e);
			$.each(json.result,function(index,obj){
				$("#level2").append('<div isleaf="'+obj.isLeaf+'" class="bd_c_c_select_li">'+
				'<a href="javascript:void(0);" onclick="choose_category.getThirdCategorys(\''+obj.objId+'\',this);return false;" class="'+(obj.isLeaf=="true"?"bd_c_c_select_li":"bd_c_c_select_li_p")+'" id="'+obj.objId+'" isleaf="'+obj.isLeaf+'">'+obj.categoryName+'</a></div>'
				);
			})
		}
	})
}

//三级
choose_category.getThirdCategorys = function(parentId,e){
	$.getJSON($("#initPath").val()+"/PurCategoryController.do?method=getCategorysByLevel",{"parentId":parentId},function(json){
		if(json.success){
			$("#level3").empty();
			choose_category.changeThisCss(e);
			$.each(json.result,function(index,obj){
				$("#level3").append('<div isleaf="true" class="bd_c_c_select_li">'+
				'<a href="javascript:void(0);" onclick="choose_category.changeThisCss(this);return false;" class="bd_c_c_select_li" id="'+obj.objId+'" isleaf="true">'+obj.categoryName+'</a></div>');
			})
		}
	})
}

//改变样式
choose_category.changeThisCss = function(e){
	$(e).parent().parent().find(".bd_c_c_select_li_c_p").removeClass().addClass("bd_c_c_select_li_p");
	$(e).parent().parent().find(".bd_c_c_select_li_c").removeClass().addClass("bd_c_c_select_li");
	if($(e).hasClass("bd_c_c_select_li_p")){
		$(e).removeClass().addClass("bd_c_c_select_li_c_p");
	}
	if($(e).hasClass("bd_c_c_select_li")){
		$(e).removeClass().addClass("bd_c_c_select_li_c");
	}
	choose_category.getCategoryNames();
	
	choose_category.getCategoryValuesSelfSelect();
}

//回填值自选类目值
choose_category.getCategoryValuesSelfSelect = function(){
	var categoryids = '';
	if( $("#level1").find("a.bd_c_c_select_li_c_p").html() ){
		categoryids += $("#level1").find("a.bd_c_c_select_li_c_p").attr("id");
	}else if( $("#level1").find("a.bd_c_c_select_li_c").html() ){
		categoryids += $("#level1").find("a.bd_c_c_select_li_c").attr("id");
	}
	if( $("#level2").find("a.bd_c_c_select_li_c_p").html() ){
		categoryids += (categoryids==''?'':',') + $("#level2").find("a.bd_c_c_select_li_c_p").attr("id");
	}else if( $("#level2").find("a.bd_c_c_select_li_c").html() ){
		categoryids += (categoryids==''?'':',') + $("#level2").find("a.bd_c_c_select_li_c").attr("id");
	}
	if( $("#level3").find("a.bd_c_c_select_li_c_p").html() ){
		categoryids += (categoryids==''?'':',') + $("#level3").find("a.bd_c_c_select_li_c_p").attr("id");
	}else if( $("#level3").find("a.bd_c_c_select_li_c").html() ){
		categoryids += (categoryids==''?'':',') + $("#level3").find("a.bd_c_c_select_li_c").attr("id");
	}
	$("#categoryvalues").val(categoryids);
}

//回填品目名称
choose_category.getCategoryNames = function(){
	var categoryNames = "";
	var level1Name = $("#level1").find("a[class=bd_c_c_select_li_c_p]").html();
	if(!level1Name) {
		level1Name = $("#level1").find("a[class=bd_c_c_select_li_c]").html();
	}
	var level2Name = $("#level2").find("a[class=bd_c_c_select_li_c_p]").html();
	if(!level2Name) {
		level2Name = $("#level2").find("a[class=bd_c_c_select_li_c]").html();
	}
	var level3Name = $("#level3").find("a[class=bd_c_c_select_li_c_p]").html();
	if(!level3Name) {
		level3Name = $("#level3").find("a[class=bd_c_c_select_li_c]").html();
	}
	if(level1Name!=null){
		categoryNames += level1Name;
	}
	if(level2Name!=null){
		categoryNames += ( categoryNames!=""?("&gt;"+level2Name):level2Name ) ;
	}
	if(level3Name!=null){
		categoryNames += ( categoryNames!=""?("&gt;"+level3Name):level3Name ) ;
	}
	$("#categoryNames").html(categoryNames);
}

//关闭或展开类目
choose_category.closeOrOpen = function(e){
	$(e).hasClass("bd_c_c_tab_h")?$(e).removeClass().addClass("bd_c_c_tab"):$(e).removeClass().addClass("bd_c_c_tab_h");
	$(e).hasClass("bd_c_c_tab_h")?$("#selectCategoryDiv").show():$("#selectCategoryDiv").hide();
	$("#searchresultDiv").hide();
}

//搜索框的焦点事件
choose_category.blurSearchText = function(e){
	$(e).val( $(e).val()==""?"请输入产品名称、类目名称等关键词":$(e).val() );
}

//搜索框的点击事件
choose_category.clickSearchText = function(e){
	$(e).val( $(e).val()=="请输入产品名称、类目名称等关键词"?"":$(e).val() );
}

//搜索事件
choose_category.clickSearchBtn = function(){
	if($("#searchText").val()!="请输入产品名称、类目名称等关键词"){
		$("#selectCategoryDiv").hide();$("#searchresultDiv").show();
		
		$.getJSON($("#initPath").val()+"/PurCategoryController.do?method=getCategorysByKeyWords",{"keyWords":native2ascii(strIgnore($("#searchText").val()))},function(json){
			if(json.success && json.purCategoryArray && json.purCategoryArray.length>0){
				$("#searchListDiv").show();$("#resultnoresult").hide();$("#resultothers").show();$("#searchListtips").show();
				$("#searchListDiv").empty();
				$.each(json.purCategoryArray,function(index,obj){
					var categoryhtml = '';
					var objId = '';
					if(obj && obj.parent&& obj.parent.parent&&obj.parent.parent.parent){
						categoryhtml += obj.parent.parent.categoryName+'&gt;';
						objId = obj.parent.parent.objId;
					}
					if(obj && obj.parent &&  obj.parent.parent){
						categoryhtml += obj.parent.categoryName+'&gt;'
						objId += (objId==''?'':',') + obj.parent.objId;
					}
					if(obj && obj.parent){
						categoryhtml += obj.categoryName						
						objId += (objId==''?'':',') + obj.objId;
					}
					$("#searchListDiv").append('<div class="bd_c_c_r_list"><div class="bd_c_c_r_radio">'+
						'<input type="radio" name="searchListRadio" value="'+objId+'" '+(index==0?'checked=checked':'')+' onclick="choose_category.searchListCheck(\''+categoryhtml+'\',\''+objId+'\');" ></div>'+
						'<div class="bd_c_c_r_name"><label>'+categoryhtml+'</label></div></div>');
					if(index==0){
						choose_category.searchListCheck(categoryhtml,objId);//默认选择结果中的第一个
					}
				})
			}else{
				$("#searchListDiv").hide();$("#resultnoresult").show();$("#resultothers").hide();$("#searchListtips").hide();
			}
		})
		$("#closecategory").removeClass().addClass("bd_c_c_tab");
	}else{
		$("#selectCategoryDiv").show();$("#searchresultDiv").hide();
	}
}

//搜索结果点击
choose_category.searchListCheck = function(categorynames,objId){
	$("#categoryNames").html(categorynames);
	$("#categoryvalues").val(objId);
}

//返回自选类目
choose_category.backtoChooseSefl = function(backValue){
	var checkedValue = backValue==null?$("#searchListDiv").find("input[type=radio]:checked").val() : backValue;
	
	if(checkedValue){
		$.getJSON($("#initPath").val()+"/PurCategoryController.do?method=getCategorysByParentId",{"checkedValue":checkedValue},function(json){
			$("#selectCategoryDiv").show();$("#searchresultDiv").hide();$("#closecategory").removeClass().addClass("bd_c_c_tab_h");
			
//第一级一定已经加载此代码多余if(json.firstCategoryArray){
//				$("#level1").empty();
//				$.each(json.firstCategoryArray,function(index,obj){
//					$("#level1").append('<div isleaf="false" class="bd_c_c_select_li">'+
//					'<a href="javascript:void(0);" onclick="choose_category.getSecondCategorys(\''+obj.objId+'\',this);return false;" class="'+(obj.objId == checkedValue.split(",")[0]? (obj.isLeaf=="true"?"bd_c_c_select_li_c":"bd_c_c_select_li_c_p"):(obj.isLeaf=="true"?"bd_c_c_select_li":"bd_c_c_select_li_p"))+'" id="'+obj.objId+'" isleaf="false">'+obj.categoryName+'</a></div>');
//				})
//			}
			
			//第一级一定已经加载
			$("#level1").find("a.bd_c_c_select_li_c_p").removeClass().addClass("bd_c_c_select_li_p");
			$("#level1").find("a.bd_c_c_select_li_c").removeClass().addClass("bd_c_c_select_li");
			var object = $("#level1").find("a[id="+checkedValue.split(",")[0]+"]");
			( object && object.hasClass("bd_c_c_select_li_p"))?object.removeClass().addClass("bd_c_c_select_li_c_p"):object.removeClass().addClass("bd_c_c_select_li_c");
			
			//第二级
			if(json.secondCategoryArray){
				$("#level2").empty();
				$.each(json.secondCategoryArray,function(index,obj){
					$("#level2").append('<div isleaf="'+obj.isLeaf+'" class="bd_c_c_select_li">'+
					'<a href="javascript:void(0);" onclick="choose_category.getThirdCategorys(\''+obj.objId+'\',this);return false;" class="'+(obj.objId==checkedValue.split(",")[1]&&checkedValue.split(",").length>1? (obj.isLeaf=="true"?"bd_c_c_select_li_c":"bd_c_c_select_li_c_p"):(obj.isLeaf=="true"?"bd_c_c_select_li":"bd_c_c_select_li_p"))+'" id="'+obj.objId+'" isleaf="'+obj.isLeaf+'">'+obj.categoryName+'</a></div>'
					);
				})
			}
			
			//第三级
			if(json.thirdCategoryArray){
				$("#level3").empty();
				$.each(json.thirdCategoryArray,function(index,obj){
					$("#level3").append('<div isleaf="true" class="bd_c_c_select_li">'+
					'<a href="javascript:void(0);" onclick="choose_category.changeThisCss(this);return false;" class="'+(obj.objId == checkedValue.split(",")[2]&&checkedValue.split(",").length>2? (obj.isLeaf=="true"?"bd_c_c_select_li_c":"bd_c_c_select_li_c_p"):(obj.isLeaf=="true"?"bd_c_c_select_li":"bd_c_c_select_li_p"))+'" id="'+obj.objId+'" isleaf="true">'+obj.categoryName+'</a></div>');
				})
			}
		})
	}
}

//跳到下一步
choose_category.nextStepBtn = function(){
	var categoryNames = $("#categoryNames").html().replace(/&gt;/g,'>');
	var categoryvalues = $("#categoryvalues").val();
	
	if(categoryvalues){
		document.location.href= $("#initPath").val() + "/RequirementController.do?method=toRequirementForm&categoryvalues="+categoryvalues+"&categoryNames="+native2ascii(strIgnore(categoryNames));
	}else{
		alert("您没有选择任何品目！请选择相应的品目！");
	}
}

$(document).ready(function(){
	//返回时初始化
	if($("#categoryvalues").val()){
		choose_category.backtoChooseSefl($("#categoryvalues").val());
	}
})