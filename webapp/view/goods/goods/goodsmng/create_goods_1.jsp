<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>创建商品-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/goods/css/publish_category.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:800px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
			<div class="grid-c" id="Content">
				<div class="publish-title type-b">
					<h2><span></span></h2>
					<div class="formTips smallLight hint"><strong>提醒：</strong>商品发布虚假信息，将不被审核通过，多次发布虚假商品，机构信息将进入黑名单。</div>
				</div>
				<div id="noData" class="nodata hidden">没有查询到品目,是不是经营范围没有设置？<a href="javascript:void(0);" onclick="$('#conBody').loadPage('<c:choose><c:when test="${currentOrgStatus=='01'}">OrgInfoModifyController.do?method=toModifyOrg</c:when><c:otherwise>ExOrgInfoController.do?method=toEntBaseInfo</c:otherwise></c:choose>');return false;">点此设置经营范围</a></div>
				<div class="goods-box" id="category-search">
					<span class="rc-tp"><span></span></span>
					<div class="bd">
						<form name="categorySearchForm" method="post" action="">
							<fieldset>
								<legend>类目搜索</legend>
								<div>
									<label for="J_IptCateSearch">类目搜索：</label>
									<input value="" autocomplete="off" accesskey="s" name="keyword" id="J_IptCateSearch" />
									<span>请输入商品名称、品牌型号或类目属性名称，快速找到正确类目。</span>
									<button class="ic-btn ic-btn-grey" id="J_BtnCateSearch" type="button">搜索</button>
								</div>
							</fieldset>
						</form>
					</div>
					<span class="rc-bt"><span></span></span>
				</div>
				
				<div class="goods-box hidden" id="search-result">
					<span class="rc-tp"><span></span></span>
					<div class="bd">
						<ul id="J_LstSearchResult"></ul>
					</div>
					<span class="rc-bt"><span></span></span>
					<a title="取消" id="cancel" class="cancel" href="#"><span>取消</span></a>
				</div>
				
				<div class="goods-box" id="category-cascading">
					<span class="rc-tp"><span></span></span>
					<div class="bd">
						<div class="cascading-container">
							<ol style="left: 0px;">
								<li class="root">
									<ul id="goodscategory"></ul>
								</li>
								<li>
									<ul id="goodsclass"></ul>
								</li>
								<li>
									<ul id="goodsbrand"></ul>
								</li>
							</ol>
						</div>
						<span class="prev"><span>无上一级</span></span>
						<span class="next"><span>无下一级</span></span>
					</div>
					<span class="rc-bt"><span></span></span>
				</div>
				
				<!-- 记录当前选择的类目ID -->
				<input type="hidden" id="goodscategoryId" />
				<input type="hidden" id="goodsclassId" />
				<input type="hidden" id="goodsbrandId" />
				
				<div class="category-extra J_DetectTrigger" id="divMySelect" >
					<dl style="height:18px;">
						<dt>您当前选择的是：</dt>
						<dd>
							<ol id="mySelected" class="category-path"></ol>
						</dd>
						<dd  style="clear:left;"></dd>
					</dl>
					<span class="arrow up" ></span>
				</div>
				
				<div class="go-publish">
					 <button id="J_BtnPublish" class="disabled" disabled="disabled" type="submit">好了，去发布商品</button>
				</div>
			</div>
			
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 

<script type="text/javascript">
var pubGoods = {};

//改变样式
pubGoods.changeClass=function(ulId,ctrl) {
	//移除所有其他选中样式，再给选中的li增加选中样式
	$('#'+ulId+'').find('li').each(function(i,n){
		if($(n).attr('class').indexOf('selected') >= 0) {
			$(n).removeClass('selected');
		}
	})
	$(ctrl).addClass('selected');

	//控制发布商品按钮
	if(ulId=="goodsbrand" || ulId=="J_LstSearchResult") {
		$('#J_BtnPublish').removeClass("disabled");
		$('#J_BtnPublish').removeAttr("disabled");

		//记录当前选择的类目ID
		if(ulId="J_LstSearchResult") {
			$('#goodscategoryId').val($(ctrl).attr('categoryId'));
			$('#goodsclassId').val($(ctrl).attr('classId'));
			$('#goodsbrandId').val($(ctrl).attr('brandId'));
		}
		  
	} else {
		$('#J_BtnPublish').addClass("disabled");
		$('#J_BtnPublish').attr("disabled","disabled");
	}

	//选中品目时，清空品牌和当前选择的类目
	if(ulId=="goodscategory") {
		$('#goodsbrand').empty();
	}
}

//根据品目获取分类
pubGoods.getGoodsClassByCategory=function(ctrl,categoryId){
	pubGoods.changeClass("goodscategory",ctrl);
	$.getJSON($('#initPath').val()+"/GoodsClassCategoryController.do?method=getClassByCategory", {"categoryId":categoryId} , function(json){
		var htm = '';
		$.each(json.result,function(i,n){
			if(n[2] > 0) {
				htm += '<li class="parent" id="'+n[0]+'" onclick="pubGoods.getBrandsListByClass(this,\''+n[0]+'\');"><span>'+n[1]+'</span></li>';
			} else {
				htm += '<li id="'+n[0]+'" onclick="pubGoods.getBrandsListByClass(this,\''+n[0]+'\');"><span>'+n[1]+'</span></li>';
			}
		})
		$('#goodsclass').empty().append(htm);
	});

	//记录当前选择的类目ID
	$('#goodscategoryId').val(categoryId);

	//我选择的类目
	pubGoods.changeMySelectedText();
}

//根据分类获取品牌
pubGoods.getBrandsListByClass=function(ctrl,classId){
	pubGoods.changeClass("goodsclass",ctrl);
	$.getJSON($('#initPath').val()+"/GoodsClassBrandController.do?method=getBrandsListByClass", {"classId":classId} , function(json){
		var htm = '';
		if(json.result.length > 0) {
			$.each(json.result,function(i,n){
				htm += '<li id="'+n.objId+'" onclick="pubGoods.selectBrand(this,\''+n.objId+'\');"><span>'+n.brandName+'</span></li>';
			})
		} else {
			htm += '<label style="color:gray;">没有搜索到任何有效的品牌,请先去<a target="_blank" href="'+$('#initPath').val()+'/GoodsBrandController.do?method=toCreateOrUpdateView" class="sysicon siAdd"><strong>新增品牌</strong></a></label>';
		}
		$('#goodsbrand').empty().append(htm);
	});
	
	//记录当前选择的类目ID
	$('#goodsclassId').val(classId);

	//我选择的类目
	pubGoods.changeMySelectedText();
}

//选择品牌
pubGoods.selectBrand=function(ctrl,brandId){
	pubGoods.changeClass("goodsbrand",ctrl);

	//记录当前选择的类目ID
	$('#goodsbrandId').val(brandId);

	//我选择的类目
	pubGoods.changeMySelectedText();
}

//我选择的类目
pubGoods.changeMySelectedText=function() {
	var selectedText = '';
	selectedText += '<li class="root">'+$('#goodscategory').find('li[class$=selected]').text()+'</li>';
	selectedText += '<li>'+$('#goodsclass').find('li[class$=selected]').text()+'</li>';
	selectedText += '<li>'+$('#goodsbrand').find('li[class$=selected]').text()+'</li>';
	$('#mySelected').empty().append(selectedText);
}

$(document).ready(function(){
	//获取品目
	$.getJSON($('#initPath').val()+"/GoodsClassCategoryController.do?method=getCategoryByMainProducts", {} , function(json){
		var htm = '';
		$.each(json.result,function(i,n){
			if(n[2] > 0) {
				htm += '<li class="parent" id="'+n[0]+'" onclick="pubGoods.getGoodsClassByCategory(this,\''+n[0]+'\');"><span>'+n[1]+'</span></li>';
			} else {
				htm += '<li id="'+n[0]+'" onclick="pubGoods.getGoodsClassByCategory(this,\''+n[0]+'\');"><span>'+n[1]+'</span></li>';
			}
		})
		$('#goodscategory').empty().append(htm);
		
		//我选择的类目
		pubGoods.changeMySelectedText();
	});

	//类目搜索
	$('#J_BtnCateSearch').click(function(){
		if(!$('#J_IptCateSearch').val()) {
			alert('请输入关键字查询');
			return;
		}
		$('#divMySelect').addClass('hidden');
		$('#category-cascading').addClass('hidden');
		$('#search-result').removeClass('hidden');

		$.getJSON($('#initPath').val()+"/GoodsClassCategoryController.do?method=getProductsBySearchName", {"keyWords":native2ascii($('#J_IptCateSearch').val())} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			var htm = '';
			if(json.result.length==0) {
				htm += '<li class="status"><span>抱歉，没有找到与关键字"'+$('#J_IptCateSearch').val()+'"相关的类目。</span></li>';
			} else {
				$.each(json.result,function(i,n){
					htm += '<li categoryId="'+n[0]+'" classId="'+n[2]+'" brandId="'+n[4]+'" onclick="pubGoods.changeClass(\'J_LstSearchResult\',this);" class="item"><ol class="category-path">';
					htm += '<li class="root">'+n[1]+'</li>';
					htm += '<li>'+n[3]+'</li>';
					htm += '<li>'+n[5]+'</li>';
					htm += '</ol></li>';
				})
			}
			$('#J_LstSearchResult').empty().append(htm);
			$('#J_BtnPublish').addClass("disabled");
			$('#J_BtnPublish').attr("disabled","disabled");
		});
	})
	
	//取消
	$('#cancel').click(function(){
		$('#divMySelect').removeClass('hidden');
		$('#category-cascading').removeClass('hidden');
		$('#search-result').addClass('hidden');

		$('#J_BtnPublish').addClass("disabled");
		$('#J_BtnPublish').attr("disabled","disabled");
	})

	//发布商品
	$('#J_BtnPublish').click(function(){
		var url = $('#initPath').val() + "/GoodsController.do?method=toCreateGoods";
		url += "&categoryId="+$('#goodscategoryId').val();
		url += "&classId="+$('#goodsclassId').val();
		url += "&brandId="+$('#goodsbrandId').val();
		window.location.href = url;
	})

	//监听类目搜索框的回车事件
	$('#J_IptCateSearch').keypress(function(event){
		if(event.keyCode == 13){
			$('#J_BtnCateSearch').click();
			return false;
		}
	});
})
</script>

</body>
</html>
