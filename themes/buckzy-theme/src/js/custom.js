/**
 * 
*/

$(function () {
            $("#profile").hover(
            function () { $(".submenu").show(); },
            function () { $(".submenu").hide(); }
            );
        });
        
        //$(function () {
        //    $("#mobile-menu").hover(
        //    function () { $(".mobile-submenu").show(); },
        //    function () { $(".mobile-submenu").hide(); }
        //    );
        //});

        $(function() {
            $('#mobile-menu').click(function() {
                $(".mobile-submenu").toggle('fast');
            });
        });
        
        //$(function () {
        //    $('#left-panel').hover(
        //        function () { $(".left-menu").show(); },
        //        function () { $(".left-menu").hide(); }
        //    );
        //});
        
        $(function () {
            $('#left-panel').hover(
                function () { $(".left-hover").show(); },
                function () { $(".left-hover").hide(); }
            );
        });