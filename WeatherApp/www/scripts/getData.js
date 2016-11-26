// JavaScript source code
//General Script to show parking data


function obtainData(garage, floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    var databox = document.getElementById('#databox');
    databox.val = "testing123";
    link = "/spaces";
      firebase.database().ref(link).once('value').then(function (snap) {
             snap.forEach(function (cSnap) {
                val = cSnap.val();
                if (val.garage == garage && val.floor== floor) {
                   //document.writeln(val.spaceNumber +"<br>"); 
                   //var databox = document.getElementById('#databox');
                   databox.val = databox.val + ("Space: " + val.spaceNumber + " Type: " + val.spaceType + "<br>" + '\n');
                }
             });

      });
}

