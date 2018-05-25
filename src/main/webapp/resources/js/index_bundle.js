var index;
(function (index_1) {
    var lastClickedItemId = null;
    function makeActive(id) {
        var go_test_btn = $("#go-test");
        if (lastClickedItemId !== null)
            $("#" + lastClickedItemId).removeClass("active");
        else {
            displayNone($("#invite"));
            displayBlock(go_test_btn);
        }
        var active = $("#" + id);
        var test_id = active.attr("data-id");
        go_test_btn.attr("href", "/pass?id=" + test_id);
        active.addClass("active");
        $("#test-descriptor-title").text(active.text());
        $("#test-descriptor-description").text(active.attr("data-description"));
        lastClickedItemId = id;
    }
    $(function () {
        $("li[id^='test-item']").each(function (index, element) {
            $(element).on("click", function () {
                makeActive($(element).attr("id"));
            });
        });
    });
})(index || (index = {}));
//# sourceMappingURL=index_bundle.js.map