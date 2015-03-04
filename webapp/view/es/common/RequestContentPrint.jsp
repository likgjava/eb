<%@ page pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("gpc", request.getContextPath());
	String content = request.getSession().getAttribute("content")+"";
%>
<script type="text/javascript" src='${gpc }/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>//view/es/common/RequestContentPrint.js"></script>
<style type="text/css" media="print">
.noprint{display : none }
</style>
	<div class="noprint" style="text-align: left;">
		<button type="button" id="print_info" ><span><span id="submitSpan">打印</span></span></button>
		<button type="button" id="print_view_info" ><span><span>预览</span></span></button>
		<button type="button" id="exp_info" ><span><span>导出Word</span></span></button>
	</div>
<div id="checkRequestInfo">
<table class='MsoNormalTable' border='1' cellspacing='0' cellpadding='0' width='625' align='center'
 style='width:469.1pt;border-collapse:collapse;border:none;mso-border-alt:solid red .5pt;
 mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;mso-border-insideh:
 .5pt solid red;mso-border-insidev:.5pt solid red'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes'>
  <td width='625' style='width:469.1pt;border:none;padding:0cm 5.4pt 0cm 5.4pt' id="print_td">
<%=content %>
</td>
</tr>
</table>
</div>
<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>