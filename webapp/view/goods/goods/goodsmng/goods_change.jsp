<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GoodsChangeForm" method="post" modelAttribute="oldGoods"> 
	<input type="hidden" id="goodsId" name="goodsId" value="${oldGoods.objId}"/>
        
<c:choose>
	<c:when test="${!empty goodsChangeList && fn:length(goodsChangeList) > 0}">
		<div class="formTips light">
			<ul>
				<li class="big">
				<em>当前状态：</em>已提交变更信息，请等待审核...
				</li>
				<li>变更信息如下：</li>
				<c:forEach var="changeGoods" items="${goodsChangeList}" varStatus="status">
					<li>
						<c:if test="${changeGoods.modifyType=='purCategory'}">
							<strong>商品品目：</strong>
						</c:if>
						<c:if test="${changeGoods.modifyType=='goodsClass'}">
							<strong>商品分类：</strong>
						</c:if>
						<c:if test="${changeGoods.modifyType=='goodsBrand'}">
							<strong>商品品牌：</strong>
						</c:if>
						${changeGoods.oldValueName}<font color='red'> 变更为  >> </font>${changeGoods.newValueName}
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>	
	<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>以下信息修改后需要经过审核方生效。
			变更商品信息请点击：<span class="sysicon siAdd"><a href="javascript:void(0);" onclick="GoodsChangeForm.changeGoods();return false;"><strong>变更</strong></a></span>
		</li>
	</ul>
    </div>
	<div class="formLayout  form2Pa">
		<table id='changeTable'>
			<thead>
				<tr>
					<td><strong>当前信息</strong></td>
					<td width="50%"><a href="javascript:void(0);" onclick="GoodsChangeForm.changeGoods();return false;">变更为</a></td>
				</tr>
			</thead>
			<tbody>
		    	<tr>
		            <td><label>商品品目：</label>
		            	<span id="productName_old">${oldGoods.purCategory.categoryName}</span>
		            </td>
		            
		            <td>
		            	<span id="categoryIdDiv" name="inputSpan" modType="productName" oldVal="${oldGoods.purCategory.objId}##||##${oldGoods.purCategory.categoryName}">
			            	<input type="hidden" id="purCategory" readonly="readonly" name="purCategory" maxlength="50" size="60"/>
			            	<span id="purCategory_new"></span> 
		            	</span>
	            	</td>
		        </tr>
		        
		        <tr>
		            <td><label>商品分类：</label>
		            	<span id="goodsCode_old">${oldGoods.goodsClass.goodsClassName}</span>
		            </td>
		            
		            <td>
			        	<span id="classIdDiv" name="inputSpan" modType="productCode" oldVal="${oldGoods.goodsClass.objId}##||##${oldGoods.goodsClass.goodsClassName}">
			        		<input type="hidden" id="goodsClass" readonly="readonly" name="goodsClass" maxlength="50" size="60"/>
			        		<span id="goodsClass_new"></span> 
			        	</span>
			        </td>
		        </tr>
		        
		        <tr>
		            <td><label>商品品牌：</label>
		            	<span id="goodsCode_old">${oldGoods.goodsBrand.brandName}</span>
		            </td>
		            
		            <td>
			        	<span id="brandIdDiv" name="inputSpan" modType="productCode" oldVal="${oldGoods.goodsBrand.objId}##||##${oldGoods.goodsBrand.brandName}">
			        		<input type="hidden" id="goodsBrand" readonly="readonly" name="goodsBrand" maxlength="50" size="60"/>
			        		<span id="goodsBrand_new"></span> 
			        	</span>
			        </td>
		        </tr>
		        
		        <tr><td></td><td></td></tr>
		        </tbody>
	    </table>
	</div>
	
	<div class="conOperation">
		<button type="button" id="submit"><span>提交</span></button>
		<button type="button" id="closeG"><span>关闭</span></button>
	</div>
	</c:otherwise>
</c:choose>      
</form:form>		

<script>
var GoodsChangeForm={};

GoodsChangeForm.changeGoods=function(){
	$.epsDialog({
		id:'goodsChangeSelectDiv',
        title:'商品变更',
        url:$('#initPath').val()+'/view/goods/goods/goodsmng/goods_change_select.jsp',
        width: 600,//默认宽度
		height: 500//默认高度
    })
}

$(document).ready(function(){
	//提交
	$('#submit').click(function(){
		if($('#goodsBrand').val()==""){
			alert('您没有录入变更信息');
		}else {
			var changeGoodsArray = [];
			var res = true;
			$('span[name=inputSpan]').each(function(i,n){
				if($(n).find('input').val()!='') {
					res = false;
					var changeGoods={
							goods:{objId:$('#goodsId').val()},
							modifyType:$(n).find('input').attr("id"),
							oldValue:$(n).attr("oldVal"),
							newValue:$(n).find('input').val(),
							auditStatus:'01'
					}
					changeGoodsArray.push(changeGoods);
				}
			})
			if(res) {
				alert('请填写变更信息');
				return;
			}
			if(confirm('确认要提交变更信息吗?')){
				var changeText = $('#purCategory_new').text()+","+$('#goodsClass_new').text()+","+$('#goodsBrand_new').text();
				$.getJSON($('#initPath').val()+'/GoodsChangeController.do?method=saveChange',{changeGoodsStr:JSON.stringify(changeGoodsArray)},function(json){
					if(json.failure){alert(json.result);return;}
					alert('提交成功！');
					$("#goodsChangeDiv").find('.epsDialogClose').trigger('click');
					//修改已选类目值的提示
					$('li[id=changePurClassLi]').append('已变更为：'+changeText+',待审核通过后生效');
				});
			}
		}
	})
	//关闭
	$("#closeG").click(function(){
		$("#goodsChangeDiv").find('.epsDialogClose').trigger('click');
	})
	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/GoodsController.do?method=getGoodsHistory&id="+$("#goodsId").val();
		$.epsDialog({
			id:'goodsChangeHistoryDiv',
	        title:'商品变更历史',
	        url:url,
	        width:600,
	        height:300
	    }); 
	})
})
</script>