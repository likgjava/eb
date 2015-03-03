/*
 * 平台开发demo 供应商资格form
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var VenderQualificationForm={};
VenderQualificationForm.agreement;

//过滤已选的供货商
VenderQualificationForm.getSupply = function(){
	$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=getObjectQuery',
			{
				"agreement.objId":$("#agreementId").val(),
				queryColumns:"supplyer.objId"
			},
			function(json){
				var supplyIds = "";
				for(var i = 0;i<json.result.length;i++){
					if(i<json.result.length-1){
						supplyIds+=json.result[i]['supplyer.objId']+",";
					}else{
						supplyIds+=json.result[i]['supplyer.objId'];
					}
				}
				VenderQualificationForm.selectSupply(supplyIds);
			});
}

//弹出供货商选择页面(根据参数过滤)
VenderQualificationForm.selectSupply=function(supplyIds){
	
	//VenderQualificationForm.getSupply();
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭使用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=supplyId,supplyname' 
				+'&returnColums=objId,orgName'
				+'&DialogId=supplierSelect'
				+'&isCheckBox=false'
				+'&colums=orgName,orgCode'
				+'&columCns=供货商名称,机构代码'
				+'&domain=OrgInfo'
				+'&queryParams="objId":"'+supplyIds+'","objId_op":"!in"';
	$.epsDialog({
	   id:'supplierSelect',
       title:'请选择乙方',
       url:$('#initPath').val()+'/view/agreement/mamagement/supplierSelectList.jsp?'+params,
       width: '700',
       height: '400'
	}); 	
}

//弹出区间选择页面
VenderQualificationForm.selectArea = function(nodeId){
	/**参数:
	 * DialogId:弹出框Id供自动关闭使用
	 * className:实体名称
	 * Column：查询列
	 * defineRetuColums:回填表单Id
	 */
	var params = 'DialogId=selectArea&'
				+'className=Area'
				+'&Column=objId,areaName'
				+'&isOpen=1'
				+'&defineRetuColums=areaId,areaName'
				//+'&isCheckBox=true'
				+'&nodeId='+nodeId;
	$.epsDialog({
	   id:"selectArea",
	   title:'选择关联区间',
       url:$('#initPath').val()+'/view/agreement/mamagement/ObjectSelectTree.jsp?'+params,
       width: '700',
       height: '400'
   }); 
}

//弹出分类选择页面
VenderQualificationForm.addcategory = function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭使用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=goodsClassId,goodsClassName,brandName' 
				+'&returnColums=objId,goodsClass.goodsClassName,brand.brandName'
				+'&DialogId=addcategorySelect'
				+'&isCheckBox=true'
				+'&colums=goodsClass.goodsClassName,brand.brandName'
				+'&columCns=类别名称,品牌名称'
				+'&domain=AgreementGoodsclass'
				+'&queryParams="agreement.objId":"'+$("#objId").val()+'"';
	$.epsDialog({
	   id:'addcategorySelect',
       title:'请选择商品分类',
       url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
       width: '500',
       height: '400',
       onClose: function(){
    	   //将分类添加进入多选框
    	   var goodsClassId = $("#goodsClassId").val().split(",");
    	   var goodsClassName = $("#goodsClassName").val().split(",");
    	   var brandName = $("#brandName").val().split(",");
    	   
    	   if(''!=goodsClassId&&''!=goodsClassName&&''!=brandName){
        	   for(var i=0;i<goodsClassId.length;i++){
        		   $("#goodsClass").append('<option value="'+goodsClassId[i]+'" selected="selected">'+goodsClassName[i]+'-'+brandName[i]+'</option>');
        	   }
    	   }
    	   //清空
    	   $("#goodsClassId").val("");
    	   $("#goodsClassName").val("");
    	   $("#brandName").val("");
       }
	}); 
}

//弹出单品选择页面
VenderQualificationForm.addGoods=function(){
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭使用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=goodsId,goodsName' 
				+'&returnColums=objId,goods.productName'
				+'&DialogId=addGoodSelect'
				+'&isCheckBox=true'
				+'&colums=goods.productName,brand.brandName'
				+'&columCns=单品名称,品牌'
				+'&domain=AgreementGoods'
				+'&queryParams="agreement.objId":"'+$("#objId").val()+'"';
	$.epsDialog({
	   id:'addGoodSelect',
       title:'请选择商品',
       url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
       width: '500',
       height: '400',
       onClose: function(){
    	   $("#goodsNameTexarea").empty().append($("#goodsName").val());
    	   
    	   //将商品添加进入多选框
    	   var goodsId = $("#goodsId").val().split(",");
    	   var goodsName = $("#goodsName").val().split(",");
    	   
    	   if(""!=goodsId&&""!=goodsName){
    		   for(var i=0;i<goodsId.length;i++){
    			   $("#goods").append('<option value="'+goodsId[i]+'" selected="selected">'+goodsName[i]+'</option>');
    		   }
    	   }
    	   
    	   //清空
    	   $("#goodsId").val("");
    	   $("#goodsName").val("");
    	   
       }
	}); 
	
}

$(document).ready(function(){
	
	//开始时间
	$('#beginDate').epsDatepicker({applyRule: endRule });
	
	//结束时间
	$("#endDate").epsDatepicker({applyRule: startRule });
	
	//判断加载二级协议信息
	if(""!=$("#agreeSecondId").val()&&null!=$("#agreeSecondId").val()){
		$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=createOrUpdate',{objId:$("#agreeSecondId").val(),includedProperties:"supplyer,area"},function(json){
			jsonObjectToForm("SupplierQualify",json.agreementSecond);
			
			//加载附件
			if(""!=json.agreementSecond.content&&null!=json.agreementSecond.content){
				$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content&attachRelaId='+json.agreementSecond.content);
			}
			else{
				$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content');
			}
			
		});
	}else{
		//无附件
		$('#content').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content');
	}
	
	//是否已经选中单品或者分类
	if(''!=$("#goodsClassId").val()&&null!=$("#goodsClassId").val()){
		$.getJSON($('#initPath').val()+'/AgreementGoodsclassController.do?method=getObjectQuery',
				{
					"objId":$("#goodsClassId").val(),
					"objId_op":"in",
					queryColumns:"objId,goodsClass.goodsClassName,brand.brandName"
				},
				function(json){
					var goodsClassName = "";
					for(var i=0;i<json.result.length;i++){
							goodsClassName += '<option value="'+json.result[i].objId+'" selected="selected">'+json.result[i]['brand.brandName']+'-'+json.result[i]['goodsClass.goodsClassName']+'</option>';
					}
					$("#goodsClass").append(goodsClassName);
					//清空
					$("#goodsClassId").val("");
				});
	}
	
	//是否已经选中单品
	if(''!=$("#goodsId").val()&&null!=$("#goodsId").val()){
		$.getJSON($('#initPath').val()+'/AgreementGoodsController.do?method=getObjectQuery',
				{
					"objId":$("#goodsId").val(),
					"objId_op":"in",
					queryColumns:"objId,goods.productName"
				},
				function(json){
					var goodsName = "";
					for(var i=0;i<json.result.length;i++){
							goodsName += '<option value="'+json.result[i].objId+'" selected="selected">'+json.result[i]['goods.productName']+'</option>';
					}
					$("#goods").append(goodsName);
					//清空
					$("#goodsId").val("");
				});
	}
	
	$('#agreementFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=content');
	
	//选择供货商
	$("#supplyname").click(function(){
		VenderQualificationForm.getSupply();
	});
	
	//选区间
	$("#areaName").click(function(){
		VenderQualificationForm.selectArea($("input[id=area.objId]").val());
	});
	
	//新增商品分类
	$("#addcategory").click(function(){
		VenderQualificationForm.addcategory();
	});
	
	//删除商品分类
	$("#delcategory").click(function(){
		$("select[id=goodsClass] option:selected").remove();
	})
	
	//新增商品
	$("#addGoods").click(function(){
		VenderQualificationForm.addGoods();
	});
	
	//删除商品
	$("#delGoods").click(function(){
		$("select[id=goods] option:selected").remove();
	})
	
	//根据不同的请求来源决定表现形式的不同
	var request =  $("#resource").val();
	
	//返回
	$('#venderQualifyReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/vender_qualification_list.jsp");
	});

	//提交
	$('#venderQualifySave').click(function(){
		
		if(!$('#SupplierQualify').valid()){alert('请正确填写表单!');return;}
		//判断期间大小
		
		if($("#beginDate").val()<$("input[id=period.beginDate]").val()){
			alert("二级协议期间 必须包含于一级协议期间内");
			$("#beginDate").val("");
			return;
		}
		if($("#endDate").val()>$("input[id=period.endDate]").val()){
			alert("二级协议期间 必须包含于一级协议期间内");
			$("#endDate").val("");
			return;
		}
		
		//保存
		var ObjectStr =formToJsonObject("SupplierQualify",'second')
		$.getJSON($('#initPath').val()+'/AgreementSecondController.do?method=saveAgreementSecond',
			{
				"goodsClassId":$("#goodsClass").val(),
				"goodsIds":$("#goods").val(),
			    ObjectStr:JSON.stringify(ObjectStr)
			},
			function(json){
				if(json.success){
					alert(json.result);
					$('#conBody').loadPage($('#initPath').val()+"/view/agreement/mamagement/vender_qualification_list.jsp");
				}
			}
		);
	});
	
});
