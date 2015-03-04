/*
 * epsContentMenu
 * 右键菜单
 * by:Anson,E-mail:chents@gpcsoft.com
 *
 * Depends:
 * 
 *  
 * 	
 * 
 */

(function($) {
var shadow;
function mousePosition(ev){ 

    if(ev.pageX || ev.pageY){ 
        return {x:ev.pageX, y:ev.pageY}; 
    } 
    return { 
        x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
        y:ev.clientY + document.body.scrollTop  - document.body.clientTop 
    }; 
} 


$.fn.epsContentMenu = function(vid,settings,e) {
	
	var options = $.extend({
						   shadow:true,//投影
						   menuEv: {} //事件
						   },settings);
	ev = e || window.event;
	var mousePos = mousePosition(ev);
	var pageX = mousePos.x;
	var pageY = mousePos.y;
	var self = this;
	
	self.attr('eps','epsContentMenu').addClass('epsContentMenu').css({top:pageY+'px',left:pageX+'px',position:'absolute', zIndex:'500'}).appendTo('body').show();
	
	if(!document.getElementById('epsContentMenuShadow')){
		shadow = $('<div eps="epsContentMenu" id="epsContentMenuShadow"></div>').css({backgroundColor:'#000',position:'absolute',opacity:0.2,zIndex:499})
								 .appendTo('body')
								 .hide();
		}
	if (options.shadow) {

		shadow.css({width:self.width(),height:self.height(),left:pageX+2,top:pageY+2}).show();
    }
	
	self.find('li').hover(function(){
								   $(this).addClass('hover');
								   }, function(){
									    $(this).removeClass('hover');
									   });

	$.each(options.menuEv, function(id, func) {
      $('#'+id, self).unbind('click').bind('click', function(e) {
       func(this,vid);
	   self.hide();
	   shadow.hide();
      });
    });
	
	$(document).one('click', function(){self.hide();shadow.hide();});
	return this;
}
})(jQuery);