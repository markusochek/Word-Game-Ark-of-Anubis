class Expectation {

    page() {
        bodyInnerHTML.innerHTML = `
    <h1>ожидание других игроков</h1>
    <button onclick = 'expectation.request()'>проверить готовность игроков</button>`;
    }

    request() {
        server.checkRiddles()
            .then(
                result => {
                    if(result != null) {
                        bodyInnerHTML.innerHTML = `
                        <h1>ожидание других игроков</h1>
                        <button onclick = 'expectation.request()'>проверить готовность игроков</button>
                        <h1 class = 'riddle'> ${result} </h1>`
                    }
                }
            );
    }

}