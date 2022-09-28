class GamerCards {

    page() {
        bodyInnerHTML.innerHTML = ``;
        server.getAllHero()
            .then(
                result => {
                    const bodyHeroes = document.createElement("div");
                    for (let i = 0; i < result.length; i++) {
                        bodyHeroes.innerHTML += `
                                <h1 class = 'riddle'>
                                    ${result[i].name}
                                </h1>`;
                    }
                    bodyHeroes.style.height = "80px";
                    bodyHeroes.style.width = "99%";
                    bodyHeroes.style.display = "flex";
                    bodyHeroes.style.flexDirection = "row";
                    bodyHeroes.style.justifyContent = "center";
                    bodyHeroes.style.alignItems = "center";
                    bodyInnerHTML.append(bodyHeroes);
                    server.getAllRiddles()
                        .then(
                            result => {
                                const bodyRiddles = document.createElement("div");
                                for (let i = 0; i < result.length; i++) {
                                    bodyRiddles.innerHTML += `
                                        <h1 id = 'riddles' class = 'riddle'>
                                            ${result[i].text}
                                            </h1>`;
                                }
                                bodyRiddles.style.height = "80px";
                                bodyRiddles.style.width = "99%";
                                bodyRiddles.style.display = "flex";
                                bodyRiddles.style.flexDirection = "row";
                                bodyRiddles.style.justifyContent = "center";
                                bodyRiddles.style.alignItems = "center";
                                bodyInnerHTML.append(bodyRiddles);
                                bodyInnerHTML.innerHTML += `<hr class = 'hr-vertical-gradient'>`;
                                server.getAllCards()
                                    .then(
                                        result => {
                                            const bodyCards = document.createElement("div");
                                            for (let i = 0; i < result.length; i++) {
                                                bodyCards.innerHTML += `
                                                    <button onclick = 'gamerCards.request(${result[i].id})' class = 'card'>
                                                        ${result[i].word}
                                                    </button>`;
                                            }
                                            bodyInnerHTML.append(bodyCards);
                                        }
                                    );
                            }
                        );
                }
            );
    }

    request(WordId) {
        server.accusationGamer(WordId);
    }

}