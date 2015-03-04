//转化字符串
function obj2str(o){ 
	var r = []; 
	if(typeof o =="string") return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\""; 
	if(typeof o == "object"){ 
		if(!o.sort){ 
			r[0]="{" 
			for(var i in o){ 
				r[r.length]=i; 
				r[r.length]=":"; 
				r[r.length]=obj2str(o[i]); 
				r[r.length]=","; 
			} 
			if(!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){ 
				r[r.length]="toString:"+o.toString.toString(); 
				r[r.length]=","; 
			} 
			r[r.length-1]="}" 
		}else{ 
		r[0]="[" 
			for(var i =0;i<o.length;i++){ 
				r[r.length]=obj2str(o[i]); 
				r[r.length]=","; 
			} 
			r[r.length-1]="]" 
		} 
		return r.join(""); 
	} 
	return o.toString(); 
} 

//将字符串进行替换
function strIgnore(str){
	var ignore = new Array("%"," ");  //需要替换的字符串
	var targetstr;
	for(var i=0; i<ignore.length ; i++) {
		targetstr = new RegExp(ignore[i],'g');
		str = str.replace(targetstr,"##");
	}
	return str;
}


//将本地字符转换成ascii
function native2ascii(strNative) {

    var output = "";

    for (var i=0; i<strNative.length; i++) {

        var c = strNative.charAt(i);

        var cc = strNative.charCodeAt(i);

        if (cc > 0xff)

          output += "\\u" + toHex(cc >> 8) + toHex(cc & 0xff);

        else

          output += c;

    }

    return output;

}

//将ascii转换成本地字符
function ascii2native(strAscii) {
    var output = "";
    var posFrom = 0;
    var posTo = strAscii.indexOf("\\u", posFrom);
    while (posTo >= 0) {
        output += strAscii.substring(posFrom, posTo);
        output += toChar(strAscii.substr(posTo, 6));
        posFrom = posTo + 6;
        posTo = strAscii.indexOf("\\u", posFrom);
    }
    output += strAscii.substr(posFrom);
    return output;
}

function toChar(str) {
    if (str.substr(0, 2) != "\\u") return str;
    var code = 0;
    for (var i=2; i<str.length; i++) {
        var cc = str.charCodeAt(i);
        if (cc >= 0x30 && cc <= 0x39)
            cc = cc - 0x30;
        else if (cc >= 0x41 && cc <= 0x5A)
            cc = cc - 0x41 + 10;
        else if (cc >= 0x61 && cc <= 0x7A)
            cc = cc - 0x61 + 10;
        code <<= 4;
        code += cc;
    }
    if (code < 0xff) return str;
    return String.fromCharCode(code);
}

var hexChars = "0123456789ABCDEF";

function toHex(n) {

     var nH = (n >> 4) & 0x0f;

     var nL = n & 0x0f;

     return hexChars.charAt(nH) + hexChars.charAt(nL);

}

/****base64编解码***/
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1,
		63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1,
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
		20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
		32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
		50, 51, -1, -1, -1, -1, -1);
//客户端Base64编码
function base64encode(str) {
	var out, i, len;
	var c1, c2, c3;
	len = str.length;
	i = 0;
	out = "";
	while (i < len) {
		c1 = str.charCodeAt(i++) & 0xff;
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt((c1 & 0x3) << 4);
			out += "==";
			break;
		}
		c2 = str.charCodeAt(i++);
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt(((c1 & 0x3) << 4)
					| ((c2 & 0xF0) >> 4));
			out += base64EncodeChars.charAt((c2 & 0xF) << 2);
			out += "=";
			break;
		}
		c3 = str.charCodeAt(i++);
		out += base64EncodeChars.charAt(c1 >> 2);
		out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
		out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
		out += base64EncodeChars.charAt(c3 & 0x3F);
	}
	return out;
}

//客户端Base64解码
function base64decode(str) {
	var c1, c2, c3, c4;
	var i, len, out;

	len = str.length;
	i = 0;
	out = "";
	while (i < len) {
		/* c1 */
		do {
			c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && c1 == -1);
		if (c1 == -1)
			break;

		/* c2 */
		do {
			c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
		} while (i < len && c2 == -1);
		if (c2 == -1)
			break;

		out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

		/* c3 */
		do {
			c3 = str.charCodeAt(i++) & 0xff;
			if (c3 == 61)
				return out;
			c3 = base64DecodeChars[c3];
		} while (i < len && c3 == -1);
		if (c3 == -1)
			break;

		out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

		/* c4 */
		do {
			c4 = str.charCodeAt(i++) & 0xff;
			if (c4 == 61)
				return out;
			c4 = base64DecodeChars[c4];
		} while (i < len && c4 == -1);
		if (c4 == -1)
			break;
		out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
	}
	return out;
}
