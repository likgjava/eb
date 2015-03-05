<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GiftForm" name="GiftForm" method="post" modelAttribute="gift" enctype="multipart/form-data"> 
<form:hidden path="giftSupplier.objId"/>
<div id="firstGoodsInfo" class="formLayout formPa">
		<h4 class="title"><span>礼物信息</span></h4>
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
			<!--<input name="pictureFile" type="file" id="pictureFile" size="22" value=""/>-->
			<form:hidden path="picture"/>
			<form:hidden path="giftType" />
		</div>
		<ul>
			<!--<div class="formTips attention">选择商品作为礼品<span class="sysicon siAdd"><a href="javascript:selectGift();"><strong>选择</strong></a></span>
			<form:hidden path="goods.objId"/>
			<span id="goodsName"></span>
			</div>-->
			<div class="formTips warm hidden" id="tips"></div>
			<li class="fullLine"><label>礼品名称 ：</label> 
				<span>${gift.giftName}</span><!--<form:input cssClass="required" path="giftName"/>-->
				<!--<span class="eleRequired">*</span>-->
			</li>
			<li class="fullLine"><label>所属系列：</label>
         	    <span>${gift.giftSeries.name}</span>
         	    <!--<form:input path="giftSeries.name" cssClass="sysicon siSearch required" readonly="readonly" onclick="selectGiftSeries();"/>
         	    <form:hidden path="giftSeries.objId"/>
         	    <span class='eleRequired'>*</span>-->
			</li>
			<li class="fullLine"><label>礼品编号：</label> 
				<span>${gift.giftCode}</span><!--<form:input cssClass="required" path="giftCode"/>
				<span class="eleRequired">*</span>--> 
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
			<li class="fullLine"><label>是否启用：</label> 
				<span>${gift.isUsedCN}</span>
				<!--<form:select path="isUsed">
					<form:option value="true">是</form:option>
					<form:option value="false">否</form:option>
				</form:select>-->
			</li>
		</ul>
		<h4>礼品描述信息</h4>
		<div>${gift.giftComment}</div>
		<!--<form:textarea path="giftComment"></form:textarea>-->
</div>
</form:form>

<div class="conOperation">
	<button name="submitGift"><span>保存</span></button>
	<button name="historyBackBtn"><span>返回</span></button>
</div>

<script type="text/javascript">
var GiftForm={};
//选择商品
selectGift = function(){
	$.epsDialog({
        title:'选择商品',
        url:$('#initPath').val()+'/view/smallscale/pointmall/gift_goods_select.jsp?ctrlId='+this.id,
        onClose:function(){
        	if($("input[name=goods.objId]").val()){
            	$("input[name=giftType]").val('00');//实物
        	}else{
            	$("input[name=giftType]").val('01');//虚拟
        	}
		}
	})
}

//选择系列
selectGiftSeries = function(){
    $.epsDialog({
        title:'请选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=giftSeries&className=GiftSeries&action=listTop&isOpen=1&childNodeOnly=true&checkValues='+$("input[id=giftSeries.objId]").val()
    }); 
}

//保存或者提交
createOrUpdateGiftSeries = function(giftJson){
	$('#GiftForm').ajaxSubmit({
		url:$("#initPath").val()+"/GiftController.do?method=saveGift&saveType="+giftJson.saveType,
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
			alert("操作成功！");
		}
	});
}

$(document).ready(function(){	
	//加载验证
	$("#GiftForm").validate();

	//开始时间
    $("#startTime").epsDatepicker({timeShow:true,applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endTime").epsDatepicker({timeShow:true,applyRule: startRule });  //增加开始时间的规则

	//预览文件
	$("#picture").change(function(){
		var filePath = $("#picture").val();
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
		if(!$('#GiftForm').valid()){alert('请正确填写表单!');return;}
		var giftJson = formToJsonObject("GiftForm");
		giftJson.saveType="submit";
		createOrUpdateGiftSeries(giftJson)
	})
	
	//编辑器
	//var oFCKeditor = new FCKeditor('giftComment','100%','240','Template','');
	//oFCKeditor.ReplaceTextarea();
})
</script>

	
