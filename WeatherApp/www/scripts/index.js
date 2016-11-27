// For an introduction to the Blank template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkID=397704
// To debug code on page load in Ripple or on Android devices/emulators: launch your app, set breakpoints, 
// and then run "window.location.reload()" in the JavaScript Console.
(function () {
    "use strict";

    document.addEventListener( 'deviceready', onDeviceReady.bind( this ), false );

    function onDeviceReady() {
        // Handle the Cordova pause and resume events
        document.addEventListener( 'pause', onPause.bind( this ), false );
        document.addEventListener('resume', onResume.bind(this), false);
        //alert('123 test 123 for index.js');f
    };

    function onPause() {
        // TODO: This application has been suspended. Save application state here.
        window.location="scripts/page.html"
    };

    function onResume() {
        // TODO: This application has been reactivated. Restore application state here.
    };

})();

//This method is called on page load to set the favorite lot on the index page
//the document bit is called, but the if statement within it & ready aren't
//$(document).ready(function () {
$('#favorite-lot').click(function () {
    alert("inside the function");
    var storage = window.localStorage;
    var href = storage.getItem("favoriteLot");
    if (href != null) {
        //now redirect
        window.location.replace(href);
    }
    else {
        //favorite lot not yet set
        alert("No favorite lot set - please set in the preferences page");
    }
});
//});
