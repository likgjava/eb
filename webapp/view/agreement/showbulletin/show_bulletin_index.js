/*
 * 小额交易平台.商品展示首页列表
 */
var ShowBulletinIndex = {};

//根据分类id
ShowBulletinIndex.byPurCategory = function(purCategoryId,purCategoryCode){
	$("#sysContent").loadPage($('#initPath').val()+"/BulletinShowController.do?method=toBulletinList&rp=21&page=1&districtLevel=1&purCategoryId="+purCategoryId+"&purCategoryCode="+purCategoryCode);
}

$(document).ready(function(){

	//选中顶部菜单
	changeTabsCss("goToBulltin");
	
	//选中查询下拉框
	keyWordTypeChange('1');
});