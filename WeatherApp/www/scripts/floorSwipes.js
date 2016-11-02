/*
The point of this file is to be the generic script for the garage/other floor plans.

Currently, it will have code functionality to set up swiping one direction or another to get new stuff

http://api.jquerymobile.com/swipe/
http://api.jquerymobile.com/swipeleft/
*/
window.onload = function () {
    // Bind the swipeleftHandler callback function to the swipe event on div.box
    try {
        alert("test #1");
        document.addEventListener("DOMContentLoaded", swipeleftHandler);
        $(this).on("swipeleft", swipeleftHandler);
    }
    catch (Exception) {
        console.error("caught exception in floorswipes.js");
        alert("exception caught, console knows");
    }

    
};

// Callback function references the event target and adds the 'swipeleft' class to it
function swipeleftHandler(event) {
    $(event.target).addClass("swipeleft");
    alert('handled left');
    var href = $('#go-up-down-btn').attr('href');
    window.location.replace(href);
}