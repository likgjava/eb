<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<br/>
<div class="conOperation">
	<button id="makeIndexBtn" type="button" class="largeBtn"><span>发布《首页》</span></button>
	<button id="makeBiddingIndexBtn" type="button" class="largeBtn"><span>发布《电子招标首页》</span></button>
	<button id="makeBargainIndexBtn" type="button" class="largeBtn"><span>发布《电子采购首页》</span></button>
	<button id="makeGoodsIndexBtn" type="button" class="largeBtn"><span>发布《商品库首页》</span></button>
	<button id="makeSupplierIndexBtn" type="button" class="largeBtn"><span>发布《供应商库首页》</span></button>
</div>
<div class="conOperation">
	<button id="makeAllIndexBtn" type="button" class="largeBtn"><span>发布所有首页</span></button>
</div>

<script>
$(document).ready(function(){
	//发布首页
	$("#makeIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布《首页》...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=index", {}, function(json){
			if(json.success){
				alert("《首页》发布成功！");
			}else{
				alert("《首页》发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeIndexBtn').find('span').html('发布《首页》');
		});
	});
	//发布电子招标首页
	$("#makeBiddingIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布《电子招标首页》...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=biddingIndex", {}, function(json){
			if(json.success){
				alert("《电子招标首页》发布成功！");
			}else{
				alert("《电子招标首页》发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeBiddingIndexBtn').find('span').html('发布《电子招标首页》');
		});
	});
	//发布电子采购首页
	$("#makeBargainIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布《电子采购首页》...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=bargainIndex", {}, function(json){
			if(json.success){
				alert("《电子采购首页》发布成功！");
			}else{
				alert("《电子采购首页》发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeBargainIndexBtn').find('span').html('发布《电子采购首页》');
		});
	});
	//发布商品库首页
	$("#makeGoodsIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布《商品库首页》...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=goodsIndex", {}, function(json){
			if(json.success){
				alert("《商品库首页》发布成功！");
			}else{
				alert("《商品库首页》发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeGoodsIndexBtn').find('span').html('发布《商品库首页》');
		});
	});
	//发布供应商库首页
	$("#makeSupplierIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布《供应商库首页》...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=supplierIndex", {}, function(json){
			if(json.success){
				alert("《供应商库首页》发布成功！");
			}else{
				alert("《供应商库首页》发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeSupplierIndexBtn').find('span').html('发布《供应商库首页》');
		});
	});
	//发布所有首页
	$("#makeAllIndexBtn").click(function(){
		$('.conOperation button').attr('disabled', true);
		$(this).find('span').html('正在发布所有首页...');
		$.getJSON($("#initPath").val()+"/MakeStaticPageController.do?method=makeStaticPage&pageType=all", function(json){
			if(json.success){
				alert("所有首页发布成功！");
			}else{
				alert("所有首页发布失败！");
			}
			$('.conOperation button').attr('disabled', false);
			$('#makeAllIndexBtn').find('span').html('发布所有首页');
		});
	});
});
</script>