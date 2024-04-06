var playerInputs = document.getElementsByClassName("player");

for (var i = 0; i < playerInputs.length; i++) {
    playerInputs[i].addEventListener("focus", function () {
        this.removeAttribute("placeholder");
    });

    playerInputs[i].addEventListener("blur", function () {
        if (this.value === "") {
            if(i == 2){
                i = 0;
            }
            this.setAttribute("placeholder", "Enter the name of player " + (i + 1));
        }
    });
}
