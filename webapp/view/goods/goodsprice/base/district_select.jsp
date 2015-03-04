<!--
 * 版权信息： 政采软件版权所有，未经珠海市政采软件技术有限公司的书面同意，不得拷贝，传播本文件及其相关信息的任何内容。
 * 项目：     政府采购超源业务平台
 * JDK版本：  1.5
 * 版本：     3.0
 * 说明：     本文件包含***的代码。
 * 修订历史：
 * 序号：     1
 * 日期：     2010-1-18
 * 修改人：   Administrator    
 * 修改说明：（为什么修改，修改了什么）
 -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsprice/base/district_select.js"></script>
<style>
   .frameMain {
	width:185px;
	position:relative;
	overflow: auto;
	border:1px solid #B6C0C9;
	padding:10px;
	background-color: #FFF;
	background-image: url(../img/text-bg.gif);
	background-repeat: repeat-x;
	background-position: left top;
}
	.frameSub {
	margin:0px;
	margin-left:212px;
	overflow:auto;
	border:1px solid #B6C0C9;
	background-color: #FFF;
	background-image: url(../img/text-bg.gif);
	background-repeat: repeat-x;
	background-position: left top;
	padding:8px;	
}
</style>
<div class="frameMainSub frameMS12 ">
    <div class="treeOutside frameMain" style="height:362px;">
         <div id="districtTreeForm" class="treeContentDiv"></div>
    </div>

    <div class="treeRight frameSub" id="treeRight">
    	<div class="formLayout">
        <form id="districtTreeForm" action="" method="post">
        <h4><span>选择代理区域</span></h4>
        <ul>
        	<div class="functionBtnDiv">
                <button type="button" class="functionBtnDiv" id="removeFromList"><span>移除</span></button>
                <button type="button" class="functionBtnDiv" id="removeAllFromList"><span>清空</span></button>
            </div>
        	<li>
                <label for="districtNameSelect">代理区域名称</label>
                <select size="14" style="width:120px;height:200px;" id="districtNameSelect" multiple="true"></select>
                <span class="eleRequired">*</span>
            </li>
        </ul>
          <div class="operationBtnDiv" id="goodsTypeSelEditBtnDiv">
      	    <button type="button" class="largeBtn" id="returnPurCategoryBtn"><span>保存</span></button>
      	    <button type="button" class="largeBtn" id="cancelItemBtn"><span>关闭</span></button>
          </div>
        </form>
        </div>
     </div>  
</div>
