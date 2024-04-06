var playerInputs = document.getElementsByClassName("player");

for (var i = 0; i < playerInputs.length; i++) {
    playerInputs[i].addEventListener("focus", function () {
        this.removeAttribute("placeholder");
    });

    playerInputs[i].addEventListener("blur", function () {
        if (this.value === "") {
            this.setAttribute("placeholder", "Enter the name of player " + (i + 1));
        }
    });
}
