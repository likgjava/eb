<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/gift_series_list.js"></script>
<style>
.befSave {background-color:#dfd}
</style>

<div class="formTips attention">
	<ul>
		<li>新增礼品系列请点击<span class="sysicon siAdd"><a id="addGiftSeriesCasBtn" href="javascript:void(0);"><strong>添加礼品系列</strong></a></span></li>
	</ul>
</div>
<form id="giftSeriesForm">
	<table class="frontTableList" id="giftSeriesTable">
      <thead>
        <tr>
          <th class="left">系列名称</th>
          <th class="left">操作</th>
          <th class="left">添加子系列</th>
          <th class="left">排序</th>
        </tr>
      </thead>
      <tbody id="giftSeriesTbody">
      </tbody>
      <tfoot id="giftSeriesTfoot"  class="hidden">
      	<tr>
      		<td class="r"><span class="sysicon" name="closeOrOpen">&nbsp;</span><input type='text' name='giftSeriesName' value=''/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a name="save_GiftSeries" href="javascript:void(0);" ><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeGiftSeries"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="operation"><a href='javascript:void(0)' class="hidden" name='addGiftSeries'>添加子系列</a></td>
      		<td class="center">
      			<a name="down" href='javascript:void(0);' class="hidden"><span class="sysicon siDown">&nbsp;</span></a>
      			<a name="up" href='javascript:void(0);' class="hidden"><span class="sysicon siUp">&nbsp;</span></a>
      		</td>
      	</tr>
      	<tr>
      		<td class="r"><span class="sysicon " name="closeOrOpen">&nbsp;</span><input type='text' name='giftSeriesName' value=''/><input type='hidden' name='objId' value=''/><input type='hidden' name='parent.objId' value=''/><br/><span class="eleRequired"></span></td>
      		<td class="r"><a name="save_GiftSeries" href="javascript:void(0);" ><span class="sysicon siSave ">&nbsp;</span></a><a href="javascript:void(0);" name="removeGiftSeries"><span class="sysicon siExit">&nbsp;</span></a></td>
      		<td class="center">&nbsp;</td>
      		<td class="center">
      			<a name="down" href='javascript:void(0);' class="hidden"><span class="sysicon siDown">&nbsp;</span></a>
      			<a name="up" href='javascript:void(0);' class="hidden"><span class="sysicon siUp">&nbsp;</span></a>
      		</td>
      	</tr>
      </tfoot>
	</table>
</form>
