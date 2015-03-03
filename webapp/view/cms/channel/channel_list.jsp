<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_list.js"></script>
<div class="treePage frameMainSub frameMs12 fullScreen ">
<div class="treeOutside frameMain">
	<div class="treeBtn">
		<ul>
			<li><a id="up" class="upMove" href="javascript:void(0)"><span></span></a></li>
			<li><a id="down" class="downMove"  href="javascript:void(0)"><span></span></a></li>
		</ul> 		
	</div>
	<div id="channelTree" class="treeContentDiv"></div>
	<div class="treeResize" ></div>
</div>
<div class="treeRight frameSub">
	<form id="channelSpanForm" method="post">
	<div id="channelDetail" >
		<div class="formLayout form2Pa detail">
			<div class="treeEditNav">
				<ul>
					<li id="addChannel" class="add"><a href="javascript:void(0)"><span>新增</span></a></li>
					<li id="updateChannel" class="edit"><a href="javascript:void(0)"><span>修改</span></a></li>
					<li id="deleteChannel" class="del"><a href="javascript:void(0)"><span>删除</span></a></li>
				</ul>
			</div>
			<h5><span>栏目</span></h5>
			<ul>
				<li>
			 		<label for="channelForm.parent"><spring:message code="channelForm.parent"/>：</label>
					<span id="parent.name">顶级栏目</span>
				</li>
				<li>
	 				<label for="channelForm.channelModel"><spring:message code="channelForm.channelModel"/>：</label>
					<span id="channelModel.name"></span>
				</li>
				<li>
	 				<label for="channelForm.name"><spring:message code="channelForm.name"/>：</label>
					<span id="name"></span>
				</li>
				<li>
	 				<label for="channelForm.chnlCode"><spring:message code="channelForm.chnlCode"/>：</label>
					<span id="chnlCode"></span>
				</li>
				<li>
	 				<label for="channelForm.chnlTemplate"><spring:message code="channelForm.chnlTemplate"/>：</label>
					<span id="chnlTemplate"></span>
				</li>
				<li>
	 				<label for="channelForm.contentTemplate"><spring:message code="channelForm.contentTemplate"/>：</label>
					<span id="contentTemplate"></span>
				</li>
				<li>
	 				<label for="channelForm.sort"><spring:message code="channelForm.sort"/>：</label>
					<span id="sort"></span>
				</li>
				<li>
	 				<label for="channelForm.isAlone"><spring:message code="channelForm.isAlone"/>：</label>
					<span id="isAloneCn"></span>
				</li>
				<li>
	 				<label for="channelForm.isQuery"><spring:message code="channelForm.isQuery"/>：</label>
					<span id="isQueryCn"></span>
				</li>
				<li>
	 				<label for="channelForm.display"><spring:message code="channelForm.display"/>：</label>
					<span id="displayCn"></span>
				</li>
				<li>
	 				<label for="channelForm.filePage"><spring:message code="channelForm.filePage"/>：</label>
					<span id="filePageCn"></span>
				</li>
				<li>
	 				<label for="channelForm.pageSize"><spring:message code="channelForm.pageSize"/>：</label>
					<span id="pageSize"></span>
				</li>
				<li>
	 				<label for="channelForm.checkCount"><spring:message code="channelForm.checkCount"/>：</label>
					<span id="checkCountCn"></span>
				</li>
				<li>
	 				<label for="channelForm.sortType"><spring:message code="channelForm.sortType"/>：</label>
					<span id="sortType"></span>
				</li>
				<li class="fullLine">
	 				<label for="channelForm.outUrl"><spring:message code="channelForm.outUrl"/>：</label>
					<span id="outUrl"></span>
				</li>
				<li class="fullLine">
	 				<label for="channelForm.specifiedPath"><spring:message code="channelForm.specifiedPath"/>：</label>
					<span id="specifiedPath"></span>
				</li>
				<li class="formTextarea eleDisable" style="height:100px">
		     		<label for="channelForm.chnlMemo"><spring:message code="channelForm.chnlMemo"/>：</label>
					<textarea name="chnlMemo" id="chnlMemo" class="" ></textarea><span class="eleRequired"></span>
	    	    </li>
			</ul>
			<div class="conOperation">
				<button type="button" name="createChannelStaticPages"><span>发布栏目静态页面</span></button>
				<button type="button" name="createChannelNewsStaticPages" ><span>发布内容静态页面</span></button>
				<button type="button" name="viewChannelContentDictionary" ><span>数据字典</span></button>
				<button type="button" name="viewChannelContentXML" ><span>xml格式</span></button>
				<button type="button" name="createChannelTimeTask" ><span>添加定时器</span></button>
			</div>
		</div>
		<div class="formLayout detail">
			<div class="treeEditNav">
				<ul><li id="closeChannelDataListSpan" class="enable"><a href="javascript:void(0)"><span>展开</span></a></li></ul>
			</div>
     		<h5><span>栏目模板数据</span></h5>
     		<ul id="ChannelDataListSpan" class="hidden"></ul>
		</div>
		
		<div class="formLayout detail">
			<div class="treeEditNav">
				<ul>
					<li id="closeChannelContentDataListSpan" class="enable"><a href="javascript:void(0)"><span>展开</span></a></li>
				</ul>
			</div>
     		<h5><span>内容模板数据</span></h5>
     		<ul id="ChannelContentDataListSpan" class="hidden"></ul>
		</div>
	</div>
	</form>
	
	<form id="channelForm"  method="post">
	<div id="channelFormDiv" class="eleHide">
		<div id="channelUpdateDiv" class="formLayout form2Pa detail">
			<div class="treeEditNav">
				<ul><li id="closeChannelInputForm" class="stop"><a href="javascript:void(0)"><span>关闭</span></a></li></ul>
			</div>
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="isLeaf" id="isLeaf" value="<c:out value="${param.isLeaf}"/>"/>
			<input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parent.objId}"/>"/>
	     	<h5><span>栏目</span></h5>
	        <ul id="ChannelInputForm">
		     	<li class="fullLine">
		     		<label for="channelForm.parent"><spring:message code="channelForm.parent"/>：</label>
					<span id="parent.name">顶级栏目</span>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.channelModel"><spring:message code="channelForm.channelModel"/>：</label>
					<select name="channelModel.objId" id="channelModel.objId" ></select>
					<span class="eleRequired"></span>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.name"><spring:message code="channelForm.name"/>：</label>
					<input type="text" name="name" id="name" class="required" />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.chnlCode"><spring:message code="channelForm.chnlCode"/>：</label>
					<input type="text" name="chnlCode" id="chnlCode" class="required" />
					<span class="eleRequired">*</span>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.chnlTemplate"><spring:message code="channelForm.chnlTemplate"/>：</label>
					<select name="chnlTemplate" id="chnlTemplate" ></select>
					<span class="eleRequired"></span>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.contentTemplate"><spring:message code="channelForm.contentTemplate"/>：</label>
					<select name="contentTemplate" id="contentTemplate" ></select>
					<span class="eleRequired"></span>
	    	    </li>
	    	    <li class="fullLine">
		     		<label for="channelForm.sort"><spring:message code="channelForm.sort"/>：</label>
					<input type="text" name="sort" id="sort" class="number" />
					<span class="eleRequired"></span>
	    	    </li>
		     	<li>
		     		<label for="channelForm.isAlone"><spring:message code="channelForm.isAlone"/>：</label>
					是<input type="radio" name="isAlone" id="" value="true">
					否<input type="radio" name="isAlone" id="" value="false">
	    	    </li>
		     	<li>
		     		<label for="channelForm.isQuery"><spring:message code="channelForm.isQuery"/>：</label>
					是<input type="radio" name="isQuery" id="" value="true">
					否<input type="radio" name="isQuery" id="" value="false">
	    	    </li>
		     	<li>
		     		<label for="channelForm.display"><spring:message code="channelForm.display"/>：</label>
					是<input type="radio" name="display" id="" value="true">
					否<input type="radio" name="display" id="" value="false">
	    	    </li>
		     	<li>
		     		<label for="channelForm.filePage"><spring:message code="channelForm.filePage"/>：</label>
					是<input type="radio" name="filePage" id="" value="true">
					否<input type="radio" name="filePage" id="" value="false">
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.pageSize"><spring:message code="channelForm.pageSize"/>：</label>
					<input type="text" name="pageSize" id="pageSize"  value="20" class="number" />
					<span class="eleRequired"></span>
	    	    </li>
		     	<li>
		     		<label for="channelForm.checkCount"><spring:message code="channelForm.checkCount"/>：</label>
					<select style="width: 100px" name="checkCount" id="checkCount">
						<option value="0">不需要审核</option>
						<option value="1">需一级审核</option>
						<option value="2">需二级审核</option>
						<option value="3">需三级审核</option>
						<option value="4">需四级审核</option>
						<option value="5">需五级审核</option>
					</select>
	    	    </li>
		     	<li>
		     		<label for="channelForm.sortType"><spring:message code="channelForm.sortType"/>：</label>
					<select style="width: 100px" name="sortType" id="sortType">
						<option value="1">时间倒序</option>
						<option value="2">时间顺序</option>
						<option value="3">访问量倒序</option>
						<option value="4">访问量顺序</option>
						<option value="5">按编号顺序</option>
					</select>
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.outUrl"><spring:message code="channelForm.outUrl"/>：</label>
					<input type="text" name="outUrl" id="outUrl" class="" size="60" />
	    	    </li>
		     	<li class="fullLine">
		     		<label for="channelForm.outUrl"><spring:message code="channelForm.specifiedPath"/>：</label>
					<input type="text" name="specifiedPath" id="specifiedPath" class="" size="60" value=""  />
	    	    </li>
				<li class="formTextarea" style="height:100px">
		     		<label for="channelForm.chnlMemo"><spring:message code="channelForm.chnlMemo"/>：</label>
					<textarea name="chnlMemo" id="chnlMemo" class="" ></textarea><span class="eleRequired"></span>
				</li>
			</ul>
			<div class="conOperation">
				<button type="button" name="createChannelStaticPages"><span>发布栏目静态页面</span></button>
				<button type="button" name="createChannelNewsStaticPages" ><span>发布内容静态页面</span></button>
				<button type="button" name="viewChannelContentDictionary" ><span>数据字典</span></button>
				<button type="button" name="viewChannelContentXML" ><span>xml格式</span></button>
			</div>
		</div>
		<div class="formLayout form2Pa detail">
			<div class="treeEditNav">
				<ul>
					<li id="addChannelDataRow" class="hidden add"><a href="javascript:void(0)"><span>新增</span></a></li>
					<li id="closeChannelDataList" class="enable"><a href="javascript:void(0)"><span>展开</span></a></li>
				</ul>
			</div>
			<h5><span>栏目模板数据</span></h5>
			<table id="ChannelDataList" class="hidden">
				<tr>
					<th style="text-align:center">数据来源</th>
					<th style="text-align:center">名称</th>
					<th style="text-align:center">查询条数</th>
					<th style="text-align:center">排序</th>
					<th style="text-align:center">查询</th>
					<th style="text-align:center">&nbsp;操&nbsp;作&nbsp;</th>
				</tr>
			</table>
		</div>
		
		<div class="formLayout form2Pa detail">
			<div class="treeEditNav">
				<ul>
					<li id="addChannelContentDataRow" class="hidden add"><a href="javascript:void(0)"><span>新增</span></a></li>
					<li id="closeChannelContentDataList" class="enable" ><a href="javascript:void(0)"><span>展开</span></a></li>
				</ul>
			</div>
			<h5><span>内容模板数据</span></h5>
			<table id="ChannelContentDataList" class="hidden">
		     	<tr>
					<th style="text-align:center">数据来源</th>
					<th style="text-align:center">名称</th>
					<th style="text-align:center">查询条数</th>
					<th style="text-align:center">排序</th>
					<th style="text-align:center">查询</th>
					<th style="text-align:center">操作</th>
				</tr>
			</table>
		</div>
		
		<div class="conOperation">
			<button type="button" id="channelSave"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="channelReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			<button type="button" id="channelReset" type="button" tabindex="20"><span><spring:message code="globe.reset"/></span></button>
		</div>
	</div>
</form>
</div>
</div>