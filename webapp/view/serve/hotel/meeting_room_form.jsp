<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

	<form:form id="meetingRoomInfoForm" name="meetingRoomInfoForm" method="post" enctype="multipart/form-data" modelAttribute="meetingRoom"> 
	<input type="hidden" name="hotel.objId"  value="${param.hotelId }"/>
	<input type="hidden" name="reducedWidth" id="reducedWidth" value="400" />
	<input type="hidden" name="reducedHeight" id="reducedHeight" value="300" />
	<form:hidden path="objId" id="meetingRoomId"/>
	
	<div id="meetingRoomInfo">
		<div class="formLayout imgAndForm">
			<h4 class="title"><span>会议室信息</span><span class="eleRequired">（带*号的为必填项）</span></h4>
			<div class="k1">
				<div class="img_250_1" id="newPreview">
					<img id="pictureView"  src="<%=request.getContextPath()%>/view/resource/skin/goods/img/goods_add.gif" width="200px" height="175px"></img>
				</div>
				<input name="pictureFile" type="file" id="pictureFile" />
				<form:hidden path="picture" />
			</div>
			<ul>
				<div class="formTips warm hidden" id="tips"></div>
				<li class="fullLine"><label>会议室编号 ：</label> 
					<input type="hidden" id="sameMeetingRoomCode" value="false"/>
					<form:input path="meetingRoomCode" cssClass="input_medium required"  maxlength="50" /> 
					<span class="eleRequired">*</span>
				</li>
				
				<li class="fullLine"><label>市场价 ：</label> 
					<form:input path="marketPrice" cssClass="input_medium money required"  maxlength="16" /> 
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine"><label>坐席人数范围 ：</label>
				<form:hidden path="meetingNumRang"/>
					<html:select  styleClass="required" id="meetingNumRangSelect" name="" code="serve.hotel.meetingNumRang"  >
						<html:option value="">-人数范围-</html:option>
					</html:select>
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine" id="containNumLi"><label>坐席人数 ：</label>
					<input name="containNum" id="containNum" class="input_medium digits required" maxlength="5" size="5" value="${meetingRoom.containNum }"/> 
					<span class="eleRequired">*</span>
				</li>
				<li class="fullLine"><label>价格单位：</label> 
					<form:hidden path="unit" />
					<html:select id="unitCN" name="" code="serve.hotel.priceUnit" >
					</html:select>
				</li>
				<li class="fullLine"><label>会议室类型：</label> 
					<form:hidden path="meetingRoomType" />
					<html:select id="meetingRoomTypeCN" name="" code="serve.hotel.meetingRoomType" >
					</html:select>
				</li>
				<li class="fullLine"><label>会议室设施：</label> 
					<form:hidden path="meetingRoomFacilities" id="meetingRoomFacilities"/>
					<html:checkbox  styleClass="input_medium" name="meetingRoomFacilities_box" code="serve.hotel.meetingRoomFacilities"/>
					<span class="eleRequired"></span>
				</li>
				
			</ul>
		</div>
		<div class="formLayout form2Pa">
			<h4 class="title"><span>会议室描述</span><span class="eleRequired"></span></h4>
			<ul>
			<li class="formTextarea"><label>会议室描述：</label>
					<textarea name="meetingRoomFesc" id="meetingRoomFesc" maxlength="250">${meetingRoom.meetingRoomFesc}</textarea>
				</li>
			</ul>
		</div>
		<div class="conOperation">
			<button class="largeBtn" id="saveMeetingRoomBtn" name="saveMeetingRoomBtn" type="button" tabindex="17"><span>保存</span></button>
			<button class="largeBtn" id="closeBtn" name="closeBtn" type="button" tabindex="17"><span>关闭</span></button>
		</div>
	</div>
	</form:form>
	
<script>
var meetingRoomInfoForm={};

$(document).ready(function(){	

	$("#meetingRoomInfoForm").validate();

	$('#meetingNumRangSelect').val($('#meetingNumRang').val());
	// 唯一性验证
	$('#meetingRoomCode').blur(function(){
		if($(this).val() != ""){
			$.getJSON($('#initPath').val()+'/MeetingRoomController.do?method=isUnique', {"objId":$('#meetingRoomId').val(),"hotelId":$("input[name=hotel.objId]").val(),"meetingRoomCode":$(this).val()}, function(json){
	    		if(json.isUnique != 'true'){
	    			$('#meetingRoomCode').addClass("eleWarning");
		    		$('#meetingRoomCode').next('span').html("该编号已经存在！").removeClass('eleRight').addClass('eleWarning');
		    		$('#sameMeetingRoomCode').val('true');
	    			isUniqueName = false;
	        	}else{
		    		$('#sameMeetingRoomCode').val('false');
	        		$('#meetingRoomCode').next('span').html('');
	        		$('#meetingRoomCode').removeClass("eleWarning");
	        		isUniqueName = true;
		        }
			});
		}
	});
	$('#meetingNumRangSelect').change(function(){
		if($(this).val()!=""){
			$('#containNumLi').show()
			$('#containNumLi').attr('min','').attr('max','');
			if($(this).val().split("_")[0]!="")$('#containNum').attr('min',$(this).val().split("_")[0]);
			if($(this).val().split("_")[1]!="")$('#containNum').attr('max',$(this).val().split("_")[1]);
			
		}else{
			$('#containNumLi').hide()
		}
	});

	$('#meetingNumRangSelect').change();
	
	//返回
	$("#closeBtn").click(function(){         
		$('#epsDialogClose').click(); 
	})
	
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
	

	// 显示图片
    if($('#picture').val() !== ""){
    	$('img[id=pictureView]').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+$('#picture').val());
    }

	// 回填酒店的设施
	if($('#meetingRoomFacilities').val() && $('#meetingRoomFacilities').val() != ""){
		$('input[type=checkbox][name=meetingRoomFacilities_box]').each(function(j,m){
			if($('#meetingRoomFacilities').val().indexOf($(m).val()) > -1){
				$(m).attr('checked',true);
			}
		});
	}
	if($('#meetingRoomType').val()){
		$('#meetingRoomTypeCN').val($('#meetingRoomType').val());
	}
	if($('#unit').val()){
		$('#unitCN').val($('#unit').val());
	}
	
	//保存或提交
	$('#saveMeetingRoomBtn').click(function(){     
		if(!$('#meetingRoomInfoForm').valid() || $('#sameMeetingRoomCode').val() != 'false'){alert('请正确填写表单!');$('#containNum').blur();return;}
		$('#meetingRoomType').val($('#meetingRoomTypeCN').val());
		$('#unit').val($('#unitCN').val());
		$('#meetingNumRang').val($('#meetingNumRangSelect').val());
		var checkedVal = $('input[type=checkbox][name=meetingRoomFacilities_box]:checked').map(function(){ return $(this).val();}).get().join(",");
		var checkedKey = $('input[type=checkbox][name=meetingRoomFacilities_box]:checked').map(function(){ return $(this).attr('title');}).get().join(",");
		$('#meetingRoomFacilities').val(checkedVal+"##||##"+checkedKey)
		if(window.confirm("确认保存会议室信息吗?")){
			$("#meetingRoomInfoForm").ajaxSubmit({
			 	url:$('#initPath').val()+'/MeetingRoomController.do?method=saveMeetingRoom',
				dataType:'json',
				success:function(json){
					if(json.failure){alert(json.result);return;}
					alert("会议室信息保存成功！");
					$('#epsDialogClose').click(); 
					meetingRoomList.getMeetingRoomList();
									},
				error:function(msg){
					alert(JSON.stringify(msg));
				}
			})
		}
	})
})
</script>
