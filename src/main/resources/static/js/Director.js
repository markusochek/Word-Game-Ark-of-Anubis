const authentication = new Authentication;
// authentication.page();

const authorization = new Authorization;
// authorization.page();
// authorization.request();

const registration = new Registration;
// registration.page();
// registration.request();

const logout = new Logout;
// logout.page();

const mainGame = new MainGame;
// mainGame.page();
// mainGame.game();
// mainGame.rulesOfTheGame();

const sessions = new Sessions;
// sessions.page();
// sessions.request();

const characterSelection = new CharacterSelection;
// characterSelection.page();
// characterSelection.request();

const strengthsAndWeaknesses = new StrengthsAndWeaknesses;
// strengthsAndWeaknesses.page();
// strengthsAndWeaknesses.next();

const gamerCards = new GamerCards;
// gamerCards.page();
// gamerCards.request();

const gamerRiddle = new GamerRiddle;
// gamerRiddle.page();
// gamerRiddle.request();

const expectation = new Expectation;
// expectation.page();
// expectation.request();

const endGame = new EndGame;
// endGame.page();

const server = new Server;
server.checkCookie("token");
