<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/es/planform/taskplan/SelectGovernmentLinkerBox.js"></script>
<style>
/*--------------
	页面搜索
----------------*/
.conSearch { padding:5px; margin:0 10px; }

/*三列布局*/
.frameParallel {  overflow:hidden; margin:10px;}
.frameParallel ul{ padding:10px;}
.frameParallel li{ list-style:none;}
.frameParallel .framePaCon { float:left; min-height:200px;background:#e8eef5;}
.frame3Pa .framePaCon { width:38%;}
.frame3Pa .framePaCon2 { width:20%; float:left;min-height:200px; text-align:center; background:#999; position:relative;} 
  /*按钮居中*/
  .frame3Pa .framePaCon2 ul{ position:absolute; top:40%; left:2px;}
/*居中按钮*/
.conOperation { text-align:center;}
</style>
<div>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<div class="frameParallel frame3Pa">
    <div class="framePaCon">
      <ul>
        <li><select size="20" style="width:130px;height:180px" id="toSelect" multiple="true"></select></li>
      </ul>
    </div>
    <div class="framePaCon2">
      <ul>
        <li><button id="ADD" style="font-size: 12px;"><span style="font-size: 12px;">添加&nbsp;&gt;</span></button></li>
        <li></li>
        <li><button id="DELETE"><span>&lt;&nbsp;移除</span></button></li>
      </ul>
    </div>
    <div class="framePaCon">
      <ul>
        <li><select size="20" style="width:130px;height:180px" id="alreadySelect" multiple="true"></select></li>
      </ul>
   </div>
  </div>
  <div class="conOperation"><button id="OK"><span>确定</span></button>&nbsp;<button id="CLEAR"><span>清空</span></button></div>
</div>