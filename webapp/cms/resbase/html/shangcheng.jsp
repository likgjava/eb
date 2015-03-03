<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>



<style>
body {
	padding:0px;
	margin:0 auto;
	background-color:#fff;
	text-align:center;
	color:#000000;
	font-size:12px;
	font-family:"宋体";
	text-decoration: none;
}
a {text-decoration:none;color:#FF7D15;}
a:hover{text-decoration:underline;}
img{border:0;}
.jpjc_zi01{font-size:16px;font-weight:600;color:#fff;}
.ddxx_zi01{font-size:14px;font-weight:600;color:#333;}
.ddxx_zi02{line-height:250%;}
.ddxx_zi03{font-size:12px;font-weight:100;color:#f00;}
.jpjc{width:915px;height:32px;background:url(cms/resbase/img/sc_bg_01.jpg) no-repeat;margin:10px auto 0 auto;padding-left:45px;text-align:left;line-height:32px;}
.jpjc_cp{width:960px;margin:0 auto;text-align:left;}
.jc_aa{width:273px;margin-left:35px;border-bottom:1px dashed #FEBD5E;float:left;margin-top:10px;}
.jc_chanpin{width:271px;height:228px;border:1px solid #FEBD5E;}
.jc_jiage{width:269px;height:41px;background:url(cms/resbase/img/sc_bg_02.jpg) no-repeat;margin-top:1px;margin-left:1px;}
.jc_jiage2{width:269px;height:41px;background:url(cms/resbase/img/sc_bg_03.jpg) no-repeat;margin-top:1px;margin-left:1px;}
.jc_shichang{display:block;float:left;height:35px;width:90px;line-height:120%;font-size:14px;font-weight:600;color:#fff;text-align:center;padding-top:6px;}
.jc_tuangou{width:90px;height:41px;line-height:41px;font-size:20px;color:#DE0E11;font-family:"方正大黑简体";display:block;float:right;margin-right:10px;text-align:center;}
.jc_img{width:258px;height:175px;margin-top:5px;margin-left:6px;}
.jc_wenzi{width:273px;font-size:12px;line-height:150%;color:#FF7D15;margin-top:10px;}
.jc_sub{width:273px;height:50px;text-align:center;}
.jc_subm{width:81px;height:25px;border:0;background:url(cms/resbase/img/sc_sub_01.jpg);margin-top:10px;}
.jc_subm2{width:81px;height:25px;border:0;background:url(cms/resbase/img/sc_sub_02.jpg);margin-top:10px;}
#ddxx_a{width:915px;height:28px;background:url(cms/resbase/img/ddxx_bg_01.jpg) no-repeat;margin:0 auto;line-height:28px;padding-left:15px;text-align:left;font-size:14px;font-weight:600;}
#ddxx_b{width:876px;background:url(cms/resbase/img/ddxx_bg_03.jpg) repeat-y;border-top:0;border-bottom:0;margin:0 auto;padding-left:27px;padding-right:27px;}
.ddxx_b0{border-bottom:1px dashed #ccc;text-align:left;}
#ddxx_c{width:930px;height:3px;margin:0 auto;}
#ddxx_sub{width:200px;float:right;margin-top:10px;margin-bottom:10px;}
.ddxx_subm{width:160px;height:53px;border:0;background:url(cms/resbase/img/ddxx_sub.jpg) no-repeat;}
</style>

<script type="text/javascript">
//调整页面布局 
changeTabsCss("goToGift");

$("input[name=Submit]").click(function(){
	fnRemoveOtherMain(); 
	$('#sysContent').loadPage($('#initPath').val()+'/cms/resbase/html/dingdanxinxi.jsp');
	return false;
});

</script>

<div class="jpjc jpjc_zi01">精品酒城</div>
<div class="jpjc_cp">
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
118888</span><span class="jc_tuangou">88800</span></div>
<div class="jc_img">
	<a href="#">
		<img src="cms/resbase/img/sc_img_01.jpg" border="0" title=" 国窖1573?青瓷坛，是根据泸州老窖得天独厚的酿酒资源，应超高端消费人群的特定需求而推出的奢侈级酒品。作为中国第一款定制白酒，国窖1573定制酒具有稀缺性、复杂性、艺术性、综合性、私密性及收藏增值性特点，开创了中国超高端白酒定制的新时代。
品    名：国窖1573青瓷坛 酒精度：68° 规    格：2500ml*1 商场售价：118888.00 VIP贵宾：88800.00" />
	</a>
</div>
</div>
<div class="jc_wenzi"><a href="#"
	title=" 国窖1573?青瓷坛，是根据泸州老窖得天独厚的酿酒资源，应超高端消费人群的特定需求而推出的奢侈级酒品。作为中国第一款定制白酒，国窖1573定制酒具有稀缺性、复杂性、艺术性、综合性、私密性及收藏增值性特点，开创了中国超高端白酒定制的新时代。
品    名：国窖1573青瓷坛
酒精度：68°
规    格：2500ml*1
商场售价：118888.00
 VIP贵宾：88800.00">国窖1573
青瓷坛，是根据泸州老窖得天独厚的酿酒资源，应超高端消费人群的特定需求而推出的……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value="" class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
60000</span><span class="jc_tuangou">50800</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_02.jpg" width="258" height="175" border="0"
	title="国窖1573国礼酒是泸州老窖公司为表达对庆祝中华人民共和国诞辰60周年的最高敬意，由中国酿酒大师甄选产自1573国宝窖池群，并储存达60年的国窖原酒。再以国家级非物质文化遗产的泸州老窖酒传统酿制技艺，勾调出至臻醇厚的60度，盛装于印鉴国花牡丹之顶级中国红瓷器，彰显极致华贵与至高尊崇。用于国家60周年庆典品鉴，并馈赠友好国家元首。
品    名：国窖1573国礼酒
酒精度：60°
规    格：900ml*1" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="国窖1573国礼酒是泸州老窖公司为表达对庆祝中华人民共和国诞辰60周年的最高敬意，由中国酿酒大师甄选产自1573国宝窖池群，并储存达60年的国窖原酒。再以国家级非物质文化遗产的泸州老窖酒传统酿制技艺，勾调出至臻醇厚的60度，盛装于印鉴国花牡丹之顶级中国红瓷器，彰显极致华贵与至高尊崇。用于国家60周年庆典品鉴，并馈赠友好国家元首。
品    名：国窖1573国礼酒
酒精度：60°
规    格：900ml*1">国窖1573国礼酒是泸州老窖公司为表达对庆祝中华人民共和国诞辰60周年的最高敬意……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
2180</span><span class="jc_tuangou">1880</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_03.jpg" width="258" height="175" border="0"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。
品    名：中国品味         
酒精度：52°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。
品    名：中国品味         
酒精度：52°
规    格：500ml*6">本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
1980</span><span class="jc_tuangou">1680</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_04.jpg" width="258" height="175" border="0"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。
品    名：中国品味         
酒精度：38°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。
品    名：中国品味         
酒精度：38°
规    格：500ml*6">本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
989</span><span class="jc_tuangou">819</span></div>
<div class="jc_img"><a href="#"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。 
品    名：经典国窖1573         
酒精度：52°
规    格：500ml*6"><img
	src="cms/resbase/img/sc_img_05.jpg" width="258" height="175" border="0"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。 
品    名：经典国窖1573         
酒精度：52°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
799</span><span class="jc_tuangou">698</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_06.jpg" width="258" height="175" border="0"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。 
品    名：经典国窖1573         
酒精度：38°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）”及“中国非物质文化遗产（2006）”国家级保护名录特别酿制。430余年1573国宝窖池酿造，22代古法酿艺纯手工呈现，国家级物质与非物质文化遗产代表作，稀有品味，尊侯品鉴。 
品    名：经典国窖1573         
酒精度：38°
规    格：500ml*6">本品为纪念1573国宝窖池群，泸州老窖酒传统酿制技艺入选“全国重点文物保护单位（1996）……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
698</span><span class="jc_tuangou">598</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_07.jpg" width="258" height="175" border="0"
	title="泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖品牌复兴的前奏，必将带动母品牌价值提升及旗下各系列产品的价值提升
??      2010年，获得巴拿马万国博览会金奖95周年，恰逢世博会在上海召开，继奥运后，世界再一次聚焦在中国。
品    名：年份特曲(9年)
酒精度：52°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖品牌复兴的前奏，必将带动母品牌价值提升及旗下各系列产品的价值提升
??      2010年，获得巴拿马万国博览会金奖95周年，恰逢世博会在上海召开，继奥运后，世界再一次聚焦在中国。
品    名：年份特曲(9年)
酒精度：52°
规    格：500ml*6">泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
598</span><span class="jc_tuangou">498</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_img_08.jpg" width="258" height="175" border="0"
	title="泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖品牌复兴的前奏，必将带动母品牌价值提升及旗下各系列产品的价值提升
??      2010年，获得巴拿马万国博览会金奖95周年，恰逢世博会在上海召开，继奥运后，世界再一次聚焦在中国。
品    名：年份特曲(9年)
酒精度：38°
规    格：500ml*6" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"
	title="泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖品牌复兴的前奏，必将带动母品牌价值提升及旗下各系列产品的价值提升
??      2010年，获得巴拿马万国博览会金奖95周年，恰逢世博会在上海召开，继奥运后，世界再一次聚焦在中国。
品    名：年份特曲(9年)
酒精度：38°
规    格：500ml*6">泸州老窖年份特曲的上市，不仅仅是一个新产品的上市，更是公司双品牌发展战略下泸州老窖……</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div style="clear: both;"></div>
</div>
<div class="jpjc jpjc_zi01">精品茶城</div>
<div class="jpjc_cp">
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
130.00</span><span class="jc_tuangou">115</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_01.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011087027 <br />
商品名称：高山铁韵 <br />
规格型号：TC33</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
100.00</span><span class="jc_tuangou">90.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_02.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011338350 ? <br />
商品名称：高山韵 ?<br />
规格型号：Tc33</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
320.00</span><span class="jc_tuangou">288.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_03.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011780963 ? <br />
商品名称：龙韵悠香 ? <br />
规格型号：Tc33</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
260.00</span><span class="jc_tuangou">234.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_04.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011662483 ? <br />
商品名称：清谷幽兰 ? <br />
规格型号：tc33 </a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
1,580.00</span><span class="jc_tuangou">1180.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_05.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011109604 ? <br />
商品名称：精品大红袍 ? <br />
规格型号：正岩红袍大</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
380.00</span><span class="jc_tuangou">300.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_06.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A2001042011460951 ? <br />
商品名称：金骏眉 ? <br />
规格型号：特级</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
280.00</span><span class="jc_tuangou">230.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_07.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A2001042011878903 ? <br />
商品名称：正山小种 ? <br />
规格型号：特级</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
300.00</span><span class="jc_tuangou">250.00</span></div>
<div class="jc_img"><a href="#"><img
	src="cms/resbase/img/sc_cha_06.jpg" width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">商品编号：A200107012011176265 ? <br />
商品名称：大红袍 ? <br />
规格型号：特级</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div style="clear: both;"></div>
</div>
<div class="jpjc jpjc_zi01">精品书城</div>
<div class="jpjc_cp">
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
39.50</span><span class="jc_tuangou">29.60 </span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sh_01.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">百年孤独（根据马尔克斯指定版本翻译，未做任何增删）
[精装] </a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
39.90</span><span class="jc_tuangou">22.70 </span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sh_02.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">时寒冰说:经济大棋局,我们怎么办 <br />
[平装]<br />
</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage"><span class="jc_shichang">市场价<br />
28.00</span><span class="jc_tuangou">16.70</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sh_03.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">圣经密码 [平装] ~ 迈可?卓思宁 (作者), 杜默
(译者) </a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm" /></div>
</div>

<div style="clear: both;"></div>
</div>
<div class="jpjc jpjc_zi01">山水油墨画</div>
<div class="jpjc_cp">
<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
1000.00</span><span class="jc_tuangou">700.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_01.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">苍松捧寿</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
2000.00</span><span class="jc_tuangou">1500.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_02.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">黄山归来</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
2000.00</span><span class="jc_tuangou">1500.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_03.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">黄山曙光</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
1000.00</span><span class="jc_tuangou">700.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_04.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">米点山水</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
1000.00</span><span class="jc_tuangou">700.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_05.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">溪山访友图</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
800.00</span><span class="jc_tuangou">500.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_06.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#">竹雀图</a></div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div class="jc_aa">
<div class="jc_chanpin">
<div class="jc_jiage2"><span class="jc_shichang">市场价<br />
2000.00</span><span class="jc_tuangou">1500.00</span></div>
<div class="jc_img"><a href="#"><img src="cms/resbase/img/sc_sm_07.jpg"
	width="258" height="175" border="0" /></a></div>
</div>
<div class="jc_wenzi"><a href="#"></a>壮丽徽山</div>
<div class="jc_sub"><input type="submit" name="Submit" value=""
	class="jc_subm2" /></div>
</div>

<div style="clear: both;"></div>
</div>

