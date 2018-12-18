;
(function() {
	'use strict';
	/**
	 * 瀑布流组件。
	 * @ author rbai
	 * @ create time 2015.01.09
	 * @ version  1.0.0
	 */
	$.oasUiFactory("oasWaterfall", {
		options: {

			// 是否需要滚动加载更多
			isScrollLoading: false,
			// 每个间隔
			itemSpace: 10,

			// 每一项的宽度
			itemWidth: 240,

			// 每一项的选择器
			itemSelector: null,

			itemTpl: null,

			data: null,

			// 当加载更多时触发
			onLoadMore: function(){

			}
		},


		_template: function() {

			var options = this.options,
				tpl = '{{each $data}}<div class="oas-waterfall-item"><img src="{{$value.imgsrc}}" height="{{$value.height}}" realsrc="{{$value.imgsrc}}" alt="{{$value.title}}"></div>{{/each}}';
			
			return template.compile(tpl);
		},


		events: {
			'click .oas-waterfall-loadmore': function(self){
				if($(self).hasClass('isloading')){return false}
				this._emit('onLoadMore');
				this.$el.find('.oas-waterfall-loadmore').addClass('isloading').text('正在加载中...');
			}
			
		},


		_create: function() {
			var self = this,
				$el = this.$el,
				options = this.options,
				itemSelector = options.itemSelector;

			this._preAttrs();

			$el.addClass('oas-waterfall');

			if(options.itemSelector && !!$el.find(itemSelector).size()){

			}else{
				$el.append('<div class="oas-waterfall-wrap"></div><div class="oas-waterfall-loadmore">加载更多图片</div>');
			}

			$el.find('.oas-waterfall-wrap').css('position', 'relative');

			this._iCall('data', [options.data]);

			this._bindEvent();
		},

		// 准备瀑布流所必须的属性
		_preAttrs: function(){
			var $el = this.$el,
				options = this.options,
				elwidth = this.elwidth = $el.width();

			this.lines = Math.floor(elwidth/(options.itemWidth + options.itemSpace));//计算当前容器宽度下可以放下多

			$el.attr({})//设置容器无法被鼠标复制

			$el.width(this.lines*(options.itemWidth + options.itemsSpace)- options.itemsSpace);//给容器赋宽度值以居中显示

			this.curColHeights =[];//每列的高度并成数组形式
			//设置数组初始值为零
			for(var i=0;i<this.lines;i++){
				this.curColHeights[i] = 0;	
			}
		},


		_bindEvent: function(){
			var self = this,
				$el = this.$el,
				options = this.options,
				timeout;
			$(window).on('resize.oasWaterfall',function(){
				var elwidth = $el.width();
				if(self.elwidth !== elwidth && Math.floor(elwidth/(options.itemWidth + options.itemSpace))!== self.lines){
					setTimeout(function(){
						self._preAttrs();
						self._doResize();
					},10);
				}
			});

			if(options.isScrollLoading){
				$(window).on("scroll.oasWaterfall",function(){
					timeout && clearTimeout(timeout);
					timeout = setTimeout(function() {

						if ($(document).height()-$(window).scrollTop()-$(window).height() < 100) {
							self._emit('onLoadMore');
							$el.find('.oas-waterfall-loadmore').addClass('isloading');
						}
					}, 120);
				});
			}
		},


		// 获取数组高度最小列和对应的值
		_getColumnMax: function(){
			var curColHeights = this.curColHeights,
				maxHeight = Math.max.apply(Math, curColHeights);
			
			return {"maxHeight":maxHeight, "maxColumn":oasis.indexOf(maxHeight, curColHeights)}
		},

		// 获取数组高度最大列和对应的值
		_getColumnMin: function(){
			var curColHeights = this.curColHeights,
				minHeight = Math.min.apply(Math, curColHeights);

			
			return {"minHeight":minHeight,"minColumn":oasis.indexOf(minHeight, curColHeights)};
		},

		// 设置父元素
		_setElementHeight: function(){
			this.$el.find('.oas-waterfall-wrap').height(this._getColumnMax().maxHeight);
		},

		// 设置每一个的位置
		_setPosition: function(isAll){
			var options = this.options,
				$el = this.$el,
				$tempItems = $el.find('.oas-waterfall-item'),
				allItemsNumber = $tempItems.size(),//获取所有的对象个数
				positionNumber = isAll ? 0 : (allItemsNumber - this.datas.length);//获取需要定位的对象个数;
			
			if(isAll){
				for(var i = 0; i < this.lines; i++){
					this.curColHeights[i] = 0;	
				}
			}

			for(var i = positionNumber; i < allItemsNumber; i++){
				var min = this._getColumnMin(),
					$item = $tempItems.eq(i);
				$item.css({left: min.minColumn * (options.itemWidth + options.itemSpace),top:min.minHeight + options.itemSpace}).animate({opacity:1},1000);//对象赋值定位
				$item.find('img').width(options.itemWidth);
				this.curColHeights[min.minColumn] += $item.height()+ options.itemSpace;//最短值累加
			}
			this._setElementHeight();
		},

		// 执行resize事件
		_doResize: function(){
			var $el = this.$el,
				$items = this.$el.find('.oas-waterfall-item'),
				options = this.options;
			var allItemsNumber = $items.size();//获取所有的对象个数
			for(var i = 0;i < allItemsNumber;i ++){
				var min = this._getColumnMin();
				$items.eq(i).animate({left: min.minColumn * (options.itemWidth + options.itemSpace),top: min.minHeight + options.itemSpace},500);//对象赋值定位
				this.curColHeights[min.minColumn] += $items.eq(i).height()+ options.itemSpace;//最短值累加
			}
			this._setElementHeight();
		},
		
		// 对外方法集
		invoke: {

			// 设置数据
			data: function(items, isAdd){

				if(!items) return;

				this.datas = items;
				var options = this.options,
					itemTpl = options.itemTpl,
					tpl,
					html;

				if(itemTpl){
					try{
						tpl = template.compile('{{each $data}}'+ itemTpl + '{{/each}}');
					}catch(e){
						throw new Error('自定义模版错误');
					}
				}else{
					tpl = this.tpl;
				}


				html = tpl(items);
				
				if(isAdd){
					this.$el.find('.oas-waterfall-wrap').append(html);
					this._setPosition();
				}else{
					this.$el.find('.oas-waterfall-wrap').empty().append(html);
					this._setPosition(true);
				}
				
				

				this.$el.find('.oas-waterfall-loadmore').removeClass('isloading').text('加载更多图片');
			},

			// 添加数据
			add: function(items){
				this._iCall('data', [items, true]);
			}
		}
	});
})();