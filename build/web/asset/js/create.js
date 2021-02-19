var btnCreate = document.getElementById('create-button');
var modal = document.getElementById("myModal");
var btnBack = document.getElementById('back-button');
btnCreate.onclick = function(){
    modal.style.display = "flex";
}
btnBack.onclick = function(){
    modal.style.display = "none";
}

var modal2 = document.getElementById("myModal2");
var btnBack2 = document.getElementById('back-button2');
btnBack2.onclick = function(){
    modal2.style.display = "none";
}
