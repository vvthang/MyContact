

function ev_accordion(parent,selecter,tSelecter,action,adjust,speed,easing){
 	jQuery(function($){
		$(selecter).on(action,function(){
			if($(this).hasClass("open")){
				$(parent).find('.open').toggleClass("open",speed,easing);
			}else{
				$(parent).find('.open').toggleClass("open",speed,easing);
				$(this).toggleClass("open");
				$(this).find(tSelecter).toggleClass("open");
				$(this).next('.blind').toggleClass("open",speed,easing,function(){
					if(adjust > 0){
						var position  = $(this).offset().top - adjust;
						$('body,html').animate({scrollTop:position}, speed, easing);
					}
				});
			}
			return false;
		});
	});
}

function OverJump(link,px){
	var speed=600;
	if(arguments[2]){
		speed = arguments[2];
	}
	jQuery(function($){
		var target = $(link == "#" || link == "" ? 'html' : link);
		var position = target.offset().top;
		if((link.indexOf('#') >= 0)&&(link != "#top")){
			if(position - px > 0){
				position = position - px;
			}
		}
		$('body,html').animate({scrollTop:position}, speed, 'easeOutCubic');
	});
}

function anchorAdjust(px){
	var _touch = ('ontouchstart' in document) ? 'touchend' : 'click';
	var speed=600;
	if(arguments[2]){
		speed = arguments[2];
	}
	jQuery(function($){
		$('a[href^="#"]').on(_touch,function(){
			var link = $(this).attr('href');
			link = (link == "#" || link == "" )? 'html' : link;
			OverJump(link,px,speed);
			return false;
		});
	});
	return false;
}

function pageTop(){
	var selecter = arguments[0]? arguments[0]:'.pagetop';
	var position = arguments[1]? arguments[1]:200;
	jQuery(function($){
		$(window).on('scroll resize',function () {
			var s = $(this).scrollTop();
			var m = position;
			if(s > m) {
				$(selecter).fadeIn('slow');
			} else if(s < m) {
				$(selecter).fadeOut('slow');
			};
		});
	});
	return false;
}

//スムーズ内部リンクジャンプ
function smoothJump(){
	var speed = arguments[0]? arguments[0]:500;
	jQuery(function($){
		var _touch = ('ontouchstart' in document) ? 'touchend' : 'click';
		$('a[href^=#]').on(_touch,function(){
		        var href= $(this).attr("href");
		        var target = $(href == "#" || href == "" ? 'html' : href);
		        var position = target.offset().top;
		        $("html, body").animate({scrollTop:position}, speed, "easeOutCubic");
		        return false;
		 });
	});
	return false;
}
