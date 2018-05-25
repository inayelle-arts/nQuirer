namespace create
{
	function jsonify(value: any): string
	{
		if (typeof value === typeof "" || typeof value === typeof 1)
			return "\"" + value + "\"";
		
		return value.jsonify();
	}
	
	class Test
	{
		private questions: Array<Question>;
		private creationId: number;
		private sideList: HTMLUListElement;
		private form: HTMLFormElement;
		private lastActiveQuestion: number;
		public title: string;
		public description: string;
		
		public get questionCount(): number
		{
			return this.questions.length;
		}
		
		constructor(side: HTMLUListElement, form: HTMLFormElement)
		{
			Question.init(side, form);
			
			this.sideList = side;
			this.form = form;
			
			this.questions = new Array<Question>(0);
			this.creationId = 1;
		}
		
		public addQuestion()
		{
			let question = new Question(this.creationId, this);
			question.bindEvents();
			this.questions.push(question);
			this.makeQuestionActive(this.creationId);
			this.creationId++;
		}
		
		public makeQuestionActive(id: number)
		{
			this.questions.forEach((value: Question, index: number) => {
				this.lastActiveQuestion = id;
				if (value !== null)
				{
					if (index + 1 === id)
						value.activate();
					else
						value.disable();
				}
			});
		}
		
		public makeDisabledAll()
		{
			this.questions.forEach((value: Question) => {
				value.disable();
			});
			this.lastActiveQuestion = 0;
		}
		
		public addAnswer(): void
		{
			if (this.lastActiveQuestion > 0)
				this.questions[this.lastActiveQuestion - 1].addAnswer();
		}
		
		public jsonify(): string
		{
			let result: string = "{";
			
			result += jsonify("title") + ":" + jsonify(this.title) + ",";
			result += jsonify("description") + ":" + jsonify(this.description) + ",";
			result += jsonify("questions") + ":[";
			this.questions.forEach((value, index, array) => {
				result += jsonify(value);
				if (index < array.length - 1)
					result += ",";
			});
			
			result += "]";
			result += "}";
			console.log(result);
			return result;
		}
	}
	
	class Question
	{
		private text: string;
		private id: number;
		private answers: Array<Answer>;
		private dom: HTMLDivElement;
		private sideDOM: HTMLLIElement;
		private parent: Test;
		private creationId: number;
		
		private static side: HTMLUListElement;
		private static form: HTMLFormElement;
		
		static init(side: HTMLUListElement, form: HTMLFormElement)
		{
			Question.side = side;
			Question.form = form;
		}
		
		constructor(id: number, parent: Test)
		{
			this.id = id;
			this.parent = parent;
			this.answers = new Array<Answer>(0);
			this.text = "";
			this.creationId = 1;
			
			
			let dom = document.createElement("div");
			let stringID = this.getStringId();
			
			dom.setAttribute("id", stringID);
			dom.innerHTML = "<div class=\"form-group\">" +
					"<label for=\"" + stringID + "-title\">Question #" + id + " title</label>" +
					"<input type=\"text\" class=\"form-control\" id=\"" + stringID + "-title\" placeholder=\"Question title...\">" +
					"</div>";
			dom.style.display = "none";
			this.dom = dom;
			
			let sideDOM = document.createElement("li");
			sideDOM.setAttribute("id", this.getSideStringId());
			sideDOM.innerHTML =
					"<h6>Question #" + id + "</h6>" +
					"<div id='" + this.getSideStringId() + " -text'></div>";
			
			sideDOM.classList.add("list-group-item");
			sideDOM.classList.add("btn");
			
			this.sideDOM = sideDOM;
			Question.side.appendChild(this.sideDOM);
			Question.form.appendChild(this.dom);
		}
		
		public getId(): number
		{
			return this.id;
		}
		
		public getStringId(): string
		{
			return "question-" + this.id;
		}
		
		public getSideStringId(): string
		{
			return "question-side-" + this.id;
		}
		
		public getDOM(): HTMLDivElement
		{
			return this.dom;
		}
		
		public getSideDOM(): HTMLLIElement
		{
			return this.sideDOM;
		}
		
		public activate(): void
		{
			this.sideDOM.classList.add("active");
			this.dom.style.display = "block";
		}
		
		public disable(): void
		{
			this.sideDOM.classList.remove("active");
			this.dom.style.display = "none";
		}
		
		public addAnswer()
		{
			let answer = new Answer(this.creationId, this);
			this.answers.push(answer);
			this.creationId++;
		}
		
		public removeAnswer(id: number): void
		{
			this.answers[id - 1].getDOM().remove();
			delete this.answers[id - 1];

//			this.answers.forEach((value: Answer, index: number) =>
//			{
//				value.id
//			});
		}
		
		public bindEvents(): void
		{
			let textInput = $("#" + this.getStringId() + "-title");
			
			textInput.on("change", () => {
				this.text = <string>textInput.val();
				this.sideDOM.getElementsByTagName("h6")[0].innerText = this.text;
			});
			
			let sideButton = $(this.sideDOM);
			let description = $("#test-info-block");
			sideButton.on("click", () => {
				this.parent.makeQuestionActive(this.id);
				description.hide();
			});
			
		}
		
		public getAnswers(): Array<Answer>
		{
			return this.answers;
		}
		
		public getText(): string
		{
			return this.text;
		}
		
		public jsonify(): string
		{
			let result = "{";
			
			result += jsonify("id") + ": " + jsonify(this.id) + ",";
			result += jsonify("content") + ": " + jsonify(this.text) + ",";
			result += jsonify("answers") + ": [";
			
			this.answers.forEach((value: Answer, index: number, array: Array<Answer>) => {
				if (value !== null)
				{
					result += jsonify(value);
					if (index < array.length - 1)
						result += ",";
				}
			});
			
			
			result += "]";
			result += "}";
			return result;
		}
	}
	
	class Answer
	{
		private _id: number;
		private _text: string;
		private _correct: boolean;
		private dom: HTMLDivElement;
		private parent: Question;
		
		public jsonify(): string
		{
			let result = "{";
			
			result += jsonify("id") + ": " + this._id + ",";
			result += jsonify("content") + ": " + jsonify(this._text) + ",";
			result += jsonify("isCorrect") + ": " + this._correct;
			
			result += "}";
			return result;
		}
		
		constructor(id: number, parent: Question)
		{
			this._id = id;
			this.parent = parent;
			
			this._text = "";
			this._correct = false;
			
			let dom = document.createElement("div");
			dom.classList.add("form-group");
			let stringId = this.getStringId();
			dom.innerHTML =
					"<label for=\"" + stringId + "\">Answer #" + id + "</label>" +
					"<div class=\"answer\">" +
					"<input type=\"text\" name=\"" + stringId + "\" id=\"" + stringId + "\" class=\"form-control\" placeholder=\"Answer...\">" +
					"<div class=\"btn-group\">" +
					"<button class=\"btn btn-secondary\" type='button' id='" + stringId + "-correct-button'>Correct</button>" +
					"<button class=\"btn btn-danger\" type='button' id='" + stringId + "-remove-button'>Remove</button>" +
					"</div>" +
					"</div>";
			
			parent.getDOM().appendChild(dom);
			
			$("#" + stringId + "-remove-button").on("click", () => {
				console.log("remove");
				parent.removeAnswer(this._id);
			});
			
			let correctButton = $("#" + stringId + "-correct-button");
			console.log("STRINGID: " + stringId);
			correctButton.on("click", () => {
				console.log("CORRECT: " + stringId);
				this._correct = !this._correct;
				if (this._correct)
				{
					correctButton.removeClass("btn-secondary");
					correctButton.addClass("btn-success");
				}
				else
				{
					correctButton.removeClass("btn-success");
					correctButton.addClass("btn-secondary");
				}
			});
			
			let input = $("#" + stringId);
			
			input.on("change", () => {
				this._text = <string>input.val();
			});
			
			this.dom = dom;
		}
		
		public getDOM(): HTMLDivElement
		{
			return this.dom;
		}
		
		public getStringId(): string
		{
			return this.parent.getStringId() + "-answer-" + this._id;
		}
		
		
		get id(): number
		{
			return this._id;
		}
		
		get text(): string
		{
			return this._text;
		}
		
		get correct(): boolean
		{
			return this._correct;
		}
	}
	
	
	let test: Test = null;
	let titleGood: boolean = false;
	
	$(() => {
		let sideList = <HTMLUListElement>document.getElementById("question-list");
		let form = <HTMLFormElement>document.getElementById("test-form");
		test = new Test(sideList, form);
		
		let addQuestionButton = $("#add-question-button");
		let addAnswerButton = $("#add-answer-button");
		let changeDescriptionButton = $("#change-description-button");
		
		let zeroQuestionsHelper = $("#zero-questions-helper");
		
		let testInfoBlock = $("#test-info-block");
		let description = $("#test-description");
		let title = $("#test-title");
		
		let testTitleHelper = $("#test-title-helper");
		
		let invite = $("#invite");
		
		addQuestionButton.on("click", () => {
			invite.hide();
			addQuestionButton.removeClass("border border-danger");
			zeroQuestionsHelper.hide();
			testInfoBlock.hide();
			addAnswerButton.show();
			test.addQuestion();
		});
		
		addAnswerButton.on("click", () => {
			test.addAnswer();
		});
		
		changeDescriptionButton.on("click", () => {
			test.makeDisabledAll();
			testInfoBlock.show();
		});
		
		function validateTitle()
		{
			test.title = <string>title.val();
			
			if (test.title === "" || test.title === null)
			{
				testTitleHelper.text("Test should have a title");
				testTitleHelper.show();
				title.addClass("border");
				title.addClass("border-danger");
				titleGood = false;
			}
			else
				$.ajax( //check if test with such title exists
						{
							url: "/validatetitlename",
							method: "POST",
							data: "title=" + test.title,
							processData: true,
							success: (response) => {
								if (response !== "good")
								{
									testTitleHelper.text("Test with such title already exists");
									testTitleHelper.show();
									title.addClass("border");
									title.addClass("border-danger");
									titleGood = false;
								}
								else
								{
									testTitleHelper.hide();
									title.removeClass("border");
									title.removeClass("border-danger");
									titleGood = true;
								}
							}
						}
				);
		}
		
		let timerID: number;
		
		title.on("change paste keyup", () => {
			timerID = setTimeout(validateTitle, 2000);
		});
		
		title.on("keydown", () =>
		{
			clearTimeout(timerID);
		});
		
		description.on("change", () => {
			test.description = <string>description.val();
		});
		
		
		$("#submit-test").on("click", () => {
			if (!titleGood)
			{
				test.makeDisabledAll();
				testInfoBlock.show();
			}
			console.log(test.questionCount);
			if (test.questionCount === 0)
			{
				zeroQuestionsHelper.text("Test should have at least one question!");
				addQuestionButton.addClass("border border-danger");
				zeroQuestionsHelper.show();
			}
			else
			{
				let testJSON = jsonify(test);
				$.ajax(
						{
							url: "#",
							method: "POST",
							data: {"json": testJSON},
							dataType: "application/json"
						}
				);
			}
			
		});
	});
}

