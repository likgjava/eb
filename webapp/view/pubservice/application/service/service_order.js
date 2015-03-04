
var ServiceSubscribeForm={};

//您共节省，费用总计
function calcTotal() {
	var disc = 0;//节省
	var totalF = 0;//节省
	$('#mySubscribeTable tbody').find('tr').each(function(i,n) {
		disc += parseFloat($(n).find('td:eq(3)').text().replace(',',''));
		totalF += parseFloat($(n).find('td:eq(4)').text().replace(',',''));
	})
	$('#discF').text(formatAmount(disc,2));
	$('#totalF').text(formatAmount(totalF,2));
}

$(document).ready(function(){
	var $tabs=$("#epsTabs").tabs();
	//默认值
	$('#serviceTimeL').text($(':radio:checked').parent().find('label').text());
	$('#oldPrice').text(formatAmount(parseFloat($('#servicePrice').val()) * parseInt($(':radio:checked').parent().find('label').attr('timeLang')),2));
	$('#nowPrice').text(formatAmount(parseFloat($(':radio:checked').parent().find('span').text()),2));
	$('#discountPrice').text(parseFloat($('#oldPrice').text())-parseFloat($('#nowPrice').text())<0?"0.00":formatAmount(parseFloat($('#oldPrice').text())-parseFloat($('#nowPrice').text()),2));
	
	$('tr[name=firsttr]').attr('duration',$(':radio:checked').parent().find('label').attr('timeLang'));
	$('tr[name=firsttr]').attr('payAmount',parseFloat($(':radio:checked').parent().find('span').text()));
	
	//您共节省，费用总计
	calcTotal();
	
	//选择时长
	$(':radio').click(function(){
		var duration = parseInt($(this).parent().find('label').attr('timeLang'));
		var oldPrice = parseFloat($('#servicePrice').val());
		var nowPrice = $(this).parent().find('span').text();
		$('#serviceTimeL').text($(this).parent().find('label').text());//时长
		$('#oldPrice').text(formatAmount(oldPrice * duration,2));//原价
		$('#discountPrice').text(oldPrice * duration - parseFloat(nowPrice) < 0?"0.00":formatAmount(oldPrice * duration - parseFloat(nowPrice),2));//优惠
		$('#nowPrice').text(formatAmount(parseInt($(this).parent().find('span').text()),2));//现价
		
		$('tr[name=firsttr]').attr('duration',$(this).attr('value'));
		$('tr[name=firsttr]').attr('payAmount',parseFloat(nowPrice));
		
		//您共节省，费用总计
		calcTotal();
	})
	
	//选择搭配服务
	$(':checkbox').click(function(){
		var id = $(this).attr('id');//搭配服务的ID
		if($(this).attr('checked')) {
			var html = '';
			html += '<tr id="'+id+'" duration="'+$(this).attr('duration')+'" payAmount="'+$(this).attr('amount')+'">';
			html += '<td>'+$(this).attr('name')+'</td>';
			html += '<td class="center">'+$(this).attr('duration')+$(this).attr('sunit')+'</td>';
			html += '<td class="money">'+formatAmount($(this).attr('oldprice'),2)+'</td>';//原价
			if(parseFloat($(this).attr('discount'))<0) {
				html += '<td class="money">0.00</td>';//优惠
			} else {
				html += '<td class="money">'+formatAmount($(this).attr('discount'),2)+'</td>';//优惠
			}
			html += '<td class="money">'+formatAmount($(this).attr('amount'),2)+'</td>';//现价
			html += '</tr>';
			$('#mySubscribeTable tbody').append(html);
		} else {
			$('#mySubscribeTable tbody').find('tr[id='+id+']').remove();
		}
		
		//您共节省，费用总计
		calcTotal();
	})
	
	//同意协议
	$('#alowed').click(function(){
		if($('#alowed').attr('class')=='cb_ico2') {
			$('#alowed').attr('class','cb_ico3');
			$('#submitBtn').attr('class','submit_btn_hover');
			$('#submitTips').css('display','none');
		} else {
			$('#alowed').attr('class','cb_ico2');
			$('#submitBtn').attr('class','submit_btn');
			$('#submitTips').css('display','block');
		}
	})
	
	//确认订单
	$('#submitBtn').click(function(){
		if($(':radio:checked').length == 0) {
			alert('请选择订阅时长');
			return;
		}
		if($('#alowed').attr('class')=='cb_ico3') {
			var subscribes = [];
			$('#mySubscribeTable tbody').find('tr').each(function(i,n) {
				var id = $(n).attr('id');
				var subscribe={
						serviceBase:{objId:id},
						payAmount:$(n).attr('payAmount'),
						duration:$(n).attr('duration')
				}
				subscribes.push(subscribe);
			})
			
			//按钮变灰
			$(this).attr('class','submit_btn');
			$(this).text('提交中...');
			$.getJSON($('#initPath').val()+'/ServiceSubscribeController.do?method=saveServiceSubscribe', {"subscribeStr":JSON.stringify(subscribes)}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				
				//转向支付页面
				window.open($('#initPath').val()+'/ServiceSubscribeController.do?method=toServicePayView&v_business_id='+json.v_business_id+'&v_amount='+json.v_amount);
			});
		}
	})
});
