class Sessions {
    page() {
        bodyInnerHTML.innerHTML = ``;
        server.sessionsGetAll()
            .then(
                result => {
                    for(let i = 0; i < result.length; i++) {
                        bodyInnerHTML.innerHTML += `
                            <button class = 'room' onclick = 'sessions.request(${result[i].id})'>
                            игровая комната ${result[i].name} <br>
                            статус ${result[i].status} <br>
                            количество игроков ${result[i].numberOfPlayers}</button>`
                    }
                }
            );
    }

    request(id) {
        server.enterSession(id);
        server.gamerEnterSession(id);
    }
}