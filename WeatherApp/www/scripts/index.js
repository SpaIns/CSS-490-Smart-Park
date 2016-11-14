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
        //This method is called on page load to set the favorite lot on the index page
        //the document bit is called, but the if statement within it & ready aren't
        $(document).ready(function () {
            if ($('#favorite-lot')[0]) { //if the favorite-lot id exists
                var a = document.getElementById('favorite-lot');
                var storage = window.localStorage;
                a.href = storage.getItem("favoriteLot");
            }
            else {
                //do nothing
            }
        });
        //alert('123 test 123 for index.js');
    };

    function onPause() {
        // TODO: This application has been suspended. Save application state here.
    };

    function onResume() {
        // TODO: This application has been reactivated. Restore application state here.
    };

})();