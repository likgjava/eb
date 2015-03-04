<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsclass/goodsclassparamtype_main_manage.js"></script>
<link href="<%=request.getContextPath()%>/view/resource/skin/bizplatform/css/orginfo_add.css" rel="stylesheet" type="text/css" />
<style>
.befSave {background-color:#dfd}
</style>
<input type="hidden" id="importGoodsClassIds" value="" />
<input type="hidden" id="importGoodsClassNames" value="" />
<input type="hidden" id="importSuccess" value="0" />
<div class="treePage frameMainSub frameMs12 fullScreen">
	<div class="treeOutside frameMain">
          <!--树-->
      <div id="goodsClassParamTypeTreeForm" class="treeContentDiv"></div>
	  <div class="treeResize" ></div>
    </div>
<div class="treeRight frameSub" id="treeRight">
<div class="formTips attention">
	<ul>
		<li>新增参数请点击
			<span class="sysicon siAdd"><a id="addGoodsClassParamTypeBtn" href="javascript:void(0);"><strong>添加参数</strong></a></span>
			<span class="sysicon siAdd hidden"><a id="importGoodsClassParamTypeBtn" href="javascript:void(0);"><strong>导入参数</strong></a></span>
		</li>
	</ul>
</div>
	<table class="frontTableList" id="goodsClassParamTable">
      <thead>
        <tr>
          <th class="center">参数名称</th>
          <th class="center">操作</th>
          <th class="center">添加子参数</th>
          <th class="center">排序</th>
        </tr>
      </thead>
      <tbody id="goodsClassParamTbody">
      </tbody>
      <tfoot id="goodsClassParamTfoot"  class="hidden">
      	<tr>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='paramName'/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><input type='hidden' name='goodsClass.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a href="javascript:void(0);" name="detilGoodsClassParamType" class="hidden"><span class="sysicon siEdit">&nbsp;</span></a><a name="save_GoodsClassParamType" href="javascript:void(0);" ><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeGoodsClassParamType"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="operation"><a href='javascript:void(0)' class="hidden" name='addGoodsClassParam'>添加子参数</a></td>
      		<td class="center">
      			<a name="down" href='javascript:void(0);' class="hidden"><span class="sysicon siDown">&nbsp;</span></a>
      			<a name="up" href='javascript:void(0);' class="hidden"><span class="sysicon siUp">&nbsp;</span></a>
      		</td>
      	</tr>
      	<tr>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='paramName'/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><input type='hidden' name='goodsClass.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a href="javascript:void(0);" name="detilGoodsClassParam" class="hidden"><span class="sysicon siEdit">&nbsp;</span></a><a name="save_GoodsClassParam" href="javascript:void(0);"><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeGoodsClassParam"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="center">&nbsp;</td>
      		<td class="center">
      			<a name="down" href='javascript:void(0);' class="hidden"><span class="sysicon siDown">&nbsp;</span></a>
      		    <a name="up" href='javascript:void(0);' class="hidden"><span class="sysicon siUp">&nbsp;</span></a>
      		</td>
      	</tr>
      </tfoot>
	</table>
  </div>
</div>

