namespace pass
{
    function jsonify(value: any): string
    {
        if (typeof value === typeof "" || typeof value === typeof 1)
            return "\"" + value + "\"";

        return value.jsonify();
    }

    $(() =>
    {
        $.expr[':'].regex = function (elem, index, match)
        {
            let matchParams = match[3].split(','),
                validLabels = /^(data|css):/,
                attr = {
                    method: matchParams[0].match(validLabels) ?
                        matchParams[0].split(':')[0] : 'attr',
                    property: matchParams.shift().replace(validLabels, '')
                },
                regexFlags = 'ig',
                regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g, ''), regexFlags);
            return regex.test(jQuery(elem)[attr.method](attr.property));
        };

        let questionSides = $("li:regex(id,question-side-.*)");
        let questionDivs = $("div:regex(id, question-.*)");

        $("li:regex(id, question-.*-answer-.*)").each((index, element) =>
        {
            let jelem = $(element);
            jelem.on("click", () =>
            {
                if (jelem.hasClass("bg-success"))
                {
                    jelem.removeClass("bg-success");
                    jelem.attr("data-correct", "false");
                }
                else
                {
                    jelem.addClass("bg-success");
                    jelem.attr("data-correct", "true");
                }
            });
        });

        $(questionSides).each((index, element) =>
        {
            let jelem = $(element);
            jelem.on("click", () =>
            {
                $(questionSides).each((index, element) =>
                {
                    $(element).removeClass("active");
                    let qid = $(element).attr("data-qid");
                    $("#question-" + qid).hide();
                });

                jelem.addClass("active");
                let qid = jelem.attr("data-qid");
                $("#question-" + qid).show();
            });

        });

        $(questionSides[0]).trigger("click");

        $("#test-pass-form").on("submit", () =>
        {
            prepare();
            $("input[name='json']").val();
        });

        function prepare()
        {
            let result = "{";

            result += jsonify("id") + ":" + $("#test-pass-form").attr("data-tid") + ",";
            result += jsonify("questions") + ":[";

            $(questionDivs).each((index, element) =>
            {
                let jelem = $(element);
                result += "{";
                result += jsonify("id") + ":" + jelem.attr("data-qid") + ",";
                result += jsonify("answers") + ":[";

                let lis = jelem.find("li");

                lis.each((index, element) =>
                {
                    result += "{";
                    result += jsonify("id") + ":" + $(element).attr("data-aid") + ",";
                    result += jsonify("isUserCorrect") + ":" + $(element).attr("data-correct");
                    result += "}";
                    if (index < lis.length - 1)
                        result += ",";
                });
                result += "]";
                result += "}";
                if (index < questionDivs.length - 1)
                    result += ",";
            });

            result += "]";
            result += "}";

            $("input[name='json']").val(result);
        }
    });

}