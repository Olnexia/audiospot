function switchLoginRegister() {
    var registerForm = document.getElementsByClassName("register-form");
    var loginForm = document.getElementsByClassName("login-form");
    if (registerForm.style.display=== 'none') {
        loginForm.style.display = 'none';
    } else {
        registerForm.style.display = 'none';
    }
}