class GamerRiddle {

    page() {
        bodyInnerHTML.innerHTML = `
                <input id="gamerRiddleText" placeholder="введите загадку">
                <button onclick = 'gamerRiddle.request()'>отправить загадку</button>`;
        server.gamerCard()
            .then(
                result => {
                    bodyInnerHTML.innerHTML = `
                        <h1 class = 'card'> ${result[1]} </h1>`
                        + bodyInnerHTML.innerHTML;
                    bodyInnerHTML.innerHTML = `
                    <h1> тема раунда: <br> ${result[0]} <br> ваша карточка: </h1>
                    `+ bodyInnerHTML.innerHTML;
                }
            );
    }

    request() {
        const gamerRiddleText = document.getElementById('gamerRiddleText');
        server.gamerRiddle(gamerRiddleText.value);
    }

}