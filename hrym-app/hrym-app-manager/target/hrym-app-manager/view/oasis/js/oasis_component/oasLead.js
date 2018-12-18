/**
 * 引导页组件
 * @ author jguo
 * @ create time 2015.04.15
 * @ version  1.0.0
 */
 ;
(function() {
	// 组件名和文件名请统一
	$.oasUiFactory("oasLead", {
		options: {
			//分步的内容
			data: [],
			//当前的位置
			current: 1,
			//动画速度
			speed:300,
			//打开回调事件
			onOpen:function($el){

			},
			//关闭回调事件
			onClose:function($el){

			},
			onNoLongerClose:function($el,self){

			}
		},

		// 组件的模板解析，如果有模版，请使用这个方法。
		_template: function() {			
			var tpl = '<div class="oas-lead-content">'+
							'<div class="slide-holder">'+
								'<ul class="slide-runner">'+
									'{{each data}}'+
										'<li><div class="lead-img"><span class="patch"></span><img {{if $value.id}}id="{{$value.id}}"{{/if}} src="{{$value.src}}"/></div></li>'+
									'{{/each}}'+
								'</ul>'+
								'<p class="slide-nav">'+
									'{{each data}}'+
										'<a href="javascript:;" class="oasicon oasicon-dot {{if $index === current-1}}current{{/if}}"></a>'+
									'{{/each}}'+
								'</p>'+
								'<a href="javascript:;" class="slide-left slide-btn"></a>'+
								'<a href="javascript:;" class="slide-right slide-btn"></a>'+
								'<a href="javascript:;" class="close-lead"></a>'+
								'<a href="javascript:;" class="iknow"></a>'+
								'<a href="javascript:;" class="jump"></a>'+
							'</div>'+
						'</div>'+
						'<div class="lead-shadow"></div>';
						

			return template.compile(tpl);
		},

		// 事件
		events: {
			//左右箭头点击事件
			'click .oas-lead-content .slide-btn': function(self, evt) {
				var options = this.options,
					$el = this.$el;

				if($(self).hasClass('slide-left')){
				 	$el.find(".slide-right").show();	
					this.current--;
					$el.find('.slide-runner').animate({'left': -(this.current-1) * $el.find(".slide-runner li").outerWidth()},options.speed);
					if(this.current == 1){
						$el.find(".slide-left").hide();	
					}
					$el.find(".iknow").hide();
					$el.find(".jump").show();
					this._setCurrent(this.current);	
				}else{
					$el.find(".slide-left").show();
					this.current++;
					$el.find('.slide-runner').animate({'left': -(this.current-1) * $el.find(".slide-runner li").outerWidth()},options.speed);
					if(this.current == options.data.length){
						$el.find(".slide-right").hide();
						$el.find(".iknow").show();
						$el.find(".jump").hide();	
					}
					this._setCurrent(this.current);
				}
			},
			//导航小点的点击事件
			'click .slide-nav a': function(self, evt) {
				var options = this.options,
					$el = this.$el;
				 if(!$(self).hasClass('current')){
					this.current = $(self).index() + 1;
					$el.find('.slide-runner').animate({'left': -(this.current-1) * $el.find(".slide-runner li").outerWidth()},options.speed);
					$el.find('.slide-nav a').removeClass('current');
					$(self).addClass('current');
					if(this.current == 1){
						$el.find(".slide-left").hide();
						$el.find(".slide-right").show();
						$el.find(".iknow").hide();
						$el.find(".jump").show();
					}else if(this.current == options.data.length){
						$el.find(".slide-right").hide();
						$el.find(".slide-left").show();
						$el.find(".iknow").show();
						$el.find(".jump").hide();
					}else{
						$el.find(".slide-left").show();
						$el.find(".slide-right").show();
						$el.find(".iknow").hide();
						$el.find(".jump").show();
					}
				}
			},

			'click .oas-lead-content .close-lead': function(self, evt) {
				this._iCall("close");
			},
			'click .oas-lead-content .jump': function(self, evt) {
				this._iCall("close");
			},
			'click .oas-lead-content .iknow': function(self, evt) {
				this._iCall("noLongerClose");
			}

		},

		_create: function() {
			var options = this.options;

			this.current = options.current;

			this._preRenderData();
			
		},

		// 准备所有需要渲染的数据
		_preRenderData: function() {
			var options = this.options,
				_renderData = {},
				data = this.data || (this.data = options.data)
				current = this.current || (this.current = options.current);

			this._renderData = _renderData = {
				data: data,
				current: current
			};

			this._createDom();

		},

		//计算宽度
		_setWidth: function() {
			var options = this.options,
				$el = this.$el,
				size = options.data.length;
				width = $el.find(".slide-runner li").outerWidth()*size;
			$el.find(".slide-runner").width(width);
			//初始时slide-runner位置
			var left = -(this.current-1) * $el.find(".slide-runner li").outerWidth();
			$el.find(".slide-runner").css({'left': left});
		},

		//设置btn显隐
		_setBth: function() {
			var $el = this.$el,
				options = this.options;
			if(this.current == 1){
				$el.find(".slide-left").hide();
				$el.find(".iknow").hide();
				$el.find(".jump").show();
			}else{
				$el.find(".slide-left").show();
				$el.find(".iknow").hide();
				$el.find(".jump").show();
			}
			if(this.current == options.data.length){
				$el.find(".slide-right").hide();
				$el.find(".iknow").show();
				$el.find(".jump").hide();
			}else{
				$el.find(".slide-right").show();
				$el.find(".iknow").hide();
				$el.find(".jump").show();
			}
		},
		//判断slide-nav的选中
		_setCurrent: function(current) {
			var $el = this.$el,
				$lis = $el.find('.slide-nav a');

				$lis.removeClass('current');
				$lis.eq(current - 1).addClass('current');
		},

		/**创建dom节点**/
		_createDom: function() {
			var html = this.tpl(this._renderData),
				$el = this.$el;
			$el.addClass('oas-lead');
			$el.empty().append(html);
			
			this._setWidth();
			this._setBth();
			this._setCurrent(this.current);
		},
		// 组件对外方法集
		invoke: {
			//打开操作面板
			open: function(_opts) {
				var options = this.options,
					$el = this.$el;
				if(_opts){
					this.data = options.data = _opts.data;
					this.current = options.current = _opts.current;
					options.speed = _opts.speed;
					this._create();
				}
				
				if( this.result != false){
					$el.show();
					Mask.open();
					this._emit('onOpen',[$el]);
				}
				
			},
			//关闭操作面板
			close: function() {
				var options = this.options,
					$el = this.$el;
				$el.hide();
				Mask.close();
				this._emit('onClose',[$el]);
			},
			//关闭且不再出现
			noLongerClose: function() {
				var options = this.options,
					$el = this.$el;
				$el.remove();
				Mask.close();
				this._emit('onNoLongerClose',[$el]);
			},
			data: function(_data){
				var options = this.options;
				this.data = options.data = _data;
				this._preRenderData();
			}
		}
	});

})();