class Test
{
	private title: string;
	private description: string;
	private id: string;
	private href: string;
	private element: HTMLElement;
	
	constructor(title: string, description: string, id: string, href: string)
	{
		this.title = title;
		this.description = description;
		this.id = id;
		this.href = href;
		
		let element = document.createElement("li");
		element.setAttribute("id", id);
		element.classList.add("list-group-item");
		element.classList.add("secondary");
		element.setAttribute("onclick", "onTestClicked(\"" + id + "\")");
		element.innerHTML +=
			"<h5>" + title + "</h5>" +
			description;
			
		this.element = element;
	}
	
	public getElement() : HTMLElement
	{
		return this.element;
	}
}

let lastTestClicked = "";

function onTestClicked(callerID: string)
{
	let element = document.getElementById(callerID);
	if (element == null)
		return;
	
	let prevElement = document.getElementById(lastTestClicked);
	if (prevElement != null)
	{
		prevElement.classList.remove("primary");
		prevElement.classList.add("secondary");
	}
	
	
	element.classList.remove("secondary");
	element.classList.add("primary");
	lastTestClicked = callerID;
}




function test()
{
	let elem1 = new Test("Qwe", "Rfv", "test-1", "#");
	let elem2 = new Test("Asd", "Tgv", "test-2", "#");
	let elem3 = new Test("Zxc", "Xcrvg", "test-3", "#");
	
	let list = document.getElementById("test-list");
	if (list == null)
	{
		console.log("LIST == NULL");
		return;
	}
	
	
	list.appendChild(elem1.getElement());
	list.appendChild(elem2.getElement());
	list.appendChild(elem3.getElement());
	onTestClicked("test-1");
}