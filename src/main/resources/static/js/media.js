/**
 *  ブラウザ名を取得
 *  
 *  @return     ブラウザ名(ie6、ie7、ie8、ie9、ie10、ie11、chrome、safari、opera、firefox、unknown)
 *
 */

 
var getBrowser = function(){
	var ua = window.navigator.userAgent.toLowerCase();
	var ver = window.navigator.appVersion.toLowerCase();
	var name = 'unknown';

	if (ua.indexOf('chrome') != -1){					name = 'chrome';
	}else if (ua.indexOf('safari') != -1){				name = 'safari';
	}else if (ua.indexOf('opera') != -1){				name = 'opera';
	}else if (ua.indexOf('firefox') != -1){				name = 'firefox';
	}

	if (ua.indexOf("msie") != -1){
		alert('ie');
		if (ver.indexOf("msie 6.") != -1){				name = 'ie6';
		}else if (ver.indexOf("msie 7.") != -1){		name = 'ie7';
		}else if(ua.indexOf('trident/4') != -1){			name = 'ie8';
		}else if(ua.indexOf('trident/5') != -1){			name = 'ie9';
		}else if(ua.indexOf('trident/6') != -1){			name = 'ie10';
		}else if(ua.indexOf('trident/7') != -1){			name = 'ie11';
		}else{name = 'ie';}
	}else if(ua.indexOf("edge/12") != -1){name = 'edge12';}
	return name;
};



/**
 *  対応ブラウザかどうか判定
 *  
 *  @param  browsers    対応ブラウザ名を配列で渡す(ie6、ie7、ie8、ie9、ie10、ie11、chrome、safari、opera、firefox)
 *  @return             サポートしてるかどうかをtrue/falseで返す
 *
 */
var isBrowser = function(browsers){
    var thusBrowser = getBrowser();
    for(var i=0; i<browsers.length; i++){
        if(browsers[i] == thusBrowser){
            return true;
            exit;
        }
    }
    return false;
};

$('html').addClass(getBrowser);