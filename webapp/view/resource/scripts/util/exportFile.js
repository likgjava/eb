/**
 * 导出的工具类  例如导出word		excel等
 * created by wangsw
 * created date 2009-11-18
 */
	//导出文件     参数: 提交按钮的id, word标题, 提交的内容, 文件类型
var exportFile={};
	//导出word  参数: 提交按钮的id, word标题, 提交的内容, 文件类型
	exportFile.word=function exportFileExcute(id, title, content, fileType){		
		var exportWordFrame='exportWordFrame';//防止命名冲突、可配置
		var exportWordForm='exportWordForm';
		var filename='filename';
		var wordContent='wordContent';
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		$('#'+exportWordFrame).remove();
		$('#'+id).after('<div class=style:none><iframe id='+exportWordFrame+' width=400 higth=500 ></iframe></div>');
		var html='';
		html+='<form id='+exportWordForm+' method=post action='+$("#initPath").val()+'/ExportController.do?method=exportPdf >';
		html+='<input name=isAJAX value=true></input>';//用input会有特殊字符问题存在
		html+='<textarea name='+filename+'>'+title+'</textarea>';//用input会有特殊字符问题存在
		html+='<textarea name='+wordContent+'>'+content+'</textarea>';
		html+='</form>';
		$('#'+exportWordFrame).append(html);
		$('#'+exportWordFrame+' #'+exportWordForm).submit();
	}
	
	exportFile.excel=function(id,title,columnNames,queryColumns,columnWidth,clazzName,order,gridId,isDivPage){
		var p=$('#'+gridId).flexOptions()[0].p;
		var json=[];
		if(p.searchZone!=false)
			json=$('#'+p.searchZone).serializeArray()
		for(var key in p.query){/*这里主要onSuccess里需要往p.query里放*/
			json.push({name:key,value:p.query[key]});
		}
		json.push({name:'isAJAX',value:true});
		if(isDivPage) json.push({name:'isDivPage',value:true});/*是否分页*/
		
		$('#exportExcel').remove();
		var url=$('#initPath').val()+'/ExcelExportController.do?method=export';
		url+='&title='+encodeURI(title);
		url+='&columnNames='+encodeURI(columnNames);
		url+='&queryColumns='+queryColumns;
		url+='&columnWidth='+columnWidth;
		url+='&clazzName='+clazzName;
		url+='&order='+order;
		
		$.each(json,function(i,n){/*追加查询条件*/
			url+='&'+n.name+'='+encodeURI(n.value);
		});
		
		$('#'+id).after('<iframe id=exportExcel name=exportExcel style=display:none width=400 higth=500 ></iframe>')
		$('#exportExcel').html('<form id=excelForm name=excelForm method=post action='+encodeURI(url) +'></form>');
		$('#exportExcel #excelForm').submit();		
	}
	
	