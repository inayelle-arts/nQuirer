let userDefinedElements: Array<SideElement> = [];
let lastActiveElementId: string;

class SideElement
{
	private _title: string;
	private _description: string;
	private _id: string;
	private _element: HTMLElement;
	
	constructor(title: string, description: string, id: string)
	{
		this._title = title;
		this._description = description;
		this._id = id;
		
		let element = document.createElement("li");
		
		element.setAttribute("id", this._id);
		element.setAttribute("onclick", "makeActive(\"" + id + "\")");
		element.classList.add("list-group-item");
		element.classList.add("secondary");
		element.innerHTML +=
			"<h5>" + title + "</h5>" +
			description;
		
		
		this._element = element;
	}
	
	get title(): string
	{
		return this._title;
	}
	
	set title(value: string)
	{
		this._title = value;
	}
	
	get description(): string
	{
		return this._description;
	}
	
	set description(value: string)
	{
		this._description = value;
	}
	
	get id(): string
	{
		return this._id;
	}
	
	set id(value: string)
	{
		this._id = value;
	}
	
	get element()
	{
		return this._element;
	}
}

function makeActive(id: string)
{
	let elem = document.getElementById(id);
	if (elem != null)
	{
		elem.classList.remove("secondary");
		elem.classList.add("primary");
	}
	makeUnactive(lastActiveElementId);
	lastActiveElementId = id;
}

function makeUnactive(id: string)
{
	let elem = document.getElementById(id);
	if (elem != null)
	{
		elem.classList.remove("primary");
		elem.classList.add("secondary");
	}
}


function test()
{
	let text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus architecto eius exercitationem natus nesciunt optio unde vero. Blanditiis doloremque eum expedita molestias nesciunt nulla, optio placeat quod similique ullam voluptate!";
	let elem1 = new SideElement("Kek", text, "elem-1");
	let elem2 = new SideElement("Lol", text, "elem-2");
	let elem3 = new SideElement("Qwe", text, "elem-3");
	
	elem1.element.onclick = function ()
	{
		makeActive("elem-1");
		let titleHolder = document.getElementById("selected-test-title");
		let descrHolder = document.getElementById("selected-test-description");
		
		if (titleHolder == null || descrHolder == null)
			return;
		
		titleHolder.innerText = elem1.title;
		descrHolder.innerText = elem1.description;
	};
	
	elem2.element.onclick = function ()
	{
		makeActive("elem-2");
		let titleHolder = document.getElementById("selected-test-title");
		let descrHolder = document.getElementById("selected-test-description");
		
		if (titleHolder == null || descrHolder == null)
			return;
		
		titleHolder.innerText = elem2.title;
		descrHolder.innerText = elem2.description;
	};
	
	elem3.element.onclick = function ()
	{
		makeActive("elem-3");
		let titleHolder = document.getElementById("selected-test-title");
		let descrHolder = document.getElementById("selected-test-description");
		
		if (titleHolder == null || descrHolder == null)
			return;
		
		titleHolder.innerText = elem3.title;
		descrHolder.innerText = elem3.description;
	};
	
	let list = document.getElementById("list");
	if (list == null)
	{
		console.log("LIST == NULL");
		return;
	}
	
	list.appendChild(elem1.element);
	list.appendChild(elem2.element);
	list.appendChild(elem3.element);
	elem1.element.onclick(null);
}
