function switchLoginRegister() {
    var register = document.getElementById("register");
    var login = document.getElementById("login");
    var form = document.getElementById("login-register");

    if (register.style.display=== 'none') {
        login.style.display = 'none';
        register.style.display = 'block';
        form.style.height='330px'
    } else {
        register.style.display = 'none';
        login.style.display = 'block';
        form.style.height='230px'
    }
}