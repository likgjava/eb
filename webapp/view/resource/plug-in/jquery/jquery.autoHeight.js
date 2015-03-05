/*
 * autoHeight
 * 自动识别高度
 * by:Anson,E-mail:chents@gpcsoft.com
 *
 * 
 */ 
(function($){
		  
$.autoHeight = function(settings) {
	
	var options = $.extend({
							top:'#sysBranding',
							bottom:'#sysInfo',
							center:'#contentMain',
							callback:function(e){}
							},settings);
	var w = $( window ); 
	var H = w.height(); 
	var tdiv = $(options.top);
	var tbottom = $(options.bottom);
	var tcenter = $(options.center);
	var topH = exHeight(tdiv) + tdiv.height();
	var bottomH = exHeight(tbottom) + tbottom.height();
	var centerEx = exHeight(tcenter);
	var endheight = H-topH-bottomH - centerEx ;
	tcenter.css({height: endheight}); 
	options.callback(endheight);
}
})(jQuery)
function exHeight(obj){
	var paddingTop,paddingBottom,borderTop,borderBottom,marginTop,marginBottom,reHeight;
	if(isNaN(parseInt(obj.css("padding-top")))){
		paddingTop = 0
	}else{
		paddingTop = parseInt(obj.css("padding-top"))
		}; 
	if(isNaN(parseInt(obj.css("padding-bottom")))){
		paddingBottom = 0
	}else{
			paddingBottom = parseInt(obj.css("padding-bottom"))
			};
	
	if(isNaN(parseInt(obj.css("margin-top")))){
		marginTop = 0
	}else{
		marginTop = parseInt(obj.css("margin-top"))
		}; 
	if(isNaN(parseInt(obj.css("margin-bottom")))){
		marginBottom = 0
	}else{
			marginBottom = parseInt(obj.css("margin-bottom"))
			};
			
	
	if(isNaN(parseInt(obj.css("border-top-width")))){
		borderTop = 0
	}else{
			borderTop = parseInt(obj.css("border-top-width"))
			}; 
			
	if(isNaN(parseInt(obj.css("border-bottom-width")))){
		borderBottom = 0
	}else{
			borderBottom = parseInt(obj.css("border-bottom-width"))
			}; 
			
	reHeight = paddingTop + paddingBottom + borderTop + borderBottom + marginTop + marginBottom;
	//reHeight = paddingTop + paddingBottom + borderTop + borderBottom ;
	return reHeight;
	
	}
