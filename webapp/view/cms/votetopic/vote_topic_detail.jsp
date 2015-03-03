<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<table width="98%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#5AADF6">
  <tr>
    <td height="25" colspan="2" bgcolor="#E7E7E7"><strong>投票主题:${voteTopic.title }</strong></td>
  </tr>
  <tr>
    <td height="25" colspan="2" bgcolor="#FFFFFF">投票总数：${voteTopic.totalCount }</td>
  </tr>
  <c:forEach items="${voteTopic.voteItems}" var="voteItem">
   <tr>
    <td width="256" height="25" bgcolor="#FFFFFF">${voteItem.title}</td>
    <td width="719" bgcolor="#FFFFFF">
    <img src="cms/vote/navMain_bg_hover.png" width="${voteItem.votePercent}%" height="10px" border="0"/>${voteItem.votePercent}%   </td>
  </tr>
 </c:forEach>
</table>
