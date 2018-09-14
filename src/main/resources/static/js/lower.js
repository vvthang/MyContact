jQuery(function($){

	//ナビゲーション挙動
	var $window = $(window);
	var $subnav = $('.area-headblue');
	var $nav = $('.nav');
	var $dm = $('.drop-menu');

	$window.on('resize load scroll',function(){
		var wpos = $window.scrollTop();
		var npos = $subnav.offset().top;
		var nh = $subnav.height();
		var ndiff =  nh - wpos;

		if(ndiff < 0){
			$nav.css({
				"position" : "fixed",
				"top" : "0px",
				"z-index" : "10000",
				"width" : "100%",
			});

			$dm.css({
				"position" : "fixed",
				"top" : "80px",
				"bottom" : "initial",
			});

			var myObj = new Object();
			myObj.left = 0;
			$nav.offset(myObj);

		}else{
			$nav.css({
				"position" : "static",
				"top" : "initial",
			});
			$dm.css({
				"position" : "absolute",
				"top" : "130px",
				"bottom" : "initial",
			});
		}

		if($window.width()<1120){
			$nav.width(1120);
		}else{
			$nav.width($window.width());
		}

	});

});


