;
(function() {
	'use strict';
	/**
	 * 图片御览器组件。
	 * @ author rbai
	 * @ create time 2015.01.10
	 * @ version  1.0.0
	 */
	$.oasUiFactory("oasPicView", {
		options: {

			infoTpl: null,

			// 下一页和上一页时是否循环
			loop: true,

			data: []
		},


		_template: function() {

			var options = this.options,
				tpl = '<div class="picView oas-picview">'+
						'<div class="oas-picview-content clearfix">'+
							'<div class="oas-picview-pic-wrap">'+
								'<div class="oas-picview-pic">'+
									'<div class="oas-picview-pic-content"><img /></div>'+
									'<div class="oas-picview-pic-info">'+
										'<span class="current-index">第{{current}}张图片</span>'+
										'<a href="javascript:;" target="_blank" class="oas-picview-showReal">查看原图</a>'+
									'</div>'+
								'</div>'+
								
								'</a><a href="javascript:;" title="上一张" class="oasicon oasicon-prev oas-picview-prev"></a>'+
								'<a href="javascript:;" title="下一张" class="oasicon oasicon-next oas-picview-next"></a>'+
							'</div>'+

							'<div class="oas-picview-pic-expand">{{#infoHtml}}</div>'+
						'</div>'+

						'<div class="oas-picview-prevlist-wrap"><ul class="oas-picview-prevlist clearfix">'+
							'{{each data}}'+
							'<li class="{{if $index===current-1}}oas-picview-current{{/if}}" {{if $value.info}}data-oastooltip-title="{{$value.info}}"{{/if}} breviary-src="{{$value.breviarySrc}}" data-oastooltip-placement="top"></li>'+    
							'{{/each}}'+
						'</ul></div>'+
					'</div>';
			
			return template.compile(tpl);
		},


		events: {
			'click .oas-picview-prev': function(){
				this._prev();
			},

			'click .oas-picview-next': function(){
				this._next();
			},
			
			'click .oas-picview-prevlist>li': function(self){
				var index = $(self).index();
				this._jumpTo(index + 1);
			}
		},


		_create: function() {
			var self = this,
				$el = this.$el,
				options = this.options;

			$el.addClass('oas-picview');

			this._prevRenderData();

			this._createDom();

			// 获取部分参数
			this._getAttr();

			// 加载所有的图片
			this._loadImgs();


			this._jumpTo(1);

			this._addJustWidth();
		},

		// 获取部分参数
		_getAttr: function(){
			this.IMG_MAX_WIDTH = $('.oas-picview-pic').width();
			this.IMG_MAX_HEIGHT = $('.oas-picview-pic').height();
			this.PAGENUM = 1;
		},

		// 准备渲染数据
		_prevRenderData: function(){
			var options = this.options,
				renderData = this.renderData = {};

			if(options.infoTpl){
				this.infoTpl = template.compile(options.infoTpl);
			}

			this.infoTpl && (renderData.infoHtml = this.infoTpl(options.data[0]));
			renderData.data = options.data;
			renderData.current = 1;
		},

		// 插入节点
		_createDom: function(){
			var $el = this.$el;
			$el.append(this.tpl(this.renderData));
		},


		// 加载所有的图片
		_loadImgs: function(){
			var $el = this.$el,
				self = this;

			$.each($el.find('.oas-picview-prevlist li'), function(i){
				var $self = $(this);
				(function(i){
					var url = $self.attr('breviary-src');
					
					$('<img src="' + url +'" />').hide().appendTo($self).fadeIn();
				})(i);
				
			});
		},


		_next: function(){
			var $el = this.$el,
				options = this.options,
				data = options.data,
				length = data.length;
			if(this.current < length){
				this.current++;
			}else{
				if(options.loop){
					this.current = 1;
				}else{
					this.current = length;
				}
			}

			this._jumpTo(this.current);
		},

		_prev: function(){
			var $el = this.$el,
				options = this.options,
				data = options.data,
				length = data.length;
			if(this.current > 1){
				this.current--;
			}else{
				if(options.loop){
					this.current = length;
				}else{
					this.current = 1;
				}
			}

			this._jumpTo(this.current);
		},

		_jumpTo: function(current){

			var $el = this.$el,
				data = this.options.data,
				self = this,
				length = data.length,
				pageNum = Math.floor(current / 4);

			if(this.current !== current){
				this.current = current;
			}

			// 加载对应的图片
			var imgSrc = data[current - 1].imgsrc;
			this._loadImg(imgSrc, function(size){
				var $img = $el.find('.oas-picview-pic-content').find('img');
				var width = size.width > self.IMG_MAX_WIDTH ? self.IMG_MAX_WIDTH : size.width;
				var height = size.height > self.IMG_MAX_HEIGHT ? self.IMG_MAX_HEIGHT : size.height;
				$img[0].width = width;
				$img[0].height = height;
				$img[0].src = imgSrc;
				$img.css({'opacity': 0, 'marginLeft': -width/2, 'marginTop': -height/2}).show();
				$img.animate({opacity: 1}, 150);
				
				$el.find('.current-index').html('第&nbsp;'+ self.current +'&nbsp;张图片');
			});

			if(this.PAGENUM !== pageNum){
				this.PAGENUM = pageNum;
				$el.find('.oas-picview-prevlist-wrap').animate({'scrollLeft': (pageNum - 1) * 300}, 200);
			}

			$el.find('.oas-picview-pic-expand').html(this.infoTpl(data[current - 1]));

			$el.find('.oas-picview-showReal').attr('href', data[current - 1].realsrc);

			$el.find('.oas-picview-prevlist li').removeClass('oas-picview-current').eq(current - 1).addClass('oas-picview-current').attr('data-oastooltip-title', data[current - 1].info);
		},

		_loadImg: function(src, callback){
			var img = new Image();
			if($.browser.msie){
				img.onreadystatechange = function(){
					if(this.readyState === 'complete'){
						callback && callback({width: img.width, height: img.height});
						img = null;
					}
				}
			}else{
				img.onload = function(){
					if(img.complete == true){
						callback && callback({width: img.width, height: img.height});
						img = null;
					}
				}
			}
			img.src = src;
		},

		// 适应宽度
		_addJustWidth: function(){
			var width = $('.oas-picview-prevlist li').width();
			$('.oas-picview-prevlist').width($('.oas-picview-prevlist li').size() * (75 + 8));
		},
		
		// 对外方法集
		invoke: {
			next: function(){
				this._next();
			},

			prev: function(){
				this._prev();
			},

			jumpTo: function(current){
				this._jumpTo(current);
			}
		}
	});
})();