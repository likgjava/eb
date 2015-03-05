/*
 * imgPlay
 * by:Anson,E-mail:chents@gpcsoft.com
 * 
 * date:2010-4-28
 * version:1.1
 * 
 */

jQuery.fn.imgPlay = function(settings) {
	
  var options = jQuery.extend({
	  cssClass:"" //默认为imgPlayer,这里的样式为追加样式
  }, settings),
  o = options;
  
  var currentPage = 1;
  var self = this;
  self.addClass("imgPlayer").addClass(o.cssClass);
  var phtml = '';
  $('li', self).each(function(i) {
    this.id = 'imgPlay_li_' + (i + 1);
    if ((i + 1) > 1) {
      SetImageShowOrHide($(this), false);
    }
    phtml += '<a href="#" id="toImg_a_' + (i + 1) + '">' + (i + 1) + '</a>';

  })

  $('<div class="playerNav"></div>').html(phtml)
  //.append(phtml)
  .find('a').click(function() {

    pageClick($(this));
    return false;
  }).end().appendTo(self)

  ;

  function pageClick(obj) {
	if(!obj.get(0)) return;
    var pid = obj.get(0).id.replace('toImg_a_', '');

    obj.siblings().removeClass('hover').end().addClass('hover');
    currentPage = pid;
    $('li', self).each(function(i) {

      if (this.id.replace('imgPlay_li_', '') == pid) {
        SetImageShowOrHide($(this), true);
      } else {
        SetImageShowOrHide($(this), false);
      }

    })

  }
  function SetImageShowOrHide(obj, isShow) {
    //alert(obj.html())
    if (!isShow) {
      obj.fadeOut(0);
    } else {
      obj.fadeIn(1000);
    }
  }
  function player() {
    pageClick($("#toImg_a_" + currentPage));
    //alert(currentPage)

    if (currentPage == $(".playerNav a").length) currentPage = 1;
    else currentPage++;

  }
  setInterval(function() {
    player()
  },
  5000);
}