<%@ page pageEncoding="UTF-8"%>
<div class="imgEfect">
	  <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="${advertisingSubscribe.advertisingPosition.adverWidth}" height="${advertisingSubscribe.advertisingPosition.adverLength}">
	  <param name="movie" value="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" />
	  <param name="quality" value="high" />
	  <param name="wmode" value="transparent" />
	  <param name="swfversion" value="8.0.35.0" />
	  <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
	  <param name="expressinstall" value="Scripts/expressInstall.swf" />
	  <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
	  <!--[if !IE]>-->
	  <object type="application/x-shockwave-flash" data="AttachmentController.do?method=showImg&objId=${advertisingSubscribe.adverFile}" width="${advertisingSubscribe.advertisingPosition.adverWidth}" height="${advertisingSubscribe.advertisingPosition.adverLength}">
	    <!--<![endif]-->
	    <param name="quality" value="high" />
	    <param name="wmode" value="transparent" />
	    <param name="swfversion" value="8.0.35.0" />
	    <param name="expressinstall" value="Scripts/expressInstall.swf" />
	    <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
	    <div>
	      <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
	      <p><a href="http://www.lzhb.com.cn"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获取 Adobe Flash Player" width="112" height="33" /></a></p>
	    </div>
	    <!--[if !IE]>-->
	  </object>
	  <!--<![endif]-->
	</object>  
</div><!-- imgEfect -->