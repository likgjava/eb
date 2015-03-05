/*
bxGallery v1.1
Plugin developed by: Steven Wanderski
http://bxgalleryplugin.com
http://stevenwanderski.com

Released under the GPL license:
http://www.gnu.org/licenses/gpl.html
*/

(function($){

 	$.fn.extend({ 
 		
 		bxGallery: function(options) {

			var defaults = {
				maxwidth: '280',  			// if set, the main image will be no wider than specified value (in pixels)
				maxheight: '280', 			// if set, the main image will be no taller than specified value (in pixels)
				thumbwidth: '40',  			//thumbnail width (in pixels)
				thumbcrop: false,  			// if true, thumbnails will be a perfect square and some of the image will be cropped
				croppercent: .35,  			// if thumbcrop: true, the thumbnail will be scaled to this percentage, then cropped to a square
				thumbplacement: 'bottom',  	// placement of thumbnails (top, bottom, left, right)
				thumbcontainer: '280',     	// width of the thumbnail container div (in pixels)
				opacity: .6,				// opacity level of thumbnails
				load_text: '',				// if set, text will display while images are loading
				load_image: 'view/resource/images/load.gif',
				wrapperclass: 'outer'
			}
				
			var options =  $.extend(defaults, options);
			var o = options;
			var cont = '';
			var caption = '';
			var $outer = '';
			var $orig = this;
			var tall = o.maxheight;
			var iTall = o.maxheight-o.thumbwidth/2;
			var wide = o.maxwidth;
			var showing = 0;
			var i = 0;
			var k = $orig.find('img').size();
			var current;
			
			preload_img();			
						
			function preload_img(){
				$orig.hide();
				if(o.load_text != ''){
					$orig.before('<div id="loading">' + o.load_text + '</div>');
				}else{
					$orig.before('<div id="loading"><img src="' + o.load_image + '" /></div>');
				}
				$orig.parent().find('#loading').css({'text-align':'center', 'width':wide});
				$orig.find('img').each(function(){
					var the_source = $(this).attr('src');
					var the_img = new Image();
					the_img.onload = function(){preload_check();};
					the_img.src = the_source;
				});		
			}
			
			function preload_check(){
				i++;
				if(i == k){init();}
			}
			
			function init(){
				set_layout();			
				set_main_img();			
				place_thumbcontainer();			
				set_thumbs();			
			}
			
			function set_layout(){
				$orig.parent().find('#loading').hide();
				$orig.show();				
				$orig.wrap('<div class="' + o.wrapperclass + '"></div>');
				$outer = $orig.parent();
				$orig.find('li').css({'text-align':'center','position':'absolute','border':'1px solid #CDCDCD','width':wide+"px",'height':tall+'px','line-height':tall+'px'});
			}
			
			function set_main_img(){
				$orig.find('img').each(function(){
					var $this = $(this);
					var $imgheight = $this.height();
					var $imgwidth = $this.width();
					if($this.attr('title') != ''){
						caption = $this.attr('title');
						$this.parent().append('<div class="caption">' + caption + '</div>');
					}
					if(o.maxwidth != '' && $this.width()/$this.height() >= wide/iTall){
						if($this.width() > wide){						
							$this.width(wide);
							$this.height(($imgheight/$imgwidth)*wide);
						}
					}					
					if(o.maxheight != '' && $this.width()/$this.height() < wide/iTall){
						if($this.height() > iTall){
							$this.height(iTall);
							$this.width(($imgwidth/$imgheight)*iTall);
						}
					}						

					$this.css("vertical-align","middle");
					cont += '<li><img src="' + $this.attr('src') + '"/></li>'; 
				});
				
				$orig.find('li:not(:first)').hide();
				
				$orig.height(tall+"px");
				$orig.width(wide+"px");
				
				$outer.find('.caption').width(wide);
			}
									
			function place_thumbcontainer(){
				if(o.thumbplacement == 'top'){		
					$outer.prepend('<ul class="thumbs">' + cont + '</ul>');
					$outer.find('.thumbs').css({'overflow':'auto'});
				}else if(o.thumbplacement == 'left'){
					$outer.prepend('<ul class="thumbs">' + cont + '</ul>');
					$orig.css({'float':'left'});
					$outer.find('.thumbs').css({'float':'left'});
				}else if(o.thumbplacement == 'bottom'){
					$outer.append('<ul class="thumbs">' + cont + '</ul>');
					$outer.find('.thumbs').css({'margin-top':'5px',"width":o.thumbcontainer+"px"});	
				}else if(o.thumbplacement == 'right'){
					$outer.append('<ul class="thumbs">' + cont + '</ul>');
					$orig.css({'float':'left'});
					$outer.find('.thumbs').css({'float':'left'});				
				}
				$outer.append('<div style="clear:both"></div>');
				if(o.thumbcontainer != ''){
					$outer.find('.thumbs').width(o.thumbcontainer);
				}		
			}
			
			function set_thumbs(){						
	    		$outer.find('.thumbs li').each(function() {				
				
					var $this = $(this);
					var $img = $this.find('img');
					var $imgwidth = $img.width();
					var $imgheight = $img.height();
				
					if(o.thumbcrop){
						$img.width($imgwidth * o.croppercent);
						$img.height(($imgheight/$imgwidth)*$img.width());	
						$img.css("vertical-align","middle");
						$this.css({
							'float':'left',
							'border':'1px solid #CDCDCD',
							'width':o.thumbwidth + 'px',
							'height':o.thumbwidth + 'px',
							'overflow':'hidden',
							'cursor':'pointer'
						});
					}else{
						var thumbwidth = o.thumbwidth - 5;
						if($imgwidth/$imgheight >= 1){
							$img.css("width",thumbwidth + "px");
							$img.css("height",($imgheight/$imgwidth)*thumbwidth + "px");
						}					
						if($imgwidth/$imgheight < 1){
							$img.css("width",($imgwidth/$imgheight)*thumbwidth + "px");
							$img.css("height",thumbwidth + "px");
						}

						$img.css("vertical-align","middle");
						$this.css({
							'float':'left',
							'border':'1px solid #CDCDCD',
							'width':o.thumbwidth + 'px',
							'height':o.thumbwidth + 'px',
							'line-height':o.thumbwidth + 'px',
							'text-align':'center',
							'margin':'5px',
							'cursor':'pointer'
						});
						$this.height(o.thumbwidth);
					}				
				
					$this.click(function(){
						var x = $outer.find('.thumbs li').index($this);
						if(showing != x){
							$orig.find('li').fadeOut();
							$orig.find('li').eq(x).fadeIn();
							showing = x;
						}
					});
									
				});	
					
				var $thumb = $outer.find('.thumbs li');
				$thumb.eq(0).addClass('on');
				$thumb.not('.on').fadeTo(0, o.opacity);
				$thumb.click(function(){
					var t = $(this);
					var i = $thumb.index(this);
					if(current!=i){
						$thumb.removeClass('on');
						t.addClass('on');
						$thumb.not('.on').fadeTo(200, o.opacity);
						current = i;
					}
				}).hover(function(){
					$(this).stop().fadeTo(200, 1);
				}, function(){
					$(this).not('.on').stop().fadeTo(200, o.opacity);
				});		
	    		
			}
						
    	}
	});
	
})(jQuery);

