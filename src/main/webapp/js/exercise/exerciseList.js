/*검색*/
$(function(){
    $("#search").keyup(function(){
        var value = $(this).val().toLowerCase();
        
        $("#searchList>tr").filter(function(){
            var idx = $(this).text().toLowerCase().indexOf(value); 
            $(this).toggle(idx>-1);
        });
    });
});