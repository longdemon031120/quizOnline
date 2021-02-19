var count = 45 * 60 - 1;
var start = document.getElementById('start');
start.onclick = function(){
    var interval = setInterval(function () {
    var h = Math.floor(count / 3600);
    var min = Math.floor((count % 3600) / 60);
    var sec = Math.floor(count - (3600 * h + 60 * min));
    document.getElementById('count').innerHTML = min + ":" + sec;
    count--;
    if (count < 0) {
        clearInterval(interval);
        document.getElementById('count').innerHTML = 'Done';
        var button = document.getElementById('submit-btn');
        button.form.submit();
    }
    }, 1000);
    start.remove();
    var mainBox = document.getElementById('father-of-question-box');
    mainBox.classList.add('appear');
}

var app = angular.module("DemoApp", ['ngMaterial', 'cl.paging']);
app.controller("MainController", ['$scope', function($scope) {

  $scope.currentPage = 0;

  $scope.paging = {
    total: 10,
    current: 1,
    onPageChanged: loadPages,
  };

  function loadPages() {
    console.log('Current page is : ' + $scope.paging.current);
    var oldPage = document.getElementsByClassName('question-container');
    for(var i = 0; i < oldPage.length; i++){
        oldPage[i].classList.remove('appear');
    }

    var currentPage = document.getElementById($scope.paging.current);
    if(currentPage != null){
        currentPage.classList.add('appear');
    }

    $scope.currentPage = $scope.paging.current;
  }

}]);

