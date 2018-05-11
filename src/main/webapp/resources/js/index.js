"use strict";
var Test = /** @class */ (function () {
    function Test(title, description, id, href) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.href = href;
        var element = document.createElement("li");
        element.setAttribute("id", id);
        element.classList.add("list-group-item");
        element.classList.add("secondary");
        element.setAttribute("onclick", "onTestClicked(\"" + id + "\")");
        element.innerHTML +=
            "<h5>" + title + "</h5>" +
                description;
        this.element = element;
    }
    Test.prototype.getElement = function () {
        return this.element;
    };
    return Test;
}());
var lastTestClicked = "";
function onTestClicked(callerID) {
    var element = document.getElementById(callerID);
    if (element == null)
        return;
    var prevElement = document.getElementById(lastTestClicked);
    if (prevElement != null) {
        prevElement.classList.remove("primary");
        prevElement.classList.add("secondary");
    }
    element.classList.remove("secondary");
    element.classList.add("primary");
    lastTestClicked = callerID;
}
function test() {
    var elem1 = new Test("Qwe", "Rfv", "test-1", "#");
    var elem2 = new Test("Asd", "Tgv", "test-2", "#");
    var elem3 = new Test("Zxc", "Xcrvg", "test-3", "#");
    var list = document.getElementById("test-list");
    if (list == null) {
        console.log("LIST == NULL");
        return;
    }
    list.appendChild(elem1.getElement());
    list.appendChild(elem2.getElement());
    list.appendChild(elem3.getElement());
    onTestClicked("test-1");
}
//# sourceMappingURL=index.js.map