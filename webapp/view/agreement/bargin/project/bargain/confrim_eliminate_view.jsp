<%@ page pageEncoding="UTF-8"%>

<script type="text/javascript">
var confrim_eliminate_view = {};

//剔除供应商
confrim_eliminate_view.eliminateSupplier = function(){
	var endSupplierArray = [];
	var projId = $('#projectId').val();
	$('#minPriceRecordTable').find('input[name=eliminateSupplier]:checked').each(function(i,domE){
		var endSupplier={
				project: {objId:projId},
				supplier: {objId:$(domE).val()}
		}
		endSupplierArray.push(endSupplier);
	});
	if(endSupplierArray.length == 0){
		alert('至少选择一个供应商进行剔除！'); 
		$('#minPriceRecordTable').find('input[name=eliminateSupplier]:first').qtip({
               content: "在此勾选供应商剔除",
               position: {corner: {tooltip: "bottomRight", target: "topLeft"}},
               show: { when: false, ready: true },
               hide: { when: { event: 'unfocus' } },
               style: {
                  border: {width: 3,radius: 3 },
                  padding: 10, 
                  textAlign: 'center',
                  tip: true,
                  name: 'cream'
               }
            });
		return;
	}
	
	if(window.confirm('确定剔除?')) {
		$.getJSON($('#initPath').val()+'/BiddingEliminateSupplierController.do?method=endSupplier',{endSupplierListStr:JSON.stringify(endSupplierArray)},function(json){
			if(json.failure){alert(json.result);return;}
			alert("剔除成功");
			window.location.reload();
		});
	}
}

//稍后确认
confrim_eliminate_view.eliminateLater = function(){
	$("input[name*=eliminateSupplier]").hide();
	$("#operationDiv").html(null);
}
</script>

<div class="conOperation">
	<button type="button" onclick="confrim_eliminate_view.eliminateSupplier();"><span>确认剔除</span></button>
	<button type="button" onclick="confrim_eliminate_view.eliminateLater();"><span>稍后剔除</span></button>
</div>