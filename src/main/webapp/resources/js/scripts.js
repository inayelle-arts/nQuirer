"use strict";
var userDefinedElements = [];
var lastActiveElementId;
var SideElement = /** @class */ (function () {
    function SideElement(title, description, id) {
        this._title = title;
        this._description = description;
        this._id = id;
        var element = document.createElement("li");
        element.setAttribute("id", this._id);
        element.setAttribute("onclick", "makeActive(\"" + id + "\")");
        element.classList.add("list-group-item");
        element.classList.add("secondary");
        element.innerHTML +=
            "<h5>" + title + "</h5>" +
                description;
        this._element = element;
    }
    Object.defineProperty(SideElement.prototype, "title", {
        get: function () {
            return this._title;
        },
        set: function (value) {
            this._title = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SideElement.prototype, "description", {
        get: function () {
            return this._description;
        },
        set: function (value) {
            this._description = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SideElement.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SideElement.prototype, "element", {
        get: function () {
            return this._element;
        },
        enumerable: true,
        configurable: true
    });
    return SideElement;
}());
function makeActive(id) {
    var elem = document.getElementById(id);
    if (elem != null) {
        elem.classList.remove("secondary");
        elem.classList.add("primary");
    }
    makeUnactive(lastActiveElementId);
    lastActiveElementId = id;
}
function makeUnactive(id) {
    var elem = document.getElementById(id);
    if (elem != null) {
        elem.classList.remove("primary");
        elem.classList.add("secondary");
    }
}
function test() {
    var text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus architecto eius exercitationem natus nesciunt optio unde vero. Blanditiis doloremque eum expedita molestias nesciunt nulla, optio placeat quod similique ullam voluptate!";
    var elem1 = new SideElement("Kek", text, "elem-1");
    var elem2 = new SideElement("Lol", text, "elem-2");
    var elem3 = new SideElement("Qwe", text, "elem-3");
    elem1.element.onclick = function () {
        makeActive("elem-1");
        var titleHolder = document.getElementById("selected-test-title");
        var descrHolder = document.getElementById("selected-test-description");
        if (titleHolder == null || descrHolder == null)
            return;
        titleHolder.innerText = elem1.title;
        descrHolder.innerText = elem1.description;
    };
    elem2.element.onclick = function () {
        makeActive("elem-2");
        var titleHolder = document.getElementById("selected-test-title");
        var descrHolder = document.getElementById("selected-test-description");
        if (titleHolder == null || descrHolder == null)
            return;
        titleHolder.innerText = elem2.title;
        descrHolder.innerText = elem2.description;
    };
    elem3.element.onclick = function () {
        makeActive("elem-3");
        var titleHolder = document.getElementById("selected-test-title");
        var descrHolder = document.getElementById("selected-test-description");
        if (titleHolder == null || descrHolder == null)
            return;
        titleHolder.innerText = elem3.title;
        descrHolder.innerText = elem3.description;
    };
    var list = document.getElementById("list");
    if (list == null) {
        console.log("LIST == NULL");
        return;
    }
    list.appendChild(elem1.element);
    list.appendChild(elem2.element);
    list.appendChild(elem3.element);
    elem1.element.onclick(null);
}
//# sourceMappingURL=scripts.js.map