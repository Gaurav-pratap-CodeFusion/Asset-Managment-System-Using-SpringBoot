document.addEventListener("DOMContentLoaded", function () {
    const sidebar = document.querySelector(".sidebar");

    document.addEventListener("click", function (event) {
        if (event.target.id === "btn" || event.target.classList.contains("bx-search")) {
            sidebar.classList.toggle("open");
            menuBtnChange();
        }
    });

    function menuBtnChange() {
        const closeBtn = document.querySelector("#btn");
        if (sidebar.classList.contains("open")) {
            closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");
        } else {
            closeBtn.classList.replace("bx-menu-alt-right", "bx-menu");
        }
    }
});
