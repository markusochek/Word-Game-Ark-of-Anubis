class Registration {
    page() {
        bodyInnerHTML.innerHTML = `
                <input id="registrationLogin" placeholder="Введите логин">
                <input id="registrationName" placeholder="Введите ваше имя в игре">
                <input id="registrationPassword" placeholder="Введите пароль">
                <button onclick = 'registration.request()'>Зарегистрироваться</button>`
    }

    request() {
        const registrationLogin = document.getElementById("registrationLogin");
        const registrationName = document.getElementById("registrationName");
        const registrationPassword = document.getElementById("registrationPassword");
        server.registration(registrationLogin.value, registrationName.value, registrationPassword.value);
        authorization.page();
    }
}