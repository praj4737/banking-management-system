
var csmd = false;
var urmd = false
function customerServiceMenuDrop(){

     if (csmd == false) {
         csmd = true;
     document.getElementById("mycustomerServiceMenu").style.display = "block";
     }else{
         csmd = false;
     document.getElementById("mycustomerServiceMenu").style.display = "none";
     }
}
function UserRegistrationMenuDrop(){

    if (urmd == false) {
     urmd = true;
    document.getElementById("myuserregistrationmenu").style.display = "block";
    }else{
     urmd = false;
    document.getElementById("myuserregistrationmenu").style.display = "none";
    }
}

//Start Page Routing 
function loadForm(requestedPage){
    $("#formLoad").load(requestedPage);
}

//End Page Routing