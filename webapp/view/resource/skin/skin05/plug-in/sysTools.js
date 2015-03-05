/*
 *
 ********
 * $.fn.sysTools
 * 
 *
 ******** ******** ********
 *
 * by:Anson,E-mail:chents@gpcsoft.com
 *
 * Depends:
 * 
 *  
 * 	
 * 
 */



$.sysTools = function(settings){

	var options = jQuery.extend({
								
									hideSubID:'navSubControl',
									hideKeyCode:120,//F9
									fullScreenID:'screenControl',
									fullKeyCode:119,//F8
									myDesktopID:'myDesktop',
									deskURL:'',
									optionHistoryID:'optionHistory'
									
								}, settings),
	self = this,
	o = options;
	

		

	$('#'+o.hideSubID).click(function(){
									  if($(this).hasClass('show')){
											sysTools_showSub();
											

										}else{
											sysTools_hideSub();
											
										} 
										fitHeight()
									  })
	$('#'+o.fullScreenID).click(function(){
										 
										if($(this).data('fullScreen')){
											sysTools_defaultLayout();
											$(this).data('fullScreen',false);
										}else{
											sysTools_fullScreen();
											$(this).data('fullScreen',true);
										
										}	
										fitHeight()
										 })
	
	$(document).bind('keydown',function(e){
											  

										if   (e.ctrlKey   ==   true   &&   e.keyCode   ==   37)   
													  {   
													  
													sysTools_hideSub(); 
													fitHeight();
													  }   
										
										if(e.ctrlKey   ==   true   &&   e.keyCode   ==   39)   
													  {   
													 sysTools_showSub(); 
													 fitHeight();
													  }   
										if   (e.ctrlKey   ==   true   &&   e.keyCode   ==   38)   
													  {   
													  
													sysTools_fullScreen();
													$('#'+o.fullScreenID).data('fullScreen',true);
													fitHeight();
													  }   
										
										if(e.ctrlKey   ==   true   &&   e.keyCode   ==   40)   
													  {   
													sysTools_defaultLayout();
													$('#'+o.fullScreenID).data('fullScreen',false); 
													fitHeight();
													  } 
										
										})
	
	//绑定按钮								 
	var checkBind=function(e){
		e=e||window.event;
		
		switch((e.which||e.keyCode)){
			case 116://F5
				if(e.preventDefault){//FF中处理
					$("#refreshBtn").focus().trigger('click');
					e.preventDefault();
				}
				else{//IE中处理
					$("#refreshBtn").focus().trigger('click');
					window.event.keyCode=0;     
					window.event.onkeydown=false;
					//event.keyCode = 0;
					e.returnValue=false;
				}	
			break;
			
			case o.fullKeyCode:
				if(e.preventDefault){//FF中处理
					
						$('#'+o.fullScreenID).focus().trigger('click');
					
					e.preventDefault();
				}
				else{//IE中处理
					
					
						$('#'+o.fullScreenID).focus().trigger('click');

				
					window.event.keyCode=0;     
					window.event.onkeydown=false;
					//event.keyCode = 0;
					e.returnValue=false;
				}	
			break;
			
			case o.hideKeyCode:
				if(e.preventDefault){//FF中处理
				
					
						$('#'+o.hideSubID).focus().trigger('click');

					e.preventDefault();
				}
				else{//IE中处理
					
				
					$('#'+o.hideSubID).focus().trigger('click');
			
				
					window.event.keyCode=0;     
					window.event.onkeydown=false;
					//event.keyCode = 0;
					e.returnValue=false;
				}	
			break;
			
			default:
			break;
			
			}

	}
		if(document.addEventListener){
			document.addEventListener("keydown",checkBind,false);
		}
		else{
			document.attachEvent("onkeydown",checkBind);
		}
	
}

 	/*
++++++++
+ 
+ 动画效果
+ 
++++++++*/
jQuery.extend( jQuery.easing,
{
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},

	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	}
});

 	/*
++++++++
+ 
+ 取数字
+ 
++++++++*/
function getNum(v) {return parseInt(v, 10) || 0;}
 	/*
++++++++
+ 
+ 隐藏边栏
+ 
++++++++*/	
function sysTools_hideSub(){

		if($('#sysContainer').data('noSub')) return false;
		$('#contentSub')
		.hide()
		.clone()
		.insertAfter('#contentSub')
		.animate({width:0},{easing:"easeInExpo",complete:function(){
							$('#sysContainer').addClass('noSub').data('noSub',true);
							$('#navSubControl').addClass('show');
							$(this).remove()
									 }});			  
}
 	/*
++++++++
+ 
+ 显示边栏
+ 
++++++++*/			
function sysTools_showSub(){
		var menuPos = $('#sysContainer').data('menuPosition') || 'left';
		if(!$('#sysContainer').data('noSub')) return false;
		$('#sysContainer').removeClass('noSub').removeData('noSub');
		var oWidth = (getNum($('#contentSub').css('width')) > 500) ? 170 : getNum($('#contentSub').css('width'));//修复换肤时 chrome宽度Bug
		$('#contentSub')
		.clone()
		.insertAfter('#contentSub').width('0px')
		.animate({width:oWidth},{easing:"easeOutElastic",complete:function(){
										  $('#navSubControl').removeClass('show');
										  $(this).remove();
										  if(menuPos!='top'){$('#contentSub').show(); }
										   }
										   })	
}
 	/*
++++++++
+ 
+ 全屏显示
+ 
++++++++*/	
function sysTools_fullScreen(){
	
		if($('#sysContainer').data('fullScreen')) return false;
		$('#sysContainer').addClass('fullScreen').data('fullScreen',true);
		sysTools_hideSub();

}
 	/*
++++++++
+ 
+ 默认布局
+ 
++++++++*/	
function sysTools_defaultLayout(){
	
		$('#sysContainer').removeClass('fullScreen').removeData('fullScreen');
		sysTools_showSub();
	
}