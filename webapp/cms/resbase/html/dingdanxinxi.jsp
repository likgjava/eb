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

<div id="ddxx_a">填写订单信息</div>
<div id="ddxx_b">
  <div class="ddxx_b0">
	  <div class="ddxx_zi01" style="padding-top:15px;">收货人信息</div>
	  <p class="ddxx_zi02" style="margin-left:30px;">收货人姓名：
	    <input type="text" name="textfield" />
	    <br />
	    　　省　份：
	    <select name="select">
	      <option>请选择省份</option>
	      <option>北京</option>
	      <option>天津</option>
	      <option>上海</option>
        </select>
	    <br />
	    　　地　址：
	    <input name="textfield22" type="text" size="40" />
	    <br />
	    　手机号码：
	    <input type="text" name="textfield2" />
	    <br />
	    　固定电话：
	    <input type="text" name="textfield3" />
	    <br />
	    　电子邮件：
	    <input type="text" name="textfield4" />
	    <br />
	    　　邮　编：
	    <input type="text" name="textfield5" />
	  </p>
	</div>
	<div class="ddxx_b0">
	  <div class="ddxx_zi01" style="padding-top:15px;"><span class="ddxx_zi01" style="padding-top:15px;">支付及配送方式</span></div>
	  <p class="ddxx_zi02" style="margin-left:30px;">支付方式：
        <input type="radio" name="radiobutton" value="radiobutton" />
货到付款<br /> 　　　　　
<input type="radio" name="radiobutton" value="radiobutton" />
网银 （开户行：中信首体南路支行

账  号：7112510182600079277

公  司：北京国信商通科技有限公司）<br />
　　　　　
<input type="radio" name="radiobutton" value="radiobutton" />
支付宝 (暂缓开通)<br />
　　　　　　<span class="ddxx_zi03">备注：

1、请在汇款单的附言处注明订单号和用户名（非常重要！）；

<br />　　　　　　　　　
2、汇款地址

邮局汇款地址：北京市海淀区首体南路22号国兴大厦11层

邮编：100044

收款人：中国采购与招标网</span><br />
配送方式：
<input type="radio" name="radiobutton" value="radiobutton" />
快递
<input type="radio" name="radiobutton" value="radiobutton" />
平邮
<input type="radio" name="radiobutton" value="radiobutton" />
EMS</p>
	</div>
  <div class="ddxx_b0">
    <div class="ddxx_zi01" style="padding-top:15px;padding-bottom:15px;">商品清单</div>
	  <table width="840" height="60" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
        <tr>
          <td align="center" valign="middle" bgcolor="#FFF4D7">商品编号</td>
          <td align="center" valign="middle" bgcolor="#FFF4D7">商品名称</td>
          <td align="center" valign="middle" bgcolor="#FFF4D7">团购价</td>
          <td align="center" valign="middle" bgcolor="#FFF4D7">商品数量</td>
		  <td align="center" valign="middle" bgcolor="#FFF4D7">总价</td>
        </tr>
        <tr>
          <td align="center" valign="middle" bgcolor="#FFFDDD">1234567890</td>
          <td align="left" valign="middle" bgcolor="#FFFDDD" style="padding-left:5px;">&nbsp;</td>
          <td align="center" valign="middle" bgcolor="#FFFDDD">￥69.50</td>
          <td align="center" valign="middle" bgcolor="#FFFDDD">-
            <input name="textfield6" type="text" value="１" size="3" />
          +</td>
		  <td align="center" valign="middle" bgcolor="#FFFDDD">￥69.50</td>
        </tr>
      </table>
	  <div id="ddxx_sub"><input type="submit" name="Submit" value="" class="ddxx_subm" /></div>
	  <div style="clear:both;"></div>
  </div>
	<div style="clear:both;"></div>
</div>
<div id="ddxx_c"><img src="cms/resbase/img/ddxx_bg_02.jpg" /></div>

