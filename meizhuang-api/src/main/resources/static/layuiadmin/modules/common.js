layui.define(function(e) {
	var i = (layui.$, layui.layer, layui.laytpl, layui.setter, layui.view, layui.admin);
	i.events.logout = function() {
		layer.confirm('确定退出？', function(index){
			i.req({
				url: "/logout",
				type: "post",
				data: {},
				done: function(e) {
					i.exit(function() {
						location.href = "/login"
					})
				}
			})
		});
	},
	i.events.refreshResourceAuthority = function() {
		layer.confirm('确定刷新资源权限？', function(index){
			i.req({
				url: "/refreshResourceAuthority",
				type: "post",
				data: {},
				done: function(res) {
					layer.msg(res.msg, {icon: 1, time: 1000}, function() {
						window.location.reload();
					});
				}
			})
		});
	},e("common", {})
});