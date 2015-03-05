<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigtypeList.js"></script>
<div class="frameMainSub frameMs12 fullScreen ">
	<div class="frameMain">
		<div class="epsContentMenu"  id="rightClickMenu">
			<ul>
	    		<li id="add">
	      			<a href="#" class="add"><span></span>新增系统配置类型</a>
		    	</li>
		    	<li id="authorize">
		      		<a href="#" class="authorize"><span></span>修改系统配置类型</a>
		    	</li>
		    	<li id="del">
		      		<a href="#" class="del"><span></span>删除系统配置类型</a>
		    	</li>
		    	<li id="exportStaticClass">
		      		<a href="#" class=""><span></span>导出常量类</a>
		    	</li>
		    	<li id="exportPropertysFile">
		      		<a href="#" class=""><span></span>导出属性文件</a>
		    	</li>
		    	<li id="exportSQL">
		      		<a href="#" class=""><span></span>导出SQL</a>
		    	</li>
			</ul>
		</div>
		<div id="sysConfigTypeTree"></div>
	</div>
	<div class="frameSub" id="treeRight">
		<!-- 公有数据  start-->
		<input type="hidden" name="parentObjId" id="parentObjId" value=""/>
		<input type="hidden" name="curObjId" id="curObjId" value=""/>
		<!-- 当前系统配置类型对应的类型路径(系统配置条目保存时减少数据加载，所以在这公有数据上新增) -->
		<input type="hidden" name="curTypePath" id="curTypePath" value="${curTypePath }"/>
		<!-- 公有数据  end-->
  		<div id="tabInfo">
    		<ul>
      			<li >
        			<a href="#tabform" id="sysconfigTypeDetailView"><span>系统配置类型详细信息</span></a>
      			</li>
      			<li id="sysconfigItemListViewLi">
        			<a href="#tabform" id="sysconfigItemListView"><span>系统配置条目信息</span></a>
      			</li>
    		</ul>
    		<div id="tabform">
    		</div>
  		</div>
	</div>
</div>