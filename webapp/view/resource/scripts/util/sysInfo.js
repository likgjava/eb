		/*
	++++++++
	+ 
	+ 站点信息
	+ 加载json,注意格式,请在setting.js中设置
	+ 参数说明:
	+ sysFile:菜单json源
	+ dataSource:数据源(按json格式),不用数据源时设为null或删除;currentUserFile与dataSourece同时存在dataSource为
	+
	++++++++*/
$.sysInfo_load = function(settings){
	var options = jQuery.extend({
		sysFile : 'url',
		dataSource:null
	}, settings);
	
	var self = $('#footer');
	
	if(!options.dataSource){
		$.getJSON("SysInfoConfigController.do?method=getSysInfo",function(json){
			json = json.sysInfo;
			var company = json.company;
			var address = json.address;
			var telephone = json.telephone;
			var email = json.email;
			
			var website = json.sitename;
			
			self.find('.company').text(company);
			self.find('.address').text(address);
			self.find('.telephone').text(telephone);
			self.find('.email').html('<a href="mailto:'+email+'">'+email+'</a>');
			self.find('.website').html('<a href="'+website+'" target="_blank">'+website+'</a>');
		})
	}
	else{
		
		var json = options.dataSource;
			   
		var title = json.webInfo[0].webName;
		var keyWord = json.webInfo[0].keyWord;
		//公司信息
		var company = json.copyright[0].company;
		var address = json.copyright[0].address;
		var telephone = json.copyright[0].telephone;
		var email = json.copyright[0].email;
		var website = json.copyright[0].website;
		//技术支持
		var Tcompany = json.technicalSupport[0].company;
		var Taddress = json.technicalSupport[0].address;
		var Ttelephone = json.technicalSupport[0].telephone;
		var Temail = json.technicalSupport[0].email;
		var Twebsite = json.technicalSupport[0].website;
		
		self.find('.company').text(company);
		self.find('.address').text(address);
		self.find('.telephone').text(telephone);
		self.find('.email').html('<a href="mailto:'+email+'">'+email+'</a>');
		self.find('.website').html('<a href="'+website+'" target="_blank">'+website+'</a>');
	   
	   document.title = title;
	   $('meta[name=keywords]').attr('content',keyWord);
			   
			   
		}
	return this;
}

	﻿