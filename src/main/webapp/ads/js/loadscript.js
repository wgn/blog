var loadScript = ( function() {  
    var adQueue = [], dw = document.write;  
    function LoadADScript(url, container, init, callback) {  
        this.url = url;  
        this.containerObj = ( typeof container == 'string' ? document.getElementById(container) : container);  
        this.init = init ||  
        function() {  
        };  
 
        this.callback = callback ||  
        function() {  
        };  
 
    }  
 
    LoadADScript.prototype = {  
        startLoad : function() {  
            var script = document.createElement('script'), _this = this;  
 
            _this.init.apply();  
 
            if(script.readyState) {//IE  
                script.onreadystatechange = function() {  
                    if(script.readyState == "loaded" || script.readyState == "complete") {  
                        script.onreadystatechange = null;  
                        _this.startNext();  
                    }  
                };  
            } else {//Other  
                script.onload = function() {  
                    _this.startNext();  
                };  
            }  
            document.write = function(ad) {  
                var html = _this.containerObj.innerHTML;  
                _this.containerObj.innerHTML = html + ad;  
            }  
 
            script.src = _this.url;  
            script.type = 'text/javascript';  
            document.getElementsByTagName('head')[0].appendChild(script);
            document.getElementsByTagName('head')[0].removeChild(script);
        },  
        finished : function() {  
            document.write = this.dw;  
        },  
        startNext : function() {  
            adQueue.shift();  
            this.callback.apply();  
            if(adQueue.length > 0) {  
                adQueue[0].startLoad();  
            } else {  
                this.finished();  
            }  
        }  
    };  
 
    return {  
        add : function(adObj) {  
            if(!adObj)  
                return;  
 
            adQueue.push(new LoadADScript(adObj.url, adObj.container, adObj.init, adObj.callback));  
            return this;  
        },  
        execute : function() {  
            if(adQueue.length > 0) {  
                adQueue[0].startLoad();  
            }  
        }  
    };  
}());