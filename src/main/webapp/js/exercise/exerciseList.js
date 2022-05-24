/*검색*/
$(function(){
    $("#search-keyword").keyup(function(){
        var value = $(this).val().toLowerCase();
        
     $("#exercise-contents>tr").filter(function(){
            var idx = $(this).text().toLowerCase().indexOf(value); 
            $(this).toggle(idx>-1);
        });
    });
});
