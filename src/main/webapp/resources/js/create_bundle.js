var create;
(function (create) {
    function jsonify(value) {
        if (typeof value === typeof "" || typeof value === typeof 1)
            return "\"" + value + "\"";
        return value.jsonify();
    }
    var Test = /** @class */ (function () {
        function Test(side, form) {
            Question.init(side, form);
            this.sideList = side;
            this.form = form;
            this.questions = new Array(0);
            this.creationId = 1;
        }
        Object.defineProperty(Test.prototype, "questionCount", {
            get: function () {
                return this.questions.length;
            },
            enumerable: true,
            configurable: true
        });
        Test.prototype.addQuestion = function () {
            var question = new Question(this.creationId, this);
            question.bindEvents();
            this.questions.push(question);
            this.makeQuestionActive(this.creationId);
            this.creationId++;
        };
        Test.prototype.makeQuestionActive = function (id) {
            var _this = this;
            this.questions.forEach(function (value, index) {
                _this.lastActiveQuestion = id;
                if (value !== null) {
                    if (index + 1 === id)
                        value.activate();
                    else
                        value.disable();
                }
            });
        };
        Test.prototype.makeDisabledAll = function () {
            this.questions.forEach(function (value) {
                value.disable();
            });
            this.lastActiveQuestion = 0;
        };
        Test.prototype.addAnswer = function () {
            if (this.lastActiveQuestion > 0)
                this.questions[this.lastActiveQuestion - 1].addAnswer();
        };
        Test.prototype.jsonify = function () {
            var result = "{";
            result += jsonify("title") + ":" + jsonify(this.title) + ",";
            result += jsonify("description") + ":" + jsonify(this.description) + ",";
            result += jsonify("questions") + ":[";
            this.questions.forEach(function (value, index, array) {
                result += jsonify(value);
                if (index < array.length - 1)
                    result += ",";
            });
            result += "]";
            result += "}";
            console.log(result);
            return result;
        };
        return Test;
    }());
    var Question = /** @class */ (function () {
        function Question(id, parent) {
            this.id = id;
            this.parent = parent;
            this.answers = new Array(0);
            this.text = "";
            this.creationId = 1;
            var dom = document.createElement("div");
            var stringID = this.getStringId();
            dom.setAttribute("id", stringID);
            dom.innerHTML = "<div class=\"form-group\">" +
                "<label for=\"" + stringID + "-title\">Question #" + id + " title</label>" +
                "<input type=\"text\" class=\"form-control\" id=\"" + stringID + "-title\" placeholder=\"Question title...\">" +
                "</div>";
            dom.style.display = "none";
            this.dom = dom;
            var sideDOM = document.createElement("li");
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
        Question.init = function (side, form) {
            Question.side = side;
            Question.form = form;
        };
        Question.prototype.getId = function () {
            return this.id;
        };
        Question.prototype.getStringId = function () {
            return "question-" + this.id;
        };
        Question.prototype.getSideStringId = function () {
            return "question-side-" + this.id;
        };
        Question.prototype.getDOM = function () {
            return this.dom;
        };
        Question.prototype.getSideDOM = function () {
            return this.sideDOM;
        };
        Question.prototype.activate = function () {
            this.sideDOM.classList.add("active");
            this.dom.style.display = "block";
        };
        Question.prototype.disable = function () {
            this.sideDOM.classList.remove("active");
            this.dom.style.display = "none";
        };
        Question.prototype.addAnswer = function () {
            var answer = new Answer(this.creationId, this);
            this.answers.push(answer);
            this.creationId++;
        };
        Question.prototype.removeAnswer = function (id) {
            this.answers[id - 1].getDOM().remove();
            delete this.answers[id - 1];
            //			this.answers.forEach((value: Answer, index: number) =>
            //			{
            //				value.id
            //			});
        };
        Question.prototype.bindEvents = function () {
            var _this = this;
            var textInput = $("#" + this.getStringId() + "-title");
            textInput.on("change", function () {
                _this.text = textInput.val();
                _this.sideDOM.getElementsByTagName("h6")[0].innerText = _this.text;
            });
            var sideButton = $(this.sideDOM);
            var description = $("#test-info-block");
            sideButton.on("click", function () {
                _this.parent.makeQuestionActive(_this.id);
                description.hide();
            });
        };
        Question.prototype.getAnswers = function () {
            return this.answers;
        };
        Question.prototype.getText = function () {
            return this.text;
        };
        Question.prototype.jsonify = function () {
            var result = "{";
            result += jsonify("id") + ": " + jsonify(this.id) + ",";
            result += jsonify("content") + ": " + jsonify(this.text) + ",";
            result += jsonify("answers") + ": [";
            this.answers.forEach(function (value, index, array) {
                if (value !== null) {
                    result += jsonify(value);
                    if (index < array.length - 1)
                        result += ",";
                }
            });
            result += "]";
            result += "}";
            return result;
        };
        return Question;
    }());
    var Answer = /** @class */ (function () {
        function Answer(id, parent) {
            var _this = this;
            this._id = id;
            this.parent = parent;
            this._text = "";
            this._correct = false;
            var dom = document.createElement("div");
            dom.classList.add("form-group");
            var stringId = this.getStringId();
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
            $("#" + stringId + "-remove-button").on("click", function () {
                console.log("remove");
                parent.removeAnswer(_this._id);
            });
            var correctButton = $("#" + stringId + "-correct-button");
            console.log("STRINGID: " + stringId);
            correctButton.on("click", function () {
                console.log("CORRECT: " + stringId);
                _this._correct = !_this._correct;
                if (_this._correct) {
                    correctButton.removeClass("btn-secondary");
                    correctButton.addClass("btn-success");
                }
                else {
                    correctButton.removeClass("btn-success");
                    correctButton.addClass("btn-secondary");
                }
            });
            var input = $("#" + stringId);
            input.on("change", function () {
                _this._text = input.val();
            });
            this.dom = dom;
        }
        Answer.prototype.jsonify = function () {
            var result = "{";
            result += jsonify("id") + ": " + this._id + ",";
            result += jsonify("content") + ": " + jsonify(this._text) + ",";
            result += jsonify("isCorrect") + ": " + this._correct;
            result += "}";
            return result;
        };
        Answer.prototype.getDOM = function () {
            return this.dom;
        };
        Answer.prototype.getStringId = function () {
            return this.parent.getStringId() + "-answer-" + this._id;
        };
        Object.defineProperty(Answer.prototype, "id", {
            get: function () {
                return this._id;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(Answer.prototype, "text", {
            get: function () {
                return this._text;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(Answer.prototype, "correct", {
            get: function () {
                return this._correct;
            },
            enumerable: true,
            configurable: true
        });
        return Answer;
    }());
    var test = null;
    var titleGood = false;
    $(function () {
        var sideList = document.getElementById("question-list");
        var form = document.getElementById("test-form");
        test = new Test(sideList, form);
        var addQuestionButton = $("#add-question-button");
        var addAnswerButton = $("#add-answer-button");
        var changeDescriptionButton = $("#change-description-button");
        var zeroQuestionsHelper = $("#zero-questions-helper");
        var testInfoBlock = $("#test-info-block");
        var description = $("#test-description");
        var title = $("#test-title");
        var testTitleHelper = $("#test-title-helper");
        var invite = $("#invite");
        addQuestionButton.on("click", function () {
            invite.hide();
            addQuestionButton.removeClass("border border-danger");
            zeroQuestionsHelper.hide();
            testInfoBlock.hide();
            addAnswerButton.show();
            test.addQuestion();
        });
        addAnswerButton.on("click", function () {
            test.addAnswer();
        });
        changeDescriptionButton.on("click", function () {
            test.makeDisabledAll();
            testInfoBlock.show();
        });
        function validateTitle() {
            test.title = title.val();
            if (test.title === "" || test.title === null) {
                testTitleHelper.text("Test should have a title");
                testTitleHelper.show();
                title.addClass("border");
                title.addClass("border-danger");
                titleGood = false;
            }
            else
                $.ajax(//check if test with such title exists
                {
                    url: "/validatetitlename",
                    method: "POST",
                    data: "title=" + test.title,
                    processData: true,
                    success: function (response) {
                        if (response !== "good") {
                            testTitleHelper.text("Test with such title already exists");
                            testTitleHelper.show();
                            title.addClass("border");
                            title.addClass("border-danger");
                            titleGood = false;
                        }
                        else {
                            testTitleHelper.hide();
                            title.removeClass("border");
                            title.removeClass("border-danger");
                            titleGood = true;
                        }
                    }
                });
        }
        var timerID;
        title.on("change paste keyup", function () {
            timerID = setTimeout(validateTitle, 2000);
        });
        title.on("keydown", function () {
            clearTimeout(timerID);
        });
        description.on("change", function () {
            test.description = description.val();
        });
        $("#submit-test").on("click", function () {
            if (!titleGood) {
                test.makeDisabledAll();
                testInfoBlock.show();
            }
            console.log(test.questionCount);
            if (test.questionCount === 0) {
                zeroQuestionsHelper.text("Test should have at least one question!");
                addQuestionButton.addClass("border border-danger");
                zeroQuestionsHelper.show();
            }
            else {
                var testJSON = jsonify(test);
                $.ajax({
                    url: "#",
                    method: "POST",
                    data: { "json": testJSON },
                    dataType: "application/json"
                });
            }
        });
    });
})(create || (create = {}));
//# sourceMappingURL=create_bundle.js.map