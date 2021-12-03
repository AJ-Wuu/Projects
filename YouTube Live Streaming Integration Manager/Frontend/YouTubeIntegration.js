var button = document.getElementById("button");

function buttonAction() {
    if (button.textContent == "Gooo") {
        button.innerText = "Stop";
    }
    else if (button.textContent == "Stop") {
        button.innerText = "Gooo";
    }
}
