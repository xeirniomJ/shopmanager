var manager_unique = getCookie("manager_unique");// 获取管理员身份标识符
var manager_token=getCookie("manager_toke");//管理员登录后的密钥
var sup=2;
// 进入主页面后
$(function() {
	$(".div2").click(div2_click);
	$("#indexHtml").click(loadHome);
	loadHome();
});
//单击首页，加载主界面
function loadHome(){
	$("#indexHtml").addClass("checked");
	$("#rightFrame").attr("src","html/manager/home.html");
}
// 进入主界面后截取管理员等级，分别显示不同等级管理员的不同权限
(function() {
	var datas = window.location.search;
	var level = datas.substring(datas.indexOf("level=") + 6, datas.indexOf("level=")+7);
	if (level == sup) {
		$(".supper").show();
	}
})();
// 点击li列表，显示该li对应页面
function openurl(url, btn) {
	$(btn).parents("div").find("li").removeClass("checked");
	var ran = Math.random().toFixed(2);
	url += "?par=" + ran;
	$("#rightFrame").attr("src", url);
	$(btn).addClass("checked");
}
// 点击左侧菜单列表，显示隐藏子列表
function div2_click() {
	var datas = window.location.search;
	var level = datas.substring(datas.indexOf("level=") + 6, datas.indexOf("level=")+7);
	$(this).parent().children().find("img").css("display", "none");
	$(".div2").removeClass("checked");
	$(this).next("div").find("li").removeClass("checked");
	if(level!=sup){
		$(this).next("div").find("li").not(".supper").eq(0).click();
	}else{
		$(this).next("div").find("li").eq(0).click();
	}
	$(this).next("div").slideToggle("slow").siblings(".div3:visible").slideUp("slow");
	$(this).addClass("checked");
	$(this).children().show();
}