<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">

$(document).ready(function(){

	// 关闭
	$('#closeFtlTemplateDetilBtn').click(function(){
		$('#epsDialogClose').click();
	})
	
});

</script>
<div class="formLayout" >
<table style="width:750px;" align="center">
<tr>
<td id="ftlTemplateDetil">
</td>
</tr>
</table>
<div class="conOperation">
	<button type="button" id="closeFtlTemplateDetilBtn" tabindex="19"><span>关闭</span></button>
</div>
</div>