<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<div class="formLayout form2Pa">
		<h5><span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人信息</span></h5>      
	    	<ul>
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人： </label>
					<span>${workgMember.workgmName}</span>
				</li>
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人帐号：</label>
					<span>${workgMember.workgmAccount}</span>
				</li>
	    		<li>
					<label  class="short">联系电话： </label>
					<span>${workgMember.linkerPhone}</span>
				</li>
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人类型： </label>
					<span>${workgMember.workgmTypeCN}</span>
				</li>
	    		<li>
					<label  class="short">电子邮件： </label>
					<span>&nbsp;</span>
				</li>
	    		<li>
					<label  class="short">证书： </label>
					<span>&nbsp;</span>
				</li>
	    	</ul>
	<div class="conOperation">
		<button id="closeBtn" type="button" ><span>关闭</span></button>
	</div>
	</div>    
</div>

<script type="text/javascript">
$('#closeBtn').click(function(){
	$('#epsDialogCloseNoReload').click();
});

</script>    
