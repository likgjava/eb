<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/pubservice/css/goods_list.css"/>

<form id="GoodsChangeSelect" name="GoodsChangeSelect" method="post">
<div class="partContainers">
	<div class="conSearch">
		<h4><span><spring:message code="globe.query" /></span></h4>
		<ul>
			<li>
                <label for="productCode">类目查询：</label>
                <input type="text" name="searchName" id="searchName">
                <button type="button" id="search"><span><spring:message code="globe.query" /></span></button>
	        </li>
		</ul>
	</div>
	<div>
		<ul id="searchResult">
		</ul>
	</div>
	
	<div id="selectResult" class="formLayout form3Pa">
		<div id="noData" class="nodata hidden">没有查询到品目,是不是经营范围没有设置？<a href="javascript:void(0);" onclick="$('#conBody').loadPage('<c:choose><c:when test="${currentOrgStatus=='01'}">OrgInfoModifyController.do?method=toModifyOrg</c:when><c:otherwise>ExOrgInfoController.do?method=toEntBaseInfo</c:otherwise></c:choose>');return false;">点此设置经营范围</a></div>
		<ul class="goodsSel">
			<li class="framePaCon">
			<h5><span>选择采购品目</span></h5>
				<select size="10" id="goodscategory"></select>
			</li>
			<li class="framePaCon">
			<h5><span>选择商品分类 </span></h5>
				<select size="10" id="goodsclass"></select>
			</li>
			<li class="framePaCon">
			<h5><span>选择商品品牌</span></h5>
				<select size="10" id="goodsbrand"></select>
			</li>
		</ul>
  	</div>
  	<div class="conOperation">
			<button id="sure" type="button" tabindex="18"><span>确定</span></button>
			<button id="close" type="button" tabindex="18"><span>关闭</span></button>
	</div>
</div>
</form>

<script>
var GoodsChangeSelect={};

//根据查询到的类目去添加商品
function toChangeGoods(categoryId,classId,brandId,categoryname,classname,brandname) {
	//回填商品类目ID
	$('#purCategory').val(categoryId+"##||##"+categoryname);
	$('#goodsClass').val(classId+"##||##"+classname);
	$('#goodsBrand').val(brandId+"##||##"+brandname);
	
	//回填商品类目名称
	$('#purCategory_new').text(categoryname);
	$('#goodsClass_new').text(classname);
	$('#goodsBrand_new').text(brandname);
	
	$("#goodsChangeSelectDiv").find('.epsDialogClose').trigger('click');
}

$(document).ready(function(){	
	//品目
	var option = {parameter:"objId",textfield:"categoryName",isfirstselect:"no"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId","isfirstselect":"yes"};
	$('#goodscategory').FillOptions($('#initPath').val()+'/GoodsClassCategoryController.do?method=getCategoryByMainProducts', option ,function(datas){
		if(datas.result.length==0) {//没有查询到品目，提示设置经营范围
			$('#noData').show();
		}
	});
	//分类
	$('#goodscategory').CascadingSelect($('#goodsclass'),$('#initPath').val()+'/GoodsClassCategoryController.do?method=getGoodsClassByCategory',{parameter:"categoryId",textfield:"goodsClassName",isfirstselect:"no"},function(datas){});
	//品牌
	$('#goodsclass').CascadingSelect($('#goodsbrand'),$('#initPath').val()+'/GoodsClassBrandController.do?method=getBrandsListByClass',{parameter:"classId",textfield:"brandName",isfirstselect:"no"},function(datas){});

	//查询
	$('#search').click(function(){
		if(!$('#searchName').val()) {
			alert('请输入关键字查询');
			return;
		}
		$.getJSON($('#initPath').val()+"/GoodsClassCategoryController.do?method=getProductsBySearchName", {"keyWords":native2ascii($('#searchName').val())} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			var htm = '';
			$.each(json.result,function(i,n){
				htm += '<li class="fullLine" style="margin-left:20px;"><a onclick="javascript:toChangeGoods(\''+n[0]+'\',\''+n[2]+'\',\''+n[4]+'\',\''+n[1]+'\',\''+n[3]+'\',\''+n[5]+'\')" href="javascript:void(0);">'+n[1] + ">>" + n[3] + ">>" + n[5]+'</a></li>';
			})
			if(htm == '') {
				htm += '<li class="fullLine" style="margin-left:20px;">对不起,没有您要找的类目或品牌</li>';
				$('#selectResult').removeClass('hidden');
			} else {
				$('#selectResult').addClass('hidden');
			}
			$('#searchResult').empty().append(htm);
		});
	})
	
	//去添加商品
	$("#sure").click(function(){
		if($('#goodscategory').val() == null || $('#goodscategory').val() == ""){
			alert("请选择品目");
			return;
		}
		if($('#goodsclass').val() == null || $('#goodsclass').val() == ""){
			alert("请选择商品分类");
			return;
		}
		if($('#goodsbrand').val() == null || $('#goodsbrand').val() == ""){
			alert("请选择商品品牌");
			return;
		}

		//回填商品类目ID
		$('#purCategory').val($('#goodscategory').val()+"##||##"+$('#goodscategory').getSelectedText());
		$('#goodsClass').val($('#goodsclass').val()+"##||##"+$('#goodsclass').getSelectedText());
		$('#goodsBrand').val($('#goodsbrand').val()+"##||##"+$('#goodsbrand').getSelectedText());
		
		//回填商品类目名称
		$('#purCategory_new').text($('#goodscategory').getSelectedText());
		$('#goodsClass_new').text($('#goodsclass').getSelectedText());
		$('#goodsBrand_new').text($('#goodsbrand').getSelectedText());

		$("#goodsChangeSelectDiv").find('.epsDialogClose').trigger('click');
	})

	//点击关闭
	$("#close").click(function(){
		$("#goodsChangeSelectDiv").find('.epsDialogClose').trigger('click');
	})
})
</script>
