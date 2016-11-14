/*
The point of this file is to be the generic script for the garage/other floor plans.

Currently, it will have code functionality to set up swiping one direction or another to get new stuff

http://api.jquerymobile.com/swipe/
http://api.jquerymobile.com/swipeleft/
*/
//Test code for swipes
var supportTouch = $.support.touch,
            scrollEvent = "touchmove scroll",
            touchStartEvent = supportTouch ? "touchstart" : "mousedown",
            touchStopEvent = supportTouch ? "touchend" : "mouseup",
            touchMoveEvent = supportTouch ? "touchmove" : "mousemove";
$.event.special.swipeupdown = {
    setup: function () {
        var thisObject = this;
        var $this = $(thisObject);
        $this.bind(touchStartEvent, function (event) {
            var data = event.originalEvent.touches ?
                    event.originalEvent.touches[0] :
                    event,
                    start = {
                        time: (new Date).getTime(),
                        coords: [data.pageX, data.pageY],
                        origin: $(event.target)
                    },
                    stop;

            function moveHandler(event) {
                if (!start) {
                    return;
                }
                var data = event.originalEvent.touches ?
                        event.originalEvent.touches[0] :
                        event;
                stop = {
                    time: (new Date).getTime(),
                    coords: [data.pageX, data.pageY]
                };

                // prevent scrolling
                if (Math.abs(start.coords[1] - stop.coords[1]) > 10) {
                    event.preventDefault();
                }
            }
            $this
                    .bind(touchMoveEvent, moveHandler)
                    .one(touchStopEvent, function (event) {
                        $this.unbind(touchMoveEvent, moveHandler);
                        if (start && stop) {
                            if (stop.time - start.time < 1000 &&
                                    Math.abs(start.coords[1] - stop.coords[1]) > 30 &&
                                    Math.abs(start.coords[0] - stop.coords[0]) < 75) {
                                start.origin
                                        .trigger("swipeupdown")
                                        .trigger(start.coords[1] > stop.coords[1] ? "swipeup" : "swipedown");
                            }
                        }
                        start = stop = undefined;
                    });
        });
    }
};
$.each({
    swipedown: "swipeupdown",
    swipeup: "swipeupdown"
}, function (event, sourceEvent) {
    $.event.special[event] = {
        setup: function () {
            $(this).bind(sourceEvent, $.noop);
        }
    };
});


//This is the code that I actually wrote and that executes the L/R or U/D switch
var storage = window.localStorage;
var updown = "";
if (storage.getItem("swipes") == "updown") {
    updown = "true";
}
else {
    updown = "false";
}
jQuery(window).on("swipeup", function (event) {
    if (updown == "true") {
        alert("swipe detected (up)"); //up means finger/mouse moves up
        var href = $('#go-up-floor-btn').attr('href');
        alert(href);
        window.location.replace(href);
    }
});

jQuery(window).on("swipedown", function (event) {
    if (updown == "true") {
        alert("swipe detected (down)"); //down means finger moves down
        var href = $('#go-down-floor-btn').attr('href');
        alert(href);
        if (href == '') {
            href = "../index.html";
            alert("hit if statement");
        }
        window.location.replace(href);
    }
});



jQuery(window).on("swipeleft", function (event) {
    if (updown == "false") {
        alert("swipe detected (left)"); //left means finger/mouse moves left
        var href = $('#go-up-floor-btn').attr('href');
        alert(href);
        window.location.replace(href);
    }
});

jQuery(window).on("swiperight", function (event) {
    if (updown == "false") {
        alert("swipe detected (right)"); //right means mouse/finger moves right
        var href = $('#go-down-floor-btn').attr('href');
        alert(href);
        if (href == '') {
            href = "../index.html";
            alert("hit if statement");
        }
        window.location.replace(href);
    }
});

function setUpDown(tf) {
    updown = tf;
}

