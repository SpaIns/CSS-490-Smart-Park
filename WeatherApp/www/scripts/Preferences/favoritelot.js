//Javascript methods that will set the preferred default garage for the favorites button/bar or whatever

$(document).ready(function () {
    //generic function that will work for all favorite buttons
    $('.favorite').click(function () {
        var href = $(this).attr('href'); //get the href of the button clicked
        var storage = window.localStorage;
        storage.setItem("favoriteLot", href); //store the favorite; href should be the path as it would be from index.html
        $('#textbox')[0].value = href;
    });
});