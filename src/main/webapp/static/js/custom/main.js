if(typeof Object.create!="function") {
	Object.prototype.create = function(obj) {
		var F = function(){};
		F.prototype = obj;
		return new F();
	}
}