<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/choose_goods_category.js"></script>



<!-- 已选分类连接 -->
<div class="conSearch" >
	<div id="CategorySelected">
		已选择的分类：
	</div>
</div>

<!-- 某个选中的分类下的商品 -->
<div >

<!--
<div class="functionBtnDiv right">
    <button type="button" id="goodsByCategoryDel"><span>删除</span></button>
</div>
-->
<table class="frontTableList" id="chooseGoodsByCategory">
        <thead>
            <tr>
                <th>商品名称</th>
                <th>商品型号</th>
                <th class="amount">市场价</th>
                <th class="amount">协议价</th>
                <th class="amount">折扣率(%)</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>  
        </tbody>
        <tfoot>
        </tfoot>
    </table>
</div>

        
        

 
