jQuery(function($){

	//トップへ戻る
	pageTop('.pagetop',400);
	$(".menu-open").click(function(){
		$(this).hide();
		$(".pickup-side-menu").animate({"left":"0px"},500);
	});
	$(".menu-close").click(function(){
		$(".pickup-side-menu").animate({"left":"-300px"},500,function(){
			$(".menu-open").show();
		});
	});
	
});


