namespace result
{
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
		
		let answers = $("li:regex(id, question-.*-answer-.*)");
		
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
		
		let form = $("#test-result-form");
		
		answers.each((index, element) =>
		{
			let jelem = $(element);
			
			let userAns = (jelem.attr("data-user-correct") === "true");
			let trueAns = (jelem.attr("data-correct") === "true");
			
			if (trueAns === true)
				jelem.addClass("bg-success");
			
			if (userAns === true && userAns != trueAns)
				jelem.addClass("bg-danger");
		});
		
	});
	
}