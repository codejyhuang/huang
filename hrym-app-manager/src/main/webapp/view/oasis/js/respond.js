(function (global) {
	
	var timeout,
		winWidth = getWinWidth(),
		winHeight = getWinHeight();
		
	window.onresize = function() {
		timeout && clearTimeout(timeout);
		timeout = setTimeout(function() {
			var winNewWidth = getWinWidth();
			var winNewHeight = getWinHeight();

			if(winWidth !== winNewWidth || winHeight !== winNewHeight) {

				var css = format(winNewWidth, winNewHeight);
				document.getElementsByTagName('html')[0].className = 'w' + css[0] + ' h' + css[1];

				winWidth = winNewWidth;
				winHeight = winNewHeight;
			}
		}, 80);
	};

	var css = format(winWidth, winHeight);
	document.getElementsByTagName('html')[0].className = 'w' + css[0] + ' h' + css[1];

	function format(width, height){
		var tempW, tempH;
		if(width >= 1440){
			tempW = 1440;
		}else{
			tempW = 1024;
		}

		if(height >= 900){
			tempH = 900;
		}else{
			tempH = 768;
		}

		return [tempW, tempH];
	}

	function getWinHeight(){
		return window.innerHeight || document.documentElement.clientHeight;
	}

	function getWinWidth(){
		return window.innerWidth || document.documentElement.clientWidth;
	}
})(this);