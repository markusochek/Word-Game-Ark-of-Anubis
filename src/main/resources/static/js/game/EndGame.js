class EndGame {

    page() {
        server.checkRiddles()
            .then(
                result => {
                    if(result != null) {
                        bodyInnerHTML.innerHTML = `
                        <h1>Конец игры. Ожидание других игроков</h1>
                        <button onclick = 'endGame.page()'>проверить готовность игроков</button>
                        <h1 class = 'riddle'> ${result} </h1>`
                    } else {
                        endGame.request();
                    }
                }
            );
    }
    request() {
        bodyInnerHTML.innerHTML = ``;
        server.getAllGamers()
            .then(
                result1 => {
                    for(let i = 0; i < result1.length; i++) {
                        server.getAllHero().then(
                            result2 => {
                                bodyInnerHTML.innerHTML += `
                            <h1>
                               ${result1[i].name} герой ${result2[i].name} жертва ${result2[i].prey} количество обвинений ${result1[i].id}
                            </h1>`
                            }
                        )
                    }
                }
            );
    }



}