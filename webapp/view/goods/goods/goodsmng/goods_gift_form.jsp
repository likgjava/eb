<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="goodsGiftForm" name="goodsGiftForm" method="post" enctype="multipart/form-data" > 
	<input type="hidden" name="goods.objId"  value=""/>
	<input type="hidden" name="supplier.objId"  value=""/>
	<input type="hidden" name="objId" id="goodsGiftId" value="${goodsGift.objId}"/>
	
	<div id="goodsGiftInfo" class="formLayout form2Pa">
		<h4 class="title"><span>礼包信息</span><span class="eleRequired">（带*号的为必填项）</span></h4>
		<div class="k1">
			<div class="img_250_2" id="newPreview_div">
				<c:choose>
					<c:when test="${goodsGift.giftPicture==null}">
						<img id="view" src="<%=request.getContextPath()%>/view/resource/skin/bizplatform/img/orginfo_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsGift.giftPicture}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile_div" />
		</div>
		<ul>
			<li class="fullLine">
				<label>礼包名称 ：</label> 
				<input type="text" name="giftName" value="${goodsGift.giftName}" class="required"  maxlength="50" /> 
				<span class="eleRequired">*</span>
			</li>
			<li class="formTextarea">
				<label>礼包描述：</label>
				<textarea id="giftDesc" name="giftDesc" maxlength="250">${goodsGift.giftDesc}</textarea>
			</li>
		</ul>
		<div class="conOperation">
			<button type="button" id="saveGoodsGiftBtn"><span>保存</span></button>
			<button type="button" id="closeBtn"><span>关闭</span></button>
		</div>
	</div>
</form>
	
<script>
/**
 * 新增或修改礼包页面
 * create by likg
 */
var GoodsGiftForm={};

$(document).ready(function(){	

	$("#goodsGiftForm").validate();

	//关闭
	$("#closeBtn").click(function(){         
		$('#epsDialogClose').click();
	})
	
	//保存礼包信息
	$('#saveGoodsGiftBtn').click(function(){     
		if(!$('#goodsGiftForm').valid()){alert('请正确填写表单!');return;}
		$("input[name=goods.objId]").val($("#goodsId").val());
		$("input[name=supplier.objId]").val($("#supplierId").val());
		
		if(window.confirm("确认保存礼包信息吗?")){
			$("#goodsGiftForm").ajaxSubmit({
			 	url:$('#initPath').val()+'/GoodsGiftController.do?method=saveGoodsGift',
				dataType:'json',
				success:function(json){
					if(json.failure){alert(json.result);return;}
					$("#closeBtn").click();
				},
				error:function(msg){
					alert(JSON.stringify(msg));
				}
			})
		}
	});

	//预览图片
	$("#pictureFile_div").change(function(){
		var filePath = $("#pictureFile_div").val();
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
			$("#newPreview_div").find('img').attr("src",preViewPic(this));
		}
	});
})
</script>
