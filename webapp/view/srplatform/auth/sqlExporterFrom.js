//定义文件全局变量 处理方法名重复问题

var postForm={};

$(document).ready(function(){
	$('#sqlExporterForm').validate();
	
	$('select[name=domain]').change(function(){
		//$.ajaxSetup({async:false});
		$('select[name=module]').html('');
		if($(this).val()==""||this.selectedIndex>2)return;
		$.getJSON($('#initPath').val()+'/SqlExporterController.do?method=getModules',
				{domain:$(this).val()},
				function(json){
					var modules = json.result;
					$.each(modules,function(i,module){
						//alert(module)
						//$('select[name=module]').options.add(new Option(module,module));
						$('select[name=module]').append("<option value=\""+module+"\">"+module+"</option>")
					})
				});
		});
	
	//提交
	$('#toexport').click(function(){
		if(!$('#sqlExporterForm').valid()){return;}
		//alert(obj2str(formToJsonObject('sqlExporterForm')));
		$.getJSON($('#initPath').val()+'/SqlExporterController.do?method=getRecords', formToJsonObject('sqlExporterForm'), function(json){
			$("#sqltext").html('');
			$.each(json.result,function(i,sql){
				$("#sqltext").append("<span>"+sql+"</span><br>");
			})
			
		});
	});
	$('#manyAtta').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/upload/many_attachments.jsp');
	})
	$('#manyAtta2').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/upload/many_attachments2.jsp');
	})
	$('#manyAtta3').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/upload/many_attachments3.jsp');
	})
	$('#light').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/wflow/status_light.jsp');
	})
	$('#reloadEnum').click(function(){
		$.getJSON($('#initPath').val()+'/SqlExporterController.do?method=reloadEnum',{},function(json){
			if(json.failtrue){
				alert(json.result);
			}
			else{
				alert('重置枚举完成');
			}
			
		})
	})

});
