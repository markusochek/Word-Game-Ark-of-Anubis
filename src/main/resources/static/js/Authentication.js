class Authentication {
    page() {
        const bodyInnerHTML = document.getElementById("bodyInnerHTML");
        bodyInnerHTML.innerHTML = `
            <button onclick = 'authorization.page()'>Авторизация</button>
            <button onclick = 'registration.page()'>Регистрация</button>`;
    }
}