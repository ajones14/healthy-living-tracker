function init () {

    let addBreakfastButton = document.getElementById("addBreakfast");
    let addLunchButton = document.getElementById("addLunch");
    let addDinnerButton = document.getElementById("addDinner");
    let addSnackButton = document.getElementById("addSnack");

    let breakfastCancel = document.getElementById("breakfastCancel");

    let breakfastSave = document.getElementById("breakfastSave");

    let breakfast = document.getElementById("breakfast");
    let lunch = document.getElementById("lunch");
    let dinner = document.getElementById("dinner");
    let snack = document.getElementById("snack");

    let breakfast2 = document.getElementById("breakfast2");

    addBreakfastButton.addEventListener("click", function () {
        breakfast.style.display = "none";
        breakfast2.style.display = "inline-block";
    });

    breakfastCancel.addEventListener("click", function () {
        breakfast.style.display = "inline-block";
        breakfast2.style.display = "none";
    });

}

window.onload = init;