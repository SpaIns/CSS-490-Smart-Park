// JavaScript source code
//General Script to show parking data

//populated by obtainData(), holds dictionaries contained spot info on a floor, in a garage
var data = [];
//populated by getTotals(), holds information about all open spots, can grab individual floors or garage totals
var totalSpots = [{ "North": { 1: 0, 2: 0, 3: 0, 4: 0 }, "South": { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 } }];


//firebase initialization
var config = {
    apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
    databaseURL: "https://smartpark-aa8eb.firebaseio.com",
};
firebase.initializeApp(config);
var database = firebase.database(); //get a database reference
var link = "/spaces"
//end firebase initialization

/*
  used to populate the dynamic images
  pre-conditions: must call it like this:
  obtainData(garage,floor).then(function(){
    //your code here
  });
*/
function obtainData(garage, floor) {
    return new Promise(function (resolve, reject) {//handle the aysnchronous call
        firebase.database().ref(link).once('value').then(function (snap) {

            snap.forEach(function (cSnap) {//for each space 
                val = cSnap.val();
                if (val.garage == garage && val.floor == floor) { //if the floor and garage are correct          
                    dict = { "spaceNumber": val.spaceNumber, "spaceType": val.spaceType, "available": val.isAvailable, "pref": "true" };
                    data.push(dict);//save the number, type, availability, and preference 
                }
            });
            resolve(data);//important!!
        });

    });
}
/*
  get the array that obtainData() populates
  pre-conditions: must call it like this: 
   var myArray;
   obtainData(garage,floor).then(function(){
         myArray=getArray();
});
*/
function getArray() {
    return data;
}
/*
  retrieves data from firebase and populates totalSpots based on open spots
  pre-condtitions: must call it like this:
  getTotals(garage).then(function(){
    //any code after data retrieval
  });
*/
function getTotals(garage) {

    return new Promise(function (resolve, reject) {//to handle asynchronous calls
        initTotals();//intialize global var
        firebase.database().ref(link).once('value').then(function (snap) {
            snap.forEach(function (cSnap) {//for each space

                val = cSnap.val();//shortcut lol
                if (garage == val.garage && val.isAvailable == "true") {//if current space is in the garage and open

                    totalSpots[0][garage][val.floor] += 1;//add to total number of open spaces on that floor, in that garage
                }
            });
            resolve(totalSpots);//most important line!!
        });
    });
}

/*
  gets the total number of available spaces of a given floor, in a given garage
  pre-conditions: must call it like this:
  var currFloorTotal;
  getTotals(garage).then(function(){
        currFloorTotal=getSpaces(garage,floor);
  });
*/
function getSpaces(garage, floor) {
    return totalSpots[0][garage][floor];//global variable 
}


//initializes the global variable for data retrieval
//no pre-conditions
function initTotals() {
    totalSpots = [{ "North": { 1: 0, 2: 0, 3: 0, 4: 0 }, "South": { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 } }];

}



//get total number of available spots in a garage
function garageTotal(garage) {
    var end = (garage = "South") ? 5 : 4;//top floor
    var sum = 0;
    for (var i = 1; i <= end; i++) {

        sum += totalSpots[0][garage][i];//current floor
    }
    return sum;//totals garage spaces

}






