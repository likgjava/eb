<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="PromoterBuyerRecordForm" id="PromoterBuyerRecordForm">  
<div class="formLayout form2Pa">
	<ul>        
		<li>
            <label for="promoterName">推荐人姓名：</label>
            <input type="text" name="promoterName" id="promoterName" class="required" />
            <span class="eleRequired">*</span>
        </li>
    	<li>
            <label for="promoterDate">推荐日期：</label>
            <input type="text" name="promoterDate" id="promoterDate" class="sysicon siDate required" />
            <span class="eleRequired">*</span>
            <input type="hidden" name="recordType" id="recordType" value="00"/>
            <input type="hidden" name="dealStatus" id="dealStatus" value="00"/>
            <input type="hidden" name="tellStatus" id="tellStatus" value="0"/>
        </li>
        <li class="fullLine">
            <label for="promoterCID">推荐人身份证号：</label>
            <input type="text" name="promoterCID" id="promoterCID" class="cnIdCard required" maxlength="18" />
            <span class="eleRequired">*</span>
        </li>
        <li class="fullLine formTextarea">
            <label for="promoterMemo">备注：</label>
            <textarea name="promoterMemo" id="promoterMemo" ></textarea>
            <span class="operationBtnDiv"><button type="button" id="submitBtn" class="next"><span>提交</span></button></span>
        </li>
    </ul>
</div>
</form>

<script>
var PromoterBuyerRecordForm = {};

$(document).ready(function(){
	//表单验证
	$("#PromoterBuyerRecordForm").validate();

	//初始化日期控件
	$("#promoterDate").epsDatepicker();

	//确认提交
	$("#submitBtn").click(function(){
		if(!$('#PromoterBuyerRecordForm').valid()){alert('请正确填写表单!');return;}
		
		if(window.confirm('是否确认提交？')){
			$("#submitBtn").attr("disabled", true);
			$.getJSON($('#initPath').val()+"/PromoterController.do?method=promoterSave", formToJsonObject('PromoterBuyerRecordForm'), function(json){
				if(json.failure){if(json.result)alert(json.result);$("#submitBtn").attr("disabled",false);return;}
				else{
					if(json.result=='faile'){
						alert('提交失败,请重新提交!');
						$("#submitBtn").attr("disabled",false);
					}else{
			  			alert('提交成功.' + "   生成对方确认的验证码为：" + json.result)
			  			$('#promoterInfo').loadPage($('#initPath').val()+'/PromoterController.do?method=toPromoterBuyerRecord');
			  		}				
				}
			});
		}
	});
});
</script>