/*!
 *oasis v1.2.3 
 *concat at 2016-05-03 
 * create by ued 
 */
;
(function(global) {

	if (global.oasis) {
		return;
	}

	var oasis = global.oasis = {
		version: '0.1dev'
	};

	var config = oasis.config = {
		debug: true
	};

	var DIRNAME_RE = /[^?#]*\//;

	function dirname(path) {
		return path.match(DIRNAME_RE)[0]
	}

	var doc = window.document,
		loc = window.location,
		cwd = dirname(loc.href),
		ArrayProto = Array.prototype,
		ObjProto = Object.prototype,
		FunProto = Function.prototype,
		LocalStorage = window.localStorage;


	oasis.indexOf = function(target, arr){
		if(ArrayProto.indexOf){
			return arr.indexOf(target);
		}else{
			for(var i = 0, len = arr.length; i < len; i++){
				if(arr[i] === target){
					return i;
				}
			}
		}

		return -1;
	};
	/**
	 *  @param {Function} 地图显示和查询方法.
	 *	显示功能包括：包括经纬度、TGDID、地址、基站。
	 *  查询功能包括：经纬度、地址。
	 *  @param {Oject} [options]存数相关数据。
	 *  由于地图组件要求跨域访问，所以IE暂不支持。
	 */

	oasis.openMap= function(options){

	   if(options==undefined){
			options="";
		}
		if(options.url){
			LocalStorage.setItem("getMapURL",escape(options.url));
		}else{
			LocalStorage.setItem("getMapURL",escape(""));
		}
        OasShyPage.open({
	        title: '地图定位',
	        hasToggle: false,
	        closeable: true,
	        refresh:false,
	        width: 1000,
	        src:oasis.oasisDir+'../oasis_tpl/map.html',
	        onLoad: function($el, win){
	        	win.innerFunction(options);// 调用半遮页里的innerFunction方法。
	        },
	        onClose: function(el, innerWin) {
				$('.oasicon-map-location').parent().parent().removeClass("oas-current");
			}
 		});
	};
	// 获取oasis的路径.
	var scripts = doc.getElementsByTagName('script'),
		oasisScript = doc.getElementById('oasisnode'),
		src,
		oasisDir;

	if (!oasisScript) {
		for (var i = 0, len = scripts.length; i < len; i++) {
			src = scripts[i].hasAttribute ? scripts[i].src : scripts[i].getAttribute("src", 4);
			if (src.indexOf('oasis') !== -1) {
				oasisDir = dirname(src);
				break;
			}
		}
	}

	oasis.oasisDir = oasisDir;


	var isType = function(type) {
		return function(object) {
			return ObjProto.toString.call(object) === "[object " + type + "]";
		}
	}

	var type = oasis.type = {};

	var isObject = type.isObject = isType("Object");
	var isString = type.isString = isType("String");
	var isArray = type.isArray = Array.isArray || isType("Array");
	var isFunction = type.isFunction = isType("Function");

	var support = oasis.support = {
		"placeholder": !! ("placeholder" in document.createElement('input'))
	}
	/**
	 * 使一个函数在规定的时间间隔内只执行一次（函数节流）.
	 * @param {Function} 需要节流的方法.
	 * @param {Object} [context] 运行方法的上下文
	 * @param {Number} [ms] 运行方法的间隔时间，-1时节流不生效，默认为150.
	 * @return {Function} 返回一个方法用以节流调用fn函数。
	 */
	oasis.throttle = function(fn, ms, context) {
		ms = ms || 150;

		if (ms === -1) {
			return (function() {
				fn.apply(context || this, arguments);
			});
		}

		var start = new Date().getTime();
		return (function() {
			var now = new Date().getTime();
			if (now - start > ms) {
				start = now;
				fn.apply(context || this, arguments);
			}
		});
	};


	oasis.stringFormat = function(){
		var a = arguments;
	    if (a.length === 0) {
	        return this;
	    }
	    return this.replace(/{(\d+)}/g, function(c, b) {
	        return typeof a[b] != "undefined" ? a[b] : c;
	    });
	};


	/*
	 * 移除数组中的一项
	 * @param target 需要删除的目标。
	 * @param array 需要删除的数组。
	 * @return 返回新的数组。
	 */
	oasis.erase = function(target, array) {
		for (var i = 0, arrLen = array.length; i < arrLen; i++) {
			if (target === array[i]) {
				array.splice(i, 1);
				return array;
			}
		};
	};

	/*
	 * 在数组中添加一项
	 * @param target 需要添加的项
	 * @param index 需要添加的位置
	 * @param array 需要添加的数组
	 * @return 返回添加后的数组
	 */
	oasis.addArrItem = function(target, index, array) {
		var array = $.extend(true, [], array),
			tempfront = array.splice(0, index),
			tempfinale = array;
		tempfront.push(target);

		return tempfront.concat(tempfinale);
	};

	// var private_loader = document.createElement('div');
	// var private_body = document.getElementsByTagName('body')[0];

	// private_loader.setAttribute('data-oasloader', 'loader');
	// private_body && private_body.appendChild(private_loader);

	var header = document.getElementsByTagName('head')[0];


	/*
     * 获取不跨域的最顶层的window对象
     * @return 最顶层的window对象
	 **/
	oasis.getTopWindow = function() {
		var twin = window;
		while(twin.parent && twin.parent != twin){
			try {
				if(twin.parent.document.domain != document.domain){
					break;
				}
			} catch(e){
				break;
			}

			twin = twin.parent;
		}

		return twin;
	};


	/*
	 * 动态加载js
	 */
	var loadJS = oasis.loadJS = function(url, fn) {

		// if (!private_body) {
		// 	private_body = document.getElementsByTagName('body')[0];
		// 	private_body.appendChild(private_loader);
		// }

		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.charset = 'UTF-8';

		if (script.readyState) { // IE浏览器
			script.onreadystatechange = function() {
				if (script.readyState == 'loaded' || script.readyState == "complete") {
					script.onreadystatechange = null;
					fn && fn();
				}
			}
		} else {
			script.onload = function() {
				fn && fn();
			}
		}

		script.src = url;
		//private_loader.appendChild(script);
		header.appendChild(script);
	};


	var loadCss = oasis.loadCss = function(url) {
		var link = document.createElement('link');
		link.type = "text/css";
		link.rel = "stylesheet";
		link.href = url;
		document.getElementsByTagName('head')[0].appendChild(link);
	};


	/*
     * 加载一个html片段模版
     * @param url 需要加载的内容url
     * @param jqObj 需要将内容注入的区域jquery对象
     * @注入完成后需要执行的回调函数
	 */
	oasis.loadTpl = function(jqObj, url, callback){
		jqObj.load(url, function(data){
			if(data.indexOf('placeholder') !== -1){
				$(':[placeholder]').oasInput();
			}else if(data.indexOf('select') !== -1){
				$('select').oasSelect();
			}

			callback && callback();
		});
	};


	oasis.DBC2SBC = function(str){
		var result = "";
		if(!str){
			return result;
		}
		for(var i = 0;i<str.length;i++){
			code = str.charCodeAt(i);
			if(code>=65281&&code<=65373){
				result += String.fromCharCode(str.charCodeAt(i)-65248);
			}else if(code==12288){
				result += String.fromCharCode(str.charCodeAt(i)-12288+32);
			}else{
				result += str.charAt(i);
			}
		}
		return result;
	};


	/**
	 * 提供兼容性的本地存储
	 */
	if (!LocalStorage) {
		LocalStorage = {
			userData: null,
			name: location.hostname,
			init: function() {
				if (!LocalStorage.userData) {
					try {
						LocalStorage.userData = document.createElement("INPUT");
						LocalStorage.userData.type = "hidden";
						LocalStorage.userData.style.display = "none";
						LocalStorage.userData.addBehavior("#default#userData");
						var expires = new Date();
						expires.setDate(expires.getDate() + 365);
						LocalStorage.userData.expires = expires.toUTCString();
					} catch (e) {
						return false;
					}
				}
				return true;
			},
			setItem: function(key, value) {
				if (LocalStorage.init()) {
					LocalStorage.userData.load(LocalStorage.name);
					LocalStorage.userData.setAttribute(key, value);
					LocalStorage.userData.save(LocalStorage.name);
				}
			},
			getItem: function(key) {
				if (LocalStorage.init()) {
					LocalStorage.userData.load(LocalStorage.name);
					return LocalStorage.userData.getAttribute(key);
				}
			},
			remove: function(key) {
				if (LocalStorage.init()) {
					LocalStorage.userData.load(LocalStorage.name);
					LocalStorage.userData.removeAttribute(key);
					LocalStorage.userData.save(LocalStorage.name);
				}

			}
		};
	}

	oasis.LocalStorage = this.LocalStorage = LocalStorage;

	/*
	 * 判断浏览器是否支持console对象，log->alert, 增加console.time和console.timeEnd
	 * 如果不支持（IE且未打开调试工具时，将console.log指向window.alert）
	 */
	window.oasis_property = {};
	/*if (!window.console) {
		window.console = {};

		window.console.log = function(msg) {
			window.alert(msg);
		}
	}*/

	// 重置掉console.
	window.console || (window.console = {log: function(){}, error: function(){}});

	if (!window.console.time) {
		window.console.time = function(name) {
			window.oasis_property[name] = +new Date();
		}

		window.console.timeEnd = function(name) {
			var tempTime = +new Date();
			window.console.log(name + '---耗时：' + (tempTime - window.oasis_property[name]) + 'ms');
		}
	}

	// 浏览器判断
	var ua = (window.navigator.userAgent || "").toLowerCase(),
		isIE7 = ua.indexOf("msie 7") !== -1,
		isIE8 = ua.indexOf("msie 8") !== -1;

	oasis.browser = {
		isIE: ua.indexOf("msie") !== -1,
		isIE7: isIE7,
		isIE8: isIE8
	};


	$.fn.addBack = $.fn.addBack || $.fn.andSelf;

	$.fn.extend({

		actual: function(method, options) {
			// check if the jQuery method exist
			if (!this[method]) {
				throw '$.actual => The jQuery method "' + method + '" you called does not exist';
			}

			var defaults = {
				absolute: false,
				clone: false,
				includeMargin: false
			};

			var configs = $.extend(defaults, options);

			var $target = this.eq(0);
			var fix, restore;

			if (configs.clone === true) {
				fix = function() {
					var style = 'position: absolute !important; top: -1000 !important; ';

					// this is useful with css3pie
					$target = $target.
					clone().
					attr('style', style).
					appendTo('body');
				};

				restore = function() {
					// remove DOM element after getting the width
					$target.remove();
				};
			} else {
				var tmp = [];
				var style = '';
				var $hidden;

				fix = function() {
					// get all hidden parents
					$hidden = $target.parents().addBack().filter(':hidden');
					style += 'visibility: hidden !important; display: block !important; left: -9999px; top:-9999px;';

					if (configs.absolute === true) style += 'position: absolute !important; ';

					// save the origin style props
					// set the hidden el css to be got the actual value later
					$hidden.each(function() {
						// Save original style. If no style was set, attr() returns undefined
						var $this = $(this);
						var thisStyle = $this.attr('style');

						tmp.push(thisStyle);
						// Retain as much of the original style as possible, if there is one
						$this.attr('style', thisStyle ? thisStyle + ';' + style : style);
					});
				};

				restore = function() {
					// restore origin style values
					$hidden.each(function(i) {
						var $this = $(this);
						var _tmp = tmp[i];

						if (_tmp === undefined) {
							$this.removeAttr('style');
						} else {
							$this.attr('style', _tmp);
						}
					});
				};
			}

			fix();
			// get the actual value with user specific methed
			// it can be 'width', 'height', 'outerWidth', 'innerWidth'... etc
			// configs.includeMargin only works for 'outerWidth' and 'outerHeight'
			var actual = /(outer)/.test(method) ?
				$target[method](configs.includeMargin) :
				$target[method]();

			restore();
			// IMPORTANT, this plugin only return the value of the first element
			return actual;
		}
	});
	

	// 根据浏览器类型，动态加载对应的图标资源。
	// if(oasis.browser.isIE8 || oasis.browser.isIE7){
	// 	$.each($('link'), function(){
	// 		if(this.href.indexOf('oasis-icon.css') !== -1){
	// 			$(this).remove();
	// 		}
	// 	});

	// 	oasis.loadCss(oasis.oasisDir.replace('js/','css') + '/oasis-icon-for-ie.css');
	// }
	
	if (oasis.browser.isIE7) {
		oasis.loadJS(oasis.oasisDir + 'jquery.pseudo.js');
		oasis.loadJS(oasis.oasisDir + 'json2.js');
	} 

	//格式化数字
	var formatNum = function(num){
		var num = num + "";
		num = num.length == 1 ? "0" + num : num;
		num = num.length == 0 ? "00" : num;
		return num;
	};

	//时间格式化
	oasis.formatDate = function(date,fmt){
		if(!(date instanceof Date)) return;
		var year = date.getFullYear(),
			month = date.getMonth() + 1,
			day = date.getDate(),
			hour = date.getHours(),
			minute = date.getMinutes(),
			second = date.getSeconds();
		switch(fmt){
			case "yyyy-MM-dd":
				return year + "-" + formatNum(month) + "-" + formatNum(day);
				break;
			case "yyyy-MM-dd HH:mm:ss":
				return year + "-" + formatNum(month) + "-" + formatNum(day) + " " + formatNum(hour) + ":" + formatNum(minute) + ":" + formatNum(second);
				break;
			default:
				break;
		}
	};

	var getCurrTime = function(){
		var now = new Date();
		return now;
	};

	//获取昨天
	oasis.getYesterday = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-1,0,0,0),new Date(now.getFullYear(),now.getMonth(),now.getDate()-1,23,59,59)];
	};

	//获取今天
	oasis.getToday = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate(),0,0,0),now];
	};	

	//获取近一天
	oasis.getNearOneDay = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-1,0,0,0),now];
	};	

	//获取近三天
	oasis.getNearThreeDays = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-3,0,0,0),now];
	};

	//获取近一周
	oasis.getNearOneWeek = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-7,0,0,0),now];
	};

	//获取近一月 目前近一月按天数减去30算
	oasis.getNearOneMonth = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-30,0,0,0),now];
	};

	//获取近三月 目前近三月按天数减去90算
	oasis.getNearThreeMonths = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-90,0,0,0),now];
	};

	//获取近半年 目前近半年按天数减去180算
	oasis.getNearHalfYear = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear(),now.getMonth(),now.getDate()-180,0,0,0),now];
	};

	//获取近一年
	oasis.getNearOneYear = function(){
		var now = getCurrTime();
		return [new Date(now.getFullYear()-1,now.getMonth(),now.getDate(),0,0,0),now];
	};
})(this);
;
(function(global) {

	oasis.respond = {};
	var timeout,
		winWidth = getWinWidth(),
		winHeight = getWinHeight(),
		respondLiast = oasis.respond.respondLiast = [];

	$(window).resize(function() {
		timeout && clearTimeout(timeout);
		timeout = setTimeout(function() {
			var winNewWidth = getWinWidth();
			var winNewHeight = getWinHeight();

			if (winWidth !== winNewWidth || winHeight !== winNewHeight) {

				var css = format(winNewWidth, winNewHeight);
				var tempClassName = 'w' + css[0] + ' h' + css[1];

				if( document.getElementsByTagName('html')[0].className !== tempClassName){
					document.getElementsByTagName('html')[0].className = 'w' + css[0] + ' h' + css[1];

					if(respondLiast && !!respondLiast.length){
						$.each(respondLiast, function(i){
							respondLiast[i].call(oasis, css[0], css[1]);
						});
					}else if(oasis.type.isFunction(respondLiast)){
						respondLiast.call(oasis, css[0], css[1]);
					}
				}

				winWidth = winNewWidth;
				winHeight = winNewHeight;
			}
		}, 80);
	});

	var css = format(winWidth, winHeight);
	document.getElementsByTagName('html')[0].className = 'w' + css[0] + ' h' + css[1];

	function format(width, height) {
		var tempW, tempH;
		if (width >= 1436) {
			tempW = 1440;
		} else if(width < 1436 && width > 1024 ) {
			tempW = 1024;
		}else{
			tempW = 1000;
		}

		if (height >= 900) {
			tempH = 900;
		} else {
			tempH = 768;
		}

		return [tempW, tempH];
	}

	function getWinHeight() {
		var twin = oasis.getTopWindow();
		return twin.innerHeight || twin.document.documentElement.clientHeight;
	}

	function getWinWidth() {
		var twin = oasis.getTopWindow();
		return twin.innerWidth || twin.document.documentElement.clientWidth;
	}


	oasis.respond.callback = function(fn){
		respondLiast.push(fn);
	} 

})(this);
/*!art-template - Template Engine | http://aui.github.com/artTemplate/*/
!function(){function a(a){return a.replace(t,"").replace(u,",").replace(v,"").replace(w,"").replace(x,"").split(/^$|,+/)}function b(a){return"'"+a.replace(/('|\\)/g,"\\$1").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+"'"}function c(c,d){function e(a){return m+=a.split(/\n/).length-1,k&&(a=a.replace(/\s+/g," ").replace(/<!--.*?-->/g,"")),a&&(a=s[1]+b(a)+s[2]+"\n"),a}function f(b){var c=m;if(j?b=j(b,d):g&&(b=b.replace(/\n/g,function(){return m++,"$line="+m+";"})),0===b.indexOf("=")){var e=l&&!/^=[=#]/.test(b);if(b=b.replace(/^=[=#]?|[\s;]*$/g,""),e){var f=b.replace(/\s*\([^\)]+\)/,"");n[f]||/^(include|print)$/.test(f)||(b="$escape("+b+")")}else b="$string("+b+")";b=s[1]+b+s[2]}return g&&(b="$line="+c+";"+b),r(a(b),function(a){if(a&&!p[a]){var b;b="print"===a?u:"include"===a?v:n[a]?"$utils."+a:o[a]?"$helpers."+a:"$data."+a,w+=a+"="+b+",",p[a]=!0}}),b+"\n"}var g=d.debug,h=d.openTag,i=d.closeTag,j=d.parser,k=d.compress,l=d.escape,m=1,p={$data:1,$filename:1,$utils:1,$helpers:1,$out:1,$line:1},q="".trim,s=q?["$out='';","$out+=",";","$out"]:["$out=[];","$out.push(",");","$out.join('')"],t=q?"$out+=text;return $out;":"$out.push(text);",u="function(){var text=''.concat.apply('',arguments);"+t+"}",v="function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);"+t+"}",w="'use strict';var $utils=this,$helpers=$utils.$helpers,"+(g?"$line=0,":""),x=s[0],y="return new String("+s[3]+");";r(c.split(h),function(a){a=a.split(i);var b=a[0],c=a[1];1===a.length?x+=e(b):(x+=f(b),c&&(x+=e(c)))});var z=w+x+y;g&&(z="try{"+z+"}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:"+b(c)+".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");try{var A=new Function("$data","$filename",z);return A.prototype=n,A}catch(B){throw B.temp="function anonymous($data,$filename) {"+z+"}",B}}var d=function(a,b){return"string"==typeof b?q(b,{filename:a}):g(a,b)};d.version="3.0.0",d.config=function(a,b){e[a]=b};var e=d.defaults={openTag:"<%",closeTag:"%>",escape:!0,cache:!0,compress:!1,parser:null},f=d.cache={};d.render=function(a,b){return q(a,b)};var g=d.renderFile=function(a,b){var c=d.get(a)||p({filename:a,name:"Render Error",message:"Template not found"});return b?c(b):c};d.get=function(a){var b;if(f[a])b=f[a];else if("object"==typeof document){var c=document.getElementById(a);if(c){var d=(c.value||c.innerHTML).replace(/^\s*|\s*$/g,"");b=q(d,{filename:a})}}return b};var h=function(a,b){return"string"!=typeof a&&(b=typeof a,"number"===b?a+="":a="function"===b?h(a.call(a)):""),a},i={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},j=function(a){return i[a]},k=function(a){return h(a).replace(/&(?![\w#]+;)|[<>"']/g,j)},l=Array.isArray||function(a){return"[object Array]"==={}.toString.call(a)},m=function(a,b){var c,d;if(l(a))for(c=0,d=a.length;d>c;c++)b.call(a,a[c],c,a);else for(c in a)b.call(a,a[c],c)},n=d.utils={$helpers:{},$include:g,$string:h,$escape:k,$each:m};d.helper=function(a,b){o[a]=b};var o=d.helpers=n.$helpers;d.onerror=function(a){var b="Template Error\n\n";for(var c in a)b+="<"+c+">\n"+a[c]+"\n\n";"object"==typeof console&&console.error(b)};var p=function(a){return d.onerror(a),function(){return"{Template Error}"}},q=d.compile=function(a,b){function d(c){try{return new i(c,h)+""}catch(d){return b.debug?p(d)():(b.debug=!0,q(a,b)(c))}}b=b||{};for(var g in e)void 0===b[g]&&(b[g]=e[g]);var h=b.filename;try{var i=c(a,b)}catch(j){return j.filename=h||"anonymous",j.name="Syntax Error",p(j)}return d.prototype=i.prototype,d.toString=function(){return i.toString()},h&&b.cache&&(f[h]=d),d},r=n.$each,s="break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",t=/\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,u=/[^\w$]+/g,v=new RegExp(["\\b"+s.replace(/,/g,"\\b|\\b")+"\\b"].join("|"),"g"),w=/^\d[^,]*|,\d[^,]*/g,x=/^,+|,+$/g;e.openTag="{{",e.closeTag="}}";var y=function(a,b){var c=b.split(":"),d=c.shift(),e=c.join(":")||"";return e&&(e=", "+e),"$helpers."+d+"("+a+e+")"};e.parser=function(a,b){a=a.replace(/^\s/,"");var c=a.split(" "),e=c.shift(),f=c.join(" ");switch(e){case"if":a="if("+f+"){";break;case"else":c="if"===c.shift()?" if("+c.join(" ")+")":"",a="}else"+c+"{";break;case"/if":a="}";break;case"each":var g=c[0]||"$data",h=c[1]||"as",i=c[2]||"$value",j=c[3]||"$index",k=i+","+j;"as"!==h&&(g="[]"),a="$each("+g+",function("+k+"){";break;case"/each":a="});";break;case"echo":a="print("+f+");";break;case"print":case"include":a=e+"("+c.join(",")+");";break;default:if(-1!==f.indexOf("|")){var l=b.escape;0===a.indexOf("#")&&(a=a.substr(1),l=!1);for(var m=0,n=a.split("|"),o=n.length,p=l?"$escape":"$string",q=p+"("+n[m++]+")";o>m;m++)q=y(q,n[m]);a="=#"+q}else a=d.helpers[e]?"=#"+e+"("+c.join(",")+");":"="+a}return a},"function"==typeof define?define(function(){return d}):"undefined"!=typeof exports?module.exports=d:this.template=d}();
;
(function(global, $) {

    /**
     * 实现多种定位方式
     * @ author rbai
     * @ create time 2014.08.11
     * @ version  1.0.0
     */

    var Position = {},
        VIEWPORT = {
            _id: 'VIEWPORT',
            nodeType: 1
        },
        isPinFiexd = false, //用来标示目标元素是否为fiex定位
        ua = (window.navigator.userAgent || "").toLowerCase(),
        isIE6 = ua.indexOf("msie 6") !== -1;

    /**
     * 将目标元素相对于基准元素进行定位。
     * @param {Obj} 目标元素
     * @param {obj} 基准元素
     */
    Position.pin = function(pinObject, baseObject,parentNode) {
        //将两个参数转化为标准定位对象 {element: a, x: 0, y: 0}
        pinObject = normalize(pinObject);
        baseObject = normalize(baseObject);


        // 设定目标元素的 position 为绝对定位
        // 若元素的初始 position 不为 absolute，会影响元素的 display、宽高等属性
        var pinElement = $(pinObject.element);

        if (pinElement.css('position') !== 'fixed' || isIE6) {
            pinElement.css('position', 'absolute');
            isPinFixed = false;
        } else {
            // 定位 fixed 元素的标志位，下面有特殊处理
            isPinFixed = true;
        }

        // 将位置属性归一化为数值
        // 注：必须放在上面这句 `css('position', 'absolute')` 之后，
        //    否则获取的宽高有可能不对
        posConverter(pinObject);
        posConverter(baseObject);

        var parentOffset = getParentOffset(pinElement);
        var baseOffset = baseObject.offset();

        // 计算目标元素的位置
        var top = baseOffset.top + baseObject.y -
            pinObject.y - parentOffset.top;

        var left = baseOffset.left + baseObject.x -
            pinObject.x - parentOffset.left;
        if(parentNode){
            top += parentNode.scrollTop();
            left += parentNode.scrollLeft();
        }
        // 定位目标元素
        pinElement.css({
            left: left,
            top: top
        });
    }


    // 将目标元素相对于基准元素进行居中定位
    // 接受两个参数，分别为目标元素和定位的基准元素，都是 DOM 节点类型
    Position.center = function(pinElement, baseElement) {
        Position.pin({
            element: pinElement,
            x: '50%',
            y: '50%'
        }, {
            element: baseElement,
            x: '50%',
            y: '50%'
        });
    };

    /* 相对于基准元素四周定位
     * @param {Object} pinElement 目标元素
     * @param {Object} baseElement 基类元素
     * @param {Object} options 定位配置箱
     * options : {
            direction: 'h', // v
            offset: [0, 0]

       }
     */
    Position.advPin = function(pinElement, baseElement, options) {

        pinElement = $(pinElement), baseElement = $(baseElement);
        // 准备坐标数据
        var baseWidth = baseElement.outerWidth(),
            baseHeight = baseElement.outerHeight(),
            baseLeft = baseElement.offset().left,
            baseTop = baseElement.offset().top,
            pinWidth = pinElement.outerWidth(),
            pinHeight = pinElement.outerHeight(),
            pinLeft = pinElement.offset().left,
            pinTop = pinElement.offset().top,
            scrollTop = $(document).scrollTop(),
            scrollLeft = $(document).scrollLeft(),
            winHeight = $(window).height(),
            winWidth = $(window).width(),
            offset;
        if (arguments.length === 2) {

            options = {
                direction: 'v'
            }
        } else if (arguments.length === 3 && typeof options == 'string') {
            options = {
                direction: options
            }
        }

        if (pinElement.css('position') !== 'fixed') {
            pinElement.css('position', 'absolute');
        }

        //计算偏移量
        if (options.offset) {
            offset = options.offset;
        }
        if (options.direction == 'v') { // 垂直定位
            var tempHeight = winHeight - (baseTop - scrollTop + baseHeight + pinHeight),
                tempTop, tempLeft;
            offset ? (tempTop = baseTop + offset[0], tempLeft = baseLeft + offset[1]) : (tempTop = baseTop, tempLeft = baseLeft);
            if (tempHeight > 0) {
                // 定位到基类元素底部
                pinElement.css({
                    top: (tempTop + baseHeight),
                    left: tempLeft
                });
            } else {
                // 定位到基类元素上面
                if ((baseTop - scrollTop) < pinHeight) {
                    pinElement.css({
                        top: (tempTop + baseHeight),
                        left: tempLeft
                    });
                } else {
                    pinElement.css({
                        top: (tempTop - pinHeight),
                        left: tempLeft
                    });
                }

            }
        } else if (options.direction == 'h') { // 水平定位
            var tempWidth = winWidth - (baseLeft - scrollLeft + baseWidth + pinWidth);
            offset ? (tempTop = baseTop + offset[0], tempLeft = baseLeft + offset[1]) : (tempTop = baseTop, tempLeft = baseLeft);
            if (tempWidth > 0) {
                // 定位到基类元素右边
                pinElement.css({
                    left: (tempLeft + baseWidth),
                    top: tempTop
                });
            } else {
                // 定位到基类元素上面
                if ((baseLeft - scrollLeft) < pinWidth) {
                    pinElement.css({
                        left: (tempLeft + baseWidth),
                        top: tempTop
                    });
                } else {
                    pinElement.css({
                        left: (tempLeft - pinWidth),
                        top: tempTop
                    });
                }

            }
        }



    }

    /*
     * 获取一个元素的位置
     * @param {String} pinElement 需要获取的元素
     * @param {String} type 获取位置方式，支持"fixed",即获取元素相对于window的位置，默认是相对于document的位置
     * return {top:0,right:0,bottom:0,left:0}
     */
    Position.location = function(pinElement, type) {
        pinElement = $(pinElement);
        var pinWidth = pinElement.outerWidth(),
            pinHeight = pinElement.outerHeight(),
            pinLeft = pinElement.offset().left,
            pinTop = pinElement.offset().top,
            scrollTop = $(document).scrollTop(),
            scrollLeft = $(document).scrollLeft(),
            winHeight = $(window).height(),
            winWidth = $(window).width(),
            docHeight = $(document).height(),
            docWidth = $(document).width();

        if (type == 'fixed') {
            return {
                top: (pinTop - scrollTop),
                right: (winWidth - (pinLeft - scrollLeft) - pinWidth),
                bottom: (winHeight - (pinTop - scrollTop) - pinHeight),
                left: (pinLeft - scrollLeft)
            };
        } else {
            return {
                top: pinTop,
                right: (docWidth - pinLeft - pinWidth),
                bottom: (docHeight - pinTop - pinHeight),
                left: pinLeft
            };
        }
    }

    /**
     * 计算两个元素重合的面积
     */
    Position.overlapArea = function(rElement, bElement){

        var rOffset = rElement.offset(),
            rleft = rOffset.left,
            rtop = rOffset.top,
            rwidth = rElement.width(),
            rHeight = rElement.height(),
            bOffset = bElement.offset(),
            bleft = bOffset.left,
            btop = bOffset.top,
            bwidth = bElement.width(),
            bheight = bElement.height();

        if((rleft + rwidth < bleft) || (bleft+bwidth < rleft) || (rtop + rHeight < btop) || (btop+bheight < rtop)){
            return 0;
        }

        // 计算重叠的left，top
        var tempLeft = rleft - bleft,
            overlapLeft,
            tempTop = rtop - btop,
            overlapTop;

        overlapLeft = tempLeft < 0 ? rwidth - Math.abs(tempLeft) : bwidth - tempLeft;

        overlapTop = tempTop < 0 ? rHeight - Math.abs(tempTop) : bheight - tempTop;

        return [overlapLeft, overlapTop];

    };


    // 这是当前可视区域的伪 DOM 节点
    // 需要相对于当前可视区域定位时，可传入此对象作为 element 参数
    Position.VIEWPORT = VIEWPORT;


    global.oasis.Position = global.Position = Position;


    // Helpers
    // -------

    // 将参数包装成标准的定位对象，形似 { element: a, x: 0, y: 0 }

    function normalize(posObject) {
        posObject = toElement(posObject) || {};

        if (posObject.nodeType) {
            posObject = {
                element: posObject
            };
        }

        var element = toElement(posObject.element) || VIEWPORT;
        if (element.nodeType !== 1) {
            throw new Error('posObject.element is invalid.');
        }

        var result = {
            element: element,
            x: posObject.x || 0,
            y: posObject.y || 0
        };

        // config 的深度克隆会替换掉 Position.VIEWPORT, 导致直接比较为 false
        var isVIEWPORT = (element === VIEWPORT || element._id === 'VIEWPORT');

        // 归一化 offset
        result.offset = function() {
            // 若定位 fixed 元素，则父元素的 offset 没有意义
            if (isPinFixed) {
                return {
                    left: 0,
                    top: 0
                };
            } else if (isVIEWPORT) {
                return {
                    left: $(document).scrollLeft(),
                    top: $(document).scrollTop()
                };
            } else {
                return getOffset($(element)[0]);
            }
        };

        // 归一化 size, 含 padding 和 border
        result.size = function() {
            var el = isVIEWPORT ? $(window) : $(element);
            return {
                width: el.outerWidth(),
                height: el.outerHeight()
            };
        };

        return result;
    }

    // 对 x, y 两个参数为 left|center|right|%|px 时的处理，全部处理为纯数字

    function posConverter(pinObject) {
        pinObject.x = xyConverter(pinObject.x, pinObject, 'width');
        pinObject.y = xyConverter(pinObject.y, pinObject, 'height');
    }

    // 处理 x, y 值，都转化为数字

    function xyConverter(x, pinObject, type) {
        // 先转成字符串再说！好处理
        x = x + '';

        // 处理 px
        x = x.replace(/px/gi, '');

        // 处理 alias
        if (/\D/.test(x)) {
            x = x.replace(/(?:top|left)/gi, '0%')
                .replace(/center/gi, '50%')
                .replace(/(?:bottom|right)/gi, '100%');
        }

        // 将百分比转为像素值
        if (x.indexOf('%') !== -1) {
            //支持小数
            x = x.replace(/(\d+(?:\.\d+)?)%/gi, function(m, d) {
                return pinObject.size()[type] * (d / 100.0);
            });
        }

        // 处理类似 100%+20px 的情况
        if (/[+\-*\/]/.test(x)) {
            try {
                // eval 会影响压缩
                // new Function 方法效率高于 for 循环拆字符串的方法
                // 参照：http://jsperf.com/eval-newfunction-for
                x = (new Function('return ' + x))();
            } catch (e) {
                throw new Error('Invalid position value: ' + x);
            }
        }

        // 转回为数字
        return numberize(x);
    }

    // 获取 offsetParent 的位置

    function getParentOffset(element) {
        var parent = element.offsetParent();

        // IE7 下，body 子节点的 offsetParent 为 html 元素，其 offset 为
        // { top: 2, left: 2 }，会导致定位差 2 像素，所以这里将 parent
        // 转为 document.body
        if (parent[0] === document.documentElement) {
            parent = $(document.body);
        }

        // 修正 ie6 下 absolute 定位不准的 bug
        if (isIE6) {
            parent.css('zoom', 1);
        }

        // 获取 offsetParent 的 offset
        var offset;

        // 当 offsetParent 为 body，
        // 而且 body 的 position 是 static 时
        // 元素并不按照 body 来定位，而是按 document 定位
        // http://jsfiddle.net/afc163/hN9Tc/2/
        // 因此这里的偏移值直接设为 0 0
        if (parent[0] === document.body &&
            parent.css('position') === 'static') {
            offset = {
                top: 0,
                left: 0
            };
        } else {
            offset = getOffset(parent[0]);
        }

        // 根据基准元素 offsetParent 的 border 宽度，来修正 offsetParent 的基准位置
        offset.top += numberize(parent.css('border-top-width'));
        offset.left += numberize(parent.css('border-left-width'));

        return offset;
    }

    function numberize(s) {
        return parseFloat(s, 10) || 0;
    }

    function toElement(element) {
        return $(element)[0];
    }

    // fix jQuery 1.7.2 offset
    // document.body 的 position 是 absolute 或 relative 时
    // jQuery.offset 方法无法正确获取 body 的偏移值
    //   -> http://jsfiddle.net/afc163/gMAcp/
    // jQuery 1.9.1 已经修正了这个问题
    //   -> http://jsfiddle.net/afc163/gMAcp/1/
    // 这里先实现一份
    // 参照 kissy 和 jquery 1.9.1
    //   -> https://github.com/kissyteam/kissy/blob/master/src/dom/sub-modules/base/src/base/offset.js#L366 
    //   -> https://github.com/jquery/jquery/blob/1.9.1/src/offset.js#L28

    function getOffset(element) {
        var box = element.getBoundingClientRect(),
            docElem = document.documentElement;

        // < ie8 不支持 win.pageXOffset, 则使用 docElem.scrollLeft
        return {
            left: box.left + (window.pageXOffset || docElem.scrollLeft) - (docElem.clientLeft || document.body.clientLeft || 0),
            top: box.top + (window.pageYOffset || docElem.scrollTop) - (docElem.clientTop || document.body.clientTop || 0)
        };
    }

})(this, jQuery);
;
(function(global, $, undefined) {
	/**
	 * 所有ui组件的核心工厂方法。
	 * @ author rbai
	 * @ create time 2014.07.20
	 * @ version  1.0.0
	 */

	/*
	 * @param name即是组件名，示例oasDialog
	 * @param prototype为name组件对应的原型方法
	 */
	$.oasUiFactory = function(name, prototype) {

		var uiName = name;

		// 给组件创建选择器
		$.expr[":"][name] = function(elem) {
			return !!$.data(elem, name);
		}

		$['oasis'] = $['oasis'] || {};

		// 组件的构造器
		$['oasis'][name] = function(options, element) {

			if (arguments.length) {
				this._createOasUi(options, element);
			}
		}


		// 属性的继承
		var basePrototype = new $.OasUiFactory();

		basePrototype.options = $.extend(true, {}, basePrototype.options);

		$['oasis'][name].prototype = $.extend(true, basePrototype, {

			namespace: 'oasis',
			uiName: uiName,
			widgetBaseClass: name

		}, prototype);

		$.oasUiFactory.bridge(name, $['oasis'][name]);
	}

	$.oasUiFactory.bridge = function(name, object) {

		$.fn[name] = function(options, element) {

			var query = arguments[0],
				methodInvoked = (typeof query == 'string'),
				queryArguments = [].slice.call(arguments, 1),
				returnValue = $(this);



			// 方法调用
			if (methodInvoked) {

				this.each(function() {
					var instance = $.data(this, name);
					if (!instance) throw Error(name + '还没有被初始化');

					if (instance.invoke[query]) {
						var methodValue = instance.invoke[query].apply(instance, queryArguments);
						if (methodValue !== instance && methodValue !== undefined) {
							returnValue = methodValue;
							return false;
						}
					} else {
						console.warn(query + '---方法不存在');
						console.info(name + ' 组件你可以调用以下方法');
						if (console.table) {
							console.table(instance.invoke);
						} else {
							console.info(instance.invoke);
						}
					}
				});

			} else {
				// 初始化
				this.each(function() {
					var instance = $.data(this, name);

					if (!instance) {
						$.data(this, name, new object(options, this));
					}
				});

			}

			return returnValue;
		}
	}

	$.OasUiFactory = function(options, element) {


		if (arguments.length) {
			this._createOasUi(options, element);
		}
	}


	$.OasUiFactory.prototype = {

		_createOasUi: function(options, element) {

			// 缓存数据

			this.$el = $(element);

			// 合并所有的配置项，包括绑定在dom节点上的。
			var options = this.options = $.extend(true, {}, this.options, this._getElementOptions(), options);

			this.onCreate = options.onCreate;

			// modify by rbai @2015-05-07 
			// 新增组件是否已经创建完成标识。
			this.__isCreated__ = false;

			// 有模版文件则执行模版文件,并将预编译的模版文件存放到this.tpl中
			if (this._template) {
				this.tpl = this._template();
			}

			// 执行_create方法
			var _createValue = this._create();

			this.__isCreated__ = true;
			// 触发_create事件
			if (this.onCreate) {
				this.onCreate.call(this);
			}

			// 如果_create方法返回false，则退出创建UI对象。
			if (_createValue === false) {
				return false;
			}
			// 绑定事件
			this._bindUiEvents();


			// 覆盖默认的对外方法。
			this.invoke = $.extend(true, {
				show: function() {
					this.$el.show();
				},

				hide: function() {
					this.$el.hide();
				},

				destory: function() {
					this.$el.unbind('.' + this.uiName).removeData(this.uiName);

					$(document).unbind('.' + this.uiName);
				},


				// 获取(设置)一个组件对象的option.key
				option: function(key){
					if(typeof key === 'string'){
						return this.options[key];
					}
				}

			}, this.invoke);
		},

		// 从元素中获取配置项。
		_getElementOptions: function() {

			var options = this.$el.data(),
				reg = new RegExp('^' + this.uiName, 'i'),
				tempOptions = {},
				tempDataName;

			for (var dataName in options) {
				if (reg.test(dataName)) {
					tempDataName = dataName.replace(reg, '');
					tempDataName = tempDataName.charAt(0).toLowerCase() + tempDataName.substr(1);
					tempOptions[tempDataName] = options[dataName];
				}
			}

			return tempOptions;
		},

		_create: function() {

		},


		/*
		 * 绑定事件，在具体的组件类里需要有events对象
		 * events: {
		 *	  'click li': '_liClick',
		 *    'hover .item': '_itemHover'
		 * }
		 */
		_bindUiEvents: function() {

			var $el = this.$el,
				self = this,
				_evetsTarget,
				_evetsType,
				_eventsArr,
				_event_callback,
				expr = /(\S+)\s(.+)/;

			if (this.events) {
				for (var _evets in this.events) {
					_eventsArr = _evets.match(expr);
					_evetsTarget = _eventsArr[2];
					_eventsType = _eventsArr[1];

					_event_callback = null;
					if (oasis.type.isFunction(this.events[_evets])) {

						_event_callback = this.events[_evets];
					} else {
						_event_callback = this[this.events[_evets]];
					}

					(function(_callback) {
						$el.on(_eventsType + '.' + self.uiName, _evetsTarget, function(evt) {
							if (_callback.call(self, this, evt) === false) {
								return false;
							}
						});
					})(_event_callback);

				}

				_eventsArr = null;
				_evetsTarget = null;
				_eventsType = null;
				_event_callback = null;
			}
		},

		/*
		 * 调用对外方法。
		 * @param {string} callname 对外方法函数名。
		 * @param {Array} 需要传递的参数。
		 * @return 返回对外方法的返回值。
		 * this.invokeCall('show', [akdlaskda,jja]);
		 */
		_iCall: function(callname, argumentA) {
			var result = argumentA ? this.invoke[callname].apply(this, argumentA) : this.invoke[callname].apply(this);
			return result;
		},

		/**
		 * 触发事件函数。
		 * @param {string} 需要触发的事件名
		 * @param {Array} 需要传递的参数。
		 * this._emit('onSelect', [id, value])
		 */
		_emit: function(eventname, argumentA) {
			if(oasis.type.isString(this.options[eventname])){
				return argumentA ? window[this.options[eventname]].apply(this.$el, argumentA) : window[this.options[eventname]].apply(this.$el);
			}
			var result = argumentA ? this.options[eventname].apply(this.$el, argumentA) : this.options[eventname].apply(this.$el);
			//this.$el.trigger(eventname.replace(/on/i, '').toLocaleLowerCase(), argumentA);
			return result;
		}
	};

})(this, jQuery);
;
(function() {

	$.oasUiFactory("oasDrag", {
		options: {

			// 拖拽触发的把手
			handle: null,

			// 可以拖拽的方向，为null时，不限制
			axis: null,

			// 拖拽的放置区域
			drop: null,

			// 可拖拽的范围
			scope: null,

			// 定位方式，可以为absoulte（边界为document） fixed（边界为window）
			position: 'absolute',

			// 是否只能在自己的父节点里
			inParent: true,

			// 是否需要代理。
			// 为null时，不需要代理。
			// 为true时代理直接copy一份$le。
			// 为具体的元素时，代理为该元素。
			proxy: null,

			// 拖拽开始事件
			onStart: function() {

			},
			// 拖拽中事件
			onDrag: function() {

			},

			// 拖拽结束事件
			onEnd: function() {

			}

		},


		_create: function() {

			this._initElementStyle();

			this._bindEvent();
		},

		_initElementStyle: function() {

			var self = this,
				$el = this.$el,
				options = this.options,
				$handle = $el.find(options.handle),
				$scope,
				axis = options.axis;

			if (options.scope) {
				if ($el.parents(options.scope).size() > 0) {
					$scope = $el.parents(options.scope);
				}
			}

			$el.addClass('oas-drag').css('position', options.position);

			if ( !! $handle.size()) {
				$handle.addClass('oas-drag-handle');
				this.$handle = $handle;
			} else {
				$el.addClass('oas-drag-handle');
				this.$handle = $el;
			}

			if (axis) {
				$el.data('oas-drag-axis', axis);
			}

			// 设定拖拽
			if ($scope) {
				$el.data('oas-drag-scope', $scope)
					.data('oas-drag-scope-left', $scope.position().left)
					.data('oas-drag-scope-top', $scope.position().top);
			}
		},

		_bindEvent: function() {
			var self = this,
				startX,
				startY,
				$el = this.$el,
				options = this.options;

			// 拖拽开始
			this.$handle.on('mousedown.' + self.uiName, function(e) {

				if(e.button == 2){
					return;
				}

				var $this = $(this),
					startX = e.clientX,
					startY = e.clientY,
					distanceX = 0,
					distanceY = 0,
					$drag = $this.parents('.oas-drag').size() > 0 ? $this.parents('.oas-drag') : $this,
					startLeft = $drag.position().left,
					startTop = $drag.position().top,
					nowLeft = startLeft,
					nowTop = startTop,
					$scope = $drag.data('oas-drag-scope');

				e.preventDefault();

				if(options.proxy === true){
					var $source = $drag;
					$drag = $drag.clone(true).addClass('oas-drag-proxy');
					$source.after($drag);

				}

				this.onselectstart = function() {
					return false;
				};

				// 解决在下面有iframe时拖动卡顿的问题 fixed at 2014/10/13 by rbai
				var $dragmask = $('<div style="position:fixed;left:0;top:0;right:0;bottom:0;"></div>');
				$('body').append($dragmask);

				$(document).on('mousemove.' + self.uiName, function(e) {
					e.preventDefault();

					this.setCapture && this.setCapture();

					var currentX = e.clientX,
						currentY = e.clientY;

					distanceX = currentX - startX;
					distanceY = currentY - startY;

					switch ($drag.data('oas-drag-axis')) {
						case 'x':
							distanceY = 0;
							nowLeft = startLeft + distanceX;
							nowTop = startTop;
							break;
						case 'y':
							distanceX = 0;
							nowLeft = startLeft;
							nowTop = startTop + distanceY;
							break;
						default:
							nowLeft = startLeft + distanceX;
							nowTop = startTop + distanceY;
							break;
					}

					// 有自定义边界
					if ($drag.data('oas-drag-scope')) {
						nowTop -= $drag.data('oas-drag-scope-top');
						nowLeft -= $drag.data('oas-drag-scope-left');
					}

					var position = self._boundDetection($drag, {
						left: nowLeft,
						top: nowTop
					});

					nowLeft = position.left;
					nowTop = position.top;

					$drag.css({
						left: nowLeft,
						top: nowTop
					});

					self._emit('onDrag', [self.$el, [nowLeft, nowTop], [distanceX, distanceY]]);
					//self.options.onDrag.call(self.$el, [nowLeft, nowTop], [distanceX, distanceY]);

				}).on('mouseup.' + self.uiName, function(e) {
					$dragmask.remove();
					this.releaseCapture && this.releaseCapture();
					$(document).off('mousemove.' + self.uiName).off('mouseup.' + self.uiName);

					if(distanceX !== 0 || distanceY !== 0){
						self._emit('onEnd', [self.$el, [nowLeft, nowTop], [distanceX, distanceY]]);
					}

					if(options.proxy === true){
						$drag.stop().animate({left: $source.position().left, top: $source.position().top}, 300, function(){
							$drag.remove();
						});
					}
					//self.options.onEnd.call(self.$el, [nowLeft, nowTop], [distanceX, distanceY]);
				});

				self._emit('onStart', [self.$el, [nowLeft, nowTop]]);
				//self.options.onStart.call(self.$el, [nowLeft, nowTop]);

			});
		},

		/*
		 * 检测边界,
		 * @$drag 拖拽元素
		 * @position 当前位置
		 */
		_boundDetection: function($drag, position) {
			var left = position.left,
				top = position.top,
				width = $drag.outerWidth(),
				height = $drag.outerHeight(),
				$scope = $drag.data('oas-drag-scope'),
				scopeLeft,
				scopeTop,
				tempPosition = position,
				options = this.options;

			if ($drag.hasClass('oas-dialog')) {
				height += 4;
			}
			// 无自定义边界范围
			if (!$scope || !options.inParent) {
				if (options.position === 'absolute') {

					if (left + width >= $(document).width()) {
						tempPosition.left = $(document).width() - width;
					}

					if (top + height >= $(document).height()) {
						tempPosition.top = $(document).height() - height;
					}

				} else {

					if (left + width >= $(window).width()) {
						tempPosition.left = $(window).width() - width;
					}
					if (top + height >= $(window).height()) {
						tempPosition.top = $(window).height() - height;
					}

					if (left < 0) {
						tempPosition.left = 0;
					}

					if (top < 0) {
						tempPosition.top = 0;
					}

				}
			} else if (options.inParent && $scope) {
				if (left <= 0) tempPosition.left = 0;
				if (top <= 0) tempPosition.top = 0;

				scopeLeft = $drag.data('oas-drag-scope-left'),
				scopeTop = $drag.data('oas-drag-scope-top');
				if ((left + width >= $scope.outerWidth())) {
					tempPosition.left = $scope.outerWidth() - width;
				}

				if ((top + height >= $scope.outerHeight())) {
					tempPosition.top = $scope.outerHeight() - height;
				}


				tempPosition.left = tempPosition.left < 0 ? tempPosition.left / 2 : tempPosition.left; 
				tempPosition.top = tempPosition.top < 0 ? tempPosition.top / 2 : tempPosition.top; 
			}

			return tempPosition;
		},

		// 对外方法集
		invoke: {
			destory: function() {
				this.$el.removeData().off(this.uiName);
			}
		}
	});


})();
;
(function() {
	/**
	 * 提供浮出层的基本功能，包括显示方式，位置定位，resize位置调整等。
	 * @ author rbai
	 * @ create time 2014.07.30
	 * @ version  1.0.0
	 */
	$.oasUiFactory("oasOverlay", {
		options: {

			// 触发类型,默认为hover，也可以为click
			triggerType: 'hover',

			// 触发类型为hover时的延时时间
			delay: 150,

			// 触发的元素
			target: null,

			// 定位参数
			align: {
				// element 的定位点，默认为左上角
				selfXY: [0, 0],
				// 基准定位元素，默认为当前的可视区域
				baseElement: Position.VIEWPORT,
				// 基准定位元素的定位点，默认为左上角
				baseXY: [0, 0]
			},

			// 父元素
			parentNode: document.body,

			// 是否需要高级定位
			advPin: false,

			// 是否需要点击其他地方消失
			blurHide: true,

			//是否需要展示时的动画
			isNeedAnimation:false,
			onOpen: function() {

			},

			beforeOpen: function() {

			},

			onClose: function() {

			}

		},


		_create: function() {
			var self = this,
				options = this.options,
				$targetElement = $(options.target),
				delay = options.delay,
				triggerType = options.triggerType;
			this.target = $targetElement;
			oasOverlay.allOverlays.push(this);
			this.$el.addClass('oas-overlay');
			// 支持hover
			if (triggerType === 'hover' && $targetElement) {
				$targetElement.on('mouseenter.' + this.uiName, function(e) {
					if ($(this).hasClass('disabled')) return;
					clearTimeout(self.hideTime);
					self.target = this;
					self.showTime = setTimeout(function() {
						self._iCall('show');
					}, delay);
				}).on('mouseleave.' + this.uiName, function() {
					if ($(this).hasClass('disabled')) return;
					clearTimeout(self.showTime);
					self.hideTime = setTimeout(function(e) {
						self._iCall('hide');
					}, delay);
				});


				this.$el.on('mouseenter.' + this.uiName, function() {
					clearTimeout(self.hideTime);
				});

				this.$el.on('mouseleave.' + this.uiName, function() {
					self.hideTime = setTimeout(function() {
						self._iCall('hide');
					}, delay);
				});
			} else if (triggerType === 'click' && $targetElement) {

				$targetElement.context = $targetElement.context && $targetElement.context != undefined? $targetElement.context : $targetElement.parent();
				$targetElement.live('click.' + this.uiName, function() {
					if ($(this).hasClass('disabled')) return;
					self.target = this;
					self._iCall('show');
				});
			}else if (triggerType === 'focus' && $targetElement) {

				$targetElement.on('focus.' + this.uiName, function() {
					if ($(this).hasClass('disabled')) return;
					self.target = this;
					self._iCall('show');
				});

				// 联想下拉blur引发bug.

				// $targetElement.on('blur', function() {
				// 	if ($(this).hasClass('disabled')) return;
				// 	self.target = this;
				// 	//self._iCall('hide');
				// });

			}

			this._blurHide();
		},


		_setPosition: function(align) {

			var options = this.options,
				advPin = options.advPin;

			align || (align = options.align);

			if (!align) return;

			if (advPin) {
				this._setAdvPosition(align, advPin);
			} else {
				this._setNomalPosition(align);
			}
		},


		/*
		 * 普通定位
		 */
		_setNomalPosition: function(align) {
			var $el = this.$el,
				isHidden = $el.css('display') === 'none',
				parentNode = this.options.parentNode,
				baseXY = align.baseXY,
				selfXY = align.selfXY;

			//在定位时，为避免元素高度不定，先显示出来

			if (isHidden) {
				$el.css({
					visible: 'hidden',
					display: 'block'
				});
			}
			if(parentNode !== document.body && parentNode !== $(document.body)){
				Position.pin({
					element: $el,
					x: selfXY[0],
					y: selfXY[1]
				}, {
					element: this.target,
					x: baseXY[0],
					y: baseXY[1]
				},parentNode);
			}else{
				Position.pin({
					element: $el,
					x: selfXY[0],
					y: selfXY[1]
				}, {
					element: this.target,
					x: baseXY[0],
					y: baseXY[1]
				});
			}
			

			// 定位后还原
			if (isHidden) {
				$el.css({
					visibility: '',
					display: 'none'
				});
			}

		},

		// 高级定位
		_setAdvPosition: function(align, advPin) {
			var $el = this.$el,
				isHidden = $el.css('display') === 'none';


			//在定位时，为避免元素高度不定，先显示出来

			if (isHidden) {
				$el.css({
					visible: 'hidden',
					display: 'block'
				});
			}

			Position.advPin($el, align.baseElement, {
				offset: align.baseXY,
				direction: advPin.direction
			});
			// 定位完成后，还原
			if (isHidden) {
				$el.css({
					visibility: '',
					display: 'none'
				});
			}
		},

		// 除了element和relativeElements， 点击body后都会隐藏 element
		_blurHide: function(arr) {


			if(!arr) oasOverlay.blurOverlays.push(this);
			arr = $.makeArray(arr);
			arr.push(this.options.target);
			this._relativeElements = arr;
			
		},

		//判断overlay展示在目标元素的哪个方位
		_judgeDirection:function(){
			var	result = {},
				isHidden = this.$el.css('display') === 'none';
			//在定位时，为避免元素高度不定，先显示出来
			if (isHidden) {
				this.$el.css({
					visible: 'hidden',
					display: 'block'
				});
			}
			var	self = this.$el,
				target = $(this.target),
			    selfPos = {top:this.$el.offset().top,left:this.$el.offset().left},
			    targetPos = {top:target.offset().top,left:target.offset().left};
			if(this.options.parentNode !== document.body && this.options.parentNode !== $(document.body)){
				selfPos = {top:this.$el.position().top,left:this.$el.position().left},
			    targetPos = {top:target.position().top,left:target.position().left};
			}
			if(selfPos.top >= targetPos.top + target.outerHeight()){
				result.direction = "bottom";
			}else if(targetPos.top >= selfPos.top + self.outerHeight()){
				result.direction = "top";
			}else if(selfPos.left >= targetPos.left + target.outerWidth()){
				result.direction = "right";
			}else if(targetPos.left >= selfPos.left + self.outerWidth()){
				result.direction = "left";
			}
			result.selfPos = selfPos;
			// 定位后还原
			if (isHidden) {
				this.$el.css({
					visibility: '',
					display: 'none'
				});
			}
			return result;
		},

		// 对外方法集
		invoke: {
			show: function() {
				/*
                 * fixed bugs 2014/9/19
				 */
				// 关闭所有其他的overlay
				// $.each(oasOverlay.allOverlays, function(i, item) {
				// 	// 当实例为空或隐藏时，不处理
				// 	if (!item || !item.visible) {
				// 		return;
				// 	}
				// 	item._iCall('hide');
				// });
				
				this.options.beforeOpen.call(this.$el, this);
				this.visible = true;
				this._setPosition();
				if(this.options.isNeedAnimation){
					var result = this._judgeDirection(),
					$el = this.$el,
					isHidden = $el.css('display') === 'none';
					if(result.direction == "bottom"){
						$el.css("top",result.selfPos.top + 15);
						if(!isHidden){
							$el.hide();
						}
						$el.animate({top:result.selfPos.top,opacity: 'show'},300);
					}else if(result.direction == "top"){
						$el.css("top",result.selfPos.top - 15);
						if(!isHidden){
							$el.hide();
						}
						$el.animate({top:result.selfPos.top,opacity: 'show'},300);
					}else if(result.direction == "left"){
						$el.css("left",result.selfPos.left - 15);
						if(!isHidden){
							$el.hide();
						}
						$el.animate({left:result.selfPos.left,opacity: 'show'},300);
					}else if(result.direction == "right"){
						$el.css("left",result.selfPos.left + 15);
						if(!isHidden){
							$el.hide();
						}
						$el.animate({left:result.selfPos.left,opacity: 'show'},300);
					}else{
						$el.show();
					}
				}else{
				this.$el.show();
				}
				if(this.options.hasOpacity){
					$('body').find('.oas-mask-opacity').show();
				}
				this.options.onOpen.call(this.$el, this);
			},

			hide: function() {

				this.visible = false;
				this.$el.hide();
				if(this.options.hasOpacity){
					$('body').find('.oas-mask-opacity').hide();
				}
				this.options.onClose.call(this.$el, this);
			},
			/**
			 * 只做展示
			 */
			showSelf:function(){
				this.visible = true;
				this.$el.show();
				this.options.onOpen.call(this.$el, this);
			},
			/**
			 * 修改对齐配置
			 */
			align: function(_align) {

				if (_align) {
					this.options.align = $.extend({}, this.options.align, _align);

					this._setPosition(this.options.align);
				}

			},

			blurHide: function(arr) {

				this._blurHide(arr);

			},

			destroyOverlay: function() {
				var self = this,
					options = this.options,
					$targetElement = $(options.target),
					delay = options.delay,
					triggerType = options.triggerType;
				oasis.erase(oasOverlay.allOverlays, this);
				this.$el.unbind('.' + this.uiName).removeData(this.uiName);
				if (triggerType === 'hover' && $targetElement) {
					$targetElement.off('mouseover.' + this.uiName).off('mouseleave.' + this.uiName);
					this.$el.off('mouseover.' + this.uiName);
					this.$el.off('mouseleave.' + this.uiName);
				} else if (triggerType === 'click' && $targetElement) {
					$targetElement.context = $targetElement.context && $targetElement.context != undefined? $targetElement.context : $targetElement.parent();
					$targetElement.die('click.' + this.uiName);
				}else if (triggerType === 'focus' && $targetElement) {
					$targetElement.off('focus.' + this.uiName);
				}
				//$(document).unbind('.' + this.uiName);
			}
		}
	});

	oasOverlay = {};
	oasOverlay.allOverlays = [];

	oasOverlay.blurOverlays = [];

	$(document).off('.oasOverlay');
	$(document).on('click.oasOverlay', function(e) {
		hideBlurOverlays(e);
	});


	// 绑定resie重新定位事件
	var timeout,
		winWidth = $(window).width(),
		winHeight = $(window).height(),
		hasRun = true;


	$(window).resize(function() {
		if(!hasRun) {
			return;
		}
		timeout && clearTimeout(timeout);
		timeout = setTimeout(function() {
			var winNewWidth = $(window).width();
			var winNewHeight = $(window).height();

			if (winWidth !== winNewWidth || winHeight !== winNewHeight) {
				$.each(oasOverlay.allOverlays, function(i, item) {
					// 当实例为空或隐藏时，不处理
					if (!item || !item.visible) {
						return;
					}
					item._setPosition();
				});

				winWidth = winNewWidth;
				winHeight = winNewHeight;
			}
			hasRun = true;
		}, 200);
	});


	// 点击其他区域隐藏

	function hideBlurOverlays(e) {
		$.each(oasOverlay.blurOverlays, function(index, item) {
			// 当实例为空或隐藏时，不做处理
			if (!item || !item.visible) {
				return;
			}

			if( !item.options.blurHide ){
				return;
			}

			// 遍历_relativeElements, 当点击的元素落在这些元素上时，不处理
			for (var i = 0, itemLen = item._relativeElements.length; i < itemLen; i++) {
				if(!!item._relativeElements[i].size() && $.contains($("body")[0], $(item._relativeElements[i])[0])){
					if(item._relativeElements[i].selector){
					if(item._relativeElements[i].size() == $(item._relativeElements[i].selector).size()){
						for (var j = 0, len = item._relativeElements[i].length; j < len; j++){
							var el = $(item._relativeElements[i][j])[0];
							if (el === e.target || $.contains(el, e.target)) {
								return;
							}
						}
					}else{
						var els = $(item._relativeElements[i].selector);
						if(!!els.size()){
							for (var k = 0, lens = els.length; k < lens; k++){
								var el = $(els[k])[0];
								if (el === e.target || $.contains(el, e.target)) {
									return;
								}
								}
							}
						}
					}else{
						for (var j = 0, len = item._relativeElements[i].length; j < len; j++){
							var el = $(item._relativeElements[i][j])[0];
							if (el === e.target || $.contains(el, e.target)) {
								return;
							}
						}
					}
				}else{
					var el = $(item._relativeElements[i])[0];
					if(el == undefined || el == null || !$.contains($("body")[0], el)){
						var els = $(item._relativeElements[i].selector);
						if(!!els.size()){
							for (var k = 0, lens = els.length; k < lens; k++){
								var el = $(els[k])[0];
								if (el === e.target || $.contains(el, e.target)) {
									return;
								}
							}
						}else{
							//throw new Error("target元素不存在或者相关元素不存在!");
						}
					}else{
						if (el === e.target || $.contains(el, e.target)) {
							return;
						}
					}
				}
			};

			if(($(e.target).is('.oas-overlay') || !!$(e.target).closest('.oas-overlay').size()) && $(e.target).closest('.oas-overlay').is(item.$el)){
				return;
			}

			// 隐藏对应元素
			item._iCall('hide');
		});
	}

})();
;
(function() {
	

	var $window = $(window),
   		$document = $(document),
    	oasSticked = [],
    	windowHeight = $window.height(),

    	scroller = function() {
	      var scrollTop = $window.scrollTop(),
	          documentHeight = $document.height(),
	          dwh = documentHeight - windowHeight,
	          extra = (scrollTop > dwh) ? dwh - scrollTop : 0;

	      for (var i = 0; i < oasSticked.length; i++) {
	        var s = oasSticked[i],
	            elementTop = s.stickyWrapper.offset().top;

            elementTop = s.bottomfixed ? elementTop + s.stickyWrapper.outerHeight() : elementTop;
	        var etse = elementTop - s.topSpacing - extra;

	        if (scrollTop <= etse) {
	          if (s.currentTop !== null) {
	            s.stickyElement
	              .css('position', '')
	              .css('top', '');
	            s.stickyElement.parent().removeClass(s.className);
	            s.that._emit('unFixed');
	            s.currentTop = null;
	          }
	        }
	        else {
	          var newTop = documentHeight - s.stickyElement.outerHeight()
	            - s.topSpacing - s.bottomSpacing - scrollTop - extra;
	          if (newTop < 0) {
	            newTop = newTop + s.topSpacing;
	          } else {
	            newTop = s.topSpacing;
	          }

	          if (typeof s.getWidthFrom !== 'undefined' && s.getWidthFrom !== '') {
	              s.stickyElement.css('width', $(s.getWidthFrom).width());
	            }else{
	            	//var width = $(s.stickyElement).width() === 0 ? $(s.stickyElement).actualWidth() : $(s.stickyElement).width();
	            	s.stickyElement.css('width', $(s.stickyElement).actual('width'));
	            }

	          if (s.currentTop != newTop) {
	            s.stickyElement
	              .css('position', 'fixed')
	              .css('top', newTop);

	            s.stickyElement.parent().addClass(s.className);
	            s.that._emit('onFixed');
	            s.currentTop = newTop;
	          }
	        }

            // 如果超过了stoption则移除。
            if(s.stoptionsp && s.stoptionsp < scrollTop ){
                  s.stickyElement
                      .css('position', '')
                      .css('top', '');
                  s.stickyElement.parent().removeClass(s.className);
                  s.that._emit('unFixed');
                  s.currentTop = null;
            }
	      }
    	},

    	// window resize时对所有的固定元素宽度等进行重新计算
	    resizer = function() {
	      windowHeight = $window.height();

	      for (var i = 0; i < oasSticked.length; i++) {
	        var s = oasSticked[i];
	        if (typeof s.getWidthFrom !== 'undefined' && s.responsiveWidth === true) {
	            s.stickyElement.css('width', $(s.getWidthFrom).width());
	        }else if(s.stickyElement.actual('width', { clone : true })===0&&s.stickyElement.actual('width')!==0){
	        	// 修复该bug.这边有性能问题，表格不需要这段代码，但是其他固定元素需要，所以暂时去掉。后面有时间补上这里逻辑。
        		s.stickyElement.css('width',"100%");
        		trueWidth = s.stickyElement.actual('width') - s.stickyElement.css('margin-left').slice(0,-2) - s.stickyElement.css('margin-right').slice(0,-2);
        		s.stickyElement.width(trueWidth);
            }
	      }
	    };

	/**
	 * 提供一个元素当滚动条滚动到该元素的高度时，该元素固定，以及达到某个高度是接触固定。
	 * @ authoptionsr rbai
	 * @ create time 2014.08.24
	 * @ versioptionsn  1.0.0
	 */
	$.oasUiFactory("oasSticky", {
		options: {

			topSpacing: 0,
		    bottomSpacing: 0,
		    className: 'oas-issticky',
		    wrapperClassName: 'oas-sticky-wrapper',
		    center: false,
		    getWidthFrom: '',
		    responsiveWidth: false,

            bottomfixed: false,
			// stoptionsp 当滚动超过多少时不在固定
			stoptionsp: null,

			onFixed: function(){

			},

			unFixed: function(){

			}
		},

		_create: function() {
			var $el = this.$el,
				options = this.options,
				self = this;


	          var stickyId = $el.attr('id');
	          var wrapperId = stickyId ? stickyId + '-' + options.wrapperClassName : options.wrapperClassName 
	          var wrapper = $('<div></div>')
	            .attr('id', stickyId + '-sticky-wrapper')
	            .addClass(options.wrapperClassName);
	          $el.addClass('oas-sticky').wrapAll(wrapper);

	          if (options.center) {
	            $el.parent().css({width:$el.outerWidth(),marginLeft:"auto",marginRight:"auto"});
	          }

	          if ($el.css("float") == "right") {
	            $el.css({"float":"none"}).parent().css({"float":"right"});
	          }

	          var stickyWrapper = $el.parent();
	          	//  height = $el.height() === 0 ? $el.actualHeight() : $el.outerHeight();
	          
	          stickyWrapper.css('height', $el.actual('height'));

	          // 保存到所有的固定元素的对象中，便于统一管理。
	          oasSticked.push({
	            topSpacing: options.topSpacing,
	            bottomSpacing: options.bottomSpacing,
	            stickyElement: $el,
	            currentTop: null,
	            stickyWrapper: stickyWrapper,
	            className: options.className,
	            getWidthFrom: options.getWidthFrom,
	            responsiveWidth: options.responsiveWidth,
                stoptionsp: options.stoptionsp,
                bottomfixed: options.bottomfixed,
	            that: this
	          });
		},
		
		// 对外方法集
		invoke: {
			
			update: scroller,


			/*
			 * 解除当前元素的固定。
			 */
			unstick: function() {

	          var unstickyElement = $el;

	          var removeIdx = -1;
	          for (var i = 0; i < oasSticked.length; i++)
	          {
	            if (oasSticked[i].stickyElement.get(0) == unstickyElement.get(0))
	            {
	                removeIdx = i;
	            }
	          }
	          if(removeIdx != -1)
	          {
	            oasSticked.splice(removeIdx,1);
	            unstickyElement.unwrap();
	            unstickyElement.removeAttr('style');
	          }
		    },


            /*
            * 设置关闭固定的高度。
            * */
            stoptionsp: function(top){
                this.options.stoptionsp = top;

                for(var i = 0, len = oasSticked.length; i < len; i ++ ){
                    if(oasSticked[i].that === this){
                        oasSticked[i].stoptionsp = top;
                        break;
                    }
                }
            }
		}
	});

	// 绑定事件
    if (window.addEventListener) {
	    window.addEventListener('scroll', scroller, false);
	    window.addEventListener('resize', resizer, false);
	} else if (window.attachEvent) {
	    window.attachEvent('onscroll', scroller);
	    window.attachEvent('onresize', resizer);
	};

	 $(function() {
	    setTimeout(scroller, 300);
	 });		
	
	$(window).on("load", function() {
        $('[oassticky]').oasSticky();
    });

})();

;
(function(global) {

	/**
	 * 封装的esc方法，类似于vm中的esc方法
	 */
	global.esc = oasis.esc = {
		/**
		 * 静态的escapeHtml方法
		 * @param param 需要被escape的参数
		 */
		html: function(param) {
			if (param) {
				var div = document.createElement("div");
				div.appendChild(document.createTextNode((param + "").trim()));
				return div.innerHTML;
			} else {
				return "";
			}
		},
		/**
		 * 将_id变成合法的id(转换成36进制的字符)
		 * @param _id 原始字符串
		 * @returns {String} 结果字符串
		 */
		toValidId: function(_id) {
			_id = _id + "";
			var result = "";
			if (_id && _id.length > 0) {
				var result = "";
				for (var i = 0; i < _id.length; i++) {
					result += _id.charAt(i).charCodeAt().toString(36) + "_";
				}
				_id = result;
			} else {

			}
			return result;
		}
	}
})(this);
;
(function(global) {


	var Mask = function(opt) {

		var twin = oasis.getTopWindow();
		var $mask = this.$mask = $('<div class="oas-mask"></div>');

		var $mask_opacity = this.$mask_opacity = $('<div class="oas-mask-opacity"></div>');
		$('body').append($mask_opacity);
		$('body', twin.document).append($mask);

		this.openCount = 0;
	}

	Mask.prototype = {

		constructor: Mask,

		open: function() {
			this.openCount++;
			this.$mask.show();
		},

		close: function() {
			this.openCount--;
			this.openCount = this.openCount < 0 ? 0 : this.openCount;
			if (this.openCount === 0) {
				this.$mask.hide();
			}

		}
	};


	// 确保单例，全局只有一个
	$(function(){
		var twin = oasis.getTopWindow();
		global.Mask = global.oasis.Mask = twin.Mask ? twin.Mask : new Mask();
	});
	

})(this);
/**
 * 操作板组件
 * @ author gehao
 * @ create time 2014.08.07
 * @ version  1.0.0
 */
;
(function() {
	$.oasUiFactory("oasOperate", {
		options: {

			// 操作板子集打开方式，可以选择click|hover，默认为click
			trigger: 'click',

			// 延时打开操作板子集(ms),当trigger为hover时生效
			delay: 150,

			/**操作面板数据集合,如果操作项中有复制剪切板功能的多传一个字段copyContent,目前只支持一级剪切板:[{iconClass:"",text:"",id:"",pid:"",copyContent:"",disable:false},{iconClass:"",text:"",id:"",pid:""}]**/
			data: [],

			//打开操作板的目标元素
			target: '',

			// 操作面板位置偏移量，用于微调,默认值为[0,0]
			offset: [0, 0],

			//放在触发元素的哪一侧
			placement:'auto',

			//位置，绝对位置
			position: null,

			//放置面板的父元素
			content: null,

			//打开回调函数
			onOpen: function() {},

			//关闭回调函数
			onClose: function() {},

			//点击后的回调
			onSelected: function(option,target){},

			//增加打开之前的回调
			beforeOpen: function(){}
		},
		//模版
		_template: function() {
			var options = this.options,
				tpl;
			tpl = '{{if level == 0}}' +
				'<div class="operate-arrow top-arrow"></div>' +
				'{{/if}}' +
				'<ul class="{{if level == 1}}second-menu oas-operate open{{/if}}{{if level == 0}}first-menu{{/if}}{{if level == 2}}third-menu oas-operate open{{/if}}' +
				'{{if layout.horizontal == "right"}} to-right{{/if}}{{if layout.horizontal == "left"}} to-left{{/if}}{{if layout.vertical == "bottom"}} to-bottom{{/if}}{{if layout.vertical == "top"}} to-top{{/if}}">' +
				'{{each list}}' +
				'{{if level != 0}}' +
				'{{if $value.hasChild != true}}' +
				'<li index={{$index}} id="{{$value.id}}" class="{{if $value.disable == true}} oas-disable{{/if}}" {{if $value.title != null}}title="{{$value.title}}"{{/if}}><a href="javascript:;" class="oasicon {{$value.iconClass}}"></a><a href="javascript:;">{{$value.text}}</a></li>' +
				'{{else}}' +
				'<li index={{$index}} id="{{$value.id}}" class="{{if $value.disable == true}} oas-disable{{/if}}" {{if $value.title != null}}title="{{$value.title}}"{{/if}}><a href="javascript:;" class="oasicon {{$value.iconClass}}"></a><a href="javascript:;">{{$value.text}}</a><a href="javascript:;" class="oasicon oasicon-arrow-right"></a></li>' +
				'{{/if}}' +
				'{{else}}' +
				'{{if $value.hasChild != true}}' +
				'<li index={{$index}} {{if $value.copyContent != null}}copycontent="{{$value.copyContent}}"{{/if}} id="{{$value.id}}" class="{{if $value.disable == true}} oas-disable{{/if}}" {{if $value.title != null}}title="{{$value.title}}"{{/if}}><a href="javascript:;" class="oasicon {{$value.iconClass}}"></a><a href="javascript:;">{{$value.text}}</a></li>' +
				'{{/if}}' +
				'{{/if}}' +
				'{{/each}}' +
				'</ul>' +
				'{{if hasChild == true && level == 0}}' +
				'<ul class="{{if level == 0}}expand-menu{{/if}}">' +
				'{{each list}}' +
				'{{if $value.hasChild == true}}' +
				'<li index={{$index}} id="{{$value.id}}" class="{{if $value.disable == true}} oas-disable{{/if}}" {{if $value.title != null}}title="{{$value.title}}"{{/if}}>' +
				'<a href="javascript:;" class="oasicon {{$value.iconClass}}"></a>' +
				'<a href="javascript:;">{{$value.text}}</a>' +
				'<a href="javascript:;" class="oasicon oasicon-arrow-right"></a>' +
				'</li>' +
				'{{/if}}' +
				'{{/each}}' +
				'</ul>' +
				'{{/if}}';
			return template.compile(tpl);
		},

		// 事件
		events: {
			'mouseover .expand-menu>li': function(self, evt) {
				if (this.options.trigger == "hover") {
					var index = $(self).attr("index"),
						id = this.mainList[index].id;
					$(self).siblings(".open").children(".second-menu").remove();
					if($(self).hasClass("oas-disable")){
						return;
					}
					$(self).addClass("open");
					this.subOneList = this._createList(id);
					this.layoutRule.vertical = 'bottom';
					this._createSubDom(this._preRenderData(this.subOneList, 1, self), self);
				}
				//return false;
			},
			'mouseleave .oas-operate': function(self, evt) {
				//return false;
			},
			'mouseover .first-menu>li': function(self, evt) {
				if (this.options.trigger == "hover") {
					$(self).parent().next().find("li.open").children(".second-menu").remove();
				}
				//return false;
			},
			'mouseover .second-menu>li': function(self, evt) {
				if (this.options.trigger == "hover") {
					var index = $(self).attr("index"),
						id = this.subOneList[index].id;
					$(self).siblings(".open").children(".third-menu").remove();
					if($(self).hasClass("oas-disable")){
						return;
					}
					if (this.subOneList[index].hasChild) {
						$(self).addClass("open");
						this.subTwoList = this._createList(id);
						this.layoutRule.vertical = 'bottom';
						this._createSubDom(this._preRenderData(this.subTwoList, 2, self), self);
					}
				}
				//return false;
			},
			'click .expand-menu>li': function(self, evt) {
				if (this.options.trigger == "click") {
					var index = $(self).attr("index"),
						id = this.mainList[index].id;
					$(self).siblings(".open").children(".second-menu").remove();
					if($(self).hasClass("oas-disable")){
						return;
					}
					$(self).addClass("open");
					this.subOneList = this._createList(id);
					this.layoutRule.vertical = 'bottom';
					this._createSubDom(this._preRenderData(this.subOneList, 1, self), self);
				}
				return false;
			},
			'click .first-menu>li': function(self, evt) {
				var index = $(self).attr("index");
				$(self).parent().next().find("li.open").children(".second-menu").remove();
				if($(self).hasClass("oas-disable")){
					return;
				}
				this._emit('onSelected',[this.mainList[index],this.$target]);
				this._iCall('close');
				return false;
			},
			'click .second-menu>li': function(self, evt) {
				var index = $(self).attr("index"),
					id = this.subOneList[index].id;
				if (this.options.trigger == "click") {
					$(self).siblings(".open").children(".third-menu").remove();
					if($(self).hasClass("oas-disable")){
						return;
					}
					if (this.subOneList[index].hasChild) {
						$(self).addClass("open");
						this.subTwoList = this._createList(id);
						this.layoutRule.vertical = 'bottom';
						this._createSubDom(this._preRenderData(this.subTwoList, 2, self), self);
					}else{
						this._emit('onSelected',[this.subOneList[index],this.$target]);
						this._iCall('close');
					}
				}else if(this.options.trigger == "hover"){
					if($(self).hasClass("oas-disable")){
						return;
					}
					if (!this.subOneList[index].hasChild) {
						this._emit('onSelected',[this.subOneList[index],this.$target]);
						this._iCall('close');
					}
				}
				return false;
			},
			'click .third-menu>li': function(self, evt) {
				if($(self).hasClass("oas-disable")){
					return;
				}
				var index = $(self).attr("index");
				this._emit('onSelected',[this.subTwoList[index],this.$target]);
				this._iCall('close');
				return false;
			}
		},
		//创建组件入口
		_create: function() {
			this.$target = null;
			if(this.options.data != null && this.options.data.length > 0){
				if(this.options.position){
					this.openType = 0;
					this._initData();
					this._openByPosition();
					this._bindOuterClick();
				}else{
					this.openType = 1;
					this._initData();
					this._openOverLay();
					this.$target = $(this.options.target);
				}
			}
		},
		//如果是坐标初始化需绑定该事件
		_bindOuterClick:function(){
			var self = this;
			$(document).on('click.oasOperate', function(e){
				if($(e.target).parents('.oas-operate').size()<1 && !$(e.target).is('.oas-operate') && $(e.target).parents('.oas-operate').size()<1 && !$(e.target).is('.oas-operate')){
					self._iCall('close');
					$(document).off('click.oasOperate');
				}
			});
		},
		//初始化数据
		_initData:function(){
			//初始化全局变量
			this._initParam();
			//整合数据
			this._tidyData();
		},
		//初始化全局变量
		_initParam: function() {
			this.layoutRule = {
				horizontal: 'right',
				vertical: 'bottom'
			};
			this.mainList = [];
			this.subOneList = [];
			this.subTwoList = [];
		},
		//整合数据
		_tidyData: function() {
			this.mainList = this._createList("0");
			this._createDom(this._preRenderData(this.mainList, 0));
		},
		//创建数据
		_createList: function(id) {
			var list = this.options.data,
				tempList = [],
				returnlist = [],
				num = 0,
				tempObj = null;
			for (var i = 0, len = list.length; i < len; i++) {
				if (list[i].pid == id) {
					tempList.push(list[i]);
				}
			}
			for (var i = 0, len = tempList.length; i < len; i++) {
				for (var j = 0, lens = list.length; j < lens; j++) {
					if (tempList[i].id == list[j].pid) {
						tempList[i].hasChild = true;
						break;
					}
				}
				if (tempList[i].hasChild) {
					returnlist.push(tempList[i]);
				} else {
					returnlist.unshift(tempList[i]);
				}
			}
			tempList = [];
			for (var i = 0; i < returnlist.length; i++) {
				if(!returnlist[i].hasChild){
					tempList.push(returnlist[i]);
					num ++;
				}
			};
			tempList.reverse();
			returnlist.splice(0,num);
			return tempList.concat(returnlist);
		},
		//准备渲染的数据
		_preRenderData: function(list, level, parentNode) {
			var options = this.options,
				hasChild = false,
				_renderData = {};
			for (var i = 0, len = list.length; i < len; i++) {
				if (list[i].hasChild) {
					hasChild = true;
					break;
				};
			};
			if (parentNode) {
				this._judgeLayOut(parentNode,list);
			}
			return _renderData = {
				hasChild: hasChild,
				list: list,
				level: level,
				layout:this.layoutRule
			};
		},
		//创建dom节点
		_createDom: function(_renderData) {
			var html = this.tpl(_renderData),
			outerContent = this.options.content;
			if(this.openType){
				var $el = this.$el;
				$el.addClass('oas-operate').addClass("opt-panel");
				$el.empty().append(html);
				if(outerContent){
					outerContent.append($el);
				}else{
					$('body').append($el);
				}
			}else{
				this.$el.addClass('oas-operate').addClass("opt-panel").empty().append(html);
				if(outerContent){
					outerContent.append($el);
				}else{
					$('body').append(this.$el);
				}
			}
		},
		//创建子dom节点
		_createSubDom: function(_renderData, parentNode) {
			var html = this.tpl(_renderData),
				$el = this.$el;
			if ($(parentNode).has("ul").length == 0) {
				$(html).appendTo($(parentNode));
			}
		},
		//按照position方式打开
		_openByPosition:function(){
			var position = this.options.position,
				$el = this.$el,
				thisLeft = position.left, //left值
				thisTop = position.top,
				thisHeight = $el.outerHeight(), 
				thisWidth = $el.outerWidth(), 
				left = position.left, //left值
				top = position.top,
				win = {
					height: $(window).height(),
					width: $(window).width(),
					scrollTop: $(window).scrollTop(),
					scrollLeft: $(window).scrollLeft()
				};
			$el.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("left-arrow");
			if (win.width + win.scrollLeft - thisLeft - thisWidth< 0) {
				$el.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("right-arrow");
				left = thisLeft - thisWidth;
			}
			if (win.height + win.scrollTop - thisTop - thisHeight < 0) {
				$el.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("left-arrow");
				top = thisTop - thisHeight;
				$el.find(".operate-arrow").addClass("opposite");
			}
			if (win.height + win.scrollTop - thisTop - thisHeight < 0 && win.width + win.scrollLeft - thisLeft - thisWidth < 0) {
				$el.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("right-arrow");
				left = thisLeft - thisWidth;
				top = thisTop - thisHeight;
				$el.find(".operate-arrow").addClass("opposite");
			}
			$el.css("left",left);
			$el.css("top",top);
			
		},
		//判断错位展示
		_judgeLayOut: function(target,list) {
			var position = Position.location(target,"fixed"),
				result = {},
				layoutRule = this.layoutRule;
			if(position.right-191 >= 0){
				result.right = true;
			}else {
				result.right = false;
			}
			if(position.left-191 >= 0){
				result.left = true;
			}else {
				result.left = false;
			}
			if(position.top-((list.length-1)*30 + 6) >= 0){
				result.top = true;
			}else {
				result.top = false;
			}
			if(position.bottom-((list.length-1)*30 + 6) >= 0){
				result.bottom = true;
			}else {
				result.bottom = false;
			}
			if(layoutRule.horizontal == "right"){
				if(result.right){
					layoutRule.horizontal = "right";
				}else{
					if(result.left){
						layoutRule.horizontal = "left";
					}else{
						layoutRule.horizontal = "right";
					}
				}
			}
			if(layoutRule.horizontal == "left"){
				if(result.left){
					layoutRule.horizontal = "left";
				}else{
					if(result.right){
						layoutRule.horizontal = "right";
					}else{
						layoutRule.horizontal = "left";
					}
				}
			}
			if(layoutRule.vertical == "bottom"){
				if(result.bottom){
					layoutRule.vertical = "bottom";
				}else{
					if(result.top){
						layoutRule.vertical = "top";
					}else{
						layoutRule.vertical = "bottom";
					}
				}
			}
			if(layoutRule.vertical == "top"){
				if(result.top){
					layoutRule.vertical = "top";
				}else{
					if(result.bottom){
						layoutRule.vertical = "bottom";
					}else{
						layoutRule.vertical = "top";
					}
				}
			}
		},
		//用overlay打开
		_openOverLay: function() {
			var options = this.options,
				thisEl = this.$el,
				self = this,
				elem = options.target,
				trig = options.trigger,
				delay = options.delay,
				placement = this.options.placement,
				thisHeight = this.$el.outerHeight(), //气泡高度
				thisWidth = this.$el.outerWidth(), //气泡宽度
				x = options.offset[0], //x轴偏移量
				y = options.offset[1], //y轴偏移量
				outerContent = options.content,
				win = {
					height: $(window).height(),
					width: $(window).width(),
					scrollTop: $(window).scrollTop(),
					scrollLeft: $(window).scrollLeft()
				};
			thisEl.oasOverlay({
				target: $(elem),
				triggerType: trig,
				delay: delay,
				parentNode:outerContent,
				align: {
					// 基准定位元素，默认为当前的可视区域
					baseElement: elem,
					// 基准定位元素的定位点，默认为左上角
					baseXY: [0, 0]
				},
				onClose:function(){
					self._iCall('close');
				},
				beforeOpen:function($el){
					win = {
						height: $(window).height(),
						width: $(window).width(),
						scrollTop: $(window).scrollTop(),
						scrollLeft: $(window).scrollLeft()
					};
					$('body').find(".oas-operate").hide();
					//self._initData();
					self._emit('beforeOpen');
					var baseXY,
						left = 0,
						top = 0,
						splitNum = 10, 
						thisTop = $($el.target).offset().top, //top值
						thisLeft = $($el.target).offset().left, //left值
						tgtWidth = $($el.target).outerWidth(),
						tgtHeight = $($el.target).outerHeight();
						/**
						left = +x;
						top = 25 + y;
						thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
						baseXY = [left, top];
						if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
							left = -thisWidth + 40 + x;
							top = 25 + y;
							baseXY = [left, top];
							thisEl.find(".operate-arrow").addClass("opposite");
						}
						if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
							thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("bottom-arrow");
							left = +x;
							top = -thisHeight + y - 10;
							baseXY = [left, top];
							//thisEl.find(".operate-arrow").addClass("opposite");
						}
						if (win.height + win.scrollTop - thisTop - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
							thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite").addClass("bottom-arrow");
							left = -thisWidth + 40 + x;
							top = -thisHeight + y - 10;
							baseXY = [left, top];
							thisEl.find(".operate-arrow").addClass("opposite");
						}**/
					switch (placement) {
						case 'left':
							left = -thisWidth - 10 + x;
							top = -15 + y;
							thisEl.find(".operate-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("right-arrow");
							baseXY = [left, top];
							if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
								left = -thisWidth - 10 + x;
								top = -thisHeight + y + 35;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							break;
						case 'right':
							left = +$($el.target).outerWidth() + 10 + x;
							top = -13 + y;
							thisEl.find(".operate-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("left-arrow");
							baseXY = [left, top];
							if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
								left = $($el.target).outerWidth() + 10 + x;
								top = -thisHeight + y + 35;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							break;
						case 'top':
							left = +x;
							top = -thisHeight - 5 + y;
							thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow opposite").addClass("bottom-arrow");
							baseXY = [left, top];
							if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
								left = -thisWidth + 40 + x;
								top = -thisHeight - 5 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							break;
						case 'bottom':
							left = +x;
							top = 25 + y;
							thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							baseXY = [left, top];
							if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
								left = -thisWidth + 40 + x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							break;
						default:
							left = +x;
							top = 25 + y;
							thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							baseXY = [left, top];
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y >= 0) {
								//在左侧展示
								left = -thisWidth + 40 + x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0){
								left = -thisWidth + 40 + x;
								top = -thisHeight + y ;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								thisEl.find(".operate-arrow").addClass("bottom-arrow opposite");
							}
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x >= 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0){
								left = + x;
								top = -thisHeight + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								thisEl.find(".operate-arrow").addClass("bottom-arrow");
							}
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x >= 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y >= 0){
								left = +x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							}
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y >= 0 && 
								thisLeft - thisWidth - splitNum + x < 0) {
								left = +x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							}
							if(win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0 && 
								thisLeft - thisWidth - splitNum + x < 0) {
								left = +x;
								top = -thisHeight + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								thisEl.find(".operate-arrow").addClass("bottom-arrow");
							}
							if(win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && 
								thisTop - thisHeight - splitNum + y < 0) {
								left = -thisWidth + 40 + x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								thisEl.find(".operate-arrow").addClass("opposite");
							}
							if(win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x >= 0 && 
								thisTop - thisHeight - splitNum + y < 0) {
								left = +x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							}
							if(win.height + win.scrollTop - thisTop - tgtHeight - splitNum - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - tgtWidth - splitNum - thisWidth - x < 0 && 
								thisTop - thisHeight - splitNum + y < 0 && thisLeft - thisWidth - splitNum + x < 0) {
								left = +x;
								top = 25 + y;
								baseXY = [left, top];
								thisEl.find(".operate-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
							}
					}
					this.oasOverlay('align', {
						baseXY: baseXY
					});
				}

			});
			//thisEl.oasOverlay('blurHide', [$('.oasicon-arrow-right').parent()]);
		},
		// 组件对外方法集
		invoke: {
			//打开操作面板
			open: function() {
				var self = this;
				var timeOut = setTimeout(function(){
					self._create();
					self._emit('onOpen');
					self.$el.show();
				},50);
				
			},
			//关闭操作面板
			close: function() {
				//this.$el.html("");
				this._emit('onClose');
				this.$el.find(".second-menu,.third-menu").remove();
				this.$el.hide();	
				$('body').find('.oas-mask-opacity').hide();
			},
			/*
			 * @param {ObjArray} dataList 操作面板数据集合
			 */
			data: function(dataList) {
				this.options.data = dataList;
				this._create();
			},
			/*
			 * @param {obj} position 位置，绝对位置
			 */
			position: function(position) {
				if(this.options.position){
					this.options.position = position;
					this._create();
				}
			},
			/*
			 * @param {obj} 触发弹出操作板的目标元素
			 */
			target: function(target) {
				if(target){
					this.$target = target;
				}
			}
		}
	});
})();
/**
 * 表单提示组件
 * @ author jyye
 * @ create time 2014.08.011
 * @ version  1.0.0
 */
(function(){
	// 组件名和文件名请统一

	$.oasUiFactory("oasFormTip", {
		options: {

		},

		 _create : function() {
            var $el = this.$el, options = this.options, self = this, iconRmind , iconError;
            this.$remind, this.$error;
            this.upArrow = false;
            //图标
            this.iconRmind = $('<i class="form-tip-icon oasicon oasicon-tip"></i>');
            this.iconError = $('<i class="form-tip-icon oas-error oasicon oasicon-tip"></i>');
            //提示结构生成
            this.$remind = this._createFormTip();
            this.$error = this._createErrorFormTip();
            
            //判断普通提示
            if($el.attr("oasmsg") != null ){
                if($el.attr("type") == "checkbox" || $el.attr("type") == "radio" ){
                    //this.iconRmind.insertAfter($el.closest(".form-content"));
                    $el.closest(".form-content").append(this.iconRmind);
                    this.iconRmind.siblings(".form-tip-icon").remove();
                    this.iconRmind.css({position:'absolute',right:0})
                    
                }else{

                    if($el.parent(".input-hasicon").size()>0){
                        this.iconRmind.appendTo($el.parent(".input-hasicon")); 
                    }else{
                        this.iconRmind.insertAfter($el);
                    }
                }
            	 
            }
            //判断错误提示
            if($el.attr("oaserrormsg") != null && $el.hasClass("oas-error")){
                 if($el.attr("type") == "checkbox" || $el.attr("type") == "radio"){
                    this.iconRmind.remove();
                   // this.iconError.insertAfter($el.closest(".form-content"));
                    $el.closest(".form-content").append(this.iconError);
                    this.iconError.siblings(".form-tip-icon").remove();
                    this.iconError.css({position:'absolute',left:$el.closest(".form-content").width()-24})
                   
                }else{
                    if($el.parent(".input-hasicon").size()>0){
                        this.iconRmind.remove();
                        this.iconError.remove();
                        this.iconError.appendTo($el.parent(".input-hasicon")); 
                    }else{
                       this.iconRmind.remove();
                       this.iconError.remove();
                       this.iconError.insertAfter($el); 
                    }
                }
            	
            }
            this._bindEvent(this.$remind, this.$error);
        },
        /**
         *普通提示
         */
        _createFormTip : function() {
            $formTip = $('<div class="oas-form-tip"><div class="form-arrow"></div><div class="oas-tip-bd"></div></div>');
            return $formTip;
        },
        /**
         *错误提示
         */
        _createErrorFormTip : function() {
            var $formTip = this._createFormTip();
            $formTip.addClass("oas-error");
            return $formTip;
        },
        /**
         *定位
         */
        _setPosition : function($el, $obj) {
            this._setUpArrow($el.offset().top, $obj.height());
            if (this.upArrow) {
                $obj.find(".form-arrow").addClass("form-tip-arrowup");
                $obj.css({
                    position : "absolute",
                    top : $el.offset().top + $obj.height() +6,
                    left : $el.offset().left -$obj.width()+8
                });
            } else {
                $obj.find(".form-arrow").removeClass("form-tip-arrowup");
                $obj.css({
                    position : "absolute",
                    top : $el.offset().top - $obj.height() -27 ,
                    left : $el.offset().left -$obj.width()+8
                });
            }
        },
        /**
         *input后面跟按钮的情况下定位
         */
        _hasInputIconPosition : function($el, $obj) {
            this._setUpArrow($el.offset().top, $obj.height());
            if (this.upArrow) {
                $obj.find(".form-arrow").addClass("form-tip-arrowup");
                $obj.css({
                    position : "absolute",
                    top : $el.offset().top + $obj.height() +6,
                    left : $el.offset().left -$obj.width()+ 45
                });
            } else {
                $obj.find(".form-arrow").removeClass("form-tip-arrowup");
                $obj.css({
                    position : "absolute",
                    top : $el.offset().top - $obj.height() -27 ,
                    left : $el.offset().left -$obj.width()+ 45
                });
            }
        },
        /**
         *绑定事件
         */
        _bindEvent : function($remind, $error) {
            var $el = this.$el, options = this.options, self = this;
            if($el.attr("type") == "checkbox" || $el.attr("type") == "radio"){
                //checkbox和radio
                var obj = $el.closest(".form-content");
                    obj.hover(function() {//hover事件
                        if ($el.hasClass("oas-error")) {
                            if ($el.attr("oaserrormsg") != null) {
                                $("body").append($error);
                                $error.siblings(".oas-form-tip").remove();
                                $error.width($el.closest(".form-content").width()-6);
                                $(".oas-tip-bd", $error).text($el.attr("oaserrormsg"));
                                self._setPosition($(this).find(".oasicon"), $error);
                                $error.show();
                            }
                        }else{
                            if ($el.attr("oasmsg") != null) {
                                $("body").append($remind);
                                $remind.siblings(".oas-form-tip:not(.oas-error)").remove();
                                $remind.width($el.closest(".form-content").width()-6);
                                $(".oas-tip-bd", $remind).text($el.attr("oasmsg"));
                                self._setPosition($(this).find(".oasicon"), $remind);
                                $remind.show();
                            }
                        }
                    }, function() {
                        $error.remove();
                        $remind.remove();
                    });
            }else if($el.parent(".input-hasicon").size()>0){
                //父节点有input-hasicon类的情况
                $el.on("focus", function() {//聚焦和模糊
                    if ($(this).attr("oasmsg") != null) {
                        $("body").append($remind);
                        $remind.width($el.parent().width()-5);
                        $(this).unbind("hover");
                        $error.remove();
                        $(".oas-tip-bd", $remind).text($(this).attr("oasmsg"));
                        self._hasInputIconPosition($(this).parent(".input-hasicon").find(".oasicon"), $remind);
                        $remind.show();
                    }
                }).on("blur", function() {
                    $remind.remove();
                    $(this).hover(function() {
                        if ($(this).hasClass("oas-error")) {
                            if ($(this).attr("oaserrormsg") != null) {
                                $("body").append($error);
                                $error.width($el.parent().width()-8);
                                $error.siblings(".oas-form-tip").remove();
                                $(".oas-tip-bd", $error).text($(this).attr("oaserrormsg"));
                                self._hasInputIconPosition($(this).parent(".input-hasicon").find(".oasicon"), $error);
                                $error.show();
                            }
                        }
                    }, function() {
                        $error.remove();
                    })
                });

                $el.hover(function() {//hover事件
                    if ($(this).hasClass("oas-error")) {
                        if ($(this).attr("oaserrormsg") != null) {
                            $("body").append($error);
                            $error.width($el.parent().width()-8);
                            $error.siblings(".oas-form-tip").remove();
                            $(".oas-tip-bd", $error).text($(this).attr("oaserrormsg"));
                            self._hasInputIconPosition($(this).parent(".input-hasicon").find(".oasicon"), $error);
                            $error.show();
                        }
                    }
                }, function() {
                    $error.remove();
                });

                this.iconError.hover(function(){
                    $("body").append($error);
                    $error.width($el.parent().width()-8);
                    $error.siblings(".oas-form-tip").remove();
                    $(".oas-tip-bd", $error).text($(this).parent().find("[oaserrormsg]").attr("oaserrormsg"));
                    self._setPosition($(this), $error);
                    $error.show();
                }, function() {
                    $error.remove();
                });

            }else{
                //无按钮情况下
                $el.on("focus", function() {//聚焦和模糊
                    if ($(this).attr("oasmsg") != null) {
                        $("body").append($remind);
                        $remind.width($el.width()+40);
                        $(this).unbind("hover");
                        $error.remove();
                        $(".oas-tip-bd", $remind).text($(this).attr("oasmsg"));
                        self._setPosition($(this).next(".oasicon"), $remind);
                        $remind.show();
                    }
                }).on("blur", function() {
                    $remind.remove();
                    $(this).hover(function() {
                        if ($(this).hasClass("oas-error")) {
                            if ($(this).attr("oaserrormsg") != null) {
                                $("body").append($error);
                                $error.width($el.width()+40);
                                $error.siblings(".oas-form-tip").remove();
                                $(".oas-tip-bd", $error).text($(this).attr("oaserrormsg"));
                                self._setPosition($(this).next(".oasicon"), $error);
                                $error.show();
                            }
                        }
                    }, function() {
                        $error.remove();
                    })
                });

                $el.hover(function() {//hover事件
                    if ($(this).hasClass("oas-error")) {
                        if ($(this).attr("oaserrormsg") != null) {
                            $("body").append($error);
                            $error.width($el.width()+40);
                            $error.siblings(".oas-form-tip").remove();
                            $(".oas-tip-bd", $error).text($(this).attr("oaserrormsg"));
                            self._setPosition($(this).next(".oasicon"), $error);
                            $error.show();
                        }
                    }
                }, function() {
                    $error.remove();
                });

                this.iconError.hover(function(){
                    $("body").append($error);
                    $error.width($el.width()+40);
                    $error.siblings(".oas-form-tip").remove();
                    $(".oas-tip-bd", $error).text($(this).prev().attr("oaserrormsg"));
                    self._setPosition($(this), $error);
                    $error.show();
                }, function() {
                    $error.remove();
                });
            }
        },
         /**
         * 判断箭头方向
         */
        _setUpArrow : function(_top, _height) {
            if (_top -27 < _height) {
                this.upArrow = true;
            } else {
                this.upArrow = false;
            }
        },
		// 组件对外方法集
		invoke: {
			 message : function(_msg, _type) {
                var $el = this.$el,
                    iconRmind = this.iconRmind,
                    iconError = this.iconError;
                switch(_type) {
                    case "oasmsg":
                        _msg = _msg ? _msg : "";
                        iconRmind.remove();
                        iconError.remove();
                        $el.attr("oasmsg",_msg);
                        break;
                    case "oaserrormsg":
                        _msg = _msg ? _msg : "";
                        iconRmind.remove();
                        iconError.remove();
                        $el.addClass("oas-error");
                        $el.parent('.input-hasicon').addClass('oas-error');
                        $el.attr("oaserrormsg",_msg);
                        break;
                    default: 
                        _msg = _msg ? _msg : "";
                        iconRmind.remove();
                        iconError.remove();
                        $el.attr("oasmsg",_msg);
                        break;

                }
                this._create();
            },
            remove : function(_type){
                var $el = this.$el,
                    iconRmind = this.iconRmind,
                    iconError = this.iconError;
                if(_type === "oaserrormsg"){
                    $el.removeClass("oas-error");
                    $el.removeAttr("oaserrormsg");
                    $el.parent('.input-hasicon').removeClass('oas-error');
                    iconRmind.remove();
                    iconError.remove();
                    this.$error.remove();
                }else if(_type === "oasmsg"){
                    $el.removeAttr("oasmsg");
                    iconRmind.remove();
                    iconError.remove();
                    this.$remind.remove();
                }else{
                    $el.removeClass("oas-error");
                    $el.removeAttr("oaserrormsg");
                    $el.removeAttr("oasmsg");
                    $el.parent('.input-hasicon').removeClass('oas-error');
                    iconRmind.remove();
                    iconError.remove();
                    this.$error.remove();
                    this.$remind.remove();
                }
                this._create();
            }
		}
	});
})();
$(document).ready(function() {
    $('form input[type=text],form input[type=password],form input[type=search],form input[type=url],form input[type=tel],form input[type=email],form input[type=number],form textarea').oasFormTip();
})
/**
 * 瞬间提示组件
 * @ author jyye
 * @ create time 2014.08.07
 * @ version  1.0.0
 */
;
(function() {
	// 组件名和文件名请统一

	$.oasUiFactory("oasInstantTip", {
		options: {

			/**
			 * tip显示类型,分为"success","warning","loading","error"
			 * @type string
			 * @default null
			 * @example
			 * $("body").oasInstantTip({
			 *     type:"success"
			 * })
			 */
			type: 'success',
			/**
			 *提示文本
			 * @type string
			 * @default null
			 * @example
			 *  $("body").oasInstantTip({
			 *     message:"公共研发"
			 * })
			 */
			message: '恭喜！成功了',
            /**
			 *取消加载提示文本
			 * @type string
			 * @default null
			 * @example
			 *  $("body").oasInstantTip({
			 *     cancel:"取消加载"
			 * })
			 */
			cancel: null,
			/**
			 *设置显示时间
			 * @type int
			 * @default null
			 * @example
			 * $("body").oasInstantTip({
			 *     time:1000
			 * })
			 */
			time: 2000,
			/**
			 *是否有遮罩
			 * @type boolean
			 * @default false
			 * @example
			 * $("body").oasInstantTip({
			 *     modal:false
			 * })
			 */
			modal: null,
			

			//打开回调函数
			onOpen: function() {
				
			},
			//关闭回调函数
			onClose: function() {

			},
            //取消回调函数
            onCancel: function() {

            }

		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl = '<div class="oas-instant-tip info {{if type === "success"}}success{{/if}}{{if type === "error"}}error{{/if}}{{if type === "warning"}}warning{{/if}}{{if type === "loading"}}loading{{/if}}" style="display:none;">' +
				'<div class="instant-img"></div>' +
				'<div class="instant-text">{{message}}</div>' +
                '{{if type === "loading" && cancel}}<div class="cancel-text"><a class="oas-assist">{{cancel}}</a></div>{{/if}}' +
				'</div>';


			return template.compile(tpl);
		},

		// 事件
		events: {
            "click .cancel-text ": function(self, evt) {
                //alert("cancel me");
                var twin = oasis.getTopWindow(),
                    options = this.options,
                    $el = this.$el.children(".oas-instant-tip");
                $el.stop(true, false).fadeOut("500");
                if (options.modal  != false) {
                    $("body", twin.document).find(".oas-mask").removeClass("zindex93");
                    $el.removeClass("hasModal");
                    Mask.close();
                }
                this._emit('onCancel',[this.$el]);
            }
		},

		_create: function() {
			var options = this.options;
			this._preRenderData();
			this._judgeModal();
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				type = options.type,
				position = options.position,
				message = options.message,
                cancel = options.cancel;
			this._renderData = _renderData = {
				type: type,
				position: position,
				message: message,
                cancel: cancel
			};
			
			this._createDom();
		},

		_judgeModal: function() {
			var options = this.options;
			if (options.modal != null) {
				return;
			} else {
				switch (options.type) {
					case "success":
						{
							options.modal = false;
							break;
						}
					case "warning":
						{
							options.modal = false;
							break;
						}
					case "error":
						{
							options.modal = false;
							break;
						}
					case "loading":
						{
							options.modal = true;
							break;
						}
					default:
						break;
				}
			}
		},

		_createDom: function() {
			var options = this.options,
				$el = this.$el.children('.oas-instant-tip'),
				html = this.tpl(this._renderData);
			$el.remove();
			this.$el.append(html);
		},

		// 组件对外方法集
		invoke: {
			//打开提示
			open: function(_data) {
				var options = this.options,
					self = this,
					$el = this.$el.children(".oas-instant-tip");
				var twin = oasis.getTopWindow();
				//转换数据
				if(_data){
					//this._iCall("message",[_data.message]);
					//this._iCall("type",[_data.type]);
					//this._iCall("time",[_data.time]);
					options.message = _data.message;
					options.type = _data.type;
					options.time = _data.time;
					options.modal = _data.modal;
                    options.cancel = _data.cancel;
					options.onOpen = _data.onOpen;
					options.onClose = _data.onClose;
                    options.onCancel = _data.onCancel;
					this._preRenderData();
					this._iCall("openOther");
					
				}else{
					//打开动画
					if(options.type != 'loading'){
						if (options.modal === true ) {
							Mask.open();
							$("body", twin.document).find(".oas-mask").addClass("zindex93");
							$el.addClass("hasModal");
							$el.fadeIn("slow");
							setTimeout(function() {
								$el.fadeOut(500);
								$("body", twin.document).find(".oas-mask").removeClass("zindex93");
								$el.removeClass("hasModal");
								Mask.close();
								self._emit('onClose',[this.$el]);
							}, options.time);
						}else{
							$el.fadeIn("slow");
							setTimeout(function() {
								$el.fadeOut(500);
								self._emit('onClose',[this.$el]);
							}, options.time);
						}
					}else if(options.type === 'loading' ){
						if (options.modal  != false  ) {
							Mask.open();
							$("body", twin.document).find(".oas-mask").addClass("zindex93");
							$el.addClass("hasModal");
							$el.fadeIn("slow");
						}
						$el.fadeIn("slow");
					}
				}
				
				this._iCall("resize");
				self._emit('onOpen',[this.$el]);
			},
			//关闭
			close: function() {
				var options = this.options,
					self = this,
					$el = this.$el.children('.oas-instant-tip');
				var twin = oasis.getTopWindow();
				$el.fadeOut(500);
				$el.removeClass("hasModal");
				$("body", twin.document).find(".oas-mask").removeClass("zindex93");
				this.options.modal && Mask.close();
				self._emit('onClose',[this.$el]);
			},
			openOther: function() {
				var options = this.options,
					self = this,
					$el = this.$el.children('.oas-instant-tip');
				var twin = oasis.getTopWindow();
				if(options.type != 'loading'){
					if (options.modal === true ) {
						Mask.open();
						$("body", twin.document).find(".oas-mask").addClass("zindex93");
						$el.addClass("hasModal");
						$el.fadeIn("slow");
						setTimeout(function() {
							$el.fadeOut(500);
							$el.removeClass("hasModal");
							$("body", twin.document).find(".oas-mask").removeClass("zindex93");
							Mask.close();
							self._emit('onClose',[this.$el]);
						}, options.time);
					}else{
						$el.fadeIn("slow");
						setTimeout(function() {
							$el.fadeOut(500);
							self._emit('onClose',[this.$el]);
						}, options.time);
					}
					
					
				}else if(options.type === 'loading' ){
					if (options.modal  != false  ) {
						Mask.open();
						$("body", twin.document).find(".oas-mask").addClass("zindex93");
						$el.addClass("hasModal");
						$el.fadeIn("slow");
					}
					$el.fadeIn("slow");
				}
			},
			/*
			 * 修改持续时间
			 * @param _time int
			 */
			time: function(_time) {
				this.options.time = _time;
			},
			/*
			 * 修改文字内容
			 * @param _message tring
			 */
			message: function(_message) {
				var options = this.options,
					$el = this.$el.children('.oas-instant-tip');
				$el.find('.instant-text').text(_message);
			},
			/*
			 * 修改类型
			 * @param _type,_message tring
			 */
			type: function(_type, _message) {
				var options = this.options,
					$el = this.$el.children('.oas-instant-tip');
				$el.removeClass("success error loading warning").addClass(_type);
				if (_message) {
					$el.find('.instant-text').text(_message);
				}
			},
			resize:function(){
				var $el = this.$el.children('.oas-instant-tip'),
					width = $el.outerWidth()/2,
					height = $el.outerHeight()/2;

				$el.css({"margin-top": -height});
				$el.css({"margin-left": -width});
			}
		}
	});
	$(window).unload(function () { 
		var twin = oasis.getTopWindow();
		if($(twin.document).find(".oas-instant-tip").size()>0 ){
			if($(twin.document).find('.oas-instant-tip.hasModal').size()>0){
				Mask.close();
				$(twin.document).find(".oas-mask").removeClass("zindex93");
			}
			$(twin.document).find('.oas-instant-tip').remove();
		}
	});
	$(window).bind('onframeblur',function () { 
		var twin = oasis.getTopWindow();
		if($(twin.document).find(".oas-instant-tip").size()>0 ){
			if($(twin.document).find('.oas-instant-tip.hasModal').size()>0){
				Mask.close();
				$(twin.document).find(".oas-mask").removeClass("zindex93");
			}
			$(twin.document).find('.oas-instant-tip').remove();
		}
	});
})();
;
(function() {

		$.oasUiFactory("oasSelect", {
				options: {

					// 触发的下拉框展开的事件类型，可以选择click|hover，默认为click
					trigger: 'click',

					// 下拉框的宽度,默认为250px
					width: 250,

					//下拉框下拉面板的高度,默认为auto
					height: 'auto',

					//下拉框下拉面板的最大高度
					maxHeight:400,

					// 延期触发显示下拉面板(ms),当trigger为hover时生效
					delay: 150,

					//下拉框默认提示
					tip: '请选择内容',

					//下拉框是否有注释,默认false
					comment: false,

					//下拉框数据集合:[{value:"",text:"",selected:true,disabled:false,comment:"我是备注",vip:true,title:" 我是title的内容"}]
					//value: option的value,
					//text:option的内容,
					//selected:是否选中，
					//disabled：下拉框是否激活，
					//comment备注内容，
					//vip: 优先级，
					//title:鼠标移上去显示属性
					data: [],

					//下拉框的是否处于激活状态
					disabled: false,

					//展示类型,表示下拉面板是向上展开还是向下展开：'up','down'
					showType: 'down',

					offset: [0, 0],
					//下拉面板元素选中改变
					onChange: function(value, text) {

					}
				},
				//模版
				_template: function() {
					var tpl = '<div class="select-hd{{if comment == true}} has-comments{{/if}}{{if disabled == true}} disabled{{/if}}">' +
						'<div class="select-hd-content">' +
						'<span class="select-text {{if chosenIndex != -1 }}select-color{{else}}tip-color{{/if}} {{if disabled == true}}oas-text-disable{{/if}}">{{tip}}</span>' +
						'<span class="comment-text {{if disabled == true}}oas-text-disable{{/if}}">{{commentText}}</span>' +
						'</div>' +
						'<a class="oasicon oasicon-arrow-down"></a>' +
						'</div>' +
						'<ul class="select-bd {{if comment == true}}has-comments{{/if}} {{if showType == "up"}}up{{/if}}">' +
						'{{each list}}' +
						'<li {{if $value.title != null}}title="{{$value.title}}"{{/if}} class="select-bd-li {{if $index == list.length-1}}last{{/if}} {{if $index == 0}}start{{/if}} {{if $index == chosenIndex && $value.disabled != true}}focus{{/if}} {{if $value.disabled == true}}disabled{{/if}}" index={{$index}} disable={{"" + $value.disabled}} val={{$value.value}}>' +
						'<div class="select-bd-content {{if $value.vip == true}}vip{{/if}}">' +
						'<span class="option-text {{if $value.disabled == true}}oas-text-disable{{/if}}">{{#$value.text}}</span>' +
						'<span class="option-comment {{if $value.disabled == true}}oas-text-disable{{/if}}">{{$value.comment}}</span>' +
						'</div>' +
						'</li>' +
						'{{/each}}' +
						'</ul>';
					return template.compile(tpl);
				},

				// 事件
				events: {
					'click .select-bd-li': function(self, evt) {
						if ($(self).attr('disable') == 'false') {
							if (this.currentIndex == -1 || this.currentIndex != $(self).attr('index')) {
								this.currentIndex = $(self).attr('index');
								this.$el_hd.find('.select-hd-content .select-text').text($(self).find(".option-text").text()).removeClass('tip-color').addClass('select-color');
								this.$el_hd.find('.select-hd-content .comment-text').text($(self).find(".option-comment").text());
								this._emit('onChange', [this.options.data[this.currentIndex].value, this.options.data[this.currentIndex].text]);
								if(this.type == 1){
									this.$dom.val(this.options.data[this.currentIndex].value);
									this.$dom.find("option").removeAttr("selected");
									this.$dom.find("option").eq(this.currentIndex).attr("selected","true");
									this.$dom.trigger('change');
								}
							}
							this.$el.oasOverlay("hide");
						}
						return false;
					},
					'mouseover .select-bd-li': function(self, evt) {
						if ($(self).attr('disable') == 'false') {
							$.each(this.$el.find('.select-bd-li'), function(i, n) {
								$(n).removeClass('focus');
							});
							this.currentHoverIndex = $(self).attr('index');
							$(self).addClass('focus');
						}
						//return false;
					}
				},
			//创建组件入口
			_create: function() {
				//销毁下拉面板overlay
				this._desTroyOverLay();
				//初始化组件所需参数
				this._initParam();
				//判断是否是select元素还是数据初始化
				this._judgeSelect();
				//this._bindKeyDown();
				this._openOverLay();
			},

			//用overlay打开 
			_openOverLay: function() {
				var options = this.options,
					$el = this.$el,
					self = this,
					elem = this.$el_hd,
					delay = options.delay,
					x = options.offset[0], //x轴偏移量
					y = options.offset[1], //y轴偏移量
					trig = options.trigger;
				$el.oasOverlay({
					target: $(elem),
					triggerType: trig,
					delay: delay,
					isNeedAnimation:false,
					align: {
						// 基准定位元素，默认为当前的可视区域
						baseElement: elem,
						// 基准定位元素的定位点，默认为左上角
						baseXY: [0, 30]
					},
					onOpen: function($el) {
						if(trig !== "hover"){
							if(!self.isOpen){
								self.isOpen = true;
								self.$el.show();
							}else{
								self.isOpen = false;
								self.$el.hide();
							}
						}
						// create the transparent mask for click the other position(iframe) can be close 
						self.$_transparent = $('<div style="position:fixed;top:0;left:0;right:0;bottom:0;z-index:99994;"></div>').appendTo('body');
					},
					onClose:function($el){
						self.isOpen = false;
						self.$_transparent.remove();
					},
					beforeOpen:function($el){
						$('body').find(".oas-select-bd").hide();
						self.$el.find('.select-bd-li').removeClass('focus');
						self.$el.find('.select-bd-li[index="' + self.currentIndex + '"]').addClass('focus');
						this.oasOverlay('align', {
							baseXY: [0+x,30+y]
						});
					}
				});
			},

			//初始化全局变量
			_initParam: function() {
				//当前选中的下标
				this.currentIndex = -1;
				//当前hover的焦点
				this.currentHoverIndex = -1;
				//当前下拉面板是否打开
				this.isOpen = false;
				//缓存当前页面中所有的oasSelect组件，以便页面操作上下按键时能找到此时下拉面板展开的那个
				oasSelect.allSelect.push(this);
			},
			//键盘向上键操作
			_liUp: function(e) {
				var self = this,
					currentIndex = self.currentHoverIndex,
					$el = this.$el;
				if (self.isOpen && self.currentHoverIndex != -1) {
					e.preventDefault();
					while (self.currentHoverIndex > 0) {
						self.currentHoverIndex--;
						if (!this.options.data[self.currentHoverIndex].disabled) {
							$el.find('.select-bd-li[index="' + self.currentHoverIndex + '"]').trigger('mouseover');
							break;
						}
						if (self.currentHoverIndex == 0) {
							self.currentHoverIndex = currentIndex;
							break;
						}
					}
				}
				return false;
			},
			//键盘向下键操作
			_liDown: function(e) {
				var self = this,
					currentIndex = self.currentHoverIndex,
					$el = this.$el;
				if (self.isOpen && self.currentHoverIndex != -1) {
					e.preventDefault();
					while (self.currentHoverIndex < this.options.data.length - 1) {
						self.currentHoverIndex++;
						if (!this.options.data[self.currentHoverIndex].disabled) {
							$el.find('.select-bd-li[index="' + self.currentHoverIndex + '"]').trigger('mouseover');
							break;
						}
						if (self.currentHoverIndex == this.options.data.length) {
							self.currentHoverIndex = currentIndex;
							break;
						}
					}
				}
				return false;
			},
			//判断是否是select元素还是数据初始化
			_judgeSelect: function() {
				var $el = this.$el;
				//select节点初始化
				if ($el.is('select')) {
					//整合元素初始化数据
					this._tidySelectData($el,true);
					//替换新的$el
					this.$el = $('<div></div>');
					//缓存初始化节点
					this.$dom = $el;
					this.type = 1;
				} else {
					//数据节点初始化
					this.type = 0;
				}
				this._preRenderData();
				//缓存初始化数据，供reset的时候使用
				this.recordOptions = $.extend(true,{},this.options);
			},
			//整合元素初始化数据
			_tidySelectData:function($el,isfirst){
				var options = this.options;
				var self = this;
				var copyOptions = [];
				options.data = [];
				options.comment = $el.attr('comment');
				if(isfirst){
					if(options.disabled == null){
						options.disabled = $el.prop("disabled") ? true : false;
					}else{
						$el.prop("disabled",options.disabled);
					}
				}else{
					options.disabled = $el.prop("disabled") ? true : false;
				}
				$.each($el.find('option'), function(index, obj) {
					var isShow = $(obj).attr("isShow");
					if(isShow != undefined && isShow == "false"){
						return true;
					}
					var option = {};
					option.value = $(obj).attr("value");
					option.text = $(obj).text();
					option.selected = $(obj).prop("selected") ? true : false;
					if(option.selected && isfirst){
						self.initChosen = index;
					}
					option.disabled = $(obj).prop("disabled") ? true : false;
					option.vip = $(obj).attr("vip") ? true : false;
					option.comment = "";
					option.title = option.text;
					options.data.push(option);
					if(isfirst){
						copyOptions.push(obj);
						self.copyOptions = $.extend(true,[],copyOptions);
					}
				});
			},
			//预处理模版数据集合，isInvoke为区分是初始化调用，还是已初始化之后的调用
			_preRenderData: function(isInvoke) {
				var options = this.options,
					_renderData = {},
					list = [],
					comment = false,
					chosenIndex = -1;
				vipList = [],
                noVipList = [];
                //newVipList = [],
                //vipIndexs = [],
				commentText = "",
				tip = options.tip;
				if (options.comment) {
					comment = options.comment;
				}
				list = options.data;
                for (var i = 0, len = list.length; i < len; i++) {
                    var item = list[i];
                    item.disabled = !! item.disabled;
                    item.selected = !! item.selected;
                    if (item.vip) {
                        //TODO: need to clone vip item ?
                        vipList.push(item);
                    } else {
                        noVipList.push(item);
                    }
                }
                options.data = vipList.concat(noVipList);
                /* removed by txqin
				for (var i = 0, len = list.length; i < len; i++) {
					if (!list[i].disabled) {
						list[i].disabled = false;
					}
					if (!list[i].selected) {
						list[i].selected = false;
					}
					if (list[i].vip) {
						vipList.push(list[i]);
						list[i].vip = false;
						vipIndexs.push(i);
					}
				}
				for (var i = 0, len = vipList.length; i < len; i++) {
					var vipOptionClone = {};
					for (var param in vipList[i]) {
						vipOptionClone[param] = vipList[i][param];
					}
					vipOptionClone.vip = true;
					newVipList.push(vipOptionClone);
				}
                // this loop has index error defect
				for (var i = 0; i < vipIndexs.length; i++) {
					list.splice(vipIndexs[i],1);
				}
				options.data = newVipList.concat(list);
                */
				chosenIndex = this._chosenSelect();
				if (chosenIndex != -1) {
					tip = options.data[chosenIndex].text;
					commentText = options.data[chosenIndex].comment;
				}
				this.currentHoverIndex = chosenIndex;
				this.currentIndex = chosenIndex;
				if(options.disabled == null){
					options.disabled = false;
				}
				//tip:输入框中的提示
				//list:整合好的下拉面板的数据集合
				//disabled:下拉组件是否处于disable状态
				//chosenIndex:默认选中的节点下标
				//commentText:选中节点的标注文本
				//showType:下拉面板展示方向
				//comment:下拉框的标注
				this._renderData = _renderData = {
					tip: tip,
					list: options.data,
					disabled: options.disabled,
					chosenIndex: chosenIndex,
					commentText: commentText,
					showType: options.showType,
					comment: comment
				};
				this._createDom(isInvoke);
			},

			/**创建页面节点
			 *@param type:
			 *	0--数据初始化
			 *	1--元素初始化
			 *@param isInvoke:
			 *	isInvoke为区分是初始化调用，还是已初始化之后的调用
			 *	true：已初始化之后的调用
			 *  false：初始化调用
			 */
			_createDom: function(isInvoke) {
				var html = "",
					dom = this.$dom;
					$el = this.$el;
				if(isInvoke){
					html = this.tpl(this._renderData);
					var $temp = $("<div></div>").append($(html));
					this.$el_select.empty().append($temp.find(".select-hd"));
					this.$el_hd = this.$el_select.find(".select-hd");
					this.$el.empty().append($temp.find(".select-bd"));
				}else{
					html = this.tpl(this._renderData);
					this.$el.empty().append(html).addClass('oas-select');
					if (this.type) {
						dom.after(this.$el).hide();
					}
					this.$el_select = this.$el;
					this.$el_hd = this.$el.find(".select-hd");
					this.$el = $("<div></div>");
					$el.find(".select-bd").appendTo(this.$el);
					this.$el.prependTo($('body')).addClass("oas-select-bd");
					if (this.type) {
						this.$el.data("origin",dom);
					}
				}
				this._adjustLayout(this.type);
			},
			//判断初始化中哪个下拉项被选中
			_chosenSelect: function() {
				var chosenIndex = -1;
				$.each(this.options.data, function(index, obj) {
					if (obj.selected) {
						chosenIndex = index;
						return false;
					}
				});
				return chosenIndex;
			},
			//调整数据初始化组件的长和宽:@param type:0--数据初始化,1--元素初始化
			_adjustLayout: function(type) {
				var options = this.options;
				//if (!type) {
					this._adjustHeight();
					this._adjustWidth();
				//}
			},

			_adjustHeight: function() {
				if (typeof(this.options.height) == "number") {
					this.options.height = this.options.height <= this.options.maxHeight ? this.options.height : this.options.maxHeight;
					var trueheigth = 30 * this.options.data.length;
					if (trueheigth <= this.options.height) {
						this.$el.find(".select-bd").css("height", trueheigth);
					} else {
						this.$el.find(".select-bd").css("height", this.options.height).addClass("overflow-y").addClass("oas-scroll-webkit");
					}
				}else if(this.options.height == "auto"){
					var trueheigth = 30 * this.options.data.length;
					if (trueheigth <= this.options.maxHeight) {
						this.$el.find(".select-bd").css("height", trueheigth);
					} else {
						this.$el.find(".select-bd").css("height", this.options.maxHeight).addClass("overflow-y").addClass("oas-scroll-webkit");
					}
				}
			},

			_adjustWidth: function() {
				var $el = this.$el;
				var options = this.options;
				var $el_hd = this.$el_hd;
				if (options.comment && options.width <= 130) {
					options.width = 130;
				}
				if (!options.comment && options.width <= 60) {
					options.width = 60;
				}
				$el_hd.parent().width(options.width);
				$el_hd.width(options.width - 42);
				$el.find(".select-bd").width(options.width - 2);
				if (typeof(this.options.height) == "number") {
					this.options.height = this.options.height <= this.options.maxHeight ? this.options.height : this.options.maxHeight;
					var trueheigth = 30 * this.options.data.length;
					if (trueheigth <= this.options.height) {
				$el.find("li").width(options.width - 22);
					} else {
						$el.find("li").width(options.width - 22 -10);
					}
				}else if(this.options.height == "auto"){
					var trueheigth = 30 * this.options.data.length;
					if (trueheigth <= this.options.maxHeight) {
						$el.find("li").width(options.width - 22);
					} else {
						$el.find("li").width(options.width - 22 -10);
					}
				}
			},
			//根据option的value找到它的下标
			_findIndexByValue: function(value) {
				var chosenIndex = -1,
					$el = this.$el;
				$.each($el.find('.select-bd-li'), function(i, n) {
					if ($(n).attr('val') == value) {
						chosenIndex = i;
						return false;
					}
				});
				return chosenIndex;
			},
			//根据传入的条件找到它的下标
			_findIndexByCondition: function(selected) {
				var chosenIndex = -1;
				if (typeof(selected) == "number") {
					if (selected > -1 && selected < this.options.data.length) {
						chosenIndex = selected;
					}
				} else {
					chosenIndex = this._findIndexByValue(selected);
				}
				return chosenIndex;
			},
			//改变option的disable状态
			_changeDisableStatus: function(selected, status) {
				var chosenIndex = this._findIndexByCondition(selected);
				if (chosenIndex == -1) {
					return;
				}
				this.options.data[chosenIndex].disabled = status;
				this._reInitSelect();
			},
			//重新初始化下拉面板
			_reInitSelect: function() {
				this._desTroyOverLay();
				this.currentIndex = -1;
				this.currentHoverIndex = -1;
				this.isOpen = false;
				this._preRenderData(true);
				this._openOverLay();
			},
			//销毁下拉面板的overlay
			_desTroyOverLay:function(){
				if(this.$el.data("oasOverlay")){
					this.$el.oasOverlay("destroyOverlay");
				}
			},
			// 对外方法集
			invoke: {
				//改变下拉框的宽度
				width: function(width) {
					this.options.width = width;
					this._adjustWidth();
					return this;
				},
				//改变下拉框的高度
				height: function(height) {
					this.options.height = height;
					this._adjustHeight();
					return this;
				},
				//改变下拉框的最大高度
				maxHeight: function(height) {
					this.options.maxHeight = height;
				},
				//获取当前被选中的option对象
				select: function(selected) {
					var self = this,
						$el = this.$el,
						chosenIndex = -1,
						option = {};
					if (typeof(selected) == "undefined") {
						return this.options.data[this.currentIndex];
					} else {
						chosenIndex = this._findIndexByCondition(selected);
						if (chosenIndex == -1) {
							return;
						}
						for (var i = 0; i < this.options.data.length; i++) {
							this.options.data[i].selected = false;
						};
						this.options.data[chosenIndex].selected = true;
						if(this.type == 1){
							this.$dom.val(this.options.data[chosenIndex].value);
							this.$dom.find("option").removeAttr("selected");
							this.$dom.find("option").eq(chosenIndex).attr("selected","true");
							this.$dom.trigger('change');
						}
						this._reInitSelect();
						this._emit('onChange', [this.options.data[chosenIndex].value, this.options.data[chosenIndex].text]);
						return this;
					}
				},
				//设置下拉框被选中的项@param data {ObjectArray}
				data: function(optionList) {
					if (optionList.length > 0) {
						this.options.data = optionList;
						this._reInitSelect();
					}
				},
				//将某个选项置为不可选,param selected {Number || String} Number为置为不可选的索引值，为String类型时，为option的value值  
				disableOption: function(selected) {
					this._changeDisableStatus(selected, true);
				},
				//将某个选项置为可选
				enableOption: function(selected) {
					this._changeDisableStatus(selected, false);
				},
				//移除某个选项
				removeOption: function(selected) {
					var chosenIndex = this._findIndexByCondition(selected);
					if (chosenIndex == -1) {
						return;
					}
					this.options.data.splice(chosenIndex, 1);
					if(this.type){
						this.$dom.find("option").eq(chosenIndex).remove();
					}
					if (this.options.data.length > 0) {
						this._reInitSelect();
					}
				},
				//添加某个选项
				addOption: function(start, option) {
					if (start > -1 && start < this.options.data.length || this.options.data.length == 0) {
						this.options.data.splice(start, 0, option);
						//{value:"",text:"",selected:true,disabled:false
						if(this.type){
							var $option = $("<option></option>");
							$option.text(option.text).attr("value",option.value);
							if(option.selected){
								$option.attr("selected","true");
							}
							if(option.disabled){
								$option.attr("disabled","true");
							}
							this.$dom.find("option").eq(start).before($option);
						}
						this._reInitSelect();
					}
				},
				//通知select面板向上还是向下展示
				showDirection: function(direction) {
					if (typeof(direction) != 'undefined') {
						this.options.showType = direction;
						this._reInitSelect();
					}
				},
				//重置下拉框
				reset: function() {
					this.options = this.recordOptions;
					this._reInitSelect();
					if(this.type == 1){
						this.$dom.empty().append(this.copyOptions);
						this.$dom.val(this.options.data[this.initChosen].value);
						this.$dom.find("option").removeAttr("selected");
						this.$dom.find("option").eq(this.initChosen).attr("selected","true");
					}
				},
				//重新刷新下拉框数据,只针对元素初始化的场景
				reLoad: function() {
					if(this.type == 1){
						this._tidySelectData(this.$dom);
						this._reInitSelect();
					}	
				},
				//隐藏相应面板
				hidePanel: function() {
					//this.$el.css('display', "none");
					this.$el.oasOverlay('hide');
					this.$_transparent.remove();
				},
				//展示相应面板
				showPanel: function() {
					//this.$el.css('display', "block");
					if(this.$el.css('display') != 'block'){
						this.$el.oasOverlay('show');
						// create the transparent mask for click the other position(iframe) can be close 
						this.$_transparent = $('<div style="position:fixed;top:0;left:0;right:0;bottom:0;z-index:99994;"></div>').appendTo('body');
					}
				},
				//下拉框是否激活
				disable:function(disable){
					this.options.disabled = disable;
					this._reInitSelect();
					if(this.type){
						if(disable){
							this.$dom.prop('disabled',true);
						}else{
							this.$dom.prop('disabled',false);
						}
					}
				},
				//获取下拉框disabled状态
				getDisableStatus:function(){
					return this.options.disabled;
				}
			}
		});
	oasSelect = {};
	oasSelect.allSelect = [];
	$(document).off('keydown.oasSelect');
	$(document).on('keydown.oasSelect', function(event){
			switch (event.keyCode) {
			case 38:
				$.each(oasSelect.allSelect, function(i, item) {
					if (item.isOpen) {
						item._liUp(event);
					}
				});
				break;
			case 40:
				$.each(oasSelect.allSelect, function(i, item) {
					if (item.isOpen) {
						item._liDown(event);
					}
				});
				break;
			default:
				break;
			}
	});
})();
/**
 * 弹窗组件
 * @ author jyye
 * @ create time 2014.08.05
 * @ version  1.0.0
 */
(function() {

	// 组件名和文件名请统一

	$.oasUiFactory("oasDialog", {
		options: {

			/**
             * 弹窗显示类型,分为"success","confirm","error","warning","loading"
             * @type string
             * @default null
             * @example
             * $("body").oasDialog({
             *     type:"success"
             * })
             */
			type: 'success',

			/**
             * 头部标题
             * @type string
             * @default null
             * @example
             *  $("body").oasDialog({
             *     titkle:"公共研发"
             * })
             */
			title: '头部标题',
			/**
             * 内容iframe页
             * @type string
             * @default null
             * @example
             *  $("body").oasDialog({
             *     src:"wangzong_v4/platntform/index.html"
             * })
             */
			src:null,
			/**
             * 内容文本(支持简单html结构)
             * @type string
             * @default null
             * @example
             *  $("body").oasDialog({
             *     content:'<p>xxxxxxxxx</p>'+'<p>ccccccccccccc</p>',
             * })
             */
			content:null,
			/**
             * 内容文本
             * @type string
             * @default null
             * @example
             *  $("body").oasDialog({
             *     message:"公共研发"
             * })
             */
			message: null,
			/**
             *是否有遮罩
             * @type boolean
             * @default false
             * @example
             * $("body").oasDialog({
             *     modal:false
             * })
             */
			modal: false,
			/**
             * 弹窗位置
             * @type arry
             * @default 居中
             * @example
             * $("body").oasDialog({
             *     position:[100,200]
             * })
             */
			position: null,
			/**
             *是否默认展示
             * @type boolean
             * @default false
             * @example
             * $("body").oasDialog({
             *     autoOpen:false
             * })
             */
			autoOpen: false,
			/**
             *是否可以拖拽
             * @type boolean
             * @default true
             * @example
             * $("body").oasDialog({
             *     isDrag:false
             * })
             */
			isDrag: true,
			/**
             *是否可以fixed定位
             * @type boolean
             * @default false
             * @example
             * $("body").oasDialog({
             *     fixed:false
             * })
             */
			fixed: false,
			/**
             *设置宽高，只在有src的情况下可用
             * @type int
             * @default false
             * @example
             * $("body").oasDialog({
             *     width:100,
             *	   height:100		
             * })
             */
            width: 'auto',
            height: 'auto',
			/**
             * 弹窗按钮
             * @type arry
             * @default 确定，取消
             * @example
             * $("body").oasDialog({
             *     button:[]
             * })
             */
			buttons: [{
						label:'确定', 
						callBack : function($el){
							$el.oasDialog('close');
						}	
					}],
			closeable: true,
			/*弹窗打窗口对象
			 * $("body").oasDialog({
             *     context:$("body")
             * })
			*/
			context:null,
			//打开回调函数
			onOpen: function() {
			},
			//关闭回调函数
			onClose: function() {
			},
			//load回调函数
			onLoad: function() {
			},
			//拖拽开始事件
			onDragStart: function() {
			},
			//拖拽中事件
			onDrag: function() {

			},
			//拖拽结束事件
			onDragEnd: function() {

			}

		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var options = this.options,tpl;
			if(options.src ){
				tpl =  '<div class="dialog-hd {{if isDrag === true}}drag{{/if}}"><h4 class="oas-title">{{title}}</h4>{{if closeable === true}}<a href="javascript:;" class="dialog-close oasicon oasicon-close"></a>{{/if}}</div>' +
						'<div class="dialog-bd">' +
							'<iframe src="" frameborder="0" width="100%" height="100%" class="dialog-iframe"></iframe>'+
							'<div class="opacity"></div>'+
						'</div>';
			}else{
				tpl =  '<div class="dialog-hd {{if isDrag === true}}drag{{/if}}"><h4 class="oas-title">{{title}}</h4>{{if closeable === true}}<a href="javascript:;" class="dialog-close oasicon oasicon-close"></a>{{/if}}</div>' +
						'<div class="dialog-bd">' +
							'<div class="prompt-info {{if type === "error"}}error{{/if}}{{if type === "success"}}success{{/if}}{{if type === "confirm"}}confirm{{/if}}{{if type === "warning"}}warning{{/if}}">' +
								'<div class="prompt-img"></div>' +
								'{{if message === null}}' +
								'<h4 class="oas-title content"><span>{{content}}</span></h4>' +
								'{{/if}}' +
								'<h4 class="oas-title content"><span>{{#message}}</span></h4>' +
							'</div>' +
						'</div>';
			}
			

			return template.compile(tpl);
		},

		// 事件
		events: {
			'click .dialog-close': function(self, evt) {
				this._iCall('close');
				return false;
			}
		},

		_create: function() {
			var options = this.options;
			this._adjustButtonOrder();
			this.$el.addClass("oas-dialog");
			this._preRenderData();
			this._location(this.$el, options.position);
			this._drag();
		},
		//调整button顺序:确定在最左边，取消在最右边
		_adjustButtonOrder:function(){
			var buttons = this.options.buttons,
				sureButton = null,
				cancelButton = null;

			if(buttons){
				for (var i = 0; i < buttons.length; i++) {
					if(buttons[i].label.indexOf("确定") != -1){
						sureButton = buttons[i];
						buttons.splice(i,1);
						break;
					}
				}
				for (var j = 0; j < buttons.length; j++) {
					if(buttons[j].label.indexOf("取消") != -1){
						cancelButton = buttons[j];
						buttons.splice(j,1);
						break;
					}
				}
			}
			if(sureButton !== null){
				buttons.unshift(sureButton);
			}
			if(cancelButton !== null){
				buttons.push(cancelButton);
			}
		},
		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				type = this.type || (this.type = options.type),
				title = this.title || (this.title = options.title),
				message = this.message || (this.message = options.message),
				content = this.$el.text(),
				src = this.src || (this.src = options.src),
				isDrag = options.isDrag;


			this._renderData = _renderData = {
				type: type,
				title: title,
				isDrag: isDrag,
				message: message,
				src:src,
				closeable:options.closeable,
				content: content

			};

			this._createDom();
			this._drag();
		},
		//弹窗定位
		_location: function($el, location) {
			if (location) {
				this.left = location[0];
				this.top = location[1];
			}
			if (this.left === 'right') {
				if(this.top === 'bottom'){
					if (!this.options.fixed) {
						$el.css({
							position: "absolute",
							bottom: 0,
							right: 0
						})
					} else {
						$el.css({
							position: "fixed",
							bottom: 0,
							right: 0
						})
					}
				}else {
					if (typeof this.top != 'number'){
						this._checkTop(this.top, $el);
					}
					if (!this.options.fixed) {
						$el.css({
							position: "absolute",
							top: this.top,
							right: 0
						})
					} else {
						$el.css({
							position: "fixed",
							top: this.top - $(window).scrollTop(),
							right: 0
						})
					}
				}
				
			}else if(this.top === 'bottom'){
				if (typeof this.left != 'number') {
					this._checkLeft(this.left, $el);
				}
				if (!this.options.fixed) {
					$el.css({
						position: "absolute",
						bottom: 0,
						left: this.left
					})
				} else {
					$el.css({
						position: "fixed",
						bottom: 0,
						left: this.left
					})
				}
			}else{
				if (typeof this.left != 'number') {
					this._checkLeft(this.left, $el);
				}
				if (typeof this.top != 'number') {
					this._checkTop(this.top, $el);
				}
				if (!this.options.fixed) {
					$el.css({
						position: "absolute",
						top: this.top,
						left: this.left
					})
				} else {
					$el.css({
						position: "fixed",
						top: this.top - $(window).scrollTop(),
						left: this.left
					})
				}
			}
			
		},
		//判断left值
		_checkLeft: function(_left, $el) {
			switch (_left) {
				case "left":
					this.left = 0;
					break;
				case "center":
					this.left = ($(window).width() - $el.outerWidth()) / 2;
					break;
				default:
					this.left = ($(window).width() - $el.outerWidth()) / 2;
					break;
			}
		},
		//判断top值
		_checkTop: function(_top, $el) {
			switch (_top) {
				case "top":
					this.top = 0;
					break;
				case "center":
					this.top = ($(window).height() - $el.outerHeight()) / 2 + $(window).scrollTop();
					break;
				default:
					this.top = ($(window).height() - $el.outerHeight()) / 2 + $(window).scrollTop();
					break;
			}
		},

		//拖拽事件
		_drag: function() {
			var options = this.options,
				$el = this.$el,
				self = this;
			if (options.isDrag === true) {
				if (options.fixed === true) {
					$el.oasDrag({
						handle: '.dialog-hd',
						position: 'fixed',
						// 拖拽开始事件
						onStart: function(){self._emit('onDragStart')},
						// 拖拽中事件
						onDrag: function(){
							$el.find(".opacity").show();
							self._emit('onDrag');
						},
						// 拖拽结束事件
						onEnd: function(){
							$el.find(".opacity").hide();
							self._emit('onDragEnd');
						}
					});
				} else {
					$el.oasDrag({
						handle: '.dialog-hd',
						position: 'absolute',
						// 拖拽开始事件
						onStart: function(){self._emit('onDragStart')},
						// 拖拽中事件
						onDrag: function(){
							$el.find(".opacity").show();
							self._emit('onDrag');
						},
						// 拖拽结束事件
						onEnd: function(){
							$el.find(".opacity").hide();
							self._emit('onDragEnd');
						}
					});
				}

			}
		},

		_createButtons : function(obj, buttons,iframe) {
            var self = this,$el = this.$el;
            $.each(buttons, function(index) {
            	var label = buttons[index].label
                var $btn;
                if(buttons[index].recommend == true){
                	$btn = $('<a href="javascript:;" class="oas-btn oas-recommend">' + label + '</a>');
                	if(buttons[index].disabled == true){
                		$btn = $('<a href="javascript:;" class="oas-btn oas-disabled">' + label + '</a>');
                	}
                }else if(buttons[index].disabled == true){
                	$btn = $('<a href="javascript:;" class="oas-btn oas-disabled">' + label + '</a>');
                }else{
                	$btn = $('<a href="javascript:;" class="oas-btn">' + label + '</a>')
                }
               	obj.append($btn);
                $btn.bind("click", function(event) {
                	if($(this).hasClass("oas-disabled")){
                		return false
                	}else{
                		if(iframe){
	                		buttons[index].callBack($el,iframe.contentWindow);
	                	}else{
	                		buttons[index].callBack($el);
	                	}
                	}
                	
                })
            })
        },

        /**设置属性，宽度等**/
		_setProp: function () {
			var $el = this.$el,
				options = this.options,
				width = options.width,
				height = options.height;

			
			$el.width(width+30);
			$el.find(".dialog-bd").height(height);
			$el.find(".dialog-iframe").width(width);
			$el.find(".dialog-iframe").height(height);
		},

		_cacheData: function (argument) {
			var self = this,
				$el = this.$el;
			setTimeout(function(){
				self._iCall('src', [self.options.src]);
			},300);

			// 缓存半遮页设置数据
			this.customData = {};
		},

		_createDom: function() {
			var options = this.options,
				$el = this.$el,
				html = this.tpl(this._renderData);
			this.$el.append(html);
			// 缓存iframe
			this.$frame = $el.find('iframe');
			if(options.src){
				$el.addClass("info-mode");
				this._cacheData();
			}
			var buttons = options.buttons;
			if (buttons) {
				$el.append('<div class="dialog-ft"></div>')
				var $btns = $el.find(".dialog-ft");
                this._createButtons($btns, buttons,this.$frame[0]);
            }
            if(options.type === 'loading'){
            	$el.addClass("loading");
            	$el.find(".dialog-hd").remove();
            }
			if (options.autoOpen === true) {
				this._iCall('open');
			}
			if (options.content){
				$el.addClass("info-mode");
				$el.find(".dialog-bd").empty().append(options.content);
				$el.find(".dialog-bd").css('overflow-y','auto');
			}
			this._setProp();
			
		},

		// 组件对外方法集
		invoke: {
			//打开弹窗
			open: function() {
				var options = this.options;
				var $el = this.$el;
				if (options.modal === true) {
					Mask.open();
					$el.addClass("hasModal");
				}
				$el.show();
				if(options.src){
					this._emit('onOpen', [$el,this.$frame[0].contentWindow]);
				}else{
					this._emit('onOpen', [$el]);
				}
				
			},
			//关闭弹窗
			close: function() {
				var $el = this.$el;
				var options = this.options;
				var result;
					
					if(options.src){
						result = this._emit('onClose', [$el,this.$frame[0].contentWindow]);
					}else{
						result = this._emit('onClose', [$el]);
					}
					if(result != false){
						this.$el.hide(0,function(){
							$el.remove();
						});
						if( this.options.modal == true){
							Mask.close();
							$el.removeClass("hasModal");
							$("body").find(".oas-mask").removeClass("zindex93");
						}
					}
					
			},
			/*
			 * 修改标题
			 * @param _title 修改的标题名称
			 */
			title: function(_title) {
				this.$el.find('.dialog-hd h4').text(_title);
			},
			/*
			 * 修改内容信息
			 * @param _message 修改的提示信息
			 */
			message: function(_message) {
				this.$el.find('.content').text(_message);
			},
			btnDisabled: function(_disabled) {
				if(_disabled == true){
					this.$el.find('.dialog-ft .oas-btn').addClass("oas-disabled");
				}else{
					this.$el.find('.dialog-ft .oas-btn').removeClass("oas-disabled");
				}
			},
			src: function(_src) {
				var self = this;
				//if (_src && this.src != _src) {
					this.options.src = _src;
					this._iCall('refresh');
					// ie
					if(this.$frame[0].attachEvent){
						this.$frame[0].attachEvent('onload', _iframeOnload);
					}else{
						this.$frame[0].onload = _iframeOnload;
					}
					function _iframeOnload(){
						// 设置oasShyPage到iframe中，确保iframe中可以直接使用oasShyPage来调用某些方法。
						var contentWindow = self.$frame[0].contentWindow;
						if(contentWindow.OasDialog){
							contentWindow.OasDialog.close = function(){
								self._iCall('close');
							};
						}else{
							contentWindow['OasDialog'] = self;
						}
						contentWindow['OasDialog'].openerWin = window;

						// 设置自定义属性到iframe的window中
						for(var key in self.customData){

							contentWindow[key] = self.customData[key];
						}
						// 触发iframe的onload事件
						self._emit('onLoad', [self.$el, contentWindow]);
						if(contentWindow.init){
							contentWindow.init();
						}
					}
				//}
			},
			refresh: function() {
				var options = this.options,
					src = options.src;
				src = /\?/.test(src) ? src + '&newTime=newTime' + new Date().getTime() : src + '?newTime=newTime' + new Date().getTime();

				this.$frame.attr('src', src);
			},
			/*
			 * 获取的数据。
			 * @param key {string}需要获取的数据的key
			 */
			getData: function(key) {
				var value = this.$frame[0].contentWindow[key];

				if (value !== undefined) {
					return value;
				} else {
					throw Error(key + '----不存在于半遮页的window对象中，请确认是否保存或书写正确');
				}
			},

			/*
			 * 设置的数据。
			 * @param obj {string}需要设置的数据
			 */
			setData: function(obj){

				var contentWindow;
				this.customData = {};
				for(var key in obj){

					if(obj.hasOwnProperty(key)) this.customData[key] = obj[key];
					
				}

				if(this.$frame[0].contentWindow && (contentWindow = this.$frame[0].contentWindow)){
					for(var key in this.customData){

						contentWindow[key] = this.customData[key];
					}
				}
				

				// 原先直接赋值给半遮页里iframe的window对象，由于某些情况设置数据时iframe还未加载完成，导致数据设置不成功。故废弃
				// var contentWindow = this.$frame[0].contentWindow.window;
				// // 循环赋值
				// for(var key in obj){
				// 	console.log(key)
				// 	contentWindow[key] = obj[key];
				// }

			},
			/*
			 * 修改弹窗位置
			 * @param _left,_top 修改的弹窗位置信息
			 */
			position: function(_left, _top) {
				this.left = _left || _left == 0 ? _left : this.left;
				this.top = _top || _top == 0 ? _top : this.top;
				if (typeof this.left != "number") {
					this._checkLeft(this.left, this.$el);
				}
				if (typeof this.top != "number") {
					this._checkTop(this.top, this.$el);
				}
				this.$el.css({
					left: this.left,
					top: this.top
				});
			}
		}
	});

})();
;
(function() {
	$.oasUiFactory("oasToolTip", {
		options: {
			//tooltip需要展示的内容
			title: '',

			//tooltip展示位置相对于触发元素的位置
			placement: 'left',
			//tooltip展示的偏移量
			offset:'0,0'
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl = '<div class="tooltip-arrow"></div>' +
				'<p class="tooltip-bd">{{#title}}</p>';
			return template.compile(tpl);
		},

		// 事件
		events: {
			
		},

		_create: function() {
			this.needShow = true;
			this.triggerEle = this.$el;
			this._bindTriggerElement();
			
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {};
			options.title = $(this.triggerEle).attr("data-oastooltip-title");
			this._renderData = _renderData = {
				title: options.title
			};
			this._createDom();
		},
		//给触发元素绑定事件
		_bindTriggerElement: function() {
			var options = this.options,
				self = this;
			// this.$el.hover(function() {
			// 	self._showWithAnimation(this);
			// }, function() {
			// 	self._hideWithAnimation(this);
			// });
			$(this.$el).on("mouseenter."+this.uiName,function(){
				self._showWithAnimation(this);
			});
			$(this.$el).on("mouseleave."+this.uiName,function(){
				self._hideWithAnimation(this);
			});
			if(this.triggerEle.attr('data-oastooltip-autoOpen') == 'true'){
				this._showWithAnimation(this.triggerEle);
			}
		},
		_showWithAnimation: function(ele) {
			if(!this.needShow){
				return;
			}
			$('body').find('.oas-tooltip').remove();
			this._$el = $('<div></div>');
			this._preRenderData();
			var options = this.options,
				$el = this._$el,
				thisHeight = $el.outerHeight(), //tip高度
				thisWidth = $el.outerWidth(), //tip宽度
				trigerHeight = $(ele).outerHeight(), //触发元素的高度
				trigerWidth = $(ele).outerWidth(), //触发元素的宽度
				triggerLeft = $(ele).offset().left, //触发元素的left
				triggerTop = $(ele).offset().top; //触发元素的top
			$el.find(".tooltip-arrow").removeClass("left-arrow bottom-arrow right-arrow top-arrow");
			if (options.placement == "left") {
				var leftTemp = triggerLeft-25-thisWidth + parseInt(this._getXorY().X,10);
				leftTemp = leftTemp > (triggerLeft-25-thisWidth) ? (triggerLeft-25-thisWidth) : leftTemp;
				$el.css("left",leftTemp);
				if(thisHeight <= trigerHeight){
					$el.css("top",triggerTop + (trigerHeight-thisHeight)/2 + parseInt(this._getXorY().Y,10));
				}else{
					$el.css("top",triggerTop - (thisHeight-trigerHeight)/2 + parseInt(this._getXorY().Y,10));
				}
				$el.find(".tooltip-arrow").addClass("right-arrow");
				var left = triggerLeft-10-thisWidth + parseInt(this._getXorY().X,10);
				left = left > (triggerLeft-10-thisWidth) ? (triggerLeft-10-thisWidth) : left;
				$el.animate({left:left,opacity: 'show'},300);
			} else if (options.placement == "top") {
				var topTemp = triggerTop-25-thisHeight + parseInt(this._getXorY().Y,10);
				topTemp = topTemp > (triggerTop-25-thisHeight) ? (triggerTop-25-thisHeight) : topTemp;
				$el.css("top",topTemp);
				if(thisWidth <= trigerWidth){
					$el.css("left",triggerLeft+(trigerWidth-thisWidth)/2 + parseInt(this._getXorY().X,10));
				}else{
					$el.css("left",triggerLeft-(thisWidth-trigerWidth)/2 + parseInt(this._getXorY().X,10));
				}
				$el.find(".tooltip-arrow").addClass("bottom-arrow");
				var top = triggerTop-10-thisHeight + parseInt(this._getXorY().Y,10);
				top = top > (triggerTop-10-thisHeight) ? (triggerTop-10-thisHeight) : top;
				$el.animate({top:top,opacity: 'show'},300);
			} else if (options.placement == "right") {
				var leftTemp = triggerLeft+25+trigerWidth + parseInt(this._getXorY().X,10);
				leftTemp = leftTemp < (triggerLeft+25+trigerWidth) ? (triggerLeft+25+trigerWidth) : leftTemp;
				$el.css("left",leftTemp);
				if(thisHeight <= trigerHeight){
					$el.css("top",triggerTop + (trigerHeight-thisHeight)/2 + parseInt(this._getXorY().Y,10));
				}else{
					$el.css("top",triggerTop - (thisHeight-trigerHeight)/2 + parseInt(this._getXorY().Y,10));
				}
				$el.find(".tooltip-arrow").addClass("left-arrow");
				var left = triggerLeft+10+trigerWidth + parseInt(this._getXorY().X,10);
				left = left < (triggerLeft+10+trigerWidth) ? (triggerLeft+10+trigerWidth) : left;
				$el.animate({left:left,opacity: 'show'},300);
			} else if (options.placement == "bottom") {
				var topTemp = triggerTop+25+trigerHeight + parseInt(this._getXorY().Y,10);
				topTemp = topTemp < (triggerTop+25+trigerHeight) ? (triggerTop+25+trigerHeight) : topTemp;
				$el.css("top",topTemp);
				if(thisWidth <= trigerWidth){
					$el.css("left",triggerLeft+(trigerWidth-thisWidth)/2 + parseInt(this._getXorY().X,10));
				}else{
					$el.css("left",triggerLeft-(thisWidth-trigerWidth)/2 + parseInt(this._getXorY().X,10));
				}
				$el.find(".tooltip-arrow").addClass("top-arrow");
				var top = triggerTop+10+trigerHeight + parseInt(this._getXorY().Y,10);
				top = top < (triggerTop+10+trigerHeight) ? (triggerTop+10+trigerHeight) : top;
				$el.animate({top:top,opacity: 'show'},300);
			}
		},

		_hideWithAnimation: function() {
			$('body').find('.oas-tooltip').remove();
		},

		/**创建dom节点**/
		_createDom: function() {
			var html = this.tpl(this._renderData),
				$el = this._$el;
			$el.addClass('oas-tooltip');
			$el.empty().append(html).appendTo($('body'));


		},
		_getXorY:function(){
			var offset = this.options.offset,
			result = {};
			result.X = offset.split(",")[0];
			result.Y = offset.split(",")[1];
			return result;
		},
		// 组件对外方法集
		invoke: {
			//控制本身显示
			triggerShow:function(){
				this.needShow = true;
			},
			//控制本身隐藏
			triggerHide:function(){
				this.needShow = false;
			},
			//销毁方法
			destory: function(){
				this.$el.off("."+this.uiName);
				$('body').find('.oas-tooltip').remove();
			},
			//修改提示内容
			title:function(title){
				this.options.title = title;
				this.triggerEle.attr("data-oastooltip-title",title);
			},
			//修改offset
			offset:function(offset){
				this.options.offset = offset;
			}
		}
	});

	$(document).ready(function() {
		$('[data-oastooltip-title]').oasToolTip();
	})
})();
;
(function() {
	/**
	 * 分页组件，提供正常分页和简单分页。
	 * @ author rbai
	 * @ create time 2014.07.20
	 * @ version  1.1.0
     * 
	 * update 2015-2-10
	 * 优化分页页码展示
	 * 快速跳转支持全角数字。
	 * 优化箭头展示和页码展示
     * update 2015-3-4
	 * 当每页显示条数发送改变时，当前页码大于总页数时，当前页码置为最后一页。
	 * update 2015-6-10
	 * 增加分页尺寸配置，分页分为正常分页和小型分页
	 */
	$.oasUiFactory("oasPagin", {
		options: {

			// 分页类型，normal为正常型，simple为简单型。
			type: 'normal',

			// 分页的样式大小，normal为正常，little为小模式。
			size: 'normal',

			// 当前页码
			current: 1,

			// total,总条数
			total: null,

			// pageItems 每页显示条数
			pageItems: 10,

			//  是否需要快速跳转，只有在normal时生效。
			skipto: true,

			// 是否需要设置每页显示条数操作,只有在normal时生效
			setPageItems: true,

			//页码显示形式，当type为normal时生效。
			displayStyle: [2,3,2], 

			onPageNoChage: function(current, pageItems) {

			},

			onPageItemsChange: function(current, pageItems) {

			}
		},


		_template: function() {

			var options = this.options,
				tpl;
			if (options.type === 'normal') {
				tpl = '<span class="pagin-info">共&nbsp;<i>{{total}}</i>&nbsp;条数据&nbsp;<i>{{totalPages}}</i>&nbsp;页，当前显示第&nbsp;{{startItems}}-{{if endItems > total}}{{total}}{{else}}{{endItems}}{{/if}}&nbsp;条</span>' +
					'<div class="pagin-content">' +
					'{{if setPageItems === true}}' +
					'<div class="dropdown-btn" data-toggle="dropdown">' +
					'<a class="oas-btn-icon" href="javascript:;"><span class="btn-text">每页{{pageItems}}条</span><em class="oasicon oasicon-arrow-down"></em></a>' +
					'<ul class="dropdown-menu" data-toggle="dropdown-menu">' +
					'<li><span class="btn-text">每页10条</span></li>' +
					'<li><span class="btn-text">每页20条</span></li>' +
					'<li class="last"><span class="btn-text">每页50条</span></li>' +
					'</ul>' +
					'</div>' +
					'{{/if}}' +
					'{{if skipto === true}}' +
					'<input type="text" class="page" placeholder="页码">' +
					'<a class="oas-btn-icon skip" href="javascript:;" title="点击跳转">GO</a>' +
					'{{/if}}' +
					'<a class="oas-btn-icon pagin-btn prev{{if current == 1}} oas-disabled{{/if}}" href="javascript:;"><i class="oasicon oasicon-arrow-left"></i></a>' +
					'<ul class="paginations">' +
					'{{each list}}' +
					'{{if $value == current}}' +
					'<li class="active"><a href="javascript:;">{{$value}}</a></li>' +
					'{{else if $value == "..."}}' +
					'<li class="oas-pagin-ellipsis"><span>...</span></li>' +
					'{{else}}' +
					'<li class="pagin-no"><a href="javascript:;">{{$value}}</a></li>' +
					'{{/if}}' +
					'{{/each}}' +
					'</ul>' +
					'<a class="oas-btn-icon pagin-btn next oas-recommend{{if current == totalPages}} oas-disabled{{/if}}" href="javascript:;"><i class="oasicon oasicon-arrow-right"></i></a>' +
					'</div>';
			} else {
				tpl = '<span class="pagin-info"><i>{{total}}</i>&nbsp;条&nbsp;<i>{{current}}/{{totalPages}}</i>&nbsp;页</span>' +
					'<div class="pagin-content">' +
					'{{if skipto === true}}' +
					'<input type="text" class="page" placeholder="页码">' +
					'<a class="oas-btn-icon skip" href="javascript:;" title="点击跳转">GO</a>' +
					'{{/if}}' +
					'<a class="oas-btn-icon pagin-btn prev{{if current == 1}} oas-disabled{{/if}}" href="javascript:;"><i class="oasicon oasicon-arrow-left"></i></a>' +
					'<a class="oas-btn-icon pagin-btn next{{if current == totalPages}} oas-disabled{{/if}}{{if current < totalPages}} oas-recommend{{/if}}" href="javascript:;"><i class="oasicon oasicon-arrow-right"></i></a>' +
					'</div>';
			}

			return template.compile(tpl);
		},


		events: {

			// 快速跳转事件
			'click .skip': function(self, evt) {

				if ($(self).hasClass('oas-disabled')) return false;
				var page = parseInt(oasis.DBC2SBC($(self).prev('input').val()));

				if (page != this.current && page > 0 && page <= this.totalPages) {
					this._setCurrentPagin(page);
				}

			},

			// 页码点击事件
			'click .pagin-no': function(self, evt) {
				var current = parseInt($(self).text(), 10);
				this._setCurrentPagin(current);
			},

			// 按钮点击事件
			'click .pagin-btn': function(self, evt) {
				if ($(self).hasClass('oas-disabled')) return false;
				if ($(self).hasClass('homepage')) {

					this._setCurrentPagin(1);

				} else if ($(self).hasClass('prev')) {

					this._prevPagin();

				} else if ($(self).hasClass('next')) {

					this._nextPagin();

				}
			},

			// 选择每页显示多少条事件
			'click .dropdown-btn': function(self, evt){
				var that = this;
				if(!!$(evt.target).closest('.dropdown-menu').size() || $(evt.target).is('li')){
					setTimeout(function(){
						var pageItems = +$(self).find('.btn-text').eq(0).text().match(/[0-9][0-9]/g)[0];
						that._iCall('pageItems', [pageItems]);
						that._emit('onPageItemsChange', [pageItems, that.current]);
					},0);
				}
			}
		},


		_create: function() {
			var options = this.options;

			this.$el.addClass('oas-pagin clearfix').addClass('oas-pagin-' + options.size);

			this._preRenderData();

		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			if(this.options.total === null) return;
			var options = this.options,
				_renderData = {},
				total = this.total !== undefined ?  this.total : options.total,
				pageItems = this.pageItems || (this.pageItems = options.pageItems),
				totalPages = this.totalPages = Math.ceil(total / pageItems),
				current = this.current || (this.current = options.current),
				startItems = pageItems * (current - 1) + 1,
				endItems = pageItems * current,
				list = this._paginList();

			if(+total === 0){
				this.$el.hide();
				return;
			}else{
				this.$el.show();
			}

			this._renderData = _renderData = {
				total: total,
				pageItems: pageItems,
				totalPages: totalPages,
				current: current,
				startItems: startItems,
				endItems: endItems,
				setPageItems: options.setPageItems,
				skipto: options.skipto,
				list: list
			};

			this._createDom();

		},

		// 重新设置当前页码
		// @ param needEmit Boolean 是否触发事件
		_setCurrentPagin: function(current, needEmit) {

			if(this.current === current){
				return;
			}
			this.current = current;

			this._preRenderData();

			if(needEmit !== false){
				this._emit('onPageNoChage', [current, this.pageItems]);
			}
		},

		// 上一页
		_prevPagin: function() {
			var self = this,
				current;
			if (this.current > 1) {

				current = parseInt(this.current, 10) - 1;

				setTimeout(function(){
					self._setCurrentPagin(current);
				},0);
			}
		},

		// 下一页
		_nextPagin: function() {
			var self = this;
			if (this.current < this.totalPages) {
				current = parseInt(this.current, 10) + 1;

				setTimeout(function(){
					self._setCurrentPagin(current);
				},0);
				
			}
		},

		/*
		 * 计算分页数据list
		 */
		/*_paginList: function() {

			var current = this.current,
				totalPages = this.totalPages,
				start = 1,
				end,
				list = [],
				i,
				tempStart;
			if (current <= 10) {
				start = 1;
				end = 10;
			} else {

				tempStart = Math.floor(current / 10);
				if (current > tempStart * 10) {

					start = tempStart * 10 + 1;
					end = start + 9;

				} else {

					start = (tempStart - 1) * 10 + 1;
					end = start + 9;

				}

			}

			if (end > totalPages) {
				if(end > 10){
					list.push('…');
				}
				end = totalPages;
			}

			for (var i = start; i <= end; i++) {
				if (i < 10) {
					list.push('0' + i);
				} else {
					list.push(i);
				}

			};

			if (totalPages > end) {
				list.push('...');
			}

			return list;
		},*/

		_paginList: function(){
			var options = this.options,
				interval = this._getInterval(),
			    displayStyle = options.displayStyle,
				list = [];
			if (interval[0] > 0 && displayStyle[2] > 0) {
				var end = Math.min(displayStyle[2], interval[0]);
				for (var i = 0; i < end; i++) {
					this._addToPaginList(list, i);
				}
				if(displayStyle[2] < interval[0]){
					list.push('...');
				}
			}
			// 中间的页码
			for (var i = interval[0]; i < interval[1]; i++) {
				this._addToPaginList(list, i);
			}
			if (interval[1] < this.totalPages && displayStyle[2] > 0) {
				 // 点点点字符串
				if (this.totalPages - displayStyle[2] > interval[1]) { 
					// 将点点点添加到容器
					list.push('...');
				}
				var begin = Math.max(this.totalPages - displayStyle[2], interval[1]);
				for (var i = begin; i < this.totalPages; i++) {
					this._addToPaginList(list, i);
				}
			}

			return list;
		},

		/*
		 * 将分页信息添加到List中
		 */
		_addToPaginList: function(list, id){
			id = (id < 0 ? 0 : (id < this.totalPages ? id : this.totalPages - 1)),id++;
			list.push(id);
		},

		//获取页码中间的起始值（当时type为normal时生效）
		_getInterval : function() {
			var current = this.current - 1, 
				options = this.options,
			    displayStyle = options.displayStyle;
			//··中间和末尾显示页面的个数
			var ne_half = parseInt(displayStyle[1]) + parseInt(displayStyle[2]);
			//··总页数
			var pages = this.totalPages;
			//··总页数 - 中间显示页数
			var upper_limit = pages - displayStyle[1];
			//··计算中间显示页码的开始
			var start = current + 1 >= ne_half ? Math.max(Math.min(parseInt(current)+2
									- displayStyle[1], upper_limit), 0) : 0;
			//··计算中间显示页码的结束
			var end = current + 1 >= ne_half ? Math.min(parseInt(current) + 2,
					pages) : Math.min(ne_half, pages);
			return [start, end];
		},


		_createDom: function() {
			var html = this.tpl(this._renderData);

			this.$el.empty().append(html);

			if(this.current === this.totalPages){
				this.$el.find('.prev').addClass('oas-recommend');
			}
		},


		// 对外方法集
		invoke: {
			/*
			 * 修改总数据条数
			 * @param _total 需要修改的总记录条数
			 */
			total: function(_total) {

				// if(this.options.total !== _total){
				// 	this.current = 1;
				// }

				this.options.total = this.total = _total;
				this._preRenderData();
			},

			/*
			 * 修改每页显示的记录条数。
			 * @param _pageItems 需要修改的总记录条数
			 */
			pageItems: function(_pageItems) {
				this.pageItems = _pageItems;
				this.totalPages = Math.ceil(this.total / _pageItems);
				if(this.current > this.totalPages && this.totalPages > 0){
					this.current = this.totalPages;
					this._emit('onPageNoChage', [this.current, this.pageItems]);
				}
				this._preRenderData();
			},

			/*
             * 获取或者修改当前分页组件的页码
			 */
			current: function(current){
				if(!!current && current <= this.totalPages){
					//this.current = current;
					//this._preRenderData();
					this._setCurrentPagin(current, false);
				}else{
					return this.current;
				}
			},

			/*
			 * 跳转的某一页。
			 */
			jumpTo: function(current){
				if(!!current && current <= this.totalPages){
					this._setCurrentPagin(current);
				}else{
					return this.current;
				}
			}
		}
	});

	// Helpers
	// ----------绑定回车事件

	$(document).unbind('keydown.oasPagin');
	// 分页回车事件
	$(document).bind('keydown.oasPagin', function(e){
		if(e.keyCode === 13){
			
			$(':oasPagin').each(function(){
				var val = oasis.DBC2SBC($(this).find('.page').val());
				var page = parseInt(val, 10);
				if(!isNaN(page)){
					$(this).oasPagin('jumpTo', page);
					return false;
				}
			});
		}
	});

})();
;
(function() {

	/**
	 * 自动触发器，实现了部分事件的自动触发。无需绑定事件。
	 * @ author rbai
	 * @ create time 2014.08.07
	 * @ version  1.0.0
	 */

	$.oasUiFactory("oasTrigger", {
		options: {

			// 触发器类型，可以选择toggle|show|hide|toggleClass|addClass|removeClass|function(){}
			trigger: 'toggle',

			// trigger为toggleClass|addClass|removeClass时，切换的内容。
			data: null,

			// 子代选择器，用于过滤触发条件
			selector: null,

			// 触发时间名，绑定在触发器上的事件名称，默认为'click'，即点击元素触发
			events: 'click',

			// 动画类型，当显示或隐藏元素时执行动画的类型.可以选择slide|fade|normal
			animate: 'slide',

			// 动画缓动函数，可以选择linear|swing
			easing: 'linear',


			// 动画执行速度，可以选择normal|slow|fast|或者为数字，表示动画执行的时长。
			animateSpeed: 'normal',

			// 是否阻止事件默认行为，可以选择true|false
			preventDefault: true,

			// 是否取消事件冒泡，可以选择true|false
			cancelBubble: true,

			// 触发后事件执行的主体，为标准选择器字符或dom对象，或jquery对象，如果为null，则表示触发器自身。
			target: null,

			// 触发前执行的事件函数
			before: function() {},

			// 触发后执行的事件函数
			after: function() {}

		},

		_create: function() {
			var options = this.options;

			this._bindEvent();
		},


		_bindEvent: function() {
			var self = this,
				$el = self.$el,
				options = self.options;

			$el.on(options.events, options.selector, function(evt) {

				// 如果options.target为null或者self,则target为$el
				var target = (!options.target) || options.target == 'self' ? $el : $(options.target);

				// 准备事件需要返回的数据
				var data = {
					event: evt,
					element: this,
					target: target,
					options: options
				};

				// 触发before事件
				options.before.call($el, data);

				// 如果trigger是一个function，则直接执行.
				if (oasis.type.isFunction(options.trigger)) {
					options.trigger.call($el, data);
				} else {
					var type = options.trigger,
						params = {
							duration: options.animateSpeed,
							easing: options.easing
						};

					// 判断是显示还是隐藏	
					if (type === 'toggle') {
						type = target.hasClass('hide') ? 'show' : 'hide';
					}

					switch (type) {
						case 'toggle':
							target.toggle();
							break;
						case 'show':

							target.removeClass('hide');
							if (options.animate === 'slide') {
								target.slideDown(params);
							} else if (options.animate === 'fade') {
								target.fadeIn(params);
							} else {
								target.show(params);
							}
							break;
						case 'hide':
							target.addClass('hide');

							if (options.animate === 'slide') {
								target.slideUp(params);
							} else if (options.animate === 'fade') {
								target.fadeOut(params);
							} else {
								target.hide(params);
							}
							break;

						case 'addClass':
						case 'removeClass':
						case 'toggleClass':
							target[type](options.data);
							break;
					}
				}


				// 触发after事件
				options.after.call($el, data);

				// 阻止默认行为和事件冒泡
				if(options.preventDefault) evt.preventDefault();
            	if(options.cancelBubble) evt.stopPropagation();
			});
		}
	});


	$(function(){
        $('[data-oastrigger-toggle="autoTrigger"]').oasTrigger();
        $('[data-oastrigger-toggle="toggle"]').oasTrigger();
        $('[data-oastrigger-toggle="show"]').oasTrigger({trigger: 'show'});
        $('[data-oastrigger-toggle="hide"]').oasTrigger({trigger: 'hide'});
        $('[data-oastrigger-toggle="addClass"]').oasTrigger({trigger: 'addClass'});
        $('[data-oastrigger-toggle="removeClass"]').oasTrigger({trigger: 'removeClass'});
        $('[data-oastrigger-toggle="toggleClass"]').oasTrigger({trigger: 'toggleClass'});
    });

})();
/**
 * 气泡组件
 * @ author jyye
 * @ create time 2014.08.01
 * @ version  1.0.0
 */
(function() {
	// 组件名和文件名请统一
	$.oasUiFactory("oasBubble", {
		options: {
			/**
             * 气泡触发元素,
             * @type string
             * @default null
             * @example
             * $("body").oasBubble({
             *     target:".oasicon"
             * })
             */
			target: '',
			/**
             * 气泡触发方式,
             * @type string
             * @default 'click,'hover'
             * @example
             * $("body").oasBubble({
             *     trigger:"hover"
             * })
             */
			trigger: 'click',
			/**
             * hover延时,
             * @type int
             * @default 150
             * @example
             * $("body").oasBubble({
             *     delay:350
             * })
             */
			delay: 150,
			/**
             * 气泡标题,
             * @type string
             * @default null
             * @example
             * $("body").oasBubble({
             *     title:"woshibiaoti"
             * })
             */
			title: null,
            /**
             * 是否显示close按钮,
             * @type boolean
             * @default false 
             * @example
             * $("body").oasBubble({
             *     close:true
             * })
             */
			close: true,
			/**
             * 气泡宽度,
             * @type int
             * @default 'auto'
             * @example
             * $("body").oasBubble({
             *     width:300
             * })
             */
			width: 'auto',
			/**
             * 气泡高度,
             * @type int
             * @default 'auto'
             * @example
             * $("body").oasBubble({
             *     height:300
             * })
             */
			height: 'auto',
			
			/**
             * 气泡位置偏移,
             * @type array
             * @default [0,0]
             * @example
             * $("body").oasBubble({
             *    offset: [0, 0]
             * })
             */
			offset: [0, 0],
			/**
             * 气泡文字提示信息,
             * @type string
             * @default null
             * @example
             * $("body").oasBubble({
             *    message: "气泡文字提示信息"
             * })
             */
			message:null,
			/**
             * 气泡内传入IFRAME页面,
             * @type string
             * @default null
             * @example
             * $("body").oasBubble({
             *    src: "../index.htm;"
             * })
             */
			src:null,
			/**
             * 气泡内是否有透明遮罩,
             * @type boolean
             * @default false
             * @example
             * $("body").oasBubble({
             *    hasOpacity: false,
             * })
             */
			hasOpacity: false,
			/**
             * 气泡展示是否需要动画效果,
             * @type boolean
             * @default false
             * @example
             * $("body").oasBubble({
             *    isNeedAnimation: false,
             * })
             */
			isNeedAnimation:false,
			/**
             * 气泡内loading样式,
             * @type boolean
             * @default false
             * @example
             * $("body").oasBubble({
             *    loading: false,
             * })
             */
			loading:false,
			/**
             * 气泡append到哪个dom节点内，默认为body,
             * @type boolean
             * @default false
             * @example
             * $("body").oasBubble({
             *    context: "#div",
             * })
             */
			context:null,
			/**
             * 气泡是否默认展示,
             * @type boolean
             * @default false
             * @example
             * $("body").oasBubble({
             *    autoOpen: false,
             * })
             */
			autoOpen:false,
			//打开回调函数
			onOpen: function($el) {
				
			},
			//关闭回调函数
			onClose: function($el) {
				
			},
			beforeOpen : function($el){
			}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl,
				options = this.options;

				tpl =   '<div class="oas-bubble">'+
							'<div class="bubble-arrow"></div>' +
							'{{if title != null}}' +
							'<div class="bubble-hd"><span class="oas-title">{{title}}</span>{{if close}}<a href="javascript:;" class="oasicon oasicon-close close-bubble"></a>{{/if}}</div>' +
							'{{/if}}' +
						'</div>'
				 

			return template.compile(tpl);
		},

		// 事件
		events: {


		},

		_create: function() {
			var options = this.options;
			this._preRenderData();
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				title = this.title || (this.title = options.title),
                close = this.close = options.close === true ? true : false;

			this._renderData = _renderData = {
				title: title,
                close: close
			};

			this._createDom();

		},
		/**气泡宽高属性**/
		_bubbleAttr: function() {
			var options = this.options,
				bd = this.$el,
				loading = bd.parents(".oas-bubble").find(".bubble-loading");
			bd.width(options.width);
			bd.height(options.height);
			if(options.src){
				bd.find("iframe").width(options.width);
				bd.find("iframe").height(options.height);
			};
			if (options.loading) {
				loading.width(options.width);
				loading.height(options.height);
			};
			this._showAndHide();
		},
		/**气泡组件显隐**/
		_showAndHide: function() {
			var options = this.options,
				thisEl = this.$el.parent(".oas-bubble"),
				element = this.$el,
				self = this,
				elem = options.target,
				trig = options.trigger,
				delay = options.delay,
				x = options.offset[0], //x轴偏移量
				y = options.offset[1], //y轴偏移量
				thisHide;
			if(options.title != null && options.close === true){
				thisHide = false;
			}else{
				thisHide = true;
			}
			thisEl.oasOverlay({
				target: $(elem),
				triggerType: trig,
				delay: delay,
				blurHide:thisHide,
				hasOpacity: options.hasOpacity,
				isNeedAnimation:options.isNeedAnimation,
				align: {
					// 基准定位元素，默认为当前的可视区域
					baseElement: $(elem),
					// 基准定位元素的定位点，默认为左上角
					baseXY: [0, 21]
				},

				beforeOpen: function($el) {
					self._emit('beforeOpen',[element]);
					var baseXY,
						left = 0,
						top = 0,
						thisHeight =  thisEl.outerHeight() , //气泡高度
						thisWidth =  thisEl.outerWidth(); //气泡宽度
					//判断是否有context属性，如果有，则气泡节点传入$(options.context)内，如果无，则传入$("body")内
					if(options.context){
						var thisTop = $($el.target).position().top, //top值
							thisLeft = $($el.target).position().left,
							win = {
								height: $(options.context).height(), //$(options.context)节点的宽高
								width: $(options.context).width(),
								scrollTop: $(options.context).scrollTop(), //$(options.context)节点的滚动条宽高
								scrollLeft: $(options.context).scrollLeft()
							}; //left值
						switch ($($el.target).attr('data-placement')) {
							case 'left':
								left = -thisWidth - 10 + x;
								top = -15 + y + win.scrollTop;
								thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("right-arrow");
								baseXY = [left, top];
								if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = -thisWidth - 10 + x;
										top = -15 + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("right-arrow");
									}else{
										left = -thisWidth - 10 + x;
										top = -thisHeight + y + 35 + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").addClass("opposite");
									}
								}
								break;
							case 'right':
								left = +$($el.target).outerWidth() + 10 + x;
								top = -13 + y + win.scrollTop;
								thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("left-arrow");
								baseXY = [left, top];
								if (win.height - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = +$($el.target).outerWidth() + 10 + x;
										top = -13 + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("left-arrow");
									}else{
										left = $($el.target).outerWidth() + 10 + x;
										top = -thisHeight + y + 35 + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").addClass("opposite");
									}
									
								}
								break;
							case 'top':
								left = +x;
								top = -thisHeight - 5 + y + win.scrollTop;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow opposite").addClass("bottom-arrow");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = -thisHeight - 5 + y + win.scrollTop;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								break;
							case 'bottom':
								left = +x;
								top = 25 + y + win.scrollTop;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = 25 + y + win.scrollTop;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								break;
							default:
								left = +x ;
								top = 25 + y  + win.scrollTop;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = 25 + y + win.scrollTop;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								if (win.height - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = + x;
										top = 25 + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("bottom-arrow ");
									}else{
										left = + x;
										top = -thisHeight + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow opposite").addClass("bottom-arrow");
									}
								}
								if(win.height - thisTop - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - thisWidth - x < 0){
									if(thisTop < thisHeight){
										left = -thisWidth + 40 + x;
										top = 25 + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("bottom-arrow ");
									}else{
										left = -thisWidth + 40 + x;
										top = -thisHeight + y + win.scrollTop;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow").addClass("bottom-arrow opposite");
									}
									
								}
								
						}
						
						this.oasOverlay('align', {
							baseXY: baseXY
						});
					}else{
						var thisTop = Position.location($el.target).top, //top值
							thisLeft = Position.location($el.target).left,
							win = {
								height: $(window).height(), //窗口的宽高
								width: $(window).width(),
								scrollTop: $(window).scrollTop(),//窗口的滚动条宽高
								scrollLeft: $(window).scrollLeft()
							}; //left值
						switch ($($el.target).attr('data-placement')) {
							case 'left':
								left = -thisWidth - 10 + x;
								top = -15 + y;
								thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("right-arrow");
								baseXY = [left, top];
								if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = -thisWidth - 10 + x;
										top = -15 + y;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("right-arrow");
									}else{
										left = -thisWidth - 10 + x;
										top = -thisHeight + y + 35;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").addClass("opposite");
									}
								}
								break;
							case 'right':
								left = +$($el.target).outerWidth() + 10 + x;
								top = -13 + y;
								thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("left-arrow");
								baseXY = [left, top];
								if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = +$($el.target).outerWidth() + 10 + x;
										top = -13 + y;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow bottom-arrow opposite").addClass("left-arrow");
									}else{
										left = $($el.target).outerWidth() + 10 + x;
										top = -thisHeight + y + 35;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").addClass("opposite");
									}
									
								}
								break;
							case 'top':
								left = +x;
								top = -thisHeight - 5 + y;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow opposite").addClass("bottom-arrow");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = -thisHeight - 5 + y;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								break;
							case 'bottom':
								left = +x;
								top = 25 + y;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = 25 + y;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								break;
							default:
								left = +x;
								top = 25 + y;
								thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow bottom-arrow opposite");
								baseXY = [left, top];
								if (win.width + win.scrollLeft - thisLeft - thisWidth - x < 0) {
									left = -thisWidth + 40 + x;
									top = 25 + y;
									baseXY = [left, top];
									thisEl.find(".bubble-arrow").addClass("opposite");
								}
								if (win.height + win.scrollTop - thisTop - thisHeight - y < 0) {
									if(thisTop < thisHeight){
										left = + x;
										top = 25 + y;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("bottom-arrow ");
									}else{
										left = + x;
										top = -thisHeight + y ;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow opposite").addClass("bottom-arrow");
									}
								}
								if(win.height + win.scrollTop - thisTop - thisHeight - y < 0 && win.width + win.scrollLeft - thisLeft - thisWidth - x < 0){
									if(thisTop < thisHeight){
										left = -thisWidth + 40 + x;
										top = 25 + y;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("bottom-arrow ");
									}else{
										left = -thisWidth + 40 + x;
										top = -thisHeight + y ;
										baseXY = [left, top];
										thisEl.find(".bubble-arrow").removeClass("left-arrow right-arrow").addClass("bottom-arrow opposite");
									}
									
								}
								
						}
						this.oasOverlay('align', {
							baseXY: baseXY
						});

					}
					
				},
				onOpen : function(){
					self._iCall('openBubble');
				},
				onClose :function(){
					self._iCall('closeBubble');
				}
			});


		},

		/**创建dom节点**/
		_createDom: function() {
			var $html = $(this.tpl(this._renderData)),
			 	content = this.$el.html(),
			 	self = this,
			 	options = this.options;
			//创建节点
			if (options.context) {
				$(options.context).append($html);
				$(options.context).css('position','relative')
			}else{
				$("body").append($html);
			};
			this.$el.addClass("bubble-bd");
			//判断是否有src，如果有src，则只放入iframe
			if(options.src){
				this.$el.attr('style','overflow:hidden');
				this.$el.empty().append("<iframe width='100%' height='100%' frameborder='0'></iframe>");
				this.$frame = this.$el.find('iframe');
				this.$frame.attr("src",options.src);
			}
			//判断是否有message，如果有message，则只放入message
			if(options.message){
				this.$el.empty().append(options.message);
			}
			$html.append(this.$el);
			//判断是否有close按钮
            if (this.close) {
                $html.find(".close-bubble").click(function(){
                    self._iCall("closeBubble");
                });
            }
            //判断是有有loading，如果有loading，则加入此loading样式
			if(options.loading == true){
				var loading = '<div class="bubble-loading">'+
								  '<div class="oas-local-loading clearfx">'+
								  	'<span class="loading"></span>'+
								  	'<span class="loading-text">数据读取中....</span>'+
								  '</div>'+
							  '</div>';
				if($html.find(".bubble-loading").size()==0){
					$html.append(loading);
				}
				this.$el.hide();
			}else{
				$html.find(".bubble-loading").remove();
				this.$el.show();
			}
			this._bubbleAttr();
			//判断是否默认展开
			if(options.autoOpen){
				if(options.trigger == 'hover'){
					$(document).ready(function(){
						$(document).find(options.target).eq(0).trigger('mouseover');
					});
				}else{
					$(document).ready(function(){
						$(document).find(options.target).eq(0).trigger('click');
					});
				}
			}
		},

		// 组件对外方法集
		invoke: {
			/*
			 * 修改标题
			 * @param _title 修改的标题名称
			 */
			title: function(_title) {
				this.title = _title;
				this.$el.parent(".oas-bubble").find(".oas-title").text(_title);
			},
			//打开气泡
			openBubble: function() {
				var options = this.options,
					$el = this.$el.parent(".oas-bubble");
				$el.show();
				if (options.src) {
					this._emit('onOpen',[this.$el,this.$frame[0].contentWindow]);
				}else{
					this._emit('onOpen',[this.$el]);
				}
			},
			//关闭气泡
			closeBubble: function() {
				var options = this.options,
					$el = this.$el.parent(".oas-bubble");
				$el.hide();
				if (options.src) {
					this._emit('onClose',[this.$el,this.$frame[0].contentWindow]);
				}else{
					this._emit('onClose',[this.$el]);
				}
			},
			/*
			 * @param {int} _width 需要设置的宽度，不传参数根据内容自适应。
			 */
			width: function(_width) {
				//this.$el.width(_width);
				//this.$el.find("iframe").width(_width);
				this.options.width = _width;
				this._bubbleAttr();
			},
			/*
			 * @param {int} _height 需要设置的高度，不传参数根据内容自适应。
			 */
			height: function(_height) {
				//this.$el.height(_height);
				//this.$el.find("iframe").height(_height);
				this.options.height = _height;
				this._bubbleAttr();
			},

			destory: function(){
				var $el = this.$el;
					$el.parent(".oas-bubble").oasOverlay('destroyOverlay');
					$el.parent(".oas-bubble").remove();
					$el.unbind('.oasBubble').removeData('oasBubble');
			},
			src : function(_src){
				var options = this.options;
					options.src = _src;
				this.$el.empty().append("<iframe width='100%' height='100%' frameborder='0'></iframe>");
				this.$el.attr('style','overflow:hidden');
				this.$frame = this.$el.find('iframe');
				this.$frame.attr("src",options.src);
				this._bubbleAttr();
			},
			message : function(_message){
				var options = this.options;
				options.message = _message;
				this.$el.empty().append(options.message);
			}
			,
			loading : function(_loading){
				var options = this.options, $el= this.$el;
					options.loading = _loading;
				if(_loading == true){
					var loading = '<div class="bubble-loading">'+
								  '<div class="oas-local-loading clearfx">'+
								  	'<span class="loading"></span>'+
								  	'<span class="loading-text">数据读取中....</span>'+
								  '</div>'+
							  '</div>';
					if($el.parent(".oas-bubble").find(".bubble-loading").size()==0){
						$el.parent(".oas-bubble").append(loading);
					}
					$el.parent(".oas-bubble").find(".bubble-loading").width(options.width);
					$el.parent(".oas-bubble").find(".bubble-loading").height(options.height);
					$el.hide();
				}else{
					$el.parent(".oas-bubble").find(".bubble-loading").remove();
					$el.show();
				}
				
			}
		}
	});

})();
;
(function() {

	/**
	 * 半遮页组件，提供框架层的半遮页
	 * @ author rbai
	 * @ create time 2014.08.15
	 * @ version  1.0.0
	 */

	$.oasUiFactory("oasShyPage", {
		options: {

			title: null,
			src: null,

			// 是否可以收起展开
			hasToggle: false,

			// 是否需要刷新按钮
			refresh: true,

			/*是否需要可以关闭*/
			closeable: true,

			// 半遮页的宽度
			width: 1000,

			// 打开的时候触发
			onOpen: function() {

			},

			// 当关闭完了触发
			onClose: function() {

			},

			// 半遮页页面load事件
			onLoad: function(){

			},

			// 当关闭中触发。
			onClosing: function() {
				
			}
		},

		_template: function() {
			var options = this.options,
				tpl;
			tpl = '{{if hasToggle === true}}' +
				'<div class="oas-dialog-fload-mask"></div>' +
				'{{/if}}' +
				'<div class="oas-dialog-fload-content">' +
				'<div class="dialog-hd">' +
				'<h4 class="oas-title">{{title}}</h4>' +
				'<span class="oas-dialog-opt">' +
				'{{if hasToggle === true}}' +
				'<a href="javascript:;" class="dialog-toggle oasicon oasicon-downfload" title="最小化"></a>' +
				'<a href="javascript:;" class="dialog-toggle oasicon oasicon-upfload" title="最大化"></a>' +
				'{{/if}}' +
				'{{if refresh === true}}' +
				'<a href="javascript:;" class="dialog-refresh oasicon oasicon-refresh" title="刷新"></a>' +
				'{{/if}}' +
				'{{if closeable === true}}' +
				'<a href="javascript:;" class="dialog-close oasicon oasicon-close" title="关闭"></a>' +
				'{{/if}}' +
				'</span>' +
				'</div>' +
				'<div class="dialog-bd">' +
				'<div class="oas-dialog-fload-frame oas-scroll-webkit"><div class="oas-dialog-loading">页面加载中...</div><iframe src="about:blank" frameborder="0" width="100%" height="100%"></iframe></div>' +
				'</div>' +
				'</div>';

			return template.compile(tpl);
		},

		events: {

			'click .dialog-hd': function() {
				if (this.toggle) {
					this._toggle();
				}
			},
			'click .dialog-close': function(self, evt) {
				this._iCall('close');
				return false;
			},

			'click .dialog-refresh': function(self, evt) {
				this._iCall('refresh');
				return false;
			},

			'click .dialog-toggle': function(self, evt) {
				this._toggle();
				return false;
			},

			'click .oas-dialog-fload-mask': function(self, evt) {
				//this._iCall('close');
				return false;
			},

			'mouseover .dialog-hd': function (self, evt) {
				if (this.toggle && this.$el.hasClass('open')) {
					this.$el.stop().animate({
						top: this.height - 42
					}, 100);
				}
			},

			'mouseout .dialog-hd': function (self, evt) {
				if (this.toggle && this.$el.hasClass('open')) {
					this.$el.stop().animate({
						top: this.height - 32
					}, 100);
				}
			}
			,
			'click .oas-dialog-fload-content': function() {
				$(this.$el.find('iframe')[0].contentWindow.document).find('body').trigger('click');
				return false;
			}
		},

		_create: function() {
			this.$container = this.$el;
			var self = this,
				$el = this.$el = $('<div class="oas-dialog-fload animated onloading"></div>'),
				twin = this.twin = oasis.getTopWindow();

			
			$('body', twin.document).append($el);

			this._preRenderData();

		},

		_resize: function() {
			var self = this,
				$el = self.$el;

			$(window).off('resize.oasShyPage');
			$(window).on('resize.oasShyPage', function() {

				var height = self.height = $(self.twin).height();

				if(self.toggle){
					$el.css({
						top: self.height - 32
					});
				}

				$el.find('.oas-dialog-fload-frame').height(height - 162);

			}).resize();
		},

		// 准备渲染数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				title = options.title,
				src = options.src;

			this._renderData = _renderData = {
				title: title,
				hasToggle: options.hasToggle,
				refresh: options.refresh,
				closeable: options.closeable
			};
			this._createDom();
		},

		_toggle: function() {
			var $el = this.$el,
				self = this;
			
			if (!self.toggle && $el.hasClass('open')) {
				self.toggle = true;
				$el.addClass('shrink');
				$el.stop().animate({
					'top': this.height - 32
				}, 300);
				
			} else {
				self.toggle = false;
				$el.removeClass('shrink');
				$el.stop().animate({
					'top': 100
				}, 300);
				
			}

		},

		_createDom: function() {
			var html = this.tpl(this._renderData);

			this.$el.empty().append(html);

			// 设置属性，宽度等。
			this._setProp();

			// 绑定resize事件
			this._resize();

			// 缓存部分数据
			this._cacheData();
		},

		_cacheData: function (argument) {
			var self = this,
				$el = this.$el;
			// 缓存iframe
			this.$frame = $el.find('iframe');


			// 如果已经创建，则执行替换src
			// @modify by rbai @2015-05-07 修复打开半遮页时加载两次src问题。
			if(this.__isCreated__){
				if($.browser.msie){
					self._iCall('src', [self.options.src]);
				}else{
					setTimeout(function(){
						self._iCall('src', [self.options.src]);
					},260);
				}
			}
			// 缓存半遮页设置数据
			this.customData = {};
		},

		/**设置属性，宽度等**/
		_setProp: function () {
			var $el = this.$el,
				options = this.options,
				width = options.width,
				isPercent = /%/.test(width),
				tempWidth = width,
				marginLeft = -width / 2;

			// 支持百分比
			if(isPercent){
				tempWidth = width,
				marginLeft = (-width.replace(/%/,'') / 2) + '%'
			}
			$el.css({
				width: tempWidth,
				'marginLeft': marginLeft
			});
		},


		close: function(){
			this._iCall('close');
		},

		// 对外方法集
		invoke: {
			close: function() {
				var $el = this.$el;
				// 先触发onClosing的回调，如果回调返回false,则不关闭半遮页，其他情况则关闭，
				// !== 是为了避免方法默认返回undefined出现问题
				if(this._emit('onClosing', [$el, this.$frame[0].contentWindow.window]) !== false){
					Mask.close();
					$el.removeClass('open').removeClass('shrink').css('top', 100);
					this.$container.remove();
					this.toggle = false;
					this._emit('onClose', [$el,this.$frame[0].contentWindow.window]);
				}
			},

			/**
			 * 打开半遮页
			 * @param _src {string}设置半遮页的src
			 * @param _title {string}设置半遮页的标题
			 * @param _refresh {Boolean}指定半遮页是否刷新
			 */
			open: function(_src, _title, _refresh) {
				var $el = this.$el.addClass('onloading'),
					self = this;

				Mask.open();

				// 如果参数是obj。
				if(oasis.type.isObject(_src)){
					var opts = _src;
					(!opts.onOpen) && (opts.onOpen = function(){});
					(!opts.onClose) && (opts.onClose = function(){});
					(!opts.onLoad) && (opts.onLoad = function(){});
					(!opts.onClosing) && (opts.onClosing = function(){});
					$.extend(true, this.options, opts);
				}else{
					_src && (this.options.src = _src);
					_title && (this.options.title = _title);
				}

				this._preRenderData();
				
				$el.css('top', $el.height()).addClass('open').removeClass('shrink').stop().animate({'top': 100}, 300, function(){
					self.toggle = false;
				});

				this._emit('onOpen', [$el,this.$frame[0].contentWindow.window]);

				
			},
			/**
			 * 设置半遮页的里的页面地址
			 * @param _src {srting}半遮页的里的页面地址
			 */
			src: function(_src) {
				var self = this;
				if (_src && this.src != _src) {

					this.options.src = _src;
					this._iCall('refresh');

					// ie
					if(this.$frame[0].attachEvent){
						this.$frame[0].attachEvent('onload', _iframeOnload);
					}else{
						this.$frame[0].onload = _iframeOnload;
					}



					function _iframeOnload(){
						self.$el.removeClass('onloading');
						// 设置oasShyPage到iframe中，确保iframe中可以直接使用oasShyPage来调用某些方法。
						var contentWindow = self.$frame[0].contentWindow;
						if(contentWindow.OasShyPage){
							contentWindow.OasShyPage.close = function(){
								self._iCall('close');
							};
						}else{
							contentWindow['OasShyPage'] = self;
						}
						contentWindow['OasShyPage'].openerWin = window;

						// 设置自定义属性到iframe的window中
						for(var key in self.customData){

							contentWindow[key] = self.customData[key];
						}
						// 触发iframe的onload事件
						self._emit('onLoad', [self.$el, contentWindow]);
						if(contentWindow.init){
							contentWindow.init();
						}
					}
				}
			},

			/*
             * 设置半遮页的标题
			 */
			title: function(_title){
				if(_title) this.$el.find('h4').text(_title);
			},

			/*
             * 刷新半遮页
			 */
			refresh: function() {
				var options = this.options,
					src = options.src;
				src = /\?/.test(src) ? src + '&newTime=newTime' + new Date().getTime() : src + '?newTime=newTime' + new Date().getTime();

				this.$frame.attr('src', src);
			},

			/*
			 * 获取半遮页的数据。
			 * @param key {string}需要获取的数据的key
			 */
			getData: function(key) {
				var value = this.$frame[0].contentWindow[key];

				if (value !== undefined) {
					return value;
				} else {
					throw Error(key + '----不存在于半遮页的window对象中，请确认是否保存或书写正确');
				}
			},

			/*
			 * 设置半遮页的数据。
			 * @param obj {string}需要设置的数据
			 */
			setData: function(obj){

				var contentWindow;
				this.customData = {};
				for(var key in obj){

					if(obj.hasOwnProperty(key)) this.customData[key] = obj[key];
					
				}

				if(this.$frame[0].contentWindow && (contentWindow = this.$frame[0].contentWindow)){
					for(var key in this.customData){

						contentWindow[key] = this.customData[key];
					}
				}
				

				// 原先直接赋值给半遮页里iframe的window对象，由于某些情况设置数据时iframe还未加载完成，导致数据设置不成功。故废弃
				// var contentWindow = this.$frame[0].contentWindow.window;
				// // 循环赋值
				// for(var key in obj){
				// 	console.log(key)
				// 	contentWindow[key] = obj[key];
				// }

			}
		}
	});

})();
;
(function() {
	/**
	 * 表格组件，提供常用的表格方法。
	 * @ author rbai
	 * @ create time 2014.07.20
	 * @ version  1.1.0
	 * 
	 * update - 2015-02-09 --> rbai
	 * 修改高度计算bug.
	 * 新增行控制器
	 * 优化列宽
	 * 优化数据更改后checkbox的
	 */
	$.oasUiFactory("oasGrid", {
		options: {

			// 表格头部数据
			head: null,

			// 表格体数据。
			body: null,

			// 是否默认渲染表格。
			autoRender: false,

			// 是否有选择框
			checkbox: false,

			// 表格体的高度，默认为auto，根据表格行自适应。
			height: 'auto',

			// 表格的宽度，默认为auto，为表格容器的宽度。
			width: 'auto',

			// 表格的操作数据。
			opts: null,

			headFixed: true,

			// 表格头悬浮位置(距离顶部)
			fixedTop: 0,

			// 表格行操作.
			colOpts: {
				data: null,
				type: 'normal',
				width: 150
			},

			// 表格是否有斑马线
			striped: false,

			// 是否禁止单元格折行。
			// 为true时，表示禁止，单元格无法折行
			// 为false时表示单元个根据内容折行。
			nowrap: true,

			// 表格的首尾列固定，checkbox和尾部操作不计算在内
			// 为数组时[首, 尾]分别为首尾固定的列数
			fixedColumn: false,

			// 表格是否包含通用操作（列筛选和内容过滤）
			settings: {},


			// 渲染每一行数据时，都会将该行的数据带到该对象中每个方法去执行。
			rowController: {
				
				// 返回一个字符串，来给当前的行添加该class
				className: function(data, index){

				},

				// 返回一个字符串，来给当前的行添加行内style
				style: function(data, index){

				},

				// 返回false 或 true，false:取消选中当前行checkbox, true:选中当前行checkbox
				checked: function(data, index){

				},

				// 返回'disabled' 或 'enabled'，disable:当前行checkbox置为enabled, disabled:当前行checkbox置为disabled
				checkboxShow: function(data, index){

				},

				// 返回对象，为当前行的操作数据。v1.1.1 支持。
				colOpts: function(data, index){

				},
				//返回false或者true，为当前行的备注内容
				comment:function(data, index){

				}
			},

			// 定制列相关信息
			customColInfo: {

				// 项目地址
				context_path: null,

				// 用户id
				user_id: null,

				// 模块id
				sys_id: null,

				// 表格id
            	res_id: null,

            	// 默认定制列，由业务决定，不做持久化，如果不传此数据，则默认定制列为所有的。
            	defaultCol: null
			},

			// 表格形式，sample为普通形式，只是单表格。
			// add by rbai @ 2015-3-19
			type: 'normal',


			// 是否允许多个字段同时处于排序状态。
			multiSort: false,

			// 表格的排序事件
			onSort: function(sortName, index, sortType){

			},

			// 点击表格操作事件
			// @param obj 点击操作按钮的对象。包含id,label,icon等。
			onOptsClick: function(obj){

			},

			// 点击表格行操作事件
			onColOptsClick: function(){

			},

			// 搜索表格内容时触发
			onSearch: function(val){

			},


			// onCheckedChange: checkbox选中项改变时触发。

			onCheckedChange: function(){

			},

			// 当表格滚动条滚动时触发。
			onScrolling: function(){

			},

			// 当表格定制完成后触发，返回当前的列定制序列
        	afterCustom: function(){

        	},

        	// 当表格的一行点击时触发,返回当前的行对象
        	onRowClick: function(){

        	}
		},


		_template: function(){
			var tpl = '<div class="oas-grid-head">'+
					'{{if opts!== null || settings.customVO || settings.filter }}'+
						'<div class="oas-grid-head-fix"></div>'+
						'<div class="oas-grid-optBar clearfix">'+
							'{{if opts !== null}}'+
								'<div class="oas-grid-option">'+
									   '{{each opts}}'+
									   		'<a class="oas-btn-icon{{if $value.className}} {{$value.className}}{{/if}} oas-grid-opt-btn{{if $value.disable}} oas-disabled{{/if}}"{{if $value.id}} id="{{$value.id}}"{{/if}}{{if $value.title}} title="{{$value.title}}"{{/if}} href="javascript:;" data-placement="bottom">'+
												'<i class="{{$value.icon}}"></i>'+
												'<span class="btn-text">{{$value.label}}</span>'+
											'</a>'+
									   '{{/each}}'+
								'</div>'+
							'{{/if}}'+
							'{{if settings.customVO || settings.filter}}'+
								'<div class="oas-grid-setting clearfix">'+
									'{{if settings.customVO}}'+
									'<a class="oas-btn grid-customVO-btn" href="javascript:;">'+
										'<span class="btn-text">列定制</span>'+
									'</a>'+
									'{{/if}}'+
									'{{if settings.filter === true}}'+
									'<div class="oas-grid-search">'+
										'<input type="text" id="grid-search-input" placeholder="输入你想查询的关键词" />'+
										'<a href="javascript:;" id="grid-search-btn" class="oas-btn">查询</a>'+
									'</div>'+
									'{{else if typeof settings.filter === "string"}}'+
										'{{#settings.filter}}'+
									'{{/if}}'+
								'</div>'+
							'{{/if}}'+
						'</div>'+
					'{{/if}}'+
					'<div class="oas-grid-thead">'+
					'</div>'+
					'<div class="oas-grid-loading">'+
						'<div class="oas-grid-loading-content">数据加载中...</div>'+
					'</div>'+
					'</div>'+
					'<div class="oas-grid-tbody">'+
					'<div class="oas-grid-mask"></div>'+
					'</div>'+
					'<div class="oas-grid-scroll-h oas-scroll-webkit"><div></div></div>';

			var headTpl = '{{if checkbox === true || headFront}}'+
						'<div class="grid-front-fixed">'+
							'<table>'+
								'<thead>'+
									'<tr>'+
										'{{if checkbox === true }}'+
										'<th class="oas-grid-checkbox">'+
											'<div class="oas-grid-th"><input type="checkbox" /></div>'+
										'</th>'+
										'{{/if}}'+
										'{{each headFront}}'+
										'<th{{if $value.title}} title="{{$value.title}}"{{/if}}{{if $value.minwidth}} class="oas-grid-minwidth" minwidth="{{$value.width}}"{{/if}}{{if $value.name}} name="{{$value.name}}"{{/if}}>'+
											'<div class="oas-grid-th" style="width:{{$value.width - 10}}px;">{{#$value.label}}{{if $value.sort}}<a class="sort-icon oasicon oasicon-{{$value.sort}}"></a>{{/if}}</div>'+
										'</th>'+
										'{{/each}}'+
									'</tr>'+
								'</thead>'+
							'</table>'+
						'</div>'+
						'{{/if}}'+
						'<div class="grid-main">'+
							'<table>'+
								'<thead>'+
									'<tr>'+
										'{{each headCenter}}'+
											'<th{{if $value.title}} title="{{$value.title}}"{{/if}}{{if $value.minwidth}} class="oas-grid-minwidth" minwidth="{{$value.width}}"{{/if}}{{if $value.name}} name="{{$value.name}}"{{/if}}>'+
												'<div class="oas-grid-th" style="width:{{headCenter[$index].width - 10}}px;">{{#$value.label}}{{if $value.sort}}<a class="sort-icon oasicon oasicon-{{$value.sort}}"></a>{{/if}}</div>'+
											'</th>'+
										'{{/each}}'+
									'</tr>'+
								'</thead>'+
							'</table>'+
						'</div>'+
						'{{if colOpts.data || headFinale}}'+
						'<div class="grid-finale-fixed">'+
							'<table>'+
								'<thead>'+
									'<tr>'+
										'{{each headFinale}}'+
										'<th{{if $value.title}} title="{{$value.title}}"{{/if}}{{if $value.minwidth}} class="oas-grid-minwidth" minwidth="{{$value.width}}"{{/if}}{{if $value.name}} name="{{$value.name}}"{{/if}}>'+
											'<div class="oas-grid-th" style="width:{{$value.width - 10}}px;">{{#$value.label}}{{if $value.sort}}<a class="sort-icon oasicon oasicon-{{$value.sort}}"></a>{{/if}}</div>'+
										'</th>'+
										'{{/each}}'+
										'{{if colOpts.data }}'+
										'<th class="oas-grid-operator oas-grid-operator-{{colOpts.type}}">'+
											'<div class="oas-grid-th">操作</div>'+
										'</th>'+
										'{{/if}}'+
									'</tr>'+
								'</thead>'+
							'</table>'+
						'</div>'+
						'{{/if}}';

			var bodyTpl = '{{if checkbox === true || headFront}}'+
							'<div class="grid-front-fixed">'+
								'<table>'+
									'<tbody>'+
										'{{each bodyFront}}'+
										'<tr class="{{if tempBody && $helpers.oasgridChecked(tempBody[$index], $index)}}oas-active {{/if}}{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}" style="{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}">'+
											'{{if checkbox === true}}'+
											'<td class="oas-grid-checkbox">'+
												'<div class="oas-grid-td"><input type="checkbox"{{if tempBody && $helpers.oasgridCheckboxShow(tempBody[$index], $index) === false}}disabled="disabled"{{/if}} {{if tempBody && $helpers.oasgridChecked(tempBody[$index], $index)}}checked="checked"{{/if}} {{if bodyCenter[$index] && (typeof bodyCenter[$index].id !== "undefined")}}row-id="{{bodyCenter[$index].id}}"{{/if}} /></div>'+
											'</td>'+
											'{{/if}}'+
											'{{each $value}}'+
											'<td{{if headFront[$index].minwidth}} class="oas-grid-minwidth"{{/if}} {{if headFront[$index].tbodyTitle}} title="{{#$value}}"{{/if}}>'+
												'<div class="oas-grid-td" style="width:{{headFront[$index].width - 9}}px;">{{#$value}}</div>'+
											'</td>'+
											'{{/each}}'+
										'</tr>'+
										'{{if tempBody && $helpers.oasgridComment(tempBody[$index], $index)}}'+
											'<tr class="oas-grid-comment">'+
												'<td colspan="{{if checkbox === true}}{{$value.length+1}}{{else}}{{$value.length}}{{/if}}">'+
													'<div>&nbsp;</div>' +
												'</td>'+
											'</tr>'+
										'{{/if}}'+
										'{{/each}}'+
									'</tbody>'+
								'</table>'+
							'</div>'+
							'{{/if}}'+
							'<div class="grid-main">'+
								'<table class="oas-grid-table">'+
									'<tbody>'+
										'{{each bodyCenter}}'+
										'<tr class="{{if tempBody && $helpers.oasgridChecked(tempBody[$index], $index)}}oas-active {{/if}}{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}" style="{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}">'+
											'{{each $value}}'+
											'<td{{if headCenter[$index].minwidth}} class="oas-grid-minwidth"{{/if}} {{if headCenter[$index].tbodyTitle}} title="{{#$value}}"{{/if}}>'+
												'<div class="oas-grid-td" style="width:{{headCenter[$index].width - 9}}px;">{{#$value}}</div>'+
											'</td>'+
											'{{/each}}'+
										'</tr>'+
										'{{if tempBody && $helpers.oasgridComment(tempBody[$index], $index)}}'+
											'<tr class="oas-grid-comment">'+
												'<td colspan="{{$value.length}}">'+
													'<div>{{#tempBody[$index].comment}}</div>' +
												'</td>'+
											'</tr>'+
										'{{/if}}'+
										'{{/each}}'+
									'</tbody>'+
								'</table>'+
							'</div>'+
							'{{if colOpts.data || bodyFinale}}'+
							'<div class="grid-finale-fixed">'+
								'<table>'+
									'<tbody>'+
										'{{each bodyFinale}}'+
										'<tr class="{{if tempBody && $helpers.oasgridChecked(tempBody[$index], $index)}}oas-active {{/if}}{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}" style="{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}">'+
											'{{each $value}}'+
											'<td{{if headFinale[$index].minwidth}} class="oas-grid-minwidth"{{/if}} {{if headFinale[$index].tbodyTitle}} title="{{#$value}}"{{/if}}>'+
												'<div class="oas-grid-td" style="width:{{headFinale[$index].width - 9}}px;">{{#$value}}</div>'+
											'</td>'+
											'{{/each}}'+
											'{{if  colOpts.data}}'+
											'<td class="oas-grid-operator oas-grid-operator-{{colOpts.type}}">'+
												'<div class="oas-grid-td clearfix">'+
													'{{if colOpts.type === "panel"}}'+
													'<a href="javascript:;" class="oas-btn-icon">'+
														'<i class="oasicon oasicon-opt-row"></i>'+
														'<span class="btn-text">操作</span>'+
													'</a>'+
													'{{else}}'+
													'{{each colOpts.data[$index]}}'+
													'<a href="javascript:;" class="oas-grid-colopt{{if $value.disable}} oas-text-disable{{/if}}" {{if bodyCenter[$index] && (typeof bodyCenter[$index].id !== "undefined")}}row-id="{{bodyCenter[$index].id}}"{{/if}} name="{{$value.name}}">{{$value.label}}</a>'+
													'{{/each}}'+
													'&nbsp;{{/if}}'+
												'</div>'+
											'</td>'+
											'{{/if}}'+
										'</tr>'+
										'{{if tempBody && $helpers.oasgridComment(tempBody[$index], $index)}}'+
											'<tr class="oas-grid-comment">'+
												'<td colspan="{{if colOpts.data}}{{$value.length+1}}{{else}}{{$value.length}}{{/if}}">'+
													'<div>&nbsp;</div>' +
												'</td>'+
											'</tr>'+
										'{{/if}}'+
										'{{/each}}'+
									'</tbody>'+
								'</table>'+
							'</div>'+
						'{{/if}}';

			var simpleGridTpl = '<div class="oas-grid">'+
									'<div class="oas-grid-panel">'+
										'<table class="oas-grid-table" style="width:100%;">'+
											'<thead>'+
												'<tr>'+
													'{{each headCenter}}'+
														'<th{{if $value.title}} title="{{$value.title}}"{{/if}}{{if $value.name}} name="{{$value.name}}"{{/if}} style="min-width:{{$value.width}}px;">'+
															'<div class="oas-grid-th">{{#$value.label}}{{if $value.sort}}<a class="sort-icon oasicon oasicon-{{$value.sort}}"></a>{{/if}}</div>'+
														'</th>'+
													'{{/each}}'+
												'</tr>'+
											'</thead>'+

											'<tbody>'+
												'{{each bodyCenter}}'+
												'<tr class="{{if tempBody && $helpers.oasgridChecked(tempBody[$index], $index)}}oas-active {{/if}}{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}" style="{{tempBody && $helpers.oasgridClassName(tempBody[$index], $index)}}">'+
													'{{each $value}}'+
													'<td style="width:{{headCenter[$index].width - 9}}px;" {{if headCenter[$index].tbodyTitle}} title="{{#$value}}"{{/if}}>'+
														'<div class="oas-grid-td">{{#$value}}</div>'+
													'</td>'+
													'{{/each}}'+
												'</tr>'+
												'{{/each}}'+
											'</tbody>'+
										'</table>'+
									'</div>'+
								'</div>';

			this.headTpl = template.compile(headTpl);

			this.bodyTpl = template.compile(bodyTpl);


			// add by rbai @ 2015-3-19
			if(this.options.type === 'simple'){
				this.simpleBodyTpl = template.compile(simpleGridTpl);
			}

			return template.compile(tpl);
		},

		// 事件
		events: {

			'mousedown #grid-search-btn': function(self, evt){
				var val = $(self).prev().val();
				this._emit('onSearch', [val]);
			},

			'focus #grid-search-input': function(self, evt){
				$(self).parent().addClass('focus');
			},

			'blur #grid-search-input': function(self, evt){
				$(self).parent().removeClass('focus');
			},
			// 点击checkbox事件
			'click .oas-grid-checkbox': function(self, evt){
				var $el = this.$el, checkedArray = this.checkedArray = [];
				// 点击的不是checkbox则跳出
				if(!($(evt.target).is('input[type=checkbox]'))) return;

				var $input = $(evt.target),
					isChecked = $input.prop('checked'),
					index = $input.closest('tr').index();

				// 点击的非前面固定的checkbox，则跳出
				if(!!!$(self).closest('.grid-front-fixed').size()) return;

				if(!!$(self).closest('.oas-grid-thead').size()){
					// 点击头部的checkbox
					var tempAll = $el.find('.oas-grid-tbody').find('.grid-front-fixed').find('.oas-grid-checkbox').find('input[type=checkbox]');
					isChecked ? tempAll.not("[disabled=disabled]").prop('checked', true) : tempAll.not("[disabled=disabled]").prop('checked', false);
					var indexs  = [];
					tempAll.filter("[disabled=disabled]").each(function(){
						indexs.push($(this).closest('tr').index());
					});
					if(isChecked){
						$el.find('.oas-grid-tbody').find('table').find('tr').not(function(){
							var result = false;
							for (var i = 0; i < indexs.length; i++) {
								if($(this).index() == indexs[i]){
									result = true;
									break;
								}
							}
							return result;
						}).addClass('oas-active');
					}else{
						$el.find('.oas-grid-tbody').find('table').find('tr').not(function(){
							var result = false;
							for (var i = 0; i < indexs.length; i++) {
								if($(this).index() == indexs[i]){
									result = true;
									break;
								}
							}
							return result;
						}).removeClass('oas-active');
					}
				}else{
					// 点击表格体的checkbox
					var tempAll = $el.find('.oas-grid-tbody').find('.grid-front-fixed').find('.oas-grid-checkbox').find('input[type=checkbox]'),
						len = tempAll.size(),
						headCheckbox = $el.find('.oas-grid-thead').find('.grid-front-fixed').find('.oas-grid-checkbox').find('input[type=checkbox]');
					if(isChecked){
						$el.find('.oas-grid-tbody').find('table').each(function(){
							$(this).find('tr').eq(index).addClass('oas-active');
						});
					}else{
						$el.find('.oas-grid-tbody').find('table').each(function(){
							$(this).find('tr').eq(index).removeClass('oas-active');
						});
					}
					// 选中的等于所有条数时，选中all,否则取消选中all
					tempAll.filter(':checked').size() === len ? headCheckbox.prop('checked', true) : headCheckbox.prop('checked', false);
				}
				// 更新当前选中条目的index
				$.each(tempAll, function(i, element){
					if($(this).prop('checked')){
						checkedArray.push(i);
					}
				});
				this._emit('onCheckedChange', [this._iCall("getCheckedIndex")]);
			},

			// 表格操作按钮点击事件
			'click .oas-grid-opt-btn': function(self, evt){
				if(!$(self).hasClass('oas-disabled')){
					var index = $(self).index();
					this._emit('onOptsClick', [self.id, this.checkedArray, this.options.opts[index]]);
				}
			},

			// 排序操作
			'click .sort-icon': function(self, evt){
				var $th = $(self).closest('th'),
					name = $th.attr('name'),
					type = self.className.match(/sort|asc|desc/g)[1],
					newType;

				// 是否允许多字段排序

				if(!this.options.multiSort){
					$th.siblings('th').removeClass('is-sorted').find('.sort-icon').removeClass('oasicon-desc oasicon-asc').addClass('oasicon-sort');
					$th.closest('div').siblings('div').find('th').removeClass('is-sorted').find('.sort-icon').removeClass('oasicon-desc oasicon-asc').addClass('oasicon-sort');
				}

				if(type === 'sort'){
					$(self).removeClass('oasicon-sort').addClass('oasicon-desc');
					newType = 'desc';
				}else if(type === 'desc'){
					$(self).removeClass('oasicon-desc').addClass('oasicon-asc');
					newType = 'asc';
				}else{
					$(self).removeClass('oasicon-asc').addClass('oasicon-desc');
					newType = 'desc';
				}

				$th.addClass('is-sorted');
				this._emit('onSort', [name, newType]);
				evt.preventDefault();
			},

			// 表格定制
			'click .grid-custom-btn': function(){

					

				this._gridCustom();


			},

			// 行操作事件
			'click .oas-grid-operator-normal': function(self, evt){

				var $target = $(evt.target),
					index = $(self).parent().index(),
					name = $target.attr('name');

				if(!($target.is('a'))) return;

				if(!$target.hasClass('oas-text-disable')){
					this._emit('onColOptsClick', [name, index, this.tempBody? this.tempBody[index] : this.options.body[index]]);
				}
				
			},

			// 行hover事件
			'mouseover tr': function(self, evt){
				// 是表格体内的才执行次事件
				var $tbody = this.$el.find('.oas-grid-tbody');
				if(!!$(self).closest('.oas-grid-tbody').size()){
					$tbody.find('tr').removeClass('oas-grid-hover');
					var index = $(self).index();
					$tbody.find('table').each(function(i, element){
						$(this).find('tr').eq(index).addClass('oas-grid-hover');
					});
				}
			},

			// 行hover操作
			'mouseout tr': function(self, evt){
				// 是表格体内的才执行次事件
				var $tbody = this.$el.find('.oas-grid-tbody');
				if($(self).closest('.oas-grid-tbody')){
					$tbody.find('tr').removeClass('oas-grid-hover');
				}
			}
		},

		_create: function() {
			var options = this.options;

			this._setGridProp();

			/*if(options.settings){
				this._initCustomCol();
			}*/

			if(options.autoRender){
				this.render();
			}
		},

		// 初始化配置项helps。
		_initOptionsHelps: function(options){
			var rowController = options.rowController;
			template.helper('oasgridClassName', rowController.className);
			template.helper('oasgridStyle', rowController.style);
			template.helper('oasgridChecked', rowController.checked);
			template.helper('oasgridCheckboxShow', rowController.checkboxShow);
			template.helper('oasgridColOpts', rowController.colOpts);
			template.helper('oasgridComment', rowController.comment);
		},

		//表格列定制方法
		_gridCustom:function(){
			var self = this,
			options = this.options,
			customColInfo = options.customColInfo,
			customCol = this.currentCustom,
			defaultCol = customColInfo.defaultCol;
			OasShyPage.open({
				id: 'oas-grid-customdialog',
				title: '表格定制',
				hasToggle: true,
				src: oasis.oasisDir + '../oasis_tpl/grid_custom.html',
				onClose: function ($el, win) {
					if(win.currentCustom && win.currentCustom.length > 0 && win.issave){
						self._iCall('setCustomCol', [customColInfo.defaultCol, win.currentCustom]);
						oasGrid.saveCustomCol({
							context_path: customColInfo.context_path,
	                    	user_id: customColInfo.user_id,
	                    	sys_id: customColInfo.sys_id,
	                    	res_id: customColInfo.res_id,
	                    	colsJsonStr: JSON.stringify(win.currentCustom)
						});
						self._emit('afterCustom', [win.currentCustom]);
					}
				},
				onLoad: function($el, win){
					$('#oas-grid-customdialog').oasShyPage('setData', {
					 	customCol: customCol,
					 	defaultCol: defaultCol,
					 	head: self._splitFromCustom(options.head)
				 	});
				}
			});
		},

		// 渲染表格
		render: function(){
			
			this._createTable();

			// 设置表格已经渲染完成
			this.renderdy = true;
		},

		_createTable: function(){

			var self = this,
				$el = this.$el,
				options = this.options;

			// 重新计算列宽
			this._adjustColumnWidth();

			// 设置自定义列相关信息
			if(options.settings.custom){
				this._iCall('setCustomCol', [options.customColInfo.defaultCol]);
			}
			

			this._iCall('colOpts', [options.colOpts.data]);

			// 渲染表格
			this._renderGrid();
			this._renderHead();
			options.body && this._renderBody();

			//渲染完毕后更新this.checkArray
			this._updateCheckArray();
			// 适应表格宽高
			this._adjustGrid();

			this._checkNoData();
			// 绑定额外事件
			this._bindEvent();
		},
		//更新this.checkArray
		_updateCheckArray:function(){
			var $el = this.$el, checkedArray = this.checkedArray = [];
			var tempAll = $el.find('.oas-grid-tbody').find('.grid-front-fixed').find('.oas-grid-checkbox').find('input[type=checkbox]'),
			len = tempAll.size(),
			headCheckbox = $el.find('.oas-grid-thead').find('.grid-front-fixed').find('.oas-grid-checkbox').find('input[type=checkbox]');	
			// 选中的等于所有条数时，选中all,否则取消选中all
			(tempAll.filter(':checked').size() === len && len !== 0) ? headCheckbox.prop('checked', true) : headCheckbox.prop('checked', false);
			// 更新当前选中条目的index
			$.each(tempAll, function(i, element){
				if($(this).prop('checked')){
					checkedArray.push(i);
				}
			});
		},
		// 绑定额外的事件。
		_bindEvent: function(){
			var self = this,
				$el = this.$el,
				timeout,
				winWidth = $(window).width(),
				winHeight = $(window).height();

			//$(window).off('scroll.oas-grid');
			$(window).on('scroll.oas-grid', function(){
				self._adjustScroll();
			});

			$el.find('.oas-grid-scroll-h').off('scroll.oas-grid');
			// 滚动条事件
			$el.find('.oas-grid-scroll-h').on('scroll.oas-grid',function(){
				$el.find('.grid-main table').css('margin-left', 0 - $(this).scrollLeft());
				self._emit('onScrolling');
			});
			
			// 计算滚动条
			setTimeout(function(){
				self._adjustScroll();
			},400);

			
			$(window).resize(function() {
				timeout && clearTimeout(timeout);
				timeout = setTimeout(function() {
					var winNewWidth = $(window).width();
					var winNewHeight = $(window).height();

					if (winWidth !== winNewWidth || winHeight !== winNewHeight) {
						var width = $el.actual('width');
						$el.find('.oas-grid-head').width(width);
						$el.find('.oas-grid-scroll-h').width(width - self.frontWidth - self.finaleWidth);
						self._adjustGrid();
						winWidth = winNewWidth;
						winHeight = winNewHeight;
					}
				}, 80);
			});

			/*$el.find('.oas-grid-tbody')[0].addEventListener('DOMSubtreeModified', function(){
				alert(22);
			}, false);*/

/*			$el.find('.oas-grid-table').on('DOMSubtreeModified', function(){
				alert(22);
				self._iCall('resize');
			})*/

			$el.on('click.oas-grid', '.oas-grid-tbody tr' ,function(){
				var index = $(this).index();
				var $tbody = $el.find('.oas-grid-tbody'),
					arr = [];
				
				$tbody.find('table').each(function(i, element){
					arr.push($(this).find('tr').eq(index));
				});
				self._emit('onRowClick', [arr, index]);
			});
		},

		// 过滤显示的列头部。
		_filterCustomHeadCol: function(){
			var $el = this.$el,
				options = this.options,
				customHead = this.customHead = [],
				head = options.head,
				currentCustom = this.currentCustom,
				sequence = this.sequence = [];

			// 如果没有定制，则返回所有的
			if(!currentCustom){
				$.extend(true, customHead, head);
				return;
			}

			for(var j = 0, jLen = currentCustom.length; j < jLen; j++){

				for(var i = 0, len = head.length; i < len; i++){
				
					if(head[i].name === currentCustom[j].name){
						customHead.push(head[i]);
						sequence.push(i);
					}
				}
			}

		},

		// 过滤显示的列体部。
		_filterCustomBodyCol: function(){
			var $el = this.$el,
				options = this.options,
				customBody = this.customBody = [],
				body = options.body,
				sequence = this.sequence;

			// 如果没有定制，则返回所有的
			if(!sequence || !this.currentCustom){
				$.extend(true, customBody, body);
				return;
			}

			for(var i = 0, len = body.length; i < len; i++){
				customBody[i] = [];
				for(var j = 0, jLen = sequence.length; j < jLen; j++){
					customBody[i].push(body[i][sequence[j]]);
				}
			}
		},

		// 适应滚动条
		_adjustScroll: function(){
			var $el = this.$el,
				tableTop = this.$el.offset().top,
				winHeight = $(window).height(),
				docTop = $(document).scrollTop();

				// 表格操作和头部悬浮
				// if(docTop < tableTop + $el.height()-30){
				// 	if(docTop > tableTop){
				// 		$el.addClass('has-head-fixed');
				// 		$el.find('.oas-grid-head').width($el.width());
				// 		this.$kakou = $('<div></div>').css({
		  //           		width: $el.find('.oas-grid-head').outerWidth(),
		  //           		height: $el.find('.oas-grid-head').outerHeight()
		  //           	});
				// 	}else{
				// 		this.$kakou && this.$kakou.remove();
				// 		$el.removeClass('has-head-fixed');
				// 	}
				// }else{
				// 	this.$kakou && this.$kakou.remove();
				// 	$el.removeClass('has-head-fixed');
				// }
				
				// 滚动条悬浮
				if((docTop+winHeight > tableTop) &&(docTop < tableTop + $el.height() - winHeight)){
					$el.addClass('has-scrollh-fixed');
				}else{
					$el.removeClass('has-scrollh-fixed');
				}

		},

		// 初始化表格定制
		_initCustomCol: function(){
			var self = this,
				options = this.options,
				customColInfo = options.customColInfo;

			if(!options.settings.custom) return;
			
		},

		/*
         * 适应表格各个部分的宽度
		 */
		_adjustWidth: function(){
			var $el = this.$el,
				frontWidth = 0,
				finaleWidth = 0,
				self = this;

			$el.find('.oas-grid-thead .grid-front-fixed').find('th').each(function(){
				if($(this).hasClass('oas-grid-checkbox')){
					frontWidth += 40;
				}else{
					frontWidth += $(this).find('.oas-grid-th').outerWidth();
				}
			});

			// modify by rbai @ 2015-05-07 修复后边固定列时，无数据表格宽度计算错误
			$el.find('.oas-grid-thead .grid-finale-fixed').find('th').each(function(){
				if($(this).hasClass('oas-grid-operator') && $(this).hasClass('oas-grid-operator-panel')){
					finaleWidth += 100;
				}else if($(this).hasClass('oas-grid-operator-normal')){
					finaleWidth += self.options.colOpts.width;
				}else{
					finaleWidth += $(this).find('.oas-grid-th').outerWidth();
				}
			});

			// modify by rbai @ 2015-05-07 修复后边固定列时，无数据表格宽度计算错误，暂时去除（待版本稳定删除）
			// $el.find('.oas-grid-tbody .grid-finale-fixed').find('tr').eq(0).find('td').each(function(){
			// 	if($(this).hasClass('oas-grid-operator') && $(this).hasClass('oas-grid-operator-panel')){
			// 		finaleWidth += 100;
			// 	}else if($(this).hasClass('oas-grid-operator-normal')){
			// 		finaleWidth += self.options.colOpts.width;
			// 	}else{
			// 		finaleWidth += $(this).find('.oas-grid-td').outerWidth();
			// 	}
			// });

			$el.find('.grid-front-fixed').width(frontWidth);
			$el.find('.grid-finale-fixed').width(finaleWidth);

			// 表格体主表格左右适应
			$el.find('.grid-main').css({
				'left': frontWidth,
				'right': finaleWidth
			});

			if(this.options.type !== 'simple'){
				this._setMinWidth();
			}
			

			// 表格水平滚动条。
			$el.find('.oas-grid-scroll-h').css({
				'marginLeft': frontWidth,
				'marginRight': finaleWidth
			}).width($el.width() - frontWidth - finaleWidth).scrollLeft(0).find('div').width($el.find('.grid-main').find('table').width());


			// var width = $el.find('.grid-main').width(),
			// 	tempWidth = width - this.gridTotalWidth;
			// var $tds = $el.find('.grid-main').find('tr').find('td');
			// //$el.find('.grid-main').find('th').eq(this.minWidthCol).find('.oas-grid-th').width($tds.eq(this.minWidthCol).width()+(width - this.gridTotalWidth) - 10);
			// $tds.eq(this.minWidthCol).find('.oas-grid-td').width($tds.eq(this.minWidthCol).width() + tempWidth - 10);


			this.frontWidth = frontWidth;
			this.finaleWidth = finaleWidth;
		},


		/*
         * 设置自适应列的宽度
		 */
		_setMinWidth: function(){
			if(!this.customHead){
				return;
			}
			var $el = this.$el,
				options = this.options,
				tempWidth = $el.width() - this.gridTotalWidth,
				offset = this.customHead.length * 10,
				$minwidthCol = $el.find('.oas-grid-minwidth');
				minwidth = parseInt($minwidthCol.attr('minwidth'));

			if(options.checkbox){
				tempWidth -= 40;
			}

			if(tempWidth > minwidth){
				$minwidthCol.find('.oas-grid-th').width(tempWidth - offset).end().find('.oas-grid-td').width(tempWidth - offset + 1);
			}else{
				$minwidthCol.find('.oas-grid-th').width(minwidth).end().find('.oas-grid-td').width(minwidth + 1);
			}
			
		},

		/*
         * 适应表格高度
		 */
		_adjustHeight: function(){
			var $el = this.$el,
				options = this.options,
				height = options.height,
				$tbodys = $el.find(".oas-grid-tbody"),
				$tbodyMainTrs = $tbodys.find(".grid-main tr:not('.oas-grid-comment')"),
				$tbodyFrontTrs = $tbodys.find('.grid-front-fixed').find("tr:not('.oas-grid-comment')"),
				$tbodyFinaleTrs = $tbodys.find('.grid-finale-fixed').find("tr:not('.oas-grid-comment')");

			// 如果允许换行
			if(!options.nowrap){
				 // 遍历所有的行
				$.each($tbodyMainTrs, function(i, element){
					var $tbodyFrontTr = $tbodyFrontTrs.eq(i).find('td').css('height', 'auto').end();
					var $tbodyFinaleTr = $tbodyFinaleTrs.eq(i).find('td').css('height', 'auto').end();
					$(this).find('td').css('height', 'auto');
					var height = i === 0 ? $(this).height() - 10 : $(this).height() - 11;
					var Frontheight = i === 0 ? $tbodyFrontTr.height() - 10 : $tbodyFrontTr.height() - 11;
					var Finaleheight = i === 0 ? $tbodyFinaleTr.height() - 10 : $tbodyFinaleTr.height() - 11;
					var arr = [height, Frontheight, Finaleheight],
						max = Math.max.apply(null, arr);
					
					$tbodyFrontTr.find('td').height(max);
					$tbodyFinaleTr.find('td').height(max);
					$(this).find('td').height(max);
				});
			}
			//换到下面去执行
			//if(height === 'auto'){
				//var tableHeight = $el.find('.grid-main').eq(1).height();
				//tableHeight = !!tableHeight ? tableHeight : 300;
				//$el.find('.oas-grid-tbody').height(tableHeight);
			//}
		},
		_adjustCommentHeight:function(){
			var $el = this.$el,
				options = this.options,
				height = options.height,
				$tbodys = $el.find('.oas-grid-tbody'),
				$tbodyMainComTrs = $tbodys.find('.grid-main tr.oas-grid-comment'),
				$tbodyFrontComTrs = $tbodys.find('.grid-front-fixed').find('tr.oas-grid-comment'),
				$tbodyFinaleComTrs = $tbodys.find('.grid-finale-fixed').find('tr.oas-grid-comment');

			// 如果允许换行
			//if(!options.nowrap){
				 // 遍历所有有comment的行
				$.each($tbodyMainComTrs, function(i, element){
					var $tbodyFrontComTr = $tbodyFrontComTrs.eq(i);
					var $tbodyFinaleComTr = $tbodyFinaleComTrs.eq(i);
					var height = $(this).height() - 11;
					var Frontheight = $tbodyFrontComTr.height() - 11;
					var Finaleheight = $tbodyFinaleComTr.height() - 11;
					var arr = [height, Frontheight, Finaleheight],
						max = Math.max.apply(null, arr);
					
					$tbodyFrontComTr.find('td').height(max);
					$tbodyFinaleComTr.find('td').height(max);
					$(this).find('td').height(max);
				});
			//}
			if(height === 'auto'){
				var tableHeight = $el.find('.grid-main').eq(1).height();
				tableHeight = !!tableHeight ? tableHeight : 140;
				$el.find('.oas-grid-tbody').height(tableHeight);
			}
		},
		/*
		 * 计算表格每一列的宽度，如果未设定宽度则宽度默认为100,
		 * 通过文字字符长度匹配宽度，确保每个文字都可以显示出来。
		 */
	    _adjustColumnWidth: function(){
	    	var options = this.options,
	    	    tempTotalWidth = 0,
	    		head = options.head,
	    		widthArr = [],
	    		hasMinWidth = false,
	    		maxwidthcol;

	    	var $tempDiv = $('<div class="outWindow" style="font-weight:bold;"></div>').appendTo($('body'));

	    	if(!options.head || options.head.length === 0) return;

    		for (var i = 0, headLen = head.length; i < headLen; i++) {
    			var tempHd = head[i], 
    				tempdiv = $('<span style="float:left;"></span>'),
    				text,
    				tempWidth;
				if(tempHd.width === 'minwidth'){
					hasMinWidth = i;
				}
    			if( !!!tempHd.width || tempHd.width === "" || isNaN(tempHd.width)){
    				tempHd.width = 10;
    			}else{
    				tempHd.width = parseInt(tempHd.width, 10)
    			}
    			tempdiv.html(tempHd.label); // 包裹div节点，防止计算字符长度错误。
    			$tempDiv.append(tempdiv);
    			
    			text = tempdiv.text();
    			tempWidth = tempdiv.width() + 20;
    			if(tempHd.sort && tempHd.sort !== 'false'){tempWidth += 40;}
    			tempdiv.remove();
    			if(tempHd.width < tempWidth){ 
    				tempHd.width = tempWidth;
    			}

    			widthArr.push(tempHd.width);

    			tempTotalWidth += (tempHd.width+1);

    		}

    		if(hasMinWidth !== false){
    			this.minWidthCol = hasMinWidth;
    		}else{
    			maxwidthcol = Math.max.apply(null, widthArr);
    			this.minWidthCol = oasis.indexOf(maxwidthcol, widthArr);
    		}

    		head[this.minWidthCol].minwidth = true;

    		tempTotalWidth -= (head[this.minWidthCol].width + 1);
    		// 保存所有的列的宽度。
    		this.gridTotalWidth = tempTotalWidth;
		},

		_adjustHeadData: function(){

			this._filterCustomHeadCol();

			var options = this.options,
				fixedColumn = options.fixedColumn,
	    		frontFixed = fixedColumn[0],
	    		finaleFixed = fixedColumn[1],
	    		head = this.customHead,
	    		allHead = options.head,
	    		finaleStart,
	    		mainEnd,
	    		hasFrontFixed = (frontFixed && frontFixed > 0),
	    		hasFinaleFixed = (finaleFixed && finaleFixed > 0),
	    		_renderData = this._renderData;

	    	if(hasFrontFixed && !hasFinaleFixed){
	    		// 只存在前固定, modify by gh on 06-13
	    		_renderData.headFront = allHead.slice(0, frontFixed);
	    		if(options.settings && options.settings.custom){
	    		_renderData.headCenter = head;
	    		}else{
	    			_renderData.headCenter = head.slice(frontFixed, head.length);
	    		}
	    	}else if(!hasFrontFixed && hasFinaleFixed){
	    		// 只存在尾固定
	    		mainEnd = allHead.length - finaleFixed;
	    		_renderData.headFinale = allHead.slice(mainEnd, allHead.length);
	    		if(options.settings && options.settings.custom){
	    		_renderData.headCenter = head;
	    		}else{
	    			_renderData.headCenter = head.slice(0, mainEnd);
	    		}
	    	}else if(hasFrontFixed && hasFinaleFixed){
	    		// 同时存在首尾固定
	    		finaleStart = allHead.length - finaleFixed;
	    		_renderData.headFront = allHead.slice(0, frontFixed);
	    		_renderData.headFinale = allHead.slice(finaleStart, allHead.length);
	    		if(options.settings && options.settings.custom){
	    		_renderData.headCenter = head;
	    		}else{
	    			_renderData.headCenter = head.slice(frontFixed, finaleStart);
	    		}
	    	}else{
	    		_renderData.headCenter = head;
	    	}
		},

		_adjustBodyData: function(){

			// 如果数据为对象，则先整理数据
			if(this.options.body && oasis.type.isObject(this.options.body[0])){
				this._convertBodyData();
			}

			this._filterCustomBodyCol();

			var options = this.options,
				fixedColumn = options.fixedColumn,
	     		body = this.customBody,
	     		allBody = options.body,
	     		frontFixed = fixedColumn[0],
	     		finaleFixed = fixedColumn[1],
	     		hasFrontFixed = (frontFixed && frontFixed > 0),
	    		hasFinaleFixed = (finaleFixed && finaleFixed > 0),
	    		_renderData = this._renderData;

	    	var bodyFront = _renderData.bodyFront = [],
		        bodyCenter = _renderData.bodyCenter = [],
		        bodyFinale = _renderData.bodyFinale = [];

	     	if(hasFrontFixed && !hasFinaleFixed){

	    		// 只存在前固定, modify by gh on 06-13
		        for (var i = 0, len = body.length; i < len; i++) {
		          bodyFront.push(allBody[i].slice(0, frontFixed));
		          if(options.settings && options.settings.custom){
		          bodyCenter.push(body[i]);
	    		  }else{
	    		      bodyCenter.push(body[i].slice(frontFixed, body[i].length));
	    		  }
		        };
		    		
		    		 
		    	}else if(!hasFrontFixed && hasFinaleFixed){

		    	// 只存在尾固定
		        for (var i = 0, len = body.length; i < len; i++) {
		          mainEnd = allBody[i].length - finaleFixed;
		          bodyFinale.push(allBody[i].slice(mainEnd, allBody[i].length));
		          if(options.settings && options.settings.custom){
		          bodyCenter.push(body[i]);
	    		  }else{
	    		      bodyCenter.push(body[i].slice(0, mainEnd));
	    		  }
		        }
			        if(options.checkbox){
	    		  	  bodyFront = _renderData.bodyFront = new Array(body.length);
	    		    }
		    		

		    	}else if(hasFrontFixed && hasFinaleFixed){

		    	// 同时存在首尾固定
		        for(var i = 0, len = body.length; i < len; i++){
		          finaleStart = allBody[i].length - finaleFixed;
		          bodyFront.push(allBody[i].slice(0, frontFixed));
		          bodyFinale.push(allBody[i].slice(finaleStart, allBody[i].length));
		          if(options.settings && options.settings.custom){
		          bodyCenter.push(body[i]);
	    		  }else{
	    		      bodyCenter.push(body[i].slice(frontFixed, finaleStart));
	    		  }
		        }
	    	} else {
	    		bodyCenter = _renderData.bodyCenter = body;
	    		bodyFront = _renderData.bodyFront = new Array(body.length);
	    		bodyFinale = _renderData.bodyFinale = new Array(body.length);
	    		
	    	}
		},

		// 转换body的数据。
		_convertBodyData: function(){
			var self = this,
				options = this.options,
				body = options.body,
				tempBody = this.tempBody = $.extend(true, body, {}),
				head = options.head;

			body = options.body = [];

			// 遍历所有的行
			for(var i = 0, len = tempBody.length; i < len; i++){
				var dataRow = tempBody[i],
					oneRow = [];

				// 遍历所有的列
				for(var j = 0, jlen = head.length; j < jlen; j++){
					var column = head[j],
						value = dataRow[column["name"]],
						convertor = column["dataRender"];

					if(convertor && oasis.type.isFunction(convertor)){
						value = convertor(dataRow);
					}

					oneRow.push(value);
				}

				body.push(oneRow);
			}

		},
		/*
		 设置表格属性
		 */
		_setGridProp: function(){
			var $el = this.$el,
				options = this.options,
				nowrap = options.nowrap;

			$el.addClass('oas-grid');
			if( nowrap ){
				$el.addClass('oas-grid-nowrap');
			}

			if(options.striped){
				$el.addClass('grid-striped');
			}
		},


		/**
		 * 渲染表格操作，头部，滚动条等。
		 */
		_renderGrid: function(){
			var _renderData = this._renderData = {},
				options = this.options;

			_renderData.opts = options.opts;
			_renderData.settings = options.settings;
			
			this._createDom();
		},

		// 渲染表格头等。
		_renderHead: function(){
			var _renderData = this._renderData,
				options = this.options;

			this._adjustHeadData();

			_renderData.headFront = _renderData.headFront || [];
    		_renderData.headFinale = _renderData.headFinale || [];
    		_renderData.headCenter = _renderData.headCenter || [];

			//_renderData.opts = options.opts;
			//_renderData.settings = options.settings;
			_renderData.checkbox = options.checkbox;
			this._renderData.colOpts = options.colOpts;
			
			// add by rbai @ 2015-3-19
			if(options.type !== 'simple'){
				this.$el.find('.oas-grid-thead').empty().append(this.headTpl(this._renderData));
			}
			
		},

		/*
		 * 渲染表格体数据
		 */
		_renderBody: function(){
			var options = this.options;
			this._initOptionsHelps(options);
			
			// 重置选择的行
			this.checkedArray = [];
			this._adjustBodyData();
			this._renderData.tempBody = this.tempBody;
			this._renderData.colOpts = options.colOpts;


			var html = this.bodyTpl(this._renderData);

			// add by rbai @ 2015-3-19
			if(options.type === "simple"){
				this.$el.html(this.simpleBodyTpl(this._renderData));
				return false;
			}

			this.$el.find('.oas-grid-tbody').empty().append(html).append('<div class="oas-grid-mask"></div>').append('<div class="oas-table-nodata clearfix"><div class="oas-mascot-img"><div class="oas-mascot-medium oas-mascot-nodata"></div></div><div class="oas-mascot-text"><p>对不起，暂无数据</p></div></div>');

			// 重置顶部的checkbox为未选中
			this.$el.find('.oas-grid-thead').find('.oas-grid-checkbox').find('input[type=checkbox]').prop('checked', false);
			//渲染完毕后更新this.checkArray
			this._updateCheckArray();
			this._iCall('loaded');
		},

		/*
         * 适应表格。
		 */
		_adjustGrid: function(){
			
			var self = this,
				options = this.options,
				$el = this.$el,
				head = options.head,
				body = options.body;

			this._adjustHeight();

			setTimeout(function(){
				self._adjustCommentHeight();
			},10);

			// add by rbai @ 2015-3-19
			if(head && body && body.length > 0){
				if(options.headFixed && options.type !== "simple"){
					$el.find('.oas-grid-head').attr('data-oassticky-top-spacing',options.fixedTop);
					$el.find('.oas-grid-head').oasSticky();
					setTimeout(function(){
						$el.find('.oas-grid-head').oasSticky('stoptionsp', $el.height()+$el.offset().top)
					},30);
				}

			}
			
			this._adjustWidth();
		},
		/*
		 * 创建表格顶部操作和头部及表格框架
		 */
		_createDom: function(){
			var html = this.tpl(this._renderData);

			this.$el.empty().append(html);
		},

		_checkNoData: function(){
			var $el = this.$el,
				options = this.options,
				head = options.head,
				body = options.body;

			if(head.length === 0 && body.length === 0){
				$el.addClass('oas-grid-nodata').removeClass('oas-grid-hidebody');
			}else if(!!!body){ // 无表格体
				$el.addClass('oas-grid-hidebody');
			}else if(body.length === 0){ // 表格体无数据
				$el.addClass('oas-grid-nobody').removeClass('oas-grid-hidebody');
			}else{

				$el.removeClass('oas-grid-nodata oas-grid-nobody oas-grid-hidebody');
			}
		},
		//把固定列的数据从定制列中分离出来 add by gh 06-12
		_splitFromCustom:function(customs){
			var options = this.options,
				fixedColumn = options.fixedColumn,
	    		frontFixed = fixedColumn[0],
	    		finaleFixed = fixedColumn[1],
	    		head = options.head,
	    		finaleStart,
	    		mainEnd,
	    		hasFrontFixed = (frontFixed && frontFixed > 0),
	    		hasFinaleFixed = (finaleFixed && finaleFixed > 0),
	    		fixedColumns = [],
	    		filterCustoms = [];
				
	    	if(hasFrontFixed && !hasFinaleFixed){
	    		// 只存在前固定
	    		fixedColumns = head.slice(0, frontFixed);
	    	}else if(!hasFrontFixed && hasFinaleFixed){
	    		// 只存在尾固定
	    		mainEnd = head.length - finaleFixed;
	    		fixedColumns = head.slice(mainEnd, head.length);
	    	}else if(hasFrontFixed && hasFinaleFixed){
	    		// 同时存在首尾固定
	    		finaleStart = head.length - finaleFixed;
	    		var fronts = head.slice(0, frontFixed);
	    		var ends = head.slice(finaleStart, head.length);
	    		fixedColumns = fronts.concat(ends);
	    	}
	    	for (var i = 0; i < customs.length; i++) {
	    		var custom = customs[i];
	    		var find = false;
	    		for (var j = 0; j < fixedColumns.length; j++) {
	    			if(fixedColumns[j].name == customs[i].name){
	    				find = true;
	    				break;
			}
	    		}
	    		!find && (filterCustoms.push(custom));
	    	};
	    	return filterCustoms;
		},


		// 对外方法集
		invoke: {
			/*
             * 设置表格数据。
             * @param body 为表格体数据。
             * @param head 为表格头部数据。
             * 当只有一个参数时，为body，当两个参数时，为head和body。
			 */
			data: function(head, body){

				// 添加loading时间验证，loading时间不足800毫秒，均继续loading。
				// var time = new Date().getTime() - this.loadingTime,
				// 	self = this;
				// if(this.loadingTime && time < 800){
				// 	setTimeout(function(){
				// 		if(body){
				// 			self._iCall('data', [head, body]);
				// 		}else{
				// 			self._iCall('data', [head]);
				// 		}
						
				// 	},800 - time);
				// 	return;
				// }

				var $el = this.$el,
					options = this.options;


				if(arguments.length === 1){
					body = head;
					head = null;
				}

				if(body && !head){
					options.body = body;
					// 如果没有渲染，则退出
					if(!options.autoRender && !this.renderdy) return;

					this._iCall('colOpts', [options.colOpts.data]);
					this._checkNoData();
					this._renderBody();
					this._adjustGrid();
					return this;
				}

				if(body && head){
					options.body = body;
					options.head = head;

					this._checkNoData();
					// 如果没有渲染，则退出
					if(!options.autoRender && !this.renderdy) return;
					this._createTable();
					return this;
				}
			},

			/*
             * 设置行操作。
             * @param data 操作数据
			 */
			colOpts: function(data){
				var options = this.options,
					hasOptData = options.colOpts.data,
					body = options.body;

				if(!!!data || !!!body) return;

				if(data.length === 1){
					for(var i = 1, len = body.length; i < len; i++){
						data[i] = data[0];
					}
				}

				options.colOpts.data = data;

				// 如果默认不渲染且表格没有渲染，则退出
				if(!options.autoRender && !this.renderdy) return;

				
				this._renderData.colOpts = options.colOpts;
				// 渲染表格
				if(hasOptData){
					 this._renderBody();
					 this._adjustGrid();
				}else{
					
					this._renderHead();
					this._renderBody();
					this._adjustGrid();
				}
			},

			/*
             * 为表格添加loading状态。
			 */
			loading: function(){
				var $el = this.$el,
					$load = $el.find('.oas-grid-loading');

				//this.loadingTime = new Date().getTime();
				//$el.find('.oas-grid-optBar').find('.oas-grid-opt-btn').addClass('oas-disabled');
				$el.addClass('oas-grid-onloading');
				$load.css('top', this.$el.find('.oas-grid-head').height());
					
			},

			/*
             * 设置表格头悬浮高度
			 */
			fixedTop:function(top){
				var $el = this.$el;
				$el.find('.oas-grid-head').attr('data-oassticky-top-spacing',top);
				$el.find('.oas-grid-head').oasSticky();
			},

			loaded: function(){
				var $el = this.$el,
					$load = this.$el.find('.oas-grid-loading');

				$el.removeClass('oas-grid-onloading');
				$load.css('top', 0);
			},

			/*
			 * 获取checkbox选中的索引
			 */
			getCheckedIndex: function(){
				var indexs = this.checkedArray,
					i,len,
					selectRows = [];
				if(this.tempBody && indexs){
					for(i = 0, len = indexs.length; i < len; i++){
						selectRows.push(this.tempBody[indexs[i]]);
					}
				}
				
				var result = {
					selectIndexs: indexs,
					selectRows: selectRows
				};
				return result;
			},


			/*
			 * 设置checkbox选中的索引
			 * @param indexs {Array} 需要选中的checkbox索引,
			 * @param merge {Boolean} 是否合并页面中已有的checkbox
			 */
			setCheckedIndex: function(indexs, merge){
				var $el = this.$el;
				var $checkboxs = $el.find('.oas-grid-tbody .grid-front-fixed').find('input[type=checkbox]');
			
				for(var i = 0, len = indexs.length; i < len; i++){
					$checkboxs.eq(indexs[i]).click();
				}

				this.checkedArray = indexs;
			},

			/*
             * 设置需要定制的序列
			 */
			setCustomCol: function(_defaultColObj, _customColObj){
				
				var options = this.options,
					customColObj,
					defaultColObj,
					customColInfo = options.customColInfo;

				// 如果只有一个参数或者传的用户定制为空,就是,需要获取用户的定制列信息
				if(arguments.length === 1 || !_customColObj){
					
					customColObj = oasGrid.getCustomCol({
						context_path: customColInfo.context_path,
	                    user_id: customColInfo.user_id,
	                    sys_id: customColInfo.sys_id,
	                    res_id: customColInfo.res_id
					});

					if(( !customColObj || (customColObj && customColObj.length < 0) ) && options.customColInfo.showDefault ){
						customColObj = _defaultColObj;
					}
					
				}else if(arguments.length === 2){
					customColObj = _customColObj;
					defaultColObj = _defaultColObj;
				}


				// 如果没有传默认定制列，则认为默认定制为所有的。
				if(!_defaultColObj){
					defaultColObj = oasGrid._head2Custom(options.head);
				}else{
					defaultColObj = _defaultColObj;
				}

				//从默认定制列中排除固定列 add by gh 06-12
				defaultColObj = this._splitFromCustom(defaultColObj);
				
				// 设置默认定制
				defaultColObj && (customColInfo.defaultCol = defaultColObj);

				if(!customColObj){
					customColObj = oasGrid._head2Custom(options.head);
				}
				//从定制列中排除固定列 add by gh 06-12
				customColObj = this._splitFromCustom(customColObj);

				// 设置当前定制
				if(customColObj){
					this.currentCustom = customColInfo.customCol = customColObj;
					// 如果默认不渲染且表格没有渲染，则退出
					if(!options.autoRender && !this.renderdy) return;

					// 如果表格已经渲染则再次重绘表格
					this._renderHead();
					this._renderBody();
					this._adjustGrid();
				}
				
			},

			/*
             * 重新设置表格的定制信息
			 */
			setCustomColInfo: function(customInfo){
				if(!customInfo){return;}
				$.extend(this.options.customColInfo, customInfo);
				this._iCall('setCustomCol',[this.options.customColInfo.defaultCol]);
			},

			/*
			 * 设置行样式
			 */
			setTrsAttr: function(arrIndex, classNames){
				
				var $tables = this.$el.find('.oas-grid-tbody').find('table');
				for(var i = 0, len = arrIndex.length; i < len; i++){
					$tables.each(function(){
						$(this).find('tr').eq(arrIndex[i]).addClass(classNames);
					});
				}
			},

			/* 
			 * 渲染表格
			 */
			render: function(){
				var options = this.options;

				if(!options.autoRender && this.renderdy) return;

				this.render();
			},

			resize: function(){
				this._adjustGrid();
			},
			/* 
			 * 增加表格定制触发的对外方法
			 */
			gridCustom:function(){
				if(this.options.settings.custom){
				this._gridCustom();
				}
			}
		}
	});


	// helpers oasGrid静态方法。

	var oasGrid = {};


	// 头部数据转定制数据
	oasGrid._head2Custom = function(head){
		var customCol = [];

		for(var i = 0, len = head.length; i < len; i++){
			customCol.push({
				name: head[i].name,
				label: head[i].label
			});
		}

		return customCol;
	}

	/* 获取用户定制项列。
     * @param obj 需要传给obj，obj包含如下属性:
     * 1. context_path  (String)  项目路径
	 * 2. user_id (String)  用户ID
	 * 3. sys_id (String)  模块ID
	 * 4. res_id (String)  定制表格ID（和模块ID组合成 定制唯一标示项）项）
     * 
     */
	oasGrid.getCustomCol = function(obj){

        var context_path = obj.context_path,
            user_id = obj.user_id,
            sys_id = obj.sys_id,
            res_id = obj.res_id,
            customCol;

        $.ajax({
            type: "GET",
            url: context_path + '/component/colCustom!getCustomCfg.action',
            async: false,
            data: {
                userId: user_id,
                modeCode: [sys_id, res_id].join('_')
            },

            success: function(response){
                if(parseInt(response.rspCode) === 200){
                	if(response.rspMsg){
                		customCol = JSON.parse(response.rspMsg);
                	}
                }else{
                	OasMsg.open({
                		type: 'error',
                		message: response.rspMsg
                	});
                }
            },
            error: function () {
            	OasMsg.open({
            		type: 'error',
            		message: '获取用户定制项数据失败'
            	});
            }
        });

        return customCol;
    };


    /* 保存用户定制项列。
     * @param obj 需要传给obj，obj包含如下属性:
     * 1. context_path  (String)  项目路径
	 * 2. user_id (String)  用户ID
	 * 3. sys_id (String)  模块ID
	 * 4. res_id (String)  定制表格ID（和模块ID组合成 定制唯一标示项）项）
     * 5. colsJsonStr (String) 用户定制的数据，需要转为字符串。
     */
    oasGrid.saveCustomCol = function(obj){

        var context_path = obj.context_path,
            user_id = obj.user_id,
            sys_id = obj.sys_id,
            res_id = obj.res_id,
            colsJsonStr = obj.colsJsonStr;

        $.ajax({
            type: "POST",
            url: context_path + '/component/colCustom!updateCustomCol.action',
            data: {
                userId: user_id,
                modeCode: [sys_id, res_id].join('_'),
                colsJsonStr: colsJsonStr
            },

            success: function(response){
                if(parseInt(response.rspCode) === 200){
                	OasMsg.open({
                		type: 'success',
                		message: '保存定制信息成功'
                	});
                }else{
                	OasMsg.open({
                		type: 'error',
                		message: response.rspMsg
                	});
                }
            },
            error: function () {
            	OasMsg.open({
            		type: 'warning',
            		message: '保存失败,仅本次生效'
            	});
            }
        });
    };

})();
;
(function() {
	$.oasUiFactory("oasCalendar", {
		options: {
			/*组件默认显示的日期，默认为空*/
            defaultDate : null,
			/*组件显示格式。为字符串。表示显示的组件格式*/
			showTime : false,
			/*可选日期范围,为日期类型数组[startDate,endDate]*/
			range : [],
			/*当前显示年中有活动的日期数据集合:格式:{'0':[1,2,3,4,5,6],'1':[12,17]}*/
			active:null,
			/*日历展示类型:normal,panel*/
			type:"normal",
			/*是否需要清空按钮*/
			isNeedClear:true,
			/*日历面板id，默认为空*/
			panelId:"",
			/*年份改变回调*/
			yearChange:function(currentYear){},
			/*月份改变回调*/
			monthChange:function(date){},
			/*日期改变回调*/
			dateChange:function(date){},
			/*时间改变回调*/
			timeChange:function(type,prevValue,afterValue){},
			/*点击日期回调，不论日期有没有变化*/
			clickDate:function(date){},
			/*点击确定后选中的时间*/
			selectedTime:function(date){},
			/*选中时间改变*/
			selectedChange:function(prevDate,afterDate){},
			/*清空之后回调*/
			afterClear:function(){}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl = '<div class="oas-calendar" id="{{panelId}}">' +
						'<div class="calendar-arrow"></div>' +
						'<div class="calendar-hd">' +
							'<a href="javascript:;" class="prev oasicon oasicon-prev"></a>' +
							'<a href="javascript:;" class="year"></a>' +
							'<a href="javascript:;" class="month"></a>' +
							'<a href="javascript:;" class="next oasicon oasicon-next"></a>' +
						'</div>' +
						'<div class="calendar-bd">' +
						'</div>' +
						'<div class="calendar-ft">' +
						   '<div class="time-box clearfix {{if showTime == false}}hide{{/if}}">' +
							   '<div class="time-choose">' +
					               '<input type="text" maxlength="2" class="hour" max="23" value={{time.hour}}>' +
				          	       '<span class="oas-unit">时</span>' +
					           '</div>' +
					           '<span class="colon">:</span>' +
					           '<div class="time-choose">' +
					               '<input type="text" maxlength="2" class="minute" max="59" value={{time.minute}}>' +
				          	       '<span class="oas-unit">分</span>' +
					           '</div>' +
					           '<span class="colon">:</span>' +
					           '<div class="time-choose last">' +
					               '<input type="text" maxlength="2" class="second" max="59" value={{time.second}}>' +
				          	       '<span class="oas-unit">秒</span>' +
					           '</div>' +
					           '<div class="time-bubble">' +
					           	'<div class="time-bubble-arrow bottom-arrow"></div>' +
					           	'<a href="javascript:;" class="oasicon oasicon-close"></a>' +
					            '</div>' +
					       '</div>' +
					       '<div class="current-time clearfix">' +
					       		'<a href="javascript:;" class="today">设为当前时间</a>' +
					       		'<a href="javascript:;" class="sure oas-btn">确定</a>' +
					       		'<a href="javascript:;" class="clear oas-btn{{if isNeedClear == false}} hide{{/if}}">清空</a>' +
					       '</div>' +
						'</div>' +
					 '</div>';
					 var tplPanel = '<div class="oas-calendar oas-calendar-panel">' +
									'<div class="calendar-hd">' +
										'<a href="javascript:;" class="prev oasicon oasicon-prev"></a>' +
										'<a href="javascript:;" class="year"></a>' +
										'<a href="javascript:;" class="month"></a>' +
										'<a href="javascript:;" class="next oasicon oasicon-next"></a>' +
									'</div>' +
									'<div class="calendar-bd">' +
									'</div>' +
							 '</div>';
				this.tplPanel = template.compile(tplPanel);
				var bodyTplA =     '<ul class="clearfix week">' + 
										'<li>一</li>' +
										'<li>二</li>' +
										'<li>三</li>' +
										'<li>四</li>' +
										'<li>五</li>' +
										'<li>六</li>' +
										'<li class="last">日</li>' +
									'</ul>' +
									'<ul class="clearfix day">' +
										'{{each list}}' +
											'<li class="{{if ($index + 1) % 7 == 0}}last{{/if}}{{if $value.isDayOfCurrMon == 0 ||  $value.isDayOfCurrMon == 2}} other{{/if}}{{if $value.inRange == false}} oas-disable{{/if}}{{if $value.isCurrentDay == true}} current{{/if}}{{if $value.active == true}} has-active{{/if}}" index={{$index}}>' + 
												'{{$value.value}}' +
											'</li>' +
										'{{/each}}' +
									'</ul>';
								
				this.bodyTplA = template.compile(bodyTplA);

				var bodyTplB =  '<ul class="clearfix {{if type == 1}}year{{/if}} {{if type == 2}}month{{/if}}">' +
									'{{each list}}' +
										'<li class="{{if ($index + 1) % 4 == 0}}last{{/if}} {{if $value.isfuture == true}}other{{/if}} {{if $value.isCurrent == true}}current{{/if}} {{if $value.active == true}}has-active{{/if}}" index={{$index}}>' +
										'{{$value.value}}' +
										'</li>' +
									'{{/each}}' +
								'</ul>';
				this.bodyTplB = template.compile(bodyTplB);

				var bubbleTpl = '<ul class="clearfix {{if type == 0}}hour{{/if}} {{if type == 1}}minute{{/if}} {{if type == 2}}second{{/if}}">' +
									'{{each list}}' +
										'<li class="{{if $value.current == true}}current{{/if}}{{if $value.disable == true}}oas-disable{{/if}}" index={{$index}}>{{$value.value}}</li>' +
									'{{/each}}' +
								'</ul>';
				this.bubbleTpl = template.compile(bubbleTpl);

			return template.compile(tpl);
		},

		// 事件
		events: {

			'click .calendar-hd>.prev': function(self, evt) {
				var $el = this.$el;
				if(this.showType == 0){
					var lastMonFirDay = new Date(new Date().setFullYear(this.showYear, this.showMonth - 1,1));
					var lastMonthDays = this._getMonthDays(lastMonFirDay.getFullYear(),lastMonFirDay.getMonth() + 1);
					
					var preMonthDay = new Date(new Date().setFullYear(this.showYear, this.showMonth-1,1));
					var dayList = this._createDays(preMonthDay);
					this._renderBody(dayList);
				}else if(this.showType == 1){
					var yearList = this._createYears(this.startYear-10);
					this._renderBody(yearList);
				}else if(this.showType == 2){
					this.showYear --;
					this._emit('yearChange', [this.showYear]);
					//this._emit("timeChange",["year",new Date(this.showYear+1,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
					var monthList = this._createMonths(0);
					this._renderBody(monthList);
				}
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-hd>.next': function(self, evt) {
				var $el = this.$el;
				if(this.showType == 0){
					var nextMonFirDay = new Date(new Date().setFullYear(this.showYear, this.showMonth + 1,1));
					var nextMonthDays = this._getMonthDays(nextMonFirDay.getFullYear(),nextMonFirDay.getMonth() + 1);
					
					var nextMonthDay = new Date(new Date().setFullYear(this.showYear, this.showMonth+1,1));
					var dayList = this._createDays(nextMonthDay);
					this._renderBody(dayList);
				}else if(this.showType == 1){
					var yearList = this._createYears(this.endYear);
					this._renderBody(yearList);
				}else if(this.showType == 2){
					this.showYear ++;
					this._emit('yearChange', [this.showYear]);
					//this._emit("timeChange",["year",new Date(this.showYear-1,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
					var monthList = this._createMonths(0);
					this._renderBody(monthList);
				}
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-bd>ul.day>li': function(self, evt) {
				var $el = this.$el;
				if(!$(self).hasClass("oas-disable")){
					if(this.canBodyList[$(self).attr('index')].isDayOfCurrMon == 1){
						$el.find('.calendar-bd>ul.day>li').removeClass('current');
						$(self).addClass('current');
						this.showDay = this.canBodyList[$(self).attr('index')].value;
						if(this.focusDate.getTime() != new Date(this.showYear,this.showMonth,this.showDay).getTime()){
							this.focusDate = new Date(this.showYear,this.showMonth,this.showDay);
							this._emit('dateChange', [new Date(this.showYear,this.showMonth,this.showDay)]);
						}
						this._emit('clickDate', [new Date(this.showYear,this.showMonth,this.showDay)]);
					}else if(this.canBodyList[$(self).attr('index')].isDayOfCurrMon == 0){
						this.showDay = this.canBodyList[$(self).attr('index')].value;
						if(this.focusDate.getTime() != new Date(this.showYear,this.showMonth - 1,this.showDay).getTime()){
							this.focusDate = new Date(this.showYear,this.showMonth - 1,this.showDay);
							this._emit('dateChange', [new Date(this.showYear,this.showMonth - 1,this.showDay)]);
						}
						this._emit('clickDate', [new Date(this.showYear,this.showMonth - 1,this.showDay)]);
						var preMonthDay = new Date(new Date().setFullYear(this.showYear, this.showMonth-1,1));
						var dayList = this._createDays(preMonthDay);
						this._renderBody(dayList);
					}else if(this.canBodyList[$(self).attr('index')].isDayOfCurrMon == 2){
						this.showDay = this.canBodyList[$(self).attr('index')].value;
						if(this.focusDate.getTime() != new Date(this.showYear,this.showMonth + 1,this.showDay).getTime()){
							this.focusDate = new Date(this.showYear,this.showMonth + 1,this.showDay);
							this._emit('dateChange', [new Date(this.showYear,this.showMonth + 1,this.showDay)]);
						}
						this._emit('clickDate', [new Date(this.showYear,this.showMonth + 1,this.showDay)]);
						var nextMonthDay = new Date(new Date().setFullYear(this.showYear, this.showMonth+1,1));
						var dayList = this._createDays(nextMonthDay);
						this._renderBody(dayList);
					}
				}
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				if(this.options.type == "normal"){
					return false;
				}
			},
			'click .calendar-bd>ul.year>li': function(self, evt) {
				var $el = this.$el;
				$el.find('.calendar-bd>ul.year>li').removeClass('current');
				$(self).addClass('current');
				var prevYear = this.showYear;
				if(this.showYear != this.canBodyList[$(self).attr('index')].value){
					this.showYear = this.canBodyList[$(self).attr('index')].value;
					this.focusYear = this.showYear;
					this._emit('yearChange', [this.showYear]);
					//this._emit("timeChange",["year",new Date(prevYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
				}
				var firstDay = new Date(new Date().setFullYear(this.showYear, this.showMonth,1));
				this.showType = 0;
				if(this.options.showTime){
					this.$el.find(".calendar-ft>.time-box").show();
				}
				dayList = this._createDays(firstDay);
				this._renderBody(dayList);
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-bd>ul.month>li': function(self, evt) {
				var $el = this.$el;
				$el.find('.calendar-bd>ul.month>li').removeClass('current');
				$(self).addClass('current');
				var prevMonth = this.showMonth;
				if(this.showMonth != parseInt($(self).attr('index'),10)){
					this.showMonth = parseInt($(self).attr('index'),10);
					this.focusMonth = new Date(this.showYear,this.showMonth);
					this._emit('monthChange', [new Date(this.showYear,this.showMonth)]);
					//this._emit("timeChange",["month",new Date(this.showYear,prevMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
				}
				var firstDay = new Date(new Date().setFullYear(this.showYear, this.showMonth,1));
				this.showType = 0;
				if(this.options.showTime){
					this.$el.find(".calendar-ft>.time-box").show();
				}
				dayList = this._createDays(firstDay);
				this._renderBody(dayList);
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-hd>.year': function(self, evt) {
				if(this.showType == 0){
					this.showType = 1;
					this.focusYear = this.showYear;
					var yearList = this._createYears(this.showYear);
					this._renderBody(yearList);
					if(this.options.showTime){
						this.$el.find(".calendar-ft>.time-box").hide();
					}
				}
				this.$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-hd>.month': function(self, evt) {
				if(this.showType == 0){
					this.showType = 2;
					this.focusMonth = new Date(this.showYear,this.showMonth);
					var monthList = this._createMonths(this.showMonth);
					this._renderBody(monthList);
					if(this.options.showTime){
						this.$el.find(".calendar-ft>.time-box").hide();
					}
				}
				this.$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'click .calendar-ft>.current-time>a.today': function(self, evt) {
				if(!$(self).hasClass("oas-disable")){
					var currTime = new Date(),
					preSelectTime = this._iCall("getSelectedTime"),
					options = this.options;
					if(options.range.length == 2){
						startDate = options.range[0];
						endDate = options.range[1];
						if(!options.showTime){
							currTime = new Date(currTime.getFullYear(),currTime.getMonth(),currTime.getDate());
							startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate());
							endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());	
						}
						if(currTime.getTime() > endDate.getTime()){
							currTime = endDate;
						}else if(currTime.getTime() < startDate.getTime()){
							currTime = startDate;
						}
					}
					if(options.showTime){
						this.triggerEle.val(currTime.getFullYear()+'-'+this._formatTime(currTime.getMonth() + 1)+'-'+this._formatTime(currTime.getDate()) + ' ' +this._formatTime(currTime.getHours()) + ':' +this._formatTime(currTime.getMinutes()) +':' +this._formatTime(currTime.getSeconds()));
					}else{
						this.triggerEle.val(currTime.getFullYear()+'-'+this._formatTime(currTime.getMonth() + 1)+'-'+this._formatTime(currTime.getDate()));	
					}	
					var currSelectTime = this._iCall("getSelectedTime");
					this._emit('selectedTime', [currSelectTime]);
					if(preSelectTime && preSelectTime.getTime() != currSelectTime.getTime()){
						this._emit('selectedChange', [preSelectTime,currSelectTime]);
					}else if(!preSelectTime){
						this._emit('selectedChange', [null,currSelectTime]);
					}
					this.$el.oasOverlay("hide");
				}
				return false;
			},
			'click .calendar-ft>.current-time>a.clear': function(self, evt) {
				var preSelectTime = this._iCall("getSelectedTime")
				this.triggerEle.val("");
				var currSelectTime = this._iCall("getSelectedTime");
				this.$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				this._emit('afterClear');
				this._emit('selectedChange', [preSelectTime,currSelectTime]);
				return false;
			},
			'click .calendar-ft>.current-time>a.sure': function(self, evt) {
				var currTime = new Date(),
					preSelectTime = this._iCall("getSelectedTime"),
					options = this.options;
				this.selectedYear = this.focusDate.getFullYear();
				this.selectedMonth = this.focusDate.getMonth();
				this.selectedDay = this.focusDate.getDate();
				this.selectedHour = this.$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val();
				this.selectedMin = this.$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val();
				this.selectedSec = this.$el.find('.calendar-ft>.time-box>.time-choose>input.second').val();
				this._isOverRange();
				//var tempHour = this.$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val();
				//var tempMinute = this.$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val();
				//var tempSec = this.$el.find('.calendar-ft>.time-box>.time-choose>input.second').val();
				//if(this._compareTime(1,tempHour)){
				//	this.selectedHour = tempHour;
				//}else{
					//this.$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(this.selectedHour);
				//}
				//if(this._compareTime(2,tempMinute)){
				//	this.selectedMin = tempMinute;
				//}else{
				//	this.$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(this.selectedMin);
				//}
				//if(this._compareTime(3,tempSec)){
				//	this.selectedSec = tempSec;
				//}else{
				//	this.$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(this.selectedSec);
				//}
				this._checkHour(this.selectedHour);
				this._checkMinute(this.selectedMin);
				this._checkSecond(this.selectedSec);
				if(this.options.showTime){
					this.triggerEle.val(this.selectedYear+'-'+this._formatTime(this.selectedMonth + 1)+'-'+this._formatTime(this.selectedDay) +' '+this._formatTime(this.selectedHour) +':' +this._formatTime(this.selectedMin) + ':' +this._formatTime(this.selectedSec));
				}else{
					this.triggerEle.val(this.selectedYear+'-'+this._formatTime(this.selectedMonth + 1)+'-'+this._formatTime(this.selectedDay));
				}
				var currSelectTime = this._iCall("getSelectedTime");
				this._emit('selectedTime', [this._iCall("getSelectedTime")]);
				if(preSelectTime && preSelectTime.getTime() != currSelectTime.getTime()){
					this._emit('selectedChange', [preSelectTime,currSelectTime]);
				}else if(!preSelectTime){
					this._emit('selectedChange', [null,currSelectTime]);
				}
				this.$el.oasOverlay("hide");
				return false;
			},
			'focus .calendar-ft>.time-box>.time-choose>input.hour': function(self, evt) {
				var list = [],
					currValue = $(self).val();
				for (var i = 0; i < 24; i++) {
					var obj = {};
					obj.value = i + "";
					obj.value = obj.value.length < 2 ? ("0" + obj.value) : obj.value;
					obj.current = (i == currValue && currValue != "") ? true : false;
					if(!this._compareTime(1,obj.value)){
						obj.disable = true;
					}
					list.push(obj);
				}
				this.bubbleList = list;
				this.bubbleType = 0;
				this._renderBubble(list);
				return false;
			},
			'focus .calendar-ft>.time-box>.time-choose>input.minute': function(self, evt) {
				var list = [],
					currValue = $(self).val();
				for (var i = 0; i < 12; i++) {
					var obj = {};
					obj.value = i*5 +"";
					obj.value = obj.value.length < 2 ? ("0" + obj.value) :obj.value;
					obj.current = obj.value == currValue ? true : false;
					if(!this._compareTime(2,obj.value)){
						obj.disable = true;
					}
					list.push(obj);
				};
				this.bubbleList = list;
				this.bubbleType = 1;
				this._renderBubble(list);
				return false;
			},
			'focus .calendar-ft>.time-box>.time-choose>input.second': function(self, evt) {
				var list = [],
					currValue = $(self).val();
				for (var i = 0; i < 12; i++) {
					var obj = {};
					obj.value = i*5 +"";
					obj.value = obj.value.length < 2 ? ("0" + obj.value) :obj.value;
					obj.current = obj.value == currValue ? true : false;
					if(!this._compareTime(3,obj.value)){
						obj.disable = true;
					}
					list.push(obj);
				};
				this.bubbleList = list;
				this.bubbleType = 2;
				this._renderBubble(list);
				return false;
			},
			'blur .calendar-ft>.time-box>.time-choose>input': function(self, evt) {
				this._isOverRange();
				return false;
			},
			'click .calendar-ft>.time-box>.time-bubble>ul>li': function(self, evt) {
				$el = this.$el;
				if(!$(self).hasClass('oas-disable')){
					if(this.bubbleType == 0){
						var prevHour = parseInt(this.selectedHour,10);
						this.selectedHour = this.bubbleList[$(self).attr('index')].value;
						$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(this._formatTime(this.bubbleList[$(self).attr('index')].value));
						this._isOverRange();
						if(prevHour != parseInt(this.selectedHour,10)){
							this._emit("timeChange",["hour",prevHour,parseInt(this.selectedHour,10)]);
						}
					}else if(this.bubbleType == 1){
						var prevMin = parseInt(this.selectedMin,10);
						this.selectedMin = this.bubbleList[$(self).attr('index')].value;
						$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(this._formatTime(this.bubbleList[$(self).attr('index')].value));
						this._isOverRange();
						if(prevMin != parseInt(this.selectedMin,10)){
							this._emit("timeChange",["minute",prevMin,parseInt(this.selectedMin,10)]);
						}
					}else if(this.bubbleType == 2){
						var prevSec = parseInt(this.selectedSec,10);
						this.selectedSec = this.bubbleList[$(self).attr('index')].value;
						$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(this._formatTime(this.bubbleList[$(self).attr('index')].value));
						this._isOverRange();
						if(prevSec != parseInt(this.selectedSec,10)){
							this._emit("timeChange",["second",prevSec,parseInt(this.selectedSec,10)]);
						}
					}
					$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				}
				return false;
			},
			'keyup .calendar-ft>.time-box>.time-choose>input[type=text]': function(self, evt) {
				var keyCode = event.keyCode || evt.keyCode;
				//只捕获数字键和10以内的案件，增加对最大长度限制。
				if(!((keyCode > 47 && keyCode < 58 && !event.shiftKey) || (keyCode > 95 && keyCode < 106) || (keyCode < 10))){
					evt.preventDefault();
					if(!this._checkNum($(self))){
						return;
					}
					return false;
				}else{
					if(!this._checkNum($(self))){
						return;
					}
					var currValue = parseInt($(self).val(),10);
					if($(self).hasClass("hour")){
						if(currValue != parseInt(this.selectedHour,10)){
							this._emit("timeChange",["hour",parseInt(this.selectedHour,10),currValue]);
							this.selectedHour = currValue;
							this._checkHour(this.selectedHour);
						}
					}else if($(self).hasClass("minute")){
						if(currValue != parseInt(this.selectedMin,10)){
							this._emit("timeChange",["minute",parseInt(this.selectedMin,10),currValue]);
							this.selectedMin = currValue;
							this._checkMinute(this.selectedMin);
						}
					}
					else if($(self).hasClass("second")){
						if(currValue != parseInt(this.selectedSec,10)){
							this._emit("timeChange",["second",parseInt(this.selectedSec,10),currValue]);
							this.selectedSec = currValue;
							this._checkSecond(this.selectedSec);
						}
					}
					//this._isOverRange();
				}
			},
			'click .calendar-ft>.time-box>.time-bubble>.oasicon-close': function(self, evt) {
				$el = this.$el;
				$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				return false;
			},
			'keydown .calendar-ft>.time-box>.time-choose>input[type=text]': function(self, evt) {
				var keyCode = event.keyCode || evt.keyCode;
				//只捕获数字键和10以内的案件，增加对最大长度限制。
				if(!((keyCode > 47 && keyCode < 58 && !event.shiftKey) || (keyCode > 95 && keyCode < 106) || (keyCode < 10))){
					evt.preventDefault();
					return false;
				}
			}
		},
		_checkNum:function($input){
			var value = $input.val();
			var result = true;
			if(value != ''){
				var num = parseInt(value,10);
				if('number' != typeof num || isNaN(num)){
					$input.val('');
					result = false;
				}else{
					$input.val(num);
				}
			}else{
				result = false;
			}
			return result;
		},
		_checkTime:function(){
			var result = this._checkIsborderDate(),
				$el = this.$el,
				options = this.options;
			if(result.result && result.border == "left"){
				$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(this._formatTime(options.range[0].getHours()));
				$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(this._formatTime(options.range[0].getMinutes()));
				$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(this._formatTime(options.range[0].getSeconds()));
			}else if(result.result && result.border == "right"){
				$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(this._formatTime(options.range[1].getHours()));
				$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(this._formatTime(options.range[1].getMinutes()));
				$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(this._formatTime(options.range[1].getSeconds()));
			}
		},
		_isOverRange:function(){
			var options = this.options,
				startDate,
				endDate;
			if(options.range.length == 2){
				startDate = options.range[0];
				endDate = options.range[1];
				startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),startDate.getHours(),startDate.getMinutes(),startDate.getSeconds());
				endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate(),endDate.getHours(),endDate.getMinutes(),endDate.getSeconds());
				var currentTime = new Date(this.showYear,this.showMonth,this.showDay,this.selectedHour,this.selectedMin,this.selectedSec);
				if(currentTime.getTime() >= endDate.getTime()){
					this._fillHMS(endDate);
				}else if(currentTime.getTime() <= startDate.getTime()){
					this._fillHMS(startDate);
				}
			}
		},
		_checkIsborderDate:function(){
			var options = this.options,
				result = {};
			if(this.options.range.length == 2){
				var range0 = options.range[0],
					range1 = options.range[1],
					rangeDay0 = new Date(range0.getFullYear(),range0.getMonth(),range0.getDate()),
					rangeDay1 = new Date(range1.getFullYear(),range1.getMonth(),range1.getDate()),
					currentShowDay = new Date(this.showYear,this.showMonth,this.showDay);
				if(currentShowDay.getTime() == rangeDay0.getTime()){
					result.result = true;
					result.border = "left";
				}else if(currentShowDay.getTime() == rangeDay1.getTime()){
					result.result = true;
					result.border = "right";
				}else{
					result.result = false;
				}
			}else{
				result.result = false;
			}
			return result;
		},
		_checkHour:function(hour){
			var tmp = "";
			if(hour.length == 2){
				if(hour.substring(0,1) != "0"){
					tmp = parseInt(hour,10);
					this.selectedHour = tmp > 23 ? "23" :  hour;
				}
			}
		},
		_checkMinute:function(minute){
			var tmp = "";
			if(minute.length == 2){
				if(minute.substring(0,1) != "0"){
					tmp = parseInt(minute,10);
					this.selectedMin = tmp > 59 ? "59" :  minute;
				}
			}
		},
		_checkSecond:function(second){
			var tmp = "";
			if(second.length == 2){
				if(second.substring(0,1) != "0"){
					tmp = parseInt(second,10);
					this.selectedSec = tmp > 59 ? "59" :  second;
				}
			}
		},
		_create: function() {
			this.options.defaultDate = this._initDefaultDate(this.options.defaultDate);
			this.options.defaultDate = this.options.defaultDate == null ? new Date() : this.options.defaultDate;
			if(this.options.range.length == 2){
				this.options.range[0] = this._initDefaultDate(this.options.range[0]);
				this.options.range[1] = this._initDefaultDate(this.options.range[1]);
			}
			
			this._initParam();
			if(this.options.type == "normal"){
				this._addInputClass();
				this._preRenderData();
				this._showByOverLay();
			}else if(this.options.type == "panel"){
				this._preRenderData();
			}
			this._bindOuterClick();
		},
		//点击空白区域关闭bubble
		_bindOuterClick:function(){
			var $el = this.$el;
			$(document).on('click.calendar', function(e){
				if($(e.target).parents('.oas-calendar .time-bubble').size()<1 && !$(e.target).is('.oas-calendar .time-bubble') && $(e.target).parents('.oas-calendar .time-bubble').size()<1 && !$(e.target).is('.oas-calendar .time-bubble')
					 && $(e.target).parents('.oas-calendar .time-choose>input[type=text]').size()<1 && !$(e.target).is('.oas-calendar .time-choose>input[type=text]') && $(e.target).parents('.oas-calendar .time-choose>input[type=text]').size()<1 && !$(e.target).is('.oas-calendar .time-choose>input[type=text]')){
					$el.find('.calendar-ft>.time-box>.time-bubble').hide();
				}	
			});
		},
		_initDefaultDate:function(defaultDate){
			var tempDate = "";
			if(defaultDate != null && typeof(defaultDate) != "object"){
				if(typeof(defaultDate) == "string" && defaultDate.indexOf("-") != -1){
					tempDate = defaultDate.replace(new RegExp("-","gm"),"/");
				}else{
					tempDate = parseInt(defaultDate,10);
				}
				return new Date(tempDate);
			}
			else{
				return defaultDate;
			}
		},
		_initParam:function(){
			this.triggerEle = this.$el;
			this.showType = 0;
			this.selectedDate = this.options.defaultDate == null ? new Date() : this.options.defaultDate;
			this.showYear = this.selectedYear = this.selectedDate.getFullYear();
			this.showMonth = this.selectedMonth = this.selectedDate.getMonth();
			this.showDay = this.selectedDay = this.selectedDate.getDate();
			this.focusDate =  new Date(this.showYear,this.showMonth,this.showDay);
			this.selectedHour = this.selectedDate.getHours();
			this.selectedMin = this.selectedDate.getMinutes();
			this.selectedSec = this.selectedDate.getSeconds();
			this._checkHour(this.selectedHour);
			this._checkMinute(this.selectedMin);
			this._checkSecond(this.selectedSec);
			this.activeYear = this.options.defaultDate ? this.options.defaultDate.getFullYear() : new Date().getFullYear();
		},
		_addInputClass:function(){
			var $i = $('<i class="oasicon oasicon-calendar"></i>');
			var $a = $('<a href="javascript:;" class="calendar-date-icon oas-btn-icon"></a>');
			this.triggerEle.after($a.append($i)).addClass('calendar-date');
			this.triggerEle.attr("readonly","readonly");
			//this.triggerEle = this.triggerEle.parent();
		},
		//反填时间
		_fillTime:function(){
			var time = this.triggerEle.val();
			if(time.length > 0){
				this.showType = 0;
				this.showYear = time.substring(0,4);
				this.showMonth = parseInt(time.substring(5,7),10)-1;
				this.showDay = time.substring(8,10);
				this.focusDate = new Date(time.substring(0,4),parseInt(time.substring(5,7),10)-1,time.substring(8,10));
				var dayList = this._createDays(this.focusDate);
				this._renderBody(dayList);
				if(this.options.showTime){
					var hour = time.substring(11,13);
					var minute = time.substring(14,16);
					var second = time.substring(17,19);
					this.selectedHour = hour;
					this.selectedMin = minute;
					this.selectedSec = second;
					this.$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(hour);
					this.$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(minute);
					this.$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(second);
				}
			}	
		},
		//反填时分秒
		_fillHMS:function(time){
			if(time && this.options.showTime){
				var hour = time.getHours();
				var minute = time.getMinutes();
				var second = time.getSeconds();
				this.selectedHour = hour;
				this.selectedMin = minute;
				this.selectedSec = second;
				this.$el.find('.calendar-ft>.time-box>.time-choose>input.hour').val(this._formatTime(hour));
				this.$el.find('.calendar-ft>.time-box>.time-choose>input.minute').val(this._formatTime(minute));
				this.$el.find('.calendar-ft>.time-box>.time-choose>input.second').val(this._formatTime(second));
			}	
		},
		/**日历面板显隐**/
		_showByOverLay: function() {
			var options = this.options,
				triggerEle = this.triggerEle,
				thisEl = this.$el,
				self = this,
				thisHeight = thisEl.outerHeight(), //日历面板高度
				thisWidth = thisEl.outerWidth(), //日历面板宽度
				win = {
					height: $(window).height(),
					width: $(window).width(),
					scrollTop: $(window).scrollTop(),
					scrollLeft: $(window).scrollLeft()
				};

			thisEl.oasOverlay({
				target: $(triggerEle),
				triggerType: 'click',
				align: {
					// 基准定位元素，默认为当前的可视区域
					baseElement: $(triggerEle),
					// 基准定位元素的定位点，默认为左上角
					baseXY: [0, 0]
				},
				beforeOpen: function($el) {
					win = {
						height: $(window).height(),
						width: $(window).width(),
						scrollTop: $(window).scrollTop(),
						scrollLeft: $(window).scrollLeft()
					};
					$('body').find(".oas-calendar").hide();
					$('body').find(".oas-calendar.oas-calendar-panel").show();
					self._fillTime();
					var baseXY,
						left = 0,
						top = 0,
						thisTop = triggerEle.offset().top, //top值
						thisLeft = triggerEle.offset().left; //left值

						thisEl.find('.calendar-ft>.time-box>.time-bubble').hide();
						//thisEl.find('.calendar-ft>.time-box>.time-choose>input').val("");
						
						thisEl.find(".calendar-arrow").removeClass("bottom-arrow opposite");
						if(win.height + win.scrollTop - thisTop - triggerEle.outerHeight() -12 - thisHeight < 0 && thisTop -12 - thisHeight > 0){
							top = -thisHeight - 12;
							thisEl.find(".calendar-arrow").addClass("bottom-arrow");
							if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
								left = triggerEle.outerWidth() - thisWidth;
								thisEl.find(".calendar-arrow").addClass("opposite");
							}else{
								left = 0;
							}
						}else{
							top = triggerEle.outerHeight() + 12;
							if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
								left = triggerEle.outerWidth() - thisWidth;
								thisEl.find(".calendar-arrow").addClass("opposite");
							}else{
								left = 0;
							}
						}
						baseXY = [left, top];
					this.oasOverlay('align', {
						baseXY: baseXY
					});
					if(self.options.range.length == 2){
						var now = new Date(),
						startDate = self.options.range[0];
						endDate = self.options.range[1];
						if(!self.options.showTime){
							now = new Date(now.getFullYear(),now.getMonth(),now.getDate());
							startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate());
							endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());	
						}
						if(now.getTime() >= startDate.getTime() && now.getTime() <= endDate.getTime()){
							self.$el.find(".calendar-ft>.current-time>a.today").removeClass("oas-disable");
						}else{
							self.$el.find(".calendar-ft>.current-time>a.today").addClass("oas-disable");
						}
					}
				}
			});
			thisEl.oasOverlay('blurHide', [$('.input-hasicon > a.calendar-date-icon.oas-btn-icon')]);
		},
		_compareTime:function(type,value){
			var range = this.options.range;
			var focusDate = this.focusDate;
			var value = parseInt(value,10);
			if(!(range.length == 2)){
				return true;
			}
			var startDate = range[0];
			var endDate = range[1];
			if(type == 1){
				//比较小时
				var startValue = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),startDate.getHours(),0,0);
				var endValue = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate(),endDate.getHours(),0,0);
				var currValue = new Date(this.showYear,this.showMonth,this.showDay,value,0,0);
				if(startValue.getTime() <= currValue.getTime() && endValue.getTime() >= currValue.getTime()){
					return true;
				}else{
					return false;
				}
			}else if(type == 2){
				//比较分钟
				var startValue = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),startDate.getHours(),startDate.getMinutes(),0);
				var endValue = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate(),endDate.getHours(),endDate.getMinutes(),0);
				var currValue = new Date(this.showYear,this.showMonth,this.showDay,this.selectedHour,value,0);
				if(startValue.getTime() <= currValue.getTime() && endValue.getTime() >= currValue.getTime()){
					return true;
				}else{
					return false;
				}

			}else if(type == 3){
				//比较秒
				var startValue = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),startDate.getHours(),startDate.getMinutes(),startDate.getSeconds());
				var endValue = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate(),endDate.getHours(),endDate.getMinutes(),endDate.getSeconds());
				var currValue = new Date(this.showYear,this.showMonth,this.showDay,this.selectedHour,this.selectedMin,value);
				if(startValue.getTime() <= currValue.getTime() && endValue.getTime() >= currValue.getTime()){
					return true;
				}else{
					return false;
				}
			}
		},
		_createDays:function(day){
			var options = this.options,
			firstDay = new Date(new Date().setFullYear(day.getFullYear(), day.getMonth(),1)),
			firIndex = firstDay.getDay()-1,
			dayList = [],
			lastMonFirDay,
			lastMonthDays,
			leftDay,
			startDate,
			endDate,
			dataFirstDay,
			dataLastDay,
			currActive,
			monthDays = this._getMonthDays(day.getFullYear(),day.getMonth() + 1);
			if(options.range.length == 2){
				startDate = new Date(options.range[0].getFullYear(),options.range[0].getMonth(),options.range[0].getDate());
				endDate = new Date(options.range[1].getFullYear(),options.range[1].getMonth(),options.range[1].getDate());
			}
			firIndex = firIndex < 0 ? 6 : firIndex;
			var prevYear = this.showYear;
			if(this.showYear != day.getFullYear()){
				this.showYear = day.getFullYear();
				this._emit('yearChange', [this.showYear]);
				//this._emit("timeChange",["year",new Date(prevYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
			}
			var prevMonth = this.showMonth;
			if(this.showMonth != day.getMonth()){
				this.showMonth = day.getMonth();
				this._emit('monthChange', [new Date(this.showYear,this.showMonth)]);
				//this._emit("timeChange",["month",new Date(this.showYear,prevMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec),new Date(this.showYear,this.showMonth,this.showDay,this.
						//selectedHour,this.selectedMin,this.selectedSec)]);
			}
			
			lastMonFirDay = new Date(new Date().setFullYear(day.getFullYear(), day.getMonth() - 1,1));
			lastMonthDays = this._getMonthDays(lastMonFirDay.getFullYear(),lastMonFirDay.getMonth() + 1);
			leftDay = 42 - firIndex - monthDays;

			dataFirstDay = new Date(lastMonFirDay.getFullYear(),lastMonFirDay.getMonth(),lastMonthDays - firIndex + 1);
			dataLastDay = new Date(day.getFullYear(),day.getMonth()+1,leftDay);
			
			for (var i = 0; i < firIndex; i++) {
				var tempDay = {};
				tempDay.date = new Date(lastMonFirDay.getFullYear(),lastMonFirDay.getMonth(),lastMonthDays - firIndex + i + 1);
				tempDay.value = lastMonthDays - firIndex + i + 1;
				tempDay.isDayOfCurrMon = 0;
				tempDay.active = false;
				dayList.push(tempDay);
			}
			currActive = options.active == null ? null : options.active[day.getMonth()];
			currActive = currActive == undefined ? null : currActive;
			currActive = day.getFullYear() != this.activeYear ? null : currActive;
			for (var i = 0; i < monthDays; i++) {
				var tempDay = {};
				tempDay.value = i + 1;
				tempDay.date = new Date(day.getFullYear(),day.getMonth(),i + 1);
				tempDay.isCurrentDay = (new Date(this.showYear,this.showMonth,i+1).getTime() == this.focusDate.getTime()) ? true : false;
				tempDay.isDayOfCurrMon = 1;
				tempDay.active = false;
				if(currActive != null){
					for(var index in currActive){
						if(tempDay.value == currActive[index]){
							tempDay.active = true;
							break;
						}
					}
				}
				dayList.push(tempDay);
			}
			for (var i = 0; i < leftDay; i++) {
				var tempDay = {};
				tempDay.date = new Date(day.getFullYear(),day.getMonth()+1,i + 1);
				tempDay.value = i + 1;
				tempDay.isDayOfCurrMon = 2;
				tempDay.active = false;
				dayList.push(tempDay);
			}
			if(endDate != undefined && startDate != undefined){
				if(endDate.getTime() <= dataFirstDay.getTime()){
					for (var i = 0; i < dayList.length; i++) {
						dayList[i].inRange = false;
					}
				}else if(dataLastDay.getTime() < startDate.getTime()){
					for (var i = 0; i < dayList.length; i++) {
						dayList[i].inRange = false;
					}
				}else if(startDate.getTime() < dataFirstDay.getTime() && dataFirstDay.getTime() < endDate.getTime() && endDate.getTime() < dataLastDay.getTime()){
					for (var i = 0; i < dayList.length; i++) {
						if(dayList[i].date.getTime() <= endDate.getTime()){
							dayList[i].inRange = true;
						}else{
							dayList[i].inRange = false;
						}
					}
				}
				else if(endDate.getTime() > dataLastDay.getTime() && startDate.getTime() > dataFirstDay.getTime() && startDate.getTime() < dataLastDay.getTime()){
					for (var i = 0; i < dayList.length; i++) {
						if(dayList[i].date.getTime() < startDate.getTime()){
							dayList[i].inRange = false;
						}else{
							dayList[i].inRange = true;
						}
					}
				}
				else if(startDate.getTime() >= dataFirstDay.getTime() && endDate.getTime() <= dataLastDay.getTime()){
					for (var i = 0; i < dayList.length; i++) {
						if(dayList[i].date.getTime() < startDate.getTime()){
							dayList[i].inRange = false;
						}else if(dayList[i].date.getTime() >= startDate.getTime() && dayList[i].date.getTime() <= endDate.getTime()){
							dayList[i].inRange = true;
						}else if(dayList[i].date.getTime() > endDate.getTime()){
							dayList[i].inRange = false;
						}
					}
				}
			}else{
				for (var i = 0; i < dayList.length; i++) {
						dayList[i].inRange = true;
					}
			}
			this.canBodyList = dayList;
			return dayList;
		},
		_createYears:function(currYear){
			var year = parseInt((currYear+"").substring(3,4),10);
			var startYear = parseInt(currYear,10) - year;
			var endYear = startYear + 10;
			var yearList = [];
			var nowYear = parseInt(new Date().getFullYear(),10);
			this.startYear = startYear;
			this.endYear = endYear;
			for (var i = 0; i < 12; i++) {
				var yearObj = {};
				yearObj.value = endYear - 11 + i;
				yearObj.isfuture = (endYear - 11 + i) > nowYear ? true : false;
				yearObj.isCurrent = (endYear - 11 + i) == this.focusYear ? true : false;
				yearObj.active = false;
				if(this.activeYear == yearObj.value && this.options.active != null){
					yearObj.active = true;
				}
				yearList.push(yearObj);
			};
			this.canBodyList = yearList;
			return yearList;
		},
		_createMonths:function(currmonth){
			var monthList = [];
			var nowYear = parseInt(new Date().getFullYear(),10);
			var nowMonth = parseInt(new Date().getMonth(),10);
			var activeMonth = [];
			if(this.activeYear == this.showYear && this.options.active != null){
				for(var mon in this.options.active){
					activeMonth.push(mon);
				}
			}
			for (var i = 0; i < 12; i++) {
				var monthObj = {};
				monthObj.value = (i + 1) + '月' ;
				monthObj.active = false;
				monthObj.isfuture = ((i > nowMonth && this.showYear == nowYear) || this.showYear > nowYear) ? true : false;
				monthObj.isCurrent = this.focusMonth.getTime() == new Date(this.showYear,i).getTime() ? true : false;
				if(activeMonth.length > 0){
					for(var mon in activeMonth){
						if(activeMonth[mon] == i){
							monthObj.active = true;
						}
					}
				}
				monthList.push(monthObj);
			};
			this.canBodyList = monthList;
			return monthList;
		},
		/*
		 * 获取某一个月的天数
		 * param (int) year
		 * param (int) month
		 */
		_getMonthDays : function(year,month){
			var days;
			if((month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0)){
				days = 31;
			}else if(month != 2){
				days = 30;
			}else{
				//如果是闰年
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
			}
            return days;
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				time = {},
				dayList,
				day;
			this._renderData = _renderData = {
				showTime:options.showTime,
				isNeedClear:options.isNeedClear
			};
			time.hour = this._formatTime(options.defaultDate.getHours());
			time.minute = this._formatTime(options.defaultDate.getMinutes());
			time.second = this._formatTime(options.defaultDate.getSeconds());
			this._renderData.time = time;
			this._renderData.panelId = options.panelId;
			this._createDom();

			day = options.defaultDate ? options.defaultDate : new Date();
			dayList = this._createDays(day);
			this._renderBody(dayList);
		},
		//格式化时间
		_formatTime:function(num){
			var num = num + "";
			num = num.length == 1 ? "0" + num : num;
			num = num.length == 0 ? "00" : num;
			return num;
		},
		/*
		 * 渲染时分秒气泡
		 */
		_renderBubble:function(list){
			var options = this.options,
			$el = this.$el,
				html,
				_renderData = {
					list:list,
					type:this.bubbleType
				};
			html = this.bubbleTpl(_renderData);
			$el.find('.calendar-ft>.time-box>.time-bubble>ul').remove();
			$el.find('.calendar-ft>.time-box>.time-bubble').append(html);
			if(this.bubbleType == 0){
				$el.find('.calendar-ft>.time-box>.time-bubble').css('top',-115);
				$el.find('.calendar-ft>.time-box>.time-bubble>.time-bubble-arrow').css('left',15);
			}else if(this.bubbleType == 1){
				$el.find('.calendar-ft>.time-box>.time-bubble').css('top',-71);
				$el.find('.calendar-ft>.time-box>.time-bubble>.time-bubble-arrow').css('left',84);
			}else if(this.bubbleType == 2){
				$el.find('.calendar-ft>.time-box>.time-bubble').css('top',-71);
				$el.find('.calendar-ft>.time-box>.time-bubble>.time-bubble-arrow').css('left',154);
			}
			$el.find('.calendar-ft>.time-box>.time-bubble').show();
		},
		/*
		 * 渲染日历体数据
		 */
		_renderBody: function(list){
			var options = this.options,
				html,
				_renderData = {};
				

			if(this.showType == 0){
				_renderData = {
					list:list
				};
				html = this.bodyTplA(_renderData);
				this.$el.find('.calendar-hd>.year').text(this.showYear +"年");
				this.$el.find('.calendar-hd>.month').text((this.showMonth + 1) +"月");
				this.$el.find('.calendar-hd>.year').css('margin-left',18);
				this.$el.find('.calendar-hd>.month').show();
			}else if(this.showType == 1){
				_renderData = {
					list:list,
					type:this.showType
				};
				html = this.bodyTplB(_renderData);
				this.$el.find('.calendar-hd>.year').text(this.startYear + '-' +this.endYear);
				this.$el.find('.calendar-hd>.year').css('margin-left',27);
				this.$el.find('.calendar-hd>.month').hide();
			}else if(this.showType == 2){
				_renderData = {
					list:list,
					type:this.showType
				};
				html = this.bodyTplB(_renderData);
				this.$el.find('.calendar-hd>.year').text(this.showYear);
				this.$el.find('.calendar-hd>.year').css('margin-left',44);
				this.$el.find('.calendar-hd>.month').hide();
			}
			this.$el.find('.calendar-bd').html("");
			this.$el.find('.calendar-bd').append(html);
		},
		/**创建dom节点**/
		_createDom: function() {
			if(this.options.type == "normal"){
				var html = this.tpl(this._renderData);
				this.$el = $(html);
				$("body").append(this.$el);
				//this.triggerEle.parents(".input-hasicon").after(this.$el);
			}else if(this.options.type == "panel"){
				var html = this.tplPanel(this._renderData);
				this.$el.empty().append(html);
			}
			if(this.options.range.length == 2){
				var now = new Date(),
				startDate = this.options.range[0];
				endDate = this.options.range[1];
				if(!this.options.showTime){
					now = new Date(now.getFullYear(),now.getMonth(),now.getDate());
					startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate());
					endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());	
				}
				if(now.getTime() >= startDate.getTime() && now.getTime() <= endDate.getTime()){
					this.$el.find(".calendar-ft>.current-time>a.today").removeClass("oas-disable");
				}else{
					this.$el.find(".calendar-ft>.current-time>a.today").addClass("oas-disable");
				}
			}
		},

		// 组件对外方法集
		invoke: {
			//当前年有活动日期的数据集合,格式:{'0':[1,2,3,4,5,6],'1':[12,17]}
			activeDates:function(active,activeYear){
				this.activeYear = activeYear;
				this.options.active = active;
			},
			//获取当前选中的时间
			getSelectedTime:function(){
				var time = this.triggerEle.val();
				if(time.length > 0){
					if(this.options.showTime){
						return new Date(time.substring(0,4),parseInt(time.substring(5,7),10)-1,time.substring(8,10),time.substring(11,13),time.substring(14,16),time.substring(17,19));
					}else{
						return new Date(time.substring(0,4),parseInt(time.substring(5,7),10)-1,time.substring(8,10));
					}
				}else{
					return null;
				}
			},
			//改变可选日期范围,[startDate,endDate]
			range:function(range){
				if(range.length == 2){
					range[0] = this._initDefaultDate(range[0]);
					range[1] = this._initDefaultDate(range[1]);
				}
				this.options.range = range;
				var startDate = range[0],
					focusDate = this.focusDate;
					endDate = range[1];
				startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),startDate.getHours(),startDate.getMinutes(),startDate.getSeconds());
				endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate(),endDate.getHours(),endDate.getMinutes(),endDate.getSeconds());
				this.options.range[0] = startDate;
				this.options.range[1] = endDate;
				this.showType = 0;
				if(focusDate.getTime() < startDate.getTime()){
					this.focusDate = startDate;
					this._fillHMS(this.focusDate);
				}else if(focusDate.getTime() > endDate.getTime()){
					this.focusDate = endDate;
					this._fillHMS(this.focusDate);
				}
				var now = new Date();
				if(!this.options.showTime){
					now = new Date(now.getFullYear(),now.getMonth(),now.getDate());
					startDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate());
					endDate = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());	
				}
				if(now.getTime() >= startDate.getTime() && now.getTime() <= endDate.getTime()){
					this.$el.find(".calendar-ft>.current-time>a.today").removeClass("oas-disable");
				}else{
					this.$el.find(".calendar-ft>.current-time>a.today").addClass("oas-disable");
				}
				this.showYear = this.focusDate.getFullYear();
				this.showMonth = this.focusDate.getMonth();
				this.showDay = this.focusDate.getDate();
				var dayList = this._createDays(this.focusDate);
				this._renderBody(dayList);
			}
		}
	});	
})();
/**
 * 选择线组件，提供类似于checkbox的交互功能。
 * @ author rbai
 * @ create time 2014.09.01
 * @ version  1.0.0
 */
(function() {

	$.oasUiFactory("oasCheckLine", {
		options: {

			/**
             * check选择的步骤
             * @type array
             */
			data: [],

			current: 1,

			/**
             * 选中时触发的回调
             * @type Function
             */
			onSelect: function(){

			}
		},

		
		_template: function() {
			var tpl = '<div class="oas-checkline-line"></div>'+
					  '<div class="oas-checkline-line-top" style="width:{{topwidth}}%"></div>'+
						'{{each data}}'+
						'<div title="{{$value.title}}" class="oas-checkline-circle{{if current-1 >= $index}} active{{/if}}" style="width:{{width}}%">'+
							'<div class="oas-checkline-href">'+
								'<div class="circleLine"></div>'+
								'<span>{{$value.label}}</span>'+
							'</div>'+
						'</div>'+
						'{{/each}}';

			return template.compile(tpl);
		},

		// 事件
		events: {
			'click .oas-checkline-circle': function(self, evt) {
				var index = $(self).closest('.oas-checkline').find('.oas-checkline-circle').index($(self));

				this._skip(index);
			}
		},

		_create: function() {
			var options = this.options;
			this.$el.addClass("oas-checkline");
			this._preRenderData();
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = this._renderData = {},
				data = options.data;
				
			var totalStep = this.totalStep = data.length;

			var width = _renderData.width = 100 / totalStep;
			var current = _renderData.current = options.current;
			_renderData.data = data;
			_renderData.topwidth = width*(current-1) + width/2;

			this.$el.empty().append(this.tpl(_renderData));
		},

		_skip: function(_step){

			var $el = this.$el,
				options = this.options,
				_renderData = this._renderData;

			console.log(_step);

			$el.find('.oas-checkline-circle').removeClass('active').eq(_step).addClass('active');

			if(_step === this.totalStep-1){
				this.$el.find('.oas-checkline-line-top').css({'width': '100%'});
			}else{
				this.$el.find('.oas-checkline-line-top').css({'width': (_renderData.width * _step + _renderData.width/2) + '%'});
			}
				
			$el.find('.oas-checkline-circle:lt('+ _step +')').addClass('active');

			this.current = _step+1;
		},
		
		// 组件对外方法集
		invoke: {

			/*
             * 跳转到某一步
             * @param _step {index},跳转到某步
			 */
			skip: function(_step){
				this._skip(--_skip);
			}
		}
	});

})();
;
(function() {
	/**
	 * 星星评分组件
	 * @ author rbai
	 * @ create time 2014.09.09
	 * @ version  1.0.0
	 */
	$.oasUiFactory("oasGrade", {
		options: {

			// 当前分数
			current: 1,

			// 是否只可以阅读，不可以评分
			onlyread: false,

			// total,总等级数
			total: 5,

			// 每个等级hover时对应的提示
			tips: [],

			// 选择等级变化时触发
			onChange: function(current) {

			}
		},


		_template: function() {

			var options = this.options,
				tpl;
			
				tpl = '<ul>'+
					    '{{each total}}'+
				        '<li>'+
				        	'<a href="javascript:;">'+
				        		'<i class="oasicon oasicon-unfav"></i>'+
				        		'<i class="oasicon oasicon-fav"></i>'+
					        '</a>'+
					    '</li>'+
					    '{{/each}}'+
				    '</ul>';

			return template.compile(tpl);
		},


		events: {

			'mouseover li': function(self, evt){
				if(this.options.onlyread) return;
				this._fnPoint($(self).index()+1);
			},

			'mouseout li': function(self, evt){
				if(this.options.onlyread) return;
				this._fnPoint();
			},

			'click li': function(self, evt){
				if(this.options.onlyread) return;
				if(this.options.current != $(self).index() + 1){
					this._emit('onChange', [$(self).index() + 1]);
				}
				var current = this.options.current = $(self).index() + 1;
				this._fnPoint();
			}
		},


		_create: function() {
			var options = this.options;

			options.current = parseInt(options.current);

			this.$el.addClass('oas-grade clearfix');

			this._preRenderData();

			this._fnPoint();
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			
			var _renderData = this._renderData = {},
				options = this.options;

			_renderData.total = new Array(options.total);


			this._createDom();

		},

		_createDom: function() {
			var html = this.tpl(this._renderData);

			this.$el.empty().append(html);

		},

		_fnPoint: function(iArg){
			var $el = this.$el,
				aLi = $el[0].getElementsByTagName('li'),
				current = this.options.current;

			//分数赋值
			current = iArg || current;
			for (i = 0; i < aLi.length; i++) aLi[i].className = i < current ? "on" : "";	
		},


		// 对外方法集
		invoke: {
			current: function(current){
				if(!!current){
					this.options.current = current;
					this._fnPoint();
				}else{
					return this.options.current;
				}
			}
		}
	});

})();
/**
 * 底部悬浮组件
 * @ author jyye
 * @ create time 2014.08.28
 * @ version  1.0.0
 */
(function() {
	$.oasUiFactory("oasBottomBar", {
		options: {
			/**
             * 状态类型,
             * @type string, noarl ,error
             * @default null
             * @example
             * $("body").oasBottomBar({
             *     type:"error"
             * })
             */
			type: 'normal',
			/**
             * 提示信息
             * @type string
             * @default null
             * @example
             * $("body").oasBottomBar({
             *     message:"我是一条提示"
             * })
             */
             message:'',
             /**
             * 按钮
             * @type object
             * @default null
             * @example
             *
             */
             buttons:null
		},

		// 模板
		_template: function() {
			var tpl =   '<div class="bottom-bar-content clearfix {{if type === "error"}}oas-error{{/if}}">'+
							'<div class="bottom-btns"></div>'+
							'<div class="bottom-tip oas-assist">{{message}}</div>'+
						'</div>';
						

			return template.compile(tpl);
		},

		// 事件
		events: {
			

		},

		_create: function() {
			var options = this.options;
			
			this._preRenderData();
			
		},

		// 准备数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				message = this.message || (this.message = options.message),
				type = this.type || (this.type = options.type)

			this._renderData = _renderData = {
				message: message,
				type :type
			};
			this._setPosition();
            this._createDom();

		},

		 _createButtons : function(obj, buttons) {
            var self = this,$el = this.$el;
            $.each(buttons, function(index) {
            	var label = buttons[index].label
                var $btn = $('<a href="javascript:;" class="oas-btn">' + label + '</a>');
                if(buttons[index].recommend == true){
                	$btn = $('<a href="javascript:;" class="oas-btn oas-recommend">' + label + '</a>');
                }
                obj.append($btn);
                $btn.bind("click", function(event) {
                    buttons[index].callBack($el);
                })
            });
        },

        _setPosition : function(){
        	if($(document).find("#oas-bd.has-left-bar").size()>0){
        		this.$el.css({"left":"130px"})
        	}
        },
		
		/**创建结构**/
		_createDom: function() {
			var html = this.tpl(this._renderData);
			this.$el.addClass("oas-bottom-bar");
			this.$el.empty().append(html);
			var buttons = this.options.buttons,
				$btns = this.$el.find(".bottom-btns");
			if (buttons) {
                this._createButtons($btns, buttons);
            }
		},

		// 对外方法
		invoke: {
			/*
			 * 修改类型
			 * @param _type
			 */
			type: function(_type) {
				var $el = this.$el;
				if(_type == 'error'){
					$el.find(".bottom-bar-content ").addClass("oas-error");
				}else{
					$el.find(".bottom-bar-content ").removeClass("oas-error");
				}
			},
			/*
			 * 修改文字信息
			 * @param _message 
			 */
			message: function(_message) {
				var $el = this.$el;
				$el.find(".bottom-tip").text(_message);
			},
			/*
			 * 修改按钮信息
			 * @param _message 
			 */
			buttons: function(_buttons) {
				var $btns = this.$el.find(".bottom-btns");
				var buttons = this.options.button = _buttons;
				$btns.empty();
				this._createButtons($btns,buttons);
			}
		}
	});

})();
/**
 * 引导分步组件
 * @ author jguo
 * @ create time 2014.08.26
 * @ version  1.0.0
 */
(function() {
	// 组件名和文件名请统一
	$.oasUiFactory("oasGuide", {
		options: {
			//分步的内容
			data: null,
			//当前的位置
			current: 1 || null			
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {			
			var tpl = '<div class="oas-guide-nav">'+
                	'<ul class="clearfix">'+
					'{{each data}}' +
							'<li{{if $index === current-1}} class="current"{{/if}} {{if $index <current}} class="finished"{{/if}} {{if $value.title}} title="{{$value.title}}"{{/if}}>'+
							  '<span class="oas-guide-number">{{if $index >= current-1}}{{$index+1}}{{/if}}</span>'+
							   ' <span class="oas-guide-text">{{#$value.label}}</span>'+
							   '{{if $index < data.length-1}}'+
								'<i class="oasicon oasicon-nextstep"></i>'+
								'{{/if}}'+
						   '</li>'+
					'{{/each}}'+
					'</ul>'+
                '</div>';		
						

			return template.compile(tpl);
		},

		// 事件
		events: {
		},

		_create: function() {
			var options = this.options;

			this._preRenderData();
			this._findGuideContent();
			// 绑定额外事件
			this._bindEvent();
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				data = this.data || (this.data = options.data);

			this._renderData = _renderData = {
				data: data,
				current: this.current || (this.current = options.current)
			};

			this._createDom();

		},
		
		//寻找是否存在的div
		_findGuideContent:function(){
			var $content = this.$content = $('.oas-guide-steps');
			if($content){
				var options = this.options;
				// 计算宽度	
				$(".oas-guide-wrap").width($(".oas-guide-wrap").width());
				$content.find('.oas-guide-step').width($(".oas-guide-wrap").width());
				$content.width($content.find('.oas-guide-step').size()*$(".oas-guide-wrap").width());
				$content.css({
					"margin-left":function(){return -[$(".oas-guide-wrap").width()*(options.current-1)]}
				});	
				$(window).resize(function(e) {
                   // 计算宽度	
				   $(".oas-guide-wrap").width($(".oas-guide-wrap").width());
					$content.find('.oas-guide-step').width($(".oas-guide-wrap").width());
					$content.width($content.find('.oas-guide-step').size()*$(".oas-guide-wrap").width());
					$content.css({
						"margin-left":function(){return -[$(".oas-guide-wrap").width()*(options.current-1)]}
					}); 
                });			
			}
			
			return ;
			
		},
		
		/**创建dom节点**/
		_createDom: function() {
			var html = this.tpl(this._renderData),
				$el = this.$el;
			$el.addClass('oas-guide');
			$el.empty().append(html);
			
		},
		_bindEvent: function(){
			//重新计算
			$self=this;
			$(window).resize(function(e) {
				$(".oas-guide-wrap").width($(".oas-guide-wrap").width());
				if(!$self.$content){
					$self._findGuideContent();
				 }
				//this.current;//序列号不会改变
				var _current=$self.current-1;
				$self.$content.css({
					"margin-left":function(){return -[$(".oas-guide-wrap").width()*_current]},
					"transition":"none 0 ease 0"
				});
				$self._preRenderData();
            });			
		},
		// 组件对外方法集
		invoke: {
			// 返回上一步
			 prev:function(){
				 if(!this.$content){
				 	this._findGuideContent();
				 }
				 if(1<this.current && this.current<= this.data.length){					 
					this.current--;//分步的序列号的改变
					this._preRenderData();
					var _current=this.current-1;
					this.$content.css({
						"margin-left":function(){return -[$(".oas-guide-wrap").width()*_current];},
						"transition":function(){return "margin-left ease-in .8s";}
					});	
				 } 
			},
			
			//直接完成
			finish:function(){
				if(!this.$content){
				 	this._findGuideContent();
				 }
				 if(this.current < this.data.length ){
					var _current=this.data.length-1;
					this.current=this.data.length;//分步的序列号的改变
					this.$content.css({
						"margin-left":function(){return -[$(".oas-guide-wrap").width()*_current]},
						"transition":function(){return "none 0 ease 0";}
						
					});	
					this._preRenderData();
				 } 
			},
			
			
			//下一步
			next:function(){
				if(!this.$content){
				 	this._findGuideContent();
				 }
				 if(this.current < this.data.length){
					this.current++;//分步的序列号的改变
					var _current=this.current-1;//序列号减一个，是把当前的前面几个移走					
					this.$content.css({
						"margin-left":function(){return -[$(".oas-guide-wrap").width()*_current]},
						"transition":function(){return "margin-left ease-in .8s";}
					});
					this._preRenderData();	
				 } 
			}
			
			
			
		}
	});

})();
;
(function() {
	$.oasUiFactory("oasDateSelect", {
		options: {
			/*组件默认显示的日期，默认为空*/
            defaultDate : null,
			/*组件显示格式。为字符串。表示显示的组件格式*/
			format : 'yyyy-MM-dd',
			/*可选日期范围,为日期类型数组[startDate,endDate]*/
			range : [new Date(1900,0,1), new Date()],
			dateChange:function(){},
			chooseNull:function(){

			}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl =  '<div class="date-select-item {{if yearInit.yearShow == false}}hide{{/if}} date-select-year ue-state-default">' + 
							'<input type="text" class="item-input year-input" maxlength="4" value="{{yearInit.value}}">' +
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>年</span>' +
						'</div>' +
						'<div class="date-select-item {{if monthInit.monthShow == false}}hide{{/if}} date-select-month ue-state-default">' +
							'<input type="text" class="item-input month-input" maxlength="2" max="12" value="{{monthInit.value}}">' + 
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>月</span>' +
						'</div>' +
						'<div class="date-select-item {{if dayInit.dayShow == false}}hide{{/if}} date-select-day ue-state-default">' +
							'<input type="text" class="item-input day-input" maxlength="2" max="31" value="{{dayInit.value}}">' +
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>日</span>' +
						'</div>' +
						'<div class="date-select-item {{if hourInit.hourShow == false}}hide{{/if}} date-select-hour ue-state-default">' +
							'<input type="text" class="item-input hour-input" maxlength="2" max="23" value="{{hourInit.value}}">' +
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>时</span>' +
						'</div>' +
						'<div class="date-select-item {{if minInit.minShow == false}}hide{{/if}} date-select-minute ue-state-default">' +
							'<input type="text" class="item-input min-input" maxlength="2" max="59" value="{{minInit.value}}">' +
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>分</span>' +
						'</div>' +
						'<div class="date-select-item {{if secInit.secShow == false}}hide{{/if}} date-select-second ue-state-default">' +
							'<input type="text" class="item-input sec-input" maxlength="2" max="59" value="{{secInit.value}}">' + 
							'<a href="javascript:;" class="oasicon oasicon-arrow-down"></a>' +
							'<span>秒</span>' +
						'</div>' +
						'<div class="datelist oas-scroll-webkit">' +
							'<div class="datelist-content">' +
								'<ul>' +
								'</ul>' +
							'</div>' +
						'</div>';
			return template.compile(tpl);
		},

		// 事件
		events: {
			'keydown .item-input': function(self, evt) {
				var keyCode = event.keyCode || evt.keyCode;
				//只捕获数字键和10以内的案件，增加对最大长度限制。
				if(!((keyCode > 47 && keyCode < 58 && !event.shiftKey) || (keyCode > 95 && keyCode < 106) || (keyCode < 10))){
					evt.preventDefault();
					return false;
				}
			},
			'click .date-select-item>.oasicon-arrow-down': function(self, evt) {
				if($(self).parent().hasClass("ue-state-disable")){
					return false;
				}
				$(self).prev().trigger('focus');
				//return false;
			},
			'click .date-select-item': function(self, evt) {
				var startYear = this.options.range[0] != null ? this.options.range[0].getFullYear() : null;
				var endYear = this.options.range[1] != null ? this.options.range[1].getFullYear() : null;
				if(!$(self).hasClass('ue-state-disable')){
					/*年*/
					if($(self).hasClass('date-select-year')){
						startYear = startYear != null? startYear : 1900;
						endYear = endYear != null? endYear : new Date().getFullYear();
						this._createDataList(startYear, endYear, $(self).find('input'), true);
					}
					// 尝试
					else {
						var index = $(self).index();
						var t = this._getSelectValue();
						var range = this._getAvailableRange(this,index,[t.year,t.month,t.day,t.hour!==null?t.hour:0,t.minute!==null?t.minute:0,t.second!==null?t.second:0]);
						this._createDataList(range[0],range[1],$(self).find('input'), true);
					}
					// end
					// /*月*/
					// if($(self).hasClass('date-select-month')){
					// 	var startMonth = 1, endMonth = 12;
					// 	if(this.options.range){
					// 		var startDate = this.options.range[0], endDate = this.options.range[1], tempYear = this.$el.find('.date-select-year').find('input').val();
					// 		if(tempYear == startDate.getFullYear()){
					// 			startMonth = startDate.getMonth() + 1;
					// 		}
					// 		if(tempYear == endDate.getFullYear()){
					// 			endMonth = endDate.getMonth() + 1;
					// 		}
					// 	}
					// 	this._createDataList(startMonth, endMonth, $(self).find('input'), true);
					// }
					// /*日*/
					// if($(self).hasClass('date-select-day')){
					// 	var tempYear = this.$el.find('.date-select-year').find('input').val(),
					// 		tempMonth = this.$el.find('.date-select-month').find('input').val(),
					// 		days = this._getMonthDays(parseInt(tempYear,10),parseInt(tempMonth,10)),
					// 		startDay = 1;
					// 	if(this.options.range){
					// 		var startDate = this.options.range[0],endDate = this.options.range[1];
					// 		if(tempYear == startDate.getFullYear() && tempMonth == (startDate.getMonth() +1)){
					// 			startDay = startDate.getDate();
					// 		}
					// 		if(tempYear == endDate.getFullYear() && tempMonth == (endDate.getMonth() +1)){
					// 			days = endDate.getDate();
					// 		}
					// 	}
					// 	this._createDataList(startDay, days, $(self).find('input'), true);
					// }
					// /*时*/
					// if($(self).hasClass('date-select-hour')){
					// 	var tempYear = this.$el.find('.date-select-year').find('input').val(),
					// 		tempMonth = this.$el.find('.date-select-month').find('input').val(),
					// 		tempDay = this.$el.find('.date-select-day').find('input').val(),
					// 		startHour = 0,
					// 		endHour = 23;
					// 	if(this.options.range) {
					// 		var startDate = this.options.range[0],endDate = this.options.range[1];
					// 		if(tempYear == startDate.getFullYear() && tempMonth == (startDate.getMonth() +1) && tempDay == startDate.getDate()){
					// 			startHour = startDate.getHours();
					// 		}
					// 		if(tempYear == endDate.getFullYear() && tempMonth == (endDate.getMonth() +1) && tempDay == endDate.getDate()){
					// 			endHour = endDate.getHours();
					// 		}
					// 	}
					// 	this._createDataList(startHour, endHour, $(self).find('input'), true);
					// }
					// 分
					// if($(self).hasClass('date-select-minute')){
					// 	var tempYear = this.$el.find('.date-select-year').find('input').val(),
					// 		tempMonth = this.$el.find('.date-select-month').find('input').val(),
					// 		tempDay = this.$el.find('.date-select-day').find('input').val(),
					// 		tempHour = this.$el.find('.date-select-hour').find('input').val(),
					// 		startMinute = 0,
					// 		endMinute = 59;
					// 	if(this.options.range) {
					// 		var startDate = this.options.range[0],endDate = this.options.range[1];
					// 		if(tempYear == startDate.getFullYear() && tempMonth == (startDate.getMonth() +1) && tempDay == startDate.getDate() && tempHour == startDate.getHours()){
					// 			startMinute = startDate.getMinutes();
					// 		}
					// 		if(tempYear == endDate.getFullYear() && tempMonth == (endDate.getMonth() +1) && tempDay == endDate.getDate() && tempHour == endDate.getHours()){
					// 			endMinute = endDate.getMinutes();
					// 		}
					// 	}
					// 	this._createDataList(startMinute, endMinute, $(self).find('input'), true);
					// }
					// /*秒*/
					// if($(self).hasClass('date-select-second')){
					// 	var tempYear = this.$el.find('.date-select-year').find('input').val(),
					// 		tempMonth = this.$el.find('.date-select-month').find('input').val(),
					// 		tempDay = this.$el.find('.date-select-day').find('input').val(),
					// 		tempHour = this.$el.find('.date-select-hour').find('input').val(),
					// 		tempMinute = this.$el.find('.date-select-minute').find('input').val(),
					// 		startSecond = 0,
					// 		endSecond = 59;
					// 	if(this.options.range) {
					// 		var startDate = this.options.range[0],endDate = this.options.range[1];
					// 		if(tempYear == startDate.getFullYear() && tempMonth == (startDate.getMonth() +1) && tempDay == startDate.getDate() && tempHour == startDate.getHours() && tempMinute == startDate.getMinutes()){
					// 			startSecond = startDate.getMinutes();
					// 		}
					// 		if(tempYear == endDate.getFullYear() && tempMonth == (endDate.getMonth() +1) && tempDay == endDate.getDate() && tempHour == endDate.getHours() && tempMinute == endDate.getMinutes()){
					// 			endSecond = endDate.getMinutes();
					// 		}
					// 	}
					// 	this._createDataList(startSecond, endSecond, $(self).find('input'), true);
					// }
					$(self).find('input').focus().select();
				}
				return false;
			},
			'change .item-input': function(self, evt) {
				if(this.options.dateChange){
					this.options.dateChange(this._getselectedDateInvoke());
				}
				return false;
			},
			'keyup .year-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var startYear = this.options.range[0] != null ? this.options.range[0].getFullYear() : null;
				var endYear = this.options.range[1] != null ? this.options.range[1].getFullYear() : null;
				var val = $(self).val(), startTemp = '0000', endTemp = '9999';
				var start = val + startTemp.substr(0,4-val.length);
				var end = parseInt(val + endTemp.substr(0,4-val.length),10);
				start = parseInt(startYear > start ? startYear : start, 10);
				end = parseInt(endYear < end && endYear != null ? endYear : end ,10);
				this._createDataList(start, end, $(self), false);
				return false;
			},
			'keyup .month-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var val = $(self).val(), startTemp = '00', endTemp = '99';
				var start = parseInt(val + startTemp.substr(0,2-val.length),10);
				var end = parseInt(val + endTemp.substr(0,2-val.length),10);
				end = 12 < end ? 12 : end;
				this._createDataList(start, end, $(self), false, val);
				return false;
			},
			'keyup .day-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var val = $(self).val(), startTemp = '00', endTemp = '99';
				var days = this._getMonthDays(parseInt(this.$el.find('.date-select-year').find('input').val(),10),parseInt(this.$el.find('.date-select-month').find('input').val(),10));
				var start = parseInt(val + startTemp.substr(0,2-val.length),10);
				var end = parseInt(val + endTemp.substr(0,2-val.length),10);
				end = days < end ? days : end;
				this._createDataList(start, end, $(self), false, val);	
				return false;
			},
			'keyup .hour-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var val = $(self).val(), startTemp = '00', endTemp = '99';
				var start = parseInt(val + startTemp.substr(0,2-val.length),10);
				var end = parseInt(val + endTemp.substr(0,2-val.length),10);
				end = 23 < end ? 23 : end;
				this._createDataList(start, end, $(self), false, val);
				return false;
			},
			'keyup .min-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var val = $(self).val(), startTemp = '00', endTemp = '99';
				var start = parseInt(val + startTemp.substr(0,2-val.length),10);
				var end = parseInt(val + endTemp.substr(0,2-val.length),10);
				end = 59 < end ? 59 : end;
				this._createDataList(start, end, $(self) ,false,val);
				return false;	
			},
			'keyup .sec-input': function(self, evt) {
				if(!this._checkNum($(self))){
					return;
				}
				var val = $(self).val(), startTemp = '00', endTemp = '99';
				var start = parseInt(val + startTemp.substr(0,2-val.length),10);
				var end = parseInt(val + endTemp.substr(0,2-val.length),10);
				end = 59 < end ? 59 : end;
				this._createDataList(start, end, $(self),false,val);
				return false;
			},
			'blur .year-input': function(self, evt) {
				this._yearChage(self);
				return false;
			},
			'blur .month-input': function(self, evt) {
				this._monthChage(self);
				return false;
			},
			'blur .item-input': function(self, evt) {
				var $el = this.$el;
				var val = parseInt($(self).val());
				var max = parseInt($(self).attr('max'));
				if(val > max){
					$(self).val(max);
				}
				//如果该输入框是空则需要将后面的输入框置为disable
				var index = $(self).parent().index();
				if($(self).val() == ''){//如果选中的是空白，则比其更精确的都为空白
					$el.find('.date-select-item:gt('+index+')').find('input').val('').attr('disabled',true).end().addClass('ue-state-disable');
				}else{
					$el.find('.date-select-item:eq('+(index+1)+')').removeClass('ue-state-disable').find('input').removeAttr('disabled');
				}
				
				if($(self).val().length == 1){
					$(self).val(this._formatNum($(self).val()));
				}
				return false;
			}
		},
		_checkNum:function($input){
			var value = $input.val();
			var result = true;
			if(value != ''){
				var num = parseInt(value,10);
				if('number' != typeof num || isNaN(num)){
					$input.val('');
					result = false;
				}else{
					$input.val(num);
				}
			}else{
				result = false;
			}
			return result;
		},
		_createDescList : function(start, end){
			var li = '';
			for(var i = end;i >= start;i--){
				var tempLi = '<li class="ue-state-default">'+this._formatNum(i)+'</li>';
				li += tempLi;
			}
			return li;
		},
		/*生成联想下拉，并显示*/
		_createDataList : function(start, end, $input, flag, expand){
			var self = this,
				$el = this.$el,
				$dataList = this.$el.find(".datelist"),
			 	li = '';
				//记录触发联想下拉的输入框对象
				this.target = $input;
				$dataList.width($input.outerWidth()-2);
			if($input.parent().hasClass('date-select-year')){
				li = this._createDescList(start, end);
			}else{
				for(;start<=end;start++){
					var tempLi = '<li class="ue-state-default">'+this._formatNum(start)+'</li>';
					li += tempLi;
				}
			}
			
			$dataList.find('ul').empty().append(li);
			if(parseInt(expand) >= 0 && parseInt(expand)<10 && expand.indexOf('0') != 0){
				//插入自己的节点.
				var expand = expand.length == 1? this._formatNum(expand) : expand;
				if($dataList.find('ul li').size()){
					$('<li class="ue-state-default">'+expand+'</li>').insertBefore($dataList.find('ul li').eq(0));
				}else{
					$dataList.find('ul').append('<li class="ue-state-default">'+expand+'</li>');
				}
				
			}
			if($input.val() ==  '' || flag){
				$('<li class="ue-state-default"></li>').insertBefore($dataList.find('ul li').eq(0));
			}
			//显示联想下拉部分
			if($dataList.find('li').size()){
				//this._showDataList($input);
			}else{
				$el.find('.datelist').oasOverlay("hide");
			}

			$dataList.find('li').click(function(){
				self.target.val($(this).text());
				var selectedTimeObj = self._getSelectValue(),
					selectedDate,
					isInRange = false;
				if(selectedTimeObj.year!==null) {
					selectedDate = new Date(selectedTimeObj.year,selectedTimeObj.month-1,selectedTimeObj.day===null?1:selectedTimeObj.day,selectedTimeObj.hour,selectedTimeObj.minute,selectedTimeObj.second);
					isInRange = selectedDate>=self.options.range[0]&&selectedDate<=self.options.range[1];
				}
				$el.find('.datelist').oasOverlay("hide");
				/**年月改变需要检验日是否合格**/
				if($input.parent().hasClass('date-select-year')){
					self._yearChage($input);
				}
				if($input.parent().hasClass('date-select-month')){
					self._monthChage($input);
				}
				var index = self.target.parents('.date-select-item').index();
				if($(this).text() == ''){//如果选中的是空白，则比其更精确的都为空白	
					$el.find('.date-select-item:gt('+index+')').find('input').val('').attr('disabled',true).end().addClass('ue-state-disable');
					self.options.chooseNull();
				}else if(!isInRange) {
					$el.find('.date-select-item:gt('+(index+1)+')').find('input').val('').attr('disabled',true).end().addClass('ue-state-disable');
					$el.find('.date-select-item:eq('+(index+1)+')').removeClass('ue-state-disable').find('input').val('').removeAttr('disabled');
				}else{
					$el.find('.date-select-item:eq('+(index+1)+')').removeClass('ue-state-disable').find('input').removeAttr('disabled');
				}
				var chage = self.options.dateChange;
				if(chage){
					chage(self._getselectedDateInvoke());
				}
			});
			$dataList.find('li').mouseover(function(){
				$dataList.find('li').removeClass("current");
				$(this).addClass("current");
			});
		},

		_showDataList:function(){
			var options = this.options,
				thisEl = this.$el,
				elem = thisEl.find('.item-input');
			thisEl.find('.datelist').oasOverlay({
				target: $(elem),
				triggerType: 'focus',
				isNeedAnimation:false,
				align: {
					// 基准定位元素，默认为当前的可视区域
					baseElement: $(elem),
					// 基准定位元素的定位点，默认为左上角
					baseXY: [0, 30]
				},
				beforeOpen:function($el){
					$('body').find(".oas-date-select > .datelist").hide();
					thisEl.find('.datelist').width($($el.target).outerWidth() -2);
				}
			});
		},
		/**获取input中的值，如果该有diabled则另作处理**/
		_getInputVal : function($input){
			var val = $input.val();
			if($input.parent().hasClass('uew-dateSelect-month') && val != ''){
				val = parseInt(val,10) - 1;
			}
			if(val == ''){
				val = 0;
				if($input.parent().hasClass('uew-dateSelect-month')){
					val = 0;
				}else if($input.parent().hasClass('uew-dateSelect-day')){
					val = 1;
				}
			}
			return val;
		},
		_getSelectValue:function(){
			var self = this, options = this.options, $el = this.$el;
			var year = $el.find('.date-select-year').find('input').val();
			var month = $el.find('.date-select-month').find('input').val();
			var day = $el.find('.date-select-day').find('input').val();
			var hour = $el.find('.date-select-hour').find('input').val();
			var minute = $el.find('.date-select-minute').find('input').val();
			var second = $el.find('.date-select-second').find('input').val();
			return {
				year:(year != "" && year != "00") ? year : null,
				month:(month != "" && month != "00") ? month : null,
				day:(day != "" && day != "00") ? day : null,
				hour:(hour != "" && hour != "00") ? hour : null,
				minute:(minute != "" && minute != "00") ? minute : null,
				second:(second != "" && second != "00") ? second : null
			}
		},
		/*获取当前选择的日期*/
		getselectedDate : function(){
			var self = this, options = this.options, $el = this.$el;
			var year = this._getInputVal($el.find('.date-select-year').find('input'));
			var month = this._getInputVal($el.find('.date-select-month').find('input'));
			var day = this._getInputVal($el.find('.date-select-day').find('input'));
			var hour = this._getInputVal($el.find('.date-select-hour').find('input'));
			var minute = this._getInputVal($el.find('.date-select-minute').find('input'));
			var second = this._getInputVal($el.find('.date-select-second').find('input'));
			//如果只有时分秒时，使用range的日期作为返回日期。或者使用当前日期
			if($el.find('.date-select-year:hidden').size()){
				if(options.range[1] != null){
					year = options.range[1].getFullYear();
					month = options.range[1].getMonth();
					day = options.range[1].getDate();
				}else{
					year = new Date().getFullYear();
					month = new Date().getMonth();
					day = new Date().getDate();
				}
			}
			/**根据具体情况重新给时分秒赋值**/
			if($el.find('.date-select-hour:hidden').size()){
				hour = 0;
			}
			if($el.find('.date-select-minute:hidden').size()){
				minute = 0;
			}
			if($el.find('.date-select-second:hidden').size()){
				second = 0;
			}
			var date;
			if(year == 0){
				date = null;
			}else{
				year = year == 0 ? new Date().getFullYear() : year;
				month = month == 0 ? new Date().getMonth() + 1 : month;
				day = day == 0 ? new Date().getDate() : day;
				date = new Date(year,month-1,day,hour,minute,second);
			}
			return date;
		},

		_getselectedDateInvoke : function() {
			var self = this,
			    options = this.options, 
			    $el = this.$el,
			    timeArray = ["year","month","day","hour","minute","second"],
			    maxTime = [0,11,31,23,59,59];
			    minTime = [0,0,1,0,0,0];
			    i = 0,
			    timeArrayLen = 6 - $el.find(".date-select-item:hidden").size();
			    temp = 0,
			    year = 0;


		    for(;i<timeArrayLen;i++) {
		    	temp = $el.find('.date-select-' + timeArray[i]).find('input').val();
		    	if(temp==="") {
		    		break;
		    	} else {
		    		temp = temp - 0;
		    	}
		    	if(i===0) {
		    		year = temp;
		    	}
		    	maxTime[i] = temp;
		    	minTime[i] = temp;
		    	if(i===1) {
		    		maxTime[i] = temp-1;
		    		minTime[i] = temp-1;
		    		switch(temp) {
		    			case 1:;
		    			case 3:;
		    			case 5:;
		    			case 7:;
		    			case 8:;
		    			case 10:;
		    			case 12:maxTime[2]=31;break;
		    			case 4:;
		    			case 6:;
		    			case 9:;
		    			case 11:maxTime[2]=30;break;
		    			case 2:if(year%400===0||(year%4===0&&year%100!==0)) {
		    				maxTime[2]=29;
		    				break;
		    			} else {
		    				maxTime[2]=28;
		    				break;
		    			}
		    		}
		    	}			    	
		    }
		    maxTime = new Date(maxTime[0],maxTime[1],maxTime[2],maxTime[3],maxTime[4],maxTime[5]);
	    	minTime = new Date(minTime[0],minTime[1],minTime[2],minTime[3],minTime[4],minTime[5]);
    		maxTime = maxTime > options.range[1]?options.range[1]:maxTime;
    		minTime = minTime > options.range[0]?minTime:options.range[0];
    		if(i===0) {
    			return null;
    		}
	    	if(maxTime.getTime()==minTime.getTime()||i===timeArrayLen) {
	    		return maxTime;
	    	} else {
	    		return [minTime,maxTime];
	    	}
		},

		/*
		 * desc 年改变了，检查日是否合格，主要为2月
		 * @param self 失去焦点的input框
		 */
		_yearChage : function(self){
			var range = this.options.range, $el = this.$el;
			//如果范围不在range内则改为今年。
			var year = parseInt($(self).val());
			if(year < range[0].getFullYear() || year > range[1].getFullYear()){
				year = new Date().getFullYear();
				$(self).val(year);
			}
			var month = $el.find('.date-select-month').find('input').val();
			var days = this._getMonthDays(year, month);
			if(days < parseInt($el.find('.date-select-day').find('input').val())){
				$el.find('.date-select-day').find('input').val(days);
			}
			$el.find('.date-select-day').find('input').attr('max',days);	
		},
		/*
		 * 获取某一个月的天数
		 * param (int) year
		 * param (int) month
		 */
		_getMonthDays : function(year,month){
			var days;
			if((month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0)){
				days = 31;
			}else if(month != 2){
				days = 30;
			}else{
				//如果是闰年
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
			}
            return days;
		},
		/*
		 * desc 月改变了，检查日是否合格
		 * @param self 失去焦点的input框
		 */
		_monthChage : function(self){
			var year = this.$el.find('.date-select-year').find('input').val();
			var month = $(self).val();
			var days = this._getMonthDays(year, month);
			if(days < parseInt(this.$el.find('.date-select-day').find('input').val())){
				this.$el.find('.date-select-day').find('input').val(days);
			}
			this.$el.find('.date-select-day').find('input').attr('max',days);	
		},
		/**回填时间到选择框中**/
		_fillToInput : function(date){
			var $el = this.$el, self = this;	
			$el.find('.date-select-item').each(function(){
				
				if($(this).find('input').val() == ''){
					$(this).removeClass('ue-state-disable').find('input').removeAttr('disabled');
					if($(this).hasClass('date-select-year')){
						$(this).find('input').val(self._formatNum(date.getFullYear()));
					}
					if($(this).hasClass('date-select-month')){
						$(this).find('input').val(self._formatNum(date.getMonth()+1));
					}
					if($(this).hasClass('date-select-day')){
						$(this).find('input').val(self._formatNum(date.getDate()));
					}
					if($(this).hasClass('date-select-hour')){
						$(this).find('input').val(self._formatNum(date.getHours()));
					}
					if($(this).hasClass('date-select-minute')){
						$(this).find('input').val(self._formatNum(date.getMinutes()));
					}
					if($(this).hasClass('date-select-second')){
						$(this).find('input').val(self._formatNum(date.getSeconds()));
					}
				}
			});
		},
		_create: function() {
			this._preRenderData();
			this._adjustDisable();
			this._showDataList();
		},

		_getAvailableRange : function(self,propNum,targetDate) {
			var startDate = self.options.range[0],
				startFlag = true,
				endDate = self.options.range[1],
				endFlag = true,
				propArr = ["getFullYear","getTrueMonth","getDate","getHours","getMinutes","getSeconds"],
				maxTime = [0,12,31,23,59,59],
			    minTime = [0,1,1,0,0,0],
				startRange,endRange,i=0;
			if(!Date.prototype.getTrueMonth) {
				Date.prototype.getTrueMonth = function() {
					return (this.getMonth() + 1);
				}
			}
			for(;i<propNum;i++) {
				if(!((startDate[propArr[i]]())==targetDate[i] && startFlag)) {
					startFlag = false;
				}
				if(!((endDate[propArr[i]]())==targetDate[i] && endFlag)) {
					endFlag = false;
				}
			}
			startRange = startFlag?startDate[propArr[i]]():minTime[i];
			endRange = endFlag?endDate[propArr[i]]():maxTime[i];
			return [startRange,endRange];
		},
		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				yearInit = {},
				monthInit = {},
				dayInit = {},
				hourInit = {},
				minInit = {},
				secInit = {},
				format = options.format,
				defaultDate = options.defaultDate,
				_renderData = {};

			yearInit.yearShow = format.indexOf('yyyy') == -1 ? false : true;
			monthInit.monthShow = format.indexOf('MM') == -1 ? false : true;
			dayInit.dayShow = format.indexOf('dd') == -1 ? false : true;
			hourInit.hourShow = format.indexOf('hh') == -1 ? false : true;
			minInit.minShow = format.indexOf('mm') == -1 ? false : true;
			secInit.secShow = format.indexOf('ss') == -1 ? false : true;

			if(defaultDate){
				yearInit.value = defaultDate.getFullYear();
				monthInit.value = this._formatNum(defaultDate.getMonth()+1);
				dayInit.value = this._formatNum(defaultDate.getDate());
				minInit.value = this._formatNum(defaultDate.getMinutes());
				hourInit.value = this._formatNum(defaultDate.getHours());
				secInit.value = this._formatNum(defaultDate.getSeconds());
			}
			this._renderData = _renderData = {
				yearInit : yearInit,
				monthInit : monthInit,
				dayInit : dayInit,
				hourInit : hourInit,
				minInit : minInit,
				secInit : secInit
			};
			this._createDom();
		},
		/**格式化数字，为不足10的不齐两位**/
		_formatNum : function(num){
			return (num>=10)? num : '0'+ num;
		},

		_adjustDisable:function(){
			var $inputs = this.$el.find('input[type=text]'),
				$el = this.$el;

			for(var i = 0,len = $inputs.size();i < len;i++){
				if($inputs.eq(i).val() == ''){
					$el.find('input:gt('+i+')').attr('disabled',true).parents('.date-select-item').addClass('ue-state-disable');
					break;
				}
			}
		},

		/**创建dom节点**/
		_createDom: function() {
			var html = this.tpl(this._renderData),
				$el = this.$el;
			$el.addClass('oas-date-select clearfix');
			$el.empty().append(html);
		},

		// 组件对外方法集
		invoke: {
			getselectedDate:function(){
				return this._getselectedDateInvoke();
			},
			setDefaultDate:function(date){
				this.options.defaultDate = date;
				this._preRenderData();
				this._adjustDisable();
				this._showDataList();
			},
			getDefaultDate:function(){
				return this.options.defaultDate;
			},
			getSelectedValue:function(){
				return this._getSelectValue();
			}
		}
	});

	
})();
;
(function() {
	$.oasUiFactory("oasRightClick", {
		options: {
			openInstance: null,
			//内部取消右击的节点
			filterElements: [],
			hasOpacity:false,
			//@param callBack {function}
			onOpen: function() {}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {

		},

		// 事件
		events: {

		},

		_create: function() {
			this.isShow = true;
			if (this.options.openInstance) {
				this.options.openInstance.addClass("oas-rightclick");
			}
			this._cancelDefaultClick();
			this._bindRightClick();
			this._filterElements();

		},
		_cancelDefaultClick: function() {
			document.oncontextmenu = function() {
				return false;
			}
		},
		_filterElements: function() {
			var elements = this.options.filterElements,
				i = 0,
				len = elements.length;

			for(; i < len; i++){
				this.$el.find(elements[i]).mousedown(function(e){
					return false;
				});
			}
		},
		_bindRightClick: function() {
			var self = this,
				options = this.options;
			$(this.$el).mousedown(function(e) {
				if (e.button == 2) {
					$(".oas-rightclick").hide();
					if(!options.openInstance){
						return;
					}
					if (options.openInstance.data('oasOperate')) {
						options.openInstance.oasOperate('position', {
							left: e.clientX + $(window).scrollLeft(),
							top: e.clientY + $(window).scrollTop()
						});
					} else {
						options.openInstance.css({
							left: e.clientX,
							top: e.clientY
						});
					}
					self._emit('onOpen',[$(e.target)]);
					options.openInstance.oasOperate("target", $(this));
					if (self.isShow) {
						options.openInstance.show();
						if(self.options.hasOpacity){
							$('body').find('.oas-mask-opacity').show();
						}
					} else {
						options.openInstance.hide();
						if(self.options.hasOpacity){
							$('body').find('.oas-mask-opacity').hide();
						}
					}
					return false;
				}
			});
		},
		// 组件对外方法集
		invoke: {
			isShow: function(isShow) {
				this.isShow = isShow == true ? true : false;
			}
		}
	});
})();
/**
 * 联想输入组件
 * @ author hge
 * @ create time 2014.08.25
 * @ version  1.0.0
 */
(function() {
	// 组件名和文件名请统一
	$.oasUiFactory("oasThinkInput", {
		options: {
			/**联想输入面板数据集合:[{label:"",num:"",value:""},{label:"",num:"",value:""}]**/
			data: [],
			//是否是分批取数据
			isBatch:true,
			paginInfo:{type:'simple',current:1,total:0,pageItems:8,onPageNoChage:function(){}},
			//当数据总条数为零时
			blankDom:'',
			//无数据时的提示
			nodataTip:"没有找到类似数据",
			//打开回调函数
			onOpen: function() {},
			//点击input回调
			onClick:function(){},
			//关闭回调函数
			onClose: function() {},
			//input框数据改变时触发的回调
			onChange: function() {},
			//选中下拉面板值回调
			onSelect:function(){

			}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl = '<div class="oas-associate-panel">' +
					  	'<div class="associate-arrow top-arrow">' +
					  	'</div>' +
						'<div class="associate-bd oas-scroll-webkit {{if isNoData == true}}no-bottom{{/if}}">'+
							'{{if isNoData == true}}' +
								'{{if blankDom == ""}}' +
									'<div class="ellipsis">' +
										'{{nodataTip}}'+
						             '</div>' +   
						        '{{else}}' +  
						        	'<div class="ellipsis">' +
										'{{nodataTip}}'+
						            '</div>' +   
						        	'<div class="no-data-style">' +
										'{{#blankDom}}' +
						            '</div>' +  
						        '{{/if}}' + 
							'{{else}}' +
								'<div>' +
									'<ul>' +
										'{{each list}}' +
											'<li class="associate-item" index={{$index}} title="{{$value.title}}">{{#$value.label}}</li>' +
										'{{/each}}' +
									'</ul>' +
								'</div>' +
							'{{/if}}' +
						'</div>' +
						'<div class="associate-ft {{if isNoData == true}}hide{{/if}}">' +
							'<div class="associate-pagin" data-oas-pagin-type="simple" data-oaspagin-skipto="false">' +
							'</div>' +
					    '</div>' +
					    '<div class="associate-loading">' +
							'<div class="oas-local-loading clearfx">' +
							     '<span class="loading"></span>' +
							     '<span class="loading-text">数据读取中....</span>' +
							'</div>' +
					    '</div>' +
					  '</div>';

            var bodyTplA =  '<div class="associate-arrow top-arrow">' +
						  	'</div>' +
							'<div class="associate-bd oas-scroll-webkit {{if isNoData == true}}no-bottom{{/if}}">'+
								'{{if isNoData == true}}' +
									'{{if blankDom == ""}}' +
										'<div class="ellipsis">' +
										    '{{nodataTip}}'+
							            '</div>' +    
							        '{{else}}' +  
							        	'<div class="ellipsis">' +
											'{{nodataTip}}'+
							            '</div>' +   
							        	'<div class="no-data-style">' +
											'{{#blankDom}}' +
							            '</div>' +  
							        '{{/if}}' + 
								'{{else}}' +
									'<div>' +
										'<ul>' +
											'{{each list}}' +
												'<li class="associate-item" index={{$index}} title="{{$value.title}}">{{#$value.label}}</li>' +
											'{{/each}}' +
										'</ul>' +
									'</div>' +
								'{{/if}}' +
							'</div>' +
							'<div class="associate-ft {{if isNoData == true}}hide{{/if}}">' +
								'<div class="associate-pagin" data-oas-pagin-type="simple" data-oaspagin-skipto="false">' +
								'</div>' +
						    '</div>' +
							'<div class="associate-loading">' +
								'<div class="oas-local-loading clearfx">' +
								     '<span class="loading"></span>' +
								     '<span class="loading-text">数据读取中....</span>' +
								'</div>' +
						    '</div>';
			var bodyTplB =  '<div>' +
								'<ul>' +
									'{{each list}}' +
										'<li class="associate-item" index={{$index}} title="{{$value.title}}">{{#$value.label}}</li>' +
									'{{/each}}' +
								'</ul>' +
							'</div>';
			var bodyTplC =  '{{if blankDom == ""}}' +
								'<div class="ellipsis">' +
									'{{nodataTip}}'+
					             '</div>' +   
					        '{{else}}' +  
					        	'<div class="ellipsis">' +
									'{{nodataTip}}'+
					            '</div>' +   
					        	'<div class="no-data-style">' +
									'{{#blankDom}}' +
					            '</div>' +  
					        '{{/if}}';
			this.bodyTplA = template.compile(bodyTplA);
			this.bodyTplB = template.compile(bodyTplB);
			this.bodyTplC = template.compile(bodyTplC);
			return template.compile(tpl);
		},

		// 事件
		events: {
			'click .associate-item': function(self, evt) {
				//this.triggerEle.val(this.currentList[this.hoverIndex].label);
				this.triggerEle.val(this.$el.find('.associate-item[index="' + this.hoverIndex + '"]').text());
				this.inputValue = this.triggerEle.val();
				this._emit('onSelect',[this.currentList[this.hoverIndex]]);
				this.$el.oasOverlay("hide");
				this.isOpen = false;
				return false;
			},
			'mouseover .associate-item': function(self, evt) {
				this.$el.find(".associate-item").removeClass("current");
				$(self).addClass("current");
				this.hoverIndex = $(self).attr("index");
				return false;
			},
			'mouseout .associate-item': function(self, evt) {
				this.$el.find(".associate-item").removeClass("current");
				$(self).removeClass("current");
				this.hoverIndex = -1;
				return false;
			}
		},

		_create: function() {
			var self = this;
			this.triggerEle = this.$el;
			if(!this.triggerEle.attr('placeholder') && !this.options.isBatch){
			this.triggerEle.attr('placeholder','多项匹配以空格隔开');
			}
			this.hoverIndex = -1;
			this.currentList = [];
			this.inputValue = "";
			this.isShowFt = true;
			oasThinkInput.allThinkInput.push(this);
			this.isOpen = false;
			if(!this.options.isBatch){
				this.allData = this.options.data;
				this._dealData();
				this._dataByIndex(parseInt(this.options.paginInfo.current,10)-1);
				this.options.paginInfo.onPageNoChage = function(current,pageItems){
					self.options.paginInfo.current = current;
					self._onPageNoChage(current-1,pageItems);
				};	
			}else{
				this.currentList = this.options.data;
				var loop = this.options.paginInfo.pageItems - this.currentList.length;
				if(this.currentList.length < this.options.paginInfo.pageItems){
					for (var i = 0; i < loop; i++) {
						this.currentList.push({label:"",num:"",value:""});
					};
				}
			}
			this._preRenderData();
			this._createDom();
			this._bindChange();
			this._showAndHide();
			this.triggerEle.live('click', function() {
					self._emit('onClick');
				});
		},
		//键盘向上键操作
		_liUp: function(e) {
			var self = this,
				currentIndex = self.hoverIndex,
				$el = this.$el;
			if (self.isOpen && currentIndex != -1 && currentIndex > 0) {
				e.preventDefault();
				self.hoverIndex--;
				this.$el.find('.associate-item[index="' + self.hoverIndex + '"]').trigger('mouseover');
			}
			return false;
		},
		//键盘向下键操作
		_liDown: function(e) {
			var self = this,
				currentIndex = self.hoverIndex,
				$el = this.$el;
			if (self.isOpen && currentIndex < self.currentTrueLength - 1) {
				e.preventDefault();
				self.hoverIndex++;
				this.$el.find('.associate-item[index="' + self.hoverIndex + '"]').trigger('mouseover');
			}
			return false;
		},
		_click:function(){
			if(this.hoverIndex != -1){
				this.triggerEle.blur();
				this.$el.find('.associate-item[index="' + this.hoverIndex + '"]').trigger('click');
			}
		},
		//内部翻页
		_onPageNoChage:function(current,pageItems){
			this._dataByIndex(current);
			//this._renderCurrentPage();
			this._tidyMatchLabel();
			var _renderData = {
				list: this.matchList
			};
			var html = this.bodyTplB(_renderData);
			this.$el.find(".associate-bd").html("").html(html);
		},
		//数据一次性传入的整合
		_dealData: function(){
			var data = this.options.data,
				total = data.length,
				pageItems = this.options.paginInfo.pageItems,
				pageNum = Math.ceil(total/pageItems),
				lastPageNum = total % pageItems,
				dataByIndex = {};
			if(total > 0){
				if(pageNum == 1){
					dataByIndex["0"] = data;
				}else{
					for (var i = 0; i < pageNum-1; i++) {
						var onePageData = [];
						for (var j = 0; j < pageItems; j++) {
							onePageData.push(data[i * pageItems + j]);
						}
						dataByIndex[i +""] = onePageData;
					}
					var lastPageData = [];
					lastPageNum = lastPageNum == 0 ? pageItems : lastPageNum;
					for (var k = 0; k < lastPageNum; k++) {
						lastPageData.push(data[(pageNum -1) * pageItems + k]);
					}
					dataByIndex[pageNum -1 + ""] = lastPageData;
				}
				
			}else if(total == 0){
				dataByIndex["0"] = [];
			}
			this.pageNum = pageNum;
			this.dataByIndex = dataByIndex;
			this.total = total;
		},
		_dataByIndex:function(pageIndex){
			this.currentList = this.dataByIndex[pageIndex + ""];
			if(!this.currentList){
				this.currentList = this.dataByIndex["0"];
			}
			this.currentTrueLength = this.currentList.length;
			if(this.currentList.length < this.options.paginInfo.pageItems){
				for(var i = 0; i < this.options.paginInfo.pageItems - this.currentList.length;i++){
					this.currentList.push({label:"",num:"",value:""});
				}
			}
			this.currentPage = pageIndex;
		},
		//给input增加onchange事件
		_bindChange:function(){
			var self = this,
			inputValue = "";
			this.triggerEle.keyup(function(){
				inputValue = $(this).val();
				if(self.inputValue === inputValue){
					return;
				}
				self.inputValue = inputValue;
				self._emit('onChange',[$(this).val()]);
				if(!self.options.isBatch){
					var data = self.allData;
					var tempArray = [];
					for(var i = 0; i < data.length; i++){
						//if(data[i].label.indexOf(inputValue)>= 0||data[i].label.toUpperCase().indexOf(inputValue.toUpperCase())>=0||data[i].value.indexOf(inputValue.toUpperCase())>= 0 ||(data[i].num && data[i].num.indexOf(inputValue)>=0)){
							//tempArray.push(data[i]);
						//}
						if(self.matchInput(inputValue,data[i].label) || (data[i].num && self.matchInput(inputValue,data[i].num)) || self.matchInput(inputValue,data[i].value)){
							tempArray.push(data[i]);
						}
					}
					self._iCall("data",[tempArray,tempArray.length,true]);
				}
			});
		},
		//匹配
		matchInput:function(input,data){
			/**var tempt=0;
			input = input.replace(/\s/g, '');
			for(i=0;i<input.length;i++){
			  for(var j=0;j<data.length;j++){
			      if(input[i].indexOf(data.charAt(j)) != -1){
			         tempt++;
			         break;
			      }
			   }
			}
			if(tempt==input.length){
			    return true;
			}else{
			  	return false;
			}**/
			var search = input.split(' ');
			var findNum = 0;
			for (var i = 0; i < search.length; i++) {
				var index = data.toLowerCase().indexOf(search[i].toLowerCase());
				if(index != -1){
					findNum ++;
				}
			}
			if(findNum == search.length){
			    return true;
			}else{
			  	return false;
			}
		},
		_dealIsBatch:function(){
			var self = this,
			inputValue = this.triggerEle.val();
			if(!this.options.isBatch){
				var data = this.allData;
				var tempArray = [];
				for(var i = 0; i < data.length; i++){
					//if(data[i].label.indexOf(inputValue)>= 0||data[i].label.toUpperCase().indexOf(inputValue.toUpperCase())>=0||data[i].value.indexOf(inputValue.toUpperCase())>= 0 ||(data[i].num && data[i].num.indexOf(inputValue)>=0)){
						//tempArray.push(data[i]);
					//}
					if(self.matchInput(inputValue,data[i].label) || (data[i].num && self.matchInput(inputValue,data[i].num)) || self.matchInput(inputValue,data[i].value)){
						tempArray.push(data[i]);
					}
				}
				this._iCall("data",[tempArray,tempArray.length,true,true]);
			}
		},
		// 准备所有需要渲染的数据
		_preRenderData: function(isfromTarget) {
			var options = this.options,
				isNoData = false,
				blankDom = options.blankDom,
				_renderData = {};
			isNoData = (options.paginInfo.total == 0 || options.data.length == 0) ? true:false;
			this._tidyMatchLabel();
			this._renderData = _renderData = {
				list:this.matchList,
				isNoData:isNoData,
				blankDom:blankDom,
				nodataTip:options.nodataTip
			};
			this.isNoData = isNoData;
			this.isShowFt = !this.isNoData;
		},
		//渲染某页数据
		_renderCurrentPage:function(){
			var $el = this.$el;
			$el.find('.associate-item').text("");
			$.each(this.currentList, function(index, obj) {
				$el.find('.associate-item[index=' + index + ']').text(obj.label);
			});
		},
		_tidyMatchLabel:function(){
			var inputValue = this.triggerEle.val();
			var list = this.currentList;
			var matchList = $.extend(true, [], list);
			/**
			var matchList = [];
			for (var i = 0; i < list.length; i++) {
				var obj = {};
				for(var key in list[i]){
					obj[key] = list[i][key];
				}
				matchList.push(obj);
			}
			**/
			var search = inputValue.split(' ');
			for (var i = 0; i < matchList.length; i++) {
				for (var j = 0; j < search.length; j++) {
					var index = matchList[i].label.toUpperCase().indexOf(search[j].toUpperCase());
				if(index != -1){
						matchList[i].label = matchList[i].label.substring(0,index) + "<span>" + matchList[i].label.substring(index,index + search[j].length) + "</span>" + matchList[i].label.substring(index + search[j].length,matchList[i].label.length);
				}
				};
			}
			this.matchList = matchList;
		},
		/**联想面板显隐**/
		_showAndHide: function() {
			var options = this.options,
				triggerEle = this.triggerEle,
				thisEl = this.$el,
				self = this,
				thisHeight = thisEl.outerHeight(), //联想面板高度
				thisWidth = thisEl.outerWidth(), //联想面板宽度
				win = {
					height: $(window).height(),
					width: $(window).width(),
					scrollTop: $(window).scrollTop(),
					scrollLeft: $(window).scrollLeft()
				};

			thisEl.oasOverlay({
				target: $(triggerEle),
				triggerType: 'click',
				isNeedAnimation:false,
				align: {
					// 基准定位元素，默认为当前的可视区域
					baseElement: $(triggerEle),
					// 基准定位元素的定位点，默认为左上角
					baseXY: [0, 0]
				},
				onOpen: function($el) {
					self.isOpen = true;
				},
				onClose:function($el){
					self.isOpen = false;
				},
				beforeOpen: function($el) {
					self._dealIsBatch();
					thisHeight = thisEl.outerHeight(); //联想面板高度
					thisWidth = thisEl.outerWidth(); //联想面板宽度
					win = {
						height: $(window).height(),
						width: $(window).width(),
						scrollTop: $(window).scrollTop(),
						scrollLeft: $(window).scrollLeft()
					};
					$('body').find(".oas-associate-panel").hide();
					var baseXY,
						left = 0,
						top = 0,
						thisTop = triggerEle.offset().top, //top值
						thisLeft = triggerEle.offset().left; //left值

						thisEl.find(".associate-arrow").removeClass("bottom-arrow opposite");
						if(win.height + win.scrollTop - thisTop - triggerEle.outerHeight() - 1 - thisHeight < 0){
							top = -thisHeight - 1;
							thisEl.find(".associate-arrow").addClass("bottom-arrow");
							if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
								left = triggerEle.outerWidth() - thisWidth;
								thisEl.find(".associate-arrow").addClass("opposite");
							}else{
								left = 0;
							}
						}else{
							top = triggerEle.outerHeight() + 1;
							if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
								left = triggerEle.outerWidth() - thisWidth;
								thisEl.find(".associate-arrow").addClass("opposite");
							}else{
								left = 0;
							}
						}
						if(win.height + win.scrollTop - thisTop - triggerEle.outerHeight() -1 - thisHeight < 0 && thisTop -1 - thisHeight < 0){
							thisEl.find(".associate-arrow").removeClass("bottom-arrow opposite");
							top = triggerEle.outerHeight() + 1;
							if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
								left = triggerEle.outerWidth() - thisWidth;
								thisEl.find(".associate-arrow").addClass("opposite");
							}else{
								left = 0;
							}
						}
						baseXY = [left, top];
					this.oasOverlay('align', {
						baseXY: baseXY
					});
				}
			});
		},
		//调整面板
		_adjustPanel:function(){
			var options = this.options,
				triggerEle = this.triggerEle,
				thisEl = this.$el,
				self = this,
				thisHeight = thisEl.outerHeight(), //联想面板高度
				thisWidth = thisEl.outerWidth(), //联想面板宽度
				win = {
					height: $(window).height(),
					width: $(window).width(),
					scrollTop: $(window).scrollTop(),
					scrollLeft: $(window).scrollLeft()
				};

			var baseXY,
				left = 0,
				top = 0,
				thisTop = triggerEle.offset().top, //top值
				thisLeft = triggerEle.offset().left; //left值

				thisEl.find(".associate-arrow").removeClass("bottom-arrow opposite");
				if(win.height + win.scrollTop - thisTop - triggerEle.outerHeight() -1 - thisHeight < 0){
					top = -thisHeight - 1;
					thisEl.find(".associate-arrow").addClass("bottom-arrow");
					if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
						left = triggerEle.outerWidth() - thisWidth;
						thisEl.find(".associate-arrow").addClass("opposite");
					}else{
						left = 0;
					}
				}else{
					top = triggerEle.outerHeight() + 1;
					if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
						left = triggerEle.outerWidth() - thisWidth;
						thisEl.find(".associate-arrow").addClass("opposite");
					}else{
						left = 0;
					}
				}
				if(win.height + win.scrollTop - thisTop - triggerEle.outerHeight() -1 - thisHeight < 0 && thisTop -1 - thisHeight < 0){
					top = triggerEle.outerHeight() + 1;
					if(win.width + win.scrollLeft - thisLeft - thisWidth < 0){
						left = triggerEle.outerWidth() - thisWidth;
						thisEl.find(".associate-arrow").addClass("opposite");
					}else{
						left = 0;
					}
				}
				baseXY = [left, top];
				thisEl.oasOverlay('align', {
					baseXY: baseXY
				});
		},
		/**创建dom节点**/
		_createDom: function(type) {
			var html;
			if(type == 1){
				html = this.bodyTplA(this._renderData);
				this.$el.append(html);
				this.$el.find(".associate-pagin").oasPagin(this.options.paginInfo);
			}else{
				html = this.tpl(this._renderData);
				this.$el = $(html);
				$("body").append(this.$el);
				//this.triggerEle.after(this.$el);
				this.$el.find(".associate-pagin").oasPagin(this.options.paginInfo);
			}
			if(this.options.paginInfo.total == 0){
				if(this.options.blankDom == ""){
					this.$el.find(".associate-bd").height(21);
				}
				
			}else{
				this.$el.find(".associate-bd").height(this.options.paginInfo.pageItems * 30);
			}
			if(this.options.paginInfo.total <= this.options.paginInfo.pageItems){
				this.$el.find(".associate-ft").hide();
				this.isShowFt = false;
				this.$el.find(".associate-bd").addClass("no-bottom");
			}
			//调整宽度
			var tempWidth = this.triggerEle.width() < 200 ? 200 : this.triggerEle.width();
			this.$el.width(tempWidth);
			this.$el.find(".associate-bd").width(tempWidth);
			if(type == 1){
				this._adjustPanel();
			}
		},

		// 组件对外方法集
		invoke: {
			/*
			 * 修改当前页数据
			 * @param dataList 新的数据集合
			 * @param total 总的数据条数
			 */
			data: function(dataList,total,isInner,isfromTarget) {
				var self = this,
					$el = this.$el;
				this.options.data = dataList;
				if(total != undefined){
					this.options.paginInfo.total = total;
					this.$el.find(".associate-pagin").oasPagin('total',total);
				}
				if(this.options.isBatch){
					this.hoverIndex = -1;
					this.currentList = this.options.data;
					if(this.isNoData && this.currentList.length > 0){
						this.currentList = this.options.data;
						var loop = this.options.paginInfo.pageItems - this.currentList.length;
						if(this.currentList.length < this.options.paginInfo.pageItems){
							for (var i = 0; i < loop; i++) {
								this.currentList.push({label:"",num:"",value:""});
							};
						}
						var _renderData = {
							list: this.currentList
						};
						var html = this.bodyTplB(_renderData);
						$el.find(".associate-bd").html("").html(html);
						$el.find(".associate-bd").removeClass("no-bottom");
						$el.find(".associate-bd").height(this.options.paginInfo.pageItems * 30);
					}else if(!this.isNoData && this.currentList.length > 0){
					$el.find('.associate-item').text("");
					$.each(this.options.data, function(index, obj) {
						var item = $el.find('.associate-item[index=' + index + ']');
							item.html(obj.label).attr('title',obj.title);
					});
					}else if(this.currentList.length == 0){
						var _renderData = {
							isNoData:true,
							blankDom:this.options.blankDom,
							nodataTip:this.options.nodataTip
						};
						var html = this.bodyTplC(_renderData);
						$el.find(".associate-bd").html("").html(html);
						$el.find(".associate-bd").addClass("no-bottom");
						if(this.options.blankDom == ""){
							$el.find(".associate-bd").height(21);
						}
					}
					if(this.options.paginInfo.total <= this.options.paginInfo.pageItems){
						this.$el.find(".associate-ft").hide();
						this.isShowFt = false;
						this.$el.find(".associate-bd").addClass("no-bottom");
					}else{
						this.$el.find(".associate-ft").show();
						this.isShowFt = true;
						this.$el.find(".associate-bd").removeClass("no-bottom");
					}
				}else{
					if(!isInner){
						this.allData = this.options.data;
					}
					this.$el.html("");
					this.hoverIndex = -1;
					this.currentList = [];
					var tempPageNum = this.pageNum;
					this._dealData();
					if(tempPageNum != this.pageNum){
						this._dataByIndex(0);
						this.options.paginInfo.current = 1;
						
					}else{
						this._dataByIndex(parseInt(this.options.paginInfo.current,10)-1);
					}
					this._preRenderData(isfromTarget);
					this._createDom(1);
					if(!isInner){
						this.$el.oasOverlay("showSelf");
					}
				}
				this.isNoData = (this.options.paginInfo.total == 0 || this.options.data.length == 0) ? true:false;
				this.isShowFt = !this.isNoData;
			},
			//设置联想输入当前页
			current:function(num){
				var current = parseInt(num,10);
				this.options.paginInfo.current = current;
				this.$el.find(".associate-pagin").oasPagin("current",current);
			},
			//打开面板
			open: function() {
				this._emit('onOpen');
				this.$el.oasOverlay("show");
			},
			//关闭面板
			close: function() {
				this.isOpen = false;
				this._emit('onClose');
				this.$el.oasOverlay("hide");
			},
			//显示加载
			showLoading:function(){
				this.$el.find(".associate-bd").hide();
				this.$el.find(".associate-ft").hide();
				this.$el.find(".associate-loading").show();
			},
			//关闭加载
			hideLoading:function(){
				this.$el.find(".associate-bd").show();
				if(this.isShowFt){
					this.$el.find(".associate-ft").show();
				}else{
					this.$el.find(".associate-ft").hide();
				}
				this.$el.find(".associate-loading").hide();
			}
		}
	});
	oasThinkInput = {};
	oasThinkInput.allThinkInput = [];
	$(document).off('keydown.oasThinkInput');
	$(document).on('keydown.oasThinkInput', function(event){
			switch (event.keyCode) {
			case 38:
				$.each(oasThinkInput.allThinkInput, function(i, item) {
					if (item.isOpen) {
						item._liUp(event);
					}
				});
				break;
			case 40:
				$.each(oasThinkInput.allThinkInput, function(i, item) {
					if (item.isOpen) {
						item._liDown(event);
					}
				});
				break;
			case 13:
				$.each(oasThinkInput.allThinkInput, function(i, item) {
					if (item.isOpen) {
						item._click(event);
					}
				});
				break;
			default:
				break;
			}
	});

})();
;
(function() {
	/**
	 * 提供一个元素内的子元素可以拖动排序。
	 * @ author rbai
	 * @ create time 2014.11.4
	 * @ version  1.0.0
	 */
	$.oasUiFactory("oasSortable", {
		options: {

			// 拖拽动画持续时长
			duration: 200,

			// 可拖动排序的节点
			childClass: 'oas-sortable-item',

			// 拖动过程中的占位元素class
			placeholderClass: 'oas-sortable-placeholder',

			/*
			 * type 为exchange时为交换位置，
			 *      为 shove 时为排挤。
			 */
			type: "exchange",


			onChange: function() {

			}
		},


		_create: function() {
			var $el = this.$el,
				options = this.options,
				self = this;

			this.$sortable = $el.addClass('oas-sortable').children();

			this._initPosition();

			this.$sortable.oasDrag({
				proxy: true,
				onEnd: function($el, now, dis) {

					var position,
						left = now[0],
						top = now[1],
						
						$sortable = self.$el.children().not('.oas-drag-proxy'),
						nowIndex = $sortable.index($el),
						exchangeIndex = $sortable.length,
						positionArr = [];

					var $exchangeElement = self._getIndexofExchange(0.3);

					if ( !! $exchangeElement) {
						exchangeIndex = $sortable.index($exchangeElement);
					}

					if (exchangeIndex !== nowIndex && exchangeIndex !== $sortable.length) {} else if (exchangeIndex !== nowIndex && exchangeIndex === $sortable.length && nowIndex !== $sortable.length - 1) {
						exchangeIndex--;
					}

					if (exchangeIndex === nowIndex || exchangeIndex > $sortable.length - 1) {
						return false;
					}

					// 默认为exchange
					if (options.type === "exchange") {
						self._exchangeItemPosition($sortable, nowIndex, exchangeIndex);
					} else {
						self._shoveItemPosition($sortable, nowIndex, exchangeIndex);
					}
					self.$el.find('.oas-drag-proxy').remove();
				}
			});
		},

		_initPosition: function() {
			this.$el.height(this.$el.height());
			this.$sortable.each(function() {
				var position = $(this).position(),
					left = position.left,
					top = position.top;

				$(this).data("position", position);
			});

			this.$sortable.each(function() {
				var position = $(this).data("position"),
					left = position.left,
					top = position.top;

				$(this).css({
					"position": "absolute",
					"left": left,
					"top": top
				});
			});
		},

		_shoveItemPosition: function($sortable, nowIndex, exchangeIndex) {

			var length = $sortable.length,
				options = this.options;


			if (nowIndex < exchangeIndex) {

				for (var i = nowIndex + 1; i < exchangeIndex + 1; i++) {
					(function(i) {
						var position = $sortable.eq(i - 1).data('position');

						$sortable.eq(i).animate({
							'left': position.left,
							'top': position.top
						}, options.duration, function() {
							$sortable.eq(i).data('position', position);
						});
					})(i);
				}
				$sortable.eq(exchangeIndex).after($sortable.eq(nowIndex));
				$sortable.eq(nowIndex).css({
					'left': $sortable.eq(exchangeIndex).data('position').left,
					'top': $sortable.eq(exchangeIndex).data('position').top
				}).data('position', $sortable.eq(exchangeIndex).data('position'));
			} else if (nowIndex > exchangeIndex) {
				for (var i = nowIndex-1; i > exchangeIndex - 1; i--) {
					(function(i) {
						var position = $sortable.eq(i + 1).data('position');

						$sortable.eq(i).animate({
							'left': position.left,
							'top': position.top
						}, options.duration, function() {
							$sortable.eq(i).data('position', position);
						});
					})(i);
				}

				$sortable.eq(exchangeIndex).before($sortable.eq(nowIndex));
				$sortable.eq(nowIndex).css({
					'left': $sortable.eq(exchangeIndex).data('position').left,
					'top': $sortable.eq(exchangeIndex).data('position').top
				}).data('position', $sortable.eq(exchangeIndex).data('position'));


			}

			this._emit('onChange');
		},

		_exchangeItemPosition: function($sortable, nowIndex, exchangeIndex) {

			var options = this.options,
				$nowElement = $sortable.eq(nowIndex),
				$exchange = $sortable.eq(exchangeIndex),
				nowPosition = $nowElement.data('position'),
				nowLeft = nowPosition.left,
				nowTop = nowPosition.top;

			$nowElement.animate({
				left: $exchange.data('position').left,
				top: $exchange.data('position').top
			}, options.duration);
			$nowElement.data("position", $exchange.data('position'));
			$exchange.after($nowElement);

			$exchange.animate({
				left: nowLeft,
				top: nowTop
			}, options.duration);

			$exchange.data("position", nowPosition);
			if (Math.abs(nowIndex - exchangeIndex) > 1) {

				$sortable.eq(nowIndex + 1).before($exchange);
			}

			this._emit('onChange');


		},

		_getIndexofExchange: function(ratio) {

			var app_width = this.$sortable.width(),

				app_height = this.$sortable.height(),

				app_area = app_width * app_height,
				$temp;
			$.each(this.$sortable, function() {
				var overlapPosition = Position.overlapArea($('.oas-drag-proxy'), $(this));
				(overlapPosition[0] == 0) && (overlapPosition[0] = 1);
				(overlapPosition[1] == 0) && (overlapPosition[1] = 1);

				if (overlapPosition[0] * overlapPosition[1] / app_area > ratio) {
					$temp = $(this);
					return false;
				}
			});

			return $temp;
		},

		// 对外方法集
		invoke: {
			destory: function() {
				this.$el.removeData().off(this.uiName).css("height","auto");
			}
		}
	});


	// auto init
	$(window).on("load", function() {
		$('[oassortable]').oasSortable();
	});

})();
/**
 * 时间线组件
 * @ author rbai
 * @ create time 2014.08.25
 * @ version  1.0.0
 */
(function() {
	// 组件名和文件名请统一
	$.oasUiFactory("oasTimeline", {
		options: {
			// 所有的date参数均为javascript的Date类型。
			/*
             * [{
				day: {
	               date: Date类型,
	    			各类汇总信息
				},
				// 这一天的时间段
				times: [{
					date: Date类型,
					html: ''
				},{
					date: Date类型,
					html: ''
				}]
             }]
			 */

			// 数据源, 时间必须为可以直接转换为Date类型的，或者为Date对象，组件不负责排序，请确保传递过来的数据顺序。
			/*｛
			 	"startIdentity":{"identity":"_无尽_空虚","miniType":"1280001"},
			 	"startOtherIdentitys":[
			 		{"miniType":"1020008","value":"18099231308"},
			 		{"miniType":"1030036","value":"1123930543"},
			 		{"miniType":"1030001","value":"2746197688"}
			 	],
			 	"endIdentity":{"identity":"ALan丶n1ce","miniType":"1280001"},
			 	"endOtherIdentitys":[
			 		{"miniType":"1020008","value":"18099231308"},
			 		{"miniType":"1030036","value":"1123930543"},
			 		{"miniType":"1030001","value":"2746197688"}
			 	],
			 	"spreadDate":"2012-12-09 01:31:18",
			 	"spreadTypeDesc":"收藏（保存）"
			 }*/
			data: null,
			// 日期字段，必须为每一条数据对象的其中一条字段。
			dateField: null,
			dayRender: null,
			timePointRender: null,
			//滚动加载更多
			isScrollLoading: false

		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {
			var tpl = '{{if !$data[0].isAppend}}<div class="oas-timeline-point-line"></div>{{/if}}' +
				'{{each $data as value_data}}' +
				'<div class="oas-timeline-day">' +
				'<div class="oas-timeline-year">' +
				'<div class="oas-timeline-point">{{value_data["year"]}}<p>年</p></div>' +
			// '<div class="oas-timeline-point-bg"></div>'+								
			'</div>' +

			'{{each value_data.days}}' +
				'<div class="oas-timeline-days">' +
				'<div class="oas-timeline-day-head">' +
				'<div class="oas-timeline-day-wrap">' +
				'<div class="oas-timeline-day-content">{{#$value["content"]}}</div>' +
				'</div>' +

			'<div class="oas-timeline-point">{{dateFormat_year($value["date"],"day")}}<p>{{dateFormat_year($value["date"],"month")}}月</p></div>' +
			// '<div class="oas-timeline-point-bg"></div>'+
			'</div>' +

			'{{each $value.time}}' +
				'<div class="oas-timeline-day-body">' +
				'<div class="oas-timeline-time">' +
				'<div class="oas-timeline-time-wrap">' +
				'<div class="oas-timeline-time-content">' +
				'<div class="oas-timeline-time-css">' +
				'{{#$value["content"]}}' +
				'</div>' +
				'</div>' +
				'</div>' +
				'<div class="oas-timeline-point">{{$value["date"]}}</div>' +
			// '<div class="oas-timeline-point-bg"></div>'+
			'</div>' +
				'</div>' +
				'{{/each}}' +
				'</div>' +
				'{{/each}}' +
				'</div>' +
				'{{/each}}'

			//为什么一定要放在这里否则第二次加载的会报错		
			this._addHelps_year();
			this._addHelps_day();
			return template.compile(tpl);
		},
		// 年份已经存在,月日插入(插入的模板)
		_template_insert: function() {
			var tpl_insert = '{{each $data[0].days}}' +
				'<div class="oas-timeline-days">' +
				'<div class="oas-timeline-day-head">' +
				'<div class="oas-timeline-day-wrap">' +
				'<div class="oas-timeline-day-content">{{#$value["content"]}}</div>' +
				'</div>' +

			'<div class="oas-timeline-point">{{dateFormat_year($value["date"],"day")}}<p>{{dateFormat_year($value["date"],"month")}}月</p></div>' +
			// '<div class="oas-timeline-point-bg"></div>'+
			'</div>' +

			'{{each $value.time}}' +
				'<div class="oas-timeline-day-body">' +
				'<div class="oas-timeline-time">' +
				'<div class="oas-timeline-time-wrap">' +
				'<div class="oas-timeline-time-content">' +
				'<div class="oas-timeline-time-css">' +
				'{{#$value["content"]}}' +
				'</div>' +
				'</div>' +
				'</div>' +
				'<div class="oas-timeline-point">{{$value["date"]}}</div>' +
			// '<div class="oas-timeline-point-bg"></div>'+
			'</div>' +
				'</div>' +
				'{{/each}}' +
				'</div>' +
				'{{/each}}';
			return template.compile(tpl_insert);
		},
		// 年月份已经存在,日插入(插入的模板)
		_template_insert_day: function() {
			var tpl_insert = '{{each $data[0].days[0].time}}' +
				'<div class="oas-timeline-day-body">' +
				'<div class="oas-timeline-time">' +
				'<div class="oas-timeline-time-wrap">' +
				'<div class="oas-timeline-time-content">' +
				'<div class="oas-timeline-time-css">' +
				'{{#$value["content"]}}' +
				'</div>' +
				'</div>' +
				'</div>' +
				'<div class="oas-timeline-point">{{$value["date"]}}</div>' +
			// '<div class="oas-timeline-point-bg"></div>'+
			'</div>' +
				'</div>' +
				'{{/each}}';
			return template.compile(tpl_insert);
		},
		// 事件
		events: {
			"click .chooseData": function(self) {
				if ($(self).parents(".oas-timeline-day-content").find(".oasicon.oasicon-fold").size()) {
					$(self).find(".oasicon.oasicon-fold").removeClass("oasicon-fold").addClass("oasicon-unfold");
					$(self).parents(".oas-timeline-days").find(".oas-timeline-day-body").hide();
					$(self).parents(".oas-timeline-days").find(".oas-timeline-day-head.current").removeClass("current");
				} else {
					$(self).find(".oasicon.oasicon-unfold").removeClass("oasicon-unfold").addClass("oasicon-fold");
					$(self).parents(".oas-timeline-days").find(".oas-timeline-day-body").show();
					$(self).parents(".oas-timeline-day-head").addClass("current");
				}
			}
		},

		_create: function() {
			this.$el.addClass('oas-timeline');
			var newData = this._adjustData();
			var newDatas = this._preRenderData(newData);
			this._createDom(newDatas);

		},

		// 适应数据，将数据转换为{'2012-12-09': [{},{}] }
		_adjustData: function() {
			var options = this.options,
				datas = options.data,
				dateField = options.dateField,
				i = 0,
				len = datas.length,
				currentDate, //具体的年份
				newDatas = {},
				specificDay,
				newYears = [];
			for (; i < len; i++) { //options.data数组的大小
				var data = datas[i];
				// 如果没有日期字段，则退出。
				if (!data[dateField]) throw new Error('数据的第[' + i + ']元素，没有（' + dateField + '）日期字段');
				// 判断当前日期是否是日期类型
				if (oasis.type.isString(data[dateField])) {
					currentDate = data[dateField].split(' ')[0].split('-')[0]; //提取年份
					specificDay = data[dateField].split(' ')[0];
				} else {
					currentDate = data[dateField].toJSON().split('T')[0].split('-')[0];
					specificDay = data[dateField].toJSON().split('T')[0];
				}
				//具体的年的信息---->newDatas这个对象中，有以年划分的各个对象
				if (currentDate in newDatas) { //在newDatas的每一条数据里面提取不同的年份件
					newDatas[currentDate].push(data); //按年把每一条信息分类	
				} else {
					newDatas[currentDate] = [data];
				}

			}
			var newTotal = {
				datas: []
			};
			for (var k in newDatas) {
				var yearObj = {
					year: k,
					days: []
				};
				var tmp = newDatas[k]; //以年划分过的数据块
				var dataObject = {};
				for (var j = 0; j < tmp.length; j++) {
					//截取新数据里面的时间
					theDate = tmp[j][dateField].split(' ')[0].toString();
					startIndex = theDate.indexOf('-');
					sDate = theDate.substring(startIndex + 1);
					yDate = theDate.substring(0, startIndex);
					//具体的一天的信息---->要以年为基础
					if (sDate in dataObject) {
						dataObject[sDate].push(tmp[j]); //按具体年月日把每一条信息分类
					} else {
						dataObject[sDate] = [tmp[j]];
					}
				}
				//具体时分秒
				var newSecond = {};
				for (var h in dataObject) {
					var dateObj = {
						day: h,
						times: []
					};
					var tmpSecond = dataObject[h];
					for (var a = 0; a < tmpSecond.length; a++) { //具体的一天中有2条数据
						var dataSecond = {};
						secondDate = tmpSecond[a][dateField].split(' ')[1];
						dataSecond.date = secondDate;
						dataSecond.data = tmpSecond[a];
						dateObj.times.push(dataSecond);
					}
					yearObj.days.push(dateObj);
				}
				newTotal.datas.push(yearObj);
			}
			return newTotal;
		},

		// 准备渲染数据
		_preRenderData: function(datas) {
			var options = this.options,
				dayRender = options.dayRender,
				timePointRender = options.timePointRender,
				newDatas = [];
			for (var i = 0; i < datas.datas.length; i++) {
				var data = {
					year: datas.datas[i].year,
					days: [],
					//用于标识数据是否是追加的
					isAppend: this.options.isScrollLoading ? true : false
				}
				for (var j = 0; j < datas.datas[i].days.length; j++) {
					var dataObj = [];
					//具体一天的数据先拿出来
					for (var k = 0; k < datas.datas[i].days[j].times.length; k++) {
						dataObj.push({
							date: datas.datas[i].days[j].times[k].date,
							content: timePointRender(datas.datas[i].days[j].times[k].data)
						});
					}
					//数据放到相同的一天中				
					data.days.push({
						date: datas.datas[i].days[j].day,
						content: dayRender(datas.datas[i].days[j]),
						time: dataObj
					});
				}
				newDatas.push(data);
			}
			return newDatas;
		},

		// 创建节点
		_createDom: function(datas, flag) {

			var $el = this.$el,
				html = this.tpl(datas);
			if (this.options.isScrollLoading) {
				$el.append(html);
			} else {
				$el.empty().append(html);
			}

		},
		_dateFormat_year: function(date, format) {
			if (!date) return;
			var date_array = date.split('-');
			if (format === "month") {
				return date_array[0];
			} else if (format === "day") {
				return date_array[1];
			} else {
				return "";
			}
		},
		_dateFormat_day: function(date) {
			if (!date) return;
			var index = date.lastIndexOf(":");
			return date.substring(0, index);
		},

		_addHelps_year: function() {
			template.helper('dateFormat_year', this._dateFormat_year);
		},
		_addHelps_day: function() {
			template.helper('dateFormat_day', this._dateFormat_day);
		},
		//三种不同的数据动态加载方式
		_append_html: function(data, type) {
			if (!data) return;
			switch (type) {
				//新的年月
				case 1:
					{
						this.options.data = data;
						var newData = this._adjustData();
						var newDatas = this._preRenderData(newData);
						this._createDom(newDatas);
						break;
					}
			    //旧的年旧的月日
				case 2:
					{
						this.options.data = data;
						var _tpl = this._template_insert_day();
						var newData = this._adjustData();
						var newDatas = this._preRenderData(newData);
						var $el = this.$el,
							html = _tpl(newDatas);
						$el.find(".oas-timeline-days:last").append(html);
						break;
					}
				//旧的年新的月日
				case 3:
					{
						this.options.data = data;
						var _tpl = this._template_insert();
						var newData = this._adjustData();
						var newDatas = this._preRenderData(newData);
						var $el = this.$el,
							html = _tpl(newDatas);
						$el.find(".oas-timeline-day:last").append(html);
						break;
					}
				default:
					break;
			}

		},

		// 组件对外方法集
		invoke: {

			/*
			 * 重新设置数据
			 */
			data: function(data) {

				if (!data) return;
				this.options.data = data;
				var newData = this._adjustData();
				var newDatas = this._preRenderData(newData);
				this._createDom(newDatas);
			},
			//新增动态加载的对外函数
			data_append: function(data_add) {
				if (!data_add) return;
				this.options.isScrollLoading = true;
				//拿到上一次渲染的数据
				var data = this.options.data;
				//拿到上一次渲染的时间点
				var pre_year = data[data.length - 1].spreadDate;
				//拿到当前渲染的时间点
				var now_year = data_add[0].spreadDate;
				//截取过去时间点里的年和月日
				var pre_theDate, pre_startIndex, pre_sDate, pre_yDate;
				pre_theDate = pre_year.split(' ')[0].toString();
				pre_startIndex = pre_theDate.indexOf('-');
				pre_sDate = pre_theDate.substring(pre_startIndex + 1);
				pre_yDate = pre_theDate.substring(0, pre_startIndex);
				// alert("过去=" + pre_yDate);
				//截取当前时间点里的年和月日
				var now_theDate, now_startIndex, now_sDate, now_yDate;
				now_theDate = now_year.split(' ')[0].toString();
				now_startIndex = now_theDate.indexOf('-');
				now_sDate = now_theDate.substring(now_startIndex + 1);
				now_yDate = now_theDate.substring(0, now_startIndex);
				// alert("当前=" + now_yDate);
				//当前年份是接着上一条记录的
				if (pre_yDate == now_yDate) {
					// 对数据进行处理(默认传进来的数据是经过时间排序了)
					var index = -1;
					var Year_index_pre = -1;
					var Month_index_pre = -1;
					for (var i = 0, len = data_add.length; i < len; i++) {
						index = data_add[i].spreadDate.indexOf(pre_yDate);
						if (index != -1) {
							Year_index_pre = i;
						}
					}
					var data_last = data_add.splice(0, Year_index_pre + 1);
					var data_next = data_add;
					for (var i = 0, len = data_last.length; i < len; i++) {
						index = data_last[i].spreadDate.indexOf(pre_sDate);
						if (index != -1) {
							Month_index_pre = i;
						}
					}
					var data_last_month_last = data_last.splice(0, Month_index_pre + 1);
					var data_last_month_next = data_last;

					//旧的年的旧的月分插入
					if (data_last_month_last.length > 0) {
						this._append_html(data_last_month_last, 2)
					}
					//旧的年的新的月分插入
					if (data_last_month_next.length > 0) {
						this._append_html(data_last_month_next, 3);
					}
					//新的年份插入
					if (data_next.length) {
						this._append_html(data_next, 1);
					}
				} else {
					this.options.data = data_add;
					var newData = this._adjustData();
					var newDatas = this._preRenderData(newData);
					this._createDom(newDatas);
				}

			}

		}
	});

})();
(function($) {
if(!document.defaultView || !document.defaultView.getComputedStyle){
    var oldCurCSS = jQuery.curCSS;
    jQuery.curCSS = function(elem, name, force){
        if(name === 'background-position'){
            name = 'backgroundPosition';
        }
        if(name !== 'backgroundPosition' || !elem.currentStyle || elem.currentStyle[ name ]){
            return oldCurCSS.apply(this, arguments);
        }
        var style = elem.style;
        if ( !force && style && style[ name ] ){
            return style[ name ];
        }
        return oldCurCSS(elem, 'backgroundPositionX', force) +' '+ oldCurCSS(elem, 'backgroundPositionY', force);
    };
}

var oldAnim = $.fn.animate;
$.fn.animate = function(prop){
    if('background-position' in prop){
        prop.backgroundPosition = prop['background-position'];
        delete prop['background-position'];
    }
    if('backgroundPosition' in prop){
        prop.backgroundPosition = '('+ prop.backgroundPosition + ')';
    }
    return oldAnim.apply(this, arguments);
};

function toArray(strg){
    strg = strg.replace(/left|top/g,'0px');
    strg = strg.replace(/right|bottom/g,'100%');
    strg = strg.replace(/([0-9\.]+)(\s|\)|$)/g,"$1px$2");
    var res = strg.match(/(-?[0-9\.]+)(px|\%|em|pt)\s(-?[0-9\.]+)(px|\%|em|pt)/);
    return [parseFloat(res[1],10),res[2],parseFloat(res[3],10),res[4]];
}

$.fx.step.backgroundPosition = function(fx) {
    if (!fx.bgPosReady) {
        var start = $.curCSS(fx.elem,'backgroundPosition');

        if(!start){//FF2 no inline-style fallback
            start = '0px 0px';
        }

        start = toArray(start);

        fx.start = [start[0],start[2]];

        var end = toArray(fx.end);
        fx.end = [end[0],end[2]];

        fx.unit = [end[1],end[3]];
        fx.bgPosReady = true;
    }

    var nowPosX = [];
    nowPosX[0] = ((fx.end[0] - fx.start[0]) * fx.pos) + fx.start[0] + fx.unit[0];
    nowPosX[1] = ((fx.end[1] - fx.start[1]) * fx.pos) + fx.start[1] + fx.unit[1];
    fx.elem.style.backgroundPosition = nowPosX[0]+' '+nowPosX[1];
};
})(jQuery);

// 滚动部分的组件的封装
;
(function() {
    /**
     * 数字滚动部分的oasis组件的封装。
     * @ author rbai
     * @ create time 2014.07.20
     * @ version  1.0.0
     */
    $.oasUiFactory("oasNumRoll", {
        options: {
            num:0
        },

        _create: function(){
            // 创建节点
            this.$el.addClass('oas-numroll').append('<span class="oas-numroll-num"></span><input type="hidden" />');

            if(this.options.num !== undefined){
                this._iCall('set', [this.options.num]);
            }
        },

        // 对外方法集
        invoke: {
            set: function(_num){
               // console.log(_num);
                var $el = this.$el,
                    $num = this.$el.find('.oas-numroll-num'),
                    it = $num.find('i'),
                    len = (_num+'').length;

                it.filter(':gt('+ (len-1) +')').remove();
                for(var i=0;i<len;i++){
                    if(it.length<=i){
                        $num.append("<i></i>");
                    }
                    var num = (_num+'').charAt(i);
                    var y = -parseInt(num)*30;
                    var obj = $num.find('i').eq(i);
                    obj.animate({
                        backgroundPosition :'(0 '+String(y)+'px)' 
                        }, 'slow','swing',function(){}
                    );
                }
                $el.find('input').val(_num);
            },

            get: function(){
                return this.$el.find('input').val();
            }
        }
    });
})();
// 滚动部分的组件的封装
;
(function() {
    /**
     * 数字滚动部分的oasis组件的封装。
     * @ author pwang
     * @ create time 2016.04.25
     * @ version  1.0.0
     */
    $.oasUiFactory("oasNumberRoll", {
        options: {
            initNum : 0,
            comma : true,
            digit : false
        },

        _numberTpl : function() {
            var tpl =   '<div class="oas-number oas-{{type}}-number">'+
                            '<div class="oas-{{type}}-number-wrap">'+
                                '<div class="oas-number-text oas-number-content hide">0</div>'+
                                '<ul class="oas-other">'+
                                    '<li class="oas-number-text">0</li>'+
                                    '<li class="oas-number-text">1</li>'+
                                    '<li class="oas-number-text">2</li>'+
                                    '<li class="oas-number-text">3</li>'+
                                    '<li class="oas-number-text">4</li>'+
                                    '<li class="oas-number-text">5</li>'+
                                    '<li class="oas-number-text">6</li>'+
                                    '<li class="oas-number-text">7</li>'+
                                    '<li class="oas-number-text">8</li>'+
                                    '<li class="oas-number-text">9</li>'+
                                    '<li class="oas-number-text">,</li>'+
                                '</ul>'+
                            '</div>'+
                        '</div>';
            return template.compile(tpl);
        },

        _flPointTpl : function() {
            var tpl =   '<div class="oas-number oas-{{type}}-number">'+
                            '<div class="oas-{{type}}-number-wrap">'+
                                '<div class="oas-number-text oas-number-content">.</div>'+
                            '</div>'+
                        '</div>';
            return template.compile(tpl);
        },

        _digitTpl : function() {
            var tpl =   '<div class="oas-number oas-{{type}}-number">'+
                            '<div class="oas-{{type}}-number-wrap">'+
                                '<div class="oas-number-text oas-number-content">{{name}}</div>'+
                            '</div>'+
                        '</div>';
            return template.compile(tpl);
        },

        _domTpl : function() {
            var tpl = '<input class="oas-number-roll-val" type="hidden" value="0">';
            return template.compile(tpl);
        },

        _create: function(){
            var toNum = this.options.initNum - 0;
            this.currentNum = toNum;
            this._createDom();
            if(toNum!==0) {
                this._moveTo(0,toNum);
            }
        },

        _createDom: function() {
            var $el = this.$el,
                domTpl = this._domTpl()(),
                numberTpl = this._numberTpl()({type:"integer"});

            $el.addClass("oas-number-roll");
            $el.append(domTpl);
            $el.find(".oas-number-roll-val").before(numberTpl);
            //获取高度
            this.numHeight = parseInt($el.find(".oas-number-content").css("font-size")) || $el.find(".oas-number-content").height();
        },

        _moveTo : function(start,end) {
            var digit = this.options.digit,
                end = digit?end/(digit.unitNum):end,
                end = digit.formatter?digit.formatter(end):end,
                $itgArr = [].reverse.call(this.$el.find(".oas-integer-number")),
                $flArr = this.$el.find(".oas-float-number"),
                compareObj = this._compare(start,end),
                itgChangeObj = compareObj.itgChangeObj,
                flChangeObj = compareObj.flChangeObj,
                len1 = Math.max($itgArr.length,itgChangeObj.length),
                len2 = Math.max($flArr.length,flChangeObj.length),
                i = 0,j = 0;

                //整数
                for(;i<len1;i++) {
                    (function(index,that) {
                        var $itg = $itgArr.eq(index),
                            changeObj = itgChangeObj[index];

                        if(changeObj.index==undefined){

                        } else if($itg.length==0) {
                            that._mvUnToNum.call(that,undefined,changeObj.to);   
                        } else if(!changeObj.to) {
                            that._mvNumToUn.call(that,changeObj.from,undefined,$itgArr.eq(i));   
                        } else {
                            that._mvNumToNum.call(that,changeObj.from,changeObj.to,$itgArr.eq(i));
                        }

                    })(i,this);
                }
                //小数部分
                if(flChangeObj.length!==0&&flChangeObj[0].to===undefined) {
                    this._deleteAllFl();
                } else if(flChangeObj.length!==0&&$flArr.length===0){
                    this._addFloatPoint();
                }
                for(;j<len2;j++) {
                    (function(index,that) {
                        var $fl = $flArr.eq(index),
                            changeObj = flChangeObj[index],
                            boolA = changeObj.to==undefined?"0":"1",
                            boolB = $fl.length===0?"0":"1",
                            bool = boolA+boolB;

                        switch(bool) {
                            case "00" : {
                                break;
                            };
                            case "01" : {
                                that._mvNumToUn.call(that,changeObj.from,undefined,$fl);
                                break;
                            };
                            case "10" : {
                                that._mvUnToNum.call(that,undefined,changeObj.to,"float");   
                                break;
                            };
                            case "11" : {
                                that._mvNumToNum.call(that,changeObj.from,changeObj.to,$fl);
                                break;
                            };
                        }
                    })(j,this);
                }
                if(digit&&this.$el.find(".oas-digit-number").length===0) {
                    this.$el.find(".oas-number-roll-val").after(this._digitTpl()({
                        type:'digit',
                        name:digit.unit
                    }))
                }
                this._setCurrentNum(end);
        },

        //删除
        _deleteAllFl : function() {
            this.$el.find(".oas-float-number").remove();
            this.$el.find(".oas-fl-point-number").remove();
        },

        //增加小数点
        _addFloatPoint:function() {
            var flPointTpl = this._flPointTpl()({type:"fl-point"})
            this.$el.find(".oas-number-roll-val").before(flPointTpl);
        },

        _mvNumToNum : function(from,to,$dom) {
            var fromNum = from - 0,
                toNum = 0-to,
                numHeight = this.numHeight;
            if(from===to) {
                $dom.find(".oas-number-content").removeClass("hide");
                return;
            }
            $dom.find(".oas-other").removeClass("hide");
            $dom.find(".oas-number-content").addClass("hide");
            $dom.find(".oas-other").animate({
                top : this.numHeight*toNum
            },function() {
                $dom.find(".oas-number-content").text(-toNum);
                $dom.find(".oas-number-content").removeClass("hide");
                $dom.find(".oas-other").addClass("hide");
            });
        },

        _mvUnToNum : function(from,to,type) {
            var $el = this.$el,
                type =  type || "integer",
                $numberTpl = $(this._numberTpl()({type:type}));
            
            if(type==="integer") {
                $el.find(".oas-number").eq(0).before($numberTpl);
            } else {
                $el.find(".oas-number-roll-val").before($numberTpl);
            }
            $el.find(".oas-other").removeClass("hide");
            if(to!==",") {
                var toNum = 0-to;
                $numberTpl.find(".oas-other").animate({
                    top : this.numHeight*toNum
                },function() {
                    $numberTpl.find(".oas-number-content").text(-toNum||to);
                    $numberTpl.find(".oas-number-content").removeClass("hide");
                    $el.find(".oas-other").addClass("hide");
                })
            } else {
                $numberTpl.find(".oas-other").css("top",-this.numHeight*10);
                $numberTpl.find(".oas-number-content").text(-toNum||to);
                $numberTpl.find(".oas-number-content").removeClass("hide");
                $el.find(".oas-other").addClass("hide");
            }
        },

        _mvNumToUn : function(from,to,$dom) {
            $dom.remove();
        },

        _compare : function(start,end) {
            var startObj = this._numberToObj(start),
                startObjFlArr = startObj.flArr,
                startObjItgArr = startObj.itgArr,
                endObj = this._numberToObj(end),
                endObjFlArr = endObj.flArr,
                endObjItgArr = endObj.itgArr,
                i = 0,j = 0,intChangeArr = [],flChangeArr = [];

            //比较整数部分和小数部分
            var len1 = Math.max(startObjItgArr.length,endObjItgArr.length);
            var len2 = Math.max(startObjFlArr.length,endObjFlArr.length);
            var itgChangeObj = this._getCompareObj(startObjItgArr,endObjItgArr,len1);
            var flChangeObj = this._getCompareObj(startObjFlArr,endObjFlArr,len2);
            return {
                itgChangeObj : itgChangeObj,
                flChangeObj : flChangeObj
            }
        },

        _getCompareObj : function(startObjArr,endObjArr,length) {
            var i = 0,
                changeArr = [];
            for(;i<length;i++) {
                if(i===startObjArr) {
                    for(;i<endObjArr.lenth;i++) {
                        changeArr.push({
                            index:i,
                            from:undefined,
                            to:endObjItgArr[i]
                        });
                    }
                    break;
                }
                if(i===endObjArr) {
                    for(;i<startObjArr.lenth;i++) {
                        changeArr.push({
                            index:i,
                            from:startObjArr[i],
                            to:undefined
                        });
                    }
                    break;
                }
                changeArr.push(this._computeChange(startObjArr[i],endObjArr[i],i));
            }
            return changeArr;
        },

        _numberToObj : function(num) {
            var numStr = String(num),
                numObj = numStr.split("."),
                flStr = numObj.length===2?numObj[1]:"",
                itgStr = this._isNeedComma() ? this._addComma(numObj[0]) : this._reverseString(numObj[0]);
            return {
                flArr : this._stringToArr(flStr),
                itgArr : this._stringToArr(itgStr)
            }
        },

        _reverseString : function(str) {
            return [].slice.call(str).reverse().join("");
        },

        _isNeedComma : function() {
            return this.options.comma;
        },

        _addComma : function(intStr) {
            var intArrRev = this._stringToArr(intStr).reverse(),
                newIntStr = "";
            for(var i=0,len=intArrRev.length;i<len;i++) {
                if(i!==0&&i%3==0) {
                    newIntStr += ",";
                }
                newIntStr += intArrRev[i];
            }
            return newIntStr;
        },

        _computeChange : function(start,end,i) {
            return {
                index:i,
                from:start,
                to:end
            }
        },

        _stringToArr : function(str) {
            return [].slice.call(str);
        },

        _getCurrentNum : function() {
            return this.currentNum || 0;
        },

        _setCurrentNum : function(currentNum) {
            this.currentNum = currentNum;
            this.$el.find(".oas-number-roll-val").val(currentNum);
        },
        // 对外方法集
        invoke: {
            setNumber : function(target) {
                this._moveTo(this.currentNum,target);
            },
            getNumber : function() {
                return this._getCurrentNum();
            }
        }
    });
})();
/**
 * 无极限树组件
 * @ author gehao
 * @ create time 2015.04.11
 * @ version  1.0.0
 */
;
(function() {
	$.oasUiFactory("oasTree", {
		options: {
			//树组件的标题文字
			title: "",
			//树的数据集合:[ztree的simpleData{"id":1, "pId":0, "name":"test1",icon:"diy/6.png"}]
			data: [],
			//是否有checkbox
			isCheckBox: false,
			//组件初始化展示方式:0:展示一级菜单其余全部收起；1:全部展开，显示所有节点;2:收起全部
			showType: 0,
			//树组件的高度,默认“auto”  
			height: "auto",
			//树组件的宽度,默认“auto”  
			width: "auto",
			//树组件操作集合{add：false,remove:false,modify:false}
			opts: {
				add: false,
				remove: false,
				modify: false,
				setting:false
			},
			//树组件检索方式：null:无搜索功能 0：即时检索，1：按回车键检索  
			searchType: 0,
			//点击树组件元素回调
			onClick: function() {},
			//checkbox状态改变回调
			checkChange: function() {},
			//新增操作回调
			add: function() {},
			//删除操作回调
			remove: function() {},
			//修改操作回调
			modify: function() {},
			//设置操作回调
			setting: function() {},
			//无数据，点击添加
			addData:function(){

			}
		},
		//模版
		_template: function() {
			var options = this.options,
				tpl = '<div class="oasTree">' +
					'<div class="oas-tree-title{{if title == false}} hide{{/if}}">' +
					'<span class="ellipsis">{{title}}</span>' +
					'<a href="javascript:;" class="open-all"><i class="oasicon oasicon-all-unfold" title="展开全部"></i></a>' +
					'<i></i>' +
					'<a href="javascript:;" class="close-all"><i class="oasicon oasicon-all-fold" title="收起全部"></i></a>' +
					'</div>' +
					'<div class="oas-tree-operate clearfix{{if opts.add == false && opts.modify == false && opts.remove == false && opts.setting == false}} hide{{/if}}">' +
					'{{if $data.opts.add ==  true}}' +
					'<a href="javascript:;" class="add-node show"><i class="oasicon oasicon-add"></i>&nbsp;新增</a>' +
					'{{/if}}' +
					'{{if $data.opts.modify == true}}' +
					'<a href="javascript:;" class="modify-node show"><i class="oasicon oasicon-edit"></i>&nbsp;修改</a>' +
					'{{/if}}' +
					'{{if $data.opts.remove ==  true}}' +
					'<a href="javascript:;" class="delete-node show"><i class="oasicon oasicon-rubbish"></i>&nbsp;删除</a>' +
					'{{/if}}' +
					'{{if $data.opts.setting ==  true}}' +
					'<a href="javascript:;" class="setting-node show"><i class="oasicon oasicon-setting"></i>&nbsp;设置</a>' +
					'{{/if}}' +
					'</div>' +
					'<div class="oas-tree-search{{if searchType == null}} hide{{/if}}">' +
					'<div class="oas-tree-search-inner">' +
					'<input type="text" placeholder="输入文字检索">' +
					'<a href="javascript:;"><i class="oasicon oasicon-search"></i></a>' +
					'</div>' +
					'</div>' +
					'<div class="oas-tree-nodes">' +
					'<ul class="ztree" id="{{zTreeId}}">' +
					'</ul>' +
					'<div class="oasTree-mask">' +
					'<div class="oas-local-loading clearfx">' +
					     '<span class="loading"></span>' +
					     '<span class="loading-text">数据读取中....</span>' +
					'</div>' +
					'<div class="oas-no-data clearfx">' +
					     '<div class="nodata-tip">' +
					     	'<i class="oasicon oasicon-data"></i>' +
					     '</div>' +
					     '<span class="nodata-text">暂无数据</span>' +
					     '<a href="javascript:;" class="add-data">添加一条？</a>' +
					'</div>' +
					'</div>' + 
					'</div>' +
					'</div>';
			return template.compile(tpl);
		},

		// 事件
		events: {
			'click .open-all': function(self, evt) {
				this.zTree.expandAll(true);
				return false;
			},
			'click .close-all': function(self, evt) {
				this.zTree.expandAll(false);
				return false;
			},
			'click .add-node': function(self, evt) {
				if (this.zTree.getSelectedNodes().length > 0) {
					this._emit('add', [this.zTree.getSelectedNodes()[0]]);
				} else if(this.zTree.getSelectedNodes().length == 0){
					//this._showMessage();
					this._emit('add', [null]);
				}
				//return false;
			},
			'click .modify-node': function(self, evt) {
				if (this.zTree.getSelectedNodes().length > 0) {
					this._emit('modify', [this.zTree.getSelectedNodes()[0]]);
				} else if(this.zTree.getSelectedNodes().length == 0){
					//this._showMessage();
					this._emit('modify', [null]);
				}
				return false;
			},
			'click .setting-node': function(self, evt) {
				if (this.zTree.getSelectedNodes().length > 0) {
					this._emit('setting', [this.zTree.getSelectedNodes()[0]]);
				} else if(this.zTree.getSelectedNodes().length == 0){
					//this._showMessage();
					this._emit('setting', [null]);
				}
				return false;
			},
			'click .delete-node': function(self, evt) {
				if (this.zTree.getSelectedNodes().length > 0) {
					//this._deleteConfirm();
					this._emit('remove', [this.zTree.getSelectedNodes()[0]]);
				} else if(this.zTree.getSelectedNodes().length == 0){
					//this._showMessage();
					this._emit('remove', [null]);
				}
				return false;
			},
			'click .add-data': function(self, evt) {
				this._emit('addData');
				return false;
			},
			'mouseover .oas-tree-nodes>.ztree':function(self, evt){
				$(self).addClass("auto");
			},
			'mouseout .oas-tree-nodes>.ztree':function(self, evt){
				$(self).removeClass("auto");
			},
			'click .oasicon.oasicon-search': function(self, evt) {
				var inputValue = this.$el.find(".oas-tree-search-inner > input[type=text]").val();
				this._searchNodes(inputValue);
				return false;
			}
		},
		//调整滚动条到第一个匹配的节点位置
		_adjustScrollBar:function(){
			var firstSearch = null;
			this.$el.find(".ztree a").each(function(index){
				if($(this).css("color") == "rgb(166, 0, 0)"){
					firstSearch = $(this);
					return false;
				}
			});
			if(firstSearch){
				this.$el.find(".oas-tree-nodes .ztree").scrollTop(0);
				var top = firstSearch.position().top;
				var treeHeight = this.$el.find(".oas-tree-nodes .ztree").height();
				if(top > treeHeight){
					this.$el.find(".oas-tree-nodes .ztree").scrollTop(top);
				}
			}	
		},
		//获取一个节点的根节点,并且一路展开父节点
		_getRootNode:function(node){
			this.searchNum ++;
			if(node.parentTId == null && this.searchNum !== 1){
				this.zTree.expandNode(node,true,false,false,false);
			}else if(node.parentTId !== null && node.parentTId !== ""){
				var parentNode = this.zTree.getNodeByTId(node.parentTId);
				if(!parentNode.open){
					this.zTree.expandNode(parentNode,true,false,false,false);
				}
				this._getRootNode(parentNode);
			}
		},
		//选择节点提示
		_showMessage: function() {
			OasMsg.open({
				type: 'warning',
				message: '请先选择节点',
				modal: false
			});
			setTimeout(function() {
				OasMsg.close();
			}, 2000);
		},
		//删除节点提示
		_deleteConfirm: function() {
			var self = this;
			OasDialog.open({
				title: '删除确定',
				type: 'confirm',
				message: '您确定要删除这个节点吗？',
				buttons: [{
					label: '确定删除',
					recommend: false,
					callBack: function($el) {
						OasDialog.close();
						self._emit('remove', [self.zTree.getSelectedNodes()[0]]);
					}
				}, {
					label: '取消',
					recommend: true,
					callBack: function($el) {
						OasDialog.close();
					}
				}]
			});
		},
		//创建组件入口
		_create: function() {
			this.nodesString = "";
			this.matchNodes = [];
			this.searchNum = 0;
			this._initData();
			this._createHead();
			this._initTree();
			this._adjustWH();
			this._bulidReflect();
		},
		//根据id映射对应的节点
		_bulidReflect:function(){
			this.reflects = {};
			var allNodes = this.zTree.transformToArray(this.zTree.getNodes());
			for (var i = 0; i < allNodes.length; i++) {
				this.reflects["" + allNodes[i].id] = allNodes[i];
			}
		},
		//给input绑定事件
		_bindChange:function(){
			var self = this,
			options =  this.options,
			inputValue = "";
			if(options.searchType == 1){
				this.$el.find(".oas-tree-search-inner > input[type=text]").on('keydown.oasTree', function(event){
					if(event.keyCode == 13) {
						inputValue = $(this).val();
						self._searchNodes(inputValue);
					}
				});
			}else if(options.searchType == 0){
				this.$el.find(".oas-tree-search-inner > input[type=text]").keyup(function(){
					inputValue = $(this).val();
					self._searchNodes(inputValue);
				});
			}
		},
		_searchNodes:function(inputValue){
			if(inputValue === ""){
				this._updateNodes(false);
			}else{
				this._updateNodes(false);
				this.matchNodes = this.zTree.getNodesByParamFuzzy("name",inputValue);
				this._updateNodes(true);
				this._adjustScrollBar();
			}
		},
		_updateNodes:function(highlight){
			for(var i = 0; i < this.matchNodes.length; i++) {
				this.matchNodes[i].highlight = highlight;
				if(highlight){
					this.searchNum = 0;
					this._getRootNode(this.matchNodes[i]);
				}
				this.zTree.updateNode(this.matchNodes[i]);
			}
		},
		_initTree: function() {
			var options = this.options,
				$el = this.$el,
				self = this;
			var setting = {
				edit: {
					enable: false,
					showRemoveBtn: false,
					showRenameBtn: false
				},
				check: {
					enable: options.isCheckBox,
					chkboxType: {
						"Y": "ps",
						"N": "ps"
					}
				},
				view: {
					showLine: false,
					fontCss: self._getFontCss
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: function(event, treeId, treeNode) {
						self._onNodeClick(event, treeId, treeNode);
					},
					onCheck: function(event, treeId, treeNode) {
						self._nodeCheckChange(event, treeId, treeNode);
					}
				}
			};
			$.fn.zTree.init($el.find("#" + this.zTreeId), setting, options.data);
			this.zTree = $.fn.zTree.getZTreeObj(this.zTreeId);
			if(options.showType == 1){
				this.zTree.expandAll(true);
			}else if(options.showType == 0){
				var level1Nodes = this.zTree.getNodesByParam("level",0,null);
				for (var i = 0; i < level1Nodes.length; i++) {
					this.zTree.expandNode(level1Nodes[i],true,false,true);
				}
			}else if(options.showType == 2){
				this.zTree.expandAll(false);
			}
		},
		_getFontCss:function(treeId, treeNode){
			return (!!treeNode.highlight) ? {color:"#A60000"} : {color:"#333"};
		},
		_onNodeClick: function(event, treeId, treeNode) {
			this._emit("onClick", [event, treeId, treeNode]);
		},
		_nodeCheckChange:function(event, treeId, treeNode){
			this._emit("checkChange", [event, treeId, treeNode]);
		},
		_adjustWH: function() {
			var options = this.options,
				$el = this.$el;
			if (options.width != "auto") {
				$el.find(".oasTree").width(options.width);
			} else {
				$el.find(".oasTree").width("100%");
			}
			if (options.height != "auto") {
				$el.find(".oasTree").height(options.height);
			} else {
				$el.find(".oasTree").height("100%");
			}
			var innerHeight = 52;
			if(options.searchType != null){
				innerHeight = innerHeight + 38;
			}
			if(options.opts.add || options.opts.modify || options.opts.remove || options.opts.setting){
				innerHeight = innerHeight + 30;
			}
			$el.find(".oas-tree-search-inner > input[type=text]").width($el.find(".oas-tree-search-inner").width() - 47);
			setTimeout(function(){
				$el.find(".oas-tree-nodes").height($el.find(".oasTree").height() -  innerHeight);
				$el.find(".oas-tree-nodes .ztree").height($el.find(".oasTree").height() -  innerHeight);
			},10);
			
		},
		//创建头部节点
		_createHead: function() {
			var $el = this.$el,
				$html = $(this.tpl(this._headData));
			$el.empty().append($html);
			this._bindChange();
		},
		//整合数据
		_initData: function() {
			var options = this.options,
				data = options.data;
			this.kvData = {};
			for (var i = 0; i < data.length; i++) {
				data[i].open = false;
				this.kvData["" + data[i].id] = data[i];
			};
			this.zTreeId = "oas-tree-nodes" + new Date().getTime();
			this._headData = {
				title: options.title,
				opts: options.opts,
				searchType:options.searchType,
				zTreeId: this.zTreeId
			};
		},
		// 组件对外方法集
		invoke: {
			/* 设置树组件的数据
			 * @param {ObjectArray} dataList 树数据集合
			 *	Object:{iconClass:"",label:"",id:"",pid:"",letters:"",number:"",checked:false,comment:""}
			 */
			data: function(dataList) {
				this.options.data = dataList;
				this._initTree();
				this._adjustWH();
				this._bulidReflect();
			},
			/* 改变树组件高度
			 * @param height {Number || "auto"}
			 *
			 */
			height: function(height) {
				this.options.height = height;
				this._adjustWH();
			},
			/* 改变树组件宽度
			 * @param width {Number || "auto"}
			 *
			 */
			width: function(width) {
				this.options.width = width;
				this._adjustWH();
			},
			/* 改变树组件标题
			 * @param title {Number || "auto"}
			 *
			 */
			title: function(title) {
				this.options.title = title;
				this.$el.find(".oas-tree-title > span").text(title);
			},
			/* 增加节点
			 * @param Object
			 * {iconClass:"",label:"",id:"",pid:"",letters:"",number:"",checked:false,comment:""}
			 */
			addNodes: function(parentNode, newNodes, isSilent) {
				var parentNodeObj = null;
				if(parentNode){
					parentNodeObj = this.zTree.getNodeByParam("id", parentNode.id, null);
				}
				var isSilent = isSilent ? isSilent : false;
				this.zTree.addNodes(parentNodeObj, newNodes, isSilent);
				this._bulidReflect();
			},
			/* 删除一个节点
			 * @param Object
			 * {iconClass:"",label:"",id:"",pid:"",letters:"",number:"",checked:false,comment:""}
			 */
			deleteNode: function(node) {
				var nodeObj = this.zTree.getNodeByParam("id", node.id, null);
				this.zTree.removeNode(nodeObj, false);
			},
			/* 修改一个节点
			 * @param Object
			 * treeNode
			 */
			modifyNode: function(node) {
				this.zTree.updateNode(node, true);
				this._bulidReflect();
			},
			/* 修改一个节点
			 * @return {ObjectArray}
			 * {iconClass:"",label:"",id:"",pid:"",letters:"",number:"",checked:false,comment:""}
			 */
			getCheckedNode: function() {
				return this.zTree.getCheckedNodes(true);
			},
			/* 根据参数条件获取节点
			 * @param {key,value}
			 * 
			 */
			getNodesByParam: function(key,value) {
				return this.zTree.getNodesByParam(key,value);
			},
			/* 选中一个节点
			 * @param {id}
			 * 
			 */
			selectNode: function(id) {
				var selectNode = this.reflects["" + id];
				this.zTree.selectNode(selectNode);
				this._emit("onClick", [null, this.zTreeId, selectNode]);
			},
			/* 操作结果提示
			 * @param {option--Object}
			 * {type:"success",message:"由于权限不够，添加节点失败！"}
			 */
			resultTip: function(option) {
				if (option.type == "success") {
					OasMsg.open({
						type: 'success',
						message: option.message,
						modal: false
					});
					setTimeout(function() {
						OasMsg.close();
					}, 2000);
				} else if (option.type == "error") {
					var self = this;
					OasDialog.open({
						title: '操作结果',
						type: 'warning',
						message: option.message,
						buttons: [{
							label: '确定',
							recommend: true,
							callBack: function($el) {
								OasDialog.close();
							}
						}]
					});
				}
			},
			showWaiting:function(){
				var $el = this.$el;
				$el.find(".oasTree-mask").show();
				$el.find(".oasTree-mask .oas-local-loading").show();
			},
			hideWaiting:function(){
				var $el = this.$el;
				$el.find(".oasTree-mask").hide();
				$el.find(".oasTree-mask .oas-local-loading").hide();
			},
			showNoData:function(noDataTip){
				var $el = this.$el;
				$el.find(".oasTree-mask").show();
				if(noDataTip){
					$el.find(".oasTree-mask .oas-no-data .add-data").text(noDataTip);
				}
				$el.find(".oasTree-mask .oas-no-data").show();
			},
			hideNoData:function(){
				var $el = this.$el;
				$el.find(".oasTree-mask").hide();
				$el.find(".oasTree-mask .oas-no-data").hide();
			},
			expandNodeById:function(id){
				var nodeObj = this.zTree.getNodeByParam("id", id, null);
				this.zTree.expandNode(nodeObj,true,false,false,false);
			}
		}
	});
})();
$(document).ready(function () {
	

	//展开收起事件
	$(".oas-search-box").on('click.oas-search','.box-toggle', function(e){
		if($(this).hasClass("oasicon-fold")){
			$(this).removeClass("oasicon-fold").addClass("oasicon-unfold");
			$(this).parents(".oas-search-box").find(".searchbox-bd").hide();
		}else{
			$(this).removeClass("oasicon-unfold").addClass("oasicon-fold");
			$(this).parents(".oas-search-box").find(".searchbox-bd").show();
			if($(window).scrollTop()>0){
				$(window).scrollTop(0);
			}
		}
	});
	var obj,name,text;
	//点击事件
   $(".oas-search-box").on('click.oas-search','.item-content:not(.muti-mode) a:not(.oas-btn-icon,.oas-disable)', function(e){
   		//$(this).siblings().removeClass("oas-current");
   		$(this).parents(".item-content").find("a").removeClass("oas-current");
   		$(this).addClass("oas-current");
   });
   //没有下一级的点击事件
   $(".oas-search-box").on('click.oas-search','.term-item.noExpand .item-content:not(.muti-mode) a:not(.oas-btn-icon,.oas-disable)', function(e){
	   	var	text = $(this).find(".termText").text(),
	   		name = $(this).parents(".term-item").attr("name"),
	   		obj = $(this).parents(".oas-search-box").find(".terms-wrap");

   		if($(this).hasClass("no-limit")){
   			obj.find('.'+name).remove();
   		}else{
   			addTerm(obj,name,text);
   		}
   });
   //有扩展的点击事件
   $(".oas-search-box").on('click.oas-search','.term-item.hasExpand .item-content:not(.muti-mode) a:not(.oas-btn-icon,.oas-disable)', function(e){
   		var termItem =$(this).parents(".term-item"),
   			name = termItem.attr("name"),
	   		obj = $(this).parents(".oas-search-box").find(".terms-wrap");

   		if($(this).hasClass("fst-choose no-limit")){
   			termItem.find(".second-item,.third-item").hide();
   			termItem.find(".second-item a,.third-item a").removeClass("oas-current");
   			obj.find('.'+name).remove();
   			$(this).parents(".term-item").find(".second-item,.third-item").removeClass("muti-mode");
   			$(this).parents(".term-item").find(".second-item,.third-item").find("a").removeClass("oas-current");
   			$(this).parents(".term-item").find(".second-item,.third-item").find("input").attr("checked",false);
   		}else if($(this).hasClass("fst-choose")){
   			typeOne = $(this).find(".termText").text();
   			text = typeOne;
   			if($(this).hasClass("has-child")){
   				termItem.find(".second-item").show();
   				addTerm(obj,name,text);
   			}else{
   				termItem.find(".second-item,.third-item").hide();
   				termItem.find(".second-item .sec-choose,.third-item .th-choose").removeClass("oas-current");
   				addTerm(obj,name,text);
   				$(this).parents(".term-item").find(".second-item,.third-item").removeClass("muti-mode");
	   			$(this).parents(".term-item").find(".second-item,.third-item").find("a").removeClass("oas-current");
	   			$(this).parents(".term-item").find(".second-item,.third-item").find("input").attr("checked",false);
   			}
   		}else if($(this).hasClass("sec-choose")){
   			typeTwo = $(this).find(".termText").text();
   			text = typeOne+typeTwo;
   			if($(this).hasClass("has-child")){
   				termItem.find(".third-item").show();
   				addTerm(obj,name,text);
   			}else{
   				termItem.find(".third-item").hide();
   				termItem.find(".third-item .th-choose").removeClass("oas-current");
   				addTerm(obj,name,text);
   				$(this).parents(".term-item").find(".third-item").removeClass("muti-mode");
	   			$(this).parents(".term-item").find(".third-item").find("a").removeClass("oas-current");
	   			$(this).parents(".term-item").find(".third-item").find("input").attr("checked",false);
   			}
   		}else if($(this).hasClass("th-choose")){
   			typeThree = $(this).find(".termText").text();
   			text = typeOne + typeTwo + typeThree;
   			addTerm(obj,name,text);
   		}
   });
	//多选事件
	$(".oas-search-box").on('click.oas-search','.item-content .muti-choose', function(e){
		if($(this).find("span").text()=="多选"){
			$(this).find("span").text("退出")
			$(this).parent().addClass("muti-mode");
			$(this).parent().find("a").removeClass("oas-current");
		}else{
			$(this).find("span").text("多选");
			$(this).parent().removeClass("muti-mode");
			$(this).parent().find("a").removeClass("oas-current");
			$(this).parent().find("input").attr("checked",false);
		}
	});
	//多选checkbox事件
	$(".oas-search-box").on('click.oas-search','.item-content.muti-mode a:not(.oas-btn-icon,.oas-disable)', function(e){
		var obj = $(this);
		 if (obj.find("input[type='checkbox']").size() > 0) {
			obj.toggleClass("oas-current");
			if (obj.hasClass("oas-current")) {
				obj.find("input[type='checkbox']").attr("checked", "checked");
			} else {
				obj.find("input[type='checkbox']").attr("checked", false);
			}
		}
	});
	//多选状态下 点击按钮级联事件
	$(".oas-search-box").on('click.oas-search','.item-content.muti-mode .muti-confim', function(e){
			var name = $(this).parents(".term-item").attr("name"),
				obj = $(this).parents(".oas-search-box").find(".terms-wrap"),
				text ='';
			if($(this).parent(".item-content").hasClass("first-item")){
				//var text = $(this).parent(".item-content").find(".oas-current").find(".termText").text();
				var doms = $(this).parent(".item-content").find(".oas-current").find(".termText");
					doms.each(function(i,dom){
						if(i == doms.length-1){
								text = text + $(dom).text();
							}else{
								text = text + $(dom).text() + "，";
							}
					});

				addTerm(obj,name,text);
			}else if($(this).parent(".item-content").hasClass("second-item")){
				//var typeOne = $(this).parents(".term-item").find(".first-item").find(".oas-current").find(".termText").text(),
				//	typeTwo = $(this).parent(".item-content").find(".oas-current").find(".termText").text(),
				//	text = typeOne + typeTwo;
				var domsTwo = $(this).parent(".item-content").find(".oas-current").find(".termText"),
					typeTwo = '',
					typeOne = $(this).parents(".term-item").find(".first-item").find(".oas-current").find(".termText").text();
					domsTwo.each(function(i,dom){
						if(i == domsTwo.length-1){
								typeTwo = typeTwo + $(dom).text();
							}else{
								typeTwo = typeTwo + $(dom).text() + "，";
							}
					});
					text = typeOne +typeTwo;
				addTerm(obj,name,text);
			}else if($(this).parent(".item-content").hasClass("third-item")){
				var typeOne = $(this).parents(".term-item").find(".first-item").find(".oas-current").find(".termText").text(),
					typeTwo = $(this).parents(".term-item").find(".second-item").find(".oas-current").find(".termText").text(),
					typeThree ='',
					domsThree = $(this).parent(".item-content").find(".oas-current").find(".termText");
					domsThree.each(function(i,dom){
						if(i == domsThree.length-1){
								typeThree = typeThree + $(dom).text();
							}else{
								typeThree = typeThree + $(dom).text() + "，";
							}
					});
					text = typeOne + typeTwo + typeThree;
					addTerm(obj,name,text);
			}
			
	});

	//删除事件
	$(".oas-search-box").on('click.oas-search','.search-term .oasicon-delete', function(e){
		var name=$(this).parent(".search-term").attr("name");
		$(this).closest(".oas-search-box").find("[name="+name+"] a").removeClass("oas-current");
		$(this).parent(".search-term").remove();
	});

	//重置事件searchbox-reset
	$(".oas-search-box").on('click.oas-search','.searchbox-reset', function(e){
		$(this).closest(".oas-search-box").find(".terms-wrap").empty();
		$(":oasSelect").oasSelect("reset");
		if($(this).closest(".oas-search-box").hasClass("connection")){
			$(this).closest(".oas-search-box").find(".item-content>a").removeClass("oas-current");
		}
	});
});
/**添加搜索条件**/
function addTerm(obj,name,text){
	var term = '<a href="javascript:;" class="search-term '+name+'" title="'+text+'" name="'+name+'"><span class="term-text">' +text+'</span><em class="oasicon oasicon-delete"></em></a>';
	obj.find('.'+name).remove();
   	obj.append(term);
}
function setTermUp(termWrap,data){
    termWrap.empty();
    $.each(data, function(index) {
        var $term = '<a href="javascript:;" class="search-term"><span class="term-text">' +data[index].text+'</span></a>';
        termWrap.append($term);
    });
}
/*! http://mths.be/placeholder v2.0.8 by @mathias */
;(function(window, document, $) {

	// Opera Mini v7 doesn’t support placeholder although its DOM seems to indicate so
	var isOperaMini = Object.prototype.toString.call(window.operamini) == '[object OperaMini]';
	var isInputSupported = 'placeholder' in document.createElement('input') && !isOperaMini;
	var isTextareaSupported = 'placeholder' in document.createElement('textarea') && !isOperaMini;
	var prototype = $.fn;
	var valHooks = $.valHooks;
	var propHooks = $.propHooks;
	var hooks;
	var placeholder;

	if (isInputSupported && isTextareaSupported) {

		placeholder = prototype.oasInput = function() {
			return this;
		};

		placeholder.input = placeholder.textarea = true;

	} else {

		placeholder = prototype.oasInput = function() {
			var $this = this;
			if($this.data('placeholder-enabled')) return; // add by rbai 2015-1-19 防止多次初始化
			$this
				.filter((isInputSupported ? 'textarea' : ':input') + '[placeholder]')
				.not('.placeholder')
				.bind({
					'focus.placeholder': clearPlaceholder,
					'blur.placeholder': setPlaceholder
				})
				.data('placeholder-enabled', true)
				.trigger('blur.placeholder');
			return $this;
		};

		placeholder.input = isInputSupported;
		placeholder.textarea = isTextareaSupported;

		hooks = {
			'get': function(element) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value;
				}

				return $element.data('placeholder-enabled') && $element.hasClass('oas-placeholder') ? '' : element.value;
			},
			'set': function(element, value) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value = value;
				}

				if (!$element.data('placeholder-enabled')) {
					return element.value = value;
				}
				if (value == '') {
					element.value = value;
					// Issue #56: Setting the placeholder causes problems if the element continues to have focus.
					if (element != safeActiveElement()) {
						// We can't use `triggerHandler` here because of dummy text/password inputs :(
						setPlaceholder.call(element);
					}
				} else if ($element.hasClass('oas-placeholder')) {
					clearPlaceholder.call(element, true, value) || (element.value = value);
				} else {
					element.value = value;
				}
				// `set` can not return `undefined`; see http://jsapi.info/jquery/1.7.1/val#L2363
				return $element;
			}
		};

		if (!isInputSupported) {
			valHooks.input = hooks;
			propHooks.value = hooks;
		}
		if (!isTextareaSupported) {
			valHooks.textarea = hooks;
			propHooks.value = hooks;
		}

		$(function() {
			// Look for forms
			$(document).delegate('form', 'submit.placeholder', function() {
				// Clear the placeholder values so they don't get submitted
				var $inputs = $('.oas-placeholder', this).each(clearPlaceholder);
				setTimeout(function() {
					$inputs.each(setPlaceholder);
				}, 10);
			});
		});

		// Clear placeholder values upon page reload
		$(window).bind('beforeunload.placeholder', function() {
			$('.oas-placeholder').each(function() {
				this.value = '';
			});
		});

	}

	function args(elem) {
		// Return an object of element attributes
		var newAttrs = {};
		var rinlinejQuery = /^jQuery\d+$/;
		$.each(elem.attributes, function(i, attr) {
			if (attr.specified && !rinlinejQuery.test(attr.name)) {
				newAttrs[attr.name] = attr.value;
			}
		});
		return newAttrs;
	}

	function clearPlaceholder(event, value) {
		var input = this;
		var $input = $(input);
		if (input.value == $input.attr('placeholder') && $input.hasClass('oas-placeholder')) {
			if ($input.data('placeholder-password')) {
				$input = $input.hide().next().show().attr('id', $input.removeAttr('id').data('placeholder-id'));
				// If `clearPlaceholder` was called from `$.valHooks.input.set`
				if (event === true) {
					return $input[0].value = value;
				}
				$input.focus();
			} else {
				input.value = '';
				$input.removeClass('oas-placeholder');
				input == safeActiveElement() && input.select();
			}
		}
	}

	function setPlaceholder() {
		var $replacement;
		var input = this;
		var $input = $(input);
		var id = this.id;
		if (input.value == '') {
			if (input.type == 'password') {
				if (!$input.data('placeholder-textinput')) {
					try {
						$replacement = $input.clone().attr({ 'type': 'text' });
					} catch(e) {
						$replacement = $('<input>').attr($.extend(args(this), { 'type': 'text' }));
					}
					$replacement
						.removeAttr('name')
						.data({
							'placeholder-password': $input,
							'placeholder-id': id
						})
						.bind('focus.placeholder', clearPlaceholder);
					$input
						.data({
							'placeholder-textinput': $replacement,
							'placeholder-id': id
						})
						.before($replacement);
				}
				$input = $input.removeAttr('id').hide().prev().attr('id', id).show();
				// Note: `$input[0] != input` now!
			}
			$input.addClass('oas-placeholder');
			$input[0].value = $input.attr('placeholder');
		} else {
			$input.removeClass('oas-placeholder');
		}
	}

	function safeActiveElement() {
		// Avoid IE9 `document.activeElement` of death
		// https://github.com/mathiasbynens/jquery-placeholder/pull/99
		try {
			return document.activeElement;
		} catch (exception) {}
	}

}(this, document, jQuery));

$(function() {

	$('input[placeholder], textarea[placeholder]').oasInput();
	
});

;
(function(global, document, undefined) {
	/**
	 * oasis库对网综支持文件，提供网综组件调用方式调用oasis组件
	 * @ author rbai
	 * @ create time 2014.08.11
	 * @ version  1.0.0
	 */


	// 分页
	$.fn.iPagination = function(totalCount, currentPage, _cfg) {
		this.oasPagin({
			total: totalCount,
			current: currentPage,
			pageItems: _cfg.pagesize,
			onPageNoChage: _cfg.callback,
			onPageItemsChange: _cfg.onPageItemsChange
		});
	}

	// 半遮页单例。
	global.OasShyPage = {};

	// 打开半遮页，没有则创建
	global.OasShyPage.open = function(opts){
		opts = $.extend({
			id: 'dlg-' + new Date().getTime(),
			src: 'about:blank',
			title: '半遮页'
		}, opts);
		var twin = oasis.getTopWindow();

		if(!!!$('#' + opts.id).size()){
			$('body').append('<div id="'+ opts.id +'"></div>');
			$('#' + opts.id).oasShyPage(opts);
			$(twin.document).find(".oas-mask").removeClass("zindex93");
			$('#' + opts.id).oasShyPage('open',opts);

			return $('#' + opts.id);
		}
	}

	global.OasShyPage.close = function(){
		$(':oasShyPage').oasShyPage('close');
	}

	//瞬间提示
	global.OasMsg = {};
	global.OasMsg.open = function(_data) {
		_data = $.extend(true,{
			type: 'success',
			message: '成功了',
            cancel: null,
			time: 2000,
			modal: null,
			//打开回调函数
			onOpen: function() {},
			//关闭回调函数
			onClose: function() {},
            //取消回调函数
            onCancel: function() {}
		},_data);
		var twin = oasis.getTopWindow();
		if(!!!$("body",twin.document).data('oasInstantTip')){
			$("body", twin.document).oasInstantTip(_data);
			$("body", twin.document).oasInstantTip("open");
		}else{
			$("body", twin.document).oasInstantTip("open",_data);
		}
		
		return $("body", twin.document);
	};
	global.OasMsg.close = function(){
		var twin = oasis.getTopWindow();
		$("body", twin.document).find('.oas-instant-tip').fadeOut(500);
		$("body", twin.document).find(".oas-mask").removeClass("zindex93");
		if($("body", twin.document).find('.oas-instant-tip.hasModal').size()>0){
			Mask.close();
			$("body", twin.document).find('.oas-instant-tip').removeClass("hasModal");
		}
	};

	//弹窗
	global.OasDialog = {};
	global.OasDialog.open = function(_data) {
		var twin = oasis.getTopWindow(),
			$dialog;
		_data = $.extend(true,{
			id:'dialog-' + new Date().getTime(),
			type: 'success',
			title: '头部标题',
			message: '恭喜！成功了',
			modal: false,
			src:null,
			content:null,
			position: null,
			autoOpen: false,
			isDrag: true,
			fixed: false,
			context:null,
			closeable: true,
			buttons: [{
						label:'确定',
						callBack : function($el){
							$el.oasDialog('close');
						}	
					}],
			//打开回调函数
			onOpen: function() {},
			//关闭回调函数
			onClose: function() {},
			//拖拽开始事件
			onDragStart: function() {},
			//拖拽中事件
			onDrag: function() {},
			//拖拽结束事件
			onDragEnd: function() {}
		},_data);
		
		/*$('body').append('<div id="'+ _data.id +'"></div>');
		$('#' + _data.id).oasDialog(_data);
		$('#' + _data.id).oasDialog('open');*/

		if(_data.context){
			_data.context.append('<div id="'+ _data.id +'" class="dialog-panel"></div>');
			$dialog = _data.context.find('#' + _data.id);
			$dialog.oasDialog(_data);
			$dialog.oasDialog('open');
			if(_data.modal == true){
				_data.context.find(".oas-mask").addClass("zindex93");
			}
		}else{
			if(window !== twin){
				twin.OasDialog.open(_data);
			}else{
				$("body").append('<div id="'+ _data.id +'" class="dialog-panel"></div>');
				$dialog = $("body").find('#' + _data.id);
				$dialog.oasDialog(_data);
				$dialog.oasDialog('open');
				if(_data.modal == true){
					$("body").find(".oas-mask").addClass("zindex93");
				}
			}
		}


		return $dialog;

	};
	global.OasDialog.close = function(){
		var twin = oasis.getTopWindow();
		if(window !== twin){
			twin.OasDialog.close();
		}
		if($("body").find(":oasDialog").size()>0){
			if($("body").find(".dialog-panel.hasModal").size()>0){
				Mask.close();
				$("body").find(".oas-mask").removeClass("zindex93");
			}
			$("body").find(".dialog-panel").oasDialog("close");
		}
		
	};
	global.OasDialog.show = function(){
		var twin = oasis.getTopWindow();
		if(window !== twin){
			twin.OasDialog.show();
		}
		if($("body").find(":oasDialog").size()>0){
			if($("body").find(".dialog-panel.hasModal").size()>0){
				Mask.open();
				$("body").find(".oas-mask").addClass("zindex93");
			}
			$("body").find(".dialog-panel").show();
		}
	};
	global.OasDialog.hide = function(){
		var twin = oasis.getTopWindow();
		if(window !== twin){
			twin.OasDialog.hide();
		}
		if($("body").find(":oasDialog").size()>0){
			if($("body").find(".dialog-panel.hasModal").size()>0){
				Mask.close();
				$("body").find(".oas-mask").removeClass("zindex93");
			}
			$("body").find(".dialog-panel").hide();
		}
	};
	//表单提示
	global.OasFormTip = {};
	global.OasFormTip.open =function(element,message,type){
		//if(!!!$(element).is(":oasFormTip")){
			$(element).oasFormTip();
		//}
		if(type==='oaserrormsg'){
			$(element).addClass('oas-error');
			if($(element).parent('.input-hasicon').size()>0){
				$(element).parent('.input-hasicon').addClass('oas-error');
			}
		}
		$(element).oasFormTip('message',message, type);
	}
	global.OasFormTip.remove =function(element,type){
		if(element){
			//if(!!!$(element).is(":oasFormTip")){
				$(element).oasFormTip();
			//}
			if(type==='oaserrormsg'){
				$(element).removeClass('oas-error');
				if($(element).parent('.input-hasicon').size()>0){
					$(element).parent('.input-hasicon').removeClass('oas-error');
				}
			}
			$(element).oasFormTip('remove', type);
			if($(element).parent('.input-hasicon').size()>0){
				$(element).parent('.input-hasicon').removeClass('oas-error');
			}
		}else{
			$('body').find(":oasFormTip").oasFormTip('remove', type);
		}
	}

	// 引导页单例。
	global.OasLead = {};

	// 打开引导页，没有则创建
	global.OasLead.open = function(_opts){
		_opts = $.extend({
			id: 'oasLead',
			data: [],
			current: 1,
			speed:300
		}, _opts);
		var twin = oasis.getTopWindow();
		if(window !== twin){
			twin.OasLead.open(_opts);
		}else{
			if(!!!$('#' + _opts.id).size()){
				$("body").append('<div id="'+ _opts.id +'" class="lead-panel"></div>');
				$("body").find('#' + _opts.id).oasLead(_opts);
				$("body").find('#' + _opts.id).oasLead('open');
			}else{
				$("body").find('#' + _opts.id).oasLead('open',_opts);
			}
		}
		$(twin.document).find(".oas-mask").removeClass("zindex93");
		
	}
	global.OasLead.close = function(){
		$(':oasLead').oasLead('close');
	}
})(this, document);

$(document).ready(function () {

	

	if($.browser.msie){
		$('body').on('focus', 'a, input:checkbox, input:radio, input:button', function(){
			$(this).blur();
		});
	}

	// 兼容validator表单验证。
	if($.validator){
		$.validator.setDefaults({
		
			showErrors: function(errorMap, errorList){
				if(!!errorList.length){
					for(var i = 0, len = errorList.length; i < len; i++){
						var element = errorList[i].element;
						if(element.nodeName.toLowerCase() === 'select'){
							OasFormTip.open($(element).next('.oas-select'), errorList[i].message,'oaserrormsg');
						}else{
							OasFormTip.open(element,errorList[i].message,'oaserrormsg');
						}
					}

				}else{
					if(this.lastElement && this.lastElement.nodeName.toLowerCase() === 'select'){
						OasFormTip.remove($(this.lastElement).next('.oas-select'));
					}else if(this.lastElement){
						OasFormTip.remove(this.lastElement);
					}
				}
			}
		});
	}
	
	// 处理dropdown
	$(document).on('click.oas-dropdown', function(e){
		if( $(e.target).is('[data-toggle=dropdown]') || !!$(e.target).closest('[data-toggle=dropdown]').size() ) return false;
		$('[data-toggle=dropdown-menu]').hide();
	});

	$(document).on('click.oas-dropdown', '[data-toggle=dropdown]', function(e){
		var self = this;
		$('body').find('[data-toggle=dropdown]').not(this).find('[data-toggle=dropdown-menu]').hide();
		setTimeout(function(){
			var location = Position.location($(self), 'fixed'),
				height = $(self).find('[data-toggle=dropdown-menu]').height() + 5;
			if(location.bottom < height){
				$(self).addClass('oas-dropdown-top');
			}else{
				$(self).removeClass('oas-dropdown-top');
			}
			$(self).find('[data-toggle=dropdown-menu]').toggle();
		},0);
		
	});

	$(document).on('click.oas-dropdown-btn', '.dropdown-menu li', function(){
		var text = $(this).text();
		$(this).closest('.dropdown-btn').find('.btn-text').eq(0).text(text);

	});

    // 处理tab	
	$(document).on('click', '.tabs-tab', function(){
		if($(this).hasClass('oas-text-disable')) return;

		// 寻找当前tabs卡控制的所有元素
		var tabsContent = $(this).closest('.oas-tabs').data('oastabs');

		$(this).siblings().removeClass('current').end().addClass('current');

		if(tabsContent){
			$('[oastabs-content='+ tabsContent +']').hide();
			var tabContent = $(this).data('tabcontent')
			$('[oastab-content='+ tabContent +']').show();
		}
	});

	 // 处理树tab
	$(document).on('click', '.oasTree-subfield .top-subfield > a,.oasTree-subfield .sub-subfield > a', function(){
		$(this).parent().find('>a').removeClass("current");
		$(this).addClass("current");
	});

	$('.tabs-toggle').on('click', function(){
		$(this).toggleClass("oasicon-unfold").toggleClass("oasicon-fold");
	});
	$('.tabs-toggle').oasTrigger();

	// 处理表单kv结构
	$(document).on('click.oas-form', '.form-label', function(){
		$(this).next().find('input[type=text],input[type=password],textarea').focus();
	});

	// 处理表单choose
	$(document).on('click.oas-form-choose', '.oas-choose:not(.oas-disable)', function(e){
		var obj = $(this);
		
		if (obj.find("input[type='radio']").size() > 0) {//是单选框
			if (!obj.hasClass("oas-current")) {
				obj.parent().find(".oas-choose").removeClass("oas-current");
				obj.parent().find("input[type='radio']:checked").attr("checked", false);
				obj.addClass("oas-current");
				obj.find("input[type='radio']").attr("checked", "checked");

				if (obj.parents(".form-group").hasClass("customVO-time")) {
					if(obj.hasClass("near-one-week")){
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearOneWeek()[0],"yyyy-MM-dd"));
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearOneWeek()[1],"yyyy-MM-dd"));
					}else if(obj.hasClass("near-one-month")){
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearOneMonth()[0],"yyyy-MM-dd"));
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearOneMonth()[1],"yyyy-MM-dd"));
					}else if(obj.hasClass("near-three-months")){
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearThreeMonths()[0],"yyyy-MM-dd"));
						obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearThreeMonths()[1],"yyyy-MM-dd"));
					}
					if (obj.hasClass("user-defined")) {
						obj.parents(".customVO-time").find(".interval").show();
						obj.parents(".customVO-time").find(".time-interval").hide();
					} else {
						obj.parents(".customVO-time").find(".interval").hide();
						obj.parents(".customVO-time").find(".time-interval").show();
					}
				}
			}
		} else if (obj.find("input[type='checkbox']").size() > 0) {
			obj.toggleClass("oas-current");
			if (obj.hasClass("oas-current")) {
				obj.find("input[type='checkbox']").attr("checked", "checked");
				if(obj.hasClass("oas-checkall")){//执行全选操作
					obj.closest(".form-content").find(".oas-choose:not('.oas-checkall') input[type='checkbox']").attr("checked", "checked");
					obj.closest(".form-content").find(".oas-choose:not('.oas-checkall')").addClass("oas-current");
				}
			} else {
				obj.find("input[type='checkbox']").attr("checked", false);
				if(obj.hasClass("oas-checkall")){//执行全选操作
					obj.closest(".form-content").find(".oas-choose:not('.oas-checkall') input[type='checkbox']").attr("checked", false);
					obj.closest(".form-content").find(".oas-choose:not('.oas-checkall')").removeClass("oas-current");
				}
			}
			if(obj.closest(".form-content").find(".oas-checkall").size()){//点的不是全选，且他的兄弟有全选的	
				if(obj.closest(".form-content").find(".oas-choose:not('.oas-checkall')").find("input[type='checkbox']").size() == obj.closest(".form-content").find(".oas-choose:not('.oas-checkall')").find("input[type='checkbox']:checked").size()){
					obj.closest(".form-content").find(".oas-checkall").addClass("oas-current")	;
					obj.closest(".form-content").find(".oas-checkall").find("input[type='checkbox']").attr("checked", "checked");
				}else{
					obj.closest(".form-content").find(".oas-checkall").removeClass("oas-current");
					obj.closest(".form-content").find(".oas-checkall").find("input[type='checkbox']").attr("checked", false);	
				}
			}
		}
	});
	//label标签的全选样式
	$(document).on('click.oas-choose', '.oas-checkbox', function(e){
		var obj = $(this);
		if(e.target.tagName != 'INPUT'){
			return;
		}else{
			if (obj.find("input[type='radio']").size() > 0) {//是单选框
				if (obj.parents(".form-group").hasClass("customVO-time")) {
					if (obj.hasClass("user-defined")) {
						obj.parents(".customVO-time").find(".interval").show();
					} else {
						obj.parents(".customVO-time").find(".interval").hide();
					}
				}
			} else if (obj.find("input[type='checkbox']").size() > 0) {
				if (obj.hasClass("oas-checkall")) {
					if(obj.find("input[type='checkbox']").prop("checked")){//执行全选操作
						obj.closest(".form-content").find(".oas-checkbox:not('.oas-checkall') input[type='checkbox']").prop("checked",true);
					}else {
						obj.closest(".form-content").find(".oas-checkbox:not('.oas-checkall') input[type='checkbox']").prop("checked", false);
					}
				} 
				if(obj.closest(".form-content").find(".oas-checkall").size()){//点的不是全选，且他的兄弟有全选的	
					if(obj.closest(".form-content").find(".oas-checkbox:not('.oas-checkall')").find("input[type='checkbox']").size() == obj.closest(".form-content").find(".oas-checkbox:not('.oas-checkall')").find("input[type='checkbox']:checked").size()){
						obj.closest(".form-content").find(".oas-checkall").find("input[type='checkbox']").prop("checked", "checked");
					}else{
						obj.closest(".form-content").find(".oas-checkall").find("input[type='checkbox']").prop("checked", false);	
					}
				}
			}
		}
	});

	//处理file 文件选择js
	$(document).on('click.oas-btn-file', '.oas-btn-file .open-file', function(){
        $(this).parent().find(".oas-filebutton-default").trigger("click");
    });
	//处理alert关闭
	$(document).on('click.oas-alert', '.oas-alert-close', function(){
		$(this).parent().hide();
	});
	//有图标的input点击，右侧按钮选中样式
	$(document).on('focus.input-hasicon', '.input-hasicon input,.input-hasicon textarea', function(){
        $(this).parent().find(".oas-btn-icon").addClass("btn-active");
    });
    $(document).on('blur.input-hasicon', '.input-hasicon input,.input-hasicon textarea', function(){
        $(this).parent().find(".oas-btn-icon").removeClass("btn-active");
    });
    //属性组克隆事件
    $(document).on('click.oas-clone', '.inputs-clone .add-item', function(){
		var $icon = $(this).find(".oasicon")
		if($icon.hasClass("oasicon-additem")){
			var obj = $(this).parents(".inputs-clone").clone();
			var selfInput = obj.find("input[type=text],input[type=password],input[type=search],input[type=url],input[type=tel],input[type=email],input[type=number],textarea");
				$(this).parents(".inputs-clone").addClass("dashed");
				$(this).parents(".inputs-clone").find(".add-item .oasicon").removeClass("oasicon-additem").addClass("oasicon-delitem");
				$(this).parents(".inputs-clone").find(".add-item .btn-text").text("取消");
				obj.find("option").removeAttr("selected");
				OasFormTip.remove(selfInput);
				obj.find(".form-tip-icon").remove();
				obj.find(".oas-select").remove().end().find("input[type=text]").val("").end().find("select").oasSelect();
				obj.insertBefore($(this).parents(".inputs-clone"));
				obj.find("input[type=text],input[type=password],input[type=search],input[type=url],input[type=tel],input[type=email],input[type=number],textarea").oasFormTip();
		}else{
			$(this).parents(".inputs-clone").remove();
		}
	});

	//新增的多选按钮
	 $(document).on('click.check-item', '.check-item:not(.oas-disable)', function(){
        if($(this).hasClass("oas-current")){
			$(this).removeClass("oas-current");
		}else{
			$(this).addClass("oas-current");
		}
    });
	$(document).on('click.check-item', '.check-item-single:not(.oas-disable)', function(){
		var obj = $(this);
		obj.siblings(".check-item-single").removeClass("oas-current");
		obj.addClass("oas-current");
		if (obj.closest(".form-group").hasClass("customVO-time")) {

			if (obj.hasClass("user-defined")) {
				obj.parent(".form-content").hide();
				obj.closest(".customVO-time").find(".more-info").show();
			}else{
				if(obj.hasClass("near-one-week")){
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearOneWeek()[0],"yyyy-MM-dd"));
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearOneWeek()[1],"yyyy-MM-dd"));
				}else if(obj.hasClass("near-one-month")){
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearOneMonth()[0],"yyyy-MM-dd"));
					obj.parents(".customVO-time").find(".time-interval").find("la2bel").eq(1).text(oasis.formatDate(oasis.getNearOneMonth()[1],"yyyy-MM-dd"));
				}else if(obj.hasClass("near-three-months")){
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearThreeMonths()[0],"yyyy-MM-dd"));
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearThreeMonths()[1],"yyyy-MM-dd"));
				}else if(obj.hasClass("near-half-year")){
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(0).text(oasis.formatDate(oasis.getNearHalfYear()[0],"yyyy-MM-dd"));
					obj.parents(".customVO-time").find(".time-interval").find("label").eq(1).text(oasis.formatDate(oasis.getNearHalfYear()[1],"yyyy-MM-dd"));
				}
				
				if (obj.hasClass("defined-time")) {
					obj.parents(".customVO-time").find(".interval").show();
					obj.parents(".customVO-time").find(".time-interval").hide();
				} else {
					obj.parents(".customVO-time").find(".interval").hide();
					obj.parents(".customVO-time").find(".time-interval").show();
				}
			}
		}
    });
   
    $(".customVO-time").on('click.check-item', '.close-more', function(){
		var obj = $(this);
		obj.closest(".customVO-time").find(".form-content").show();
		obj.parent(".form-content.more-info").hide();
    });
	//输入框清空事件
    $(document).on('click.oas-input-delete', '.clear-data-icon', function(){
		$(this).closest(".oas-delete-data").find("input").val(' ');
	});

	// Prevent the backspace key from navigating back.
	$(document).bind('keydown', function (event) {
	    var doPrevent = false;
	    if (event.keyCode === 8) {
	        var d = event.srcElement || event.target;
	        if ((d.tagName.toUpperCase() === 'INPUT' && 
	             (
	                 d.type.toUpperCase() === 'TEXT' ||
	                 d.type.toUpperCase() === 'PASSWORD' || 
	                 d.type.toUpperCase() === 'FILE' || 
	                 d.type.toUpperCase() === 'EMAIL' || 
	                 d.type.toUpperCase() === 'SEARCH' || 
	                 d.type.toUpperCase() === 'NUMBER' || 
	                 d.type.toUpperCase() === 'DATE' )
	             ) || 
	             d.tagName.toUpperCase() === 'TEXTAREA') {
	            doPrevent = d.readOnly || d.disabled;
	        }else if(d.tagName.toUpperCase() === 'DIV' && $(e.target).prop('contenteditable')){
		    	doPrevent = false;
		    }else {
	            doPrevent = true;
	        }
	    }
	    if (doPrevent) {
	        event.preventDefault();
	    }
	});
	//给日历组件后面图标绑定click
	$(document).on('click.calendar-a', '.input-hasicon > a.calendar-date-icon.oas-btn-icon', function(){
		$(this).prev("input[type=text].calendar-date").trigger("click");	
	});

});