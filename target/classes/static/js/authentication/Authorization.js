class Authorization {
    page() {
        bodyInnerHTML.innerHTML = `
                <input id="authorizationLogin" placeholder="Введите логин">
                <input id="authorizationPassword" placeholder="Введите пароль">
                <button onclick = 'authorization.request()'>Авторизоваться</button>`
    }

    request() {
        const authorizationLogin = document.getElementById("authorizationLogin");
        const authorizationPassword = document.getElementById("authorizationPassword");
        server.authorization(authorizationLogin.value, authorizationPassword.value);
    }
}