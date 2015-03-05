<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/business/forum_topic_list.js"></script>

<!-- 搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<div id="areaForumTopic">
		<form id="forumTopicSearchForm" >
			<ul>
				<li>
					<label for="title">帖子主题：</label>
					<input type="text" name="title" value="">
					<input type="hidden" name="title_op" value="like">
				</li>
				<li>
					<label for="community.communityName">所属社区：</label>
					<input type="text" name="community.communityName" value="">
					<input type="hidden" name="community.communityName_op" value="like">
				</li>
				<li>
					<label for="orginfo.orgName">发表机构：</label>
					<input type="text" name="orginfo.orgName">
					<input type="hidden" name="orginfo.orgName_op" value="like">
				</li>
				<li>
					<label>置顶：</label>
					<select name="isTop" id="isTop">
						<option></option>
						<option value="true">已置顶</option>
						<option value="false">未置顶</option>
					</select>
					<input type="hidden" name="isTop_op" value="=">
				</li>
				<li>
					<label>精华：</label>
					<select name="isElite" id="isElite">
						<option></option>
						<option value="true">已精华</option>
						<option value="false">未精华</option>
					</select>
					<input type="hidden" name="isElite_op" value="=">
				</li>
				<li>
					<label>显示：</label>
					<select name="isShow" id="isShow">
						<option></option>
						<option value="true">已显示</option>
						<option value="false">未显示</option>
					</select>
					<input type="hidden" name="isShow_op" value="=">
				</li>
				<li class="operationBtnDiv">
					<button id = "forumTopicSearch" type="button"><span>查询</span></button>
				</li>
			</ul>
		</form>
	</div>
	<div id="areaForumReply">
		<form id="forumReplySearchForm" >
			<ul>
				<li>
					<label for="topic.title">帖子主题：</label>
					<input type="text" name="topic.title" value="">
					<input type="hidden" name="topic.title_op" value="like">
				</li>
				<li>
					<label for="orgName">发表机构：</label>
					<input type="text" name="orginfo.orgName">
					<input type="hidden" name="orginfo.orgName_op" value="like">
				</li>
				<li>
					<label>显示：</label>
					<select name="isShow" id="isShow">
						<option></option>
						<option value="true">已显示</option>
						<option value="false">未显示</option>
					</select>
					<input type="hidden" name="isShow_op" value="=">
				</li>
				<li class="operationBtnDiv">
					<button id = "forumReplySeach" type="button"><span>查询</span></button>
				</li>
			</ul>
		</form>
	</div>
</div>

<div class="formTips attention" id="topicReplyTips">
	<ul>
		<li>			
			批量删除帖子请点击：   &nbsp;&nbsp;<span class="sysicon"><a href="javascript:void(0);" id="removeForumTopicReply"><strong>帖子批量删除</strong></a></span>
		</li>
	</ul>
</div>

<div id="epsTabs" class="epsTabs">
	<ul>
		<li>
			<a href="#topicListInfo" id = "tabs_topicManager" class="refreshData"><span>主题管理</span></a>
		</li>
		<li>
			<a href="#topicReplyListInfo" id = "tabs_topicReplyManager" class="refreshData"><span>帖子管理</span></a>
		</li>
	</ul>
	
	<div id="topicListInfo">
		<table class="frontTableList" id="forumTopicList">
	      <thead>
	        <tr>
	          <th class="left omission">帖子主题</th>
	          <th class="left omission">所属社区</th>
	          <th class="left omission">发表机构</th>
	          <th class="left">回帖数</th>
	          <th class="left">更新时间</th>
	          <th class="operation">操作</th>
	        </tr>
	      </thead>
      	  <tbody>
      	  </tbody>
		</table>
	</div>
	
	<div id="topicReplyListInfo">
		<table class="frontTableList" id="forumReplyList">
	      <thead>
	        <tr>
	        	<th class="left">帖子主题</th>
				<th class="left" >发表机构</th>
				<th class="left">发表时间</th>
				<th class="operation">操作</th>
	        </tr>
	      </thead>
      	  <tbody>
      	  </tbody>
		</table>
	</div>
</div>