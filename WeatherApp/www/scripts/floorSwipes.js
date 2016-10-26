/*
The point of this file is to be the generic script for the garage/other floor plans.

Currently, it will have code functionality to set up swiping one direction or another to get new stuff

http://api.jquerymobile.com/swipe/
http://api.jquerymobile.com/swipeleft/
*/
(function () {
    "use strict";

    document.addEventListener('deviceready', onDeviceReady.bind(this), false);

    function onDeviceReady() {
        // Handle the Cordova pause and resume events
        document.addEventListener('swipedleft', swipedLeft.bind(this), false);
        document.addEventListener('swipedright', swipedRight.bind(this), false);
        alert('123 test 123');
    };

    /*
    The user swiped to the left (finger moved left). Proceed to go up a floor, if available
    */
    function swipedLeft() {
        $(event.target).addClass("swipeleft");
        var href = $('#go-up-down-btn').attr('href');
        window.location.replace(href);
    };

    /*
    The user swiped to the right (finger moved right). Proceed to go down a floor, if available
    */
    function swipedRight() {
        $(event.target).addClass("swiperight");
        var href = $('#go-up-down-btn').attr('href');
        window.location.replace(href);
    };
})();