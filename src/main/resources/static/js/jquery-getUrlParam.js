// (function ($) {
// 	$.getUrlParam = function (name) {
// 		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
// 		var r = window.location.search.substr(1).match(reg);
// 		if (r != null)
// 			return unescape(r[2]);
// 		return null;
// 	}
// })(jQuery);

(function ($) {
	$.getIdFromUrl = function () {
		return new URLSearchParams(window.location.search).get("id");
	};
})(jQuery);