//包组区分JS方法、属性的唯一性
var itemValueAddForList={};
var checkedIds = new Array();
$(document).ready(function(){
	$("#preValuesTable tr>td>input[type=checkbox]").click(function(){
		checkedIds = new Array();
		$("#preValuesTable tr>td>input[type=checkbox][checked=true]").each(function(i,n){
			checkedIds.push($(n).val());
		})
		if(this.checked){
			$(this).parent().parent().find('td').eq(3).find('input').addClass('required');
			$(this).parent().parent().find('td').eq(5).find('input').addClass('required');
			$(this).parent().parent().find('td').eq(3).find('span').empty().append('*');
			$(this).parent().parent().find('td').eq(5).find('span').empty().append('*');
		}else{
			$(this).parent().parent().find('td').eq(3).find('input').removeClass('required');
			$(this).parent().parent().find('td').eq(5).find('input').removeClass('required');
			$(this).parent().parent().find('td').eq(3).find('span').empty();
			$(this).parent().parent().find('td').eq(5).find('span').empty();
		}
	})
	$("#preValuesTable tr>td>input[type=checkbox][checked=true]").each(function(i,n){
		checkedIds.push($(n).val());
	})
	
	// 必填项控制
	$("#preValuesTable tr>td>input[type=checkbox][checked=false]").each(function(i,n){
		$(n).parent().parent().find('td').eq(3).find('input').removeClass("required");
		$(n).parent().parent().find('td').eq(5).find('input').removeClass("required");
		$(n).parent().parent().find('td').eq(3).find('span').empty();
		$(n).parent().parent().find('td').eq(5).find('span').empty();
	})
	
	// 返回
	$("#itemValueAddForListReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	// 提交
	$("#itemValueAddForListSave").click(function(){
		if(!$('#itemValue').valid()){alert('请正确填写表单!');return;}
		
		var names = new Array();
		var codes = new Array();
		$("#preValuesTable tr>td>input[type=checkbox][checked=true]").each(function(i,n){
			names.push($(n).parent().parent().find('td').eq(5).find('input').val());
			codes.push($(n).parent().parent().find('td').eq(3).find('input').val());
		})
		var count = 0;
		$.each(names,function(i,n){
			$.each(names,function(m,o){
				if(o == n){
					count ++;
				}
			})
		})
		if(count > names.length){
			alert("[配置值名称]有重复值,请修正..");
			return false;
		}
		count = 0
		$.each(codes,function(i,n){
			$.each(codes,function(m,o){
				if(o == n){
					count ++;
				}
			})
		})
		if(count > codes.length){
			alert("[配置值]有重复值,请修正..");
			return false;
		}
		
		$('[name=itemValueObjIds]').val(checkedIds.toString());
		$('#itemValue').ajaxSubmit({
			url:$('#initPath').val()+"/SysConfigController.do?method=saveSysConfigItemValue",
			dataType:'json',
			success:function(json){
			if(json.failure){
				alert(json.result);
				return;
			}
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			$("#sysconfigItemListView").click();
			},
			error:function(msg){
				alert(msg);
				$("#sysconfigItemListView").click();
			}
		});
	})
});
