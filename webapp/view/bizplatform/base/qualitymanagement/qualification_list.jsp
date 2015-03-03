<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/base/qualitymanagement/qualification_list.js"></script>
<style>
.befSave {background-color:#dfd}
</style>
<div class="formTips attention">
	<ul>
		<li>新增资质分类请点击<span class="sysicon siAdd"><a id="addQulificationCasBtn" href="javascript:void(0);"><strong>添加资质分类</strong></a></span></li>
	</ul>
</div>
<form id="qualityForm">

<table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="center">资质类型</th>
          <th class="center">名称</th>
          <th class="center">操作</th>
          <th class="center">添加子分类</th>
          <th class="center">添加资质定义</th>
          <th class="center">添加资质参数</th>
          <th class="center">排序</th>
        </tr>
      </thead>
      <tbody id="qualityTbody">
      </tbody>
      <tfoot id="qualityTfoot"  class="hidden">
      	<tr>
      		<td class="operation">资质分类</td>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='qualityName'/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a href="javascript:void(0);" class="hidden" name="detilQualificationClass" class="hidden"><span class="sysicon siEditBtn">&nbsp;</span></a><a href="javascript:void(0); " name="save_Class"><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeQualificationClass"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="operation"><a href='javascript:void(0);' class="hidden" name='addSubQualityClass'>添加子分类</a></td>
      		<td class="operation"><a href='javascript:void(0);' class="hidden" name='addSubQualityDefine'>添加资质定义</a></td>
      		<td class="center">&nbsp;</td>
      		<td class="center"><a href='javascript:void(0);' name='up' class="hidden"><span class="sysicon siUp">&nbsp;</span></a><a href='javascript:void(0);' name='down' class="hidden"><span class="sysicon siDown">&nbsp;</span></a></td>
      	</tr>
      	<tr>
      		<td class="operation">资质定义</td>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='qualityName'/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a href="javascript:void(0);" name="detilQualificationDefine" class="hidden"><span class="sysicon siEdit">&nbsp;</span></a><a href="javascript:void(0);" name="save_Define"  ><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeQualificationDefine"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="center">&nbsp;</td>
      		<td class="center">&nbsp;</td>
      		<td class="operation"><a href='javascript:void(0);' class="hidden" name='addSubQualityParam'>添加资质参数</a></td>
      		<td class="center"><a href='javascript:void(0);' name='up' class="hidden"><span class="sysicon siUp">&nbsp;</span></a><a href='javascript:void(0)' name='down' class="hidden"><span class="sysicon siDown">&nbsp;</span></a></td>
      	</tr>
      	<tr>
      		<td class="operation">资质参数</td>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='qualityName'/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a href="javascript:void(0);" name="detilQualificationParam" class="hidden"><span class="sysicon siEdit">&nbsp;</span></a><a href="javascript:void(0);" name="save_Param"><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeQualificationParam"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="center">&nbsp;</td>
      		<td class="center">&nbsp;</td>
      		<td class="center">&nbsp;</td>
      		<td class="center"><a href='javascript:void(0);' name='up' class="hidden"><span class="sysicon siUp">&nbsp;</span></a><a href='javascript:void(0);' name='down' class="hidden"><span class="sysicon siDown">&nbsp;</span></a></td>
      	</tr>
      </tfoot>
</table>

</form>