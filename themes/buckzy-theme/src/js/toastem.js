var toastem = (function($) {

    var normal = function(content) {
        var item = $('<div class="notification normal"><span>' + content + '</span></div>');
        $("#toastem").append($(item));
        $(item).animate({ "right": "12px" }, "slow");
        setInterval(function() {
            $(item).animate({ "right": "-400px" }, "slow", function() {
                $(item).remove();
            });
        }, 8000);
    };


    var success = function(content) {
        var item = $('<div class="notification success"><span>' + content + '</span></div>');
        $("#toastem").append($(item));
        $(item).animate({ "right": "12px" }, "slow");
        setInterval(function() {
            $(item).animate({ "right": "-400px" }, "slow", function() {
                $(item).remove();
            });
        }, 8000);
    };


    var error = function(content) {
        var item = $('<div class="notification error"><span>' + content + '</span></div>');
        $("#toastem").append($(item));
        $(item).animate({ "right": "12px" }, "slow");
        setInterval(function() {
            $(item).animate({ "right": "-400px" }, "slow", function() {
                $(item).remove();
            });
        }, 8000);
    };

    $(document).on('click', '.notification', function() {
        $(this).fadeOut(400, function() {
            $(this).remove();
        });
    });

    return {
        normal: normal,
        success: success,
        error: error,
    };

})(jQuery);