class StrengthsAndWeaknesses {

    page() {
        server.getPrey()
            .then(
                    result => {
                        bodyInnerHTML.innerHTML = `
                            <h1>Ваш герой ${result.name} Ваша жертва ${result.prey}</h1>
                            <button onclick = 'strengthsAndWeaknesses.next()'>Далее</button>
                            <responseCheckGamerSession></responseCheckGamerSession>`;
                    }
                )
    }

    next() {
        const responseCheckGamerSession = document.getElementsByTagName('responseCheckGamerSession')[0];
            server.checkGamerSession()
                .then(
                    result => {
                        responseCheckGamerSession.innerHTML = `
                        <h1> ${result} </h1>`
                    }
                );
    }
}