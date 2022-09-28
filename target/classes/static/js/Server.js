class Server {

    async POST(object, url) {
        const response = await fetch(url, {
            method: "POST",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(object)
        });
        return await response.json();

    }

    async GET(url) {
        const response = await fetch(url);
        return await response.json();
    }

    async authorization(login, password) {
        let user = {
            "login": login,
            "password": password
        };
        const data = await this.POST(user,`api/users/authorization`);
        switch (data.status) {
            case 'OK': {
                await server.setCookie("token", data['jsonResponse'][0]);
                mainGame.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR authorization');
                break;
            }
        }
    }

    async registration(login, name, password) {
        let user = {
            "login": login,
            "name" : name,
            "password": password
        };
        const data = await this.POST(user,`api/users/registration`);
        switch (data.status) {
            case 'OK': {
                authorization.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR registration');
                break;
            }
        }
    }

    async unsetCookie(name) {
        let cookie = {
            "name": name
        };
        const data = await this.POST(cookie, `api/unsetCookie`);
        switch (data.status) {
            case 'OK': {
                break;
            }
            case 'ERROR': {
                console.error('ERROR unsetCookie');
                break;
            }
        }
    }

    async checkCookie(name) {
        let cookie = {
            "name": name
        };
        const data = await this.POST(cookie, `api`);
        switch (data.status) {
            case 'OK': {
                mainGame.page();
                break;
            }
            case 'ERROR': {
                authentication.page();
                //console.error('ERROR checkCookie');
                break;
            }
        }
    }

    async setCookie(name, value) {
        let cookie = {
            "name": name,
            "value" : value
        };
        const data = await this.POST(cookie, `api/setCookie`);
        switch (data.status) {
            case 'OK': {
                break;
            }
            case 'ERROR': {
                console.error('ERROR setCookie');
                break;
            }
        }
    }

    async enterSession(id) {
        const data = await this.GET(`api/sessions/${id}`);
        switch (data.status) {
            case 'OK': {
                break;
            }
            case 'ERROR': {
                console.error('ERROR enterSession');
                break;
            }
        }
    }

    async gamerEnterSession(sessionId) {
        const data = await this.GET(`api/gamers/session/${sessionId}`);
        switch (data.status) {
            case 'OK': {
                characterSelection.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR gamerEnterSession');
                break;
            }
        }
    }

    async gamerEnterHero(heroName) {
        const data = await this.GET(`api/gamers/hero/${heroName}`);
        switch (data.status) {
            case 'OK': {
                strengthsAndWeaknesses.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR gamerEnterHero');
                break;
            }
        }
    }

    async getPrey() {
        const data = await this.GET(`api/heroes/getPrey`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'][0];
            }
            case 'ERROR': {
                console.error('ERROR getPrey');
                break;
            }
        }
    }

    async checkGamerSession() {
        const data = await this.GET(`api/sessions/checkGamerSession`);
        switch (data.status) {
            case 'OK': {
                gamerRiddle.page();
                break;
            }
            case 'ERROR': {
                return data['jsonResponse'];
                //console.error('ERROR checkGamerSession');
            }
        }

    }

    async gamerCard() {
        const data = await this.GET(`api/cards/save`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR gamerCard');
                break;
            }
        }
    }

    async gamerRiddle(text) {
        let gRiddle = {
            "text": text
        }
        const data = await this.POST(gRiddle, `api/riddles/save`);
        switch (data.status) {
            case 'OK': {
                expectation.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR gamerRiddle');
                break;
            }
        }
    }

    async checkRiddles() {
        const data = await this.GET(`api/riddles/checkRiddles`);
        switch (data.status) {
            case 'OK': {
                gamerCards.page();
                return null;
            }
            case 'ERROR': {
                // console.error('ERROR checkRiddles');
                return data['jsonResponse'];
            }
            case 'END': {
                return null;
            }
        }
    }

    async getAllCards() {
        const data = await this.GET(`api/cards`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR getAllCards');
                break;
            }
        }
    }

    async getAllRiddles() {
        const data = await this.GET(`api/riddles`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR getAllRiddles');
                break;
            }
        }
    }

    async sessionsGetAll() {
        const data = await this.GET(`api/sessions`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR getAllSessions');
                break;
            }
        }
    }

    async getAllHeroes() {
        const data = await this.GET(`api/heroes`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR getAllHeroes');
                break;
            }
        }
    }

    async getAllHero() {
        const data = await this.GET(`api/heroes/all`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse'];
            }
            case 'ERROR': {
                console.error('ERROR getAllHeroes');
                break;
            }
        }
    }

    async accusationGamer(WordId) {
        const data = await this.GET(`api/gamers/accusationGamer/${WordId}`);
        switch (data.status) {
            case 'OK': {
                gamerRiddle.page();
                break;
            }
            case 'ERROR': {
                console.error('ERROR accusationGamer');
                break;
            }

            case 'END' : {
                endGame.page();
            }
        }
    }

    async getAllGamers() {
        const data = await this.GET(`api/gamers`);
        switch (data.status) {
            case 'OK': {
                return data['jsonResponse']
            }
            case 'ERROR': {
                console.error('ERROR getAllGamers');
                break;
            }
        }
    }
}