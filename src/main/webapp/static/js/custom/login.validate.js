(function($,window,document){
	$.fn.loginvalidate = function(opts) {
		var validate = $.fn.validate;
		opts = initRulesAndMessages(opts);
		var options = $.extend($.fn.validate.prototype,$.fn.loginvalidate.opts,opts);
		validate.call(this,options);
		return this;
	};
	/**
	 * 初始化规则,如果设置了replaceR或者replaceM会完全替换规则和消息，否则只是在rules中添加
	 */
	function initRulesAndMessages(opts) {
		if(opts&&opts.rules) {
			if(opts.replaceR) {
				$.fn.loginvalidate.opts.rules = opts.rules
			} else {
				$.fn.loginvalidate.opts.rules = $.extend($.fn.loginvalidate.opts.rules,opts.rules);
			}
			delete opts.rules;
		}
		if(opts&&opts.messages) {
			if(opts.replaceM) {
				$.fn.loginvalidate.opts.messages = opts.messages
			} else {
				$.fn.loginvalidate.opts.messages = $.extend($.fn.loginvalidate.opts.messages,opts.messages);
			}
			delete opts.messages;
		}
		return opts;
	};
	$.fn.loginvalidate.opts = {
		rules:{
			username:"required",
			password:"required"
		},
		messages:{
			username:"用户名不能为空",
			password:"用户密码不能为空"
		},
		 /*错误提示位置*/
        errorPlacement: function (error, element) {//验证消息放置的地方
        	error.insertAfter(element.closest('.form-control'));
        },
        highlight: function(element, errorClass) { //针对验证的表单设置高亮
        	$(element).addClass(errorClass);
        },
		submitHandler: function (form) {
			form.submit();
		}
	};
	
})(jQuery,window,document);