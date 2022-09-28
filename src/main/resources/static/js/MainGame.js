class MainGame {
    page() {
        bodyInnerHTML.innerHTML = `
            <button onclick = 'mainGame.game()'>Играть</button>
            <button onclick = 'mainGame.rulesOfTheGame()'>Правила игры</button>
            <button onclick = 'logout.page()'>Выход из системы</button>`;
    }

    game() {
        sessions.page();
    }

    rulesOfTheGame() {
        bodyInnerHTML.innerHTML = `
        <h1>Правила игры</h1>`;
    }
}