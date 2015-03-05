var districtTreeForm={};
districtTreeForm.tree;
var isUniqueName = true;

// 点击节点事件
districtTreeForm.nodeClick=function(id){
	removeEleWarning();
	// 切换表单
	$("#districtFormPage").hide();
	$("#districtDetailPage").show();
	$("#districtForm")[0].reset();
	$("#newDistrict").attr("disabled",false);
	if("" == id || "-1" == id) {
		$("#modifyDistrict").attr("disabled",true);
		$("#deleteDistrict").attr("disabled",true);
		json2Object("districtDetail","");
		$("input[name=level]").val(0);
		$("input[name=objId]").val("");
		$("#parentId").val("");
	}else {
		$("#modifyDistrict").attr("disabled",false);
		$("#deleteDistrict").attr("disabled",false);
		
		$.getJSON($('#initPath').val()+'/DistrictController.do?method=createOrUpdate',{objId:id},function(json){
			json2Object("districtDetail",json.district);
			$("#districtId").val(id)
			jsonObjectToForm('districtForm', json.district);
			if(undefined != json.district.parent) {
				$("#parentId").val(json.district.parent.objId)
			}
			if(json.district.isLeaf == "true") {
				$("#deleteDistrict").attr("disabled",false);
			}else {
				$("#deleteDistrict").attr("disabled",true);
			}
		});
	}
}

// 初始化树
districtTreeForm.initGoodsClassTree=function(){
    districtTreeForm.tree=new dhtmlXTreeObject('districtTreeForm',"100%","100%",0);
    districtTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    districtTreeForm.tree.enableDragAndDrop(false);
    districtTreeForm.tree.enableThreeStateCheckboxes(true);
    districtTreeForm.tree.setOnClickHandler(districtTreeForm.nodeClick);
    districtTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/DistrictController.do?method=getTree&action=listById&order=sort');//点 + 号触发展开事件
    districtTreeForm.tree.loadXML($('#initPath').val()+'/DistrictController.do?method=getTree&action=listTop&isOpen=0&order=sort',function(){
    });
}

// 新增品目
districtTreeForm.newDistrict=function(){
	
	// 切换表单
	$("#districtFormPage").show();
	$("#districtDetailPage").hide();
	
	// 初始化id
	var parentId = $("input[id=districtId]").val();
	var level = $("input[name=level]").val();
	$("#districtForm")[0].reset();
	$("input[name=objId]").val("");
	$("input[name=parent.objId]").val(parentId);
	$("input[name=level]").val(parseInt(level)+1);
}

//修改事件
districtTreeForm.modifyDistrict = function(){
	
	if("-1" == $("input[id=districtId]").val() || "" == $("input[id=districtId]").val()){
		alert("请选择一个节点进行修改!");
		return false;
	}
	
	//切换表单
	$("#districtFormPage").show();
	$("#districtDetailPage").hide();
}

/*保存区域*/
districtTreeForm.save=function(id){
	if(!$('#districtForm').valid()){alert('请正确填写表单!');return;}
    var district = formToJsonObject('districtForm');
    
    
    var josnObj=formToJsonObject($("#districtForm")[0]);
    josnObj['parent.objId'] = $('input[name=parent.objId]').val();
    josnObj.json=JSON.stringify(josnObj);
    $.ajax({
    	url:"DistrictController.do?method=saveDistrict",
    	type:"POST",
    	data:josnObj,
    	dataType:'json',
    	success:function(json){
	    	if(json.success){
	    		alert("保存成功");
	    	}
	    	var pid=$("input[id=parentId]").val();
	    	var id=$("#districtId").val();
	    	 // 修改
	        if($("input[id=districtId]").val() == json.district.objId){
	        	treeUtil.refreshTree(districtTreeForm.tree,"edit",json.district.objId,$("input[id=name]").val());
	        }else{// 新增
	        	var pid=$("input[id=parentId]").val();
	        	var oper = "new";
	        	if(pid && pid != ""){ oper = "child"};
	        	treeUtil.refreshTree(districtTreeForm.tree,oper,json.district.objId,$("input[id=name]").val());

	        }
	        districtTreeForm.nodeClick(json.district.objId);
	        
	        
	    }
    })
    
}

/*删除区域*/
districtTreeForm.removeDistrict=function(){
	if($("input[id=districtId]").val()==null||$("input[id=districtId]").val()==""){
		alert("请选择一个分类，再进行删除操作!");
		return false;
	}
    if(confirm("是否确认删除【"+$('span[id=name]').html()+"】?")){
        $.getJSON($('#initPath').val()+'/DistrictController.do?method=removeDistrict',{objId:$("input[id=districtId]").val()},function(json){
        	if(json.failure){
        		alert("【"+$('span[id=name]').html()+"】已经被关联，不充许删除！");
        		return false;
        	}
        	if(json.result)alert(json.result);
            treeUtil.refreshTree(districtTreeForm.tree,'delete','');
            
            // 点击父节点
            districtTreeForm.nodeClick($("input[name=parent.objId]").val());
        });
    }
}

/*返回*/
districtTreeForm.back=function(){
	if("" == $("input[id=districtId]").val()|| "-1" == $("input[id=districtId]").val()){
		districtTreeForm.nodeClick($("input[id=parentId]").val());
	}else{
		districtTreeForm.nodeClick($("input[id=districtId]").val());
	}
}


/*****业务操作处理 结束*****/

$(document).ready(function(){
	districtTreeForm.initGoodsClassTree();//加载树
	
	$.validator.addMethod("codeUnique",function(value,element,param){return uniqueHandler("District",param,value,$("#districtId").val());},'该区域代码已存在');

	//表单验证
	$("#districtForm").validate({
		rules:{
		code:{codeUnique:"code"}
		}
	});	
    
    //返回
    $("#returnBtn").click(function(){
    	removeEleWarning();
    	districtTreeForm.back();
    });
    
    //保存
    $("#saveDistrictBtn").click(function(){
    	if(!isUniqueName){
    		alert("该地区名称已存在！");
    		return false;
    	}
        districtTreeForm.save();
    });
    
    //跳转到新增页面
    $('#newDistrict').click(function(){
    	removeEleWarning();
        districtTreeForm.newDistrict();
    });
    
    //修改
    $('#modifyDistrict').click(function(){
    	removeEleWarning();
        districtTreeForm.modifyDistrict();
    });
    
    //删除
    $("#deleteDistrict").click(function(){
        districtTreeForm.removeDistrict();
    });
    
    //上移
    $('#up').click(function(){
        treeUtil.sortUp(districtTreeForm.tree,"up","com.gpcsoft.srplatform.baseData.domain.District")
    });

    //下移
    $('#down').click(function(){
    	treeUtil.sortDown(districtTreeForm.tree,"down","com.gpcsoft.srplatform.baseData.domain.District")
    });
   
    // 验证父区域下的子区域是否唯一 
    $('#districtForm').find('input[name=name]').blur(function(){
		if($(this).val() != ""){
			$.getJSON($('#initPath').val()+'/DistrictController.do?method=isUnique', {"objId":$('#districtId').val(),"parentId":$('#parentId').val(),"name":native2ascii($('#districtForm').find('input[name=name]').val())}, function(json){
	    		if(json.isUnique != 'true'){
	    			$(this).addClass("eleWarning");
	    			$('#districtForm').find('input[name=name]').next('span').html("该地区名称已存在！").removeClass('eleRight').addClass('eleWarning');
	    			isUniqueName = false;
	        	}else{
	        		$('#districtForm').find('input[name=name]').next('span').html('');
	        		$(this).removeClass("eleWarning");
	        		isUniqueName = true;
		        }
			});
		}
	});
    
    // 验证code是否唯一 
    $('#districtForm').find('input[name=code]').blur(function(){
		if($(this).val() != ""){
			$.getJSON($('#initPath').val()+'/DistrictController.do?method=isUnique', {"objId":$('#districtId').val(),"code":native2ascii($('#districtForm').find('input[name=code]').val())}, function(json){
	    		if(json.isUnique != 'true'){
	    			$(this).addClass("eleWarning");
	    			$('#districtForm').find('input[name=code]').next('span').html("该地区编码已存在！").removeClass('eleRight').addClass('eleWarning');
	    			isUniqueName = false;
	        	}else{
	        		$('#districtForm').find('input[name=code]').next('span').html('');
	        		$(this).removeClass("eleWarning");
	        		isUniqueName = true;
		        }
			});
		}
	});
    
    
	
    $("input[name=releaseDate]").epsDatepicker();
    $("#modifyDistrict").attr("disabled",true);
	$("#deleteDistrict").attr("disabled",true);
	$("#newDistrict").attr("disabled",true);
})

function removeEleWarning(){
	$("#districtFormPage span.eleWarning").html("*"); 
	$("#districtFormPage input").removeClass("eleWarning");
}