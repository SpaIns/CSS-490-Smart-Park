$(document).ready(function () {
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
});