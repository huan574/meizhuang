;(function(doc, win){
	function Rotation(obj){
		this.wraper = doc.getElementById(obj.el) //窗口
		this.sliders = this.wraper.getElementsByClassName('sliders')[0] //图片父盒子
		this.slideList = this.sliders.getElementsByClassName('slider') //所有图片
		this.length = this.slideList.length
		this.index = 1 //当前显示的图片的索引
		this.timer = null //单张图片运动定时器
		this.animation = null //自动轮播定时器

		// 在obj中可以自定义的参数
		this.mode = 'easy-in-out'//动画曲线，可选 'linear'
		this.step = 5 //匀速运动滚动步长
		this.delay = 2500 //轮播间隔
		this.duration = 40 //单张图片动画时长
		this.auto = true //是否开启自动轮播
		this.btn = false //是否有左右按钮
		this.focusBtn = true //是否支持焦点事件

		for(var k in obj)
			this[k] = obj[k]
		if(this.btn){
			this.prev = this.wraper.getElementsByClassName('prev')[0]
			this.next = this.wraper.getElementsByClassName('next')[0]
		}
		if(this.focusBtn){
			this.pagenation = this.wraper.getElementsByClassName('pagenation')[0]
			this.pageList = this.pagenation.getElementsByClassName('page')
			this.activePage = 0 //当前的焦点的索引
		}
		this.init()
	}

	var D = Rotation.prototype
	/*
	 * func init
	 * 初始化函数
	 * @para 0 
	 */
	D.init = function(){
		this.width = this.wraper.clientWidth
		if(this.mode == 'linear')
			this.duration = 1
		for(var i=0; i<this.length; i++)
			this.slideList[i].style.width = this.width + 'px'

		this.sliders.style.width = this.width * this.length + 'px'
		this.sliders.style.marginLeft = -this.width + "px";
		this.handler()
		this.auto && this.autoPlay()
	}

	D.getStyle = function(obj, attr){
		return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj, false)[attr];	
	}
	/*
	 * @method bind
	 * 事件绑定函数
	 * bind events 
	 */
	D.handler = function(){
		var _th = this,i=0
		if(this.btn){
			this.prev.onclick = function(){
				_th.turnLeft();
			}
			this.next.onclick = function(){
				_th.turnRight();
			}
		}
		if(this.focusBtn){
			for(; i<this.pageList.length; i++){
				this.pageList[i].key = i
				this.pageList[i].onmouseover=function(){
					_th.index = this.key + 1
					_th.toggleClass()
				}
			}
		}
		this.wraper.onmouseover = function(){
			clearInterval(_th.animation);
		}
		this.wraper.onmouseout = function(){
			_th.auto && _th.autoPlay()
		}
	}
	/*
	 * func turnRight
	 * 向右轮播函数
	 * @para 0
	 */
	D.turnRight = function(){
		this.index++;
		if(this.index == this.length-1){
			this.index=1;
			this.sliders.style.marginLeft = 0;
		}
		this.toggleClass();	
	}
	/*
	 * func turnLeft
	 * 向左轮播函数
	 * @para 0
	 */
	D.turnLeft = function(){
		this.index--;
		if(this.index == 0){
			this.index = this.length-2;
			this.sliders.style.marginLeft = -this.width * (this.length-1) + "px";
		}
		this.toggleClass();
	}
	/*
	 * func toggleClass
	 * 改变数字焦点样式,轮播动画核心函数调用
	 * @para 0
	 */
	D.toggleClass = function(){
		if(this.focusBtn){
			this.pageList[this.activePage].className = "page";
			this.pageList[this.index-1].className = "page page-active";
			this.activePage = this.index-1;
		}
		this.slide(-this.index * this.width);
	}
	/*
	 * func slide
	 * 图片滚动函数，核心函数
	 * @param ins 滚动终点
	 */
	D.slide = function(ins){
		var _th = this
		// 防止动画过度过程中计算错误
		if(_th.timer) clearInterval(_th.timer);

		_th.timer = setInterval(move,_th.duration);

		function move(){

			var currentPosition = parseFloat(_th.sliders.style.marginLeft);
			// 根据起始点和目标位置的比较确定向哪个方向移动
			var n = ins-currentPosition<0?-1:1;

			if(Math.abs(ins-currentPosition) < _th.step){
				_th.sliders.style.marginLeft = ins + "px";
				clearInterval(_th.timer);
			}else{
				// 变速运动关键，注释变为匀速运动
				if(_th.mode == 'easy-in-out')
					_th.step = Math.abs(ins-currentPosition)/5
				_th.sliders.style.marginLeft = currentPosition + n*_th.step + "px";
			}
			
		}
	}
	/*
	 * func autoPlay
	 * 自动轮播函数
	 * @para 0
	 */
	D.autoPlay = function(){
		var _th = this
		clearInterval(_th.animation)
		_th.animation = setInterval(function(){
			_th.turnRight();
		},_th.delay)
	}
	/*
	 * func debounce
	 * 节流函数
	 * @para fn<要执行的函数> delay<节流时间>
	 * @value func
	 */
	D.debounce = function(fn,delay){
		var timer = null
		return function(){
			if(timer){
				clearTimeout(timer)
			}
			timer = setTimeout(fn,delay)
		}
	}
	/*
	 * func refresh
	 * 自动刷新函数，这里采用节流是防止刷新操作太过于频繁导致性能下降
	 * @para 0
	 */
	D.refresh = function(){
		var _th = this
		this.debounce(function(){
			_th.init()
			_th.toggleClass()
		},100)()
	}
	/*
	 * func rotation
	 * 实例化函数
	 * @para obj 实例化的具体参数
	 * @value 返回具体实例
	 */
	win.rotation = function(obj){
		return new Rotation(obj)
	}
})(document, window)