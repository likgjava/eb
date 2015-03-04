<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bid_notice_submit.js"></script>
<div class="formLayout">
<span style="margin-left:5px">当前位置：采购预告 >> 提交采购预告</span>
	<form id="preNoticeForm">
	<div class="partContainers operationLog"><h5 id="preNotice"><span class="switch left11">提交采购预告</span></h5></div>
	<div id="preNoticeContent">
    <ul style="padding-left:7px;padding-top:10px">
      <li>
      	<label>采购预告标题</label>
      	<input type="text" name="title" class="required" />
      	<em>*</em>
      </li>
      <li>
      	<label>关键字</label>
      	<input type="text" name="keyword" class="required"/>
      	<em>*</em>
      </li>
      <li>
      	<label>标书购买开始时间</label>
      	<input type="text" name="beginTime" id="beginTime" class="required" style="width: 145px"/>
      	<em>*</em>
      </li>
       <li>
      	<label>标书购买截止时间</label>
      	<input type="text" name="endTime" id="endTime" class="required" style="width: 145px"/>
      	<em>*</em>
      </li>
      <li>
      	<label>上传文件</label>
      	<input type="file"/>
      </li>
      <li class="formTextarea">
      	<label>预告内容</label>
      	 <textarea id="input20" style="width:40%;height:105px;margin-top:3px;"></textarea>
      </li>
      <li>
	    <div class="conOperation" style="text-align:center">
	       	<button type="button" id="subBtn"><span>确定</span></button>
	   	 </div>
      </li>
      <li>
      	<div class="functionBtnDiv" style="padding-top:5px;padding-bottom:5px">
		    <button type="button" id="pre"><span>上一步</span></button><button type="button" id="next"><span>下一步</span></button>
	    </div>
      </li>
    </ul>
    </div>
    </form>
</div>
<div id="historyDiv"></div>
