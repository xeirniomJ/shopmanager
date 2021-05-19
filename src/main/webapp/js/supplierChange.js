var shop_unique=getCookie("shop_unique");
$(function(){
	//页面加载后，自动加载省份
	loadProvince();
	//修改省份，自动加载城市列表
	$("#provinces").change(loadCities);
	//城市修改后，自动加载区县列表
	$("#cities").change(loadContries);
});