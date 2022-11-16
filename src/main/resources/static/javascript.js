let userName = document.getElementById("userName");
let loginSubmit = document.getElementById("loginSubmit");
let inputLoginInfo = document.getElementById("inputLoginInfo");
let login = document.querySelector(".login");
let val = false;

loginSubmit.addEventListener("click", (e)=>{
    val=!val
    if(!val){

login.innerHTML=`<form>
<input type = "text" placeholder = "User Name" id="userName">
</form>`
loginSubmit.innerHTML = "Submit"

    } else{

        login.innerHTML = `<h2> Welcome ${userName.value}</h2>`
        loginSubmit.innerHTML = "Reset"




    }
})

