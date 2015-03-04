<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/es/planform/bid/hostSpeakWord.js'></script>
<style type="text/css" media="print">
.noprint{display : none }
</style>
	<div class="noprint" style="text-align: left;">
		<input name="projId" type="hidden" id="projId" value="${param.projId}"/>
		<button type="button" id="print_info" ><span><span id="submitSpan">打印</span></span></button>
		<button type="button" id="print_view_info" ><span><span>预览</span></span></button>
		<button type="button" id="exp_info" ><span><span>导出Word</span></span></button>
	</div>
<div id="checkRequestInfo">
${content}
</div>
<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>
