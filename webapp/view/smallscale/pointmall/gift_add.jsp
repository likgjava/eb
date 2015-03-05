<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GiftForm" name="GiftForm" method="post" modelAttribute="gift" enctype="multipart/form-data"> 
<div id="firstGoodsInfo">
	<!-- 礼品信息 -->
	<div class="formLayout imgAndForm">
		<h4 class="title"><span>礼品信息</span></h4>
		<div class="k1">
			<div class="img_250_1" id="newPreview">
				<c:choose>
				<c:when test="${gift.picture!=null}">
					<img width="200" height="175" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}"/>"></img>
				</c:when>
				<c:otherwise>
					<img width="200" height="175" src="<c:url value="/view/resource/skin/goods/img/brand_add.gif"/>"></img>
				</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22" value=""/>
			<form:hidden path="picture"/>
		</div>
		<ul>
			<div class="formTips attention">选择商品作为礼品<span class="sysicon siAdd"><a href="javascript:selectGift();"><strong>选择</strong></a>&nbsp;如果不选择商品将作为虚拟礼品</span>
			<form:hidden path="goods.objId"/>
			<span id="goodsName"></span>
			</div>
			<div class="formTips warm hidden" id="tips"></div>
			<li class="fullLine"><label>礼品名称 ：</label> 
				<form:input cssClass="required" path="giftName"/> 
				<input type="hidden" id="samename" value="false"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>礼品类型 ：</label> 
				<form:select path="giftType" onchange="giftTypeChange(this);return false;">
					<form:option value="00">实物</form:option>
					<form:option value="01">虚拟</form:option>
				</form:select>
			</li>
			<!-- 
			<li>
				<label>是否启用：</label> 
				<form:select path="isUsed">
					<form:option value="true">是</form:option>
					<form:option value="false">否</form:option>
				</form:select>
			</li>
			 -->
			<li class="fullLine hidden" id="giftSeriesLi"><label>所属系列：</label>
         	    <form:input path="giftSeries.name" cssClass="sysicon siSearch" readonly="readonly" onclick="selectGiftSeries();"/>
		       	<form:hidden path="giftSeries.objId"/>
			</li>
			<li class="fullLine"><label>礼品供货商：</label>
         	    <form:input path="giftSupplier.supplierName" cssClass="sysicon siSearch" readonly="readonly" onclick="selectGiftSupplier();"/>
		       	<form:hidden path="giftSupplier.objId"/>
			</li>
			<li class="fullLine"><label>兑换总数：</label> 
				<form:input cssClass="required digits" path="exchangeCount"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine"><label>开始时间：</label>
				<input class="required" name="startTime" id="startTime" value="<fmt:formatDate value="${gift.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				至：<input class="required" name="endTime" id="endTime" value="<fmt:formatDate value="${gift.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				<span class="eleRequired">*</span>
			</li>
			<c:choose>
				<c:when test="${fn:length(gift.giftExchangeRuleSet)>0}">
					<c:forEach var="giftExchangeRule" items="${gift.giftExchangeRuleSet}" varStatus="statu">
						<c:choose>
							<c:when test="${statu.index==0}">
								<li name="rule" id="${giftExchangeRule.objId}" class="fullLine"><label>兑换规则：</label> 
									<input type="text" name="ruleYuan${statu.index+1}" class="money required" style="width:40px;" value="${giftExchangeRule.amount}"/>&nbsp;元+
									<input type="text" name="ruleFen${statu.index+1}" class="money required" style="width:40px;" value="${giftExchangeRule.score}"/>&nbsp;分
									&nbsp;<a href="javascript:void(0);" onclick="addRule(this);">添加规则</a>
								</li>
							</c:when>
							<c:otherwise>
								<li name="rule" id="${giftExchangeRule.objId}" class="fullLine"><label>或：</label> 
									<input type="text" name="ruleYuan${statu.index+1}" class="money" style="width:40px;" value="${giftExchangeRule.amount}"/>&nbsp;元+
									<input type="text" name="ruleFen${statu.index+1}" class="money" style="width:40px;" value="${giftExchangeRule.score}"/>&nbsp;分
									&nbsp;<a href="javascript:void(0);" onclick="delRule(this);return false" name="deleteRule">删除</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li name="rule" class="fullLine"><label>兑换规则：</label> 
						<input type="text" name="ruleYuan1" class="money required" style="width:40px;"/>&nbsp;元+
						<input type="text" name="ruleFen1" class="money required" style="width:40px;"/>&nbsp;分
						&nbsp;<a href="javascript:void(0);" onclick="addRule(this);return false;">添加规则</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<!-- 礼品描述信息 -->
	<div>
		<h4 style="background: none repeat scroll 0 0 #CCE2F8;"><span>礼品描述信息</span></h4>
		<div id="htmlEditor"></div>
		<textarea id="giftComment" name="giftComment" class="hidden">${gift.giftComment}</textarea>
	</div>
	
	<!-- 操作 -->
	<div class="conOperation">
		<button type="button" name="submitGift"><span>保存</span></button>
		<button type="button" name="historyBackBtn"><span>返回</span></button>
	</div>
</div>
</form:form>


<div name="Temp" class="hidden">
<li name="Temp" class="fullLine">
	<label>或：</label> 
	<input type="text" name="ruleYuan" class="money" style="width:40px;"/>&nbsp;元+
	<input type="text" name="ruleFen" class="money" style="width:40px;"/>&nbsp;分
	&nbsp;<a href="javascript:void(0);" onclick="delRule(this);return false" name="deleteRule">删除</a>
</li>
</div>

<script type="text/javascript">
var GiftForm={};
var htmlEditor;

//选择商品
selectGift = function(){
	$.epsDialog({
        title:'选择商品',
        url:$('#initPath').val()+'/view/smallscale/pointmall/gift_goods_select.jsp?ctrlId='+this.id,
        onClose:function(){
        	if($("input[name=goods.objId]").val()){
            	$("select[name=giftType]").val('00');//实物
            	giftTypeChange();
        	}else{
            	$("select[name=giftType]").val('01');//虚拟
            	giftTypeChange();
        	}
		}
	})
}

//切换礼品类型
giftTypeChange = function(e){
  	var giftType = $("select[name=giftType]").val();
	//虚拟则清空
	if("01"==giftType){
		$("input[name=goods.objId]").val(null);//回填id
		$("span[id=goodsName]").empty();//回显名称

		$("li[id=giftSeriesLi]").hide("fast");

		$("input[name=giftName]").val("");//清空名称
		$("input[name=giftSeries.name]").val("");//清空名称
		$("input[name=giftSeries.objId]").val("");//清空名称
	}else{
		$("li[id=giftSeriesLi]").show("fast");
	}
}

//选择系列
selectGiftSeries = function(){
    $.epsDialog({
        title:'请选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=giftSeries&className=GiftSeries&action=listTop&isOpen=1&childNodeOnly=true&checkValues='+$("input[id=giftSeries.objId]").val()
    }); 
}

//删除规则
delRule = function(e){
	if($(e).parent().attr("id")){
		$.getJSON($("#initPath").val()+"/GiftExchangeRuleController.do?method=remove",{"objId":$(e).parent().attr("id")},function(json){
			if(json.success){$(e).parent().remove();}
		})	
	}else{
		$(e).parent().remove();
	}
}

//添加规则
addRule = function(e){
	var size = $("li[name=rule]").length;
	var newRow = $("div[name=Temp]").html().replace("Temp","rule").replace("hidden","");
	newRow = newRow.replace("ruleYuan","ruleYuan"+(size+1));
	newRow = newRow.replace("ruleFen","ruleFen"+(size+1));
	$("li[name=rule]:last").after(newRow);
}

//选择供货商
selectGiftSupplier = function(){


	var currentdate = gpcsoftDate.getFullYear()+"-"+ (gpcsoftDate.getMonth()+1) +"-" + gpcsoftDate.getDate() ;
	
	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭时用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums=giftSupplier.objId,giftSupplier.supplierName' 
				+'&returnColums=objId,supplierName'
				+'&DialogId=giftSupplier'
				+'&isCheckBox=false'
				+'&colums=supplierName'
				+'&columCns=供货商名称'
				+'&domain=GiftSupplier'
				+'&queryParams=\'isUsed\':true,\'startTime\':\''+currentdate + '\',\'startTime_op\':\'<=\'' +',\'endTime\':\''+currentdate + '\',\'endTime_op\':\'>=\'';
				
	$.epsDialog({
		id:'giftSupplier',
        title:'选择礼品供货商',
        url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
        width: '700',
        height: '400'
	}); 
}

//保存或者提交
createOrUpdateGiftSeries = function(giftJson){
	$('#GiftForm').ajaxSubmit({
		url:$("#initPath").val()+"/GiftController.do?method=saveGift&saveType="+giftJson.saveType+"&ruleStr="+giftJson.ruleStr,
		dataType:'json',
		success:function(json){
			if(json.result=='success') {
				alert("操作成功！");
			}else {
				alert(ascii2native(json.result));
			}
			$("button[name=historyBackBtn]").click();//返回
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
}

$(document).ready(function(){	
	var giftType = $("select[name=giftType]").val();
	if(giftType!=null && giftType=='00'){
		$("li[id=giftSeriesLi]").show();
	}
	
	//加载验证
	$("#GiftForm").validate();

	//开始时间
    $("#startTime").epsDatepicker({timeShow:true,applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endTime").epsDatepicker({timeShow:true,applyRule: startRule });  //增加开始时间的规则

	//预览文件
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	})
	
	//提交
	$("button[name=submitGift]").click(function(){

		var flag = true;//标志
		$("#giftComment").val(htmlEditor.getValue());
		if(!$('#GiftForm').valid()){alert('请正确填写表单!');return;}
		var giftJson = formToJsonObject("GiftForm");
		giftJson.saveType="submit";
		//兑换规则的数据
		giftJson.ruleStr="";
		$.each($("li[name=rule]"),function(index,obj){
			if($(obj).find("input[name*=ruleYuan]").val()&&$(obj).find("input[name*=ruleFen]").val()){
				if(Number($(obj).find("input[name*=ruleFen]").val())<=0){alert("积分不能为零！");flag=false;}
				giftJson.ruleStr += (giftJson.ruleStr==""?"":",")+$(obj).attr("id")+":"+$(obj).find("input[name*=ruleYuan]").val()+":"+$(obj).find("input[name*=ruleFen]").val();
			}
		})
		
		//实物礼品必须选择礼品
		if($("select[name=giftType]").val()=="00"&&!$("input[name=goods.objId]").val()){
			alert("实物礼品必须选择商品！"); flag = false;
		}
		
		if(flag){//验证通过能提交或保存
			createOrUpdateGiftSeries(giftJson);
		}
		return false;
	})
	
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 240,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue($("#giftComment").val()+" ");
})
</script>

	
