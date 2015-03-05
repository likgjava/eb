<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/schedulerJobList.js"></script>
<div class="operationBtnBox">
<button class="normBtn" id="newJob"><span><spring:message code="schedulerJobList.add"/></span></button>
</div>
 <table class="tableGoods">
      <tr>
        <th align="center">标识任务的名称</th>
        <th align="center">标识任务的名称</th>
        <th align="center">标识任务的名称</th>
        <th align="center">标识任务的名称</th>
      </tr>
      <tr>
        <td align="center"><img src="<%=request.getContextPath()%>/view/resource/skin/blue/img/login_epsLogo.png" /></td>
        <td align="center"><img src="<%=request.getContextPath()%>/view/resource/skin/blue/img/login_epsLogo.png" /></td>
        <td align="center"><img src="<%=request.getContextPath()%>/view/resource/skin/blue/img/login_epsLogo.png" /></td>
        <td align="center"><img src="<%=request.getContextPath()%>/view/resource/skin/blue/img/login_epsLogo.png" /></td>
      </tr>
      <tr>
        <td><div class="conOperation"><button type="submit"><span>暂停</span></button><button type="submit"><span>修改</span></button><button type="submit"><span>删除</span></button></div></td>
        <td><div class="conOperation"><button type="submit"><span>暂停</span></button><button type="submit"><span>修改</span></button><button type="submit"><span>删除</span></button></div></td>
        <td><div class="conOperation"><button type="submit"><span>暂停</span></button><button type="submit"><span>修改</span></button><button type="submit"><span>删除</span></button></div></td>
       	<td><div class="conOperation"><button type="submit"><span>暂停</span></button><button type="submit"><span>修改</span></button><button type="submit"><span>删除</span></button></div></td>
      </tr>
 </table>