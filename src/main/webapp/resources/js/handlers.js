"use strict";
var TEST_PRIMARY_STYLE = "primary-test-card";
var TEST_SECONDARY_STYLE = "secondary-test-card";
var QUESTION_PRIMARY_STYLE = "primary-question-card";
var QUESTION_SECONDARY_STYLE = "secondary-question-card";
var lastPrimaryCardID = 1;
var lastPrimaryQuestionID = 0;
var totalQuestions = 0;
var totalAnswers = 2;
var questions = [];
function hideElement(element) {
    element.setAttribute("hidden", "");
}
function showElement(element) {
    element.removeAttribute("hidden");
}
function setTestAsPrimary(id) {
    var card = document.getElementById("test-" + id);
    var title = document.getElementById("test-title");
    var titleElem = document.getElementById("test-title-" + id);
    var description = document.getElementById("test-description");
    var descriptionElem = document.getElementById("test-description-" + id);
    if (card === null || title === null || titleElem === null || description === null || descriptionElem === null)
        return;
    title.innerText = titleElem.innerText;
    description.innerText = descriptionElem.innerText;
    card.classList.remove(TEST_SECONDARY_STYLE);
    card.classList.add(TEST_PRIMARY_STYLE);
}
function setTestAsSecondary(id) {
    var card = document.getElementById("test-" + id);
    if (card === null)
        return;
    card.classList.remove(TEST_PRIMARY_STYLE);
    card.classList.add(TEST_SECONDARY_STYLE);
}
function onTestClicked(callerID) {
    setTestAsSecondary(lastPrimaryCardID);
    setTestAsPrimary(callerID);
    lastPrimaryCardID = callerID;
}
function setQuestionAsPrimary(callerID) {
    var question = document.getElementById("question-" + callerID);
    if (question === null)
        return;
    readQuestion(callerID);
    question.classList.remove(QUESTION_SECONDARY_STYLE);
    question.classList.add(QUESTION_PRIMARY_STYLE);
}
function setQuestionAsSecondary(callerID) {
    var question = document.getElementById("question-" + callerID);
    if (question === null)
        return;
    question.classList.remove(QUESTION_PRIMARY_STYLE);
    question.classList.add(QUESTION_SECONDARY_STYLE);
}
function onQuestionClicked(callerID) {
    saveQuestion();
    setQuestionAsSecondary(lastPrimaryQuestionID);
    setQuestionAsPrimary(callerID);
    lastPrimaryQuestionID = callerID;
}
function addQuestionClicked() {
    saveQuestion();
    var commitButton = document.getElementById("commit-button");
    if (commitButton != null)
        commitButton.removeAttribute("hidden");
    ++totalQuestions;
    if (totalQuestions == 1) {
        var form = document.getElementById("test-question-form");
        if (form !== null)
            form.removeAttribute("hidden");
    }
    var questionList = document.getElementById("question-list");
    if (questionList === null)
        return;
    var newQuestion = document.createElement("li");
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
function saveQuestion() {
    var answerList = document.getElementById("answer-list");
    if (answerList == null)
        return;
    localStorage.setItem(lastPrimaryQuestionID + "-html", answerList.innerHTML);
    var inputs = answerList.getElementsByTagName("input");
    var i = 0;
    while (i < inputs.length) {
        var current = inputs.item(i);
        if (current.type == "checkbox") {
            var str = current.checked ? "true" : "false";
            localStorage.setItem("ELEM-" + lastPrimaryQuestionID + "-check-" + i, str);
        }
        else
            localStorage.setItem("ELEM-" + lastPrimaryQuestionID + "-input-" + i, current.value);
        i++;
    }
}
function readQuestion(id) {
    var answerList = document.getElementById("answer-list");
    if (answerList == null)
        return;
    var html = localStorage.getItem(id + "-html");
    if (html != null)
        answerList.innerHTML = html;
    var inputs = answerList.getElementsByTagName("input");
    var i = 0;
    while (i < inputs.length) {
        var current = inputs.item(i);
        if (current.type == "checkbox") {
            var str = localStorage.getItem("ELEM-" + id + "-check-" + i);
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
function addAnswerClicked() {
    ++totalAnswers;
    var newAnswer = document.createElement("div");
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
    var answerList = document.getElementById("answer-list");
    if (answerList !== null)
        answerList.appendChild(newAnswer);
}
var RequestMethod;
(function (RequestMethod) {
    RequestMethod["GET"] = "GET";
    RequestMethod["POST"] = "POST";
    RequestMethod["PUT"] = "PUT";
    RequestMethod["DELETE"] = "DELETE";
    RequestMethod["PATCH"] = "PATCH";
})(RequestMethod || (RequestMethod = {}));
var RequestState;
(function (RequestState) {
    RequestState[RequestState["NEW"] = 0] = "NEW";
    RequestState[RequestState["OPENED"] = 1] = "OPENED";
    RequestState[RequestState["SENT"] = 2] = "SENT";
    RequestState[RequestState["PATRYALLY_READY"] = 3] = "PATRYALLY_READY";
    RequestState[RequestState["READY"] = 4] = "READY";
})(RequestState || (RequestState = {}));
var AJAX = /** @class */ (function () {
    //	private onSuccess: StateChangedEvent[];
    //	private onFailure: StateChangedEvent[];
    //	private onSent: StateChangedEvent[];
    function AJAX(method, handler) {
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
    AJAX.prototype.request = function (params, async) {
        var req = new XMLHttpRequest();
        req.open(this.method, this.handler, async);
        req.onreadystatechange = function () {
            var currentState = req.readyState;
            var statusCode = req.status;
            if (currentState == 4 && statusCode == 200) {
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
    };
    return AJAX;
}());
function commitTestClicked() {
    var arr = {};
    saveQuestion();
    arr["title"] = document.getElementById("test-title").value;
    for (var i = 0; i < localStorage.length; i++) {
        var key = localStorage.key(i);
        if (key.match("^ELEM-[1-9]-input|check-[0-9]$"))
            arr[key] = localStorage.getItem(key);
    }
    console.log();
    var ajax = new AJAX(RequestMethod.GET, "/receiver.php?data=" + JSON.stringify(arr));
    ajax.request(arr, true);
}
//# sourceMappingURL=handlers.js.map