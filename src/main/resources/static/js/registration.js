
function clear() {
    let err = document.querySelector(".error");
    err.classList.remove("display-err");
    err.innerHTML = "";
}

function dispErr(errorMessage){
    let err = document.querySelector(".error");
    
    if(!err.classList.contains("display-err"))
        err.classList.add("display-err");

    err.innerHTML = errorMessage;
}

function dispSucc(message){
    let err = document.querySelector(".error");
    
    if(!err.classList.contains("display-success"))
        err.classList.add("display-success");

    err.innerHTML = message;
}

let form = document.forms['register-form'];

form.onsubmit = function(event) {

    clear();

    if(form.email.value === "" || form.name.value === "" || form.pass1.value === ""){
        dispErr("fill up fields");
        console.log("error");
        return false;
    }

    if(form.pass1.value != form.pass2.value ){
        dispErr("passwords should be same");
        return false;
    }

    dispSucc("success");

    event.preventDefault();
}