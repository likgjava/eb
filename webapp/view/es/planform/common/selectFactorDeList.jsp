<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/selectFactorDeList.js"></script>
<style>
/*--------------
	页面搜索
----------------*/
.conSearch { padding:5px; margin:0 10px; }

/*三列布局*/
.frameParallel {  overflow:hidden; margin:10px;}
.frameParallel ul{ padding:10px;}
.frameParallel li{ list-style:none;}
.frameParallel .framePaCon { float:left; min-height:300px;background:#e8eef5;}
.frame3Pa .framePaCon { width:40%;}
.frame3Pa .framePaCon2 { width:80px; float:left;min-height:300px; text-align:center; background:#999; position:relative;} 
  /*按钮居中*/
  .frame3Pa .framePaCon2 ul{ position:absolute; top:40%; left:2px;}
/*居中按钮*/
.conOperation { text-align:center;margin-top: 10px;}
</style>
<input type="hidden" id="_select_type" value="${param.selectType}"></input>
<input type="hidden" id="_return_id" value="${param.returnId}"></input>
<input type="hidden" id="_return_name" value="${param.returnName}"></input>
<input type="hidden" id="_epsDialog_id" value="${param.epsDialogId}">
<div class="frameMainSub frameMs12 fullScreen ">
	<div class="accordion" style="width:199px;float:left;margin-right:5px;">
		<div class="accordionHeader">
			<h2><span></span><a></a></h2>
		</div>
		<div class="accordionContent">
			<div id="menu_tree" style="height: 420px"></div>
		</div>
	</div>
	<div id="rightOptionDiv" style="500px;float:left;">
		<div id="factor_type_info"></div>
		<div id="selectFactorIsRadioList"></div>
		<div id="selectFactorIsCheckboxList">
			<div class="frameParallel frame3Pa">
			    <div class="framePaCon">
			      <ul>
			        <li><select size="20" style="width:200px;height:290px" id="toSelect" multiple="true"></select></li>
			      </ul>
			    </div>
			    <div class="framePaCon2">
			      <ul>
			        <li><button id="ADD"><span>添加&nbsp;&gt;&gt;</span></button></li>
			        <li></li>
			        <li><button id="DELETE"><span>&lt;&lt;&nbsp;移除</span></button></li>
			      </ul>
			    </div>
			    <div class="framePaCon">
			      <ul>
			        <li><select size="20" style="width:200px;height:290px" id="alreadySelect" multiple="true"></select></li>
			      </ul>
			   </div>
			  </div>
			  <div class="conOperation"><button id="OK"><span>确定</span></button>&nbsp;<button id="CLEAR"><span>清空</span></button></div>
		</div>
	</div>
</div>


