
var gadgetList={};

gadgetList.success=function(){
	
	$("#gadgetGrid").flexAddOptionStr({
		 '<button class="enable" type="button"><span>立即添加</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
				$.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=saveUserDeskTopGadget',{'gadget.objId':rowId},function(json){
					$('#epsDialogClose').click();//关闭窗口
					//var userDeskTopGadget=json.result;
					var html='<div id='+json.objId+' class="widget movable collapsable removable editable">';
							html+='<div class=widget-header>';
								html+='<strong>'+json.gadetName+'</strong>';
							html+=' </div>';
							html+='<div class="widget-editbox">';
								html+=json.gadetName;
							html+='</div>';
							html+='<div class="widget-content">';
							html+='</div>';
					    html+='</div>';
					    
					var place;
					if(myDesktop.columnNum==2){//判断该添加到哪行列   为了美观布局
						var column0=$('#widget-place0 .widget').length;
						var column1=$('#widget-place1 .widget').length;		
						if(column0>column1)	place='widget-place1';
						else place='widget-place0';
					}
						
					$.fn.AddEasyWidget(html, place, myDesktop.settings);
					$('#'+json.objId).find('.widget-content').load($('#initPath').val()+'/'+json.url);
					$('#'+json.objId+' .widget-editlink').remove();
					myDesktop.changePosition();//设置横竖坐标
//					$('#gadgetGrid').flexReload($('#gadgetGrid').flexOptions()[0].p);
				});
			 }).appendTo(obj);
		}
	});
}

$(document).ready(function(){
 
});



