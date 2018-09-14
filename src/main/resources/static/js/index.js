 $(document).ready(function() 
 {

    $('.search-tab-list li').click(function(e) 
    {
    	// Remove attributes active class
     	$(this).parent().find("li").removeClass("active");
     	// Add attributes active class for this tab
	    $(this).addClass("active");

	    // Get data-tab
	    var a = $(this).data("tab");

	    // Hide all search-content
	    $(".search-content").css("display","none");
	    // Display block corresponding with search
	    $(a).css("display","block");

    });


 });