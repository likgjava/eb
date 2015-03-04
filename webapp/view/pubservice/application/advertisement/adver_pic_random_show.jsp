<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="adverPosInfoDivId">
</div>

<script>
	$.getJSON($('#initPath').val()+"/AdvertisingPositionController.do?method=getAdverOfPositionByRandom",{"adver_position_name":"adver_index_right4.jsp"},function(json){
		var url = "";
		if(json.adverSubscribe != null){
			url = "&objId="+json.adverSubscribe.adverFile;
		}
		var strHtml = "<a href='http://www.paihang360.com' target='_blank'><img src='AttachmentController.do?method=showImg"+url+"' width='238' height='250'/></a>";
		$('#adverPosInfoDivId').empty().append(strHtml);
	});
</script>