// JavaScript source code
//General Script to show parking data


function obtainData(garage, floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    link = "/spaces";
      firebase.database().ref(link).once('value').then(function (snap) {
             snap.forEach(function (cSnap) {
                val = cSnap.val();
                if (val.garage == garage && val.floor== floor) {
                   document.writeln(val.spaceNumber +"<br>");     
                }
             });

      });
}

