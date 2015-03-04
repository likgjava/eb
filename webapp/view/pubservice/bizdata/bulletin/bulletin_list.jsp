<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/bulletin/bulletin_list.js"></script>
 <div id="conTitle">
 	<div class="navCurrent ">
 	<a href="<%=request.getContextPath()%>/view/srplatform/portal/index.jsp" target="_self">首页</a><a href="javascript:void(0);" id="noticeMore">公告、公示</a><span id="bullTypeCN"></span>
 	</div>
 <div class="formTips attention">
	<ul><li><em>公告公示展示：</em>汇聚多平台公告公示信息，方便快捷，一目了然</li></ul>
 </div>	
  <div class="simpleSearch">
   <ul>
	<li><label> 关键字：</label>
		<input type="text" name="searchKeyContentMain" id="searchKeyContentMain" style="width:120px"/>
	</li>
	<li><label> 发布时间：</label>
		<input type="text" name="startTime" id="startTime" readonly="readonly" style="width:80px"/>&nbsp;至
		<input type="text" name="endTime" id="endTime" readonly="readonly" style="width:80px"/>
	</li>
	<li><label> 公告来源：</label>
		<select name="bulletinSrc" id="bulletinSrc" style="width:100px">
		<option value="">--请选择--</option>
		</select>
	</li>
	<li><button type="button" id="searchNewsContentMainBtn"><span>搜索</span></button></li>
  </ul>
 <input name="bullType" id="bullType" type="hidden" value="${param.bullType}"/>
</div>
 
 </div>
 
 <div id="conBody">

 <table class="frontTableList" id="provinceNoticeTable">
      <thead>
        <tr>
          <th class="left" style="width:500px">公告标题</th>
          <th class="right">时间</th>
        </tr>
      </thead>
        <tr>
        </tr>
        <tfoot class="hidden">
        <tr>
          <td></td>
          <td  style="text-align:left"></td>
          <td  style="text-align:center"></td>
         </tr>
        </tfoot>
  </table>
      <div class="flipPage" id="flipPage">
      
      </div>
      
  </div>
