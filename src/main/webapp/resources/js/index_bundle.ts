namespace index
{
	let lastClickedItemId: string = null;
	
	function makeActive(id: string)
	{
		let go_test_btn = $("#go-test");
		
		if (lastClickedItemId !== null)
			$("#" + lastClickedItemId).removeClass("active");
		else
		{
			displayNone($("#invite"));
			displayBlock(go_test_btn);
		}
		
		let active = $("#" + id);
		let test_id = active.attr("data-id");
		go_test_btn.attr("href", "/pass?id=" + test_id);
		active.addClass("active");
		$("#test-descriptor-title").text(active.text());
		$("#test-descriptor-description").text(active.attr("data-description"));
		
		lastClickedItemId = id;
	}
	
	$(() =>
	{
		$("li[id^='test-item']").each((index, element) =>
		{
			$(element).on("click", () =>
			{
				makeActive($(element).attr("id"));
			});
		});
	});
}