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

    $(function () {
        // Bind the swipeHandler callback function to the swipe event on div.box
        $().on("swiperight", swipeHandler);

        // Callback function references the event target and adds the 'swipe' class to it
        function swipeHandler(event) {
            $(event.target).addClass("swiperight");
        }
    });

    $(function () {
        // Bind the swipeHandler callback function to the swipe event on div.box
        $().on("swipeleft", swipeHandler);

        // Callback function references the event target and adds the 'swipe' class to it
        function swipeHandler(event) {
            $(event.target).addClass("swipeleft");
        }
    });

    /*
    The user swiped to the left (finger moved left). Proceed to go up a floor, if available
    */
    function swipedLeft() {
        
    };

    /*
    The user swiped to the right (finger moved right). Proceed to go down a floor, if available
    */
    function swipedRight() {
        
    };
})();