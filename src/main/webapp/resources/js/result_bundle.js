var result;
(function (result) {
    $(function () {
        $.expr[':'].regex = function (elem, index, match) {
            var matchParams = match[3].split(','), validLabels = /^(data|css):/, attr = {
                method: matchParams[0].match(validLabels) ?
                    matchParams[0].split(':')[0] : 'attr',
                property: matchParams.shift().replace(validLabels, '')
            }, regexFlags = 'ig', regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g, ''), regexFlags);
            return regex.test(jQuery(elem)[attr.method](attr.property));
        };
        var questionSides = $("li:regex(id,question-side-.*)");
        var questionDivs = $("div:regex(id, question-.*)");
        var answers = $("li:regex(id, question-.*-answer-.*)");
        $(questionSides).each(function (index, element) {
            var jelem = $(element);
            jelem.on("click", function () {
                $(questionSides).each(function (index, element) {
                    $(element).removeClass("active");
                    var qid = $(element).attr("data-qid");
                    $("#question-" + qid).hide();
                });
                jelem.addClass("active");
                var qid = jelem.attr("data-qid");
                $("#question-" + qid).show();
            });
        });
        $(questionSides[0]).trigger("click");
        var form = $("#test-result-form");
        answers.each(function (index, element) {
            var jelem = $(element);
            var userAns = (jelem.attr("data-user-correct") === "true");
            var trueAns = (jelem.attr("data-correct") === "true");
            if (trueAns === true)
                jelem.addClass("bg-success");
            if (userAns === true && userAns != trueAns)
                jelem.addClass("bg-danger");
        });
    });
})(result || (result = {}));
//# sourceMappingURL=result_bundle.js.map