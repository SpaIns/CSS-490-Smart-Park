﻿<script id="floorTemplate" type="text/x-handlebars-template">
    <html>
    <head>
        <link href="../css/BootStrap/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="../scripts/JQueryMobile/jquery.mobile-1.4.5.min.js">
        <script src="../scripts/JQuery/jquery-2.2.4.min.js"></script>
        <script src="../scripts/JQueryMobile/jquery.mobile-1.4.5.min.js"></script>
        <script src="../scripts/floorSwipes.js"></script>
    </head>
    <body>
        <h5>{{floor_level}}</h5>
        <div>Content goes here.</div>

        <div>
            <a id="go-up-floor-btn" class="btn btn-primary btn-lg active" role="button" href="{{floor_up_ref}}">{{up_msg}}</a>
            <!-- invisible button to redirect swipe to South Main-->
            <a id="go-down-floor-btn" class="btn btn-primary btn-lg active" role="button" href="{{floor_down_ref}}">{{down_msg}}</a>
        </div>
    </body>
    </html>
</script>