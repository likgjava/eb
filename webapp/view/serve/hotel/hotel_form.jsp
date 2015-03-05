<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="hotelInfo">
	<form:form id="hotelInfoForm" name="hotelInfoForm" method="post" enctype="multipart/form-data" modelAttribute="hotel"> 
		<input type="hidden" name="objId" id="hotelId"  value="${param.objId }"/>
		<form:hidden path="auditStatus" id="auditStatus"  />
		<input type="hidden" name="hotelJsonString" id="hotelJsonString"  />
		<input type="hidden" id="submitRelaId" name="additionPicture" value="${hotel.additionPicture }"/>
		<input type="hidden" name="reducedWidth" id="reducedWidth" value="400" />
		<input type="hidden" name="reducedHeight" id="reducedHeight" value="300" />
		<form:hidden path="hotelDetail" />
		<div class="formLayout imgAndForm">	
			<h4 class="title"><span>酒店基本信息</span><span class="eleRequired">（带*号的为必填项）</span></h4>
			<div class="k1">
				<!-- 
				<div class="img_250_1" id="newPreview">
					<img id="pictureView"  src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="200px" height="175px"></img>
				</div>
				<input name="pictureFile" type="file" id="pictureFile" size="22" />
				<form:hidden path="picture" />
				-->
				
				<!-- 嵌入图片上传页面  
					maxWidth:宽
					maxHeight:高
					propertieName:表单中的名称（一般不直接写实体属性）
					nopicPath:提示图片路径
				 	propertieValue:表单值(用于回显图片)
				-->
				<jsp:include page="/view/srplatform/upload/img_upload_load.jsp">
					<jsp:param name="maxWidth" value="100" />
					<jsp:param name="maxHeight" value="100" />
					<jsp:param name="propertieName" value="pictureFile" />
					<jsp:param name="propertieValue" value="${hotel.picture}" />
					<jsp:param name="nopicPath" value="AttachmentController.do?method=showImg" />
				</jsp:include>
				 
			</div>
			<ul>
				<div class="formTips warm hidden" id="tips"></div>
				
				<li class="fullLine"><label>酒店名称 ：</label> 
					<input type="hidden" id="sameHotelName" value="false"/>
					<form:input path="hotelName" cssClass="input_medium required" id="hotelName" maxlength="50" size="40"/> 
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine"><label>酒店星级：</label> 
				<form:hidden path="star" />
				<html:select  styleClass="required" id="starSelect" name="" code="serve.hotel.star" >
					<html:option value="">-酒店星级-</html:option>
				</html:select><span class="eleRequired">*</span>
				</li>
				<li class="fullLine">
		            <label>开业时间：</label>
		            <input type="text" id="startTime" name="startTime" value="<fmt:formatDate value="${hotel.startTime}" pattern="yyyy-MM-dd"/>" size="27" class="required" readonly="readonly"/>
		            <span class="eleRequired">*</span>
		        </li>
				
				<li class="fullLine"><label>联系电话：</label> 
					<form:input path="contact" cssClass="input_medium cnPhone" id="contact" maxlength="20"/>
					<span class="eleRequired"></span>
				</li>
				<li class="fullLine"><label>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label> 
					<form:input path="fax" cssClass="input_medium cnPhone" id="fax" maxlength="20"/>
					<span class="eleRequired"></span>
				</li>
				<li class="formTextarea"><label>周围环境：</label>
					<textarea name="surroundings" id="surroundings" maxlength="250">${hotel.surroundings }</textarea>
				</li>
				
			</ul>
		</div>
		<div class="formLayout form2Pa">
			<h4 class="title"><span>酒店详细信息</span></h4>
			<ul id="hotelSelectBox">
				<li class="fullLine"><label>酒店服务项目：</label> 
					<form:hidden path="serviceItems" id="hotel_box_value" />
					<html:checkbox  styleClass="input_medium" name="serviceItems_box" code="serve.hotel.serviceItems"/>
					<span class="eleRequired"></span>
				</li>
				<li class="fullLine"><label>餐饮设施：</label> 
					<form:hidden path="foodFacilities" id="hotel_box_value" />
					<html:checkbox  styleClass="input_medium" name="foodFacilities_box" code="serve.hotel.foodFacilities"/>
					<span class="eleRequired"></span>
				</li>
				<li class="fullLine"><label>娱乐设施：</label> 
					<form:hidden path="funFacilities" id="hotel_box_value"/>
					<html:checkbox  styleClass="input_medium" name="funFacilities_box" code="serve.hotel.funFacilities"/>
					<span class="eleRequired"></span>
				</li>
				<li class="fullLine"><label>客房设施和服务：</label> 
					<form:hidden path="guestRoomFacilities" id="hotel_box_value"/>
					<html:checkbox  styleClass="input_medium" name="guestRoomFacilities_box" code="serve.hotel.guestRoomFacilities"/>
						<span class="eleRequired"></span>
				</li>
				<li class="fullLine"><label>可接受信用卡类型：</label> 
					<form:hidden path="creditCardType" id="hotel_box_value"/>
					<html:checkbox  styleClass="input_medium"  name="creditCardType_box" code="serve.hotel.creditCardType"/>
					<span class="eleRequired"></span>
				</li>
				<li class="fullLine">
		            <label>所在区域：</label>
		            <form:select path="" id="province">
		            	<c:if test="${hotel.district == null}">
		            		<form:option value="">请选择</form:option>
		            	</c:if>
		            	<form:options items="${province}" itemValue="code" itemLabel="name"/> 
		            </form:select>
		            <form:select path="" id="city">
		            	<form:options items="${city}" itemValue="code" itemLabel="name"/> 
		            </form:select>
		            <input type="hidden" id="townId" name="district.objId" value="<c:out value="${hotel.district.objId}"/>"/>
					<input type="hidden" id="cityId" name="cityId" value="<c:out value="${hotel.district.parent.objId}"/>"/>
					<input type="hidden" id="provinceId" name="provinceId" value="<c:out value="${hotel.district.parent.parent.objId}"/>"/>
		            <form:select path="" id="district.objId" cssClass="required">
		            	<form:options items="${town}" itemValue="code" itemLabel="name"/> 
		            </form:select>
		            <span class="eleRequired">*</span>
				</li>
				<li class="fullLine"><label>酒店地址：</label> 
					<form:input path="hotelAddress" cssClass="input_medium required" id="hotelAddress" maxlength="100"  size="60"/>
					<span class="eleRequired">*</span>
				</li>
				<li class="formTextarea"><label>酒店概况：</label>
					<textarea name="hotelDesc" id="hotelDesc" maxlength="500">${hotel.hotelDesc }</textarea>
				</li>
			</ul>
		</div>
	</form:form>
	
	<div id="picturesInfo" class="formLayout form2Pa">
		<h4 class="title"><span>更多酒店图片信息</span></h4>
		<div class="uploadFile2" id="additionPicture"></div>
	</div>
		
	<div>
		<h4 style="background: none repeat scroll 0 0 #CCE2F8;"><span>酒店详细介绍</span></h4>
		<div id="htmlEditor"></div>
		<textarea id="hotelDetailArea" class="hidden" maxlength="2000"></textarea>
	</div>
			
	<div class="conOperation">
		<button class="largeBtn" id="saveHotelBtn_save" name="saveHotelBtn_save" type="button" tabindex="17"><span>保存</span></button>
		<button class="largeBtn" id="saveHotelBtn_sumbit" name="saveHotelBtn_sumbit" type="button" tabindex="17"><span>提交</span></button>
		<button class="largeBtn" id="returnBtn" name="returnBtn" type="button" tabindex="17"><span>返回</span></button>
	</div>
</div>
	
<script>
var hotelInfoForm={};
var htmlEditor;

$(document).ready(function(){	

	$("#hotelInfoForm").validate();

	$("#startTime").epsDatepicker();

	// 唯一性验证
	$('#hotelName').blur(function(){
		if($(this).val() && $(this).val() != ""){
			$.getJSON($('#initPath').val()+'/HotelController.do?method=isUnique', {"objId":$("input[id=hotelId]").val(),"hotelName":native2ascii($(this).val())}, function(json){
	    		if(json.isUnique != 'true'){
	    			$('#hotelName').addClass("eleWarning");
		    		$('#hotelName').next('span').html("该名称已经存在！").removeClass('eleRight').addClass('eleWarning');
		    		$('#sameHotelName').val('true');
	    			isUniqueName = false;
	        	}else{
		    		$('#sameHotelName').val('false');
	        		$('#hotelName').next('span').html('');
	        		$('#hotelName').removeClass("eleWarning");
	        		isUniqueName = true;
		        }
			});
		}
	});

	//更多图片
	$('#additionPicture').loadPage($('#initPath').val()+'/AttachmentController.do?method=toUploadAjaxImg',{
			defineSelf:'additionPicture',//存放关联id的属性名
			maxSize:'1024',
			fileType: 'gif|jpeg|jpg|bmp|png',
			quantity:'4',//附件最大数
			attachRelaId:$("input[name=additionPicture]").val(),
			isView:'0',//是否只是显示图片不删除和上传
			maxWidth:100,//建议长
			maxHeight:100,//建议宽
			nopicPath:'AttachmentController.do?method=showImg'//提示图片  可以是AttachmentController.do?method=showImg
	});
	
	//返回
	$("button[name=returnBtn]").click(function(){         
		$('#conBody').loadPage($('#returnUrl').val());
	})
	
	/*
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
	*/
		
    //行政区域联动
	var option = {parameter:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=district.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});

    $("#province").val($("#provinceId").val());
    $("#city").val($("#cityId").val());
    $("select[id=district.objId]").val($("#townId").val());
    $('#starSelect').val($('#star').val());

    // 显示图片
    if($('#picture').val() !== ""){
    	$('img[id=pictureView]').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+$('#picture').val());
    }
    
	//保存或提交
	$('button[id^=saveHotelBtn_]').click(function(){ 
		$('#hotelName').blur();
		
		$("#hotelDetailArea").val(htmlEditor.getValue());  
		$('#hotelDetail').val($('#hotelDetailArea').val()); 
		if(!$('#hotelInfoForm').valid() || $('#sameHotelName').val() != 'false'){alert('请正确填写表单!');return;}
		if($('select[id=district.objId]').val()==null || $('select[id=district.objId]').val()==""){
			alert("请选择行政区域!");
			return;
		}
		
		$('button[id^=saveHotelBtn_]').attr('disabled',true);
		$("#townId").val($("select[id=district.objId]").val());
		$('#star').val($('#starSelect').val());
		var but_msg = $(this).attr("id").replace("saveHotelBtn_","")=="save"?"保存":"提交审核";
		$('#auditStatus').val($(this).attr("id").replace("saveHotelBtn_","")=="save"?"00":"01");
		$('input[id=hotel_box_value]').each(function(i,n){
			var boxName = $(this).attr("name");
			var checkedVal = $('input[type=checkbox][name='+boxName+'_box]:checked').map(function(){ return $(this).val();}).get().join(",");
			var checkedKey = $('input[type=checkbox][name='+boxName+'_box]:checked').map(function(){ return $(this).attr('title');}).get().join(",");
			$(n).val(checkedVal+"##||##"+checkedKey)
		});
		var hotel = formToJsonObject('hotelInfoForm','json');
		$('#hotelJsonString').val(JSON.stringify(hotel));
		if(window.confirm("确认"+but_msg+"该酒店信息吗?")){
			$("#hotelInfoForm").ajaxSubmit({
			 	url:$('#initPath').val()+'/HotelController.do?method=saveHotel',
				dataType:'json',
				success:function(json){
					if(json.result=='success') {
						alert("成功"+but_msg+"该酒店信息！");
						$('#conBody').loadPage($('#returnUrl').val());
					}else{
	    				alert(ascii2native(json.result));
	    				$('button[id^=saveHotelBtn_]').attr('disabled',false);
	    			}
				},
				error:function(msg){
					alert(JSON.stringify(msg));
					$('button[id^=saveHotelBtn_]').attr('disabled',false);
				}
			})
		}else{
			$('button[id^=saveHotelBtn_]').attr('disabled',false);
		}
	})
	
	// 回填酒的设施
	if($('#hotelId').val() != ""){
		$('input[id=hotel_box_value]').each(function(i,n){
			if($(n).val() != ""){
				var boxName = $(this).attr("name");
				$('input[type=checkbox][name='+boxName+'_box]').each(function(j,m){
					if($(n).val().indexOf($(m).val()) > -1){
						$(m).attr('checked',true);
					}
				});
			}
    	});
	}

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
	htmlEditor.setValue($("#hotelDetail").val()+" ");
})
</script>
