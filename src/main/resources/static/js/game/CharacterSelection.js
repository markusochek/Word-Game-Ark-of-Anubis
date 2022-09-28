class CharacterSelection {

    constructor() {
        this.heroes = { MEDUSA: "MEDUSA", HECATONCHIRES: "HECATONCHIRES", HYDRA: "HYDRA", PEGASUS : "PEGASUS"};
    }

    page() {
        bodyInnerHTML.innerHTML = ``;
        server.getAllHeroes()
            .then(
                result => {
                    for(let i = 0; i < result.length; i++) {
                        bodyInnerHTML.innerHTML += `
                            <button onclick = 'characterSelection.request(characterSelection.heroes.${result[i].name})'>
                            ${result[i].name} </button>`
                    }
                }
            );
    }

    request(name) {
        server.gamerEnterHero(name);
    }
}