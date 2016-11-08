/*
The point of this file is to be the generic script for the garage/other floor plans.

Currently, it will have code functionality to set up swiping one direction or another to get new stuff

http://api.jquerymobile.com/swipe/
http://api.jquerymobile.com/swipeleft/
*/
//Test code for swipes
jQuery(window).on("swipeleft", function (event) {
    alert("swipe detected (left)"); //left means finger/mouse moves 
    var href = $('#go-up-floor-btn').attr('href');
    alert(href);
    window.location.replace(href);
});

jQuery(window).on("swiperight", function (event) {
    alert("swipe detected (right)"); //right means mouse/finger moves right
    var href = $('#go-down-floor-btn').attr('href');
    alert(href);
    if (href == '') {
        href = "../index.html";
        alert("hit if statement");
    }
    window.location.replace(href);
});