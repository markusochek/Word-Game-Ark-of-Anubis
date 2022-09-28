class Logout {
    page() {
        server.unsetCookie("token");
        authentication.page();
    }
}