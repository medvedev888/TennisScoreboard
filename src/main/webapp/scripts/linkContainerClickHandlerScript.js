var linkContainers = document.getElementsByClassName("link-container");

for (var i = 0; i < linkContainers.length; i++) {
    linkContainers[i].addEventListener("click", function() {
        var link = this.querySelector('.link').getAttribute('href');
        window.location.href = link;
    });
}
