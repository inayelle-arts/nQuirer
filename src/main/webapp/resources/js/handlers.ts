const TEST_PRIMARY_STYLE = "primary-test-card";
const TEST_SECONDARY_STYLE = "secondary-test-card";
const QUESTION_PRIMARY_STYLE = "primary-question-card";
const QUESTION_SECONDARY_STYLE = "secondary-question-card";

let lastPrimaryCardID = 1;
let lastPrimaryQuestionID = 0;
let totalQuestions = 0;
let totalAnswers = 2;

let questions: Array<[number, string]> = [];

function hideElement(element: HTMLElement)
{
	element.setAttribute("hidden", "");
}

function showElement(element: HTMLElement)
{
	element.removeAttribute("hidden");
}

function setTestAsPrimary(id: number): void
{
	let card = document.getElementById("test-" + id);
	let title = document.getElementById("test-title");
	let titleElem = document.getElementById("test-title-" + id);
	let description = document.getElementById("test-description");
	let descriptionElem = document.getElementById("test-description-" + id);
	
	if (card === null || title === null || titleElem === null || description === null || descriptionElem === null)
		return;
	
	title.innerText = titleElem.innerText;
	description.innerText = descriptionElem.innerText;
	
	card.classList.remove(TEST_SECONDARY_STYLE);
	card.classList.add(TEST_PRIMARY_STYLE);
}

function setTestAsSecondary(id: number): void
{
	let card = document.getElementById("test-" + id);
	if (card === null)
		return;
	
	card.classList.remove(TEST_PRIMARY_STYLE);
	card.classList.add(TEST_SECONDARY_STYLE);
}

function onTestClicked(callerID: number)
{
	setTestAsSecondary(lastPrimaryCardID);
	setTestAsPrimary(callerID);
	
	lastPrimaryCardID = callerID;
}

function setQuestionAsPrimary(callerID: number)
{
	let question = document.getElementById("question-" + callerID);
	if (question === null)
		return;
	
	readQuestion(callerID);
	
	question.classList.remove(QUESTION_SECONDARY_STYLE);
	question.classList.add(QUESTION_PRIMARY_STYLE);
}

function setQuestionAsSecondary(callerID: number)
{
	let question = document.getElementById("question-" + callerID);
	if (question === null)
		return;
	
	question.classList.remove(QUESTION_PRIMARY_STYLE);
	question.classList.add(QUESTION_SECONDARY_STYLE);
}

function onQuestionClicked(callerID: number)
{
	saveQuestion();
	
	setQuestionAsSecondary(lastPrimaryQuestionID);
	setQuestionAsPrimary(callerID);
	
	lastPrimaryQuestionID = callerID;
}

function addQuestionClicked()
{
	saveQuestion();
	
	let commitButton = document.getElementById("commit-button");
	if (commitButton != null)
		commitButton.removeAttribute("hidden");
	
	++totalQuestions;
	
	if (totalQuestions == 1)
	{
		let form = document.getElementById("test-question-form");
		if (form !== null)
			form.removeAttribute("hidden");
	}
	
	let questionList = document.getElementById("question-list");
	if (questionList === null)
		return;
	
	let newQuestion = document.createElement("li");
	newQuestion.innerText = "q" + totalQuestions.toString();
	newQuestion.classList.add("secondary-question-card");
	newQuestion.setAttribute("id", "question-" + totalQuestions.toString());
	newQuestion.setAttribute("onclick", "onQuestionClicked(" + totalQuestions.toString() + ")");
	questionList.appendChild(newQuestion);
	
	onQuestionClicked(totalQuestions);
}

//function saveQuestion()
//{
//	let questionTextElem = document.getElementById("test-question");
//
//	let questionText = "";
//
//	if (questionTextElem != null)
//		questionText = questionTextElem.value;
//
//	localStorage.setItem(lastPrimaryQuestionID + "-question-text", questionText);
//}

function saveQuestion()
{
	let answerList = document.getElementById("answer-list");
	if (answerList == null)
		return;
	
	localStorage.setItem(lastPrimaryQuestionID + "-html", answerList.innerHTML);
	
	let inputs = answerList.getElementsByTagName("input");
	let i = 0;
	while (i < inputs.length)
	{
		let current = inputs.item(i);
		
		if (current.type == "checkbox")
		{
			let str = current.checked ? "true" : "false";
			localStorage.setItem("ELEM-" + lastPrimaryQuestionID + "-check-" + i, str);
		}
		else
			localStorage.setItem("ELEM-" + lastPrimaryQuestionID + "-input-" + i, current.value);
		i++;
	}
}

function readQuestion(id: number)
{
	let answerList = document.getElementById("answer-list");
	if (answerList == null)
		return;
	
	let html = localStorage.getItem(id + "-html");
	if (html != null)
		answerList.innerHTML = html;
	
	let inputs = answerList.getElementsByTagName("input");
	let i = 0;
	while (i < inputs.length)
	{
		let current = inputs.item(i);
		
		if (current.type == "checkbox")
		{
			let str = localStorage.getItem("ELEM-" + id + "-check-" + i);
			if (str == "true")
				current.checked = true;
			else
				current.checked = false;
		}
		else
			current.value = localStorage.getItem("ELEM-" + id + "-input-" + i);
		
		
		i++;
	}
}

//function readQuestion(id: number)
//{
//	let questionTextElem = document.getElementById("test-question");
//
//	let text = localStorage.getItem(id + "-question-text");
//
//	if (text == null)
//		text = "";
//
//	if (questionTextElem != null)
//		questionTextElem.value = text;
//}

function addAnswerClicked()
{
	++totalAnswers;
	
	let newAnswer = document.createElement("div");
	newAnswer.classList.add("form-group");
	newAnswer.innerHTML = "<div class=\"row align-items-center\">" +
		"<div class=\"col-9\">" +
		"<label for=\"test-answer-" + totalAnswers + "\" class=\"d-inline\">Answer text</label>" +
		"<input type=\"text\" class=\"form-control\" id=\"test-answer-" + totalAnswers + "\" placeholder=\"Enter possible answer...\">" +
		"</div>" +
		"<div class=\"col\">" +
		"<label class=\"form-check-label d-inline\">" +
		"<input type=\"checkbox\" class=\"form-check-input\" id=\"test-answer-" + totalAnswers + "check\">" +
		"Mark as correct" +
		"</label>" +
		"</div>" +
		"</div>";
	
	
	let answerList = document.getElementById("answer-list");
	if (answerList !== null)
		answerList.appendChild(newAnswer);
}

enum RequestMethod
{
	GET = "GET",
	POST = "POST",
	PUT = "PUT",
	DELETE = "DELETE",
	PATCH = "PATCH"
}

enum RequestState
{
	NEW = 0,
	OPENED,
	SENT,
	PATRYALLY_READY,
	READY
}

type StateChangedEvent = (state: RequestState, statusCode: number) => void;

class AJAX
{
	private method: RequestMethod;
	private handler: string;
//	private onSuccess: StateChangedEvent[];
//	private onFailure: StateChangedEvent[];
//	private onSent: StateChangedEvent[];
	
	constructor(method: RequestMethod, handler: string)
	{
		this.method = method;
		this.handler = handler;
	}

//	public addOnSuccessHandler(functor: StateChangedEvent)
//	{
//		this.onSuccess.push(functor);
//	}
//
//	public addOnSentHandler(functor: StateChangedEvent)
//	{
//		this.onSent.push(functor);
//	}
//
//	public addOnFailureHandler(functor: StateChangedEvent)
//	{
//		this.onFailure.push(functor);
//	}
	
	public request(params: any, async: boolean): void
	{
		let req = new XMLHttpRequest();
		req.open(this.method, this.handler, async);
		
		req.onreadystatechange = () =>
		{
			let currentState = req.readyState;
			let statusCode = req.status;
			
			if (currentState == 4 && statusCode == 200)
			{
				alert(req.responseText);
			}

//			if (req.readyState == RequestState.SENT)
//			{
//				this.onSent.forEach(callback);
//				return;
//			}
//
//			if (req.status != 200)
//			{
//				this.onFailure.forEach(callback);
//				return;
//			}
//
//			if (req.readyState == RequestState.READY)
//			{
//				this.onSuccess.forEach(callback);
//				return;
//			}
		};
		
		req.send();
	}
}

function commitTestClicked()
{
	type Pair<T> = {key: string, value: T};
	
	var arr: {[key: string]: number;} = {};
	
	saveQuestion();

	arr["title"] = document.getElementById("test-title").value;
	
	
	for (let i = 0; i < localStorage.length; i++)
	{
		let key = localStorage.key(i);
		if (key.match("^ELEM-[1-9]-input|check-[0-9]$"))
			arr[key] = localStorage.getItem(key);
	}
	
	console.log();
	let ajax = new AJAX(RequestMethod.GET, "/receiver.php?data=" + JSON.stringify(arr));
	ajax.request(arr, true);
}