
var purchaseDocList={};
purchaseDocList.rows=null;//列表查询的结果集
	
	purchaseDocList.success = function(){
		$("#purchaseCDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toClarificationDocPageForJCJ&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
$(document).ready(function(){
});

